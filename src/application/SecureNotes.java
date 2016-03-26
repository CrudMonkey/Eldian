package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;

public class SecureNotes {

	@FXML
	private HTMLEditor editor;
	
	private File currentFile;
	
	private String password;
	int uid = 0;
	

	public void setPassword(String password) {
		this.password = password;
		 uid = 109;
	}

	@FXML
	private void onSaveAs(MouseEvent event) {
		String text = editor.getHtmlText();
		if(uid != 109){
		password = getPasswordfromUser();
		}
		AES a = new AES(password);
		a.encrypt(text);
		String encryptedText = a.getEncryptedString();
		//System.out.println(text);
		System.out.println("as" + encryptedText);
		openSaveLocation(encryptedText);

	}
	
	private File openSaveLocation(String text) {
		// TODO Auto-generated method stub
		FileChooser fileChooser = new FileChooser();
        
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = 
            new FileChooser.ExtensionFilter("CRYPT files (*.ldn)", "*.ldn");
        fileChooser.getExtensionFilters().add(extFilter);
         
        //Show save file dialog
        File file = fileChooser.showSaveDialog(editor.getScene().getWindow());
         
        if(file != null){
            SaveFile(text, file);
        }
        
        return file;
		
	}

	private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter;
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
          
    }
	
	@FXML
	private void onOpen() throws IOException{
		FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        currentFile = fileChooser.showOpenDialog(null);
        List<String> text = Files.readAllLines(currentFile.toPath());
        String fileText = text.get(0);
        if(uid != 109){
        password = getPasswordfromUser();
        }
        AES a = new AES(password);
		a.decrypt(fileText);
		String decryptedText = a.getDecryptedString();
        editor.setHtmlText(decryptedText);
	}
	
	private String getPasswordfromUser() {
		// TODO Auto-generated method stub
		TextInputDialog dialog = new TextInputDialog("walter");
		dialog.setTitle("Text Input Dialog");
		dialog.setHeaderText("Look, a Text Input Dialog");
		dialog.setContentText("Please enter your name:");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		   return result.get();
		}
		return null;
	}

	@FXML
	private void onSave(MouseEvent event) {
		String text = editor.getHtmlText();
		if(password == null){
			password = getPasswordfromUser();
		}
		AES a = new AES(password);
		a.encrypt(text);
		String encryptedText = a.getEncryptedString();
		System.out.println("s"+encryptedText);
		if(currentFile == null){
			currentFile = openSaveLocation(encryptedText); 
		}
		else{
			
			SaveFile(encryptedText,currentFile);
		}
	}

}
