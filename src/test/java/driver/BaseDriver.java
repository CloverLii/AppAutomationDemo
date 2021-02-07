package driver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseDriver {
	
	private static Logger log = LoggerFactory.getLogger(BaseDriver.class);
	
	private MobileDriver<WebElement> driver;
    private String platformName;
    private String platformVersion;
    private String deviceName;
    private String appDir;
    private String appPackage;
    private String appActivity;
    private String automationName;
    private String url;
    
    public BaseDriver(String platformName, String platformVersion, String deviceName, String appPkg, String appActivity, String autoName, String urlStr) throws MalformedURLException {
    	
    	 this.platformName = platformName;
    	 this.platformVersion = platformVersion;
         this.deviceName = deviceName;
         this.appPackage = appPkg;
         this.appActivity = appActivity;
         this.automationName = autoName;
         this.url = urlStr;
    }
    
    public MobileDriver<WebElement> startMobile() throws MalformedURLException {
    	
    	//File app = new File(appDir);
    	//log.info(String.format("====get directory of application package: %s ====",appDir));
        //iOS app file: https://applitools.bintray.com/Examples/eyes-ios-hello-world.zip
    	
    	DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);    
        cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
        cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
        //cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);
        
        cap.setCapability(MobileCapabilityType.NO_RESET, true);
		cap.setCapability("sessionOverride", true);	
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,"60");
		cap.setCapability("resetKeyboard", false);
		cap.setCapability("noSign", true);
        
        URL  appiumUrl = new URL(url);
        
        if(platformName == "Android") {
        	driver = new AndroidDriver<WebElement>(appiumUrl, cap);
        }else {
        	driver = new IOSDriver<WebElement>(appiumUrl, cap);
        }
        log.info("====start mobile driver ====");
        
        return driver;
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

    public String getAppDir() {
        return appDir;
    }

    public String getAutomationName() {
        return automationName;
    }
    
    public String getAppiumUrl() {
    	return url;
    }
    

    public void closeDriver() {
    	if (driver != null) {
        	log.info(String.format("====close application ===="));
            driver.closeApp();
            
            log.info(String.format("====close mobile driver ===="));
            driver.quit();
    	}
    }
}
