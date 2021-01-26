package pageobj.page;

import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileDriver;

public class LoginPage extends BasePage{
	
	public LoginPage(MobileDriver<WebElement> driver) {
		super(driver);
	}
	
	public void launchApp() {
		System.out.println("...This is Login Page");
	}

}
