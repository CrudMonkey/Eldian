package application;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.ObservableList;

public class DeleteEntry {
	private String userID;
	
	
	public DeleteEntry(String userID) {
		// TODO Auto-generated constructor stub
		this.userID = userID;
		
	}
	
	public void deleteEntries(ObservableList<UserEntry> selectedItems) throws ClassNotFoundException, SQLException{
		Account account = new Account(userID);
		ArrayList<String> idList = new ArrayList<>();
		for(UserEntry userEntry : selectedItems){
			idList.add(userEntry.getEntry_id());
		}
		account.deleteEntry(idList);
	}
}
