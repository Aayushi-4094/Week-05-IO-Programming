import java.lang.reflect.Field;

class Product {
    public String name = "Book";
    public double price = 19.99;
}

public class ObjectToJson {
    public static String toJson(Object obj) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            sb.append("\"").append(field.getName()).append("\":");
            Object value = field.get(obj);
            if (value instanceof String) {
                sb.append("\"").append(value).append("\"");
            } else {
                sb.append(value);
            }
            sb.append(",");
        }
        if (sb.charAt(sb.length() - 1) == ',') sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        Product product = new Product();
        System.out.println(toJson(product));
    }
}
