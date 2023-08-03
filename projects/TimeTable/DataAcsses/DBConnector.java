package TimeTable.DataAcsses;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {

	private static Connection connection=null;
	
	public static Connection getConnection()
	{
		if(connection!=null)
			return connection;
		else
		{
			try{
				Class.forName("org.postgresql.Driver");
				connection=DriverManager.getConnection(
						"jdbc:postgresql://127.0.0.1:5432/TimeTable", "admin",
						"password");
			}
			catch(Exception ee){
				ee.printStackTrace();
			}
			return connection;
		}
					
	}
	
}
