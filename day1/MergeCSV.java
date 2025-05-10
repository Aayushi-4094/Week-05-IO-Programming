import java.io.*;
import java.util.*;

public class MergeCSV {
    public static void main(String[] args) throws IOException {
        String file1 = "students1.csv";
        String file2 = "students2.csv";
        String outputFile = "merged_students.csv";
        
        Map<String, String> studentDetails = new HashMap<>();
        BufferedReader br1 = new BufferedReader(new FileReader(file1));
        String line;
        while ((line = br1.readLine()) != null) {
            String[] data = line.split(",");
            studentDetails.put(data[0], line);
        }
        br1.close();
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        BufferedReader br2 = new BufferedReader(new FileReader(file2));
        
        writer.write("ID,Name,Age,Marks,Grade\n");
        
        while ((line = br2.readLine()) != null) {
            String[] data = line.split(",");
            String id = data[0];
            if (studentDetails.containsKey(id)) {
                String studentInfo = studentDetails.get(id);
                String mergedLine = studentInfo + "," + data[1] + "," + data[2];
                writer.write(mergedLine + "\n");
            }
        }
        
        br2.close();
        writer.close();
    }
}
