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
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseDriver {
	
	private static Logger log = LoggerFactory.getLogger(BaseDriver.class);
	
	private MobileDriver<WebElement> driver;
    private String platformName;
    private String platformVersion;
    private String deviceName;
    private String appDir;
    private String automationName;
    private String url;
    
    public BaseDriver(String platformName, String platformVersion, String deviceName, String appDir, String autoName, String urlStr) throws MalformedURLException {
    	
    	 this.platformName = platformName;
    	 this.platformVersion = platformVersion;
         this.deviceName = deviceName;
         this.appDir = appDir;
         this.automationName = autoName;
         this.url = urlStr;
    }
    
    public MobileDriver<WebElement> startMobile() throws MalformedURLException {
    	
    	File app = new File(appDir);
    	log.info(String.format("====get application package: %s ====",appDir));
        //iOS app file: https://applitools.bintray.com/Examples/eyes-ios-hello-world.zip
    	
    	DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);        
        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);
        
        cap.setCapability(MobileCapabilityType.NO_RESET, true);
		cap.setCapability("sessionOverride", true);	
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,"60");
		cap.setCapability("resetKeyboard", false);
		cap.setCapability("noSign", true);
        
        URL  appiumUrl = new URL(url);
        
        driver = new AndroidDriver<>(appiumUrl, cap);
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
        return appDir;
    }

    public String getAppActivity() {
        return automationName;
    }
    
    public String getAppiumUrl() {
    	return url;
    }
    

    public void closeDriver() {
    	if (driver != null) {
        	log.info(String.format("====close application ===="));
            driver.closeApp();
            
            log.info(String.format("====close iOS driver ===="));
    	}
    }
}
