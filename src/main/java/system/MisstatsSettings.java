package system;

import java.time.Duration;

public class MisstatsSettings {
    private static final Duration timeout = Duration.ofSeconds(20);

    public static synchronized Duration timeout() {
        return timeout;
    }
}
