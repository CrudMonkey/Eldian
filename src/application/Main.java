package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		Login controller = fxmlLoader.<Login> getController();
		primaryStage.setTitle("Login");
		Scene scene = new Scene(root, 600, 575);
		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
