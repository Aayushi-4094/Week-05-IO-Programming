import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SearchEmployeesByName {
    public static void main(String[] args) {
        String filePath = "employees.csv"; 
        String searchName = "Bob"; 
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); 
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[1].equalsIgnoreCase(searchName)) {
                    System.out.printf("Department: %s, Salary: %s%n", data[2], data[3]);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Employee not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
