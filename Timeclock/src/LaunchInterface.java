import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.application.Application;

import javafx.stage.Stage;

public class LaunchInterface extends Application {
	
	private Connection connection;
	
	public static void main(String[] args){
		// TODO Auto-generated method stub
		launch(args);
	}//end of main
	
	public void start(Stage primaryStage) throws Exception{
		//initialize database connection and create statement objects
		initializeDB();
		//open LoginStage
		new LoginStage();
		
		
	}
	
	private void initializeDB() {
		try {
			//load jdbc driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			//establish database connection
			connection = DriverManager.getConnection(MySQLConnection.getPath(),MySQLConnection.getDatabase(),MySQLConnection.getDBPassword());
			System.out.println("DB connected");
					
			
		}//end try
		catch(Exception ex) {
			ex.printStackTrace();
		}////end catch
	}

}