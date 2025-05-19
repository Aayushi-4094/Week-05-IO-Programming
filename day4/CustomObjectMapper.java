import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

class User {
    public String name;
    public int age;
}

public class CustomObjectMapper {
    public static <T> T toObject(Class<T> clazz, Map<String, Object> properties) throws Exception {
        T instance = clazz.getDeclaredConstructor().newInstance();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (properties.containsKey(field.getName())) {
                field.set(instance, properties.get(field.getName()));
            }
        }
        return instance;
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Aayushi");
        data.put("age", 22);

        User user = toObject(User.class, data);
        System.out.println("User{name='" + user.name + "', age=" + user.age + "}");
    }
}
