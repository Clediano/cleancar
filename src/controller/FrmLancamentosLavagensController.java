package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class FrmLancamentosLavagensController {

    @FXML
    private Button btnAdicionar;

    @FXML
    private TextField txtProduto;

    @FXML
    private Tab hBoxConsulta;

    @FXML
    private TableColumn<?, ?> tblColumnCliente;

    @FXML
    private Button btnFechar;

    @FXML
    private TableColumn<?, ?> tblColumnValor;

    @FXML
    private TextField txtCliente;

    @FXML
    private TableView<?> tblLancamentos;

    @FXML
    private TableColumn<?, ?> tblColumnProduto;

    @FXML
    private TextField txtFiltro;

    @FXML
    private TableColumn<?, ?> tblColumnData;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextArea txtObservacao;

    @FXML
    private TableColumn<?, ?> tblColumnSituacao;

    @FXML
    private Button btnExcluir;

    @FXML
    private TableColumn<?, ?> tblColumnObservacao;

    @FXML
    private Button btnAbrir;

    @FXML
    private ComboBox<?> cbFiltro;

    @FXML
    private Label lblNomeCliente;

    @FXML
    private Button btnEditar;

    @FXML
    private DatePicker txtDataFechamento;

    @FXML
    private TableView<?> tblItensLancamentos;

    @FXML
    private HBox hBox;

    @FXML
    private Tab hBoxCadastro;

    @FXML
    private Button btnIncluir;

    @FXML
    private Label lblNomeProduto;

    @FXML
    private Label lblValorTotal;

    @FXML
    private TextField txtValor;

    @FXML
    private GridPane gridPane;

    @FXML
    private TabPane tabPane;

    @FXML
    private DatePicker txtDataInclusao;

    @FXML
    void handleAbrir(ActionEvent event) {

    }

    @FXML
    void handleFechar(ActionEvent event) {

    }

    @FXML
    void handleFechar(ActionEvent event) {

    }

    @FXML
    void handleDuplicar(ActionEvent event) {

    }

    @FXML
    void onKeyDelPressed(ActionEvent event) {

    }

    @FXML
    void onKeyClientePressed(ActionEvent event) {

    }

    @FXML
    void onKeyProdutoPressed(ActionEvent event) {

    }

    @FXML
    void onKeyProdutoPressed(ActionEvent event) {

    }

    @FXML
    void handleSearchProduto(ActionEvent event) {

    }

    @FXML
    void onKeyClientePressed(ActionEvent event) {

    }

    @FXML
    void handleSearchCliente(ActionEvent event) {

    }

    @FXML
    void handleAdicionar(ActionEvent event) {

    }

    @FXML
    void onKeyPressedAdicionar(ActionEvent event) {

    }

    @FXML
    void onKeyPlacaPressed(ActionEvent event) {

    }

    @FXML
    void handleSearchPlaca(ActionEvent event) {

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
    	FrmContainerController.parentFrmLancamentosLavagens.setVisible(false);
    }

}
