package actions;

import api.Task;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.Header;
import pageobjects.TasksPage;
import org.junit.jupiter.api.Assertions;
import system.Auth;
import system.MisstatsSettings;
import system.Tears;

public class AddTaskTest {
    Tears tears;
    Auth auth;
    Header header;
    TasksPage tasksPage;
    String resource;
    String taskNumber;

    @Test
    @Parameters({"resource", "taskNumber"})
    void entryPoint(String resource, String taskNumber) {
        try {
            this.resource = resource;
            this.taskNumber = taskNumber;
            tears = new Tears();
            auth = new Auth();

            tears.tearUp(this.resource);

            clearTestData();
            step1_Auth();
            step2_AddTask();

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

    @Step("Добавление задачи")
    private void step2_AddTask() {
        try {
            header.clickGoToTasks();

            tasksPage = new TasksPage();
            tasksPage
                    .taskIsNotExist(taskNumber)
                    .clickAddTask()
                    .modalAddTaskIsAppear()
                    .fillTaskNumber(taskNumber)
                    .fillSprint("Team Стационар - Спринт 22-13")
                    .fillPlanTime("1")
                    .clickSaveModal();
            Assertions.assertFalse(tasksPage.modalAddTaskIsDisplayed());

            tasksPage.findTask(taskNumber);

        } catch (Throwable t) {
            MisstatsSettings.fail(t);
        }
    }

    /**
     * Удаление тестовых данных: созданной задачи
     */
    private void clearTestData() {
        int requestStatus = new Task(auth.getHostUrl(resource)).deleteTask(taskNumber);
        Assertions.assertEquals(200, requestStatus);
    }

}
