package pom.pages;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.BaseDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import util.PropertiesReader;


/**
 * Basic class containing common functions used in application page
 * 
 * @author cloverli
 * @date 08/02/2021
 *
 */
public class BasePage {
	
	protected MobileDriver<WebElement> driver;
	protected TouchAction action;
	protected WebDriverWait wait;
	
	// constructor
	public BasePage(BaseDriver baseDriver) {
		this.driver = baseDriver.getDriver();
		this.action = new TouchAction(driver);
		
		int timeouts = Integer.parseInt(PropertiesReader.getKey("conf.driver.timeout.webDriverWait"));
		this.wait = new WebDriverWait(driver, timeouts);
		
		//PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	// find element by locator
	public MobileElement findElement(By locator) {
		return (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	// find element by locator with implicit wait
	public MobileElement findElementAfterSec(By locator, int waitSeconds) {
		driver.manage().timeouts().implicitlyWait(waitSeconds, TimeUnit.SECONDS);
		return (MobileElement)driver.findElement(locator);
	}
	
	// click element by locator: button, hyper link
	public void clickElement(By locator) {
		MobileElement element = findElement(locator);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	// send keys
	public void sendKeys(By locator, String content) {
		MobileElement inputElement = findElement(locator);
		inputElement.clear();
		inputElement.sendKeys(content);
	}
	
	// get width and height of application
	public Map<String, Integer> getAppSize(){
		
		Map<String, Integer> map = new HashMap<>();
		
		int width = driver.manage().window().getSize().height;
		int width2 = driver.manage().window().getSize().getHeight();
		int height = driver.manage().window().getSize().getHeight();
		
		System.out.println("==== width: "+ width);
		System.out.println("==== width2 by get: " + width2);
		
		map.put("width", width);
		map.put("height", height);
		
		return map;						
	}
	
//	public MobileDriver<WebElement> switchWindow(){
//		driver.getContextHandles().forEach(context) -> {
//			if(context.contans("WEBVIEW")) {
//				driver.context(context);
//			}
//		};
//		return driver;
//	}
	

	// switch to next window handle
	public MobileDriver<WebElement> switch2NextWindow(){
		// get current window handle
		String currentHandle = driver.getWindowHandle();
		// get all window handles
		Set<String> allHandles = driver.getWindowHandles();
		for (String window: allHandles) {
			if(!currentHandle.equals(window)) {
				driver.switchTo().window(window);
			}
		}
		return driver;
	}
	
//	public MobileDriver<WebElement> exitWebView(){
//		driver.getContextHandles().forEach(context) -> {
//			if(context.contains("NATIVE_APP")) {
//				driver.context(context);
//			}
//		};
//		return driver;
//	}
	
	public void swipe(int fromX, int fromY, int toX, int toY, int duration) {
		PointOption fromPoint = PointOption.point(fromX, fromY);
		PointOption toPoint = PointOption.point(toX, toY);
		action.press(fromPoint).moveTo(toPoint).perform();
	}
	
	// tap on certain point
	public void tapPoint(int x, int y) {
		action.tap(PointOption.point(x, y)).perform();
	}
	
	// long press
	public void longPressPoint(int x, int y) {
		action.longPress(PointOption.point(x, y)).perform();
	}
	
	public void longPressElement(MobileElement ele) {
		//action.longPress(LongPressOptions(ele)).perform();
	}
	
}
