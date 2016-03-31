package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ForgotPassword {
	
	private String userID;
	private String securityQuestionID;
	
	@FXML
	private TextField usernameTField;
	
	@FXML
	private Label securityQuestionLabel;
	
	@FXML
	private TextField answerToQuestionTField;
	
	@FXML
	private Button submit;
	
	@FXML
	private Button getQuestionBtn;
	
	@FXML
	private void onSubmit(MouseEvent event) throws ClassNotFoundException, SQLException, IOException{
		String answerToQuestion = answerToQuestionTField.getText();
		boolean isAnsValid = validateAnswer(answerToQuestion);
		if(isAnsValid)
			goToResetScreen();
		else{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Incorrect Answer");
			alert.showAndWait();
		}
			
	}
	
	@FXML
	private void ongetQuestion(MouseEvent event) throws ClassNotFoundException, SQLException{
		String username = usernameTField.getText();
		fillLabel(username);
	}
	
	private void goToResetScreen() throws IOException {
		// TODO Auto-generated method stub
		Stage stage = (Stage) securityQuestionLabel.getScene().getWindow();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("reset_password.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setTitle("Hello World");
		Scene scene = new Scene(root, 400, 375);
		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	private boolean validateAnswer(String answerToQuestion) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Account account = new Account(userID, "");
		String correctAns = account.getSecurityQuestionAnswer();
		AES aes = new AES(userID+securityQuestionID);
		aes.encrypt(answerToQuestion);
		String ansToQuestion = aes.getEncryptedString();
		if(ansToQuestion.equals(correctAns))
			return true;
		return false;
	}

	private void fillLabel(String username) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		//System.out.println("fill combo box triggered");
		AccountCreation create_acc = new AccountCreation();
		userID = create_acc.getUid(username);
		Account account = new Account(userID, "");
		System.out.println("uid is " + userID);
		String securityQuestionID = account.getSecurityQuestionID();
		String securityQuestion = account.getSecurityQuestionText(securityQuestionID);
		System.out.println("quesid and questtext are " + securityQuestionID + " " + securityQuestion);
		securityQuestionLabel.setText(securityQuestion);
	}
	
}
