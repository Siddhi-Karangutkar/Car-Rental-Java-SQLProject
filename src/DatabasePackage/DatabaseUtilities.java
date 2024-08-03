package DatabasePackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtilities extends DatabaseConnection {
	
	public void createTable()
	{
		createCarUserTable(super.statement);
		createUserTable(super.statement);
		createBookingsTable(super.statement);
	}

	public void createCarUserTable(Statement statement)
	{
		try {
			statement.executeUpdate("create table cartable (carid int auto_increment,carmodel varchar(255),carnumber varchar(255),carstatus varchar(255),amount float,constraint pk_cartable primary key(carid))");
		} catch (SQLException e) {
			//System.out.println(e.getMessage());
		}
		
	}
	public void createUserTable(Statement statement)
	{
		try {
			statement.executeUpdate("create table User (userid int auto_increment,username varchar(255),userage int,balance float,constraint pk_user primary key(userid))");
		} catch (SQLException e) {
			//System.out.println(e.getMessage());
		}
	

	}
	public void createBookingsTable(Statement statement)
	{
		try {
			statement.executeUpdate("create table Bookings (bookingstatus varchar(255),bookingid int auto_increment,bookingdate varchar(255),carid int ,userid int,amount float,constraint bookingtable primary key(bookingid),foreign key (carid) references cartable(carid),foreign key (userid) references user(userid))");
		} catch (SQLException e) {
			//System.out.println(e.getMessage());
		}
	

	}
	public void CarUserDetails(String carname,String carnumber,float amount)
	{
		try {
			statement.executeUpdate("insert into cartable values(carid,'"+carname+"','"+carnumber+"','Unoccupied',"+amount+")");
		} catch (SQLException e) {
			
		System.out.println(e.getMessage());
		}
	}
	public void UserDetails(String name,int age,float amount)
	{
		try {
			
				statement.executeUpdate("insert into User values(userid,'"+name+"',"+age+","+amount+")");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void showCar(int carid)
	{
		try {
			ResultSet resultset = connection.createStatement().executeQuery("select * from cartable where carid = "+carid);
			while(resultset.next())
			{
				 carid = resultset.getInt("carid");
				 System.out.println("Carid: "+carid);
				String model = resultset.getString("carmodel");
				 System.out.println("Car Model: "+model);
				String carNumber = resultset.getString("carnumber");
				 System.out.println("Car Number: "+carNumber);
				 String status = resultset.getString("carstatus");
				 System.out.println("Status: "+status);
				float amount = resultset.getFloat("amount");
				 System.out.println("Amount: "+amount);
			}
			} catch (SQLException e) {
		System.out.println(e.getMessage());
		}
	}
	public float getCarAmount(int carid)
	{
		float amount=0;
		try {
			ResultSet resultset = connection.createStatement().executeQuery("select amount from cartable where carid = "+carid);
			while(resultset.next())
			{
				 amount = resultset.getFloat("amount");
				
			}
			return amount;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return amount;
	}
	public void BookingsDetails(int userid, int carid,String date,float amount)
	{
		float balance=0;
		String date1="";

		try {		
			ResultSet resultset = connection.createStatement().executeQuery("select balance from user where userid = "+userid);
			while (resultset.next())
			{
				 balance = resultset.getFloat("balance");
			}
			ResultSet resultset1 = connection.createStatement().executeQuery("select bookingdate from bookings where carid = "+carid);
			while(resultset1.next())
			{
			date1 = resultset1.getString("bookingdate");
			}
			
			if(amount<=balance) 
			{
				if(!date.equals(date1))
				{
					statement.executeUpdate("insert into bookings values('Occupied',bookingid,'"+date+"',"+carid+","+userid+","+amount+")");
					setUserBalance( userid, amount);
					statement.executeUpdate("update cartable set carstatus = 'Occupied' where carid = "+carid);	
				}
				else
				{
					System.out.println("Car Ocuupied at this date");
				}	
			}
			else
			{
				System.out.println("Balance is less than amount");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
		
	
	public void setUserBalance(int userid,float amount) {
		float balance=0;
		try {
			ResultSet resultset = connection.createStatement().executeQuery("select balance from user where userid = "+userid);
			while(resultset.next())
			{
				 balance = resultset.getFloat("balance");
				statement.executeUpdate("update user set balance ="+(balance - amount)+"where userid ="+userid);

			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void bookingcancel(int bookingid)//issue
	{
		try {
			ResultSet resultset = connection.createStatement().executeQuery("select userid,carid,amount from bookings where bookingid = "+bookingid+"");
			while(resultset.next())
			{
				int carid = resultset.getInt("carid");
				int userid = resultset.getInt("userid");
				float amount = resultset.getFloat("amount");
				statement.executeUpdate("update cartable set carstatus = 'Unoccupied' where carid = "+carid+"");
				setUserBalance(userid,(-amount));
				statement.executeUpdate("delete from bookings where bookingid = "+bookingid+"");
				System.out.println("Booking cancelled Payment will be retrieved soon");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	public void deletecar(int carid)
	{
		try {
			//CHangeto dbutil function
			//No db queries allowed in business logic
			DatabaseUtilities.statement.executeUpdate("delete from cartable where carid = "+carid);
		} catch (SQLException e) {
			System.out.println("Car in use");
			System.out.println(e.getMessage());
		}
	}
	public void showallcar()
	{
		try {
			//Same mistake
			ResultSet resultset = connection.createStatement().executeQuery("select * from cartable");
			while(resultset.next())
			{
				 int carid = resultset.getInt("carid");
				 System.out.println("Carid: "+carid);
				 String model = resultset.getString("carmodel");
				 System.out.println("Car Model: "+model);
				 String carNumber = resultset.getString("carnumber");
				 System.out.println("Car Number: "+carNumber);
				 String status = resultset.getString("carstatus");
				 System.out.println("Status: "+status);
				 float amount = resultset.getFloat("amount");
				 System.out.println("Amount: "+amount);
			}
			} catch (SQLException e) {
		System.out.println(e.getMessage());
		}
	}
	public void deleteuser(int userid)
	{
		try {
			//Mistake
			DatabaseUtilities.statement.executeUpdate("delete from User where userid = "+userid);
		} catch (SQLException e) {
			System.out.println("Booking already done");
			System.out.println(e.getMessage());
		}
	}
	public void showalluser()
	{
		try {
			//Mistake
			ResultSet resultset = connection.createStatement().executeQuery("select * from User");
			while(resultset.next())
			{
				int userid = resultset.getInt("userid");
				 System.out.println("Userid: "+userid);
				 String name = resultset.getString("username");
				 System.out.println("Name: "+name);
				 int age = resultset.getInt("userage");
				 System.out.println("User age: "+age);
				 float amount = resultset.getFloat("balance");
				 System.out.println("Balance: "+amount);
			}
			} catch (SQLException e) {
		System.out.println(e.getMessage());
		}
	}
	public void showuser(int userid)
	{
		try {
			//Mistake
			ResultSet resultset = connection.createStatement().executeQuery("select * from User where userid = "+userid);
			while(resultset.next())
			{
				 userid = resultset.getInt("userid");
				 System.out.println("Userid: "+userid);
				String name = resultset.getString("username");
				 System.out.println("Name: "+name);
				 int age = resultset.getInt("userage");
				 System.out.println("User age: "+age);
				 float amount = resultset.getFloat("balance");
				 System.out.println("Balance: "+amount);
			}
			} catch (SQLException e) {
		System.out.println(e.getMessage());
		}
	}
	public void showbookings(int bookingid)
	{
		try {
			//Mistake
			ResultSet resultset = connection.createStatement().executeQuery("select * from bookings where bookingid = "+bookingid+"");
			while(resultset.next()) {
				bookingid = resultset.getInt("bookingid");
				System.out.println("Booking id: "+bookingid);
				String date = resultset.getString("bookingdate");
				System.out.println("Booking date: "+date);
				int carid = resultset.getInt("carid");
				System.out.println("Carid:"+carid);
				int userid =resultset.getInt("userid");
				System.out.println("Userid: "+userid);
				float amount = resultset.getFloat("amount");
				System.out.println("Amount: "+amount);
			}
			
		} catch (SQLException e) {
		System.out.println(e.getMessage());	
		}
	}
	public void showallbookings()
	{
		try {
			//Mistake
			ResultSet resultset = connection.createStatement().executeQuery("select * from bookings");
			while(resultset.next())
			{
				int bookingid = resultset.getInt("bookingid");
				System.out.println("Booking id: "+bookingid);
				String date = resultset.getString("bookingdate");
				System.out.println("Booking date: "+date);
				int carid = resultset.getInt("carid");
				System.out.println("Carid:"+carid);
				int userid =resultset.getInt("userid");
				System.out.println("Userid: "+userid);
				float amount = resultset.getFloat("amount");
				System.out.println("Amount: "+amount);	
			}
			
		} catch (SQLException e) {
		System.out.println(e.getMessage());	
		}
	}
}
