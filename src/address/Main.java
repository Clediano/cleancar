package address;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		primaryStage.getIcons().add(new Image("file:resource/img/iconFrmPrincipal.png"));
		
		/**
		 * Carregando o arquivo FXML da página principal.
		 */
		initialize();
	}
	
	private void initialize() {
		try {
			Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("view/FrmLogin.fxml"));
			
			Scene scene = new Scene(parent);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Login");
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}