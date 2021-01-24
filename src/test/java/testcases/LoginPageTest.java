package testcases;

import org.testng.annotations.Test;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import page.LoginPage;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPageTest extends BaseTest {
	
	LoginPage loginPage;
	
	@Test
	public void testLogin() throws InterruptedException {
		loginPage = new LoginPage(driver);
		loginPage.launchApp();
	}
	
}
