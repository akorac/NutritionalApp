
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class PartC {

	public static void main(String args[]) {

	        // added the 2 variables for user interaction
	        int choice;
	        String select;

	        Scanner scan = new Scanner(System.in);
	        // the 8 queries + description you listed will go here

	        // Display a nice name for your application along with a short welcome message describing the goal of the database
	       
	        System.out.println("Hello. Welcome to our Nutrition application.");
	        System.out.println("Our goal here is to provide users with data of food calories. Users may discover their desired BMI for weight loss/gain.");

	        try {
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalProject",
						"root","Pranesh3194");

				Statement stmt = con.createStatement();
			System.out.println(" ");
	        System.out.println("Select the option to see result.\n"
	        		+ "1. Display the results here! \n"
	        		+ "2. Save the results to a file! ");
	        

	        choice = scan.nextInt();
	     
	        while(choice != 9) {
	        		
	        		System.out.println("Select the query number to exequte query.\n"
	      					+ "1. This query find the name of the food whose calories is less or equal to 100.\n"
	      					+ "2. This query find out the user name and amount of weight user wants to gain.\n"
	      					+ "3. This query find out the average weight of the user from age between 25 to 50.\n"
	      					+ "4. This query find out the user whose weight it more than 175.\n"
	      					+ "5. This query find the food name and  calories whose carbs is less than 2.\n"
	      					+ "6. This query find out the food name whose calories less than average calories from food whose calories is less than 100.\n"
	      					+ "7. This query find out the user name, target weight of the user.\n"
	      					+ "8. This query find out the user name whose BMI is less than 25.0\n");

	        		int choice1 = scan.nextInt();
	        		choice=choice1;
	
					switch (choice) {
					case 1:
						ResultSet one = stmt.executeQuery("select commonName from food as f inner"
								+ " join tracking as t on f.foodId = t.foodId where calories <= 100;");

						while (one.next()) {
							String newOne = one.getString(1);
							System.out.println(newOne);
						}

						break;
					case 2:
						ResultSet two = stmt
								.executeQuery("select name, weightGain from user as u inner join nutritionGoal "
										+ "as n on u.userId = n.userId where weightGain > 0;");

						while (two.next()) {
							String name = two.getString(1);
							int weightGain = two.getInt(2);
							System.out.println(name + " " + weightGain);
						}
						break;

					case 3:
						ResultSet three = stmt.executeQuery(
								"select avg(weight) as avgWeight from user where age >= 25" + " AND age <= 50;");

						while (three.next()) {
							int avgWeight = three.getInt(1);
							System.out.println("Average Weight: " + avgWeight);
						}
						break;
					case 4:
						ResultSet four = stmt
								.executeQuery("select name,height,weight,age from user where weight >= 175;");

						while (four.next()) {
							String name = four.getString(1);
							double height = four.getDouble(2);
							double weight = four.getDouble(3);
							int age = four.getInt(4);
							System.out.println("name: " + name + " " + "Height: " + height + " " + "Weight: " + weight
									+ " " + "Age: " + age);
						}
						break;
					case 5:
						ResultSet five = stmt
								.executeQuery("select commonName, calories from food as f inner join tracking as"
										+ " t on f.foodId = t.foodId where carbs < 2; ");
						while (five.next()) {
							String commonName = five.getString(1);
							double avgCal = five.getDouble(2);
							System.out.println("Common Name: " + commonName + "Avg Calories: " + avgCal);
						}
						break;
					case 6:
						ResultSet six = stmt.executeQuery("select commonName from food as f inner join "
								+ "tracking as t on f.foodId = t.foodId where "
								+ "calories < (select avg(calories) as avgCal from tracking where calories < 100);");
						while (six.next()) {
							String commonName = six.getString(1);
							System.out.println(commonName);
						}
						break;
					case 7:
						ResultSet seven = stmt.executeQuery(
								"select name, (weight + weightGain) from user as u inner join nutritionGoal "
										+ "as n on u.userId = n.userId where weightgain > 0;");
						while (seven.next()) {
							String name = seven.getString(1);
							double total = seven.getDouble(2);
							System.out.println("Name: " + name + "Target Weight: " + total);
						}
						break;
					case 8:
						ResultSet eight = stmt.executeQuery(
								"select name from user where (weight*0.45) /((height*(0.0254)) *  (height*(0.0254))) < 25.0  group by weight;");

						while (eight.next()) {
							String name = eight.getString(1);
							System.out.println(name);
						}
						break;
					case 9:
						System.exit(0);
						break;
					}
					// System.out.println("You selected " + select);

	        }
	        
			} catch (Exception e) {
				System.out.println(e);
			}
	        }
	
	}

