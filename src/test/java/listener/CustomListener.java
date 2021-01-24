
package listener;

import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import org.testng.ITestContext;
import util.ScreenshotUtil;


public class CustomListener implements ITestListener{
	
	ScreenshotUtil getScreenshot = new ScreenshotUtil(); 
	
	private void printInfo(String info) {
		System.out.println(info);
	}

	@Override
	public void onTestStart(ITestResult result) {
		printInfo("...Testing starts " + result.getMethod().getMethodName() + "at: " + result.getStartMillis());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		printInfo("...Test Pass");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		printInfo("...Test Failed");
		getScreenshot.capture(result);
	}
	
	public void onTestFailedWithinSuccessPercentage(ITestResult result) {
		printInfo("...Test fails with success percentage");
	}
	
	@Override
	public void onStart(ITestContext context) {
		printInfo("...Test begins...");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		printInfo("...Test finish...");
	}
}
