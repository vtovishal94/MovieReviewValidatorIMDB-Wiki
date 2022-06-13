package com.FilmReviewCheck.pageObjects;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WikiSearchPage {
	
	WebDriver locdriver;
	
	
	
	public WikiSearchPage(WebDriver driver){
		
		locdriver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath="//input[@id='searchInput']")
	@CacheLookup
	WebElement sbox;
	
	@FindBy(xpath="(//*[@class='suggestion-text']/h3)[1]")
	@CacheLookup
	WebElement suggReslt;
	

	
	public void enterMovieName(String mname) throws InterruptedException {
		
		sbox.sendKeys(mname);
		Thread.sleep(3000);
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void selectFirstSuggestion() {
		
		Actions act= new Actions(locdriver);
		WebDriverWait wait = new WebDriverWait(locdriver, Duration.ofSeconds(10));
		WebElement suggestion=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@class='suggestion-text']/h3)[1]")));
		
		act.moveToElement(suggestion).click().build().perform();
		
		locdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	
	
	

}
