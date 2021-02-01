package drivers;

import Utilities.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverBase.class);

    private String driverType;

    public DriverBase() {
        setDriver();
        setupDriverType();
    }

    public DriverBase(String url) {
        setDriver();
        setupDriverType();
    }

    private void setupDriverType(){
        LOGGER.debug("setting up driver..");
        switch (driverType){
            case "ChromeWindowsWebDesktop": new MyChromeWindowsDesktopDriver(); break;
            case "ChromeAndroid": new MyChromeAndroidDriver(); break;
        }
        Utils.setWebdriverWait();
    }

    private void setDriver(){
        LOGGER.debug("setting driver type..");
        driverType = System.getProperty("driver.type");
        LOGGER.debug("driverType = {}", driverType);
        if(driverType == null || driverType.equals("")){
            LOGGER.debug("driver type null! will use the default driver!");
            driverType = Utils.getConfig("default.driver");
        }
        LOGGER.debug("driverType = {}", driverType);
    }

    public static void quitDriver(){
        LOGGER.debug("quitting driver!");
        if(Boolean.parseBoolean(Utils.getConfig("isclose.browser.after.execution"))){
            DriverSource.driver.quit();
        }
    }
}
