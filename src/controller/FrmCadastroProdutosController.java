package controller;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dao.FrmCadastroProdutosDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import model.Produtos;
import util.Util;

public class FrmCadastroProdutosController implements Initializable {

	private FrmCadastroProdutosDAO cadastroProdutos = new FrmCadastroProdutosDAO();

	@FXML
	private TableColumn<Produtos, Integer> tblColumnId;

	@FXML
	private TableColumn<Produtos, Date> tblColumnData;

	@FXML
	private TextField txtConversao;

	@FXML
	private DatePicker txtDate;

	@FXML
	private ChoiceBox<String> choiceFilter;

	@FXML
	private TableColumn<Produtos, String> tblColumnNome;

	@FXML
	private TextField txtFilter;

	@FXML
	private Tab hBoxConsulta;

	@FXML
	private Button btnFechar;

	@FXML
	private Button btnGravar;

	@FXML
	private TextField txtNome;

	@FXML
	private Button btnEditar;

	@FXML
	private HBox hBox;

	@FXML
	private Button btnCancelarCadastro;

	@FXML
	private TableColumn<Produtos, Float> tblColumnPreco;

	@FXML
	private Tab hBoxCadastro;

	@FXML
	private Button btnIncluir;

	@FXML
	private Button btnCancelar;

	@FXML
	private TableView<Produtos> tblProdutos;

	@FXML
	private Button btnExcluir;

	@FXML
	private TextField txtPreco;

	@FXML
	private Label lblCodigo;

	@FXML
	private TabPane tabPane;

	@FXML
	void verificarBotoes(ActionEvent event) {
		
	}

	@FXML
	void onKeyDelPressed(ActionEvent event) {
		
	}

	@FXML
	void handleGravar(ActionEvent event) {
		
	}

	@FXML
	void handleCancelarCadastro(ActionEvent event) {
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(hBoxConsulta);	
		limparCamposCadastro();	
	}

	@FXML
	void handleIncluir(ActionEvent event) {
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(hBoxCadastro);
		
		txtDate.setValue(LocalDate.now());
		lblCodigo.setText(cadastroProdutos.capturarProximoCodigo().toString());
	}

	@FXML
	void handleExcluir(ActionEvent event) {
		Produtos produto = tblProdutos.getSelectionModel().getSelectedItem();
		if(tblProdutos.getSelectionModel().getSelectedItem() != null) {
			if(Util.alertaExclusao() == ButtonType.OK) {
				cadastroProdutos.excluirProduto(produto);
			}
		}
	}

	@FXML
	void handleEditar(ActionEvent event) {
		Produtos produto = tblProdutos.getSelectionModel().getSelectedItem();
		if(produto != null) {
			alimentarCamposCadastro(produto);
		}
	}

	@FXML
	void handleCancelar(ActionEvent event) {
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(hBoxConsulta);
		
		limparCamposCadastro();
	}

	@FXML
	void handleFechar(ActionEvent event) {
		FrmContainerController.parentFrmCadastroProdutos.setVisible(false);
	}

	@FXML
	void onKeySearchPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			if (choiceFilter.getValue().equals("ID")) {
				if (txtFilter.getText().equals("")) {
					alimentarTable();
				} else {
					try {
						tblProdutos
								.setItems(cadastroProdutos.capturarProdutosId(Integer.parseInt(txtFilter.getText())));
					} catch (NumberFormatException e) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setHeaderText("Digite um valor inteiro válido!");
						alert.setContentText("Busca não efetuada!");
						alert.setTitle("Valores inválidos");
						alert.show();
					}
				}
			} else if (choiceFilter.getValue().equals("NOME")) {
				if (txtFilter.getText().equals("")) {
					alimentarTable();
				} else {
					tblProdutos.setItems(cadastroProdutos.capturarProdutosNome(txtFilter.getText()));
				}
			} else if (choiceFilter.getValue().equals("PREÇO")) {
				if (txtFilter.getText().equals("")) {
					alimentarTable();
				} else {
					tblProdutos.setItems(cadastroProdutos.capturarProdutosPreco(Float.parseFloat(txtFilter.getText())));
				}
			} else if (choiceFilter.getValue().equals("DATA")) {
				if (txtFilter.getText().equals("")) {
					alimentarTable();
				} else {
					tblProdutos.setItems(cadastroProdutos.capturarProdutosData(txtFilter.getText()));
				}
			}
		}
	}

	private void alimentarTable() {
		tblProdutos.setItems(cadastroProdutos.capturarTodosProdutos());
	}
	
	private void limparCamposCadastro() {
		txtDate.setValue(LocalDate.now());
		lblCodigo.setText(cadastroProdutos.capturarProximoCodigo().toString());
		txtNome.setText("");
		txtPreco.setText("");
		txtConversao.setText("");
	}
	
	private void alimentarCamposCadastro(Produtos produto) {
		
		lblCodigo.setText(cadastroProdutos.capturarProximoCodigo().toString());
		txtNome.setText(produto.getCodigo().toString());
		txtDate.setValue(LocalDate.now());
		txtPreco.setText(produto.getPrecoVenda().toString());
		txtConversao.setText(produto.getConversao().toString());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		choiceFilter.setItems(FXCollections.observableArrayList("ID", "NOME", "PREÇO", "DATA"));
		choiceFilter.setValue("ID");

		tblColumnId.setCellValueFactory(new PropertyValueFactory<Produtos, Integer>("codigo"));
		tblColumnNome.setCellValueFactory(new PropertyValueFactory<Produtos, String>("nome"));
		tblColumnPreco.setCellValueFactory(new PropertyValueFactory<Produtos, Float>("preco"));
		tblColumnData.setCellValueFactory(new PropertyValueFactory<Produtos, Date>("data"));

		alimentarTable();
	}
}