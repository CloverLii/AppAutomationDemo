package testcases;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import driver.BaseDriver;
import io.appium.java_client.MobileDriver;
import util.PropertiesReader;

/**
 * BaseTest
 * @author cloverli
 * @date 08/02/2021
 */

public class BaseTest {
	
	private static Logger log = LoggerFactory.getLogger(BaseTest.class);

	private BaseDriver baseDriver;
	public MobileDriver<WebElement> driver;
	
	private String pName;
	private String pVersion;
	private String dName;
	private String appPkg;
	private String appAct;
	private String appDir;
	private String autoName;
	private String appiumUrl;
	
	
	@BeforeTest(alwaysRun = true, description = "read property files")
	@Parameters({"propertiesPath", "log4jConPath", "platformName", "platformVersion", "deviceName","appPackage", "appActivity", "automationName", "appiumURL", "appDirectory"})
	public void getProperties(@Optional("src/test/resources/config/config.properties") String propertiesPath,
							  @Optional("src/test/resources/log4j.properties") String log4jConPath,
							  @Optional("Android") String platformName,
							  @Optional("11") String platformVersion,
							  @Optional("emulator-5554") String deviceName,
							  @Optional("au.com.seek") String appPackage,
							  @Optional("seek.base.ui.features.auth.AuthActivity") String appActivity,
							  @Optional("Appium") String automationName,
							  @Optional("http://127.0.0.1:4723/wd/hub") String appiumURL,
							  @Optional("/src/test/resources/app/test.au.com.seek.apk") String appLoc) throws IOException {
		
		log.info("====BeforeTest: read properties files====");
		PropertiesReader.readProperties(propertiesPath);
		//PropertiesReader.readProperties(log4jConPath);
		PropertyConfigurator.configure(log4jConPath);
		
		pName = platformName;
		pVersion = platformVersion;
		dName = deviceName;
		appPkg = appPackage;
		appAct = appActivity;
		autoName = automationName;
		appiumUrl = appiumURL;
		appDir = appLoc;
	}
		
	private boolean appInstalled() {
		
		return driver.isAppInstalled("test.au.com.seek");
	}
	

	@Test(alwaysRun = true, priority = 1)
	public void setUp() throws MalformedURLException{
				
		// initialize mobile driver
		baseDriver = new BaseDriver(pName, pVersion, dName, appPkg, appAct, autoName, appiumUrl);
		driver = baseDriver.startMobile();
			
		log.info("====input caps: " + pName + "--" + pVersion + "--"  + dName + "--"  + appPkg + "--" + appAct + "--" + autoName + "--"  + appiumUrl);
		log.info("====confirm caps: " + baseDriver.getPlatformName() + baseDriver.getPlatformVersion() + baseDriver.getAppActivity() + baseDriver.getAutomationName() + baseDriver.getAppiumUrl());
		log.info(String.format("==== install applicaiton via package successfully: %s", appInstalled()));
			
		int implicitWait = Integer.parseInt(PropertiesReader.getKey("conf.driver.timeouts.implicitlyWait"));
		driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
				
	}	
			
	@AfterTest(alwaysRun = true)
	public void tearDown() {
		log.info("====AfterTest: close application and quit driver ====");
		baseDriver.closeDriver();
	}
	
}

