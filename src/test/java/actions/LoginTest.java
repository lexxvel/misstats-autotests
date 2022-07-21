package actions;

import system.Tears;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageobjects.Header;
import system.Auth;

public class LoginTest {

    Tears tears;
    Auth auth;
    Header header;
    String resource = "misstatsadmin";

    @Test
    void entryPoint() {
        try {
            tears = new Tears();
            auth = new Auth();

            tears.tearUp(resource);

            step1_OpenLoginPage();
            step2_Login();
            step3_CheckIsLoggedIn();

        } finally {
            tears.tearDown();
        }
    }

    void step1_OpenLoginPage() {
        header = new Header();
        header.clickGoToLogin();
    }

    void step2_Login() {
        auth.auth(resource);
    }

    //TODO: Сделать использованные элементы приватными, метод возвращающий true/false
    void step3_CheckIsLoggedIn() {
        header.btnLogoutIsAppear();
        Assertions.assertTrue(header.btn_Выйти.isDisplayed());
        Assertions.assertFalse(header.btn_Войти.isDisplayed());
        Assertions.assertTrue(header.btn_Задачи.isDisplayed());
    }
}
