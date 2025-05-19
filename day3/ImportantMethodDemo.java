
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ImportantMethod {
    String level() default "HIGH";
}

public class ImportantMethodDemo {
    @ImportantMethod
    public void processPayment() {
        System.out.println("Processing payment...");
    }

    @ImportantMethod(level = "MEDIUM")
    public void generateInvoice() {
        System.out.println("Generating invoice...");
    }

    public void printAnnotationDetails() throws Exception {
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(ImportantMethod.class)) {
                ImportantMethod imp = method.getAnnotation(ImportantMethod.class);
                System.out.println(method.getName() + " -> Level: " + imp.level());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ImportantMethodDemo obj = new ImportantMethodDemo();
        obj.printAnnotationDetails();
    }
}
