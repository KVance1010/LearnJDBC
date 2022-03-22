package learnjdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {

	public static void main(String[] args) {

		/* Creating the build path for sql. You can find this in the sql workbench. Right click on the connection and 
		it will tell you what the build path is. You can also put in the ip address */
		String url = "jdbc:mysql://localhost:3306/employees_database";
		try {
			// Establish connection object
			Connection conn = DriverManager.getConnection(url, "root", "OZuzz&FA^qkfHKJkU9u=8v4=eS+Jt/8%");
			
			// Create a statement object to send to the database
			Statement statement = conn.createStatement();
			
			// Execute the statement object
            ResultSet resultSet = statement.executeQuery("select*from employees_tbl");	
            
            // Process the result - also will calculate salary total
            int salaryTotal =0;
            while (resultSet.next()) {
            	System.out.print(resultSet.getString("name"));
            	System.out.print(" ");
            	System.out.println(resultSet.getInt("salary"));
            	salaryTotal += resultSet.getInt("salary");
            }
            // prints the total of all salaries.
            System.out.println("The salary total is $" + salaryTotal);
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
