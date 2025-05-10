import java.io.*;
import java.util.*;

public class DetectDuplicates {
    public static void main(String[] args) throws IOException {
        String filePath = "students.csv";
        Set<String> seen = new HashSet<>();
        List<String> duplicates = new ArrayList<>();
        
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            String id = data[0];
            if (seen.contains(id)) {
                duplicates.add(line);
            } else {
                seen.add(id);
            }
        }
        
        br.close();
        
        System.out.println("Duplicate records:");
        for (String duplicate : duplicates) {
            System.out.println(duplicate);
        }
    }
}
