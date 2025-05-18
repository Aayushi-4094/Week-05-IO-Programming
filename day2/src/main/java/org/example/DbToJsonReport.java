import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

public class DbToJsonReport {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/your_db";
        String username = "your_user";
        String password = "your_pass";

        String query = "SELECT id, name, age, department FROM employees";
        JSONArray jsonArray = new JSONArray();

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("id", rs.getInt("id"));
                obj.put("name", rs.getString("name"));
                obj.put("age", rs.getInt("age"));
                obj.put("department", rs.getString("department"));
                jsonArray.put(obj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("JSON Report:\n" + jsonArray.toString(4));
    }
}
