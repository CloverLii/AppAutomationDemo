package testcases;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import driver.BaseDriver;
import io.appium.java_client.MobileDriver;
import util.PropertiesReader;

/**
 * BaseTest
 * @author cloverli
 * @date 24/01/2021
 */
public class BaseTest {

	private BaseDriver baseDriver;
	public MobileDriver<WebElement> driver;
	
	private String platformName;
	private String platformVersion;
	private String deviceName;
	private String appPackage;
	private String appActivity;
	private String url;
	
	@BeforeSuite(alwaysRun = true)
	@Parameters({"propertiesPath"})
	public void getProperties(@Optional("src/test/resources/config/config.properties") String propertiesPath) throws IOException {
		
		PropertiesReader.readProperties(propertiesPath);
		platformName = PropertiesReader.getKey("conf.platformName");
		platformVersion = PropertiesReader.getKey("conf.platformVersion");
		deviceName = PropertiesReader.getKey("conf.deviceName");
		appPackage = PropertiesReader.getKey("conf.appPackage");
		appActivity = PropertiesReader.getKey("conf.appActivity");
		url = PropertiesReader.getKey("conf.appiumURL");
	}
	
	@BeforeTest(alwaysRun = true)
	public void setUp() {
		
		try {			
			baseDriver = new BaseDriver(platformName, platformVersion, deviceName,appPackage, appActivity, url);
			driver = baseDriver.getDriver();
			
			// check capabilities
			System.out.println("...input caps: " + platformName + "--" + platformVersion + "--"  + deviceName + "--"  + appPackage + "--"  + appActivity + "--"  + url);
			System.out.println("...confirm caps: " + baseDriver.getPlatformName() + baseDriver.getPlatformVersion() + baseDriver.getAppPackage() + baseDriver.getAppActivity() + baseDriver.getAppiumUrl());
			
			int implicitWait = Integer.parseInt(PropertiesReader.getKey("conf.driver.timeouts.implicitlyWait"));
			driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
			
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

