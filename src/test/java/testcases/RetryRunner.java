package testcases;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Retry failed test case up to 3 times
 * 
 * @author cloverli
 * @date 25/01/2021
 *
 */
public class RetryRunner implements IRetryAnalyzer {
	
	private int retryCount = 0;
	private final int maxRetryCount = 3;
	
	public boolean retry(ITestResult iTestResult) {
		if(retryCount < maxRetryCount) {
			retryCount ++;
			return true;
		}
		return false;
	}
}
