package pom.testcases;

import java.net.MalformedURLException;

import org.testng.annotations.Test;

import pom.pages.LoginPage;
import pom.pages.MainPage;

public class LoginPageTest extends BaseTest{
	
	private BaseTest baseTest;
	private String email = "";
	private String pwd = "";
	
	@Test(alwaysRun = true, priority = 1)
	public void setUpDevice() throws MalformedURLException {
		baseTest = new BaseTest();
		baseTest.setUp();
	}
	
}
