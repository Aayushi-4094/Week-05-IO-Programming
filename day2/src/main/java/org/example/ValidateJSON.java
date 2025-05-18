package org.example;

import org.json.JSONObject;

public class ValidateJSON {
    public static void main(String[] args) {
        JSONObject json = new JSONObject();
        json.put("name", "Alice");
        json.put("age", 25);
        json.put("email", "alice@example.com");

        if (json.has("name") && json.has("age") && json.has("email")) {
            System.out.println("JSON is structurally valid.");
        } else {
            System.out.println("Invalid JSON structure.");
        }
    }
}
