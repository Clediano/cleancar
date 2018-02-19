package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.FrmCadastroUsuarioDAO;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import model.Usuarios;

public class FrmCadastroUsuarioController implements Initializable {

	public boolean editar, incluir = false;

	@FXML
	private Button btnConfirmar;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private PasswordField txtConfirmarSenha;

	@FXML
	private Button btnFechar;

	@FXML
	private Button btnEditar;

	@FXML
	private ChoiceBox<String> choiceFilter;

	@FXML
	private TextField txtFilter;

	@FXML
	private TextField txtUsuario;

	@FXML
	private Label lblSenha;

	@FXML
	private HBox hBox;

	@FXML
	private TableView<Usuarios> tblLancamentos;

	@FXML
	private Tab hBoxCadastro;

	@FXML
	private Tab hBoxConsulta;

	@FXML
	private Button btnIncluir;

	@FXML
	private TabPane tabPane;

	@FXML
	private TableColumn<Usuarios, Integer> tblColumnId;

	@FXML
	private TableColumn<Usuarios, String> tblColumnUsuario;

	@FXML
	private TableColumn<Usuarios, String> tblColumnSenha;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnCancelarCadastro;

	@FXML
	private Label lblConfirmarSenha;

	@FXML
	private Button btnExcluir;

	@FXML
	void handleConfirmar(ActionEvent event) {
		if (incluir) {
			validarNovoCadastro();
		} else if (editar) {
			editarCadastroExistente();
		}
	}

