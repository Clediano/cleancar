package address;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.PropertiesLoaderImpl;

public class Main extends Application {

	public static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		primaryStage.getIcons().add(new Image("file:resource/img/iconFrmPrincipal.png"));

		initialize();
	}

	private void initialize() {
		try {

			PropertiesLoaderImpl.setValor("caminho", "CAMINHO DE TESTE");
			
			Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("view/FrmLogin.fxml"));

			Scene scene = new Scene(parent);
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UTILITY);
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