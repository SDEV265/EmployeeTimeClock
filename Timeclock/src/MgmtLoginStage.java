import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MgmtLoginStage {
	
	Label lblTitle = new Label("Purple Team Time clock");
	Label lblUser = new Label("Username: ");//Username label
	Label lblPassword = new Label("Password: ");//Password label
	TextField tfPassword = new TextField();//Password textbox
	Button btnLogin = new Button("Login");
	Button btnLogout = new Button("Logout");
	Label lblNotice = new Label("");
	
	private static Connection connection;
	
	final static ObservableList listUsers = FXCollections.observableArrayList();
	ComboBox<String> cmboUsers = new ComboBox<String>(listUsers);
	
	BorderPane pane = new BorderPane();
	
	MgmtLoginStage() throws ClassNotFoundException, SQLException{
		
		Stage mgmtLoginStage = new Stage();
		
		if(listUsers.isEmpty()) {
			//fills User combobox
			fillUserComboBox();
		}
		
		System.out.println("enter Scene Mgmtlogin");
		GridPane mgmtLoginPane = new GridPane();
		mgmtLoginPane.setAlignment(Pos.BASELINE_CENTER);
		mgmtLoginPane.setPadding(new Insets(20.0, 20.0,20.0,20.0));
		mgmtLoginPane.setHgap(20.0);
		mgmtLoginPane.setVgap(20.0);
		
		//Place nodes in login pane
		lblTitle.setFont(Font.font(40.0));
		lblTitle.setTextFill(Color.rgb(51,51,255));
		lblUser.setFont(Font.font(16.0));
		lblPassword.setFont(Font.font(16.0));
		tfPassword.setMinWidth(200);
		tfPassword.setFont(Font.font(16.0));
		cmboUsers.getSelectionModel().select(0);
		cmboUsers.setMinWidth(200);
		btnLogin.setMinWidth(200);
		btnLogin.setFont(Font.font(16.0));
		btnLogout.setMinWidth(200);
		btnLogout.setFont(Font.font(16.0));
		lblNotice.setFont(Font.font(18.0));
    	lblNotice.setTextFill(Color.RED);
    	
    	mgmtLoginPane.add(lblUser,2,2);
    	mgmtLoginPane.add(cmboUsers, 3,2);
    	mgmtLoginPane.add(lblPassword,2,3);
    	mgmtLoginPane.add(tfPassword, 3,3);
    	mgmtLoginPane.add(btnLogin,3,4);
    	mgmtLoginPane.add(btnLogout, 3,5);
    	mgmtLoginPane.add(lblNotice,3,6);
    	
    	btnLogin.setOnAction(e->{
			
		});
    	
    	btnLogout.setOnAction(e->{
			
			try {
				new LoginStage();
				mgmtLoginStage.close();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
    	
    	
    	pane.setTop(getTitleHbox());
		pane.setCenter(mgmtLoginPane);
		
		Scene sceneLogin = new Scene(pane);
		//Image logo = new Image("file:images/Reliancelogo.jpg");
		//LoginStage.getIcons().add(logo);
		mgmtLoginStage.setTitle("Project Purple Team");
	
		mgmtLoginStage.setScene(sceneLogin);
		mgmtLoginStage.show();
    	
		
	}
	
	private HBox getTitleHbox() {
		HBox titleHBox = new HBox(200);
		titleHBox.getChildren().add(lblTitle);
		titleHBox.setAlignment(Pos.CENTER);
		return titleHBox;
	}
	
	public static void fillUserComboBox() {
		
		ResultSet rsUsers;
		String qryUsers = "SELECT tblUsers.Username" + 
				" FROM tblUsers;";
		PreparedStatement psUsers;
		try {
			connection = DriverManager.getConnection(MySQLConnection.getPath(),MySQLConnection.getDatabase(),MySQLConnection.getDBPassword());
			psUsers = connection.prepareStatement(qryUsers);
			rsUsers = psUsers.executeQuery();
			//Add "Select" to list first for display and check
			listUsers.add("Select");	
			while (rsUsers.next()) {
				listUsers.add(rsUsers.getString("Username"));
			}//end of while
			psUsers.close();
			rsUsers.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end of Try
 
	}//end of fillMachComboBox

}
