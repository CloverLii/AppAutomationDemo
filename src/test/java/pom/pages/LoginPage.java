package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BasePage{
	
	BasePage basePage;
	
	public LoginPage(MobileDriver<WebElement> driver) {
		
		super(driver);
		basePage = new BasePage(driver);
	}
	
	@AndroidFindBy(id = "")
	private MobileElement skipBtn;
	
	private MobileElement skipBtn2 = basePage.findElement(By.id("id"));
		
}
