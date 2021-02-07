package pages;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import util.PropertiesReader;

/**
 * Basic class containing common functions used in application page
 * 
 * @author cloverli
 * @date 24/01/2021
 *
 */
public class BasePage {
	
	protected MobileDriver<WebElement> driver;
	protected TouchAction action;
	protected WebDriverWait wait;
	
	// constructor
	public BasePage(MobileDriver<WebElement> driver) {
		this.driver = driver;
		this.action = new TouchAction(driver);
		int timeouts = Integer.parseInt(PropertiesReader.getKey("conf.driver.timeout.webDriverWait"));
		this.wait = new WebDriverWait(driver, timeouts);		
	}
	
	/**
	 * 
	 * @param locator
	 * @return targeted element
	 */
	public WebElement findElement(By locator) {
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	/**
	 * 
	 * @param locator
	 * @return clickable button
	 */
	public void clickBtn(By locator) {
		WebElement ele = findElement(locator);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		ele.click();
	}
	
	/**
	 * 
	 * @param locator
	 * @param content
	 * @return input element
	 */
	public void sendKeys(By locator, String content) {
		WebElement inputEle = findElement(locator);
		inputEle.clear();
		inputEle.sendKeys(content);
	}
	
//	public MobileDriver<WebElement> switchWindow(){
//		driver.getContextHandles().forEach(context) -> {
//			if(context.contans("WEBVIEW")) {
//				driver.context(context);
//			}
//		};
//		return driver;
//	}
	
	/**
	 *  Switch to next window
	 * @return driver
	 */
	public MobileDriver<WebElement> switch2NextWindow(){
		String currentHandle = driver.getWindowHandle();
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
	
	public void swipeScreen(int fromX, int fromY, int toX, int toY, int duration) {
		PointOption fromPoint = PointOption.point(fromX, fromY);
		PointOption toPoint = PointOption.point(toX, toY);
		action.press(fromPoint).moveTo(toPoint).perform();
	}
	
}
