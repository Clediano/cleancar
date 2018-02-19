package controller;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dao.FrmCadastroClienteDAO;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.Clientes;
import util.TextFieldFormatter;
import util.Util;

public class FrmCadastroClienteController implements Initializable {

	private FrmCadastroClienteDAO cadastroCliente;
	public boolean botaoEditarClicado = false;

	@FXML
	private DatePicker txtDate;

	@FXML
	private ChoiceBox<String> choiceFilter;

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
	private TextField txtEmail;

	@FXML
	private TextField txtTelefone;

	@FXML
	private TableView<Clientes> tblClientes;

	@FXML
	private Button btnEditar;

	@FXML
	private TextField txtCnpjCpf;

	@FXML
	private ChoiceBox<String> tipoPessoa;

	@FXML
	private HBox hBox;

	@FXML
	private Button btnCancelarCadastro;

	@FXML
	private Tab hBoxCadastro;

	@FXML
	private Button btnIncluir;

	@FXML
	private Label lblCodigo;

	@FXML
	private TextField txtSobrenome;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnExcluir;

	@FXML
	private TabPane tabPane;

	@FXML
	private TableColumn<Integer, Clientes> tblColumnId;

	@FXML
	private TableColumn<String, Clientes> tblColumnNome;

	@FXML
	private TableColumn<String, Clientes> tblColumnSobrenome;

	@FXML
	private TableColumn<String, Clientes> tblColumnCnpjCpf;

	@FXML
	private TableColumn<String, Clientes> tblColumnPessoa;

	@FXML
	private TableColumn<String, Clientes> tblColumnEmail;

	@FXML
	private TableColumn<Integer, Clientes> tblColumnTelefone;

	@FXML
	private TableColumn<Date, Clientes> tblColumnCadastrado;

	@FXML
	void onKeyDelPressed(KeyEvent key) {
		if (key.getCode() == KeyCode.DELETE) {
			Clientes cliente = tblClientes.getSelectionModel().getSelectedItem();
			cadastroCliente.removerCliente(cliente.getCodigo());

			alimentarTable();
		}
	}

	@FXML
	void handleGravar(ActionEvent event) {
		if (!botaoEditarClicado) {
			Clientes cliente = new Clientes();

			cliente.setNome(txtNome.getText());
			cliente.setSobrenome(txtSobrenome.getText());
			cliente.setCnpj(txtCnpjCpf.getText());
			cliente.setEmail(txtEmail.getText());
			cliente.setTelefone(txtTelefone.getText());
			cliente.setPessoa(tipoPessoa.getValue());
			cliente.setDataCadastro(Util.asDate(txtDate.getValue()));

			cadastroCliente.cadastrarCliente(cliente);

			alimentarTable();
			limparTelaCadastro();

			SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
			selectionModel.select(hBoxConsulta);

		} else {
			Clientes cliente = new Clientes();

			cliente.setCodigo(Integer.parseInt(lblCodigo.getText()));
			cliente.setNome(txtNome.getText());
			cliente.setSobrenome(txtSobrenome.getText());
			cliente.setCnpj(txtCnpjCpf.getText());
			cliente.setEmail(txtEmail.getText());
			cliente.setTelefone(txtTelefone.getText());
			cliente.setPessoa(tipoPessoa.getValue());
			cliente.setDataCadastro(Util.asDate(txtDate.getValue()));

			cadastroCliente.editarCliente(cliente);
			botaoEditarClicado = false;

			alimentarTable();
			limparTelaCadastro();

			SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
			selectionModel.select(hBoxConsulta);
		}

	}

	@FXML
	void handleCancelarCadastro(ActionEvent event) {
		FrmContainerController.parentFrmCadastroClientes.setVisible(false);
	}

	@FXML
	void handleIncluir(ActionEvent event) {
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(hBoxCadastro);

		limparTelaCadastro();
		habilitarCamposCadastro();

		txtNome.setFocusTraversable(true);

		tipoPessoa.setValue("Física");
		txtNome.requestFocus();
		lblCodigo.setText(new FrmCadastroClienteDAO().proximoCodigoCliente().toString());
		txtDate.setValue(LocalDate.now());
	}

