
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class User {
    @MaxLength(10)
    String username;

    public User(String username) throws Exception {
        this.username = username;
        Field field = this.getClass().getDeclaredField("username");
        if (field.isAnnotationPresent(MaxLength.class)) {
            int max = field.getAnnotation(MaxLength.class).value();
            if (username.length() > max) {
                throw new IllegalArgumentException("Username exceeds max length of " + max);
            }
        }
    }
}

public class MaxLengthValidator {
    public static void main(String[] args) throws Exception {
        try {
            new User("shortName");
            new User("ThisIsTooLong");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }
}
