package com.test.automation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class CommonUtils {
	
	InputStream inputStream;
	
	public  Properties readProp() throws IOException{
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("C:\\Users\\Pushpak\\workspace\\TestAutomation\\TestData.properties");
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	return prop;
	}
}
