package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import system.MisstatsSettings;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

/**
 * класс для работы с формой входа.
 */
public class Login {
    private final static SelenideElement form = $x("//div[@class='loginForm']");

    public SelenideElement input_Логин = form.$x(".//input[@test-id='UserLogin']");
    public SelenideElement input_Пароль = form.$x(".//input[@test-id='UserPassword']");

    private final static SelenideElement btn_Войти = form.$x(".//button[@test-id='Login']");

    public Login fillUserLogin(String login) {
        input_Логин
                .shouldBe(Condition.appear, MisstatsSettings.timeout())
                .setValue(login);
        return this;
    }

    public Login fillUserPassword(String pass) {
        input_Пароль
                .shouldBe(Condition.appear, MisstatsSettings.timeout())
                .setValue(pass);
        return this;
    }

    public Login clickLogin() {
        btn_Войти
                .shouldBe(Condition.visible)
                .click();
        return this;
    }
}
