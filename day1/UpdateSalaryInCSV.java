import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UpdateSalaryInCSV {
    public static void main(String[] args) {
        String inputFile = "employees.csv"; // Ensure this file exists
        String outputFile = "updated_employees.csv";
        List<String[]> updatedData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line = br.readLine();
            updatedData.add(line.split(","));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[2].equalsIgnoreCase("IT")) {
                    double salary = Double.parseDouble(data[3]);
                    salary *= 1.10; // Increase by 10%
                    data[3] = String.format("%.2f", salary);
                }
                updatedData.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(outputFile)) {
            for (String[] row : updatedData) {
                writer.append(String.join(",", row)).append("\n");
            }
            System.out.println("Updated data written to " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
