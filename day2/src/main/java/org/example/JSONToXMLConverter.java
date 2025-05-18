package org.example;

public import org.json.JSONObject;
import org.json.XML;

public class JSONToXMLConverter {
    public static void main(String[] args) {
        String jsonString = "{ \"employee\": { \"name\": \"Aayushi\", \"age\": 23, \"department\": \"IT\" } }";

        JSONObject jsonObject = new JSONObject(jsonString);
        String xml = XML.toString(jsonObject);

        System.out.println("Converted XML:\n" + xml);
    }
}
 {
    
}
