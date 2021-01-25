package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import page.LoginPage;


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
	
	@Test(retryAnalyzer = RetryRunner.class)
	public void testRetryRunner() {
		System.out.println("...This method will be automatically executed 3 times");
		Assert.assertEquals(1, 2);
	}
	
}
