package testcases;

import org.testng.annotations.Test;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import page.LoginPage;

import org.openqa.selenium.WebElement;
import org.testng.Assert;


@Epic("Regression Tests")
@Feature("LoginPage")
@Severity(SeverityLevel.NORMAL)
public class LoginPageTest extends BaseTest {
	
	LoginPage loginPage;
	
	@Test
	@Description(" Test core function on Login Page ")
	public void testLogin() throws InterruptedException {
		loginPage = new LoginPage(driver);
		loginPage.launchApp();
	}
	
}
