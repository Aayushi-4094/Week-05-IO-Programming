
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Todo {
    String task();
    String assignedTo();
    String priority() default "MEDIUM";
}

public class TodoAnnotationDemo {
    @Todo(task = "Refactor method", assignedTo = "Aayushi", priority = "HIGH")
    public void legacyMethod() {}

    @Todo(task = "Write unit tests", assignedTo = "Nilanjana")
    public void writeTests() {}

    public static void main(String[] args) {
        Method[] methods = TodoAnnotationDemo.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Todo.class)) {
                Todo todo = method.getAnnotation(Todo.class);
                System.out.println("Method: " + method.getName() +
                        ", Task: " + todo.task() +
                        ", Assigned To: " + todo.assignedTo() +
                        ", Priority: " + todo.priority());
            }
        }
    }
}
