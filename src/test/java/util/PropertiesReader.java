package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Utility class: Read properties file and get values
 * 
 * @author cloverli
 * @date 07/02/2021
 *
 */
public class PropertiesReader {

	private static Logger log = LoggerFactory.getLogger(PropertiesReader.class);

	private static final Properties PROPERTIES = new Properties();
	
	private PropertiesReader() {}
	
	// read properties file from file path 
	public static Properties readProperties(String propertiesPath) throws IOException{
		
		InputStream inputStream = new FileInputStream(propertiesPath);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		PROPERTIES.load(bufferedReader);
		log.info(String.format("====read properties file: %s ====", propertiesPath));
		
		return PROPERTIES;		
	}
	
	// get value of property
	public static String getKey(String key) {
		String propertyValue = PROPERTIES.getProperty(key);
		log.info(String.format("====get value of {%s} from properties file: %s ====", key, propertyValue));
		
		return propertyValue;
	}
}
