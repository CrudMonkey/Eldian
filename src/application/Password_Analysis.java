package application;

import java.net.URL;
import java.util.ResourceBundle;

import org.passay.PasswordValidator;
import org.passay.RuleResult;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Password_Analysis implements Initializable{
	
	
	
	private String userID;
	private String Password;
	private PasswordValidator validator;
	private RuleResult result;
	private double scoreVar;
	private boolean isValid;
	
	
	
	@FXML
	private GridPane grid;
	@FXML
	private TextField PasswordInput;

	@FXML
	private ProgressBar Progress;

	@FXML
	private Label PWComment;

	@FXML
	private Label PWLength;

	@FXML
	private Label NumberofChars;

	@FXML
	private Label NumberofNums;

	@FXML
	private Label NumofUpperC;

	@FXML
	private Label NumofSymbols;

	@FXML
	private Label NumofLowerC;

	@FXML
	private Label NumofRepeatedChar;

	@FXML
	private Label IllegalSequenceLabel;

	@FXML
	private Text PWQualityMetrics;

	@FXML
	private Button button;

	@FXML
	private void onChange(KeyEvent event) {
		// System.out.println("lol");
		Password = PasswordInput.getText();
		System.out.println(PasswordInput.getText());
		//boolean isValid = validatePassword();
		System.out.println("is password valid : " + isValid);
		
		
	}

		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	

}
