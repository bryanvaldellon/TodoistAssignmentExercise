package StepDefinitions;

import Steps.TodoistUIStep;
import Utilities.Utils;
import cucumber.api.Scenario;
import cucumber.api.java.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import drivers.DriverBase;

public class TodoistUIStepDefinition {

    TodoistUIStep todoistUIStep = new TodoistUIStep();

    @Before
    public void beforeScenario(Scenario scenario){
        Utils.printLogger("starting scenario = " + scenario.getName());
    }

    @BeforeStep
    public void beforeStep(Scenario scenario){
        Utils.scenario = scenario;
    }

    @After
    public void afterScenario(Scenario scenario){
        Utils.printLogger("ending scenario = " + scenario.getName());
        DriverBase.quitDriver();
    }




    @Given("^user on platform$")
    public void userOnPlatform() {
        new DriverBase();
    }

    @Given("^user on Todoist app$")
    public void open_todoist_app() {
        todoistUIStep.open_todoist_app();
    }

    @When("^user Login$")
    public void login() {
        todoistUIStep.login();
    }

    @Then("^user successfully logged in to Todoist$")
    public void validate_successful_login() {
        todoistUIStep.validate_successful_login();
    }

    @When("user creates new task as {string}")
    public void create_new_task(String taskName) {
        todoistUIStep.create_new_task(taskName);
    }

    @And("user set due date to {string}")
    public void set_due_date(String date) {
        todoistUIStep.set_due_date(date);
    }

    @And("user clicks add task")
    public void click_add_task() {
        todoistUIStep.click_add_task();
    }

    @Then("task {string} with date of {string} is added to inbox")
    public void validate_task_inbox(String taskName, String date) {
        todoistUIStep.validate_task_inbox(taskName, date);
    }

    @When("user rename the task {string} by adding {string} text")
    public void rename_task(String taskName, String appendText) {
        todoistUIStep.rename_task(taskName, appendText);
    }

    @When("user marked task {string} as complete")
    public void mark_task_complete(String taskName) {
        todoistUIStep.mark_task_complete(taskName);
    }

    @Then("task {string} is added to completed tasks")
    public void validate_completed_task(String taskName) {
        todoistUIStep.validate_completed_task(taskName);
    }
}
