import java.io.FileWriter;
import java.io.IOException;

public class WriteDataToCSV {
    public static void main(String[] args) {
        String filePath = "employees.csv";
        String[] headers = {"ID", "Name", "Department", "Salary"};
        String[][] data = {
                {"1", "Alice", "HR", "50000"},
                {"2", "Bob", "IT", "60000"},
                {"3", "Charlie", "Finance", "55000"},
                {"4", "Diana", "Marketing", "52000"},
                {"5", "Ethan", "IT", "62000"}
        };

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append(String.join(",", headers)).append("\n");
            for (String[] row : data) {
                writer.append(String.join(",", row)).append("\n");
            }
            System.out.println("Data written to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
