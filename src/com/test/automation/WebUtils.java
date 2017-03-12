package com.test.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebUtils {

	public static WebDriver launchBrowser(String url){
		WebDriver driver = new FirefoxDriver();
		driver.get(url);
		return driver;
	}
	public static void closeBrowser(WebDriver driver){
		driver.close();
	}
}
