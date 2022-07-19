package debug;

import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OpenBrowserTest {

    @Test
    public void testTest() throws InterruptedException {
        Selenide.open("https://google.com");
        Thread.sleep(5000);
    }
}
