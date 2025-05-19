import java.lang.reflect.Field;

class Configuration {
    private static String API_KEY = "OLD_KEY";
}

public class ModifyStaticField {
    public static void main(String[] args) throws Exception {
        Field field = Configuration.class.getDeclaredField("API_KEY");
        field.setAccessible(true);

        System.out.println("Original API_KEY: " + field.get(null));
        field.set(null, "NEW_SECRET_KEY");
        System.out.println("Modified API_KEY: " + field.get(null));
    }
}
