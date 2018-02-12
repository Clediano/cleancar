package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FrmContainerController {
	
	public static Stage stageCaminhoBanco;

	public static Parent parentFrmLancamentosLavagens;

	public static Parent parentFrmCadastro;

    @FXML
    private MenuItem mnClientes;
    
	@FXML
	private AnchorPane anchoPane;

	@FXML
	private MenuItem btnLavagens;

	@FXML
	private MenuItem btnUsuarios;
	
    @FXML
    private MenuItem btnBanco;

	@SuppressWarnings("static-access")
	@FXML
	void handleLavagens(ActionEvent event) {

		desativarPainel();

		try {

			parentFrmLancamentosLavagens = FXMLLoader
					.load(getClass().getClassLoader().getResource("view/FrmLancamentosLavagens.fxml"));

			anchoPane.setTopAnchor(parentFrmLancamentosLavagens, 0.0);
			anchoPane.setBottomAnchor(parentFrmLancamentosLavagens, 0.0);
			anchoPane.setLeftAnchor(parentFrmLancamentosLavagens, 0.0);
			anchoPane.setRightAnchor(parentFrmLancamentosLavagens, 0.0);

			anchoPane.getChildren().add(parentFrmLancamentosLavagens);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
    
	@SuppressWarnings("static-access")
	@FXML
	void handleUsuarios(ActionEvent event) {

		desativarPainel();

		try {
			parentFrmCadastro = FXMLLoader.load(getClass().getClassLoader().getResource("view/FrmCadastroUsuario.fxml"));

			anchoPane.setTopAnchor(parentFrmCadastro, 0.0);
			anchoPane.setBottomAnchor(parentFrmCadastro, 0.0);
			
			anchoPane.setLeftAnchor(parentFrmCadastro, 0.0);
			anchoPane.setRightAnchor(parentFrmCadastro, 0.0);
			
			anchoPane.getChildren().add(parentFrmCadastro);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

    @SuppressWarnings("static-access")
	@FXML
    void handleCadastroClientes(ActionEvent event) {
    	
    	desativarPainel();
    	
    	try {
			parentFrmCadastro = FXMLLoader.load(getClass().getClassLoader().getResource("view/FrmCadastroCliente.fxml"));

			anchoPane.setTopAnchor(parentFrmCadastro, 0.0);
			anchoPane.setBottomAnchor(parentFrmCadastro, 0.0);
			
			anchoPane.setLeftAnchor(parentFrmCadastro, 0.0);
			anchoPane.setRightAnchor(parentFrmCadastro, 0.0);
			
			anchoPane.getChildren().add(parentFrmCadastro);

		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
	@SuppressWarnings("static-access")
	@FXML
	void handleBanco(ActionEvent event) {

		desativarPainel();

		try {
			parentFrmCadastro = FXMLLoader.load(getClass().getClassLoader().getResource("view/FrmCaminhoBanco.fxml"));

			anchoPane.setTopAnchor(parentFrmCadastro, 0.0);
			anchoPane.setBottomAnchor(parentFrmCadastro, 0.0);
			
			anchoPane.setLeftAnchor(parentFrmCadastro, 0.0);
			anchoPane.setRightAnchor(parentFrmCadastro, 0.0);
			
			anchoPane.getChildren().add(parentFrmCadastro);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@SuppressWarnings("static-access")
	public void mostrarTelaBanco() {

		try {
			Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("view/FrmCaminhoBanco.fxml"));
			
			stageCaminhoBanco = new Stage();
			
			Scene scene = new Scene(parent);
			stageCaminhoBanco.setScene(scene);
			stageCaminhoBanco.initStyle(StageStyle.UTILITY);
			stageCaminhoBanco.setTitle("Configuração do banco de dados");		
			stageCaminhoBanco.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean desativarPainel() {
		anchoPane.getChildren().clear();
		return true;
	}

}