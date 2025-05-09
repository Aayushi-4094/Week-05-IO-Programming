import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSVAndPrint {
    public static void main(String[] args) {
        String filePath = "students.csv"; // Ensure this file exists
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                System.out.printf("ID: %s, Name: %s, Age: %s, Marks: %s%n",
                        data[0], data[1], data[2], data[3]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
