package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import model.Clientes;

public class FrmCadastroClienteController {

    @FXML
    private DatePicker txtDate;

    @FXML
    private ChoiceBox<?> choiceFilter;

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
    private HBox hBox;

    @FXML
    private Button btnCancelarCadastro;

    @FXML
    private Tab hBoxCadastro;

    @FXML
    private Button btnIncluir;

    @FXML
    private TextField txtSobrenome;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnExcluir;

    @FXML
    private TabPane tabPane;

    @FXML
    void onKeyDelPressed(ActionEvent event) {
    	
    }

    @FXML
    void handleGravar(ActionEvent event) {
    	
    }

    @FXML
    void handleCancelarCadastro(ActionEvent event) {
    	
    }

    @FXML
    void handleIncluir(ActionEvent event) {
    	
    }

    @FXML
    void handleExcluir(ActionEvent event) {

    }

    @FXML
    void handleEditar(ActionEvent event) {

    }

    @FXML
    void handleCancelar(ActionEvent event) {

    }

    @FXML
    void handleFechar(ActionEvent event) {
    	FrmContainerController.parentFrmCadastro.setVisible(false);
    }

    @FXML
    void onKeySearchPressed(ActionEvent event) {
    	
    }
}