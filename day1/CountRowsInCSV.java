import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountRowsInCSV {
    public static void main(String[] args) {
        String filePath = "employees.csv";
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            while (br.readLine() != null) {
                count++;
            }
            System.out.println("Total records (excluding header): " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
