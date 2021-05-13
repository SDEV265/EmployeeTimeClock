import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginStage {
	
	Scene login;
	String strUser, strUserID, strFirstName, strLastName, strRole, strPassword, strClockNumber, strChkPass, strLogId, strTimeout, strTimein, strDailyout, strDatefield, strFullcard;
	String strUsername[];
	Label lblTitle = new Label("Purple Team Time clock");
	Label lblUser = new Label("Username: ");//Username label
	Label lblClockNumber = new Label("Clock Number: ");//Password label
	TextField tfClockNumber = new TextField();//Password textbox
	Button btnLogTime = new Button("Log Time");
	Button btnMgmtLogin = new Button("Management login");
	Button btnExit = new Button("Exit");
	Label lblNotice = new Label("");
	
	private static Connection connection;
	
	final ObservableList listUsers = FXCollections.observableArrayList();
	final ObservableList listUsersToo = FXCollections.observableArrayList();
	ArrayList<String> listClockNumbers = new ArrayList<>();
	ListView<String> listEmployees = new ListView<String>(listUsers);
	
	BorderPane pane = new BorderPane();
	
	LoginStage() throws ClassNotFoundException, SQLException{
		
		Stage loginStage = new Stage();
		
		//getting current date
		LocalDate strCurrentDate = LocalDate.now();
		//formatting Date to string
		//String strCurrentDate = currentDate.toString();
		
		fillUsersComboBox();
		
		System.out.println("enter Scene login");
		GridPane loginPane = new GridPane();
		loginPane.setAlignment(Pos.BASELINE_CENTER);
		loginPane.setPadding(new Insets(20.0, 20.0,20.0,20.0));
		loginPane.setHgap(20.0);
		loginPane.setVgap(20.0);
		
		//Place nodes in login pane
		lblTitle.setFont(Font.font(40.0));
		lblTitle.setTextFill(Color.rgb(51,51,255));
		lblUser.setFont(Font.font(16.0));
		listEmployees.getSelectionModel().select(0);
		listEmployees.setMinWidth(300);
		lblClockNumber.setFont(Font.font(16.0));
		tfClockNumber.setFont(Font.font(16.0));
		tfClockNumber.setMinWidth(300);
		btnLogTime.setMinWidth(300);
		btnLogTime.setFont(Font.font(16.0));
		btnMgmtLogin.setMinWidth(300);
		btnMgmtLogin.setFont(Font.font(16.0));
		btnExit.setMinWidth(300);
		btnExit.setFont(Font.font(16.0));
		lblNotice.setFont(Font.font(18.0));
    	lblNotice.setTextFill(Color.RED);
    	
		loginPane.add(lblUser,1,2);
		loginPane.add(listEmployees,2,2,1,6);
		loginPane.add(lblClockNumber,1,9);
		loginPane.add(tfClockNumber,2,9);
		loginPane.add(lblNotice, 1, 10,2,1);
		loginPane.add(btnLogTime, 2, 11);
		loginPane.add(btnMgmtLogin, 2, 12);
		loginPane.add(btnExit, 2,13);;//column,row,num of columns, num of rows
		
		btnLogTime.setOnAction(e-> {
			strUser = listEmployees.getSelectionModel().getSelectedItem();
			if(strUser==null) {
				lblNotice.setText("Select your name.");
			}else {
				strUsername = strUser.split(",");
				strFirstName = strUsername[1];
				strLastName = strUsername[0];
				strClockNumber = tfClockNumber.getText();
				if(tfClockNumber.getText().isEmpty()) {
					lblNotice.setText("Enter a clock number!");
				}else {
					//password query
					String strVerify = "SELECT tblEmployees.clocknumber FROM tblemployees WHERE (((tblemployees.firstname) = '" + strFirstName +"') and (tblEmployees.lastname) = '"+strLastName+"');";
					//Extracting password from database
					ResultSet rsPassword;
					try {
						PreparedStatement preparedStatement = connection.prepareStatement(strVerify);
						rsPassword = preparedStatement.executeQuery();
						while (rsPassword.next()) {
							strChkPass = rsPassword.getString("clocknumber");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if((!strClockNumber.equals(strChkPass))){
						lblNotice.setText("Invalid Name or clock number.");
					}else if(strClockNumber.equals(strChkPass)) {
						lblNotice.setText("Login was successful!");
						LocalTime strCurrentTime = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
	        	
						String strCheckLog = "SELECT tbltimelog.logid, tbltimelog.clocknumber, tbltimelog.dailyin, tbltimelog.timeout, tbltimelog.timein, tbltimelog.dailyout, tbltimelog.datefield, tbltimelog.fullcard"+
							" FROM tbltimelog WHERE (((tbltimelog.clocknumber) = '" + strClockNumber +"') and ((tbltimelog.datefield) = '"+strCurrentDate+"'));";
						//Extracting password from database
						ResultSet rsCheckLog;
						try {
							PreparedStatement preparedStatement = connection.prepareStatement(strCheckLog);
							rsCheckLog = preparedStatement.executeQuery();
							while (rsCheckLog.next()) {
								strLogId = rsCheckLog.getString("logid");
								strDatefield = rsCheckLog.getString("datefield");
								strTimeout = rsCheckLog.getString("timeout");
								strTimein = rsCheckLog.getString("timein");
								strDailyout = rsCheckLog.getString("dailyout");
								strFullcard = rsCheckLog.getString("fullcard");
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						if((strLogId == null) || (strFullcard.equals("1"))) {
							//insert initial punch timepunch for the day
							try {
								String qryInsertTime = "INSERT into tbltimelog (clocknumber,dailyin,datefield)"+
									"Values ('"+ tfClockNumber.getText() +
									"','"+ strCurrentTime +
									"','"+ strCurrentDate +
									"');";
								PreparedStatement preparedStatement = connection.prepareStatement(qryInsertTime);
								preparedStatement.executeUpdate();
							}//end of try for update query 
							catch (SQLException ex) {
								ex.printStackTrace();
							}//end of catch for update query
						}//end if logId is empty
						else if(strTimeout == null) {
							//insert timeout timepunch
							try {
								String qryUpdateTimeout = "Update tbltimelog set timeout ='"+ strCurrentTime+
									"', totalhours = Hour(subtime('"+strCurrentTime+"',tbltimelog.dailyIn))+(Minute(subtime('"+strCurrentTime+"',tbltimelog.dailyIn)))/60" +
									" WHERE (((clocknumber) ="+strClockNumber+") and ((datefield) ='"+strDatefield+"') and ((fullcard) = False));";
								System.out.println(qryUpdateTimeout);
								PreparedStatement preparedStatement = connection.prepareStatement(qryUpdateTimeout);
								preparedStatement.executeUpdate();
							}//end of try for update query 
							catch (SQLException ex) {
								ex.printStackTrace();
							}//end of catch for update query	
						}//end else if strTimeout is null
						else if(strTimein ==null) {
							//insert timein timepunch
							try {
								String qryUpdateTimein = "Update tbltimelog set timein ='"+ strCurrentTime+
									"' WHERE (((clocknumber) ="+strClockNumber+") and ((datefield) ='"+strDatefield+"') and ((fullcard) = False));";
								PreparedStatement preparedStatement = connection.prepareStatement(qryUpdateTimein);
								preparedStatement.executeUpdate();
							}//end of try for update query 
							catch (SQLException ex) {
								ex.printStackTrace();
							}//end of catch for update query
						}//end else if strTimein is null.
						else if(strDailyout == null) {
							//insert dailyout timepunch
							try {
								String qryUpdateDailyout = "Update tbltimelog set dailyout ='"+ strCurrentTime+
										"', totalhours = Hour(subtime((subtime('"+strCurrentTime+"',dailyIn)),(subtime(timeIn,timeOut))))+(Minute(subtime((subtime('"+strCurrentTime+"',dailyIn)),(subtime(timeIn,timeOut)))))/60"+
										" fullcard ="+true+
									" WHERE (((clocknumber) ="+strClockNumber+") and ((datefield) ='"+strDatefield+"'));";
								PreparedStatement preparedStatement = connection.prepareStatement(qryUpdateDailyout);
								preparedStatement.executeUpdate();
							}//end of try for update query 
							catch (SQLException ex) {
								ex.printStackTrace();
							}//end of catch for update query
						}//end else if strDailyout is null
					}//end if clocknumber
				}//end if clocknumber is empty
			}//end if username is empty
	        tfClockNumber.setText("");
		});//end of btnLogTime
		
		btnMgmtLogin.setOnAction(e->{
				
			
			try {
				new MgmtLoginStage(listUsersToo, listClockNumbers);
				loginStage.close();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		btnExit.setOnAction(e -> {
			stop();
			System.exit(0);
		});//end of btnExit
		
		pane.setTop(getMCAHbox());
		pane.setCenter(loginPane);
		
		Scene sceneLogin = new Scene(pane,1200,600);
		//Image logo = new Image("file:images/Reliancelogo.jpg");
		//LoginStage.getIcons().add(logo);
		loginStage.setTitle("Project Purple Team");
	
		loginStage.setScene(sceneLogin);
		loginStage.show();
		loginStage.setMaximized(true);
	}
	
	private HBox getMCAHbox() {
		HBox MCAHBox = new HBox(200);
		MCAHBox.getChildren().add(lblTitle);
		MCAHBox.setAlignment(Pos.CENTER);
		return MCAHBox;
	}
	
	public void fillUsersComboBox() {
		
		ResultSet rsUsers;
		String qryUsers = "SELECT tblemployees.clocknumber,tblemployees.lastname, tblemployees.firstname" + 
				" FROM tblemployees;";
		System.out.println(qryUsers);
		PreparedStatement psUsers;
		try {
			connection = DriverManager.getConnection(MySQLConnection.getPath(),MySQLConnection.getDatabase(),MySQLConnection.getDBPassword());
			psUsers = connection.prepareStatement(qryUsers);
			rsUsers = psUsers.executeQuery();
			listUsers.add("Select");
			while (rsUsers.next()) {
				listClockNumbers.add(rsUsers.getString("ClockNumber"));
				listUsers.add(rsUsers.getString("lastname")+","+rsUsers.getString("firstname"));
				listUsersToo.add(rsUsers.getString("lastname")+","+rsUsers.getString("firstname"));
			}//end of while
			psUsers.close();
			rsUsers.close();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end of Try
	     
	}//end of fillUsersComboBox
	
	//Close database connection
	public void stop() {
		try {
			connection = DriverManager.getConnection(MySQLConnection.getPath(),MySQLConnection.getDatabase(),MySQLConnection.getDBPassword());
			connection.close();
			System.out.println("TimeClock Access connection closed.");
		}//end connection close try
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}//end stop

}
