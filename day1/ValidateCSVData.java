import java.io.*;
import java.util.regex.Pattern;

public class ValidateCSVData {
    public static void main(String[] args) {
        String filePath = "employees.csv"; // Ensure this file exists
        Pattern emailPattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.\\w+$");
        Pattern phonePattern = Pattern.compile("^\\d{10}$");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String header = br.readLine();
            System.out.println("Invalid Records:");
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String email = data[2];
                String phone = data[3];
                if (!emailPattern.matcher(email).matches() || !phonePattern.matcher(phone).matches()) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
