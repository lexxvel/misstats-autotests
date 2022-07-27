package actions;

import api.Person;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.Header;
import pageobjects.PersonsPage;
import system.Auth;
import system.MisstatsSettings;
import system.Tears;

public class AddPersonTest {

    Tears tears;
    Auth auth;
    Header header;
    String resource;
    PersonsPage personsPage;
    String personSurname = "Автотест";
    String personName = "Добавление";
    String personSecName = "Сотрудника";
    String personFullname;

    @Test
    @Parameters({"resource"})
    public void entryPoint(String resource) {
        try {
            this.resource = resource;
            tears = new Tears();
            auth = new Auth();
            tears.tearUp(resource);
            personFullname = personSurname + " " + personName + " " + personSecName;

            clearTestData();
            step1_Auth();
            step2_OpenPersonsTab();
            step3_AddPerson();

        } finally {
            clearTestData();
            tears.tearDown();
        }

    }

    @Step("Авторизация")
    private void step1_Auth() {
        try {
            header = new Header();
            header.clickGoToLogin();

            auth.auth(resource);

            header.btn_Выйти
                    .should(Condition.appear.because("Произведен вход, должна появиться кнопка выхода"), MisstatsSettings.timeout());
        } catch (Throwable t) {
            MisstatsSettings.fail(t);
        }
    }

    @Step("Открыть вкладку Сотрудники")
    private void step2_OpenPersonsTab() {
        try {
            header.clickGoToPersons();

            personsPage = new PersonsPage();
            personsPage.formIsAppear();
        } catch (Throwable t) {
            MisstatsSettings.fail(t);
        }
    }

    @Step("Добавление сотрудника")
    private void step3_AddPerson() {
        try {
            personsPage
                    .personIsNotExist(personFullname)
                    .clickAddPerson()
                    .modalAddPersonIsAppear()
                    .fillPersonSurnameModal(personSurname)
                    .fillPersonNameModal(personName)
                    .fillPersonSecNameModal(personSecName)
                    .fillPersonPostModal("Инженер по тестированию (авто")
                    .fillPersonEmailModal("autotest@test.ru")
                    .fillPersonTableIdModal("666")
                    .clickSavePersonAddModal()
                    .personIsExist(personFullname);

            //TODO: расширить проверку, ассерты значений в системе с ранее ввседенными значениями
        } catch (Throwable t) {
            MisstatsSettings.fail(t);
        }
    }

    @Step("Удаление тестовых данных")
    private void clearTestData() {
        Person person = new Person(auth.getHostUrl(resource));
        int responseCode = person.deletePersonById(person.findPersonIdByFullname(personFullname));
        Assertions.assertEquals(200, responseCode);
    }
}
