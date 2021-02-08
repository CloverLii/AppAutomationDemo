package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import listener.FailedRetryRunner;
import pom.pages.MainPage;

import org.junit.jupiter.api.Assertions;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


@Epic("Main Page")
@Feature("MainPage can be opened correctly")
@Severity(SeverityLevel.BLOCKER)
public class MainPageTest extends BaseTest {
	
	MainPage loginPage;
	private static final Logger log = LoggerFactory.getLogger(MainPageTest.class);
	
	@Test(description = "All items on the main page are loaded correctly", groups = {"regression", "negative"})
	@Story("XX is loaded as normal")
	public void testLogin() throws InterruptedException {
		loginPage = new MainPage(driver);
		loginPage.launchApp();
	}
	
	@Test(retryAnalyzer = FailedRetryRunner.class, description = "failed method")
	public void testRetryRunner() {
		System.out.println("...testRetryRunner: this method will be automatically executed 3 times");
		log.info("retry failed test up to 3 times");
		Assert.assertEquals(1, 2);
	}
	
	@Test(dependsOnMethods = {"testRetryRunner"}, description = "skipped method")
	public void testSkipMethod() {
		System.out.println("...testSkipMethod: this method will be skipped as it depends on a failed method");
	}
	
	@Test(description = "multiple assertions using junit5, test fails if at least one fails")
	public void multipleAssertion() {
		Assertions.assertAll("all values should be eaqual",
			    () -> {Assertions.assertTrue(true);}, 
			    () -> {Assertions.assertEquals(1, 1);}, 
			    () -> {Assertions.assertEquals("a", "a");}
			);
	}
	
}
