package application;

public class UserEntry {
	
	private String user_id;
	private String entry_id;
	private String account_name;
	private String password;
	private String login_id;
	private String category;

	public UserEntry(String userID){
		this.user_id = userID;
	}

	
	public String getUser_id() {
		return user_id;
	}


	public String getEntry_id() {
		return entry_id;
	}

	public void setEntry_id(String entry_id) {
		this.entry_id = entry_id;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
}
