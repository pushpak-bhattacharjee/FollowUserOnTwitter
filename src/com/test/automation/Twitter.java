package com.test.automation;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Twitter {
	
	public static WebDriverWait waitMethod(WebDriver driver){
		WebDriverWait wait = new WebDriverWait(driver, 5);
		return wait;
	}
	
	public static String loginToTwitter(WebDriver driver, String userName, String usrPassword, String profileName) {
		String res = "Pass";
		WebDriverWait wait = waitMethod(driver);
		driver.findElement(By.xpath(".//*[@id='doc']/div[1]/div/div[1]/div[2]/button")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("session[username_or_email]")));
		driver.findElement(By.name("session[username_or_email]")).clear();
		driver.findElement(By.name("session[username_or_email]")).sendKeys(userName);
		driver.findElement(By.name("session[password]")).clear();
		driver.findElement(By.name("session[password]")).sendKeys(usrPassword);
		driver.findElement(By.xpath(".//*[@id='login-dialog-dialog']/div[2]/div[2]/div[2]/form/input[1]")).click();

		try{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(profileName)));
			WebElement name = driver.findElement(By.linkText(profileName));
		}catch (Exception e){
			res = "Failed|The login attempt was unsuccessful";
		}
		return res;
	}
	
	public static String searchUsr(WebDriver driver, String searchUsr) throws InterruptedException{
		String res = "";
		int matchCounter = 0;
		driver.findElement(By.id("search-query")).click();
		driver.findElement(By.id("search-query")).sendKeys(searchUsr);
		Thread.sleep(5000);

		List <WebElement> searchedUsers = driver.findElements(By.className("username"));
		
		for (int i = 0; i<searchedUsers.size();i++){
			if((searchedUsers.get(i).getText()).equals("@"+searchUsr)){
				searchedUsers.get(i).click();
				matchCounter++;
				break;
			}
		}
		if (matchCounter == 1){
			res = "Passed";
		}else{
			res = "Failed|The searched User was not found";
		}
		return res;
	}
	
	
	public static String followUsr(WebDriver driver) throws InterruptedException{
		String res = "";
		WebElement followBtn = driver.findElement(By.xpath(".//*[@id='page-container']/div[1]/div/div[2]/div/div/div[2]/div/div/ul/li[6]/div/div/button"));
		String btnText = followBtn.getText();
		if (("Follow").equalsIgnoreCase(btnText)){
			followBtn.click();
			Thread.sleep(5000);
			btnText = followBtn.getText();
			if ("Following".equalsIgnoreCase(btnText)){
				res = "Pass";
			}else{
				res = "Failed|Follow action failed";
			}
		}else{
			res = "Already following";
		}
		return res;
	}
	
	public static String logout(WebDriver driver) throws InterruptedException{
		WebDriverWait wait = waitMethod(driver);
		try {
			driver.findElement(By.id("signout-button")).click();
		}catch (Exception e){
			driver.findElement(By.id("user-dropdown-toggle")).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("signout-button")));
			driver.findElement(By.id("signout-button")).click();
		}
		try{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("signin-link")));
			WebElement loginLink = driver.findElement(By.id("signin-link"));
			return "Passed";
		}catch (Exception loginException){
			return "Failed|Logout was unsuccessful";
		}
		
		
	}
	
}
