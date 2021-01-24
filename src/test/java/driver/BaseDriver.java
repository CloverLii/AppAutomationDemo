package driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import util.PropertiesReader;

public class BaseDriver {

	private MobileDriver<WebElement> driver;

    private String platformName;
    private String platformVersion;
    private String deviceName;
    private String appPackage;
    private String appActivity;
    private String automationName;
    private String url;
    
    public BaseDriver(String platformName, String platformVersion, String deviceName, String appPackage, String appActivity, String automationName, String urlStr) 
    		throws MalformedURLException {
    	
    	 this.platformName = platformName;
    	 this.platformVersion = platformVersion;
         this.deviceName = deviceName;
         this.appPackage = appPackage;
         this.appActivity = appActivity;
         this.automationName = automationName;
         this.url = urlStr;

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platformName", platformName);
        cap.setCapability("platformVersion", platformVersion);
        cap.setCapability("deviceName", deviceName);
        cap.setCapability("appPackage", appPackage);
        cap.setCapability("appActivity", appActivity);
        cap.setCapability("automationName", automationName);
        cap.setCapability("noReset", true);
		cap.setCapability("sessionOverride", true);
		cap.setCapability("newCommandTimeout","60");
        
        URL  appiumUrl = new URL(url);
       
        //TODO: implement android driver and iOS driver
        
        this.driver = new AndroidDriver<>(appiumUrl, cap);
    }

    public MobileDriver<WebElement> getDriver() {
        return driver;
    }

    public String getPlatformName() {
        return platformName;
    }
    
    public String getPlatformVersion() {
        return platformVersion;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public String getAppActivity() {
        return appActivity;
    }

    public String getAutomationName() {
        return automationName;
    }
    
    public String getAppiumUrl() {
    	return url;
    }
    

    public void closeDriver() {
        if (driver != null) {
            driver.closeApp();
        	driver.quit();
        }
    }
}
