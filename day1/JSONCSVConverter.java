import com.opencsv.*;
import org.json.*;
import org.json.JSONException;

import java.io.*;
import java.util.*;

public class JSONCSVConverter {
    public static void main(String[] args) throws IOException {
        String jsonFile = "students.json";
        String csvFile = "students.csv";
        
        BufferedReader reader = new BufferedReader(new FileReader(jsonFile));
        StringBuilder jsonString = new StringBuilder();
        String line;
        
        while ((line = reader.readLine()) != null) {
            jsonString.append(line);
        }
        
        reader.close();
        
        JSONArray jsonArray = new JSONArray(jsonString.toString());
        CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFile));
        
        List<String[]> records = new ArrayList<>();
        records.add(new String[]{"ID", "Name", "Age"});  // headers
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String id = obj.getString("ID");
            String name = obj.getString("Name");
            String age = obj.getString("Age");
            records.add(new String[]{id, name, age});
        }
        
        csvWriter.writeAll(records);
        csvWriter.close();
        
        // CSV to JSON
        String csvFileInput = "students.csv";
        BufferedReader csvReader = new BufferedReader(new FileReader(csvFileInput));
        String[] headers = csvReader.readLine().split(",");
        JSONArray csvJsonArray = new JSONArray();
        
        while ((line = csvReader.readLine()) != null) {
            String[] data = line.split(",");
            JSONObject obj = new JSONObject();
            for (int i = 0; i < headers.length; i++) {
                obj.put(headers[i], data[i]);
            }
            csvJsonArray.put(obj);
        }
        
        csvReader.close();
        FileWriter jsonFileWriter = new FileWriter("students_output.json");
        jsonFileWriter.write(csvJsonArray.toString());
        jsonFileWriter.close();
    }
}
