
import java.sql.Connection;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EmployeesStage {
	
	String strUser,strRole;
	Label lblCurrentUser = new Label("");
	Label lblCurrentRole = new Label("");
	Label lblTitle = new Label("Purple Team Time clock");
	Label lblNotice = new Label("");
	Label lblNotice2 = new Label("");
	Label lblClockNumber = new Label("Clock Number");
	Label lblClockNumberField = new Label("");
	Label lblLastname = new Label("Last Name");
	TextField tfLastname = new TextField("");
	Label lblFirstname = new Label("First Name");
	TextField tfFirstname = new TextField("");
	Label lblStreetAddress = new Label("Street Address");
	TextField tfStreetAddress = new TextField("");
	Label lblCity = new Label("City");
	TextField tfCity = new TextField("");
	Label lblState = new Label("State");
	TextField tfState = new TextField("");
	Label lblZipcode = new Label("Zipcode");
	TextField tfZipcode = new TextField("");
	Label lblPayRate = new Label("Pay Rate");
	TextField tfPayRate = new TextField("");
	Label lblHireDate = new Label("Hire Date");
	TextField tfHireDate = new TextField("");
	Label lblHealthIns = new Label("Health Insurance");
	TextField tfHealthIns = new TextField("");
	Label lblVisionIns = new Label("Vision Insurance");
	TextField tfVisionIns = new TextField("");
	Label lblDentalIns = new Label("Dental Insurance");
	TextField tfDentalIns = new TextField("");
	Label lblLifeIns = new Label("Life Insurance");
	TextField tfLifeIns = new TextField("");
	Label lblCountyCode = new Label("County Code");
	TextField tfCountyCode = new TextField("");
	Label lblInactive = new Label("Inactive");
	TextField tfPassword = new TextField();
	ComboBox<String> cmboInactive = new ComboBox<String>();
	Button btnAddEmployee = new Button("Add Employee");
	Button btnUpdate = new Button("Update");
	Button btnClose = new Button("Close");
	
private TableView<Employees> employeesView = new TableView<>();
	
	BorderPane pane = new BorderPane();
	BorderPane pane2 = new BorderPane();
	
	static ObservableList listUsersToo = FXCollections.observableArrayList();
	ArrayList<String> listClockNumbers = new ArrayList<>();
	
	private static Connection connection;
	
	EmployeesStage(String newUser, String newRole,ObservableList newListUsers, ArrayList newListClockNumbers) throws ClassNotFoundException, SQLException{
		
		strUser = newUser;
		strRole = newRole;
		listUsersToo = newListUsers;
		listClockNumbers = newListClockNumbers;
		
		Stage employeesStage = new Stage();
		
		System.out.println("enter Scene getEmployees");
		GridPane employeesPane = new GridPane();
		employeesPane.setAlignment(Pos.BASELINE_CENTER);
		employeesPane.setPadding(new Insets(20.0, 20.0,20.0,20.0));
		employeesPane.setHgap(20.0);
		employeesPane.setVgap(20.0);
		lblClockNumber.setMinWidth(100);
		lblClockNumber.setFont(Font.font(16.0));
		lblClockNumberField.setMinWidth(200);
		lblClockNumberField.setFont(Font.font(16.0));
		lblLastname.setMinWidth(100);
		lblLastname.setFont(Font.font(16.0));
		lblFirstname.setMinWidth(100);
		lblFirstname.setFont(Font.font(16.0));
		lblStreetAddress.setMinWidth(100);
		lblStreetAddress.setFont(Font.font(16.0));
		lblCity.setMinWidth(100);
		lblCity.setFont(Font.font(16.0));
		lblState.setMinWidth(100);
		lblState.setFont(Font.font(16.0));
		lblZipcode.setMinWidth(100);
		lblZipcode.setFont(Font.font(16.0));
		lblPayRate.setMinWidth(100);
		lblPayRate.setFont(Font.font(16.0));
		lblHireDate.setMinWidth(100);
		lblHireDate.setFont(Font.font(16.0));
		lblHealthIns.setMinWidth(100);
		lblHealthIns.setFont(Font.font(16.0));
		lblVisionIns.setMinWidth(100);
		lblVisionIns.setFont(Font.font(16.0));
		lblDentalIns.setMinWidth(100);
		lblDentalIns.setFont(Font.font(16.0));
		lblLifeIns.setMinWidth(100);
		lblLifeIns.setFont(Font.font(16.0));
		lblCountyCode.setMinWidth(100);
		lblCountyCode.setFont(Font.font(16.0));
		tfLastname.setFont(Font.font(16.0));
		tfLastname.setMinWidth(200);
		tfFirstname.setFont(Font.font(16.0));
		tfFirstname.setMinWidth(200);
		lblInactive.setMinWidth(100);
		lblInactive.setFont(Font.font(16.0));
		cmboInactive.setMinWidth(200);
		tfStreetAddress.setFont(Font.font(16.0));
		tfStreetAddress.setMinWidth(200);
		tfCity.setFont(Font.font(16.0));
		tfCity.setMinWidth(200);
		tfState.setFont(Font.font(16.0));
		tfState.setMinWidth(200);
		tfZipcode.setFont(Font.font(16.0));
		tfZipcode.setMinWidth(200);
		tfPayRate.setFont(Font.font(16.0));
		tfPayRate.setMinWidth(200);
		tfHireDate.setFont(Font.font(16.0));
		tfHireDate.setMinWidth(200);
		tfHealthIns.setFont(Font.font(16.0));
		tfHealthIns.setMinWidth(200);
		tfVisionIns.setFont(Font.font(16.0));
		tfVisionIns.setMinWidth(200);
		tfDentalIns.setFont(Font.font(16.0));
		tfDentalIns.setMinWidth(200);
		tfLifeIns.setFont(Font.font(16.0));
		tfLifeIns.setMinWidth(200);
		tfCountyCode.setFont(Font.font(16.0));
		tfCountyCode.setMinWidth(200);
		btnAddEmployee.setMinWidth(200);
		btnAddEmployee.setFont(Font.font(16.0));
		btnUpdate.setMinWidth(200);
		btnUpdate.setFont(Font.font(16.0));
		btnClose.setMinWidth(200);
		btnClose.setFont(Font.font(16.0));
		lblCurrentUser.setText(strUser);
		lblCurrentRole.setText(strRole);
		cmboInactive.getItems().addAll("SELECT","TRUE", "FALSE");
		cmboInactive.getSelectionModel().select(0);
		
		employeesData();
		
		//checking for mouse click of tableview
		employeesView.setOnMouseClicked(e->{
		Employees rowValue = employeesView.getSelectionModel().getSelectedItem();
		String strClockNumber = rowValue.getClockNumber();
		String strLastname = rowValue.getLastname();
		String strFirstname = rowValue.getFirstname();
		String strInactive = rowValue.getInactive();
		
		lblClockNumberField.setText(strClockNumber);
		tfLastname.setText(strLastname);
		tfFirstname.setText(strFirstname);
		cmboInactive.setValue(strInactive);
		tfStreetAddress.setText(rowValue.getStreetAddress());
		tfCity.setText(rowValue.getCity());
		tfState.setText(rowValue.getState());
		tfZipcode.setText(rowValue.getZipcode());
		tfPayRate.setText(rowValue.getPayRate().replaceAll("[$,]", ""));
		tfHireDate.setText(rowValue.getHireDate());
		tfHealthIns.setText(rowValue.getHealthIns().replaceAll("[$,]", ""));
		tfVisionIns.setText(rowValue.getVisionIns().replaceAll("[$,]", ""));
		tfDentalIns.setText(rowValue.getDentalIns().replaceAll("[$,]", ""));
		tfLifeIns.setText(rowValue.getLifeIns().replaceAll("[$,]", ""));
		tfCountyCode.setText(rowValue.getCountyCode());
		});
		
		employeesPane.add(lblClockNumber, 1, 1);
		employeesPane.add(lblClockNumberField, 2, 1);
		employeesPane.add(lblLastname, 1, 2);
		employeesPane.add(tfLastname, 2, 2);
		employeesPane.add(lblFirstname, 1, 3);
		employeesPane.add(tfFirstname, 2, 3);
		employeesPane.add(lblInactive, 1, 4);
		employeesPane.add(cmboInactive, 2, 4);
		employeesPane.add(lblStreetAddress, 1, 5);
		employeesPane.add(tfStreetAddress, 2, 5,4,1);
		employeesPane.add(lblCity, 3, 1);
		employeesPane.add(tfCity, 4,1);
		employeesPane.add(lblState, 3,2);
		employeesPane.add(tfState, 4,2);
		employeesPane.add(lblZipcode, 3,3);
		employeesPane.add(tfZipcode, 4,3);
		employeesPane.add(lblPayRate, 3,4);
		employeesPane.add(tfPayRate, 4,4);
		employeesPane.add(lblHireDate, 5,1);
		employeesPane.add(tfHireDate, 6,1);
		employeesPane.add(lblHealthIns, 5,2);
		employeesPane.add(tfHealthIns, 6,2);
		employeesPane.add(lblDentalIns, 5,3);
		employeesPane.add(tfDentalIns, 6,3);
		employeesPane.add(lblVisionIns, 5,4);
		employeesPane.add(tfVisionIns, 6,4);
		employeesPane.add(lblLifeIns, 7,1);
		employeesPane.add(tfLifeIns, 8,1);
		employeesPane.add(lblCountyCode, 7,2);
		employeesPane.add(tfCountyCode, 8,2);
		
		employeesPane.add(lblNotice, 7,3);
		employeesPane.add(lblNotice2, 8, 3);
		employeesPane.add(btnUpdate, 8, 4);
		employeesPane.add(btnAddEmployee, 8, 5);
		employeesPane.add(btnClose, 8, 6);
		employeesPane.add(lblCurrentUser, 2, 7);
		employeesPane.add(lblCurrentRole, 4, 7);
		
		VBox employeesVBox = new VBox();
		employeesVBox.setPadding(new Insets(15,15,15,15));
		employeesVBox.getChildren().add(employeesView);
		employeesVBox.setPrefHeight(400);
		employeesVBox.setPrefWidth(1000);
		
		btnAddEmployee.setOnAction(e-> {
			
			//checking required field values
			if(tfFirstname.getText().isEmpty() || tfFirstname.getText().isEmpty() || 				cmboInactive.getValue().equals("SELECT")  ) {
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
	        	String updateEmployeeList = "Insert into tblEmployees (Lastname, Firstname, Inactive, StreetAddress, City, State, Zipcode, Payrate, HireDate, HealthIns, VisionIns, DentalIns, LifeIns, CountyCode)"+
	        			"VALUES ('"+ tfLastname.getText() +
	        			"','"+ tfFirstname.getText() +
	        			"','"+cmboInactive.getValue()+
	        			"','"+ tfStreetAddress.getText() +
	        			"','"+ tfCity.getText() +
	        			"','"+ tfState.getText() +
	        			"','"+ tfZipcode.getText() +
	        			"','"+ tfPayRate.getText() +
	        			"','"+ tfHireDate.getText() +
	        			"','"+ tfHealthIns.getText() +
	        			"','"+ tfVisionIns.getText() +
	        			"','"+ tfDentalIns.getText() +
	        			"','"+ tfLifeIns.getText() +
	        			"','"+ tfCountyCode.getText() +
	        			"');";
	        	System.out.println(updateEmployeeList);
	        	PreparedStatement preparedStatement = connection.prepareStatement(updateEmployeeList);
	           preparedStatement.executeUpdate();

	        }//end of try for update query 
	        catch (SQLException ex) {
	            ex.printStackTrace();
	        }//end of catch for update query
	        
	        //return field values to empty state
	        //lblClockNumberField.setText("");
			//cmboInactive.getSelectionModel().select(0);
			
			try {
				new EmployeesStage(strUser,strRole, listUsersToo, listClockNumbers);
				employeesStage.close();
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
			if(lblClockNumberField.getText().isEmpty() || tfFirstname.getText().isEmpty() || tfLastname.getText().isEmpty() || cmboInactive.getValue().equals("SELECT") || 
				tfPayRate.getText().isEmpty() || tfHireDate.getText().isEmpty() ||	
				tfStreetAddress.getText().isEmpty() || tfCity.getText().isEmpty() || tfState.getText().isEmpty() ) {
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
	        	String updateEmployeeList = "UPDATE tblEmployees SET tblEmployees.Firstname = '"+ tfFirstname.getText() +
	        			"',tblEmployees.Lastname = '"+ tfLastname.getText() +
	        			"',tblEmployees.Inactive = '"+cmboInactive.getValue()+
	        			"',tblEmployees.StreetAddress = '"+ tfStreetAddress.getText() +
	        			"',tblEmployees.City = '"+ tfCity.getText() +
	        			"',tblEmployees.State = '"+ tfState.getText() +
	        			"',tblEmployees.Zipcode = '"+ tfZipcode.getText() +
	        			"',tblEmployees.PayRate = '"+ tfPayRate.getText() +
	        			"',tblEmployees.HireDate = '"+ tfHireDate.getText() +
	        			"',tblEmployees.HealthIns = '"+ tfHealthIns.getText() +
	        			"',tblEmployees.VisionIns = '"+ tfVisionIns.getText() +
	        			"',tblEmployees.DentalIns = '"+ tfDentalIns.getText() +
	        			"',tblEmployees.LifeIns = '"+ tfLifeIns.getText() +
	        			"',tblEmployees.CountyCode = '"+ tfCountyCode.getText() +
	        			"' WHERE tblEmployees.ClockNumber = "+ lblClockNumberField.getText()+";";
	        	System.out.println(updateEmployeeList);
	        	PreparedStatement preparedStatement = connection.prepareStatement(updateEmployeeList);
	           preparedStatement.executeUpdate();

	        }//end of try for update query 
	        catch (SQLException ex) {
	            ex.printStackTrace();
	        }//end of catch for update query
	        
	        
	        //return field values to empty state
	        //lblClockNumberField.setText("");
			//cmboInactive.getSelectionModel().select(0);
			
			try {
				new EmployeesStage(strUser,strRole, listUsersToo, listClockNumbers);
				employeesStage.close();
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
				employeesStage.close();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			
		});
		
		pane.setCenter(employeesVBox);
		pane.setBottom(employeesPane);
		
		Scene sceneEmployees = new Scene(pane);
		employeesStage.setTitle("Project Purple Team");
	
		employeesStage.setScene(sceneEmployees);
		employeesStage.show();
		employeesStage.setMaximized(true);
	}
	
public void employeesData() throws ClassNotFoundException, SQLException {
    	
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
  
        ObservableList dbData = FXCollections.observableArrayList(employeesArrayList(resultSet));
        
        employeesView.setEditable(true);
        TableColumn <Employees,String> ClockNumber = new TableColumn("Clock Number");
        TableColumn <Employees,String> Lastname = new TableColumn("Last Name");
        TableColumn <Employees,String> Firstname = new TableColumn("First Name");
    	TableColumn <Employees,String> Inactive = new TableColumn("Inactive");
    	TableColumn <Employees,String> StreetAddress = new TableColumn("Street Address");
    	TableColumn <Employees,String> City = new TableColumn("City");
    	TableColumn <Employees,String> State = new TableColumn("State");
    	TableColumn <Employees,String> Zipcode = new TableColumn("Zipcode");
    	TableColumn <Employees,String> PayRate = new TableColumn("Pay Rate");
    	TableColumn <Employees,String> HireDate = new TableColumn("Hire Date");
    	TableColumn <Employees,String> HealthIns = new TableColumn("Health Ins.");
    	TableColumn <Employees,String> VisionIns = new TableColumn("Vision Ins.");
    	TableColumn <Employees,String> DentalIns = new TableColumn("Dental Ins.");
    	TableColumn <Employees,String> LifeIns = new TableColumn("Life Ins.");
    	TableColumn <Employees,String> CountyCode = new TableColumn("County Code");
       
        //Setting cell property value to correct variable from Equipment class
    	ClockNumber.setCellValueFactory(new PropertyValueFactory<Employees,String>("ClockNumber"));
    	Lastname.setCellValueFactory(new PropertyValueFactory<Employees,String>("Lastname"));
    	Firstname.setCellValueFactory(new PropertyValueFactory<Employees,String>("Firstname"));
        Inactive.setCellValueFactory(new PropertyValueFactory<Employees,String>("Inactive"));
        StreetAddress.setCellValueFactory(new PropertyValueFactory<Employees,String>("StreetAddress"));
        City.setCellValueFactory(new PropertyValueFactory<Employees,String>("City"));
        State.setCellValueFactory(new PropertyValueFactory<Employees,String>("State"));
        Zipcode.setCellValueFactory(new PropertyValueFactory<Employees,String>("Zipcode"));
        PayRate.setCellValueFactory(new PropertyValueFactory<Employees,String>("PayRate"));
        HireDate.setCellValueFactory(new PropertyValueFactory<Employees,String>("HireDate"));
        HealthIns.setCellValueFactory(new PropertyValueFactory<Employees,String>("HealthIns"));
        VisionIns.setCellValueFactory(new PropertyValueFactory<Employees,String>("VisionIns"));
        DentalIns.setCellValueFactory(new PropertyValueFactory<Employees,String>("DentalIns"));
        LifeIns.setCellValueFactory(new PropertyValueFactory<Employees,String>("LifeIns"));
        CountyCode.setCellValueFactory(new PropertyValueFactory<Employees,String>("CountyCode"));
        
        //set cells to textfields for tableview edit
        //one edits view not actual table
        //UserID.setCellFactory(TextFieldTableCell.forTableColumn());//do not want ID to be editable
        Lastname.setCellFactory(TextFieldTableCell.forTableColumn());
        Firstname.setCellFactory(TextFieldTableCell.forTableColumn());
        Inactive.setCellFactory(ComboBoxTableCell.forTableColumn("True","False"));
        StreetAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        City.setCellFactory(TextFieldTableCell.forTableColumn());
        State.setCellFactory(TextFieldTableCell.forTableColumn());
        Zipcode.setCellFactory(TextFieldTableCell.forTableColumn());
        PayRate.setCellFactory(TextFieldTableCell.forTableColumn());
        HireDate.setCellFactory(TextFieldTableCell.forTableColumn());
        HealthIns.setCellFactory(TextFieldTableCell.forTableColumn());
        VisionIns.setCellFactory(TextFieldTableCell.forTableColumn());
        DentalIns.setCellFactory(TextFieldTableCell.forTableColumn());
        LifeIns.setCellFactory(TextFieldTableCell.forTableColumn());
        CountyCode.setCellFactory(TextFieldTableCell.forTableColumn());
        
        //create eventhandlers for each lastname cell change
        Lastname.setOnEditCommit(new EventHandler<CellEditEvent<Employees, String>>(){
        	
        	@Override
        	public void handle(CellEditEvent<Employees, String> event) {
        		try {
        			Employees employees = event.getRowValue();
        			employees.setLastname(event.getNewValue());
        			tfLastname.setText(event.getNewValue());
        			
    	        	String updateEmployees = "UPDATE tblEmployees SET tblEmployees.Lastname = '"+ tfLastname.getText() +
    	        			"' WHERE tblEmployees.ClockNumber = "+ lblClockNumberField.getText()+";";
    	        	System.out.println(updateEmployees);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateEmployees);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query
        		
        	}//end handle event
        });//end Lastname setOnEditCommit event handler
        
      //create eventhandlers for each name cell change
        Firstname.setOnEditCommit(new EventHandler<CellEditEvent<Employees, String>>(){
        	
        	@Override
        	public void handle(CellEditEvent<Employees, String> event) {
        		try {
        			Employees employees = event.getRowValue();
        			employees.setFirstname(event.getNewValue());
        			tfFirstname.setText(event.getNewValue());
        			
    	        	String updateEmployees = "UPDATE tblEmployees SET tblEmployees.Firstname = '"+ tfFirstname.getText() +
    	        			"' WHERE tblEmployees.ClockNumber = "+ lblClockNumberField.getText()+";";
    	        	System.out.println(updateEmployees);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateEmployees);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query
        		
        	}//end handle event
        });//end Firstname setOnEditCommit event handler
        
      //create eventhandlers for each role cell change
        Inactive.setOnEditCommit(new EventHandler<CellEditEvent<Employees, String>>(){
        	
        	@Override
        	public void handle(CellEditEvent<Employees, String> event) {
        		try {
        			Employees employees = event.getRowValue();
        			employees.setInactive(event.getNewValue());
        			cmboInactive.setValue(event.getNewValue());
        			
    	        	String updateEmployees = "UPDATE tblEmployees SET tblEmployees.Inactive = '"+ cmboInactive.getValue() +
    	        			"' WHERE tblEmployees.ClockNumber = "+ lblClockNumberField.getText()+";";
    	        	System.out.println(updateEmployees);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateEmployees);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query
        		
        	}//end handle event
        });//end Inactive setOnEditCommit event handler
        
        StreetAddress.setOnEditCommit(new EventHandler<CellEditEvent<Employees, String>>(){
        	
        	@Override
        	public void handle(CellEditEvent<Employees, String> event) {
        		try {
        			Employees employees = event.getRowValue();
        			employees.setStreetAddress(event.getNewValue());
        			tfStreetAddress.setText(event.getNewValue());
        			
    	        	String updateEmployees = "UPDATE tblEmployees SET tblEmployees.StreetAddress = '"+ tfStreetAddress.getText() +
    	        			"' WHERE tblEmployees.ClockNumber = "+ lblClockNumberField.getText()+";";
    	        	System.out.println(updateEmployees);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateEmployees);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query
        		
        	}//end handle event
        });//end StreetAddress setOnEditCommit event handler
        
        City.setOnEditCommit(new EventHandler<CellEditEvent<Employees, String>>(){
        	
        	@Override
        	public void handle(CellEditEvent<Employees, String> event) {
        		try {
        			Employees employees = event.getRowValue();
        			employees.setCity(event.getNewValue());
        			tfCity.setText(event.getNewValue());
        			
    	        	String updateEmployees = "UPDATE tblEmployees SET tblEmployees.City = '"+ tfCity.getText() +
    	        			"' WHERE tblEmployees.ClockNumber = "+ lblClockNumberField.getText()+";";
    	        	System.out.println(updateEmployees);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateEmployees);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query
        		
        	}//end handle event
        });//end City setOnEditCommit event handler
        
        State.setOnEditCommit(new EventHandler<CellEditEvent<Employees, String>>(){
        	
        	@Override
        	public void handle(CellEditEvent<Employees, String> event) {
        		try {
        			Employees employees = event.getRowValue();
        			employees.setState(event.getNewValue());
        			tfState.setText(event.getNewValue());
        			
    	        	String updateEmployees = "UPDATE tblEmployees SET tblEmployees.State = '"+ tfState.getText() +
    	        			"' WHERE tblEmployees.ClockNumber = "+ lblClockNumberField.getText()+";";
    	        	System.out.println(updateEmployees);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateEmployees);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query
        		
        	}//end handle event
        });//end State setOnEditCommit event handler
        
        Zipcode.setOnEditCommit(new EventHandler<CellEditEvent<Employees, String>>(){
        	
        	@Override
        	public void handle(CellEditEvent<Employees, String> event) {
        		try {
        			Employees employees = event.getRowValue();
        			employees.setZipcode(event.getNewValue());
        			tfZipcode.setText(event.getNewValue());
        			
    	        	String updateEmployees = "UPDATE tblEmployees SET tblEmployees.Zipcode = '"+ tfZipcode.getText() +
    	        			"' WHERE tblEmployees.ClockNumber = "+ lblClockNumberField.getText()+";";
    	        	System.out.println(updateEmployees);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateEmployees);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query
        		
        	}//end handle event
        });//end Zipcode setOnEditCommit event handler
        
        PayRate.setOnEditCommit(new EventHandler<CellEditEvent<Employees, String>>(){
        	
        	@Override
        	public void handle(CellEditEvent<Employees, String> event) {
        		try {
        			Employees employees = event.getRowValue();
        			employees.setPayRate(event.getNewValue());
        			tfPayRate.setText(event.getNewValue().replaceAll("[$,]", ""));
        			
    	        	String updateEmployees = "UPDATE tblEmployees SET tblEmployees.PayRate = '"+ tfPayRate.getText() +
    	        			"' WHERE tblEmployees.ClockNumber = "+ lblClockNumberField.getText()+";";
    	        	System.out.println(updateEmployees);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateEmployees);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query
        		
        	}//end handle event
        });//end PayRate setOnEditCommit event handler
       
        HireDate.setOnEditCommit(new EventHandler<CellEditEvent<Employees, String>>(){
        	
        	@Override
        	public void handle(CellEditEvent<Employees, String> event) {
        		try {
        			Employees employees = event.getRowValue();
        			employees.setHireDate(event.getNewValue());
        			tfHireDate.setText(event.getNewValue());
        			
    	        	String updateEmployees = "UPDATE tblEmployees SET tblEmployees.HireDate = '"+ tfHireDate.getText() +
    	        			"' WHERE tblEmployees.ClockNumber = "+ lblClockNumberField.getText()+";";
    	        	System.out.println(updateEmployees);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateEmployees);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query
        		
        	}//end handle event
        });//end HireDate setOnEditCommit event handler
        
        HealthIns.setOnEditCommit(new EventHandler<CellEditEvent<Employees, String>>(){
        	
        	@Override
        	public void handle(CellEditEvent<Employees, String> event) {
        		try {
        			Employees employees = event.getRowValue();
        			employees.setHealthIns(event.getNewValue());
        			tfHealthIns.setText(event.getNewValue().replaceAll("[$,]", ""));
        			
    	        	String updateEmployees = "UPDATE tblEmployees SET tblEmployees.HealthIns = '"+ tfHealthIns.getText() +
    	        			"' WHERE tblEmployees.ClockNumber = "+ lblClockNumberField.getText()+";";
    	        	System.out.println(updateEmployees);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateEmployees);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query
        		
        	}//end handle event
        });//end HealthIns setOnEditCommit event handler
        
        VisionIns.setOnEditCommit(new EventHandler<CellEditEvent<Employees, String>>(){
        	
        	@Override
        	public void handle(CellEditEvent<Employees, String> event) {
        		try {
        			Employees employees = event.getRowValue();
        			employees.setVisionIns(event.getNewValue());
        			tfVisionIns.setText(event.getNewValue().replaceAll("[$,]", ""));
        			
    	        	String updateEmployees = "UPDATE tblEmployees SET tblEmployees.VisionIns = '"+ tfVisionIns.getText() +
    	        			"' WHERE tblEmployees.ClockNumber = "+ lblClockNumberField.getText()+";";
    	        	System.out.println(updateEmployees);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateEmployees);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query
        		
        	}//end handle event
        });//end VisionIns setOnEditCommit event handler
        
        DentalIns.setOnEditCommit(new EventHandler<CellEditEvent<Employees, String>>(){
        	
        	@Override
        	public void handle(CellEditEvent<Employees, String> event) {
        		try {
        			Employees employees = event.getRowValue();
        			employees.setDentalIns(event.getNewValue());
        			tfDentalIns.setText(event.getNewValue().replaceAll("[$,]", ""));
        			
    	        	String updateEmployees = "UPDATE tblEmployees SET tblEmployees.DentalIns = '"+ tfDentalIns.getText() +
    	        			"' WHERE tblEmployees.ClockNumber = "+ lblClockNumberField.getText()+";";
    	        	System.out.println(updateEmployees);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateEmployees);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query
        		
        	}//end handle event
        });//end DentalIns setOnEditCommit event handler
        
        LifeIns.setOnEditCommit(new EventHandler<CellEditEvent<Employees, String>>(){
        	
        	@Override
        	public void handle(CellEditEvent<Employees, String> event) {
        		try {
        			Employees employees = event.getRowValue();
        			employees.setLifeIns(event.getNewValue());
        			tfLifeIns.setText(event.getNewValue().replaceAll("[$,]", ""));
        			
    	        	String updateEmployees = "UPDATE tblEmployees SET tblEmployees.LifeIns = '"+ tfLifeIns.getText() +
    	        			"' WHERE tblEmployees.ClockNumber = "+ lblClockNumberField.getText()+";";
    	        	System.out.println(updateEmployees);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateEmployees);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query
        		
        	}//end handle event
        });//end LifeIns setOnEditCommit event handler
        
        CountyCode.setOnEditCommit(new EventHandler<CellEditEvent<Employees, String>>(){
        	
        	@Override
        	public void handle(CellEditEvent<Employees, String> event) {
        		try {
        			Employees employees = event.getRowValue();
        			employees.setCountyCode(event.getNewValue());
        			tfCountyCode.setText(event.getNewValue().replaceAll("[$,]", ""));
        			
    	        	String updateEmployees = "UPDATE tblEmployees SET tblEmployees.CountyCode = '"+ tfCountyCode.getText() +
    	        			"' WHERE tblEmployees.ClockNumber = "+ lblClockNumberField.getText()+";";
    	        	System.out.println(updateEmployees);
    	        	PreparedStatement preparedStatement = connection.prepareStatement(updateEmployees);
    	           preparedStatement.executeUpdate();

    	        }//end of try for update query 
    	        catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }//end of catch for update query
        		
        	}//end handle event
        });//end LifeIns setOnEditCommit event handler
        
        
        employeesView.getColumns().addAll(ClockNumber, Lastname, Firstname, Inactive, StreetAddress, City, State, Zipcode, CountyCode,PayRate, HireDate, HealthIns,VisionIns,DentalIns,LifeIns);

        //Filling up tableView with data
        employeesView.setItems(dbData);
        employeesView.getSelectionModel().getSelectedIndex();//setCellSelectionEnabled(true);
        int index = employeesView.getSelectionModel().getSelectedIndex();
        System.out.println(index);   
         
    }//end revisionData

//extracting data from ResulSet to ArrayList
	private ArrayList employeesArrayList(ResultSet resultSet) throws SQLException {
		ArrayList<Employees> data =  new ArrayList<>();
		while (resultSet.next()) {
			Employees employees = new Employees();
			employees.ClockNumber.set(resultSet.getString("ClockNumber"));
			employees.Lastname.set(resultSet.getString("Lastname"));
			employees.Firstname.set(resultSet.getString("Firstname"));
			employees.Inactive.set(resultSet.getString("Inactive"));
			employees.StreetAddress.set(resultSet.getString("StreetAddress"));
			employees.City.set(resultSet.getString("City"));
			employees.State.set(resultSet.getString("State"));
			employees.Zipcode.set(resultSet.getString("Zipcode"));
			employees.PayRate.set("$"+ resultSet.getString("PayRate"));
			employees.HireDate.set(resultSet.getString("HireDate"));
			employees.HealthIns.set("$"+ resultSet.getString("HealthIns"));
			employees.VisionIns.set("$"+ resultSet.getString("VisionIns"));
			employees.DentalIns.set("$"+ resultSet.getString("DentalIns"));
			employees.LifeIns.set("$"+ resultSet.getString("LifeIns"));
			employees.CountyCode.set(resultSet.getString("CountyCode"));
			data.add(employees);
		}
		return data;
	}//end revisionsArrayList
	
}