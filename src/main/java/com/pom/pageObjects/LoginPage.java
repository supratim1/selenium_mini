package com.pom.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class LoginPage {

    WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	private By usernameTextBox = By.name("txtUsername");
	private By passwordTextBox = By.name("txtPassword");
	private By loginButton = By.id("btnLogin");
	
	public void setUserName(String username) throws Exception
	{
		findElement(usernameTextBox).sendKeys(username);
	}
	
	public void setPassword(String password) throws Exception
	{
		findElement(passwordTextBox).sendKeys(password);
	}
	
	public void clickSubmit() throws Exception
	{
		findElement(loginButton).click();
	}	
	
	
	public WebElement findElement(By by) throws Exception 
	{

		WebElement element = driver.findElement(by);  
		
		if (driver instanceof JavascriptExecutor) 
		{
		 ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", element);
	 
		}
		return element;
	}
}