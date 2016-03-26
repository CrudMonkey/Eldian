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
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Secure_Notes.fxml")); 
        Parent root = (Parent)fxmlLoader.load(); 
        SecureNotes controller = fxmlLoader.<SecureNotes>getController();
        //controller.setUser(user_id);
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 700, 575);
		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
