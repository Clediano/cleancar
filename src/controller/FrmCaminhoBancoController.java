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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import jdbc.ConnectionFactory;
import util.PropertiesLoaderImpl;

public class FrmCaminhoBancoController implements Initializable {

	boolean selected = false;

	@FXML
	private Button btnConfirmar;

	@FXML
	private Label lblUsuario;

	@FXML
	private Button btnFechar;

	@FXML
	private Button btnEditar;

	@FXML
	private HBox hBox;

	@FXML
	private Tab hBoxCadastro;

	@FXML
	private Button btnIncluir;

	@FXML
	private Button btnCaminho;

	@FXML
	public TextField txtUser;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnTestar;

	@FXML
	public TextField txtURL;

	@FXML
	private Button btnExcluir;

	@FXML
	private TabPane tabPane;

	@FXML
	public PasswordField txtPassword;

	@FXML
	void handleIncluir(ActionEvent event) {
	}

	@FXML
	void handleExcluir(ActionEvent event) {
	}

	@FXML
	void handleEditar(ActionEvent event) {
		txtURL.setDisable(false);
		txtUser.setDisable(false);
		txtPassword.setDisable(false);
		btnCaminho.setVisible(true);
	}

	@FXML
	void handleCancelar(ActionEvent event) {
		
	}

	@FXML
	void handleFechar(ActionEvent event) {
		if (FrmLoginController.stageFrmCaminhoBanco.isShowing()) {
			FrmLoginController.stageFrmCaminhoBanco.close();
		} else {
			FrmContainerController.parentFrmCaminhoBanco.setVisible(false);
		}
	}

	@FXML
	void handleTestar(ActionEvent event) {
		String drive = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://" + txtURL.getText();
		String usu = txtUser.getText();
		String pass = txtPassword.getText();

		if (ConnectionFactory.testConnection(drive, url, usu, pass)) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Caminho do banco válido!");
			alert.setContentText("Conexão efetuada!");
			alert.setTitle("Configuração do banco de dados.");
			alert.show();

			btnConfirmar.setDisable(false);
			btnTestar.setDisable(true);

		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("Caminho do banco não informado ou inválido!");
			alert.setContentText("Conexão não efetuada!");
			alert.setTitle("Verifique os dados informados.");
			alert.show();
		}
	}

	@FXML
	void handleConfirmar(ActionEvent event) {
		PropertiesLoaderImpl.setValor("URL", txtURL.getText());
		PropertiesLoaderImpl.setValor("USER", txtUser.getText());
		PropertiesLoaderImpl.setValor("PASSWORD", txtPassword.getText());

		if (FrmLoginController.stageFrmCaminhoBanco.isShowing()) {
			FrmLoginController.stageFrmCaminhoBanco.close();
		} else {
			FrmContainerController.parentFrmCaminhoBanco.setVisible(false);
		}

	}

	@FXML
	void handleCaminho(ActionEvent event) {

		DirectoryChooser directoryChooser = new DirectoryChooser();

		directoryChooser.setTitle("Selecione a pasta do banco");

		File file = directoryChooser.showDialog(null);

		if (file != null) {
			txtURL.setText(file.getPath());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (!selected) {
			btnConfirmar.setDisable(true);
		}

		btnIncluir.setDisable(true);
		btnExcluir.setDisable(true);
		btnCancelar.setDisable(true);
		btnCaminho.setVisible(false);

		txtURL.setText(PropertiesLoaderImpl.getValor("URL"));
		txtURL.setDisable(true);
		txtUser.setText(PropertiesLoaderImpl.getValor("USER"));
		txtUser.setDisable(true);
		txtPassword.setText(PropertiesLoaderImpl.getValor("PASSWORD"));
		txtPassword.setDisable(true);
	}
}