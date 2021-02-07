package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.log4j.PropertyConfigurator;
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
	private static Logger log = LoggerFactory.getLogger(BaseTest.class);
	
	@BeforeSuite(description = "do sth before test suite")
	public void beforeSuite() {
		System.out.println("...BaseTest beforeSuite");
		log.info("====baseTest before suite====");
	}
	
	
	@BeforeTest(alwaysRun = true, description = "read properties file")
	@Parameters({"propertiesPath", "log4jConPath"})
	public void getProperties(@Optional("src/test/resources/config/config.properties") String propertiesPath,
							  @Optional("src/test/resources/log4j.properties") String log4jConPath) throws IOException {
		
		log.info("====BaseTest beforeTest: read properties file====");
		PropertiesReader.readProperties(propertiesPath);
		//PropertiesReader.readProperties(log4jConPath);
		PropertyConfigurator.configure(log4jConPath);
	}
	
	
	@BeforeClass(description = " do sth before class")
	public void beforeClass() {
		log.info("====BaseTest beforeClass====");
	}
	
	
	@BeforeTest(alwaysRun = true)
	@Parameters({"platformName","platformVersion","deviceName", "appPackage", "appActivity", "appiumURL"})
	public void setUp(@Optional("Android") String platformName, @Optional("emulator-5554") String deviceName, @Optional("11") String platformVersion,
            @Optional("your appPackage") String appPackage, @Optional("your application") String appActivity,
            @Optional("appiumURL") String appiumURL) throws MalformedURLException{
		
		log.info("====BaseTest beforeTest: set up mobile driver====");

		try {
			baseDriver = new BaseDriver(platformName, platformVersion, deviceName,appPackage, appActivity, appiumURL);
			driver = baseDriver.getDriver();
			
			// check capabilities
			log.info("====input caps: " + platformName + "--" + platformVersion + "--"  + deviceName + "--"  + appPackage + "--"  + appActivity + "--"  + appiumURL);
			log.info("====confirm caps: " + baseDriver.getPlatformName() + baseDriver.getPlatformVersion() + baseDriver.getAppPackage() + baseDriver.getAppActivity() + baseDriver.getAppiumUrl());
			
			int implicitWait = Integer.parseInt(PropertiesReader.getKey("conf.driver.timeouts.implicitlyWait"));
			driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
			
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}		
	}	
	
	
	@AfterClass
	public void afterClass() {
		log.info("====BaseTest afterClass====");
	}
	
	
	@AfterTest(alwaysRun = true)
	public void tearDown() {
		// TODO: release resources after test finish
		if (driver != null) {
			System.out.println("...BaseTest afterTest: close app and exit the driver");
			driver.closeApp();
			driver.quit();
		}
	}
	
	
	@AfterSuite
	public void afterSuite() {
		log.info("...BaseTest afterSuite");
	}
	
	
}

