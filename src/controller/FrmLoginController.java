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

public class FrmLoginController {

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
		// confere se o usuário existe no banco
		FrmLoginDAO login = new FrmLoginDAO();

		String nome = txtUsuario.getText();
		String senha = txtSenha.getText();

		if (login.confirmAccount(nome, senha)) {
			try {
				Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("view/FrmContainer.fxml"));

				Scene scene = new Scene(parent);

				Main.primaryStage.setScene(scene);
				Main.primaryStage.setTitle("Página Principal");
				Main.primaryStage.getIcons().add(new Image("img/iconFrmLogin.png"));
				Main.primaryStage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("Acesso negado!");
			alert.setContentText("Usuário ou senha inválido.");
			alert.setTitle("Usuarios do sistema");
			alert.show();
		}
	}

}
