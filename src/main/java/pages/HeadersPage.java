package pages;

import Utilities.Utils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

public class HeadersPage {

    @FindBy(xpath = "//button[@aria-label=\"Quick Add\"]")
    private WebElement quickAddButton;
    
    @FindBy(xpath = "//div[@role=\"textbox\"]")
    private WebElement addTaskTextField;

    @FindBy(xpath = "//button[@class=\"item_due_selector icon_pill\"]")
    private WebElement dueDateButton;
    
    @FindBy(xpath = "//input[@placeholder=\"Type a due date\"]")
    private WebElement dueDateField;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement addTaskButton;

    @FindBy(id = "filter_inbox")
    private WebElement inboxButton;


    public HeadersPage() {
        Utils.initializePage(this);
    }

    public boolean isQuickAddButtonExisting(){
        return Utils.isElementExisting(quickAddButton);
    }

    public void clickQuickAddButton() {
        Utils.click(quickAddButton);
    }

    public void setTaskName(String taskName) {
        Utils.click(addTaskTextField);
        Utils.sendKeys(addTaskTextField, taskName);
    }

    public void setDueDate(String date) {
        Utils.sendKeys(dueDateField, date);
        Utils.sendKeys(dueDateField, Keys.ENTER.toString());
    }

    public void clickDueDateButton() {
        Utils.click(dueDateButton);
    }

    public void clickAddTask() {
        Utils.click(addTaskButton);
    }

    public void clickInbox() {
        Utils.click(inboxButton);
    }
}
