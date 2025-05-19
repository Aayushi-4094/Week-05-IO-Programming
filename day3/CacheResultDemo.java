import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CacheResult {}

class ExpensiveOperations {
    private Map<Integer, Integer> cache = new HashMap<>();

    @CacheResult
    public int computeSquare(int number) {
        if (cache.containsKey(number)) {
            System.out.println("Returning cached result...");
            return cache.get(number);
        }
        System.out.println("Computing result...");
        int result = number * number;
        cache.put(number, result);
        return result;
    }
}

public class CacheResultDemo {
    public static void main(String[] args) throws Exception {
        ExpensiveOperations ops = new ExpensiveOperations();
        System.out.println("Result: " + ops.computeSquare(5));
        System.out.println("Result: " + ops.computeSquare(5));
        System.out.println("Result: " + ops.computeSquare(6));
    }
}