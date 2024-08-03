package Operations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import DatabasePackage.DatabaseUtilities;

public class CarUser {
	
	public CarUser(Scanner sc,DatabaseUtilities databaseUtilities)
	{
		handleCarUser(sc,databaseUtilities);
	}
	public void handleCarUser(Scanner sc,DatabaseUtilities databaseUtilities)
	{
		int choice=0;
		while(choice!=5)
		{
			showOptions();
			choice = sc.nextInt();
			switch(choice)
			{
			case 1:
				System.out.println("Enter car model: ");
				String model = sc.next();
				System.out.println("Enter car number: ");
				String carNumber= sc.next();
				System.out.println("Enter car aamount: ");
				float amount = sc.nextFloat();
				databaseUtilities.CarUserDetails(model,carNumber,amount);
				break;
				
			case 2:
				System.out.println("Enter carid to delete: ");
				int carid = sc.nextInt();
				databaseUtilities.deletecar(carid);
				break;
				
			case 3:
				System.out.println("Enter carid to view: ");
				carid = sc.nextInt();
				databaseUtilities.showCar(carid);
				
				break;
				
			case 4:
				databaseUtilities.showallcar();
				break;
				
			case 5:
				System.out.println("Exit");
				break;
				
			}
		}
	}
	public void showOptions()
	{
		System.out.println("1.To add car");
		System.out.println("2.To delete car");
		System.out.println("3.To show car");
		System.out.println("4.To show all cars");
		System.out.println("5.Exit");
		System.out.println("Enter choice: ");
	}

}
