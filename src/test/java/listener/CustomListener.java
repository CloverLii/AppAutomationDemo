package listener;

import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

import org.testng.ITestContext;


public class CustomListener implements ITestListener{
	
	
	private void printInfo(String info) {
		System.out.println(info);
	}

	public void onTestStart(ITestResult result) {
		printInfo("...Testing starts " + result.getMethod().getMethodName() + "at: " + result.getStartMillis());
	}
	
	public void onTestSuccess(ITestResult result) {
		printInfo("...Test Pass");
	}
	
	public void onTestFailure(ITestResult result) {
		printInfo("...Test Failed");
		// get screenshot when test fail
		File failureShot = null;
	}
	
	public void onTestFailedWithinSuccessPercentage(ITestResult result) {
		printInfo("...Test fails with success percentage");
	}
	
	public void onStart(ITestContext context) {
		printInfo("...Test begins...");
		
	}
	
	public void onFinish(ITestContext context) {
		printInfo("...Test finish...");
	}
}
