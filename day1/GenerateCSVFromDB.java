import java.io.*;
import java.sql.*;

public class GenerateCSVFromDB {
    public static void main(String[] args) throws SQLException, IOException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/your_database";
        String user = "your_username";
        String password = "your_password";
        String outputFile = "employee_report.csv";
        
        Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT EmployeeID, Name, Department, Salary FROM Employees");
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        writer.write("Employee ID,Name,Department,Salary\n");
        
        while (rs.next()) {
            int empId = rs.getInt("EmployeeID");
            String name = rs.getString("Name");
            String department = rs.getString("Department");
            double salary = rs.getDouble("Salary");
            
            writer.write(empId + "," + name + "," + department + "," + salary + "\n");
        }
        
        rs.close();
        stmt.close();
        conn.close();
        writer.close();
    }
}
