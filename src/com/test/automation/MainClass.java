package com.test.automation;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Properties;

public class MainClass {
	static WebUtils webUtils = new WebUtils();
	public static void main(String[] args) throws InterruptedException, IOException{
		// TODO Auto-generated method stub

		
		CommonUtils commonUtils = new CommonUtils();
		Properties prop = commonUtils.readProp();

		String url = prop.getProperty("url");
		String usrName = prop.getProperty("usrName");
		String usrPassword = prop.getProperty("usrPassword");
		String profileName = prop.getProperty("profileName");
		String searchUsr = prop.getProperty("searchUsr");

		WebDriver driver = WebUtils.launchBrowser(url);
		
		String result = Twitter.loginToTwitter(driver, usrName, usrPassword, profileName);
		System.out.println("Login result is:- "+result);
		if(!("Pass".equalsIgnoreCase(result))){
			WebUtils.closeBrowser(driver);
			System.exit(0);
		}
		String searchRes = Twitter.searchUsr(driver, searchUsr);
		System.out.println("Search Result is:- "+searchRes);
		if(!("Passed".equalsIgnoreCase(searchRes))){
			WebUtils.closeBrowser(driver);
			System.exit(0);
		}
		String followRes = Twitter.followUsr(driver);
		System.out.println("Follow result is:- "+followRes);
		if(!("Pass".equalsIgnoreCase(followRes))){
			WebUtils.closeBrowser(driver);
			System.exit(0);
		}
		String logoutres = Twitter.logout(driver);
		System.out.println("Logout result is:- "+logoutres);		
		WebUtils.closeBrowser(driver);
	}

}
