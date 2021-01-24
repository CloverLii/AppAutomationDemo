package util;

import java.util.List;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class GetElements{

	AndroidDriver driver = null;
	
	// get elements by className and index
	public List<AndroidElement> getElementsByClassAndIndex(String className,int index){
		List<AndroidElement> eleList = null;
		eleList = driver.findElementsByAndroidUIAutomator("new UiSelector().className("+className+").index("+index+")");
		return eleList;
	} 
	
	// get elements by className and index and clickability
	public List<AndroidElement> getElementsByClassAndIndexAndClickable(String classname,int index){	
		List<AndroidElement> eleList =null;
		eleList = driver.findElementsByAndroidUIAutomator("new UiSelector().className("+classname+").index("+index+").clickable(true)");
		return eleList;	
	}
	
	public AndroidElement getElementByIndex(int index){
		return (AndroidElement) driver.findElementByAndroidUIAutomator("new UiSelector().index("+index+")");
		}
	
	public List<AndroidElement> getElementByCalss(int className){
		
		List<AndroidElement> eleList = null;
		eleList = driver.findElementsByAndroidUIAutomator("new UiSelector().className("+className);
		return eleList;
	}
}
