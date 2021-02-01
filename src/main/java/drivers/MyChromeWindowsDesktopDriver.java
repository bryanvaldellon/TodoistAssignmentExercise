package drivers;

import Utilities.Utils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

public class MyChromeWindowsDesktopDriver {
    private WebDriver driver;

    public MyChromeWindowsDesktopDriver() {
        setChromeDriverpath();
        DriverSource.driver = new ChromeDriver(chromeOptions());//local
        DriverSource.platformType = "desktop";
    }

    private ChromeOptions chromeOptions(){
        ChromeOptions co = new ChromeOptions();
        co.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
        return co;
    }

    private void setChromeDriverpath(){
        Utils.printLogger("setting chrome path!");
        System.setProperty("webdriver.chrome.driver", Utils.getConfig("chrome.driver.path") + "chromedriver.exe");
    }
}
