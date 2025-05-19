import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RoleAllowed {
    String value();
}

class AccessManager {
    private String currentUserRole;

    public AccessManager(String role) {
        this.currentUserRole = role;
    }

    @RoleAllowed("ADMIN")
    public void deleteAccount() {
        System.out.println("Account deleted!");
    }

    public void executeIfAllowed(Method method) throws Exception {
        if (method.isAnnotationPresent(RoleAllowed.class)) {
            RoleAllowed roleAllowed = method.getAnnotation(RoleAllowed.class);
            if (currentUserRole.equals(roleAllowed.value())) {
                method.invoke(this);
            } else {
                System.out.println("Access Denied!");
            }
        } else {
            method.invoke(this);
        }
    }
}

public class RoleAccessControl {
    public static void main(String[] args) throws Exception {
        AccessManager admin = new AccessManager("ADMIN");
        AccessManager user = new AccessManager("USER");

        Method method = AccessManager.class.getMethod("deleteAccount");

        System.out.println("Admin trying:");
        admin.executeIfAllowed(method);

        System.out.println("User trying:");
        user.executeIfAllowed(method);
    }
}