// Import the library:
import io.airbrake.javabrake.Config;
import io.airbrake.javabrake.Notice;
import io.airbrake.javabrake.Notifier;

import java.io.IOException;

public class test {
    public static void main(String[] args) {
// todo adding this future to our project
        // Copy configuration lines:
        Config config = new Config();
        config.projectId = 389247;
        config.projectKey = "7f1b2aa6b3808f772aeecd5be4a48ad5";
        Notifier notifier = new Notifier(config);
        System.out.println("1");

        notifier.addFilter(
                (Notice notice) -> {
                    notice.setContext("environment", "production");
                    return notice;
                });
        System.out.println("2");
        try {
            int i = 1/0;
        } catch (ArithmeticException e) {
            notifier.report(e);
        }
        System.out.println("3");

    }
}