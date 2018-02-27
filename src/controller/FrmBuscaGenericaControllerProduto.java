package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import dao.FrmCadastroProdutosDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.Produto;

public class FrmBuscaGenericaControllerProduto implements Initializable {

	private FrmCadastroProdutosDAO produtosDAO = new FrmCadastroProdutosDAO();
	private FrmLancamentosLavagensController lavagens = new FrmLancamentosLavagensController();
	private TableView<Produto> tabelaProduto;

	@FXML
	private Button btnPesquisarBusca;

	@FXML
	private Button btnCancelarBusca;

	@FXML
	private BorderPane telaPesquisa;

	@FXML
	private ChoiceBox<String> cBoxPesquisa;

	@FXML
	private TextField textPesquisa;

	@FXML
	private TableColumn<Integer, Produto> colunaCodigo;

	@FXML
	private TableColumn<String, Produto> colunaNome;

	@FXML
	private TableColumn<Float, Produto> colunaPrecoVenda;

	@FXML
	private TableColumn<Date, Produto> colunaData;

	@FXML
	void selecionarProdutoEnter(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			Produto produto = tabelaProduto.getSelectionModel().getSelectedItem();

			lavagens.alimenarCamposProduto(produto);
		}
	}

	@FXML
	void procurarProdutoClick(ActionEvent event) {
		lavagens.abrirTelaBuscaProduto();
	}

	@FXML
	void selecionarProdutoClick(MouseEvent event) {
		if (event.getClickCount() == 2) {
			Produto produto = tabelaProduto.getSelectionModel().getSelectedItem();

			lavagens.alimenarCamposProduto(produto);
		}
	}

	@FXML
	void pesquisarBusca(ActionEvent event) {
		Produto produto = tabelaProduto.getSelectionModel().getSelectedItem();
		if (produto != null) {
			lavagens.alimenarCamposProduto(produto);
		}
	}

	@FXML
	void cancelarBusca(ActionEvent event) {
		FrmLancamentosLavagensController.stageTelaPesquisa.close();
	}

	@SuppressWarnings("unchecked")
	@FXML
	void pesquisarDadosEnter(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			if (cBoxPesquisa.getValue().equals("ID")) {
				if (textPesquisa.getText().equals("")) {
					alimentarTable();
				} else {
					try {
						tabelaProduto.setItems((ObservableList<Produto>) produtosDAO
								.capturarProdutosId(Integer.parseInt(textPesquisa.getText())));
					} catch (NumberFormatException e) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setHeaderText("Digite um valor inteiro válido!");
						alert.setContentText("Busca não efetuada!");
						alert.setTitle("Valores inválidos");
						alert.show();
					}
				}
			} else if (cBoxPesquisa.getValue().equals("NOME")) {
				if (textPesquisa.getText().equals("")) {
					alimentarTable();
				} else {
					tabelaProduto.setItems(produtosDAO.capturarProdutosNome(textPesquisa.getText()));
				}
			} else if (cBoxPesquisa.getValue().equals("PREÇO")) {
				if (textPesquisa.getText().equals("")) {
					alimentarTable();
				} else {
					tabelaProduto.setItems(produtosDAO.capturarProdutosPreco(Float.parseFloat(textPesquisa.getText())));
				}
			} else if (cBoxPesquisa.getValue().equals("DATA")) {
				if (textPesquisa.getText().equals("")) {
					alimentarTable();
				} else {
					tabelaProduto.setItems(produtosDAO.capturarProdutosData(textPesquisa.getText()));
				}
			}
		}
	}

	private void alimentarTable() {
		tabelaProduto.setItems(produtosDAO.capturarTodosProdutos());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> lista = FXCollections.observableArrayList("ID", "NOME", "PRECO", "DATA");

		cBoxPesquisa.setItems(lista);
		cBoxPesquisa.setValue("ID");

		colunaCodigo.setCellValueFactory(new PropertyValueFactory<Integer, Produto>("codigo"));
		colunaNome.setCellValueFactory(new PropertyValueFactory<String, Produto>("nome"));
		colunaPrecoVenda.setCellValueFactory(new PropertyValueFactory<Float, Produto>("precoVenda"));
		colunaData.setCellValueFactory(new PropertyValueFactory<Date, Produto>("dataCadastro"));
	}
}
