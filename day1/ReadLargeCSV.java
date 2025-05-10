import java.io.*;
import java.util.*;

public class ReadLargeCSV {
    public static void main(String[] args) throws IOException {
        String filePath = "large_file.csv";
        int chunkSize = 100;
        int recordCount = 0;

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = br.readLine()) != null) {
            List<String> chunk = new ArrayList<>();
            for (int i = 0; i < chunkSize && line != null; i++) {
                chunk.add(line);
                line = br.readLine();
            }
            // Process the chunk (e.g., print or analyze)
            System.out.println("Processed " + chunk.size() + " records.");
            recordCount += chunk.size();
        }
        br.close();
        System.out.println("Total records processed: " + recordCount);
    }
}
