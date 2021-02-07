package driver;

import java.net.MalformedURLException;

import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileDriver;

public abstract class DriverHandler {
	
	/**
	 * 
	 * @param platform: Android or iOS
	 * @param platformVersion
	 * @param deviceName
	 * @param appDir: the directory of application package
	 * @param automationName: Appium/UIAutomator/XCUITest
	 * @param url
	 * @return an instance of mobile driver
	 * @throws MalformedURLException
	 */
	public abstract MobileDriver<WebElement> startMobile(String platform, String platformVersion, String deviceName, String appDir, String automationName, String url) throws MalformedURLException;
	
	public abstract void closeDriver();
}
