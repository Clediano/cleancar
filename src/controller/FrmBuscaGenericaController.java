package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class FrmBuscaGenericaController {

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
    void pesquisarBusca(ActionEvent event) {

    }

    @FXML
    void cancelarBusca(ActionEvent event) {
    	
    }

    @FXML
    void pesquisarDadosEnter(KeyEvent event) {
    	if(event.equals(KeyCode.ENTER)) {
    		
    	}
    }
}
