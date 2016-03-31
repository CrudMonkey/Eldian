package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CreateAccount implements Initializable {
	private String userid;
	
	
	@FXML
	private TextField username;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private Button SubmitButt;
	
	@FXML
	private ComboBox<String> SeqQDropt;

	@FXML
	private Button CreateButt;
	
	@FXML
	private Button backbtn;
	
	@FXML
	private TextField SeqQDropAns;
	
	@FXML
	private void onCreateButtClick(MouseEvent event) throws Exception {
		System.out.println("on create init");
		String Username = username.getText();
		String Password = password.getText();
		String securityAnswer = SeqQDropAns.getText();
		AccountCreation account = new AccountCreation();
		String securityQuestionId = Integer.toString(SeqQDropt.getSelectionModel().getSelectedIndex()); 
		account.AddEntry(Username, Password, securityQuestionId,securityAnswer);
		//System.out.println("lol");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//System.out.println("initizled");
		try {
			fillComboBox();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void fillComboBox() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		//System.out.println("fill combo box triggered");
		AccountCreation create_acc = new AccountCreation();
		ArrayList<String> listofQuestions = create_acc.getSecurityQuestions();
		SeqQDropt.getItems().addAll(listofQuestions);
	}

	@FXML
	private void onBackBtnClick(MouseEvent event) throws Exception {
		Stage stage = (Stage) username.getScene().getWindow();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setTitle("Hello World");
		stage.setScene(new Scene(root, 700, 575));
		stage.show();
	}
}
