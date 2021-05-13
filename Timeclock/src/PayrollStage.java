import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class PayrollStage {
	
	String strUser,strRole, strEmployee, strClockNumber, strCurrentDate, strFirstDay, strLastDay;
	Label lblNotice = new Label("");
	Label lblUser = new Label("");
	Label lblRole = new Label("");
	Label lblTitle = new Label("Purple Team Time clock");
	Label lblWeek = new Label("");
	
	Button btnBack = new Button("Back");
	Button btnForward = new Button("Forward");
	Button btnClose = new Button("Close");
	
	private TableView<Payroll> payrollView = new TableView<>();
	
	static ObservableList listUsersToo = FXCollections.observableArrayList();
	ArrayList<String> listClockNumbers = new ArrayList<>();
	ArrayList<String> alClockNumbers = new ArrayList<>();
	ArrayList<String> alHours = new ArrayList<>();
	ArrayList<String> alDependants = new ArrayList<>();
	ArrayList<String> alStateRate = new ArrayList<>();
	ArrayList<String> alCountyCode = new ArrayList<>();
	ArrayList<String> alCountyRate = new ArrayList<>();
	
	BorderPane pane = new BorderPane();
	
	private static Connection connection;
	
	PayrollStage(String newUser, String newRole, ObservableList newUsersList, ArrayList newListClockNumbers) throws ClassNotFoundException, SQLException{
		
		strUser = newUser;
		strRole = newRole;
		listClockNumbers = newListClockNumbers;
		listUsersToo = newUsersList;
		
		Stage payrollStage = new Stage();
	
		//getting current date
		LocalDate currentDate = LocalDate.now();
		LocalDate firstDay = currentDate.with(WeekFields.of(Locale.US).getFirstDayOfWeek()).plusDays(-7);
		LocalDate lastDay = firstDay.plusDays(6);
		//formatting Date to string
		strCurrentDate = currentDate.toString();
		strFirstDay = firstDay.toString();
		strLastDay = lastDay.toString();
        
		GridPane payrollPane = new GridPane();
		payrollPane.setAlignment(Pos.BASELINE_CENTER);
		payrollPane.setPadding(new Insets(20.0, 20.0,20.0,20.0));
		payrollPane.setHgap(20.0);
		payrollPane.setVgap(20.0);
		
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
		
		btnClose.setMinWidth(200);
		
		lblUser.setText(strUser);
		lblRole.setText(strRole);
		
		payrollPane.add(btnClose, 7, 5);
		payrollPane.add(lblUser, 3, 6);
		payrollPane.add(lblRole, 5, 6);
		
		payrollData();
		
		VBox payrollVBox = new VBox();
		payrollVBox.setPadding(new Insets(15,15,15,15));
		payrollVBox.getChildren().add(payrollView);
		payrollVBox.setPrefHeight(800);
		payrollVBox.setPrefWidth(400);
		
		btnBack.setOnAction(e-> {
			LocalDate newDate =  LocalDate.parse(strCurrentDate).plusDays(-7);
			LocalDate newFirstDay = newDate.with(WeekFields.of(Locale.US).getFirstDayOfWeek()).plusDays(-7);
			LocalDate newLastDay = newFirstDay.plusDays(6);
			strCurrentDate = newDate.toString();
			lblWeek.setText("Week of "+strCurrentDate);
			String strNewFirstDay = newFirstDay.toString();
			String strNewLastDay = newLastDay.toString();
			try {
				updatePayrollData(strNewFirstDay,strNewLastDay);
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
				updatePayrollData(strNewFirstDay,strNewLastDay);
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
				new MgmtMenuStage(strUser,strRole, listUsersToo, listClockNumbers);
				payrollStage.close();
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
		pane.setCenter(payrollVBox);
		pane.setBottom(payrollPane);
		
		Scene scenePayroll = new Scene(pane);
		payrollStage.setTitle("Project Purple Team");
		payrollStage.setScene(scenePayroll);
		payrollStage.show();
		payrollStage.setMaximized(true);
		
	}
	
	public void payrollData() throws ClassNotFoundException, SQLException {
		
		String selectStateTax = "select Dependants, Weekly" + 
				" from tblStateTax;";

        //Extracting data from Database
        ResultSet resultStateTax = null;
        try {
        	connection = DriverManager.getConnection(MySQLConnection.getPath(),MySQLConnection.getDatabase(),MySQLConnection.getDBPassword());
            PreparedStatement preparedStatement = connection.prepareStatement(selectStateTax);
            resultStateTax = preparedStatement.executeQuery();
            System.out.println(resultStateTax);
            while (resultStateTax.next()) {
            	alDependants.add(resultStateTax.getString("Dependants"));
            	alStateRate.add(resultStateTax.getString("Weekly"));
            }//end while resultset.next
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String selectCountyTax = "select CountyCode, TaxRate" + 
				" from tblCountyTax;";

        //Extracting data from Database
        ResultSet resultCountyTax = null;
        try {
        	connection = DriverManager.getConnection(MySQLConnection.getPath(),MySQLConnection.getDatabase(),MySQLConnection.getDBPassword());
            PreparedStatement preparedStatement = connection.prepareStatement(selectStateTax);
            resultCountyTax = preparedStatement.executeQuery();
            System.out.println(resultCountyTax);
            while (resultCountyTax.next()) {
            	alCountyCode.add(resultStateTax.getString("CountyCode"));
            	alCountyRate.add(resultStateTax.getString("TaxRate"));
            	System.out.println("CountyCode ="+ resultStateTax.getString("CountyCode")+",County Tax Rate"+resultStateTax.getString("TaxRate"));
            }//end while resultset.next
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		String selectHours = "select Clocknumber, Sum(totalhours) AS TotalHours" + 
				" from tbltimelog" + 
				" Where datefield between '"+strFirstDay+"' AND '" + strLastDay +
				"' Group by Clocknumber;";
    	System.out.println(selectHours);

        //Extracting data from Database
        ResultSet resultHours = null;
        try {
        	connection = DriverManager.getConnection(MySQLConnection.getPath(),MySQLConnection.getDatabase(),MySQLConnection.getDBPassword());
            PreparedStatement preparedStatement = connection.prepareStatement(selectHours);
            resultHours = preparedStatement.executeQuery();
            System.out.println(resultHours);
            while (resultHours.next()) {
            	alClockNumbers.add(resultHours.getString("Clocknumber"));
            	alHours.add(resultHours.getString("TotalHours"));
            	System.out.println(resultHours.getString("ClockNumber")+","+resultHours.getString("TotalHours"));
            }//end while resultset.next
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	
    	String select = "SELECT * FROM tblEmployees;";
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
  
        ObservableList dbData = FXCollections.observableArrayList(payrollArrayList(resultSet));
        
        payrollView.setEditable(true);
        TableColumn <Payroll,String> ClockNumber = new TableColumn("Clock Number");
        TableColumn <Payroll,String> Lastname = new TableColumn("Last Name");
        TableColumn <Payroll,String> Firstname = new TableColumn("First Name");
    	TableColumn <Payroll,String> StreetAddress = new TableColumn("Street Address");
    	TableColumn <Payroll,String> City = new TableColumn("City");
    	TableColumn <Payroll,String> State = new TableColumn("State");
    	TableColumn <Payroll,String> Zipcode = new TableColumn("Zipcode");
    	TableColumn <Payroll,String> Dependants = new TableColumn("Dependants");
    	TableColumn <Payroll,String> Hours = new TableColumn("Hours");
    	TableColumn <Payroll,String> PayRate = new TableColumn("Pay Rate");
    	TableColumn <Payroll,String> OTHours = new TableColumn("OT Hours");
    	TableColumn <Payroll,String> OTPayRate = new TableColumn("OT Pay Rate");
    	TableColumn <Payroll,String> GrossPay = new TableColumn("Gross Pay");
    	TableColumn <Payroll,String> HealthIns = new TableColumn("Health Ins.");
    	TableColumn <Payroll,String> VisionIns = new TableColumn("Vision Ins.");
    	TableColumn <Payroll,String> DentalIns = new TableColumn("Dental Ins.");
    	TableColumn <Payroll,String> LifeIns = new TableColumn("Life Ins.");
    	TableColumn <Payroll,String> CountyTax = new TableColumn("Tax County");
    	TableColumn <Payroll,String> StateTax = new TableColumn("Tax State");
    	TableColumn <Payroll,String> NetPay = new TableColumn("Net Pay");
       
        //Setting cell property value to correct variable from payroll class
    	ClockNumber.setCellValueFactory(new PropertyValueFactory<Payroll,String>("ClockNumber"));
    	Lastname.setCellValueFactory(new PropertyValueFactory<Payroll,String>("Lastname"));
    	Firstname.setCellValueFactory(new PropertyValueFactory<Payroll,String>("Firstname"));
        StreetAddress.setCellValueFactory(new PropertyValueFactory<Payroll,String>("StreetAddress"));
        City.setCellValueFactory(new PropertyValueFactory<Payroll,String>("City"));
        State.setCellValueFactory(new PropertyValueFactory<Payroll,String>("State"));
        Zipcode.setCellValueFactory(new PropertyValueFactory<Payroll,String>("Zipcode"));
        Dependants.setCellValueFactory(new PropertyValueFactory<Payroll,String>("Dependants"));
        Hours.setCellValueFactory(new PropertyValueFactory<Payroll,String>("Hours"));
        PayRate.setCellValueFactory(new PropertyValueFactory<Payroll,String>("PayRate"));
        OTHours.setCellValueFactory(new PropertyValueFactory<Payroll,String>("OTHours"));
        OTPayRate.setCellValueFactory(new PropertyValueFactory<Payroll,String>("OTPayRate"));
        GrossPay.setCellValueFactory(new PropertyValueFactory<Payroll,String>("GrossPay"));
        HealthIns.setCellValueFactory(new PropertyValueFactory<Payroll,String>("HealthIns"));
        VisionIns.setCellValueFactory(new PropertyValueFactory<Payroll,String>("VisionIns"));
        DentalIns.setCellValueFactory(new PropertyValueFactory<Payroll,String>("DentalIns"));
        LifeIns.setCellValueFactory(new PropertyValueFactory<Payroll,String>("LifeIns"));
        CountyTax.setCellValueFactory(new PropertyValueFactory<Payroll,String>("CountyTax"));
        StateTax.setCellValueFactory(new PropertyValueFactory<Payroll,String>("StateTax"));
        NetPay.setCellValueFactory(new PropertyValueFactory<Payroll,String>("NetPay"));
	

    payrollView.getColumns().addAll(ClockNumber, Lastname, Firstname, StreetAddress, City, State, Zipcode, Dependants, Hours, PayRate, OTHours, OTPayRate,GrossPay,HealthIns,VisionIns,DentalIns,LifeIns, CountyTax,StateTax, NetPay);

    //Filling up tableView with data
    payrollView.setItems(dbData);
    payrollView.getSelectionModel().getSelectedIndex();//setCellSelectionEnabled(true);
    int index = payrollView.getSelectionModel().getSelectedIndex();
    System.out.println(index);   
     
	}//end revisionData
	
	public void updatePayrollData(String newFirstDay, String newLastDay) throws ClassNotFoundException, SQLException {
		
		strFirstDay = newFirstDay;
		strLastDay = newLastDay;
		
		String selectHours = "select Clocknumber, Sum(totalhours) AS TotalHours" + 
				" from tbltimelog" + 
				" Where datefield between '"+strFirstDay+"' AND '" + strLastDay +
				"' Group by Clocknumber;";
    	System.out.println(selectHours);

        //Extracting data from Database
        ResultSet resultHours = null;
        try {
        	connection = DriverManager.getConnection(MySQLConnection.getPath(),MySQLConnection.getDatabase(),MySQLConnection.getDBPassword());
            PreparedStatement preparedStatement = connection.prepareStatement(selectHours);
            resultHours = preparedStatement.executeQuery();
            System.out.println(resultHours);
            while (resultHours.next()) {
            	alClockNumbers.add(resultHours.getString("Clocknumber"));
            	alHours.add(resultHours.getString("TotalHours"));
            	System.out.println(resultHours.getString("ClockNumber")+","+resultHours.getString("TotalHours"));
            }//end while resultset.next
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	
    	String select = "SELECT * FROM tblEmployees;";
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
  
        ObservableList dbData = FXCollections.observableArrayList(payrollArrayList(resultSet));
        

        //Filling up tableView with data
        payrollView.setItems(dbData);
        payrollView.getSelectionModel().getSelectedIndex();//setCellSelectionEnabled(true);
        int index = payrollView.getSelectionModel().getSelectedIndex();
        System.out.println(index);   
	}//end updateData

	//extracting data from ResulSet to ArrayList
	private ArrayList payrollArrayList(ResultSet resultSet) throws SQLException {
	ArrayList<Payroll> data =  new ArrayList<>();
	while (resultSet.next()) {
		double dblHours = 0.0;
		double dblOTHours = 0.0;
		Payroll payroll = new Payroll();
		payroll.ClockNumber.set(resultSet.getString("ClockNumber"));
		payroll.Lastname.set(resultSet.getString("Lastname"));
		payroll.Firstname.set(resultSet.getString("Firstname"));	
		payroll.StreetAddress.set(resultSet.getString("StreetAddress"));
		payroll.City.set(resultSet.getString("City"));
		payroll.State.set(resultSet.getString("State"));
		payroll.Zipcode.set(resultSet.getString("Zipcode"));
		payroll.Dependants.set(resultSet.getString("Dependants"));
		int x = 0;
		while(x< alClockNumbers.size()) {
			if (alClockNumbers.get(x).equals(resultSet.getString("ClockNumber"))) {
				dblHours = Double.parseDouble(alHours.get(x));
				if(dblHours<40.0) {
					payroll.Hours.set(String.valueOf(dblHours));
					payroll.OTHours.set("0.0"); 
				}else {
					payroll.Hours.set("40.0");
					payroll.OTHours.set(String.valueOf(dblHours - 40.0));
					dblOTHours = dblHours - 40.0;
					dblHours = 40.0;
				}
			}
			x = x+1;
		}
		payroll.PayRate.set("$"+ resultSet.getString("PayRate"));
		double dblPayRate = Double.parseDouble(resultSet.getString("PayRate"));
		payroll.OTPayRate.set("$"+ String.format("%.2f",dblPayRate*1.5));
		double dblGrossPay = (dblHours*dblPayRate)+(dblOTHours*(dblPayRate*1.5));
		payroll.GrossPay.set("$"+ String.format("%.2f",dblGrossPay));
		payroll.HealthIns.set("$"+ resultSet.getString("HealthIns"));
		payroll.VisionIns.set("$"+ resultSet.getString("VisionIns"));
		payroll.DentalIns.set("$"+ resultSet.getString("DentalIns"));
		payroll.LifeIns.set("$"+ resultSet.getString("LifeIns"));
		x = 0;
		while(x< alStateRate.size()) {
			if (alDependants.get(x).contentEquals(resultSet.getString("Dependants"))) {
				payroll.StateTax.set(alStateRate.get(x));
			}
			x = x+1;
		}
		x = 0;
		while(x<alCountyCode.size()) {
			if(alCountyCode.get(x).contentEquals(resultSet.getString("CountyCode"))) {
				double dblCountyTax = Double.parseDouble(alCountyRate.get(x));
				dblCountyTax = dblCountyTax * dblGrossPay;
				payroll.CountyTax.set("$"+String.valueOf(dblCountyTax));
				
			}
			x=x+1;
		}
	
		data.add(payroll);
		}
		return data;
	}//end revisionsArrayList
	
}