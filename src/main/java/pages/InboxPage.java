package pages;

import Utilities.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InboxPage {

    @FindBy(xpath = "//button[@aria-label=\"Project options menu\"]")
    private WebElement projectOptionsMenu;

    @FindBy(xpath = "//div[contains(text(), \"Show completed tasks\")]")
    private WebElement showCompletedTasks;


    public InboxPage() {
        Utils.initializePage(this);
    }

    public boolean isTaskNameExisting(String taskName){
        return Utils.isElementExistingNoWait("//div[contains(text(), \""+taskName+"\")]");
    }

    public boolean isTaskDueDateExisting(String taskDueDateName, String taskName){
        String dateValue = Utils.getElementAttribute("//div[contains(text(),\""+taskName+"\")]/../..//span[@class=\"date\"]", "text");
        return taskDueDateName.contains(dateValue);
    }

    public void clickEditTaskName(String taskName) {
        Utils.click("//div[contains(text(), \""+taskName+"\")]/../../..//button[@data-action-hint=\"task-edit\"]");
    }

    public void completeTask(String taskName) {
        Utils.click("//div[contains(text(),\""+taskName+"\")]/../../../button");
    }

    public void clickMoreProjectActions() {
        Utils.click(projectOptionsMenu);
    }

    public void clickShowCompletedTasks() {
        Utils.click(showCompletedTasks);
    }

    public boolean isCompletedTaskExisting(String taskName) {
        return Utils.isElementExistingNoWait("//button[@class=\"task_checkbox priority_1 checked\"]/.." +
                "//div[@class=\"markdown_content task_content\" and contains(text(),\""+taskName+"\")]");
    }
}
