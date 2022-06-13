package com.FilmReviewCheck.pageObjects;




import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class WikiResultPage {
	
	WebDriver ldriver;
	//WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(10));
	
	
	public WikiResultPage(WebDriver driver){
		
		ldriver= driver;
		PageFactory.initElements(driver, this);
		
	
	}
	
//	public void findResultData() {
//	
//		List<WebElement> allRowElem= ldriver.findElements(By.xpath("//table[@class='infobox vevent']/tbody/tr"));
//	
//		System.out.println("Tr size is "+allRowElem.size());
//		
//		for(int i=1; i<allRowElem.size();i++) {
//			
//			
//			String specificRow="//table[@class='infobox vevent']/tbody/tr["+i+"]";
//			
//			List<WebElement> allCoulmnsThElem=ldriver.findElements(By.xpath(specificRow+"/th"));
//			List<WebElement> allCoulmnsTdElem=ldriver.findElements(By.xpath(specificRow+"/td"));
//			
//			for(WebElement cell1:allCoulmnsThElem) {
//				
////				WebElement th=allCoulmnsElem.get(0);
////				String thdata=th.getText();
////				System.out.println("Th data is "+thdata);
////				
////				WebElement td=allCoulmnsElem.get(1);
////				String tddata=td.getText();
////				System.out.println("Td data is "+tddata);
////				System.out.println();
//				cell1.getTagName();
//				
//				
//			}
//					
//			
//			
//			
//		}
	
	@FindBy(xpath="//table[@class='infobox vevent']/tbody/tr[12]/td/div/ul/li")
	@CacheLookup
	WebElement relDate;
	
	
	public String getReleaseDate() {
		
		String releaseDate= relDate.getText();
		
		return releaseDate;
	}
	
	@FindBy(xpath="//table[@class='infobox vevent']/tbody/tr[14]/td")
	@CacheLookup
	WebElement country;
	
    public String getCountry() {
		
		String countryName= country.getText();
		
		return countryName;
	}
	
	
	}



