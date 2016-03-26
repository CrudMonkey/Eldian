package application;

import org.passay.PasswordValidator;
import org.passay.RuleResult;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class PasswordAnalysis {
	
	private String userID;
	private String Password;
	private PasswordValidator validator;
	private RuleResult result;
	private PasswordScore score;
	private double scoreVar;
	private boolean isValid;

	@FXML
	private Button backBtn;

	private String PWLengthText = "";
	private String NumberofNumsText = "";
	private String NumberofUpperCText = "";
	private String NumberofLowerCText = "";
	private String NumberofSymbolsText = "";
	private String NumberofCharsText = "";
	private String NumofRepeatedCharText = "";
	private String IllegalSequenceLabelText = "";

	private boolean PWLengthisValid;
	private boolean NumberofNumsisValid;
	private boolean NumberofUpperCisValid;
	private boolean NumberofLowerCisValid;
	private boolean NumberofSymbolsisValid;
	private boolean NumberofCharsisValid;
	private boolean NumofRepeatedCharisValid;
	private boolean IllegalSequenceLabelisValid;
	
	
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
	
	
	

}
