package pages;

import Utilities.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    @FindBy(id = "email")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(xpath = "//button[@class = \"submit_btn ist_button ist_button_red sel_login\"]")
    private WebElement loginButton;

    public LoginPage() {
        Utils.initializePage(this);
    }

    public void setDefaultUserName(){
        Utils.sendKeys(userName, Utils.getConfig("username"));
    }

    public void setDefaultPassword(){
        Utils.sendKeys(password,Utils.getConfig("password"));
    }

    public void clickLoginButton(){
        Utils.click(loginButton);
    }
}
