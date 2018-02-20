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
import javafx.scene.control.Alert.AlertType;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.Produtos;
import util.Util;

public class FrmCadastroProdutosController implements Initializable {

	private FrmCadastroProdutosDAO cadastroProdutos = new FrmCadastroProdutosDAO();

	@FXML
	private TableColumn<Produtos, Integer> tblColumnId;

	@FXML
	private TableColumn<Produtos, Float> tblColumnConversao;

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
		Produtos produto = new Produtos();

		produto.setCodigo(Integer.parseInt(lblCodigo.getText()));
		produto.setNome(txtNome.getText());
		produto.setDataCadastro(Util.asDate(txtDate.getValue()));
		produto.setPrecoVenda(Util.formatFloat(txtPreco));
		produto.setConversao(Util.formatFloat(txtConversao));
		
		if(cadastroProdutos.consultarExistenciaProduto(Integer.parseInt(lblCodigo.getText()))) {
			cadastroProdutos.editarProduto(produto);
			alimentarTable();
			selecioanrTelaConsulta();
		}else {
			if (!cadastroProdutos.adicionarProduto(produto)) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setHeaderText("Produto não cadastrado");
				alert.setContentText("Verifique sua conexão com o banco de dados!");
				alert.setTitle("Falha no cadastro.");
				alert.show();
			}
			alimentarTable();
			selecioanrTelaConsulta();
		}
		
	}

	@FXML
	void handleCancelarCadastro(ActionEvent event) {
		selecioanrTelaConsulta();
		limparCamposCadastro();
		desabilitaCamposCadastro();
	}

	private void selecioanrTelaCadastro() {
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(hBoxCadastro);
	}

	private void selecioanrTelaConsulta() {
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(hBoxConsulta);
	}

	@FXML
	void handleIncluir(ActionEvent event) {
		selecioanrTelaCadastro();
		habilitaCamposCadastro();

		txtDate.setValue(LocalDate.now());
		lblCodigo.setText(cadastroProdutos.capturarProximoCodigo().toString());
		txtNome.requestFocus();
	}

	@FXML
	void handleExcluir(ActionEvent event) {
		Produtos produto = tblProdutos.getSelectionModel().getSelectedItem();
		if (tblProdutos.getSelectionModel().getSelectedItem() != null) {
			if (Util.alertaExclusao() == ButtonType.OK) {
				cadastroProdutos.excluirProduto(produto);
				alimentarTable();
				selecioanrTelaConsulta();
			}
		}
	}

	@FXML
	void handleDoubleClick(MouseEvent event) {
		Produtos produto = tblProdutos.getSelectionModel().getSelectedItem();
		if (event.getClickCount() == 2) {
			alimentarCamposCadastro(produto);
			selecioanrTelaCadastro();
			desabilitaCamposCadastro();
		}
	}

	@FXML
	void handleEditar(ActionEvent event) {
		Produtos produto = tblProdutos.getSelectionModel().getSelectedItem();
		if (produto != null) {
			alimentarCamposCadastro(produto);
			habilitaCamposCadastro();
			selecioanrTelaCadastro();
		}else {
			habilitaCamposCadastro();
		}
	}
	
	private void habilitaCamposCadastro() {
		lblCodigo.setDisable(false);
		txtNome.setDisable(false);
		txtPreco.setDisable(false);
		txtConversao.setDisable(false);
		txtDate.setDisable(false);
		btnGravar.setDisable(false);
	}
	
	private void desabilitaCamposCadastro() {
		lblCodigo.setDisable(true);
		txtNome.setDisable(true);
		txtPreco.setDisable(true);
		txtConversao.setDisable(true);
		txtDate.setDisable(true);
		btnGravar.setDisable(true);
	}

	@FXML
	void handleCancelar(ActionEvent event) {
		limparCamposCadastro();
		desabilitaCamposCadastro();
		selecioanrTelaConsulta();
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

	private void formatarCampoMonetario() {
		Util.monetaryField(txtPreco);
		Util.monetaryField(txtConversao);
	}

	private void alimentarCamposCadastro(Produtos produto) {
		lblCodigo.setText(produto.getCodigo().toString());
		txtNome.setText(produto.getNome().toString());
		txtDate.setValue(Util.asLocalDate(produto.getDataCadastro()));
		txtPreco.setText(produto.getPrecoVenda().toString());
		txtConversao.setText(produto.getConversao().toString());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		choiceFilter.setItems(FXCollections.observableArrayList("ID", "NOME", "PREÇO", "DATA"));
		choiceFilter.setValue("ID");

		tblColumnId.setCellValueFactory(new PropertyValueFactory<Produtos, Integer>("codigo"));
		tblColumnNome.setCellValueFactory(new PropertyValueFactory<Produtos, String>("nome"));
		tblColumnPreco.setCellValueFactory(new PropertyValueFactory<Produtos, Float>("precoVenda"));
		tblColumnData.setCellValueFactory(new PropertyValueFactory<Produtos, Date>("dataCadastro"));
		tblColumnConversao.setCellValueFactory(new PropertyValueFactory<Produtos, Float>("conversao"));

		alimentarTable();
		formatarCampoMonetario();
		desabilitaCamposCadastro();
	}
}