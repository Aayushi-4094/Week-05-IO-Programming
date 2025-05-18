package org.example;

import org.json.JSONObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class ReadJSON {
    public static void main(String[] args) throws Exception {
        InputStream is = ReadJSON.class.getClassLoader().getResourceAsStream("student.json");
        JSONObject json = new JSONObject(new String(is.readAllBytes(), StandardCharsets.UTF_8));

        Iterator<String> keys = json.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            System.out.println(key + ": " + json.get(key));
        }
    }
}
