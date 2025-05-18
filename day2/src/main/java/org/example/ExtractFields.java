package org.example;

import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ExtractFields {
    public static void main(String[] args) throws Exception {
        InputStream is = ExtractFields.class.getClassLoader().getResourceAsStream("student.json");

        if (is == null) {
            throw new RuntimeException("student.json not found in resources!");
        }

        String content = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        JSONObject json = new JSONObject(content);

        String name = json.optString("name");
        String email = json.optString("email");

        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
    }
}
