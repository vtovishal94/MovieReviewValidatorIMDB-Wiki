package com.ReviewTest.testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.FilmReviewCheck.Utilities.ReadConfig;


public class BaseClass {

	ReadConfig readConfig= new ReadConfig();
	
	
	public String ImdbUrl=readConfig.getImdbURL();
	public String wikiUrl=readConfig.getWikiURL();
	public String movieNameToSearch=readConfig.getMovieName();
	public static WebDriver driver;
	public static Logger logger;
	
	//@Parameters("browser")
	@BeforeClass
	public void setup() {
		    
		logger=Logger.getLogger("Moview Review check");
		PropertyConfigurator.configure("log4j.properties");
				
		
//		if(browser.equalsIgnoreCase("chrome")) {
			
			System.setProperty("webdriver.chrome.driver",readConfig.getChromePath());
			driver=new ChromeDriver();

//		}
//		else if(browser.equalsIgnoreCase("firefox")) {
//			System.setProperty("webdriver.gecko.driver",readConfig.getFirefoxPath());
//			driver=new FirefoxDriver();
//
//		}
	}
	
	
	
	@AfterClass
	public void tearDown() throws InterruptedException {
		//Thread.sleep(10000);
		driver.quit();
	}
	
	public void captureScreenshot(WebDriver driver, String tname) throws IOException {
		
		TakesScreenshot ts= (TakesScreenshot) driver;
		
		File source= ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshots"+tname+".png");
		
		FileUtils.copyFile(source, target);
		logger.info("Screenshot taken ");
		
	}
	
}
