package system;

import io.qameta.allure.Allure;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Duration;

public class MisstatsSettings {
    private static final Duration timeout = Duration.ofSeconds(20);

    public static synchronized Duration timeout() {
        return timeout;
    }

    public static synchronized void fail(Throwable t) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            String stackTrace = sw.toString();

            Allure.attachment("Fail", t.toString());
            Allure.attachment("stackTrace", stackTrace);
        } catch (Throwable throwable) {
            System.out.println(throwable);
        }
        throw new AssertionError(t.toString());
    }
}
