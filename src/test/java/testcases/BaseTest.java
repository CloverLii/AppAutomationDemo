package testcases;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import driver.BaseDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import util.PropertiesReader;

/**
 * BaseTest
 * @author cloverli
 * @date 24/01/2021
 */
public class BaseTest {

	private BaseDriver baseDriver;
	public MobileDriver<WebElement> driver;
	
	@BeforeSuite(alwaysRun = true)
	@Parameters({"propertiesPath"})
	public void beforeSuite(@Optional("src/test/resources/config/config.properties") String propertiesPath) throws IOException {
		PropertiesReader.readProperties(propertiesPath);
	}
	
	@BeforeTest(alwaysRun = true)
	@Parameters({"platformName", "platformVersion", "deviceName", "appPackage", "appActivity", "automationName", "url"})	
	public void setUp(@Optional("Android")String platformName, 
			@Optional("11")String platformVersion, 
			@Optional("emulator-5554")String deviceName, 
			@Optional("com.google.deskclock")String appPackage, 
			@Optional("com.android.deskclock.DeskClock")String appActivity, 
			@Optional("appium")String automationName, 
			@Optional("http://12.0.0.1:4723/we/hub")String url) {
		
		try {			
			baseDriver = new BaseDriver(platformName, platformVersion, deviceName,appPackage, appActivity, automationName, url);
			driver = baseDriver.getDriver();
			
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}		
	}
		 
	
	@AfterClass(alwaysRun = true)
	public void  tearDown() throws Exception{
		Thread.sleep(4000);
		if (driver != null) {
			driver.closeApp();	//close the application
			//Assert.assertNotEquals(driver.currentActivity().toString(), "expected_app");
			driver.quit();	//quit the driver
		}
	}
	
	@AfterTest(alwaysRun = true)
	public void closeApp() {
		// close application after test
		driver.closeApp();
	}
	
	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		// TODO: release resources after test finish
	}
	
}

