package pom.pages;

import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileDriver;

public class MainPage extends BasePage{
	
	public MainPage(MobileDriver<WebElement> driver) {
		super(driver);
	}
	
	public void launchApp() {
		System.out.println("...This is Login Page");
	}

}
