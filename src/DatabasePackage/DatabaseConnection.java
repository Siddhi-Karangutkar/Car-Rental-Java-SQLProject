package DatabasePackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {
	Connection connection;
	public static Statement statement;
	public DatabaseConnection()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cardatabase","root","12345");
			  this.statement = connection.createStatement();
		}catch(Exception e)
		{
		e.getStackTrace();
		//System.out.println(e.getMessage());
		}

}}
