package pages;

import Utilities.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {

    @FindBy(xpath = "(//a[@href=\"/users/showlogin\"])[1]")
    private WebElement login;

    public MainPage() {
        Utils.initializePage(this);
    }

    public void clickLogin(){
        Utils.click(login);
    }
}
