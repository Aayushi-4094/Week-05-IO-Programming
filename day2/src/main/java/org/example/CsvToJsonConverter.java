import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class CsvToJsonConverter {
    public static void main(String[] args) {
        String csvFile = "data.csv"; // example CSV: name,age,dept\nJohn,30,IT\nAlice,28,HR
        String line;
        String[] headers = null;
        JSONArray jsonArray = new JSONArray();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int lineNum = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (lineNum == 0) {
                    headers = values;
                } else {
                    JSONObject obj = new JSONObject();
                    for (int i = 0; i < headers.length; i++) {
                        obj.put(headers[i], values[i]);
                    }
                    jsonArray.put(obj);
                }
                lineNum++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Converted JSON:\n" + jsonArray.toString(4));
    }
}
