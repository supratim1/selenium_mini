package common;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.pom.utilities.ReadConfig;

public class BaseClass {
	
    ReadConfig readconfig=new ReadConfig();
	
	public String baseURL=readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	public String password1=readconfig.getWrongPassword();
	public static WebDriver driver;
	
	public static Logger logger;
	
	@Parameters("browser")
	
	// Crossbrowser Testing
	
	@BeforeTest
	public void setup(String browser) throws Exception
	{			
		logger = LogManager.getLogger(BaseClass.class);
		//PropertyConfigurator.configure("Log4j.properties");
		
		if(browser.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			driver=new ChromeDriver();
		}
		else if(browser.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
			driver = new FirefoxDriver();
		}
		else if(browser.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver",readconfig.getIEPath());
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get(baseURL);
	
	}
	
	@AfterTest
	public void tearDown() throws Exception
	{
		driver.quit();
	}
	
	//For screenshot operation common function
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public String randomestring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}
	
	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
	
	//Common login function for every module
    public void loginCommon() throws Exception
    {
      	findElement(By.name("txtUsername")).sendKeys(username);
      	logger.info("Username entered");
      	
      	findElement(By.name("txtPassword")).sendKeys(password1);
      	logger.info("Password entered");
      	
      	findElement(By.id("btnLogin")).click();
      	logger.info("Clicked on Login Button");
    }
    
    //Common logout function for every module
    public void logOut() throws Exception
    {
    	findElement(By.id("welcome")).click();
		logger.info("Navigated to Home Page Welcome section");
		
		findElement(By.linkText("Logout")).click();
		logger.info("User logged out successfully");
    }
    
    //Used for Web element bordering
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
