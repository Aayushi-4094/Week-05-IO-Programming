// File: Exercise5_RepeatableAnnotation.java
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(BugReports.class)
@interface BugReport {
    String description();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface BugReports {
    BugReport[] value();
}

class DevTools {
    @BugReport(description = "NullPointerException on line 20")
    @BugReport(description = "Slow response for edge case")
    public void buggyMethod() {
        // implementation
    }
}

public class Exercise5_RepeatableAnnotation {
    public static void main(String[] args) throws Exception {
        Method method = DevTools.class.getMethod("buggyMethod");
        BugReport[] reports = method.getAnnotationsByType(BugReport.class);
        for (BugReport br : reports) {
            System.out.println("Bug: " + br.description());
        }
    }
}
