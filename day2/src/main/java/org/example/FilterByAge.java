package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FilterByAge {
    public static void main(String[] args) throws Exception {
        String content = new String(Files.readAllBytes(Paths.get("people.json")));
        JSONArray people = new JSONArray(content);

        for (int i = 0; i < people.length(); i++) {
            JSONObject person = people.getJSONObject(i);
            if (person.getInt("age") > 25) {
                System.out.println(person.toString(2));
            }
        }
    }
}
