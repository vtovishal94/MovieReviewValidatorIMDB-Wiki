package com.FilmReviewCheck.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties pro;
	
	public ReadConfig() {
		File src= new File("./Configurations/config.properties");
		
		try {
			
			FileInputStream fip=new FileInputStream(src);
			pro= new Properties();
			pro.load(fip);
		}
		catch(Exception e){
			System.out.println("Exception in loading config properties file :"+e.getMessage());
		}
	}
	
	
	public String getImdbURL() {
		
		String IUrl=pro.getProperty("ImdbUrl");
		
		return IUrl;
	}
	
    
	public String getWikiURL() {
		
		String WUrl=pro.getProperty("wikiUrl");
		
		return WUrl;
	}
	
    
     public String getMovieName() {
		
		String movieName=pro.getProperty("movieNameToSearch");
		
		return movieName;
	}
	
     public String getChromePath() {
 		
 		String chromePath=pro.getProperty("chromepath");
 		
 		return chromePath;
 	}
     
     
     public String getFirefoxPath() {
  		
  		String firefoxPath=pro.getProperty("firefoxPath");
  		
  		return firefoxPath;
  	}

}
