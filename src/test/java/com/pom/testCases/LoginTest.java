package com.pom.testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.pom.pageObjects.*;
import common.*;
import com.pom.utilities.*;

//@Listeners(Reporting.class)
public class LoginTest extends BaseClass {

	BaseClass baseObj = new BaseClass();

	@Test(priority=1)
	public void Test1() throws Exception
	{
        System.out.println("TEST 1 starts:");
		logger.info("URL is opened");

		// Verifying Url of the application
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl,baseURL);
		logger.info("Url for the Application matches");

		// Verifying the page Title
		if(driver.getTitle().equals("OrangeHRM"))
		{
			Assert.assertTrue(true);
			logger.info("Page title matches");
		}
		else
		{
			baseObj.captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
			logger.error("Page tilte do not match");
		}

		// Login functionality
		LoginPage loginObj = new LoginPage(driver);
		loginObj.setUserName(username);
		logger.info("Entered username");

		loginObj.setPassword(password);
		logger.info("Entered password");

		// Verigying if the login button is enabled or not
		boolean login = driver.findElement(By.id("btnLogin")).isEnabled();
		if(login)
		{
			Assert.assertTrue(true);
			logger.info("Login button is enabled, proceed further");
		}
		else
		{
			baseObj.captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
			logger.warn("Login button is disabled");
		}

		loginObj.clickSubmit(); // Login button clicked after checking enable action
		logger.info("Clicked on Login button");

		// Verifying Application Logo after login
		boolean logo = driver.findElement(By.xpath("//*[@id=\"branding\"]/a[1]/img")).isDisplayed();
		if(logo)
		{
			Assert.assertTrue(true);
			logger.info("Page logo displayed");
		}
		else
		{
			baseObj.captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
			logger.error("Page logo is not displayed ");
		}
		
		baseObj.logOut();
		System.out.println("TEST 1 ends.");
	}

	
	@Test(priority=2)
	public void Test2() throws Exception
	{
		System.out.println(" ");
		System.out.println("TEST 2 starts:");
		baseObj.loginCommon();
		
		//Veryfying invalid credentials
		boolean credentials = driver.findElement(By.id("spanMessage")).isDisplayed();
		if(credentials)
		{
			baseObj.captureScreen(driver,"loginTest");
			Assert.assertTrue(true);
			logger.info("Invalid credentials provided and login unsuccessful");
		}
		else
		{
			Assert.assertTrue(false);
			logger.warn("Login successful");
		}
		
		//Verifying Login is successful or not
		boolean home = driver.findElement(By.id("welcome")).isDisplayed();
		if(home)
		{
			Assert.assertTrue(true);
			logger.info("Login successful and Home page displayed");
		}
		else
		{
			baseObj.captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
			logger.warn("Login unsuccessful");
		}
		
		baseObj.logOut();
		System.out.println("TEST 2 ends.");
	}
    
         

}
