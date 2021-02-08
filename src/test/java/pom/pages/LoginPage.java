package pom.pages;

import org.openqa.selenium.WebElement;

import driver.BaseDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BasePage{
	
	BasePage basePage;
	MobileDriver<WebElement> driver;
	
	public LoginPage(BaseDriver baseDriver) {
		
		super(baseDriver);
		driver = baseDriver.getDriver();
	}
	
	@AndroidFindBy(id = "")
	private MobileElement skipLink;
	
	@AndroidFindBy(id = "")
	private MobileElement emailAddr;
	
	@AndroidFindBy(id = "")
	private MobileElement password;
	
	@AndroidFindBy(id = "")
	private MobileElement showPwd;
	
	@AndroidFindBy(id = "")
	private MobileElement signInBtn;
	
	@AndroidFindBy(id = "")
	private MobileElement forgetPwdLink;
	
	@AndroidFindBy(id = "")
	private MobileElement keyboard;
	
	
	@AndroidFindBy(id = "UIAKeyboard")
	private MobileElement registerLink;
	
	public void typeEmail(String email) {
		emailAddr.sendKeys(email);
	}
	
	public void typePwd(String pwd) {
		password.sendKeys(pwd);
	}
	
	public void clickSignIn() {
		signInBtn.click();
	}
	
	public void hideKeyboard() {
		if	(keyboard != null) {
			driver.hideKeyboard();
		}
	}
	
	public boolean isDisplayed() {
		return skipLink.isDisplayed();
	}
	
	
	public void login(String email, String pwd) {
		hideKeyboard();
		typeEmail(email);
		typePwd(pwd);
		clickSignIn();		
	}
}
