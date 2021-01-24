package driver;

import java.io.File;
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
    private String url;
    
    public BaseDriver(String platformName, String platformVersion, String deviceName, String appPackage, String appActivity, String urlStr) 
    		throws MalformedURLException {
    	
    	 this.platformName = platformName;
    	 this.platformVersion = platformVersion;
         this.deviceName = deviceName;
         this.appPackage = appPackage;
         this.appActivity = appActivity;
         this.url = urlStr;
         
         //File appApk = new File("/application/application_name.apk");

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platformName", platformName);	//MobileCapabilityType.PLATFORM_NAME, , MobilePlatform.ANDROID
        cap.setCapability("platformVersion", platformVersion);	//obileCapabilityType.PLATFORM_VERSION
        cap.setCapability("deviceName", deviceName);	//MobileCapabilityType.DEVICE_NAME
        
        //TODO: setup based on package * activity name OR apk file, absolute dir
       // cap.setCapability("app", appApk.getAbsolutePath());	//MobileCapabilityType.APP
        cap.setCapability("appPackage", appPackage);
        cap.setCapability("appActivity", appActivity);
        cap.setCapability("automationName", "Appium");
        cap.setCapability("noReset", true);	//MobileCapabilityType.NO_RESET
		cap.setCapability("sessionOverride", true);	//--session-override
		cap.setCapability("newCommandTimeout","60");	//MobileCapabilityType.NEW_COMMAND_TIMEOUT
		cap.setCapability("resetKeyboard", false);
		cap.setCapability("noSign", true);
        
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
