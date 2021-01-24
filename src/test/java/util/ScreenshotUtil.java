package util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import testcases.BaseTest;


/**
 * utility class: take and save a screenshot when test failed
 * 
 * @author cloverli
 * @date 24/01/2021
 *
 */
public class ScreenshotUtil {
	
	private static String SCREENSHOT_PATH = System.getProperty("user.dir") + "/target/test-output/screenshot";
	//System.out.println(SCREENSHOT_PATH);

    public static void capture(ITestResult result) {
      
        WebDriver driver = ((BaseTest) result.getInstance()).driver;
        // get the dir to save screenshot file
        File screenshotDir = new File(SCREENSHOT_PATH);
        // create directory if not exist
        if (!screenshotDir.exists() && !screenshotDir.isDirectory()) {
            screenshotDir.mkdirs();
        }
        // get file format from properties file
        String screenshotFormat = PropertiesReader.getKey("screenshot.format");
        // get class name when test failure happens
        String className = result.getInstance().getClass().getSimpleName();
        // set date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd:hh:mm:ss");
        String timeStr = dateFormat.format(new Date());
        // set screenshot name
        String screenshotName = className + "-" + timeStr;
        try {
            // take screenshot
            File sourcefile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // save the screenshot as file
            FileUtils.copyFile(sourcefile, new File(SCREENSHOT_PATH + File.separator + screenshotName + screenshotFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
