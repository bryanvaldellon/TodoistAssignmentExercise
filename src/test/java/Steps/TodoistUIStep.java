package Steps;

import Utilities.Utils;
import cucumber.api.java.sl.In;
import pages.InboxPage;
import pages.LoginPage;
import pages.MainPage;
import pages.HeadersPage;

public class TodoistUIStep {

    public void open_todoist_app() {
        Utils.launchURL(Utils.getConfig("todoist.url"));
    }

    public void login() {
        MainPage mainPage = new MainPage();
        LoginPage loginPage = new LoginPage();
        mainPage.clickLogin();
        loginPage.setDefaultUserName();
        loginPage.setDefaultPassword();
        Utils.takeScreenshot();
        loginPage.clickLoginButton();
    }

    public void validate_successful_login() {
        HeadersPage headersPage = new HeadersPage();
        Utils.takeScreenshot();
        Utils.assertTrue(headersPage.isQuickAddButtonExisting());
    }

    public void create_new_task(String taskName) {
        HeadersPage headersPage = new HeadersPage();
        headersPage.clickQuickAddButton();
        Utils.implicitWait(1000);
        headersPage.setTaskName(taskName);
    }

    public void set_due_date(String date) {
        HeadersPage headersPage = new HeadersPage();
        headersPage.clickDueDateButton();
        headersPage.setDueDate(date);
    }

    public void click_add_task() {
        HeadersPage headersPage = new HeadersPage();
        Utils.takeScreenshot();
        headersPage.clickAddTask();
    }

    public void validate_task_inbox(String taskName, String date) {
        HeadersPage headersPage = new HeadersPage();
        InboxPage inboxPage = new InboxPage();
        headersPage.clickInbox();
        Utils.implicitWait(3000);
        Utils.takeScreenshot();
        Utils.assertTrue(inboxPage.isTaskNameExisting(taskName) && inboxPage.isTaskDueDateExisting(date, taskName));
    }

    public void rename_task(String taskName, String appendText) {
        HeadersPage headersPage = new HeadersPage();
        InboxPage inboxPage = new InboxPage();
        inboxPage.clickEditTaskName(taskName);
        headersPage.setTaskName(taskName + appendText);
        headersPage.clickAddTask();
    }

    public void mark_task_complete(String taskName) {
        InboxPage inboxPage = new InboxPage();
        inboxPage.completeTask(taskName);
    }

    public void validate_completed_task(String taskName) {
        InboxPage inboxPage = new InboxPage();
        inboxPage.clickMoreProjectActions();
        inboxPage.clickShowCompletedTasks();
        Utils.assertTrue(inboxPage.isCompletedTaskExisting(taskName));
    }
}
