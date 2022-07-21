package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import system.MisstatsSettings;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class Header {
    private final static SelenideElement header = $x("//div[@class='header']");

    public SelenideElement btn_Войти = header.$x(".//a[contains(text(), 'Войти')]");
    public SelenideElement btn_Выйти = header.$x(".//a[@id='HeaderLogoutBtn']");

    public SelenideElement btn_Задачи = header.$x(".//a[@id='HeaderTasksBtn']");

    /**
     * Нажать "Войти" в шапке.
     */
    public Header clickGoToLogin() {
        btn_Войти
                .shouldBe(Condition.appear, MisstatsSettings.timeout())
                .click();
        btn_Войти.shouldHave(Condition.cssClass("router-link-active"));
        return this;
    }

    /**
     * Ожидание появления кнопки выход.
     */
    public Header btnLogoutIsAppear() {
        btn_Выйти
                .shouldBe(Condition.appear, MisstatsSettings.timeout())
                .shouldBe(Condition.visible);
        return this;
    }

    /**
     * Открыть вкладку "Задачи" в шапке.
     */
    public Header clickGoToTasks() {
        btn_Задачи
                .shouldBe(Condition.appear, MisstatsSettings.timeout())
                .shouldBe(Condition.visible)
                .click();
        return this;
    }

}
