package listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import util.PropertiesReader;

/**
 * 
 * @author cloverli
 * @date 08/02/2021
 *
 */
public class FailedRetryRunner implements IRetryAnalyzer{
	
private static Logger log = LoggerFactory.getLogger(FailedRetryRunner.class);
	
	private int retryCount = 0;
	
	// get 'maxRetryCount' from config.properties file
	private static final int maxRetryCount = Integer.parseInt(PropertiesReader.getKey("conf.maxRetryCount"));

	public boolean retry(ITestResult iTestResult) {
		if (retryCount < maxRetryCount) {
			retryCount++;
			log.info(String.format("====retry failed test: %d", retryCount));
			return true;
		} else {
			return false;
		}
	}
}
