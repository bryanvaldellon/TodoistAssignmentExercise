@ui
Feature: Todoist Exercise Features for Web UI
  Background:
    Given user on platform
    When user on Todoist app
    When user Login
    Then user successfully logged in to Todoist

  @createNewTodo
  Scenario Outline: Create a new todo item
    When user creates new task as '<taskName>'
    And user set due date to '<date>'
    And user clicks add task
    Then task '<taskName>' with date of '<date>' is added to inbox

    Examples:
    |taskName |date       |
    |testTask1|03 Apr 2021|
    |testTask2|12 Apr 2022|
    |testTask3|15 Apr 2029|

  @renameTodo
  Scenario Outline: rename todo item
    When user creates new task as '<taskName>'
    And user set due date to '<date>'
    And user clicks add task
    Then task '<taskName>' with date of '<date>' is added to inbox
    When user rename the task '<taskName>' by adding '<appendText>' text
    Then task '<taskName><appendText>' with date of '<date>' is added to inbox

    Examples:
      |taskName |date       |appendText|
      |testTask4|03 Apr 2021|rename    |
      |testTask5|12 Apr 2022|rename    |
      |testTask6|15 Apr 2029|rename    |

  @completeTodo
  Scenario Outline: Complete a todo item
    When user creates new task as '<taskName>'
    And user set due date to '<date>'
    And user clicks add task
    Then task '<taskName>' with date of '<date>' is added to inbox
    When user marked task '<taskName>' as complete
    Then task '<taskName>' is added to completed tasks

    Examples:
      |taskName |date       |
      |testTask7|03 Apr 2021|
      |testTask8|12 Apr 2022|
      |testTask9|15 Apr 2029|
