import java.lang.reflect.Method;
import java.util.Scanner;

class MathOperations {
    public int add(int a, int b) { return a + b; }
    public int subtract(int a, int b) { return a - b; }
    public int multiply(int a, int b) { return a * b; }
}

public class DynamicMethodInvocation {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter method (add, subtract, multiply): ");
        String methodName = sc.nextLine();

        Method method = MathOperations.class.getMethod(methodName, int.class, int.class);
        Object result = method.invoke(new MathOperations(), 10, 5);
        System.out.println("Result: " + result);
    }
}
