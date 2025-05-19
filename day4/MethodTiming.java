import java.lang.reflect.Method;

class TaskRunner {
    public void performTask() throws InterruptedException {
        Thread.sleep(500); // Simulated delay
        System.out.println("Task done.");
    }
}

public class MethodTiming {
    public static void main(String[] args) throws Exception {
        Method method = TaskRunner.class.getMethod("performTask");
        TaskRunner runner = new TaskRunner();

        long start = System.nanoTime();
        method.invoke(runner);
        long end = System.nanoTime();

        System.out.println("Execution time: " + (end - start)/1_000_000 + " ms");
    }
}
