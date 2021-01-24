package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/***
 * utility class: get the values of properties file
 * 
 * @author cloverli
 * @date 24/01/2021
 *
 */


public class PropertiesReader {

	private static final Properties PROPERTIES = new Properties();
	
	private PropertiesReader() {}
	
	/**
	 * 
	 * @param propertiesPath: the directory of properties file
	 * @return
	 * @throws IOException
	 */
	public static Properties readProperties(String propertiesPath) throws IOException{
		
		InputStream inputStream = new FileInputStream(propertiesPath);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		PROPERTIES.load(bufferedReader);
		return PROPERTIES;
		
	}
	/***
	 * 
	 * @param key
	 * @return value of the property
	 */
	public static String getKey(String key) {
		return PROPERTIES.getProperty(key);
	}
}
