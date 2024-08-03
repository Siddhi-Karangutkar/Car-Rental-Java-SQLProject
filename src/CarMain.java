import java.util.Scanner;

import DatabasePackage.DatabaseConnection;
import DatabasePackage.DatabaseUtilities;
import Operations.Bookings;
import Operations.CarUser;
import Operations.User;

public class CarMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DatabaseConnection databaseConnection = new DatabaseConnection();
		DatabaseUtilities databaseUtilities =new DatabaseUtilities();
		databaseUtilities.createTable();
		int choice=0;
		while(choice!=4)
		{
			showOptions();
			choice = sc.nextInt();
			switch(choice)
			{
			case 1:
				CarUser carUser = new CarUser(sc,databaseUtilities);
				break;
				
			case 2:
				User user = new User(sc,databaseUtilities);
				break;
				
			case 3:
				Bookings bookings = new Bookings(sc,databaseUtilities);//issue
				break;
				
			case 4:
				System.out.println("Exit");
				break;
			}
		}
		
	}
	public static void showOptions()
	{
		System.out.println("1.Car User");
		System.out.println("2.User");
		System.out.println("3.Bookings");
		System.out.println("4.Exit");
	}

}
