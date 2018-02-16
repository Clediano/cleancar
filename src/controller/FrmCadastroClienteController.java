package controller;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.mysql.fabric.xmlrpc.base.Data;

import dao.FrmCadastroClienteDAO;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import javafx.scene.layout.HBox;
import model.Clientes;
import util.TextFieldFormatter;
import util.Util;

public class FrmCadastroClienteController implements Initializable {

	private FrmCadastroClienteDAO cadastroCliente;

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
		
		System.out.println(Integer.parseInt(txtTelefone.getText()));
		
		
		Clientes cliente = new Clientes();

		cliente.setCodigo(Integer.parseInt(lblCodigo.getText()));
		cliente.setNome(txtNome.getText());
		cliente.setSobrenome(txtSobrenome.getText());
		cliente.setCnpj(Util.formatCpfCnpj(txtCnpjCpf.getText()));
		cliente.setEmail(txtEmail.getText());
		cliente.setTelefone(Util.formatTelefone(txtTelefone.getText()));
		cliente.setDataCadastro(Util.asDate(txtDate.getValue()));

		cadastroCliente.cadastrarCliente(cliente);
	}

	@FXML
	void handleCancelarCadastro(ActionEvent event) {
		FrmContainerController.parentFrmCadastro.setVisible(false);
	}

	@FXML
	void handleIncluir(ActionEvent event) {
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(hBoxCadastro);

		tipoPessoa.setItems(FXCollections.observableArrayList("Física", "Jurídica"));
		tipoPessoa.setValue("Física");

		txtNome.setFocusTraversable(true);
		txtNome.requestFocus();
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
		if(Util.alertaExclusao() == ButtonType.OK) {
			Clientes cliente = tblClientes.getSelectionModel().getSelectedItem();
			new FrmCadastroClienteDAO().removerCliente(cliente.getCodigo());	
		}
		alimentarTable();
	}

	@FXML
	void handleEditar(ActionEvent event) {

	}

	@FXML
	void handleCancelar(ActionEvent event) {
		FrmContainerController.parentFrmCadastro.setVisible(false);
	}

	@FXML
	void handleFechar(ActionEvent event) {
		FrmContainerController.parentFrmCadastro.setVisible(false);
	}

	@FXML
	void onKeySearchPressed(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			
		}
	}
	
    @FXML
    void textFieldCnpjCpfRelease() {
    	TextFieldFormatter tff = new TextFieldFormatter();
    	if(tipoPessoa.getValue().equals("Física")) {
        	tff.setMask("###.###.###-##");
        	tff.setCaracteresValidos("0123456789");
        	tff.setTf(txtCnpjCpf);
        	tff.formatter();
    	}else {
        	tff.setMask("##.###.###/####-##");
        	tff.setCaracteresValidos("0123456789");
        	tff.setTf(txtCnpjCpf);
        	tff.formatter();
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		alimentarTable();

		cadastroCliente = new FrmCadastroClienteDAO();

		BooleanBinding algoSelecionado = tblClientes.getSelectionModel().selectedItemProperty().isNull();

		btnEditar.disableProperty().bind(algoSelecionado);
		btnExcluir.disableProperty().bind(algoSelecionado);

		choiceFilter.setItems(FXCollections.observableArrayList("ID", "NOME", "SOBRENOME", "CNPJ", "EMAIL"));

		tblColumnId.setCellValueFactory(new PropertyValueFactory<Integer, Clientes>("id"));
		tblColumnNome.setCellValueFactory(new PropertyValueFactory<String, Clientes>("nome"));
		tblColumnSobrenome.setCellValueFactory(new PropertyValueFactory<String, Clientes>("sobrenome"));
		tblColumnCnpjCpf.setCellValueFactory(new PropertyValueFactory<String, Clientes>("cnpj"));
		tblColumnEmail.setCellValueFactory(new PropertyValueFactory<String, Clientes>("email"));
		tblColumnTelefone.setCellValueFactory(new PropertyValueFactory<Integer, Clientes>("telefone"));
		tblColumnCadastrado.setCellValueFactory(new PropertyValueFactory<Date, Clientes>("cadastrado"));

		choiceFilter.setValue("ID");
		
		txtDate.setValue(LocalDate.now());
		lblCodigo.setText(new FrmCadastroClienteDAO().proximoCodigoCliente().toString());
	}

	private void alimentarTable() {
		ObservableList<Clientes> list = new FrmCadastroClienteDAO().capturarTodosClientes();
		tblClientes.setItems(list);
	}
}