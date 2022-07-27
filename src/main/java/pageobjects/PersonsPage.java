package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import system.MisstatsSettings;

import static com.codeborne.selenide.Selenide.$x;

public class PersonsPage {
    private static final SelenideElement form = $x("//div[@class='personsWindow']");

    private static final SelenideElement btn_Добавить = form.$x(".//button[@id='modalBtn' and contains(text(), 'Добавить')]");
    private final static SelenideElement modal_ДобавлениеСотрудника = $x("//div[@id='modal-addPerson-center']");
    private final static SelenideElement input_ФамилияСотрудника = modal_ДобавлениеСотрудника.$x(".//input[@id='personSurnameModal']");
    private final static SelenideElement input_ИмяСотрудника = modal_ДобавлениеСотрудника.$x(".//input[@id='personNameModal']");
    private final static SelenideElement input_ОтчествоСотрудника = modal_ДобавлениеСотрудника.$x(".//input[@id='personSecNameModal']");
    private final static SelenideElement input_EmailСотрудника = modal_ДобавлениеСотрудника.$x(".//input[@id='personEmailModal']");
    private final static SelenideElement input_ТабельныйНомерСотрудника = modal_ДобавлениеСотрудника.$x(".//input[@id='personTableIdModal']");
    private final static SelenideElement cmb_ДолжностьСотрудника = modal_ДобавлениеСотрудника.$x(".//select[@test_id='personPostModal']");
    private final static SelenideElement btn_Сохранить = modal_ДобавлениеСотрудника.$x(".//button[@id='savePersonAddModal']");


    @Step("Проверка на появление формы Сотрудники")
    public PersonsPage formIsAppear() {
        form.shouldBe(Condition.appear.because("Должна появиться форма 'Сотрудники'"))
                .shouldBe(Condition.exist);
        return this;
    }

    @Step("Кликнуть Добавить сотрудника")
    public PersonsPage clickAddPerson() {
        btn_Добавить
                .shouldBe(Condition.appear, MisstatsSettings.timeout())
                .shouldBe(Condition.visible)
                .click();
        return this;
    }

    @Step("Проверка на появление и отображение модалки добавления сотрудника")
    public PersonsPage modalAddPersonIsAppear() {
        modal_ДобавлениеСотрудника
                .shouldBe(Condition.appear, MisstatsSettings.timeout())
                .shouldBe(Condition.visible);
        return this;
    }

    @Step("Ввести фамилию сотрудника")
    public PersonsPage fillPersonSurnameModal(String personSurname) {
        input_ФамилияСотрудника.shouldBe(Condition.appear, MisstatsSettings.timeout())
                .shouldBe(Condition.visible)
                .setValue(personSurname);
        return this;
    }

    @Step("Ввести имя сотрудника")
    public PersonsPage fillPersonNameModal(String personName) {
        input_ИмяСотрудника.shouldBe(Condition.appear, MisstatsSettings.timeout())
                .shouldBe(Condition.visible)
                .setValue(personName);
        return this;
    }

    @Step("Ввести отчество сотрудника")
    public PersonsPage fillPersonSecNameModal(String personSecName) {
        input_ОтчествоСотрудника.shouldBe(Condition.appear, MisstatsSettings.timeout())
                .shouldBe(Condition.visible)
                .setValue(personSecName);
        return this;
    }

    @Step("Ввести email сотрудника")
    public PersonsPage fillPersonEmailModal(String personEmail) {
        input_EmailСотрудника.shouldBe(Condition.appear, MisstatsSettings.timeout())
                .shouldBe(Condition.visible)
                .setValue(personEmail);
        return this;
    }

    @Step("Ввести табельный номер сотрудника")
    public PersonsPage fillPersonTableIdModal(String personTableId) {
        input_ТабельныйНомерСотрудника.shouldBe(Condition.appear, MisstatsSettings.timeout())
                .shouldBe(Condition.visible)
                .setValue(personTableId);
        return this;
    }

    @Step("Выбрать должность сотрудника")
    public PersonsPage fillPersonPostModal(String personPostName) {
        cmb_ДолжностьСотрудника.shouldBe(Condition.appear, MisstatsSettings.timeout())
                .shouldBe(Condition.visible)
                .click();
        cmb_ДолжностьСотрудника.$x(".//*[contains(text(), '" + personPostName + "')]")
                .shouldBe(Condition.appear, MisstatsSettings.timeout())
                .shouldBe(Condition.visible)
                .click();
        return this;
    }

    @Step("Проверка на наличие сотрудника по ФИО")
    public PersonsPage personIsExist(String personFullname) {
        form.$x(".//div[@class='uk-card-person uk-width-1-3@m']//*[contains(text(), '" + personFullname + "')]/../..")
                .shouldBe(Condition.appear, MisstatsSettings.timeout());
        return this;
    }

    @Step("Проверка на отсутствие сотрудника по ФИО")
    public PersonsPage personIsNotExist(String personFullname) {
        form.$x(".//div[@class='uk-card-person uk-width-1-3@m']//*[contains(text(), '" + personFullname + "')]/../..")
                .shouldNotBe(Condition.exist);
        return this;
    }

    @Step("Нажать Сохранить в модальном окне добавления задачи")
    public PersonsPage clickSavePersonAddModal() {
        btn_Сохранить
                .shouldBe(Condition.appear, MisstatsSettings.timeout())
                .shouldBe(Condition.visible)
                .click();
        return this;
    }
}
