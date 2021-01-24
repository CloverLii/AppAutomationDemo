package testcases;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
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
	
	@BeforeTest(alwaysRun = true)
	@Parameters({"propertiesPath"})
	public void getProperties(@Optional("src/test/resources/config/config.properties") String propertiesPath) throws IOException {
		
		System.out.println("...Before suite: read properties file");
		PropertiesReader.readProperties(propertiesPath);
	}
	
	@BeforeTest(alwaysRun = true)
	@Parameters({"platformName","platformVersion","deviceName", "appPackage", "appActivity", "appiumURL"})
	public void setUp(@Optional("Android") String platformName, @Optional("emulator-5554") String deviceName, @Optional("11") String platformVersion,
            @Optional("your appPackage") String appPackage, @Optional("your application") String appActivity,
            @Optional("appiumURL") String appiumURL) throws MalformedURLException{
		
		System.out.println("...Before test: set up mobile driver");

		try {
			baseDriver = new BaseDriver(platformName, platformVersion, deviceName,appPackage, appActivity, appiumURL);
			driver = baseDriver.getDriver();
			
			// check capabilities
			System.out.println("...input caps: " + platformName + "--" + platformVersion + "--"  + deviceName + "--"  + appPackage + "--"  + appActivity + "--"  + appiumURL);
			System.out.println("...confirm caps: " + baseDriver.getPlatformName() + baseDriver.getPlatformVersion() + baseDriver.getAppPackage() + baseDriver.getAppActivity() + baseDriver.getAppiumUrl());
			
			int implicitWait = Integer.parseInt(PropertiesReader.getKey("conf.driver.timeouts.implicitlyWait"));
			driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
			
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}		
	}		 
		
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		// TODO: release resources after test finish
		if (driver != null) {
			System.out.println("...After class: close app and exit the driver");
			driver.closeApp();
			driver.quit();
		}
	}
	
}

