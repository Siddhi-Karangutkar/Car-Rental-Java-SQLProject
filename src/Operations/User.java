package Operations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import DatabasePackage.DatabaseUtilities;

public class User {
	
	public User(Scanner sc,DatabaseUtilities databaseUtilities)
	{
		handleUserOperations(sc,databaseUtilities);
	}
	
	public void handleUserOperations(Scanner sc,DatabaseUtilities databaseUtilities)
	{
		int choice=0;
		float amount=0;
		while(choice!=5)
		{
			showOptions();
			choice=sc.nextInt();
			switch(choice)
			{
			case 1:
				System.out.println("Enter username: ");
				String name = sc.next();
				System.out.println("Enter age: ");
				int age = sc.nextInt();
				if(age>18)
				{
					System.out.println("Enter Balance: ");
					 amount = sc.nextFloat();
					databaseUtilities.UserDetails(name,age,amount);
				}
				else
				{
					System.out.println("Age should be greater than 18");
				}
				break;
				
			case 2:
				System.out.println("Enter userid to delete: ");
				int userid = sc.nextInt();
				databaseUtilities.deleteuser(userid);
				break;
				
			case 3:
				System.out.println("Enter userid to show: ");
				userid = sc.nextInt();
				databaseUtilities.showuser(userid);
				break;
				
			case 4:
				databaseUtilities.showalluser();
				break;
				
			case 5:
				System.out.println("Exit");
				break;
				
				
			}
		}
	}
	
	public void showOptions()
	{
		System.out.println("1.To add User");
		System.out.println("2.To delete User");
		System.out.println("3.To show User");
		System.out.println("4.To show all Users");
		System.out.println("5.Exit");
		System.out.println("Enter your choice: ");
	}

}
