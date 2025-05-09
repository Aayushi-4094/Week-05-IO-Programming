import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FilterHigherMarks {
    public static void main(String[] args) {
        String filePath = "employees.csv"; 
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); 
            System.out.println(line);
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int Salary = Integer.parseInt(data[3]);
                if (Salary > 60000) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
