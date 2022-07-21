package system;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import java.io.FileInputStream;
import java.util.Properties;

public class Tears {

    public void tearUp(String resource) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/" + resource + ".properties"));
            String host = properties.getProperty("host");
            Selenide.open(host);
            Configuration.browserSize = "1920x1080";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tearDown() {
        try {
            Selenide.closeWebDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
