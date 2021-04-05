
public class MySQLConnection {
	
	static String path = "jdbc:mysql://localhost/timeclock?autoReconnect=true&useSSL=false";
	static String database = "root";
	static String dbPassword = "9653Wolf";
	
	public static String getPath() {
		
		System.out.println(path);
        return path;
    }
	
	public static String getDatabase() {
		return database;
	}
	
	public static String getDBPassword() {
		return dbPassword;
	}
	
	MySQLConnection(){}
	

}