	@FXML
	private void textFieldCelularRelease() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("(##)#####-####");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(txtTelefone);
		tff.formatter();
	}

	@FXML
	void handleExcluir(ActionEvent event) {
		if (tblClientes.getSelectionModel().getSelectedItem() != null) {
			if (Util.alertaExclusao() == ButtonType.OK) {
				Clientes cliente = tblClientes.getSelectionModel().getSelectedItem();
				new FrmCadastroClienteDAO().removerCliente(cliente.getCodigo());
			}
			alimentarTable();
		}
	}

	@FXML
	void abrirClienteSelecionado(MouseEvent event) {
		if (event.getClickCount() == 2) {
			Clientes cliente = tblClientes.getSelectionModel().getSelectedItem();

			cliente.setCodigo(cliente.getCodigo());
			cliente.setNome(cliente.getNome());
			cliente.setSobrenome(cliente.getSobrenome());
			cliente.setTelefone(cliente.getTelefone());
			cliente.setCnpj(cliente.getCnpj());
			cliente.setPessoa(cliente.getPessoa());
			cliente.setDataCadastro(cliente.getDataCadastro());

			alimentarTelaCadastro(cliente);

			desabilitarCamposCadastro();

			SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
			selectionModel.select(hBoxCadastro);
		}
	}

	public void desabilitarCamposCadastro() {
		lblCodigo.setDisable(true);
		txtNome.setDisable(true);
		txtSobrenome.setDisable(true);
		txtTelefone.setDisable(true);
		txtCnpjCpf.setDisable(true);
		tipoPessoa.setDisable(true);
		txtEmail.setDisable(true);
		txtDate.setDisable(true);
		btnGravar.setDisable(true);
	}

	public void habilitarCamposCadastro() {
		lblCodigo.setDisable(false);
		txtNome.setDisable(false);
		txtSobrenome.setDisable(false);
		txtTelefone.setDisable(false);
		txtCnpjCpf.setDisable(false);
		tipoPessoa.setDisable(false);
		txtEmail.setDisable(false);
		txtDate.setDisable(false);
		btnGravar.setDisable(false);
	}

	@FXML
	void handleEditar(ActionEvent event) {
		if (tblClientes.getSelectionModel().getSelectedItem() != null) {

			Clientes cliente = tblClientes.getSelectionModel().getSelectedItem();

			cliente.setCodigo(cliente.getCodigo());
			cliente.setNome(cliente.getNome());
			cliente.setSobrenome(cliente.getSobrenome());
			cliente.setTelefone(cliente.getTelefone());
			cliente.setCnpj(cliente.getCnpj());
			cliente.setPessoa(cliente.getPessoa());
			cliente.setDataCadastro(cliente.getDataCadastro());

			alimentarTelaCadastro(cliente);

			SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
			selectionModel.select(hBoxCadastro);
		}
		habilitarCamposCadastro();
		botaoEditarClicado = true;
	}

	public void alimentarTelaCadastro(Clientes cliente) {
		lblCodigo.setText(cliente.getCodigo().toString());
		txtNome.setText(cliente.getNome());
		txtSobrenome.setText(cliente.getSobrenome());
		txtTelefone.setText(cliente.getTelefone());
		txtCnpjCpf.setText(cliente.getCnpj());
		tipoPessoa.setValue(cliente.getPessoa());
		txtEmail.setText(cliente.getEmail());
		txtDate.setValue(Util.asLocalDate(cliente.getDataCadastro()));
	}

	public void limparTelaCadastro() {
		txtNome.setText("");
		txtSobrenome.setText("");
		txtTelefone.setText("");
		txtCnpjCpf.setText("");
		txtEmail.setText("");
		tipoPessoa.setValue("Física");
		lblCodigo.setText(new FrmCadastroClienteDAO().proximoCodigoCliente().toString());
		txtDate.setValue(LocalDate.now());
	}

	@FXML
	void handleCancelar(ActionEvent event) {
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(hBoxConsulta);
	}

	@FXML
	void handleFechar(ActionEvent event) {
		FrmContainerController.parentFrmCadastroClientes.setVisible(false);
	}

	@FXML
	void onKeySearchPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			if (choiceFilter.getValue().equals("ID")) {
				if (txtFilter.getText().equals("")) {
					alimentarTable();
				} else {
					try {
						tblClientes.setItems(cadastroCliente.capturarClientesId(Integer.parseInt(txtFilter.getText())));
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
					tblClientes.setItems(cadastroCliente.capturarClienteNome(txtFilter.getText()));
				}
			} else if (choiceFilter.getValue().equals("SOBRENOME")) {
				if (txtFilter.getText().equals("")) {
					alimentarTable();
				} else {
					tblClientes.setItems(cadastroCliente.capturarClienteSobrenome(txtFilter.getText()));
				}
			} else if (choiceFilter.getValue().equals("CNPJ/CPF")) {
				if (txtFilter.getText().equals("")) {
					alimentarTable();
				} else {
					tblClientes.setItems(cadastroCliente.capturarClienteCnpjCpf(txtFilter.getText()));
				}
			} else if (choiceFilter.getValue().equals("EMAIL")) {
				if (txtFilter.getText().equals("")) {
					alimentarTable();
				} else {
					tblClientes.setItems(cadastroCliente.capturarClienteEmail(txtFilter.getText()));
				}
			} else if (choiceFilter.getValue().equals("TELEFONE")) {
				if (txtFilter.getText().equals("")) {
					alimentarTable();
				} else {
					tblClientes.setItems(cadastroCliente.capturarClienteTelefone(txtFilter.getText()));
				}
			} else if (choiceFilter.getValue().equals("DATA")) {
				if (txtFilter.getText().equals("")) {
					alimentarTable();
				} else {
					tblClientes.setItems(cadastroCliente.capturarClienteData(txtFilter.getText()));
				}

			}
		}
	}

	@FXML
	void textFieldCnpjCpfRelease() {
		TextFieldFormatter tff = new TextFieldFormatter();
		if (tipoPessoa.getValue().equals("Física")) {
			tff.setMask("###.###.###-##");
			tff.setCaracteresValidos("0123456789");
			tff.setTf(txtCnpjCpf);
			tff.formatter();
		} else {
			tff.setMask("##.###.###/####-##");
			tff.setCaracteresValidos("0123456789");
			tff.setTf(txtCnpjCpf);
			tff.formatter();
		}
	}

	@FXML
	void verificarBotoes(ActionEvent event) {
		BooleanBinding algoSelecionado = tblClientes.getSelectionModel().selectedItemProperty().isNull();

		btnEditar.disableProperty().bind(algoSelecionado);
		btnExcluir.disableProperty().bind(algoSelecionado);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		alimentarTable();

		cadastroCliente = new FrmCadastroClienteDAO();

		choiceFilter.setItems(
				FXCollections.observableArrayList("ID", "NOME", "SOBRENOME", "CNPJ/CPF", "EMAIL", "TELEFONE", "DATA"));

		tipoPessoa.setItems(FXCollections.observableArrayList("Física", "Jurídica"));

		tblColumnId.setCellValueFactory(new PropertyValueFactory<Integer, Clientes>("codigo"));
		tblColumnNome.setCellValueFactory(new PropertyValueFactory<String, Clientes>("nome"));
		tblColumnSobrenome.setCellValueFactory(new PropertyValueFactory<String, Clientes>("sobrenome"));
		tblColumnCnpjCpf.setCellValueFactory(new PropertyValueFactory<String, Clientes>("cnpj"));
		tblColumnEmail.setCellValueFactory(new PropertyValueFactory<String, Clientes>("email"));
		tblColumnTelefone.setCellValueFactory(new PropertyValueFactory<Integer, Clientes>("telefone"));
		tblColumnCadastrado.setCellValueFactory(new PropertyValueFactory<Date, Clientes>("dataCadastro"));
		tblColumnPessoa.setCellValueFactory(new PropertyValueFactory<String, Clientes>("pessoa"));

		choiceFilter.setValue("ID");
	}

	private void alimentarTable() {
		ObservableList<Clientes> list = new FrmCadastroClienteDAO().capturarTodosClientes();
		tblClientes.setItems(list);
	}
}