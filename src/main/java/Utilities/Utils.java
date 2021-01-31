package Utilities;
import cucumber.api.Scenario;
import drivers.DriverSource;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Properties;

public class Utils {
    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);
    private final static String configFilepath = "src/test/resources/config.properties";
    private static WebDriverWait webDriverWait;
    private static Actions actions;
    public static Scenario scenario;

    public Utils() {
        LOGGER.debug("new main.java.Utilities.Utils!");
    }

    public static void printLogger(Object obj){
        LOGGER.debug(obj.toString());
    }

    public static Properties configReader(String path){
        File propertyFilePath = new File(path);
        BufferedReader reader;
        Properties properties;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration not found at " + propertyFilePath);
        }
        return properties;
    }

    public static String getConfig(String configName){
        LOGGER.debug("getting config of = {}", configName);
        String value = configReader(configFilepath).getProperty(configName);
        LOGGER.debug("value = {}", value);
        return value;
    }

    public static void launchURL(String url) {
        LOGGER.debug("launching url = {}", url);
        DriverSource.driver.get(url);
        maximizeBrowser();
    }
    public static void setWebdriverWait(){
        String wait = Utils.getConfig("explicit.wait.limit");
        LOGGER.debug("setting webdriver wait to = {}", wait);
        webDriverWait = new WebDriverWait(DriverSource.driver, Integer.parseInt(wait));

    }
    public static void maximizeBrowser(){
        if(Boolean.parseBoolean(Utils.getConfig("maximize.browser")) && !DriverSource.platformType.equals("android")) {
            LOGGER.debug("maximizing browser!");
            DriverSource.driver.manage().window().maximize();
        }
    }

    public static void newPageObect(Object clas) {
        LOGGER.debug("refreshing page object = {}", clas);
        PageFactory.initElements(DriverSource.driver, clas);
    }

    public static void click(WebElement element) {
        LOGGER.debug("clicking element = {}", element);
        element = waitForElementVisibility(element);
        element.click();
        LOGGER.debug("Element {} clicked!", element);
    }

    public static void click(String xpath) {
        LOGGER.debug("clicking element xpath = {}", xpath);
        WebElement element;
        element = waitForElementVisibility(xpath);
        try{
            element.click();
        }
        catch (ElementClickInterceptedException e){
            LOGGER.debug("retrying to click due to ElementClickInterceptedException!");
            clickElementJavascript(element);
        }
        LOGGER.debug("Element {} clicked!", element);
    }
    public static void click(String xpath, boolean isscrollIntoView) {
        LOGGER.debug("clicking element xpath = {} scroll into view = {}", xpath, isscrollIntoView);
        WebElement element;
        element = waitForElementVisibility(xpath);
        if(isscrollIntoView)
            scrollIntoViewElement(element);
        try{
            element.click();
        }
        catch (ElementClickInterceptedException e){
            LOGGER.debug("retrying to click due to ElementClickInterceptedException!");
            clickElementJavascript(element);
        }
        LOGGER.debug("Element {} clicked!", element);
    }

    public static void scrollIntoViewElement(WebElement element){
        LOGGER.debug("scrolling element into view = {}", element);
        ((JavascriptExecutor) DriverSource.driver).executeScript("arguments[0].scrollIntoView(true);", element);
        LOGGER.debug("scrolling element into view done!");
    }

    public static void clickElementJavascript(WebElement element){
        LOGGER.debug("clicking element using javascript! = {}", element);
        JavascriptExecutor executor = (JavascriptExecutor)DriverSource.driver;
        executor.executeScript("arguments[0].click();", element);
        LOGGER.debug("element clicked!");
    }

    public static WebElement waitForElementVisibility(WebElement element){
        LOGGER.debug("waiting for element = {}", element);
        try{
            element = webDriverWait.until(ExpectedConditions.visibilityOf(element));
        }
        catch(TimeoutException e){
            tryCatchExceptionHandler("waitForElementVisibility - ", e);
        }
        return element;
    }

    public static WebElement waitForElementVisibility(String xpath){
        LOGGER.debug("waiting for element xpath = {}", xpath);
        WebElement element = null;
        try{
            element = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        }
        catch(TimeoutException e){
            tryCatchExceptionHandler("waitForElementVisibility - ", e);
            return element;
        }
        catch (ElementClickInterceptedException e){
            LOGGER.debug("ignoring ElementClickInterceptedException!");
            return element;
        }
        LOGGER.debug("element found! = {}", element);
        return element;
    }

    public static void tryCatchExceptionHandler(String text, Exception e){
        LOGGER.debug(text + e.getMessage());
        throw new AssertionError();
    }

    public static void assertTrue(boolean isTrue){
        LOGGER.debug("checking if = {}", isTrue);
        Assert.assertTrue(isTrue);
    }

    public static boolean isElementExisting(WebElement element){
        LOGGER.debug("checking if element existing with wait = {}", element);
        element = waitForElementVisibility(element);
        try{
            return element.isDisplayed();
        }
        catch(NoSuchElementException e){
            LOGGER.debug("element not existing");
            return element.isDisplayed();
        }
    }

    public static boolean isElementExistingNoWait(WebElement element){
        LOGGER.debug("checking if element existing = {}", element);
        try{
            return element.isDisplayed();
        }
        catch(NoSuchElementException e){
            LOGGER.debug("element not existing");
            return element.isDisplayed();
        }
    }

    public static boolean isElementExistingNoWait(String xpath){
        LOGGER.debug("checking if element xpath existing with no wait = {}", xpath);
        try{
            WebElement element;
            element = DriverSource.driver.findElement(By.xpath(xpath));
            return element.isDisplayed();
        }
        catch(NoSuchElementException e){
            LOGGER.debug("element not existing!");
            return false;
        }
    }

    public static void implicitWait(int secs){
        LOGGER.debug("implicit wait for = {} seconds",secs);
        try {
            Thread.sleep(secs);
        } catch (InterruptedException e) {
            Utils.tryCatchExceptionHandler("implicitWait - ", e);
        }
    }

    public static void sendKeys(WebElement element, String text) {
        LOGGER.debug("sending keys = {} to element = {} ", text, element);
        element = waitForElementVisibility(element);
        element.sendKeys(text);
        LOGGER.debug("send keys complete!");
    }

    public static String getElementAttribute(WebElement element, String attrib) {
        LOGGER.debug("getting element attribute of element = {} attrib = {}", element, attrib);
        element = waitForElementVisibility(element);
        String attribValue;
        if(attrib.toLowerCase().contains("text")){
            attribValue = element.getText();
        }
        else{
            attribValue = element.getAttribute(attrib);
        }

        LOGGER.debug("attribValue = {}", attribValue);
        return attribValue;
    }

    public static String getElementAttribute(String xpath, String attrib) {
        LOGGER.debug("getting element attribute of element xpath = {} attrib = {}", xpath, attrib);
        WebElement element = DriverSource.driver.findElement(By.xpath(xpath));
        element = waitForElementVisibility(element);
        String attribValue;
        if(attrib.toLowerCase().contains("text")){
            attribValue = element.getText();
        }
        else{
            attribValue = element.getAttribute(attrib);
        }

        LOGGER.debug("attribValue = {}", attribValue);
        return attribValue;
    }

    public static void assertEquals(String text1, String text2) {
        LOGGER.debug("comparing 2 text! text1 = {} aand text2= {}", text1, text2);
        Assert.assertEquals(text1, text2);
    }

    public static void initializePage(Object claz) {
        LOGGER.debug("initalizing page = {}", claz);
        PageFactory.initElements(DriverSource.driver, claz);
    }

    public static void takeScreenshot() {
        Utils.printLogger("taking screenshot!");
        final byte[] screenshot = ((TakesScreenshot) DriverSource.driver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }
}
