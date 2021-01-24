
package listener;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.ITestContext;
import util.ScreenshotUtil;

/**
 * 
 * @author cloverli
 * @date 24/01/2021
 *
 */

public class TestLogListener extends TestListenerAdapter {
   
	@Override
    public void onStart(ITestContext iTestContext) {
        super.onStart( iTestContext );
        //log.info( String.format( "====================%s start testing...===================", iTestContext.getName() ) );
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        super.onTestStart( iTestResult );
       // log.info( String.format( "========%s.%s test start...========", iTestResult.getInstanceName(), iTestResult.getName()) );
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        super.onTestSuccess( iTestResult );
       // log.info( String.format( "========%s.%s test pass========", iTestResult.getInstanceName(), iTestResult.getName()) );
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        super.onTestFailure( iTestResult );
       // log.error( String.format( "========%s.%s test failed, because ：\n%s========", iTestResult.getInstanceName(), iTestResult.getName(), iTestResult.getThrowable() ));
        // get and save screenshot when test failed
        ScreenshotUtil.capture(iTestResult);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        super.onTestSkipped( iTestResult );
        //log.info( String.format( "========%s.%s skip test...========", iTestResult.getInstanceName(), iTestResult.getName()) );
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        super.onFinish( iTestContext );
        //.info( String.format( "====================%s end testing...====================", iTestContext.getName() ) );
    }
}