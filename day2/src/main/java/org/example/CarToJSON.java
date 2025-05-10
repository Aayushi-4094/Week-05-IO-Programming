package org.example;

import org.json.JSONObject;

class Car {
    String model;
    String brand;
    int year;

    public Car(String model, String brand, int year) {
        this.model = model;
        this.brand = brand;
        this.year = year;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("model", model);
        json.put("brand", brand);
        json.put("year", year);
        return json;
    }
}

public class CarToJSON {
    public static void main(String[] args) {
        Car car = new Car("Model S", "Tesla", 2023);
        JSONObject jsonCar = car.toJSON();
        System.out.println(jsonCar.toString(2));
    }
}
