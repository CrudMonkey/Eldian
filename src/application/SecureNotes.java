package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;

public class SecureNotes {

	@FXML
	private HTMLEditor editor;
	
	File currentFile;
	
	

	@FXML
	private void onSaveAs(MouseEvent event) {
		String text = editor.getHtmlText();
		//System.out.println(text);
		openSaveLocation(text);

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
        editor.setHtmlText(fileText);
	}
	
	@FXML
	private void onSave(MouseEvent event) {
		String text = editor.getHtmlText();
		if(currentFile == null){
			currentFile = openSaveLocation(text); 
		}
		else{
			SaveFile(text,currentFile);
		}
	}

}
