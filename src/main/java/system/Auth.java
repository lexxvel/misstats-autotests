package system;

import pageobjects.Login;

import java.io.FileInputStream;
import java.util.Properties;

public class Auth {
    public void auth(String resource) {
        try {
            Login login = new Login();
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/" + resource + ".properties"));

            String userLogin = properties.getProperty("login");
            String userPassword = properties.getProperty("password");

            login
                    .fillUserLogin(userLogin)
                    .fillUserPassword(userPassword)
                    .clickLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getHostUrl(String resource) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/" + resource + ".properties"));
            return properties.getProperty("host");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
