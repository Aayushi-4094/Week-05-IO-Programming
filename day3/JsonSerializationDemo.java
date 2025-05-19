import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface JsonField {
    String name();
}

class User {
    @JsonField(name = "user_name")
    String username;

    @JsonField(name = "email_id")
    String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}

public class JsonSerializationDemo {
    public static String convertToJson(Object obj) throws IllegalAccessException {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{\n");
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(JsonField.class)) {
                field.setAccessible(true);
                JsonField annotation = field.getAnnotation(JsonField.class);
                jsonBuilder.append("  \"").append(annotation.name()).append("\": \"")
                           .append(field.get(obj)).append("\",\n");
            }
        }
        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }

    public static void main(String[] args) throws IllegalAccessException {
        User user = new User("aayushi", "aayu@example.com");
        String json = convertToJson(user);
        System.out.println("Generated JSON:\n" + json);
    }
}