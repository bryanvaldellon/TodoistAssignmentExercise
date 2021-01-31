# Todoist Assignment Exercise
This is a Exercise Test Automation Project for Todoist Web App.

## Test Automation Framework - Tools
The project used Cucumber BDD, Page Object Model, JUnit as Test Runner, Java -  Maven Project, Selenium Webdriver and Logback classic

## Test Run 
To run the whole project, you can execute a Maven Command
```bash
mvn clean verify
```
Tags within the feature files can be run in particular and driver type can be customized (currently the project supports Windows Web desktop)
```bash
mvn clean verify -Dcucumber.options="--tags @ui" -Ddriver.type=ChromeWindowsWebDesktop
```
These commands are CI/CD ready and can be run using Jenkins/ Azure pipelines

## Cucumber Report
Cucumber report is generated every maven build. 
Location of the report : target\cucumber-reports-json\cucumber-html-reports\overview-features.html

## Log Files
For debugging purposes, log files are available on : \logs

## Configuation
Cunfiguration file is located at : src\test\resources\config.properties
```
username=<username on the app>
password=<passsword on the app>
default.driver=ChromeWindowsWebDesktop 
chrome.driver.path=src/test/resources/drivers/desktop/windows/chrome/
isclose.browser.after.execution=true ##change to false if you don't want to close the browsers after execution
maximize.browser=true
explicit.wait.limit=30 ##explicit wait limit
todoist.url=https://todoist.com/
```
