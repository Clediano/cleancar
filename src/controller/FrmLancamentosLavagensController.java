package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Stack;

import dao.FrmCadastroClienteDAO;
import dao.FrmCadastroPlacaDAO;
import dao.FrmCadastroProdutosDAO;
import dao.FrmLancamentosLavagensDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Cliente;
import model.Lavagem;
import model.Placa;
import model.Produto;

public class FrmLancamentosLavagensController implements Initializable{
	
	private FrmLancamentosLavagensDAO lavagens = new FrmLancamentosLavagensDAO();
	private FrmCadastroClienteDAO clientes = new FrmCadastroClienteDAO();
	private FrmCadastroProdutosDAO produtos = new FrmCadastroProdutosDAO();
	private FrmCadastroPlacaDAO placas = new FrmCadastroPlacaDAO();
	
	public static Stage stageTelaPesquisa;

    @FXML
    private TableColumn<Produto, String> tblColumnNomeProduto;

    @FXML
    private Button btnAdicionar;

    @FXML
    public static TextField txtProduto;

    @FXML
    private TableColumn<Lavagem, String> tblColumnPlaca;

    @FXML
    private Tab hBoxConsulta;

    @FXML
    private TableColumn<Lavagem, Integer> tblColumnClienteCodigo;

    @FXML
    private TableColumn<Produto, Float> tblColumnValorItem;

    @FXML
    private Button btnFechar;

    @FXML
    private TableColumn<Lavagem, Float> tblColumnValor;

    @FXML
    private TableColumn<Produto, Integer> tblColumnProdutoItem;
    
    @FXML
    private TableColumn<Produto, Integer> tblColumnProdutoItemId;

    @FXML
    public static TextField txtCliente;

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
    private TableView<Produto> tblItensLancamentos;

    @FXML
    private HBox hBox;

