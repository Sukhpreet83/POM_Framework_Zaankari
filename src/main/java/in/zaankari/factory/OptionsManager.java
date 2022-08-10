package in.zaankari.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;



/**
 * @author :-  Sukhpreet
 * @description :- This Class is used to add the arguments to the browser.
 * @classDetails:- framework -> in.zaankari.factory -> OptionsManager.java
 */
public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;	
	private FirefoxOptions fo;
	
	

	/**
	 * @author :- Sukhpreet
	 * @description :- This Constructor is use to initialize the Properties Object.
	 * @methodDetails :- framework -> in.zaankari.factory -> OptionsManager.java
	 */
	public OptionsManager(Properties prop)
	{
		this.prop=prop;
	}
	
	
	
	/**
	 * @author :- Sukhpreet
	 * @description :- This method is use to add the arguments to Chrome Browser
	 * @methodDetails :- framework -> in.zaankari.factory -> OptionsManager.java -> getChromeOptions
	 * @returnType :- ChromeOptions
	 */
	public ChromeOptions getChromeOptions()
	{
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
			co.addArguments("--headless");
			
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim()))
			co.addArguments("--incognito");
	
		return co;
	}
	
	
	/**
	 * @author :- Sukhpreet
	 * @description :- This method is use to to add the arguments to Firefox Browser
	 * @methodDetails :- framework -> in.zaankari.factory -> OptionsManager.java -> getFirefoxOptions
	 * @returnType :- FirefoxOptions
	 */
	public FirefoxOptions getFirefoxOptions()
	{
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
			fo.addArguments("--headless");
			
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim()))
			fo.addArguments("--incognito");
	
		return fo;
	}
	
}
