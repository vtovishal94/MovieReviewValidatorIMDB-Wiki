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
import org.testng.Assert;

public class IMDBResultPage {

WebDriver ldriver;

public String ImdbMM;
public String ImdbDD;
public String ImdbYY;
	
	public IMDBResultPage(WebDriver rdriver){
		
		ldriver= rdriver;
		PageFactory.initElements(rdriver, this);
		
	}

	
	
	@FindBy(xpath="(//div[@data-testid='title-details-section']/ul/li/div)[1]/ul/li/a")
	@CacheLookup
	WebElement releaseDate;
	
	
//	@FindBy(xpath="(//div[@data-testid='title-details-section']/ul/li/div)[2]/ul/li/a")
//	@CacheLookup
//	WebElement country;
	
	
	public void verifyMovieTitleonPage(String mname) {
		
		String resMname= ldriver.getTitle();
		
		if(resMname.contains(mname)) {
			Assert.assertTrue(true);
		}
		else {
			Assert.assertFalse(true);
		}
		
	}
	
	
	@SuppressWarnings("deprecation")
	public String getReleaseDate() throws InterruptedException {
	
	String relDate= releaseDate.getText();
	
	if(!relDate.contains("India")) {
		ldriver.findElement(By.xpath("(//div[@data-testid='title-details-section']/ul/li/div)[1]/ul/li/a")).click();
		Thread.sleep(3000);
		String indianRDate= ldriver.findElement(By.xpath("(//tr[@class='ipl-zebra-list__item release-date-item']/td)[17]")).getText();
		
		
		ldriver.navigate().back();
		ldriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		return indianRDate;
		
	}else {
		
		return relDate;
	
	}
	
	
}
	
	
	public String getCountry() {
		
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(10));
		WebElement countryImdb=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@data-testid='title-details-origin']/div")));
		
		Actions act=new Actions(ldriver);
		act.moveToElement(countryImdb).build().perform();
		
		
		String countryName= countryImdb.getText();
		
		return countryName;
	}


}