    @FXML
    public static TextField txtPlaca;

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
    	Integer situacao = Integer.parseInt(lblSituacao.getText());
    	Integer idLavagem = Integer.parseInt(lblId.getText());
    	//0 = ABERTO
    	//1 = FECHADO
    	//2 = CANCELADO
    	if(situacao == 1) {
    		lavagens.alterarStatusLavagem(0, idLavagem);
    		atualizarLabelStatusPedidos(lavagens.consultarSituacaoLavagem(idLavagem));
    	}else if(situacao == 2) {
    		Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("Impossível abrir lavagem cancelada!");
			alert.setContentText("Ação não permitida!");
			alert.setTitle("Lavagem cancelada!");
			alert.show();
    	}else {
    		Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("Lavagem já está aberta!");
			alert.setContentText("Ação não permitida!");
			alert.setTitle("Confira!");
			alert.show();
    	}
    }
    
    private void atualizarLabelStatusPedidos(Integer status) {
    	if(status == 0) {
    		lblSituacao.setText("ABERTA");
    		lblCorSituacao.setStyle("-fx-background-color : #ffffff;");
    	}else if(status == 1) {
    		lblSituacao.setText("FECHADA");
    		lblCorSituacao.setStyle("-fx-background-color : #ffff00;");
    	}else if(status == 2){
    		lblSituacao.setText("CANCELADO");
    		lblCorSituacao.setStyle("-fx-background-color : #993333;");
    	}
    }

    @FXML
    void handleFecharLavagem(ActionEvent event) {
    	selecionarTelaConsulta();
    	limparTelaCadastro();
    }

    @FXML
    void handleCancelarLavagem(ActionEvent event) {
    	limparTelaCadastro();
    	selecionarTelaConsulta();
    }

    @FXML
    void handleDuplicar(ActionEvent event) {
    	Lavagem lavagem = lavagens.capturarDadosLavagemCabecalho(Integer.parseInt(lblId.getText()));
    	ObservableList<Produto> produtos = lavagens.capturarLavagensItens(Integer.parseInt(lblId.getText()));
    	
    	lblId.setText(lavagens.capturarIdProximaLavagem().toString());
    	txtCliente.setText(lavagem.getCliente().toString());
    	alimentaLabelCliente(clientes.capturarClientesId(Integer.parseInt(txtCliente.getText())));
    	txtProduto.setText(lavagem.getProduto().toString());
    	alimentaLabelProduto(this.produtos.capturarProdutosId(Integer.parseInt(txtProduto.getText())));
    	txtPlaca.setText(lavagem.getPlaca().toString());
    	alimentaLabelPlaca(placas.pegarPlaca(Integer.parseInt(txtPlaca.getText())));
    	
    	txtObservacao.setText(lavagem.getObservacao());
    	
    	lblValorTotal.setText(lavagens.somarValorItens(lavagem.getId()).toString());
    	
    	txtDataInclusao.setEditable(false);
    	txtDataInclusao.setValue(LocalDate.now());
    	
    	tblItensLancamentos.setItems(produtos);
    }
    
    private void alimentaLabelProduto(Produto produto) {
    	lblNomeProduto.setText(produto.getNome());
    }
    
    private void alimentaLabelCliente(Cliente cliente) {
    	lblNomeCliente.setText(cliente.getNome());
    }
    
    private void alimentaLabelPlaca(Placa placa) {
    	lblPlaca.setText(placa.getNumero());
    }

    @FXML
    void onKeyDelPressed(ActionEvent event) {
    	Produto produto = tblItensLancamentos.getSelectionModel().getSelectedItem();
    	if(produto != null) {
    		lavagens.deletarLavagemItem(produto.getId());
    	}
    }

    @FXML
    void procurarClienteAtalho(KeyEvent event) {
    	if(event.getCode() == KeyCode.F12) {
    		abrirTelaBuscaCliente();
    	}
    }

    @FXML
    void procurarProdutoAtalho(KeyEvent event) {
    	if(event.getCode() == KeyCode.F12) {
    		abrirTelaBuscaProduto();
    	}
    }

    @FXML
    void procurarProdutoClick(MouseEvent event) {	
    	if(event.getClickCount() == 1) {
    		abrirTelaBuscaProduto();
    	}
    }

    @FXML
    void procurarClienteClick(MouseEvent event) {
    	if(event.getClickCount() == 1) {
    		abrirTelaBuscaCliente();
    	}
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
    		abrirTelaBuscaPlaca();
    	}
    }

    @FXML
    void procurarPlacaClick(MouseEvent event) {
    	if(event.getClickCount() == 1) {
    		abrirTelaBuscaPlaca();
    	}
    }

    @FXML
    void handleIncluir(ActionEvent event) {
    	selecioanrTelaCadastro();
    	
    	txtDataInclusao.setValue(LocalDate.now());
    	lblId.setText(lavagens.capturarIdProximaLavagem().toString());
    }
    
    @FXML
    void handleExcluir(ActionEvent event) {
    	Lavagem lavagem = tblLancamentos.getSelectionModel().getSelectedItem();
    	
    	if(lavagem != null) {
    		lavagens.deletarLavagem(lavagem.getId());
    	}
    }
    
    public void abrirTelaBuscaProduto() {
    	Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("view/FrmBuscaGenericaProduto.fxml"));
			
			Scene scene = new Scene(parent);
			stageTelaPesquisa = new Stage();
			
			stageTelaPesquisa.setScene(scene);
			stageTelaPesquisa.setTitle("Tela de pesquisa.");
			stageTelaPesquisa.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void abrirTelaBuscaPlaca() {
    	Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("view/FrmBuscaGenericaPlaca.fxml"));
			
			Scene scene = new Scene(parent);
			stageTelaPesquisa = new Stage();
			
			stageTelaPesquisa.setScene(scene);
			stageTelaPesquisa.setTitle("Tela de pesquisa.");
			stageTelaPesquisa.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void abrirTelaBuscaCliente() {
    	Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("view/FrmBuscaGenericaCliente.fxml"));
			
			Scene scene = new Scene(parent);
			stageTelaPesquisa = new Stage();
			
			stageTelaPesquisa.setScene(scene);
			stageTelaPesquisa.setTitle("Tela de pesquisa.");
			stageTelaPesquisa.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void adicionarLavagem() {
    	
    	Produto produtoModelo = new Produto();
    	ObservableList<Produto> produtosLista = FXCollections.observableArrayList();
    	Produto produto = produtos.capturarProdutosId(Integer.parseInt(txtProduto.getText()));
    	
    	produtoModelo.setCodigo(produto.getCodigo());
    	produtoModelo.setNome(produto.getNome());
    	produtoModelo.setPrecoVenda(produto.getPrecoVenda());
    	
    	produtosLista.add(produtoModelo);
    	
    	tblItensLancamentos.setItems(produtosLista);
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
    
    public void alimenarCamposProduto(Produto produto) {
    	txtProduto.setText(produto.getCodigo().toString());
    	lblNomeProduto.setText(produto.getNome().toUpperCase());
    }
    
    public void alimenarCamposPlaca(Placa placa) {
    	txtPlaca.setText(placa.getId().toString());
    	lblNomeProduto.setText(placa.getNumero().toUpperCase());
    }
    
    public void alimenarCamposCliente(Cliente cliente) {
    	txtCliente.setText(cliente.getCodigo().toString());
    	lblNomeCliente.setText(cliente.getNome().toUpperCase());
    }
    
    public void alimentaCabecalhoLavagem(Lavagem lavagem){
		Lavagem lavagemCabecalho = lavagens.capturarDadosLavagemCabecalho(lavagem.getId());
		ObservableList<Produto> lavagemItens = lavagens.capturarLavagensItens(lavagem.getId());
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
		
		tblColumnProdutoItem.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("produto"));
		tblColumnNomeProduto.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
		tblColumnValorItem.setCellValueFactory(new PropertyValueFactory<Produto, Float>("valor"));
	}
}