
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface LogExecutionTime {}

public class ExecutionTimeLogger {

    @LogExecutionTime
    public void heavyTask() {
        try { Thread.sleep(100); } catch (InterruptedException e) {}
    }

    public static void main(String[] args) throws Exception {
        ExecutionTimeLogger obj = new ExecutionTimeLogger();
        Method[] methods = obj.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(LogExecutionTime.class)) {
                long start = System.nanoTime();
                method.invoke(obj);
                long end = System.nanoTime();
                System.out.println("Execution Time of " + method.getName() + ": " + (end - start) + " ns");
            }
        }
    }
}
