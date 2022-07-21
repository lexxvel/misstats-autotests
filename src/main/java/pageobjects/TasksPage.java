package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import system.MisstatsSettings;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class TasksPage {
    private final static SelenideElement form = $x("//div[@class='TasksPage']");

    private final static SelenideElement btn_ДобавитьЗадачу = form.$x(".//button[@id='modalBtn' and contains(text(), 'Добавить')]");

    /**
     * Модальное окно добавления задачи
     */
    private final static SelenideElement modal_ДобавлениеЗадачи = $x("//div[@id='modal-addTask-center']");
    private final static SelenideElement input_НомерЗадачиModal = modal_ДобавлениеЗадачи.$x(".//input[@id='TaskNumberModal']");
    //private final static List<SelenideElement> cmb_СпринтModal = modal_ДобавлениеЗадачи.$$x(".//select[@test-id='TaskSprintModal']");
    private final static SelenideElement cmb_СпринтModal = modal_ДобавлениеЗадачи.$x(".//select[@test-id='TaskSprintModal']");
    private final static SelenideElement input_ОценкаТрудозатратModal = modal_ДобавлениеЗадачи.$x(".//input[@test-id='TaskPlanTimeModal']");
    private final static SelenideElement input_ФактическоеВремяModal = modal_ДобавлениеЗадачи.$x(".//input[@test-id='TaskFactTimeModal']");
    private final static SelenideElement btn_Сохранить = modal_ДобавлениеЗадачи.$x(".//button[contains(text(), 'Сохранить')]");



    /**
     * Кликнуть "Добавить" на форме добавления задач.
     */
    public TasksPage clickAddTask() {
        btn_ДобавитьЗадачу
                .should(Condition.appear, MisstatsSettings.timeout())
                .shouldBe(Condition.visible)
                .click();
        return this;
    }

    /**
     * Ожидание появления модалки добавления задачи.
     */
    public TasksPage modalAddTaskIsAppear() {
        modal_ДобавлениеЗадачи
                .should(Condition.appear, MisstatsSettings.timeout())
                .shouldBe(Condition.visible);
        return this;
    }

    /**
     * Проверка отображения модалки добавления задачи.
     */
    public boolean modalAddTaskIsDisplayed() {
         return modal_ДобавлениеЗадачи.isDisplayed();
    }

    /**
     * Заполнение поля "Номер задачи" в модальном окне добавления задачи.
     * @param taskNumber Номер задачи, например fillTaskNumber("12345")
     */
    public TasksPage fillTaskNumber(String taskNumber) {
        input_НомерЗадачиModal
                .shouldBe(Condition.visible)
                .setValue(taskNumber);
        input_НомерЗадачиModal.shouldHave(Condition.value(taskNumber));
        return this;
    }


    /**
     * Выбор спринта на форме добавления задачи.
     * @param sprint Спринт.
     */
    public TasksPage fillSprint(String sprint) throws InterruptedException {
        cmb_СпринтModal
                .click();
        cmb_СпринтModal.find(By.xpath(".//*[contains(text(),'" + sprint + "')]"))
                .should(Condition.appear, MisstatsSettings.timeout())
                .shouldBe(Condition.visible)
                .click();
        return this;
    }

    /**
     * Заполнение поля "Оценка трудозатрат" на форме добавления задачи.
     * @param planTime оценка трудозатрат, SP
     */
    public TasksPage fillPlanTime(String planTime) {
        input_ОценкаТрудозатратModal.shouldBe(Condition.appear, MisstatsSettings.timeout())
                .shouldBe(Condition.visible)
                .setValue(planTime);
        return this;
    }

    /**
     * Нажать "Сохранить" в модальном окне добавления задачи.
     */
    public TasksPage clickSaveModal() {
        Selenide.sleep(2000);
        btn_Сохранить.shouldBe(Condition.appear, MisstatsSettings.timeout())
                .shouldBe(Condition.enabled)
                .click();
        modal_ДобавлениеЗадачи
                .should(Condition.disappear, MisstatsSettings.timeout())
                .shouldNotBe(Condition.visible);
        return this;
    }

    public TasksPage findTask(String taskNumber) {
        form.$x(".//div[@class='task-card']//p[contains(text(), '" + taskNumber + "')]")
                .shouldBe(Condition.appear, MisstatsSettings.timeout())
                .shouldBe(Condition.visible);
        return this;
    }
}
