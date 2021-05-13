import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class TimeApprovalStage {
	
	String strUser,strRole, strEmployee, strClockNumber, strCurrentDate, strFirstDay, strLastDay;
	Label lblNotice = new Label("");
	Label lblUser = new Label("");
	Label lblRole = new Label("");
	Label lblTitle = new Label("Purple Team Time clock");
	Label lblLogID = new Label("User ID");
	Label lblLogIDField = new Label("");
	Label lblClockNumber = new Label("Clock Number");
	TextField tfClockNumber = new TextField("");
	Label lblDateField = new Label("Date");
	TextField tfDateField = new TextField("");
	Label lblDailyIn = new Label("Time In");
	TextField tfDailyIn = new TextField("");
	Label lblTimeOut = new Label("Time Out");
	TextField tfTimeOut = new TextField("");
	Label lblTimeIn = new Label("Time In");
	TextField tfTimeIn = new TextField("");
	Label lblDailyOut = new Label("Time Out");
	TextField tfDailyOut = new TextField("");
	Label lblNotes = new Label("Notes");
	TextField tfNotes = new TextField("");
	Label lblApproved = new Label("Aprroved");
	Label lblWeek = new Label("");
	ComboBox<String> cmboApproved = new ComboBox<String>();
	
	Button btnBack = new Button("Back");
	Button btnForward = new Button("Forward");
	Button btnAddDate = new Button("Add Date");
	Button btnUpdate = new Button("Update");
	Button btnClose = new Button("Close");
	
	private TableView<Hours> timeApprovalView = new TableView<>();
	
	static ObservableList listUsersToo = FXCollections.observableArrayList();
	ArrayList<String> listClockNumbers = new ArrayList<>();
	
	BorderPane pane = new BorderPane();
	
	private static Connection connection;
	
	TimeApprovalStage(String newUser, String newRole, ObservableList newUsersList, ArrayList newListClockNumbers) throws ClassNotFoundException, SQLException{
		
		strUser = newUser;
		strRole = newRole;
		listClockNumbers = newListClockNumbers;
		listUsersToo = newUsersList;
		
		Stage timeApprovalStage = new Stage();
	
		//getting current date
		LocalDate currentDate = LocalDate.now();
		LocalDate firstDay = currentDate.with(WeekFields.of(Locale.US).getFirstDayOfWeek()).plusDays(-7);
		LocalDate lastDay = firstDay.plusDays(6);
		//formatting Date to string
		strCurrentDate = currentDate.toString();
		strFirstDay = firstDay.toString();
		strLastDay = lastDay.toString();
		System.out.println("first day: "+ strFirstDay+", Last Day:"+strLastDay);
		
		System.out.println("enter Scene getHours");
		GridPane timeApprovalPane = new GridPane();
		timeApprovalPane.setAlignment(Pos.BASELINE_CENTER);
		timeApprovalPane.setPadding(new Insets(20.0, 20.0,20.0,20.0));
		timeApprovalPane.setHgap(20.0);
		timeApprovalPane.setVgap(20.0);
		
		GridPane topPane = new GridPane();
		topPane.setAlignment(Pos.BASELINE_CENTER);
		topPane.setPadding(new Insets(20.0, 20.0,20.0,20.0));
		lblTitle.setFont(Font.font(40.0));
		lblTitle.setTextFill(Color.rgb(51,51,255));
		lblWeek.setFont(Font.font(16.0));
		btnBack.setMinWidth(100);
		btnBack.setMinHeight(100);
		btnForward.setMinWidth(100);
		btnForward.setMinHeight(100);
		lblWeek.setText("Week of "+strCurrentDate);
		
		topPane.add(lblTitle, 4, 1,10,1);
		topPane.add(btnBack, 2,2,2,2);
		topPane.add(lblWeek, 4, 3,1,2);
		topPane.add(btnForward, 6, 2,2,2);
		
		GridPane listPane = new GridPane();
		listPane.setAlignment(Pos.BASELINE_CENTER);
		listPane.setPadding(new Insets(20.0, 20.0,20.0,20.0));
		ListView<String> listEmployees = new ListView<String>(listUsersToo);
		listEmployees.getSelectionModel().select(0);
		listEmployees.setMinWidth(200);
		listPane.add(listEmployees,2,4,1,10);
		strEmployee = listEmployees.getSelectionModel().getSelectedItem();
		int index = listEmployees.getSelectionModel().getSelectedIndex();
		strClockNumber = listClockNumbers.get(index);
		System.out.println("clock number = "+ strClockNumber);
		
		lblNotice.setMinWidth(100);
		lblNotice.setFont(Font.font(16.0));
		lblLogID.setMinWidth(100);
		lblLogID.setFont(Font.font(16.0));
		lblLogIDField.setFont(Font.font(16.0));
		lblClockNumber.setMinWidth(100);
		lblClockNumber.setFont(Font.font(16.0));
		tfClockNumber.setFont(Font.font(16.0));
		lblDateField.setMinWidth(100);
		lblDateField.setFont(Font.font(16.0));
		tfDateField.setFont(Font.font(16.0));
		lblDailyIn.setMinWidth(100);
		lblDailyIn.setFont(Font.font(16.0));
		tfDailyIn.setFont(Font.font(16.0));
		lblTimeOut.setMinWidth(100);
		lblTimeOut.setFont(Font.font(16.0));
		tfTimeOut.setFont(Font.font(16.0));
		lblTimeIn.setMinWidth(100);
		lblTimeIn.setFont(Font.font(16.0));
		tfTimeIn.setFont(Font.font(16.0));
		lblDailyOut.setMinWidth(100);
		lblDailyOut.setFont(Font.font(16.0));
		tfDailyOut.setFont(Font.font(16.0));
		lblNotes.setMinWidth(100);
		lblNotes.setFont(Font.font(16.0));
		tfNotes.setFont(Font.font(16.0));
		lblApproved.setMinWidth(100);
		lblApproved.setFont(Font.font(16.0));
		cmboApproved.setMinWidth(200);
		btnAddDate.setMinWidth(200);
		btnUpdate.setMinWidth(200);
		btnClose.setMinWidth(200);
		
		cmboApproved.getItems().addAll("SELECT","TRUE", "FALSE");
		cmboApproved.getSelectionModel().select(0);
		
		lblUser.setText(strUser);
		lblRole.setText(strRole);
		
		timeApprovalPane.add(lblLogID, 2, 1);
		timeApprovalPane.add(lblLogIDField, 3, 1);
		timeApprovalPane.add(lblDateField, 2, 2);
		timeApprovalPane.add(tfDateField, 3, 2);
		timeApprovalPane.add(lblClockNumber, 2, 3);
		timeApprovalPane.add(tfClockNumber, 3, 3);
		timeApprovalPane.add(lblApproved, 2, 4);
		timeApprovalPane.add(cmboApproved, 3, 4);
		timeApprovalPane.add(lblNotes, 2, 5);
		timeApprovalPane.add(tfNotes, 3, 5, 4, 1);
		timeApprovalPane.add(lblDailyIn, 4, 1);
		timeApprovalPane.add(tfDailyIn, 5, 1);
		timeApprovalPane.add(lblTimeOut, 4, 2);
		timeApprovalPane.add(tfTimeOut, 5, 2);
		timeApprovalPane.add(lblTimeIn, 4, 3);
		timeApprovalPane.add(tfTimeIn, 5, 3);
		timeApprovalPane.add(lblDailyOut, 4, 4);
		timeApprovalPane.add(tfDailyOut, 5, 4);
		timeApprovalPane.add(btnAddDate, 7,2);
		timeApprovalPane.add(btnUpdate,7,3);
		timeApprovalPane.add(btnClose, 7, 5);
		timeApprovalPane.add(lblUser, 3, 6);
		timeApprovalPane.add(lblRole, 5, 6);
		
		timeApprovalData();
		
		//checking for mouse click of tableview
		timeApprovalView.setOnMouseClicked(e->{
		Hours rowValue = timeApprovalView.getSelectionModel().getSelectedItem();
		String strLogID = rowValue.getLogId();
		String strDateField = rowValue.getDatefield();
		String strClockNumber = rowValue.getClockNumber();
		String strApproved = rowValue.getApproved();
		String strNotes = rowValue.getNotes();
		String strDailyIn = rowValue.getDailyIn();
		String strTimeIn = rowValue.getTimeIn();
		String strTimeOut = rowValue.getTimeOut();
		String strDailyOut = rowValue.getDailyOut();
				
		lblLogIDField.setText(strLogID);
		tfDateField.setText(strDateField);
		tfClockNumber.setText(strClockNumber);
		cmboApproved.setValue(strApproved);
		tfNotes.setText(strNotes);
		tfDailyIn.setText(strDailyIn);
		tfTimeOut.setText(strTimeOut);
		tfTimeIn.setText(strTimeIn);
		tfDailyOut.setText(strDailyOut);
		});
		
		VBox timeApprovalVBox = new VBox();
		timeApprovalVBox.setPadding(new Insets(15,15,15,15));
		timeApprovalVBox.getChildren().add(timeApprovalView);
		timeApprovalVBox.setPrefHeight(800);
		timeApprovalVBox.setPrefWidth(400);
		
		listEmployees.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)->{
			strEmployee = listEmployees.getSelectionModel().getSelectedItem();
			int i = listEmployees.getSelectionModel().getSelectedIndex();
			strClockNumber = listClockNumbers.get(i);
			try {
				updateData(strClockNumber,strFirstDay,strLastDay);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});//end of comboMach listener
		
		btnBack.setOnAction(e-> {
			LocalDate newDate =  LocalDate.parse(strCurrentDate).plusDays(-7);
			LocalDate newFirstDay = newDate.with(WeekFields.of(Locale.US).getFirstDayOfWeek()).plusDays(-7);
			LocalDate newLastDay = newFirstDay.plusDays(6);
			strCurrentDate = newDate.toString();
			lblWeek.setText("Week of "+strCurrentDate);
			String strNewFirstDay = newFirstDay.toString();
			String strNewLastDay = newLastDay.toString();
			System.out.println("first day: "+ strNewFirstDay+", Last Day:"+strNewLastDay);
			try {
				updateData(strClockNumber, strNewFirstDay,strNewLastDay);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		btnForward.setOnAction(e-> {
			LocalDate newDate =  LocalDate.parse(strCurrentDate).plusDays(7);
			LocalDate newFirstDay = newDate.with(WeekFields.of(Locale.US).getFirstDayOfWeek()).plusDays(-7);
			LocalDate newLastDay = newFirstDay.plusDays(6);
			strCurrentDate = newDate.toString();
			lblWeek.setText("Week of "+strCurrentDate);
			String strNewFirstDay = newFirstDay.toString();
			String strNewLastDay = newLastDay.toString();
			try {
				updateData(strClockNumber,strNewFirstDay,strNewLastDay);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		btnUpdate.setOnAction(e-> {
			
			//checking required field values
			if(lblLogIDField.getText().isEmpty() || tfClockNumber.getText().isEmpty() || cmboApproved.getValue().equals("SELECT")) {
				lblNotice.setText("*Data required!");
				lblNotice.setFont(Font.font(18.0));
				lblNotice.setTextFill(Color.RED);
				
				
			}//end if strInput true and value empty
			else {
				lblNotice.setText("");
			
	        try {
	        	String updateTimeLog = "UPDATE tblTimeLog SET tblTimeLog.Datefield = '"+ tfDateField.getText() +
	        			"', tblTimeLog.ClockNumber = '"+ tfClockNumber.getText() +
	        			"',tblTimeLog.Approved = "+cmboApproved.getValue()+
	        			",tblTimeLog.TimeIn = '"+tfTimeIn.getText()+
	        			"',tblTimeLog.DailyOut = '"+tfDailyOut.getText()+
	        			"',tblTimeLog.DailyIn = '"+tfDailyIn.getText()+
	        			"',tblTimeLog.TimeOut = '"+tfTimeOut.getText()+
	        			"',tblTimeLog.Notes = '"+tfNotes.getText()+
	        			"' WHERE tblTimeLog.LogID = "+ lblLogIDField.getText()+";";
	        	System.out.println(updateTimeLog);
	        	PreparedStatement preparedStatement = connection.prepareStatement(updateTimeLog);
	           preparedStatement.executeUpdate();

	        }//end of try for update query 
	        catch (SQLException ex) {
	            ex.printStackTrace();
	        }//end of catch for update query
	        
	        //return field values to empty state
	        lblLogIDField.setText("");
	        tfClockNumber.setText("");
	        tfDateField.setText("");
			cmboApproved.getSelectionModel().select(0);
			tfDailyIn.setText("");
			tfTimeOut.setText("");
			tfTimeIn.setText("");
			tfDailyOut.setText("");
			tfNotes.setText("");
			
			try {
				new TimeApprovalStage(strUser,strRole,listUsersToo, listClockNumbers);
				timeApprovalStage.close();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			}//end if else
		});//end btnUpdate
		
		btnAddDate.setOnAction(e-> {
			
				try {
					String qryInsertDate = "INSERT into tbltimelog (clocknumber,datefield)"+
						"Values ('"+ strClockNumber +
						"','"+ strCurrentDate +
						"');";
					PreparedStatement preparedStatement = connection.prepareStatement(qryInsertDate);
					preparedStatement.executeUpdate();
				}//end of try for update query 
				catch (SQLException ex) {
					ex.printStackTrace();
				}//end of catch for update query
			
			try {
				new TimeApprovalStage(strUser,strRole,listUsersToo, listClockNumbers);
				timeApprovalStage.close();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}//end of try catch
			
		});
		
		btnClose.setOnAction(e-> {
			try {
				new MgmtMenuStage(strUser,strRole, listUsersToo, listClockNumbers);
				timeApprovalStage.close();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		});
		pane.setTop(topPane);
		pane.setLeft(listPane);
		pane.setCenter(timeApprovalVBox);
		pane.setBottom(timeApprovalPane);
		
		Scene sceneTimeApproval = new Scene(pane,1200,600);
		Image logo = new Image("file:images/Reliancelogo.jpg");
		timeApprovalStage.getIcons().add(logo);
		timeApprovalStage.setTitle("Project Purple Team");
	
		timeApprovalStage.setScene(sceneTimeApproval);
		timeApprovalStage.show();
		timeApprovalStage.setMaximized(true);
	}
	
	public void timeApprovalData() throws ClassNotFoundException, SQLException {
    	
    	String select = "SELECT * FROM tblTimeLog "+
    			"WHERE tblTimelog.Datefield >= '"+strFirstDay+"' AND tblTimelog.DateField <='"+strLastDay+"' AND tblTimelog.clocknumber = '"+strClockNumber+"';";
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
  
        ObservableList dbData = FXCollections.observableArrayList(timeApprovalArrayList(resultSet));
        
        timeApprovalView.setEditable(true);
        TableColumn <Hours,String> LogId = new TableColumn("LogId");
        TableColumn <Hours,String> Datefield = new TableColumn("Date");
        TableColumn <Hours,String> ClockNumber = new TableColumn("ClockNumber");
    	TableColumn <Hours,String> DailyIn = new TableColumn("Time In");
    	TableColumn <Hours,String> TimeOut = new TableColumn("Time Out");
    	TableColumn <Hours,String> TimeIn = new TableColumn("Time In");
    	TableColumn <Hours,String> DailyOut = new TableColumn("Time Out");
    	TableColumn <Hours,String> Notes = new TableColumn("Notes");
    	TableColumn <Hours,String> Approved = new TableColumn("Approved");
    	TableColumn <Hours,String> TotalHours = new TableColumn("Hours");
       
        //Setting cell property value to correct variable from Hours class
    	LogId.setCellValueFactory(new PropertyValueFactory<Hours,String>("LogId"));
    	Datefield.setCellValueFactory(new PropertyValueFactory<Hours,String>("Datefield"));
    	ClockNumber.setCellValueFactory(new PropertyValueFactory<Hours,String>("ClockNumber"));
    	DailyIn.setCellValueFactory(new PropertyValueFactory<Hours,String>("DailyIn"));
        TimeOut.setCellValueFactory(new PropertyValueFactory<Hours,String>("TimeOut"));
        TimeIn.setCellValueFactory(new PropertyValueFactory<Hours,String>("TimeIn"));
        DailyOut.setCellValueFactory(new PropertyValueFactory<Hours,String>("DailyOut"));
        Notes.setCellValueFactory(new PropertyValueFactory<Hours,String>("Notes"));
        Approved.setCellValueFactory(new PropertyValueFactory<Hours,String>("Approved"));
        TotalHours.setCellValueFactory(new PropertyValueFactory<Hours,String>("TotalHours"));
        
        //set cells to textfields for tableview edit
        //one edits view not actual table
        Datefield.setCellFactory(TextFieldTableCell.forTableColumn());
        ClockNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        DailyIn.setCellFactory(TextFieldTableCell.forTableColumn());
        TimeOut.setCellFactory(TextFieldTableCell.forTableColumn());
        TimeIn.setCellFactory(TextFieldTableCell.forTableColumn());
        DailyOut.setCellFactory(TextFieldTableCell.forTableColumn());
        Notes.setCellFactory(TextFieldTableCell.forTableColumn());
        Approved.setCellFactory(ComboBoxTableCell.forTableColumn("TRUE","FALSE"));
        TotalHours.setCellFactory(TextFieldTableCell.forTableColumn());
        //Inactive.setCellFactory(ComboBoxTableCell.forTableColumn("True","False"));
        
        //create eventhandlers for each cell change        
        DailyIn.setOnEditCommit(new EventHandler<CellEditEvent<Hours, String>>(){
        	@Override
        	public void handle(CellEditEvent<Hours, String> event) {
        		try {
        			Hours hours = event.getRowValue();
        			hours.setDailyIn(event.getNewValue());
        			tfDailyIn.setText(event.getNewValue());
        			
    	        	String updateHours = "UPDATE tbltimelog SET tblTimeLog.DailyIn = '"+ tfDailyIn.getText() +
    	        			"' WHERE tblTimeLog.LogId = "+ lblLogIDField.getText()+";";
    	        	System.out.println(updateHours);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateHours);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query	
        	}//end handle event
        });//end DailyIn setOnEditCommit event handler
        
        TimeOut.setOnEditCommit(new EventHandler<CellEditEvent<Hours, String>>(){
        	@Override
        	public void handle(CellEditEvent<Hours, String> event) {
        		try {
        			Hours hours = event.getRowValue();
        			hours.setTimeOut(event.getNewValue());
        			tfTimeOut.setText(event.getNewValue());
        			
    	        	String updateHours = "UPDATE tbltimelog SET tblTimeLog.TimeOut = '"+ tfTimeOut.getText() +
    	        			"', totalhours = Hour(subtime('"+tfTimeOut.getText()+"',tbltimelog.dailyIn))+(Minute(subtime('"+tfTimeOut.getText()+"',tbltimelog.dailyIn)))/60" +
    	        			"' WHERE tblTimeLog.LogId = "+ lblLogIDField.getText()+";";
    	        	System.out.println(updateHours);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateHours);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query	
        	}//end handle event
        });//end TimeOut setOnEditCommit event handler
        
        TimeIn.setOnEditCommit(new EventHandler<CellEditEvent<Hours, String>>(){
        	@Override
        	public void handle(CellEditEvent<Hours, String> event) {
        		try {
        			Hours hours = event.getRowValue();
        			hours.setTimeIn(event.getNewValue());
        			tfTimeIn.setText(event.getNewValue());
        			
    	        	String updateHours = "UPDATE tbltimelog SET tblTimeLog.TimeIn = '"+ tfTimeIn.getText() +
    	        			"' WHERE tblTimeLog.LogId = "+ lblLogIDField.getText()+";";
    	        	System.out.println(updateHours);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateHours);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query	
        	}//end handle event
        });//end TimeIn setOnEditCommit event handler
        
        DailyOut.setOnEditCommit(new EventHandler<CellEditEvent<Hours, String>>(){
        	@Override
        	public void handle(CellEditEvent<Hours, String> event) {
        		try {
        			Hours hours = event.getRowValue();
        			hours.setDailyOut(event.getNewValue());
        			tfDailyOut.setText(event.getNewValue());
        			
    	        	String updateHours = "UPDATE tbltimelog SET tblTimeLog.DailyOut = '"+ tfDailyOut.getText() +
    	        			"', totalhours = Hour(subtime((subtime('"+tfDailyOut.getText()+"',dailyIn)),(subtime(timeIn,timeOut))))+(Minute(subtime((subtime('"+tfDailyOut.getText()+"',dailyIn)),(subtime(timeIn,timeOut)))))/60"+
    	        			"' WHERE tblTimeLog.LogId = "+ lblLogIDField.getText()+";";
    	        	System.out.println(updateHours);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateHours);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query	
        	}//end handle event
        });//end DailOut setOnEditCommit event handler
        
        Notes.setOnEditCommit(new EventHandler<CellEditEvent<Hours, String>>(){
        	@Override
        	public void handle(CellEditEvent<Hours, String> event) {
        		try {
        			Hours hours = event.getRowValue();
        			hours.setNotes(event.getNewValue());
        			tfNotes.setText(event.getNewValue());
        			
    	        	String updateHours = "UPDATE tbltimelog SET tblTimeLog.Notes = '"+ tfNotes.getText() +
    	        			"' WHERE tblTimeLog.LogId = "+ lblLogIDField.getText()+";";
    	        	System.out.println(updateHours);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateHours);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query
        		 //return field values to empty state
    	        lblLogIDField.setText("");
    	        tfClockNumber.setText("");
    	        tfDateField.setText("");
    			cmboApproved.getSelectionModel().select(0);
    			tfDailyIn.setText("");
    			tfTimeOut.setText("");
    			tfTimeIn.setText("");
    			tfDailyOut.setText("");
    			tfNotes.setText("");
        	}//end handle event
        	
        });//end Notes setOnEditCommit event handler
        
     
       
        timeApprovalView.getColumns().addAll(LogId,ClockNumber,Datefield,DailyIn,TimeOut,TimeIn, DailyOut,TotalHours,Approved,Notes);

        //Filling up tableView with data
        timeApprovalView.setItems(dbData);
        timeApprovalView.getSelectionModel().getSelectedIndex();//setCellSelectionEnabled(true);
        int index = timeApprovalView.getSelectionModel().getSelectedIndex();
        System.out.println(index);   
         
    }//end revisionData

	public void updateData(String strNewClockNumber,String newFirstDay, String newLastDay) throws ClassNotFoundException, SQLException {
		
		strClockNumber = strNewClockNumber;
		strFirstDay = newFirstDay;
		strLastDay = newLastDay;
		String select = "SELECT * FROM tblTimeLog "+
    			"WHERE tblTimelog.Datefield >= '"+strFirstDay+"' AND tblTimelog.DateField <='"+strLastDay+"' AND tblTimelog.clocknumber = '"+strClockNumber+"';";
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
		
		ObservableList dbData = FXCollections.observableArrayList(timeApprovalArrayList(resultSet));
        
        timeApprovalView.setEditable(true);

	        //Filling up tableView with data
	        timeApprovalView.setItems(dbData);
	        timeApprovalView.getSelectionModel().getSelectedIndex();//setCellSelectionEnabled(true);
	        int index = timeApprovalView.getSelectionModel().getSelectedIndex();
	        System.out.println(index);   
	         
	   }//end updateData

//extracting data from ResulSet to ArrayList
	private ArrayList timeApprovalArrayList(ResultSet resultSet) throws SQLException {
		ArrayList<Hours> data =  new ArrayList<>();
		while (resultSet.next()) {
			Hours hours = new Hours();
			hours.LogId.set(resultSet.getString("LogId"));
			hours.Datefield.set(resultSet.getString("Datefield"));
			hours.ClockNumber.set(resultSet.getString("ClockNumber"));
			hours.DailyIn.set(resultSet.getString("DailyIn"));
			hours.TimeOut.set(resultSet.getString("TimeOut"));
			hours.TimeIn.set(resultSet.getString("TimeIn"));
			hours.DailyOut.set(resultSet.getString("DailyOut"));
			hours.Notes.set(resultSet.getString("Notes"));
			String approved = resultSet.getString("Approved");
			if(approved.contentEquals("0")) {
				approved = "FALSE";
			}else {
				approved = "TRUE";
			}
			hours.Approved.set(approved);
			hours.TotalHours.set(resultSet.getString("TotalHours"));
		
			data.add(hours);
		}
		return data;
	}//end revisionsArrayList
}
