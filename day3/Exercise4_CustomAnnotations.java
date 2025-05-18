// File: Exercise4_CustomAnnotation.java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface TaskInfo {
    String priority() default "MEDIUM";
    String assignedTo();
}

class TaskManager {
    @TaskInfo(priority = "HIGH", assignedTo = "Aayushi")
    public void criticalTask() {
        System.out.println("Executing critical task");
    }
}

public class Exercise4_CustomAnnotations {
    public static void main(String[] args) throws Exception {
        for (Method method : TaskManager.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(TaskInfo.class)) {
                TaskInfo info = method.getAnnotation(TaskInfo.class);
                System.out.println("Method: " + method.getName());
                System.out.println("Priority: " + info.priority());
                System.out.println("Assigned To: " + info.assignedTo());
            }
        }
    }
}
