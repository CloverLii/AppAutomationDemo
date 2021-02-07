
package listener;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.testng.ITestContext;

/**
 * 
 * Write log in testing execution, get screenshot when test fails
 * @author cloverli
 * @date 08/02/2021
 *
 */
public class TestLogListener extends TestListenerAdapter {
	
	private static Logger log = LoggerFactory.getLogger(TestLogListener.class);
   
	@Override
    public void onStart(ITestContext iTestContext) {
        super.onStart( iTestContext );
        log.info( String.format( "====[%s] onStart...====", iTestContext.getName() ) );
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        super.onTestStart( iTestResult );
        log.info( String.format( "====[%s.%s] test starts...====", iTestResult.getInstanceName(), iTestResult.getName()) );
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        super.onTestSuccess( iTestResult );
        log.info( String.format( "====[%s.%s] test pass!====", iTestResult.getInstanceName(), iTestResult.getName()) );
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        super.onTestFailure( iTestResult );
        log.error( String.format( "====[%s.%s] test failed, because ：\n%s====", iTestResult.getInstanceName(), iTestResult.getName(), iTestResult.getThrowable() ));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        super.onTestSkipped( iTestResult );
        log.info( String.format( "====[%s.%s] skip test...====", iTestResult.getInstanceName(), iTestResult.getName()) );
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        super.onFinish( iTestContext );
        log.info( String.format( "====[%s] onFinish...====", iTestContext.getName() ) );
    }
}