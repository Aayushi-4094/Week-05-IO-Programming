// File: Exercise3_SuppressWarnings.java
import java.util.ArrayList;
import java.util.List;

public class Exercise3_SuppressWarnings {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        List list = new ArrayList(); // No generic type
        list.add("Hello");
        list.add(42);
        System.out.println(list);
    }
}
