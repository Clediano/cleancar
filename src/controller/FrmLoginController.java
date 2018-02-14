package controller;

import java.io.IOException;

import address.Main;
import dao.FrmLoginDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import jdbc.ConnectionFactory;
import util.PropertiesLoaderImpl;

public class FrmLoginController {

	public static Stage stagePrincipal;

	@FXML
	private Button btnConfirmar;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private Button btnCancelar;

	@FXML
	private TextField txtUsuario;

	@FXML
	void handleConfirmar(ActionEvent event) {
		verificarUsuario();
	}

	@FXML
	void handleCancelar(ActionEvent event) {
		// fecha a tela de login
		Main.primaryStage.close();
	}

	@FXML
	void keyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			verificarUsuario();
		}
	}

	public void verificarUsuario() {

		System.out.println(PropertiesLoaderImpl.getValor("caminho"));

		if (PropertiesLoaderImpl.getValor("URL").isEmpty() || PropertiesLoaderImpl.getValor("USER").isEmpty()
				|| PropertiesLoaderImpl.getValor("PASSWORD").isEmpty()) {
			try {
				Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("view/FrmCaminhoBanco.fxml"));

				Scene scene = new Scene(parent);
				
				FrmContainerController.stageCaminhoBanco = new Stage();
				
				FrmContainerController.stageCaminhoBanco.setScene(scene);
				FrmContainerController.stageCaminhoBanco.setTitle("Configura��o do banco de dados");
				FrmContainerController.stageCaminhoBanco.show();

			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Imposs�vel abrir a tela do caminho do banco - FrmLoginController catch exception");
			}
		} else {

			// alimenta o URL para acessar o banco de dados
			ConnectionFactory.URL += PropertiesLoaderImpl.getValor("URL");
			ConnectionFactory.USU += PropertiesLoaderImpl.getValor("USER");
			ConnectionFactory.PASS += PropertiesLoaderImpl.getValor("PASSWORD");
			
			FrmLoginDAO login = new FrmLoginDAO();

			String nome = txtUsuario.getText();
			String senha = txtSenha.getText();

			if (login.confirmAccount(nome, senha)) {
				try {
					Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("view/FrmContainer.fxml"));

					Scene scene = new Scene(parent);

					stagePrincipal = new Stage();

					stagePrincipal.setScene(scene);
					stagePrincipal.setTitle("P�gina Principal");
					stagePrincipal.getIcons().add(new Image("img/iconFrmLogin.png"));
					stagePrincipal.show();

					Main.primaryStage.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setHeaderText("Usu�rio Inv�lido!");
				alert.setContentText("Conex�o n�o efetuada!");
				alert.setTitle("Verifique os dados informados.");
				alert.show();
			}
		}
	}
}