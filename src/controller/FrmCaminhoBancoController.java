package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import jdbc.ConnectionFactory;
import util.PropertiesLoaderImpl;

public class FrmCaminhoBancoController implements Initializable{
	
	boolean selected = false;
	
    @FXML
    private Button btnConfirmar;

    @FXML
    private AnchorPane acnhorCaminhoBanco;

    @FXML
    private Button btnCaminho;

    @FXML
    private Button btnTestar;

    @FXML
    private TextField txtURL;

    @FXML
    void handleTestar(ActionEvent event) {
    	String drive = "com.mysql.jdbc.Driver";
    	String url = "jdbc:mysql://" + txtURL.getText();
    	String usu = "root";
    	String pass = "root";
    	
    	if(ConnectionFactory.testConnection(drive, url, usu, pass)) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Caminho do banco válido!");
			alert.setContentText("Conexão efetuada!");
			alert.setTitle("Configuração do banco de dados.");
			alert.show();
			
			btnConfirmar.setDisable(false);
			PropertiesLoaderImpl.setValor("caminho", txtURL.getText());
			
    	}else {
    		Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("Caminho do banco não informado ou inválido!");
			alert.setContentText("Conexão não efetuada!");
			alert.setTitle("Verifique os dados informados.");
			alert.show();
    	}
    }

    @FXML
    void handleConfirmar(ActionEvent event) {
    	FrmContainerController.stageCaminhoBanco.close();
    }

    @FXML
    void handleCaminho(ActionEvent event) {

    	DirectoryChooser directoryChooser = new DirectoryChooser(); 

        directoryChooser.setTitle("Selecione a pasta do banco");
          
        File file = directoryChooser.showDialog(null);
        
        
       if(file != null){

            txtURL.setText(file.getPath());

       } 	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(!selected) {
			btnConfirmar.setDisable(true);
		}
		
	}

}
