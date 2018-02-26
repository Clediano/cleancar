package controller;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dao.FrmCadastroClienteDAO;
import dao.FrmCadastroPlacaDAO;
import dao.FrmCadastroProdutosDAO;
import dao.FrmLancamentosLavagensDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Cliente;
import model.Lavagem;
import model.Produto;
import util.Util;

public class FrmLancamentosLavagensController implements Initializable{
	
	private FrmLancamentosLavagensDAO lavagens = new FrmLancamentosLavagensDAO();
	private FrmCadastroClienteDAO clientes = new FrmCadastroClienteDAO();
	private FrmCadastroProdutosDAO produtos = new FrmCadastroProdutosDAO();
	private FrmCadastroPlacaDAO placas = new FrmCadastroPlacaDAO();

    @FXML
    private TableColumn<Lavagem, String> tblColumnNomeProduto;

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
    private Label lblId;
    
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
    void procurarClienteAtalho(KeyEvent event) {
    	if(event.getCode() == KeyCode.F12) {
    		txtCliente.setText(abrirTelaGenericaClientes().toString());
    		lblNomeCliente.setText(clientes.capturarClientesId(Integer.parseInt(txtCliente.getText())).getNome());
    	}
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
    	adicionarLavagem();
    }

    @FXML
    void onKeyPressedAdicionar(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER) {
    		adicionarLavagem();
    	}
    }

    @FXML
    void procurarPlacaAtalho(KeyEvent event) {
    	if(event.getCode() == KeyCode.F12) {
    		abrirTelaGenericaPlacas();
    		
    		txtPlaca.setText(abrirTelaGenericaPlacas().toString());
        	lblPlaca.setText(placas.pegarPlaca(Integer.parseInt(txtPlaca.getText())).getNumero());
    	}
    }

    @FXML
    void procurarPlacaClick(MouseEvent event) {
    	if(event.getClickCount() == 1) {
    		txtPlaca.setText(abrirTelaGenericaPlacas().toString());
        	lblPlaca.setText(placas.pegarPlaca(Integer.parseInt(txtPlaca.getText())).getNumero());	
    	}
    }

    @FXML
    void handleIncluir(ActionEvent event) {
    	selecioanrTelaCadastro();
    	
    	txtDataInclusao.setValue(LocalDate.now());
    	lblId.setText(lavagens.capturarIdProximaLavagem().toString());
    	txtCliente.requestFocus();
    }

    @FXML
    void handleExcluir(ActionEvent event) {
    	Lavagem lavagem = tblLancamentos.getSelectionModel().getSelectedItem();
    	
    	if(lavagem != null) {
    		lavagens.deletarLavagem(lavagem.getId());
    	}
    }
    
    private Integer abrirTelaGenericaClientes() {
		return null;
		//MODIFICAR TELA DE ACORDO COM A NECESSIDADE DE PESQUISA DE UM CLIENTE
    }
    
    private Integer abrirTelaGenericaPlacas() {
		return null;
		//MODIFICAR TELA DE ACORDO COM A NECESSIDADE DE PESQUISA DE UM CLIENTE
    }
    
    private void adicionarLavagem() {
    	
    	Lavagem lavagem = new Lavagem();
    	ObservableList<Lavagem> lavagens = FXCollections.observableArrayList();
    	Produto produto = produtos.capturarProdutosId(Integer.parseInt(txtProduto.getText()));
    	
    	lavagem.setProduto(produto);
    	
    	lavagens.add(lavagem);
    	
    	tblItensLancamentos.setItems(lavagens);
    }

    @FXML
    void handleEditar(ActionEvent event) {
    	Lavagem lavagem = tblLancamentos.getSelectionModel().getSelectedItem();
    	
    	if(lavagem != null) {
    		alimentaCabecalhoLavagem(lavagem);
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
    
    @FXML
    void aliementarLabelProdutoNome(MouseEvent event) {
    	if(event.getClickCount() == 1){
    		Lavagem linhaSelecionada = tblItensLancamentos.getSelectionModel().getSelectedItem();
    		Produto produtoSelecionado = produtos.capturarProdutosId(linhaSelecionada.getId());
    		
    		txtProduto.setText(produtoSelecionado.getCodigo().toString());
    		lblNomeProduto.setText(produtoSelecionado.getNome());
    	}
    }
    
    public void alimentaCabecalhoLavagem(Lavagem lavagem){
		Lavagem lavagemCabecalho = lavagens.capturarDadosLavagemCabecalho(lavagem.getId());
		ObservableList<Lavagem> lavagemItens = lavagens.capturarLavagensItens(lavagem.getId());
		Cliente cliente = clientes.capturarClientesId(lavagem.getCliente().getCodigo());
    	
    	txtCliente.setText(cliente.getCodigo().toString());
    	lblNomeCliente.setText(cliente.getNome());
    	
    	txtPlaca.setText(lavagemCabecalho.getPlaca().toString());
    	lblPlaca.setText(placas.pegarPlaca(lavagemCabecalho.getPlaca().getId()).getNumero());
    	
    	txtDataInclusao.setValue(lavagemCabecalho.getDataCadastro().toLocalDate());
    	
    	tblItensLancamentos.setItems(lavagemItens);
    	
    	txtValor.setText(lavagens.somarValorItens(lavagem.getId()).toString());
    	lblValorTotal.setText(lavagens.somarValorItens(lavagem.getId()).toString());
    	txtObservacao.setText(lavagemCabecalho.getObservacao());
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		tblColumnProdutoItem.setCellValueFactory(new PropertyValueFactory<Lavagem, Integer>("produto"));
		tblColumnNomeProduto.setCellValueFactory(new PropertyValueFactory<Lavagem, String>("nome"));
		tblColumnValorItem.setCellValueFactory(new PropertyValueFactory<Lavagem, Float>("valor"));
	}
}