package controller;

import java.sql.Date;
import java.time.LocalDate;

import dao.FrmLancamentosLavagensDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Lavagem;

public class FrmLancamentosLavagensController {
	
	private FrmLancamentosLavagensDAO lavagens = new FrmLancamentosLavagensDAO();

    @FXML
    private TableColumn<Lavagem, String> tblColumnObservacaoItem;

    @FXML
    private Button btnAdicionar;

    @FXML
    private TextField txtProduto;

    @FXML
    private TableColumn<Lavagem, String> tblColumnPlaca;

    @FXML
    private Tab hBoxConsulta;

    @FXML
    private TableColumn<Lavagem, Integer> tblColumnClienteCodigo;

    @FXML
    private TableColumn<Lavagem, Float> tblColumnValorItem;

    @FXML
    private Button btnFechar;

    @FXML
    private TableColumn<Lavagem, Float> tblColumnValor;

    @FXML
    private TableColumn<Lavagem, Integer> tblColumnProdutoItem;

    @FXML
    private TextField txtCliente;

    @FXML
    private TableView<Lavagem> tblLancamentos;

    @FXML
    private TextField txtFiltro;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextArea txtObservacao;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnFecharLavagem;

    @FXML
    private TableColumn<Lavagem, String> tblColumnObservacao;

    @FXML
    private Button btnCancelarLavagem;

    @FXML
    private ComboBox<String> cbFiltro;

    @FXML
    private Label lblNomeCliente;

    @FXML
    private TableColumn<Lavagem, Date> tblColumnDataItem;

    @FXML
    private Button btnEditar;

    @FXML
    private DatePicker txtDataFechamento;

    @FXML
    private TableView<Lavagem> tblItensLancamentos;

    @FXML
    private HBox hBox;

    @FXML
    private TextField txtPlaca;

    @FXML
    private Tab hBoxCadastro;

    @FXML
    private Button btnIncluir;

    @FXML
    private TableColumn<Lavagem, Date> tblColumnDataInclusao;
    
    @FXML
    private TableColumn<Lavagem, String> tblColumnClienteNome;
    
    @FXML
    private TableColumn<Lavagem, Integer> tblColumnClienteId;

    @FXML
    private Label lblNomeProduto;

    @FXML
    private Label lblValorTotal;
    
    @FXML
    private Label lblPlaca;
    
    @FXML
    private Label lblSituacao;
    
    @FXML
    private Label lblCorSituacao;
    
    @FXML
    private Button btnAbrirLavagem;

    @FXML
    private TextField txtValor;

    @FXML
    private GridPane gridPane;

    @FXML
    private TabPane tabPane;

    @FXML
    private DatePicker txtDataInclusao;


    @FXML
    void handleAbrirLavagem(ActionEvent event) {
    	
    }

    @FXML
    void handleFecharLavagem(ActionEvent event) {
    	
    }

    @FXML
    void handleCancelarLavagem(ActionEvent event) {
    	
    }

    @FXML
    void handleDuplicar(ActionEvent event) {

    }

    @FXML
    void onKeyDelPressed(ActionEvent event) {

    }

    @FXML
    void procurarClienteAtalho(ActionEvent event) {

    }

    @FXML
    void procurarProdutoAtalho(ActionEvent event) {

    }

    @FXML
    void procurarProdutoClick(ActionEvent event) {
    	
    }

    @FXML
    void procurarClienteClick(ActionEvent event) {
    	
    }

    @FXML
    void handleAdicionar(ActionEvent event) {
    	
    }

    @FXML
    void onKeyPressedAdicionar(ActionEvent event) {
    	
    }

    @FXML
    void procurarPlacaAtalho(ActionEvent event) {

    }

    @FXML
    void procurarPlacaClick(ActionEvent event) {

    }

    @FXML
    void handleIncluir(ActionEvent event) {

    }

    @FXML
    void handleExcluir(ActionEvent event) {

    }

    @FXML
    void handleEditar(ActionEvent event) {
    	Lavagem lavagem = tblLancamentos.getSelectionModel().getSelectedItem();
    	
    	if(lavagem != null) {
    		Lavagem lavagemCabecalho = lavagens.capturarDadosLavagemCabecalho(lavagem.getId());
    		Lavagem lavagemItens = lavagens.capturarLavagensItens(lavagem.getId());
    	}
    }

    @FXML
    void handleCancelar(ActionEvent event) {
    	limparTelaCadastro();
    }

    @FXML
    void handleFechar(ActionEvent event) {
    	FrmContainerController.parentFrmLancamentosLavagens.setVisible(false);
    }
    
    private void selecioanrTelaCadastro() {
    	SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(hBoxCadastro);
    }
    private void selecionarTelaConsulta() {
    	SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(hBoxConsulta);
    }
    
    private void limparTelaCadastro(){
    	txtCliente.setText("");
    	txtDataFechamento.setValue(null);
    	txtDataInclusao.setValue(null);
    	txtFiltro.setText("");
    	txtObservacao.setText("");
    	txtPlaca.setText("");
    	txtProduto.setText("");
    	txtValor.setText("");
    	tblItensLancamentos.setItems(null);
    }
}