import java.lang.reflect.Field;

class Person {
    private int age = 25;
}

public class AccessPrivateField {
    public static void main(String[] args) throws Exception {
        Person person = new Person();
        Field ageField = Person.class.getDeclaredField("age");
        ageField.setAccessible(true);

        System.out.println("Original age: " + ageField.get(person));
        ageField.set(person, 35);
        System.out.println("Modified age: " + ageField.get(person));
    }
}
