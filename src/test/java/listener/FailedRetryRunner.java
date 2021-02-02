package listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * 
 * @author cloverli
 * @date 31/01/2021
 *
 */
public class FailedRetryRunner implements IRetryAnalyzer{
	
	private int retryCount = 0;
	private static final int maxRetryCount = 3;

	public boolean retry(ITestResult iTestResult) {
		if (retryCount < maxRetryCount) {
			retryCount++;
			return true;
		} else {
			return false;
		}
	}
}
