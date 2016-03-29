package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.EnglishSequenceData;
import org.passay.IllegalRegexRule;
import org.passay.IllegalSequenceRule;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.RuleResultDetail;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PasswordAnalysis implements Initializable {

	private String userID;
	private String masterPassword;
	private String Password;
	private PasswordValidator validator;
	private RuleResult result;
	private PasswordScore score;
	private double scoreVar;
	private boolean isValid;

	@FXML
	private Button backBtn;

	private HashMap<Label, Boolean> labelValidityMap;
	private HashMap<String, Label> errorMap;

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
		//System.out.println(PasswordInput.getText());
		isValid = validatePassword();
		//System.out.println("is password valid : " + isValid);
		checkEachValidation();
		if (Password.length() != 0)
			setLabelColors();
		// System.out.println(score.getPasswordscore());
	}

	private void setLabelColors() {
		// TODO Auto-generated method stub
		for (Label label : labelValidityMap.keySet()) {
			if (labelValidityMap.get(label) == true) {
				//System.out.println(label.getText());
				label.getStyleClass().add("errorLabel");
				//System.out.println(label.getStyleClass());
			} else {
				label.getStyleClass().add("validLabel");
			}
		}

	}

	private void checkEachValidation() {
		// TODO Auto-generated method stub
		initializeLabels();
		initializeErrorMap();
		initializeColorsofLabels();
		ArrayList<String> listofErrors = new ArrayList<String>();
		for (RuleResultDetail msg : result.getDetails()) {
			//System.out.println("Error is " + msg.getErrorCode());
			Label errorLabel = errorMap.get(msg.getErrorCode());
			//System.out.println("Error label  is " + errorLabel);
			if (errorLabel != null) {
				labelValidityMap.put(errorLabel, true);
			}

		}

		scoreVar = Password.length() * 8;

		score.setPasswordscore(scoreVar * 0.01);
	}

	private void initializeColorsofLabels() {
		// TODO Auto-generated method stub
		for(Label label : labelValidityMap.keySet()){
			//System.out.println("label calsses are "+label.getStyleClass());
			label.getStyleClass().remove("errorLabel");
			label.getStyleClass().remove("validLabel");
		}
		
	}

	private void initializeLabels() {
		// TODO Auto-generated method stub

		labelValidityMap.put(NumberofChars, false);
		labelValidityMap.put(NumberofNums, false);
		labelValidityMap.put(NumofLowerC, false);
		labelValidityMap.put(NumofUpperC, false);
		labelValidityMap.put(NumofRepeatedChar, false);
		labelValidityMap.put(PWLength, false);
		labelValidityMap.put(IllegalSequenceLabel, false);
		labelValidityMap.put(NumofSymbols, false);
	}

	private void initializeErrorMap() {
		errorMap.put("TOO_SHORT", PWLength);
		errorMap.put("INSUFFICIENT_UPPERCASE", NumofUpperC);
		errorMap.put("INSUFFICIENT_LOWERCASE", NumofLowerC);
		errorMap.put("INSUFFICIENT_DIGIT", NumberofNums);
		errorMap.put("INSUFFICIENT_SPECIAL", NumofSymbols);
		errorMap.put("ILLEGAL_MATCH", NumofRepeatedChar);
		errorMap.put("INSUFFICIENT_ALPHABETICAL", NumberofChars);
		errorMap.put("ILLEGAL_NUMERICAL_SEQUENCE", IllegalSequenceLabel);
		errorMap.put("ILLEGAL_ALPHABETICAL_SEQUENCE", IllegalSequenceLabel);
		errorMap.put("ILLEGAL_QWERTY_SEQUENCE", IllegalSequenceLabel);
	}

	private boolean validatePassword() {
		// TODO Auto-generated method stub
		result = validator.validate(new PasswordData(new String(Password)));
		if (result.isValid())
			return true;
		return false;
	}

	private void addRules() {
		validator = new PasswordValidator(Arrays.asList(
				// length between 8 and 16 characters
				new LengthRule(8, 16),

				// at least one upper-case character
				new CharacterRule(EnglishCharacterData.UpperCase, 2),

				// at least one lower-case character
				new CharacterRule(EnglishCharacterData.LowerCase, 2),

				// at least one digit character
				new CharacterRule(EnglishCharacterData.Digit, 2),

				// at least one symbol (special character)
				new CharacterRule(EnglishCharacterData.Special, 2),

				new CharacterRule(EnglishCharacterData.Alphabetical, 2),

				new IllegalRegexRule("(\\w)\\1+"),

				new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 3, true),

				new IllegalSequenceRule(EnglishSequenceData.Numerical, 3, true),

				new IllegalSequenceRule(EnglishSequenceData.USQwerty, 3, true)
		));

	}

	@FXML
	private void pbCheck(ActionEvent event) {
		score.setPasswordscore(score.getPasswordscore() + 0.1);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		errorMap = new HashMap<>();
		labelValidityMap = new HashMap<>();
		addTextLimiter(PasswordInput, 14);
		score = new PasswordScore();
		addRules();
		score.setPasswordscore(0);
		score.numberProperty().addListener((v, oldValue, newValue) -> {
		});
		Progress.progressProperty().bind(score.numberProperty());
	}

	public static void addTextLimiter(final TextField tf, final int maxLength) {
		tf.textProperty().addListener((ov, oldValue, newValue) -> {
			if (tf.getText().length() > maxLength) {
				String s = tf.getText().substring(0, maxLength);
				tf.setText(s);
			}
		});
	}

	@FXML
	private void onBackBtnClick(MouseEvent event) throws Exception {
		Stage stage = (Stage) IllegalSequenceLabel.getScene().getWindow();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home_Screen.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		HomeScreen controller = fxmlLoader.<HomeScreen> getController();
		controller.setUser(userID, masterPassword);
		stage.setTitle("Hello World");
		stage.setScene(new Scene(root, 700, 575));
		stage.show();
	}

	public void setUser(String userid, String masterPassword) {
		// TODO Auto-generated method stub
		this.userID = userid;
		this.masterPassword = masterPassword;
	}

}
