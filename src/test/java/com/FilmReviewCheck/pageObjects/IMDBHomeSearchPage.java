package com.FilmReviewCheck.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IMDBHomeSearchPage {
	
	WebDriver ldriver;
	
	public IMDBHomeSearchPage(WebDriver rdriver){
		
		ldriver= rdriver;
		PageFactory.initElements(rdriver, this);
		
	}
	
	@FindBy(xpath="//input[@class='imdb-header-search__input searchTypeahead__input react-autosuggest__input']")
	@CacheLookup
	WebElement searchbox;
	
	@FindBy(xpath="//ul[@class='react-autosuggest__suggestions-list']/li[1]/a")
	@CacheLookup
	WebElement suggestionFirst;
	
	@FindBy(id="suggestion-search-button")
	@CacheLookup
	WebElement searchButton;
	
	
	public void enterMovieName(String mname) {
		
		searchbox.sendKeys(mname);
	}
	
	
	public void clickOnSearch() {
		
		searchButton.click();
	}
	
	public void selectSuggestion() {
		
		WebDriverWait w = new WebDriverWait(ldriver, Duration.ofSeconds(30));
		WebElement sgg=w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id='react-autowhatever-1--item-0']/a")));
		Actions act= new Actions(ldriver);
		act.moveToElement(sgg).click().build().perform();
		

	}
	
	
	

}
