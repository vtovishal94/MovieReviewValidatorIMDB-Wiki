package com.ReviewTest.testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.FilmReviewCheck.pageObjects.IMDBHomeSearchPage;
import com.FilmReviewCheck.pageObjects.IMDBResultPage;
import com.FilmReviewCheck.pageObjects.WikiResultPage;
import com.FilmReviewCheck.pageObjects.WikiSearchPage;


public class MovieReviewTest extends BaseClass {

	public String IMDBCountry;
	public String IMDBReleaseDate;
	public String wikiReleaseDate;
	public String wikiCountryName;
	
	public String wikiDD;
	public String wikiMM;
	public String wikiYY;
	
	public String ImdbMM;
	public String ImdbDD;
	public String ImdbYY;
	
	@SuppressWarnings("deprecation")
	@Test
	public void ImdbSearchTest() {
		
		driver.get(ImdbUrl);
		logger.info("IMDB Web URL opened");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		IMDBHomeSearchPage hp= new IMDBHomeSearchPage(driver);
		
		hp.enterMovieName(movieNameToSearch);
		
		logger.info("Movie Name enter into IMDB Search Box");
		
		hp.selectSuggestion();
		
	    logger.info("First suggestion selected");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String pageTitle=driver.getTitle();
		
		
		if(pageTitle.contains(movieNameToSearch)) {
			Assert.assertTrue(true);
			logger.info("Page title of searched movie is matched on IMDB result page");
		}
		else {
			Assert.assertTrue(false);
			logger.info("Page title of searched movie is not matched ");
		}
		
		
		
	}
	
	@Test
	public void verifyImdbMovieReviewDetails() throws InterruptedException {
		
		IMDBResultPage rp= new IMDBResultPage(driver);
		
		rp.verifyMovieTitleonPage(movieNameToSearch);
		
		logger.info("Page title of searched movie is matched on Result page ");
		
		Thread.sleep(4000);
		
		IMDBCountry=rp.getCountry();
		
		IMDBReleaseDate=rp.getReleaseDate();
		logger.info("Release Date of IMDB searched movie is captured ");
		
		    String indD[]=IMDBReleaseDate.split(" ");
			ImdbDD=indD[0];
			ImdbMM=indD[1];
			ImdbYY=indD[2];
		
		
		
	}
	
	@SuppressWarnings("deprecation")
	@Test(dependsOnMethods= {"verifyImdbMovieReviewDetails"})
	public void testingWikiReviewDetails() throws InterruptedException {
		driver.manage().window().maximize();
		logger.info("Window is maximized for next wiki URL page ");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get(wikiUrl);
		logger.info("Page is now navigated to wikipedia url ");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	
		WikiSearchPage ws=new WikiSearchPage(driver);

		ws.enterMovieName(movieNameToSearch);
		logger.info("Movie name is entered into wiki search box ");
		
		Thread.sleep(3000);
		 ws.selectFirstSuggestion();
		logger.info("First suggestion of wiki entered movie is selected ");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WikiResultPage wp= new WikiResultPage(driver);
		
		
		 wikiReleaseDate=wp.getReleaseDate();
		 logger.info("Wiki searched movie release date is captured ");
		 
		 String relD[]= wikiReleaseDate.split(" ");
		 
		  wikiDD=relD[0];
		  wikiMM=relD[1];
		  wikiYY=relD[2];
		 
		 
		 wikiCountryName=wp.getCountry();
		 logger.info("Wiki searched movie country is captured ");
		 

		
	}
	
	
	
	@Test(dependsOnMethods= {"testingWikiReviewDetails"})
	public void verifyReleaseDateOnIMDBAndWiki() throws IOException {
		
		
		if(wikiDD.equals(ImdbDD) && wikiMM.equalsIgnoreCase(ImdbMM) && wikiYY.equalsIgnoreCase(ImdbYY)) {
			
			logger.info("Extracted Release date is matched on both IMDB & Wikipedia page for given movie");
			
			System.out.println("Review details matched in between IMDB & Wiki");
			Assert.assertTrue(true);
			
			if(wikiCountryName.equalsIgnoreCase(IMDBCountry)) {
				
				Assert.assertTrue(true);
				logger.info("Extracted Country date is matched on both IMDB & Wikipedia page for given movie");
			}
			else {
				Assert.assertTrue(false);
				captureScreenshot(driver, "verifyReleaseDateOnIMDBAndWiki");
				logger.info("Extracted Country date is not matched on both IMDB & Wikipedia page for given movie but Release date is matched");
			}
			
		}
		else {
			
			logger.info("Extracted Release date is not matched on both IMDB & Wikipedia page");
			Assert.assertTrue(false);
		}
		
		
		
	}
	
	
}
