package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.List;

class Person {
    String name;
    int age;
    Person(String name, int age) { this.name = name; this.age = age; }
}

public class ListToJson {
    public static void main(String[] args) {
        List<Person> people = List.of(new Person("Alice", 22), new Person("Bob", 30));
        JSONArray jsonArray = new JSONArray();

        for (Person p : people) {
            JSONObject obj = new JSONObject();
            obj.put("name", p.name);
            obj.put("age", p.age);
            jsonArray.put(obj);
        }

        System.out.println(jsonArray.toString(2));
    }
}