	@FXML
	void onKeySearchPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			if (choiceFilter.getValue().equals("ID")) {
				if (txtFilter.getText().equals("")) {
					alimentarTable();
				} else {
					try {
						alimentarTabelaFiltradaCodigo(Integer.parseInt(txtFilter.getText()));
					} catch (NumberFormatException e) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setHeaderText("Digite um valor inteiro válido!");
						alert.setContentText("Busca não efetuada!");
						alert.setTitle("Valores inválidos.");
						alert.show();
					}
				}
			} else if (choiceFilter.getValue().equals("USUARIO")) {
				if (txtFilter.getText().equals("")) {
					alimentarTable();
				} else {
					alimentarTabelaFiltradaLogin(txtFilter.getText());
				}
			} else if (choiceFilter.getValue().equals("SENHA")) {
				if (txtFilter.getText().equals("")) {
					alimentarTable();
				} else {
					alimentarTabelaFiltradaSenha(txtFilter.getText());
				}
			} else {
				alimentarTable();
			}
		}
	}

	@FXML
	void handleIncluir(ActionEvent event) {
		limparTelaCadastro();
		txtUsuario.setDisable(false);

		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();

		selectionModel.select(hBoxCadastro);
		txtUsuario.setFocusTraversable(true);
		txtUsuario.requestFocus();

		incluir = true;
	}

	@FXML
	void handleExcluir(ActionEvent event) {
		FrmCadastroUsuarioDAO cadastro = new FrmCadastroUsuarioDAO();
		cadastro.removerUsuario(tblLancamentos.getSelectionModel().getSelectedItem().getCodigo());
		alimentarTable();
	}

	@FXML
	void handleEditar(ActionEvent event) {
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(hBoxCadastro);

		Usuarios usuario = tblLancamentos.getSelectionModel().getSelectedItem();
		if (usuario != null) {
			txtUsuario.setText(usuario.getUsuario());
			txtSenha.setText(usuario.getSenha());
			txtConfirmarSenha.setText(usuario.getSenha());

			editar = true;
			txtUsuario.setDisable(true);
		}
	}

	@FXML
	void handleCancelar(ActionEvent event) {
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		if (selectionModel.isEmpty()) {
			FrmContainerController.parentFrmCadastroClientes.setVisible(false);
		} else if (selectionModel.getSelectedItem().equals(hBoxCadastro)) {
			selectionModel.select(hBoxConsulta);

			limparTelaCadastro();
		} else {
			FrmContainerController.parentFrmCadastroClientes.setVisible(false);
		}
	}

	@FXML
	void handleFechar(ActionEvent event) {
		FrmContainerController.parentFrmCadastroClientes.setVisible(false);
	}

	@FXML
	void handleCancelarCadastro(ActionEvent event) {
		SingleSelectionModel<Tab> sm = tabPane.getSelectionModel();

		limparTelaCadastro();

		sm.select(hBoxConsulta);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		alimentarTable();

		// indica se há algo selecionado na tabela
		BooleanBinding algoSelecionado = tblLancamentos.getSelectionModel().selectedItemProperty().isNull();

		btnEditar.disableProperty().bind(algoSelecionado);
		btnExcluir.disableProperty().bind(algoSelecionado);

		choiceFilter.setItems(FXCollections.observableArrayList("ID", "USUARIO", "SENHA"));

		tblColumnId.setCellValueFactory(new PropertyValueFactory<Usuarios, Integer>("codigo"));
		tblColumnUsuario.setCellValueFactory(new PropertyValueFactory<Usuarios, String>("usuario"));
		tblColumnSenha.setCellValueFactory(new PropertyValueFactory<Usuarios, String>("senha"));

		choiceFilter.setValue("ID");
	}

	private void limparTelaCadastro() {
		txtUsuario.setText("");
		txtSenha.setText("");
		txtConfirmarSenha.setText("");
	}

	private void validarNovoCadastro() {
		FrmCadastroUsuarioDAO cadastro = new FrmCadastroUsuarioDAO();
		if (txtSenha.getText().equals(txtConfirmarSenha.getText())) {
			if (cadastro.verificarUsuario(txtUsuario.getText())) {
				mensagemWarningCad();
			} else {
				if(cadastro.cadastrarUsario(txtUsuario.getText(), txtSenha.getText())) {
					mensagemSucessCad();
					
					limparTelaCadastro();
					alimentarTable();
				}else {
					mensagemErrorCad();
				}
			}
		}else {
			mensagemWarningCad();
		}
	}

	public void editarCadastroExistente() {

		Usuarios usu = tblLancamentos.getSelectionModel().getSelectedItem();

		if (txtSenha.getText().equals(txtConfirmarSenha.getText())) {
			FrmCadastroUsuarioDAO cadastro = new FrmCadastroUsuarioDAO();

			Usuarios usuario = new Usuarios();
			usuario.setCodigo(usu.getCodigo());
			usuario.setSenha(txtSenha.getText());

			cadastro.editarUsuario(usuario);

			mensagemSucessEdit();

			editar = false;
			txtUsuario.setEditable(true);
		} else {
			mensagemWarningEdit();
		}
		SingleSelectionModel<Tab> sm = tabPane.getSelectionModel();

		limparTelaCadastro();
		alimentarTable();

		sm.select(hBoxConsulta);
	}

	public void alimentarTable() {
		ObservableList<Usuarios> list = new FrmCadastroUsuarioDAO().capturarTodosUsuarios();
		tblLancamentos.setItems(list);
	}

	public void alimentarTabelaFiltradaCodigo(Integer codigo) {
		ObservableList<Usuarios> list = new FrmCadastroUsuarioDAO().capturarUsuariosCodigo(codigo);
		tblLancamentos.setItems(list);
	}

	public void alimentarTabelaFiltradaLogin(String usuario) {
		ObservableList<Usuarios> list = new FrmCadastroUsuarioDAO().capturarUsuariosLogin(usuario);
		tblLancamentos.setItems(list);
	}

	public void alimentarTabelaFiltradaSenha(String senha) {
		ObservableList<Usuarios> list = new FrmCadastroUsuarioDAO().capturarUsuariosSenha(senha);
		tblLancamentos.setItems(list);
	}

	public void mensagemWarningCad() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText("Usuário não cadastrado.");
		alert.setContentText("Verifique as informações inseridas nos campos.");
		alert.setTitle("Erro ao cadastrar");
		alert.show();
	}

	public void mensagemSucessCad() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Parabéns!");
		alert.setContentText("Cadastro realizado com sucesso!");
		alert.setTitle("Cadastro de usuários");
		alert.show();
	}

	public void mensagemErrorCad() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Usuário não cadastrado.");
		alert.setContentText("Problemas ao conectar-se com o banco de dados.");
		alert.setTitle("Erro ao cadastrar");
		alert.show();
	}

	public void mensagemWarningEdit() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText("Usuário não cadastrado.");
		alert.setContentText("Verifique as informações inseridas nos campos.");
		alert.setTitle("Erro ao alterar");
		alert.show();
	}

	public void mensagemSucessEdit() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Parabéns!");
		alert.setContentText("Cadastro alterado com sucesso!");
		alert.setTitle("Cadastro de usuários");
		alert.show();
	}

	public void mensagemErrorEdit() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Usuário não alterado.");
		alert.setContentText("Problemas ao conectar-se com o banco de dados.");
		alert.setTitle("Erro ao alterar");
		alert.show();
	}
}