
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class UsersStage {
	
	String strUser,strRole;
	Label lblCurrentUser = new Label("");
	Label lblCurrentRole = new Label("");
	Label lblTitle = new Label("Purple Team Time clock");
	Label lblNotice = new Label("");
	Label lblNotice2 = new Label("");
	Label lblUserID = new Label("User ID");
	Label lblUserIDField = new Label("");
	Label lblLastname = new Label("Last Name");
	TextField tfLastname = new TextField("");
	Label lblFirstname = new Label("First Name");
	TextField tfFirstname = new TextField("");
	Label lblUsername = new Label("Username");
	TextField tfUsername = new TextField("");
	Label lblPassword = new Label("Password");
	Label lblRole = new Label("Role");
	Label lblInactive = new Label("Inactive");
	TextField tfPassword = new TextField();
	ComboBox<String> cmboRole = new ComboBox<String>();
	ComboBox<String> cmboInactive = new ComboBox<String>();
	Button btnAddUser = new Button("Add User");
	Button btnUpdate = new Button("Update");
	Button btnClose = new Button("Close");
	
private TableView<Users> usersView = new TableView<>();
	
	BorderPane pane = new BorderPane();
	BorderPane pane2 = new BorderPane();
	
	static ObservableList listUsersToo = FXCollections.observableArrayList();
	ArrayList<String> listClockNumbers = new ArrayList<>();
	
	private static Connection connection;
	
	UsersStage(String newUser, String newRole,ObservableList newListUsers, ArrayList newListClockNumbers) throws ClassNotFoundException, SQLException{
		
		strUser = newUser;
		strRole = newRole;
		listUsersToo = newListUsers;
		listClockNumbers = newListClockNumbers;
		Stage UsersStage = new Stage();
		
		System.out.println("enter Scene getUsers");
		GridPane usersPane = new GridPane();
		usersPane.setAlignment(Pos.BASELINE_CENTER);
		usersPane.setPadding(new Insets(20.0, 20.0,20.0,20.0));
		usersPane.setHgap(20.0);
		usersPane.setVgap(20.0);
		lblUserID.setMinWidth(100);
		lblUserID.setFont(Font.font(16.0));
		lblUserIDField.setMinWidth(200);
		lblUserIDField.setFont(Font.font(16.0));
		lblUsername.setMinWidth(100);
		lblUsername.setFont(Font.font(16.0));
		lblLastname.setMinWidth(100);
		lblLastname.setFont(Font.font(16.0));
		lblFirstname.setMinWidth(100);
		lblFirstname.setFont(Font.font(16.0));
		tfLastname.setFont(Font.font(16.0));
		tfLastname.setFont(Font.font(16.0));
		tfFirstname.setFont(Font.font(16.0));
		tfFirstname.setFont(Font.font(16.0));
		tfUsername.setMinWidth(200);
		tfUsername.setFont(Font.font(16.0));
		lblPassword.setMinWidth(100);
		lblPassword.setFont(Font.font(16.0));
		tfPassword.setMinWidth(200);
		tfPassword.setFont(Font.font(16.0));
		lblRole.setMinWidth(100);
		lblRole.setFont(Font.font(16.0));
		lblInactive.setMinWidth(100);
		lblInactive.setFont(Font.font(16.0));
		cmboRole.setMinWidth(200);
		cmboInactive.setMinWidth(200);
		btnAddUser.setMinWidth(200);
		btnAddUser.setFont(Font.font(16.0));
		btnUpdate.setMinWidth(200);
		btnUpdate.setFont(Font.font(16.0));
		btnClose.setMinWidth(200);
		btnClose.setFont(Font.font(16.0));
		
		lblCurrentUser.setText(strUser);
		lblCurrentRole.setText(strRole);
		cmboRole.getItems().addAll("SELECT","Admin", "Management","Supervisor");
		cmboInactive.getItems().addAll("SELECT","TRUE", "FALSE");
		cmboRole.getSelectionModel().select(0);
		cmboInactive.getSelectionModel().select(0);
		
		usersData();
		
		//checking for mouse click of tableview
		usersView.setOnMouseClicked(e->{
		Users rowValue = usersView.getSelectionModel().getSelectedItem();
		String strUserID = rowValue.getUserID();
		String strLastname = rowValue.getLastname();
		String strFirstname = rowValue.getFirstname();
		String strUsername = rowValue.getUsername();
		String strPassword = rowValue.getPassword();
		String strRole = rowValue.getRole();
		String strInactive = rowValue.getInactive();
		
		lblUserIDField.setText(strUserID);
		tfLastname.setText(strLastname);
		tfFirstname.setText(strFirstname);
		tfUsername.setText(strUsername);
		tfPassword.setText(strPassword);
		cmboRole.setValue(strRole);
		cmboInactive.setValue(strInactive);
				
		});
		
		usersPane.add(lblUserID, 1, 1);
		usersPane.add(lblUserIDField, 2, 1);
		usersPane.add(lblLastname, 1, 2);
		usersPane.add(tfLastname, 2, 2);
		usersPane.add(lblFirstname, 1, 3);
		usersPane.add(tfFirstname, 2, 3);
		usersPane.add(lblUsername, 1, 4);
		usersPane.add(tfUsername, 2, 4);
		usersPane.add(lblPassword, 1, 5);
		usersPane.add(tfPassword, 2, 5);
		usersPane.add(lblRole, 1, 6);
		usersPane.add(cmboRole, 2, 6);
		usersPane.add(lblInactive, 1, 7);
		usersPane.add(cmboInactive, 2, 7);
		usersPane.add(lblNotice, 4, 1);
		usersPane.add(lblNotice2, 4, 2);
		usersPane.add(btnUpdate, 4, 3);
		usersPane.add(btnAddUser, 4, 4);
		usersPane.add(btnClose, 4, 5);
		usersPane.add(lblCurrentUser, 2, 8);
		usersPane.add(lblCurrentRole, 4, 8);
		
		VBox usersVBox = new VBox();
		usersVBox.setPadding(new Insets(15,15,15,15));
		usersVBox.getChildren().add(usersView);
		usersVBox.setPrefHeight(800);
		usersVBox.setPrefWidth(1000);
		
btnAddUser.setOnAction(e-> {
			
			//checking required field values
			if(tfUsername.getText().isEmpty() || cmboRole.getValue().equals("SELECT") || cmboInactive.getValue().equals("SELECT")  ) {
				lblNotice.setText("*Data required!");
				lblNotice2.setText("Select a Dataset.");
				lblNotice.setFont(Font.font(18.0));
				lblNotice.setTextFill(Color.RED);
				lblNotice2.setFont(Font.font(18.0));
				lblNotice2.setTextFill(Color.RED);
				
				
			}//end if strInput true and value empty
			else {
				lblNotice.setText("");
				lblNotice2.setText("");
			
	        try {
	        	String updatePMEquipmentList = "Insert into tblUsers (Lastname, Firstname, Username, Password, Role, Inactive)"+
	        			"VALUES ('"+ tfLastname.getText() +
	        			"','"+ tfFirstname.getText() +
	        			"','"+ tfUsername.getText() +
	        			"','"+ tfPassword.getText() +
	        			"','"+cmboRole.getValue()+
	        			"','"+cmboInactive.getValue()+"');";
	        	System.out.println(updatePMEquipmentList);
	        	PreparedStatement preparedStatement = connection.prepareStatement(updatePMEquipmentList);
	           preparedStatement.executeUpdate();

	        }//end of try for update query 
	        catch (SQLException ex) {
	            ex.printStackTrace();
	        }//end of catch for update query
	        
	        
	        //return field values to empty state
	        lblUserIDField.setText("");
	        tfUsername.setText("");
	        tfPassword.setText("");
			cmboRole.getSelectionModel().select(0);
			cmboInactive.getSelectionModel().select(0);
			
			try {
				new UsersStage(strUser,strRole,listUsersToo, listClockNumbers);
				UsersStage.close();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	        
			}//end if else
			
		});//end btnAddUser
		
		btnUpdate.setOnAction(e-> {
			
			//checking required field values
			if(lblUserIDField.getText().isEmpty() || tfUsername.getText().isEmpty() || cmboRole.getValue().equals("SELECT") || cmboInactive.getValue().equals("SELECT")  ) {
				lblNotice.setText("*Data required!");
				lblNotice2.setText("Select a Dataset.");
				lblNotice.setFont(Font.font(18.0));
				lblNotice.setTextFill(Color.RED);
				lblNotice2.setFont(Font.font(18.0));
				lblNotice2.setTextFill(Color.RED);
				
				
			}//end if strInput true and value empty
			else {
				lblNotice.setText("");
				lblNotice2.setText("");
			
	        try {
	        	String updatePMEquipmentList = "UPDATE tblUsers SET tblUsers.Username = '"+ tfUsername.getText() +
	        			"', tblUsers.Password = '"+ tfPassword.getText() +
	        			"',tblUsers.Role = '"+cmboRole.getValue()+
	        			"',tblUsers.Inactive = '"+cmboInactive.getValue()+
	        			"' WHERE tblUsers.UserID = "+ lblUserIDField.getText()+";";
	        	System.out.println(updatePMEquipmentList);
	        	PreparedStatement preparedStatement = connection.prepareStatement(updatePMEquipmentList);
	           preparedStatement.executeUpdate();

	        }//end of try for update query 
	        catch (SQLException ex) {
	            ex.printStackTrace();
	        }//end of catch for update query
	        
	        
	        //return field values to empty state
	        lblUserIDField.setText("");
	        tfUsername.setText("");
	        tfPassword.setText("");
			cmboRole.getSelectionModel().select(0);
			cmboInactive.getSelectionModel().select(0);
			
			try {
				new UsersStage(strUser,strRole, listUsersToo, listClockNumbers);
				UsersStage.close();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	        
			}//end if else
			
		});//end btnUpdate
		
		btnClose.setOnAction(e-> {
			try {
				new MgmtMenuStage(strUser,strRole, listUsersToo, listClockNumbers);
				UsersStage.close();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			
		});
		
		pane.setCenter(usersVBox);
		pane.setBottom(usersPane);
		
		Scene sceneUsers = new Scene(pane,1200,600);
		Image logo = new Image("file:images/Reliancelogo.jpg");
		UsersStage.getIcons().add(logo);
		UsersStage.setTitle("Project Purple Team");
	
		UsersStage.setScene(sceneUsers);
		UsersStage.show();
		UsersStage.setMaximized(true);
	}
	
public void usersData() throws ClassNotFoundException, SQLException {
    	
    	String select = "SELECT * FROM tblUsers;";
    	System.out.println(select);

        //Extracting data from Database
        ResultSet resultSet = null;
        try {
        	connection = DriverManager.getConnection(MySQLConnection.getPath(),MySQLConnection.getDatabase(),MySQLConnection.getDBPassword());
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
  
        ObservableList dbData = FXCollections.observableArrayList(usersArrayList(resultSet));
        
        usersView.setEditable(true);
        TableColumn <Users,String> UserID = new TableColumn("UserID");
        TableColumn <Users,String> Lastname = new TableColumn("Lastname");
        TableColumn <Users,String> Firstname = new TableColumn("Firstname");
    	TableColumn <Users,String> Username = new TableColumn("Username");
    	TableColumn <Users,String> Password = new TableColumn("Password");
    	TableColumn <Users,String> Role = new TableColumn("Role");
    	TableColumn <Users,String> Inactive = new TableColumn("Inactive");
    	
       
        //Setting cell property value to correct variable from Equipment class
    	UserID.setCellValueFactory(new PropertyValueFactory<Users,String>("UserID"));
    	Lastname.setCellValueFactory(new PropertyValueFactory<Users,String>("Lastname"));
    	Firstname.setCellValueFactory(new PropertyValueFactory<Users,String>("Firstname"));
        Username.setCellValueFactory(new PropertyValueFactory<Users,String>("Username"));
        Password.setCellValueFactory(new PropertyValueFactory<Users,String>("Password"));
        Role.setCellValueFactory(new PropertyValueFactory<Users,String>("Role"));
        Inactive.setCellValueFactory(new PropertyValueFactory<Users,String>("Inactive"));
        
        //set cells to textfields for tableview edit
        //one edits view not actual table
        //UserID.setCellFactory(TextFieldTableCell.forTableColumn());//do not want ID to be editable
        Lastname.setCellFactory(TextFieldTableCell.forTableColumn());
        Firstname.setCellFactory(TextFieldTableCell.forTableColumn());
        Username.setCellFactory(TextFieldTableCell.forTableColumn());
        Password.setCellFactory(TextFieldTableCell.forTableColumn());
        Role.setCellFactory(ComboBoxTableCell.forTableColumn("PMTech", "Encoder","Admin"));
        //Inactive.setCellFactory(TextFieldTableCell.forTableColumn());
        Inactive.setCellFactory(ComboBoxTableCell.forTableColumn("True","False"));
        
        //create eventhandlers for each lastname cell change
        Lastname.setOnEditCommit(new EventHandler<CellEditEvent<Users, String>>(){
        	
        	@Override
        	public void handle(CellEditEvent<Users, String> event) {
        		try {
        			Users users = event.getRowValue();
        			users.setLastname(event.getNewValue());
        			tfLastname.setText(event.getNewValue());
        			
    	        	String updateUsers = "UPDATE tblUsers SET tblUsers.Lastname = '"+ tfLastname.getText() +
    	        			"' WHERE tblUsers.UserID = "+ lblUserIDField.getText()+";";
    	        	System.out.println(updateUsers);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateUsers);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query
        		
        		
        	}//end handle event
        });//end Lastname setOnEditCommit event handler
        
      //create eventhandlers for each name cell change
        Firstname.setOnEditCommit(new EventHandler<CellEditEvent<Users, String>>(){
        	
        	@Override
        	public void handle(CellEditEvent<Users, String> event) {
        		try {
        			Users users = event.getRowValue();
        			users.setFirstname(event.getNewValue());
        			tfFirstname.setText(event.getNewValue());
        			
    	        	String updateUsers = "UPDATE tblUsers SET tblUsers.Firstname = '"+ tfFirstname.getText() +
    	        			"' WHERE tblUsers.UserID = "+ lblUserIDField.getText()+";";
    	        	System.out.println(updateUsers);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateUsers);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query
        		
        		
        	}//end handle event
        });//end Firstname setOnEditCommit event handler
        
      //create eventhandlers for each Username cell change
        Username.setOnEditCommit(new EventHandler<CellEditEvent<Users, String>>(){
        	
        	@Override
        	public void handle(CellEditEvent<Users, String> event) {
        		try {
        			Users users = event.getRowValue();
        			users.setUsername(event.getNewValue());
        			tfUsername.setText(event.getNewValue());
        			
    	        	String updateUsers = "UPDATE tblUsers SET tblUsers.Username = '"+ tfUsername.getText() +
    	        			"' WHERE tblUsers.UserID = "+ lblUserIDField.getText()+";";
    	        	System.out.println(updateUsers);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateUsers);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query
        		
        		
        	}//end handle event
        });//end Username setOnEditCommit event handler
        
      //create eventhandlers for each Password cell change
        Password.setOnEditCommit(new EventHandler<CellEditEvent<Users, String>>(){
        	
        	@Override
        	public void handle(CellEditEvent<Users, String> event) {
        		try {
        			Users users = event.getRowValue();
        			users.setPassword(event.getNewValue());
        			tfPassword.setText(event.getNewValue());
        			
    	        	String updateUsers = "UPDATE tblUsers SET tblUsers.Password = '"+ tfPassword.getText() +
    	        			"' WHERE tblUsers.UserID = "+ lblUserIDField.getText()+";";
    	        	System.out.println(updateUsers);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateUsers);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query
        		
        		
        	}//end handle event
        });//end Password setOnEditCommit event handler
        
      //create eventhandlers for each role cell change
        Role.setOnEditCommit(new EventHandler<CellEditEvent<Users, String>>(){
        	
        	@Override
        	public void handle(CellEditEvent<Users, String> event) {
        		try {
        			Users users = event.getRowValue();
        			users.setRole(event.getNewValue());
        			cmboRole.setValue(event.getNewValue());
        			
    	        	String updateUsers = "UPDATE tblUsers SET tblUsers.Role = '"+ cmboRole.getValue() +
    	        			"' WHERE tblUsers.UserID = "+ lblUserIDField.getText()+";";
    	        	System.out.println(updateUsers);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateUsers);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query
        		
        		
        	}//end handle event
        });//end Role setOnEditCommit event handler
        
      //create eventhandlers for each role cell change
        Inactive.setOnEditCommit(new EventHandler<CellEditEvent<Users, String>>(){
        	
        	@Override
        	public void handle(CellEditEvent<Users, String> event) {
        		try {
        			Users users = event.getRowValue();
        			users.setInactive(event.getNewValue());
        			cmboInactive.setValue(event.getNewValue());
        			
    	        	String updateUsers = "UPDATE tblUsers SET tblUsers.Inactive = '"+ cmboInactive.getValue() +
    	        			"' WHERE tblUsers.UserID = "+ lblUserIDField.getText()+";";
    	        	System.out.println(updateUsers);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateUsers);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query
        		
        		
        	}//end handle event
        });//end Inactive setOnEditCommit event handler
       
        usersView.getColumns().addAll(UserID, Lastname, Firstname, Username, Password, Role, Inactive);

        //Filling up tableView with data
        usersView.setItems(dbData);
        usersView.getSelectionModel().getSelectedIndex();//setCellSelectionEnabled(true);
        int index = usersView.getSelectionModel().getSelectedIndex();
        System.out.println(index);   
         
    }//end revisionData

//extracting data from ResulSet to ArrayList
	private ArrayList usersArrayList(ResultSet resultSet) throws SQLException {
		ArrayList<Users> data =  new ArrayList<>();
		while (resultSet.next()) {
			Users users = new Users();
			users.UserID.set(resultSet.getString("UserID"));
			users.Lastname.set(resultSet.getString("Lastname"));
			users.Firstname.set(resultSet.getString("Firstname"));
			users.Username.set(resultSet.getString("Username"));
			users.Password.set(resultSet.getString("Password"));
			users.Role.set(resultSet.getString("Role"));
			users.Inactive.set(resultSet.getString("Inactive"));
		
			data.add(users);
		}
		return data;
	}//end revisionsArrayList
	
}