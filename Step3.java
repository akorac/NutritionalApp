import java.sql.*;
import java.io.*;
import java.util.Scanner;

public class Step3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Calorie Counter");
		System.out.println("This app will give the data of food");
		System.out.println("");
	
		int size = 600;
//		Connection con = null;
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalProject","root","Pranesh3194");

			Statement stmt = con.createStatement();
			
			
			con.setAutoCommit(false);
			
			String sql = "INSERT INTO food(foodId, commonName) VALUES(?,?)";
			String sql1 = "INSERT INTO tracking(foodId, calories,fat,protein,carbs) VALUES (?,?,?,?,?)";
			String sql2 = "INSERT INTO user(userId, name,height,weight,age) VALUES (?,?,?,?,?)";
			String sql3 =  "INSERT INTO nutritiongoal(userId,weightLoss,weightGain,athlete) VALUES (?,?,?,?)";
			
			PreparedStatement st = con.prepareStatement(sql);
			PreparedStatement st1 = con.prepareStatement(sql1);
			PreparedStatement st2 = con.prepareStatement(sql2);
			PreparedStatement st3 = con.prepareStatement(sql3);
			
			BufferedReader br = new BufferedReader(new FileReader("food.csv"));
			BufferedReader br1 = new BufferedReader(new FileReader("tracking.csv"));
			BufferedReader br2 = new BufferedReader(new FileReader("MOCK_DATA.csv"));
			BufferedReader br3 = new BufferedReader(new FileReader("Goal.csv"));
			
			String headerText = null;
			
			int count = 0;
/*			
			// Skip the heading
			 br.readLine(); 
			 
			while((headerText = br.readLine()) != null) {
				String[] data = headerText.split(",");
				String foodId = data[0];
				String commonName = data[1];
				
				int id = Integer.parseInt(foodId);
				st.setInt(1, id);
				st.setString(2, commonName);
				st.addBatch();
				
				if(count % size == 0) {
					st.executeBatch();
				}
			}
		
				//Skip the heading
				br1.readLine();
			 
				while((headerText = br1.readLine()) != null) {
					String[] data = headerText.split(",");
					String foodId = data[0];
					String calories = data[1];
					String fat = data[2];
					String protein = data[3];
					String carb = data[4];
					
				
					int id = Integer.parseInt(foodId);
					st1.setInt(1, id);
					
					float fcal = Float.parseFloat(calories);					
					st1.setFloat(2, fcal);
					
					float ffat = Float.parseFloat(fat);
					st1.setFloat(3, ffat);
					
					float fprotein = Float.parseFloat(protein);
					st1.setFloat(4, fprotein);
					
					float fcarb = Float.parseFloat(carb);
					st1.setFloat(5, fcarb);					
					
					st1.addBatch();
					
					if(count % size == 0) {
						st1.executeBatch();
					}
				}
				
				br2.readLine();
				 
				while((headerText = br2.readLine()) != null) {
					String[] data = headerText.split(",");
					String userId = data[0];
					String name = data[1];
					String height = data[2];
					String weight = data[3];
					String age = data[4];
					
				
					int id = Integer.parseInt(userId);
					st2.setInt(1, id);
					
					st2.setString(2, name);
					
					float fheight = Float.parseFloat(height);					
					st2.setFloat(3, fheight);
					
					float fweight = Float.parseFloat(weight);
					st2.setFloat(4, fweight);
					
					int iage = Integer.parseInt(age);
					st2.setInt(5, iage);
					
					
					
					st2.addBatch();
					
					if(count % size == 0) {
						st2.executeBatch();
					}
				}
				
*/				
				br3.readLine();
				 
				while((headerText = br3.readLine()) != null) {
					String[] data = headerText.split(",");
					String userId = data[0];
					String weightLoss = data[1];
					String weightGain = data[2];
					String athlete = data[3];
					
					int id = Integer.parseInt(userId);
					st3.setInt(1, id);
					
					
					float fweightLoss = Float.parseFloat(weightLoss);					
					st3.setFloat(2, fweightLoss);
					
					float fweightGain = Float.parseFloat(weightGain);
					st3.setFloat(3, fweightGain);
					
					int iathlete = Integer.parseInt(athlete);
					st3.setInt(4, iathlete);
					
					
					
					st3.addBatch();
					
					if(count % size == 0) {
						st3.executeBatch();
					}
				}
			
			
			
			br.close();
			br1.close();
			br2.close();
			br3.close();
			
			st.executeBatch();
			st1.executeBatch();
			st2.executeBatch();
			st3.executeBatch();
			
			con.commit();
			
			con.close();
			
			
		}catch(Exception e){
			System.out.println(e);
		}

	}

}
