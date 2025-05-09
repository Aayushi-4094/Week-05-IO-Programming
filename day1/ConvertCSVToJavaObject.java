import java.io.*;
import java.util.*;

class Student {
    String id;
    String name;
    int age;
    int marks;

    public Student(String id, String name, int age, int marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Age: %d, Marks: %d", id, name, age, marks);
    }
}

public class ConvertCSVToJavaObject {
    public static void main(String[] args) {
        String filePath = "students.csv"; // Ensure this file exists
        List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Student student = new Student(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]));
                students.add(student);
            }

            for (Student s : students) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
