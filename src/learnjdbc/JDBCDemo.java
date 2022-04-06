package learnjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {

	public static void main(String[] args) {

		/*
		 * Creating the build path for sql. You can find this in the sql workbench.
		 * Right click on the connection and it will tell you what the build path is.
		 * You can also put in the ip address
		 */
		String url = "jdbc:mysql://localhost:3306/employees_database";
		try {

			// Establish connection object
			Connection conn = DriverManager.getConnection(url, "root", "OZuzzFA^qkfHKJkU9u=8v4=eS+Jt/8%");

			// Create a statement object to send to the database
			Statement statement = conn.createStatement();

			int rowsAffected = 0;

			// Adds an element to the database
			try {
				rowsAffected = statement.executeUpdate("insert into employees_tbl (id, name, dept, salary)"
						+ "values (900, 'Robert', 'Sales', 4000);");
				System.out.println("executed an insert statement changed " + rowsAffected + " rows");
			} catch (Exception e) {
				System.out.println("no changes made when trying to add to table");
			}

			// Delete an element to the database
			try {
				rowsAffected = statement.executeUpdate("delete from employees_tbl where name = 'robert';");
				System.out.println("executed a delete statement changed " + rowsAffected + " rows");
			} catch (Exception e) {
				System.out.println("no changes made when trying to remove from table");
			}

			// Update an element to the database
			try {
				rowsAffected = statement.executeUpdate("UPDATE employees_tbl SET salary = 5500 WHERE id = 700;");
				System.out.println("executed an update statement changed " + rowsAffected + " rows");
			} catch (Exception e) {
				System.out.println("no changes made when trying to update the table");
			}
			
			// Execute the statement object
			ResultSet resultSet = statement.executeQuery("select*from employees_tbl");

			// Process the result - also will calculate salary total
			int salaryTotal = 0;
			while (resultSet.next()) {
				System.out.print(resultSet.getString("name"));
				System.out.print(" ");
				System.out.println(resultSet.getInt("salary"));
				salaryTotal += resultSet.getInt("salary");
			}
			// prints the total of all salaries.
			System.out.println("The salary total is $" + salaryTotal);

		} catch (SQLException e) {
			System.out.println("Error while trying to insert");
		}
	}

}
