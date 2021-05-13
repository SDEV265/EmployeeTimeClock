import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MgmtMenuStage {
	
	String strUser,strRole;
	//create buttons
	Button btnEmployees = new Button("Employees");
	Button btnTimeApproval = new Button("Time Approval");
	Button btnPayroll = new Button("Payroll");
	Button btnUsers = new Button("Users");
	Button btnClose = new Button("Close");
	Label lblUser = new Label("");
	Label lblRole = new Label("");
	
	//create panes to hold buttons
	BorderPane pane = new BorderPane();
	
	static ObservableList listUsersToo = FXCollections.observableArrayList();
	ArrayList<String> listClockNumbers = new ArrayList<>();

	//create connection
	private Connection connection;
	
	MgmtMenuStage(String newUser, String newRole, ObservableList newUsersList, ArrayList newListClockNumbers) throws ClassNotFoundException, SQLException{
		
		strUser = newUser;
		strRole = newRole;
		//create stage
		Stage mgmtMenuStage = new Stage();
		
		listClockNumbers = newListClockNumbers;
		listUsersToo = newUsersList;
		
		System.out.println("enter Scene getEquipment");
		GridPane mgmtMenuPane = new GridPane();
		mgmtMenuPane.setAlignment(Pos.BASELINE_CENTER);
		mgmtMenuPane.setPadding(new Insets(20.0, 20.0,20.0,20.0));
		mgmtMenuPane.setHgap(20.0);
		mgmtMenuPane.setVgap(20.0);
		
		//set min width of buttons and font size
		btnEmployees.setMinWidth(200);
		btnEmployees.setFont(Font.font(16.0));
		btnTimeApproval.setMinWidth(200);
		btnTimeApproval.setFont(Font.font(16.0));
		btnPayroll.setMinWidth(200);
		btnPayroll.setFont(Font.font(16.0));
		btnUsers.setMinWidth(200);
		btnUsers.setFont(Font.font(16.0));
		btnClose.setMinWidth(200);
		btnClose.setFont(Font.font(16.0));
		
		lblUser.setText(strUser);
		lblRole.setText(strRole);
		
		mgmtMenuPane.add(btnEmployees, 2, 4);
		mgmtMenuPane.add(btnTimeApproval, 2, 5);
		mgmtMenuPane.add(btnPayroll, 2, 6);
		mgmtMenuPane.add(btnUsers, 4, 4);
		mgmtMenuPane.add(btnClose, 8, 8);
		mgmtMenuPane.add(lblUser,4,9);
		mgmtMenuPane.add(lblRole, 5, 9);
		
		btnEmployees.setOnAction(e->{
			try {
				new EmployeesStage(strUser,strRole,listUsersToo, listClockNumbers);
				mgmtMenuStage.close();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		});
		
		btnTimeApproval.setOnAction(e->{
			try {
				new TimeApprovalStage(strUser,strRole, listUsersToo, listClockNumbers);
				mgmtMenuStage.close();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		btnPayroll.setOnAction(e->{
			try {
				new PayrollStage(strUser,strRole, listUsersToo, listClockNumbers);
				mgmtMenuStage.close();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		btnUsers.setOnAction(e->{
			
			try {
				new UsersStage(strUser,strRole,listUsersToo, listClockNumbers);
				mgmtMenuStage.close();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		btnClose.setOnAction(e-> {
			try {
				new LoginStage();
				mgmtMenuStage.close();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		});
		
		pane.setCenter(mgmtMenuPane);
		
		Scene sceneMgmtMenu = new Scene(pane,1200,600);
		mgmtMenuStage.setTitle("Project Purple Team");
	
		mgmtMenuStage.setScene(sceneMgmtMenu);
		mgmtMenuStage.show();
		mgmtMenuStage.setMaximized(true);
	}//end addequipment stage
		
}