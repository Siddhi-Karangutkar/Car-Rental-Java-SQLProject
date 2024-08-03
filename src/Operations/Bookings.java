package Operations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



import DatabasePackage.DatabaseUtilities;

public class Bookings {
	public Bookings(Scanner sc,DatabaseUtilities databaseUtilities)
	{
		handleBookings(sc,databaseUtilities);
	}
	
	public void handleBookings(Scanner sc,DatabaseUtilities databaseUtilities)
	{
		int choice =0 ;
		while(choice!=5)
		{
			showOptions();
			choice=sc.nextInt();
			switch(choice)
			{
			case 1:
				System.out.println("Enter userid: ");
				int userid = sc.nextInt();
				System.out.println("Enter carid: ");
				int carid = sc.nextInt();
				databaseUtilities.showCar(carid);
				System.out.println("Enter Bookingdate: ");
				String date = sc.next();
				float amount = databaseUtilities.getCarAmount(carid);
				System.out.println("Amount to pay: "+amount);
				System.out.println("Are you sure for your payment(y/n): ");
				String ans = sc.next();
				// can also use str.toUppercase -> Y . ans.equals("Y")
				if((ans.equals("y")) || (ans.equals("Y"))) {
					databaseUtilities.BookingsDetails(userid,carid,date,amount);
				}else
				{
					System.out.println("Booking pending");
				}
				break;
				
			case 2:
				System.out.println("Enter booking id to cancel booking:");
				int bookingid = sc.nextInt();
				databaseUtilities.bookingcancel(bookingid);//issue
				break;
				
			case 3:
				System.out.println("Enter bookingid to view: ");
				bookingid = sc.nextInt();
				//statement.executeUpdate("create table Bookings (bookingstatus varchar(255),bookingid int auto_increment,bookingdate varchar(255),carid int ,userid int,amount float,constraint bookingtable primary key(bookingid),foreign key (carid) references cartable(carid),foreign key (userid) references user(userid))");

				databaseUtilities.showbookings(bookingid);
				
				break;
				
			case 4:
				databaseUtilities.showallbookings();
				
				break;
				
			case 5:
				System.out.println("Exit");
				break;
			}
		}
	}
	
	public void showOptions()
	{
		System.out.println("1.To book a car");
		System.out.println("2.To cancel a booking");
		System.out.println("3.To show Booking");
		System.out.println("4.To show all bookings");
		System.out.println("5.Exit");
		System.out.println("Enter your choice: ");
	}

}
