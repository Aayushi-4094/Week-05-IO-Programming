package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;

class Student {
    String name;
    int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("age", age);
        return obj;
    }
}

public class ListToJSONArray {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Alice", 22),
                new Student("Bob", 28)
        );

        JSONArray jsonArray = new JSONArray();
        for (Student s : students) {
            jsonArray.put(s.toJSON());
        }

        System.out.println(jsonArray.toString(2));
    }
}
