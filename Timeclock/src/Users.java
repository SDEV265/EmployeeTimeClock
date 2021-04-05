import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Users {
	
	StringProperty UserID = new SimpleStringProperty();
	StringProperty Lastname = new SimpleStringProperty();
	StringProperty Firstname = new SimpleStringProperty();
	StringProperty Username = new SimpleStringProperty();
	StringProperty Password = new SimpleStringProperty();
	StringProperty Role = new SimpleStringProperty();
	StringProperty Inactive = new SimpleStringProperty();
	
	//
	public StringProperty UserIDProperty() {
        return UserID;
    }
	public StringProperty LastnameProperty() {
        return Lastname;
    }
	public StringProperty FirstnameProperty() {
        return Firstname;
    }
	public StringProperty UsernameProperty() {
        return Username;
    }
	public StringProperty PasswordProperty() {
        return Password;
    }
	public StringProperty RoleProperty() {
        return Role;
    }
	public StringProperty InactiveProperty() {
        return Inactive;
    }
	//Users setters
	public void setUserID(String value) {
    	UserID.set(value);
    }
    public void setLastname(String value) {
    	Lastname.set(value);
    }
    public void setFirstname(String value) {
    	Firstname.set(value);
    }
    public void setUsername(String value) {
    	Username.set(value);
    }
    public void setPassword(String value) {
    	Password.set(value);
    }
    public void setRole(String value) {
    	Role.set(value);
    }
    public void setInactive(String value) {
    	Inactive.set(value);
    }
    //Users getters
    public String getUserID() {
        return UserID.get();
    }
    public String getUsername() {
        return Username.get();
    }
    public String getLastname() {
        return Lastname.get();
    }
    public String getFirstname() {
        return Firstname.get();
    }
    public String getPassword() {
        return Password.get();
    }
    public String getRole() {
        return Role.get();
    }
    public String getInactive() {
        return Inactive.get();
    }
    
    public Users(String UserIDValue, String UsernameValue, String LastnameValue, String FirstnameValue, String PasswordValue, String RoleValue, String InactiveValue) {
    	UserID.set(UserIDValue);
    	Username.set(UsernameValue);
    	Lastname.set(UsernameValue);
    	Firstname.set(UsernameValue);
    	Password.set(PasswordValue);
    	Role.set(RoleValue);
    	Inactive.set(InactiveValue);
   
    }
    
    Users(){}
    

}
