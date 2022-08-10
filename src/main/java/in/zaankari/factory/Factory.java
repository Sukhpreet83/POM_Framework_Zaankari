package in.zaankari.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This Class is a Factory class which is use to initialize the WebDriver and properties file.
 * @author :- Sukhpreet
 * @see framework -> in.zaankari.factory -> Factory.java
 */
public class Factory {

	public WebDriver fdriver;
	public static String highlight;
	OptionsManager Options;

//	public static final Logger log = Logger.getLogger(String.valueOf(Factory.class));
	public static final Logger log = Logger.getLogger("dLogger");

//	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();


	/**
	 * @author :- Sukhpreet
	 * @description :- This method is used to initialize the WebDriver and launch the Browser.
	 * @methodDetails :- framework -> in.zaankari.factory -> Factory.java -> initDriver
	 * @returnType :- WebDriver
	 */
	public WebDriver initDriver(Properties prop) {

		
/*		log.setLevel(Level.WARN);
		log.debug("Debug Message!");
		log.info("Info Message!");
		log.warn("Warn Message!");
		log.error("Error Message!"); */
		
		
		
		String browserName = prop.getProperty("browser");
		highlight = prop.getProperty("highlight");

		Options = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			fdriver = new ChromeDriver(Options.getChromeOptions());
			log.info("Chrome Driver is inilizzed...........");
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			fdriver = new FirefoxDriver(Options.getFirefoxOptions());
			log.info("FirefoxDriver Driver is inilizzed...........");
			// tlDriver.set(new FirefoxDriver(Options.getFirefoxOptions()));
		} else if (browserName.equalsIgnoreCase("Safari")) {
			fdriver = new SafariDriver();

		}

		else {
			System.out.println("Please pass correct Browser Name" + browserName);
			log.info("No browser is inilizzed...........");
		}

		fdriver.get(prop.getProperty("url"));
		fdriver.manage().window().maximize();
		fdriver.manage().deleteAllCookies();

//		getDriver().get(prop.getProperty("url"));
//		getDriver().manage().window().maximize();
//		getDriver().manage().deleteAllCookies();

		return fdriver;
//		return getDriver();
	}

//public WebDriver getDriver()
//{
//	return tlDriver.get(); 
//}


	/**
	 * @author :- Sukhpreet
	 * @description :- This method is use to to initialize the Properties file.
	 * @methodDetails :- framework -> in.zaankari.factory -> Factory.java -> init_properties
	 * @returnType :- Properties
	 */
	public Properties init_properties() {

		Properties prop = null;
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop = new Properties();
			prop.load(ip);
		}

		catch (FileNotFoundException e) {

			e.printStackTrace();
			log.error("Error file not found", e);
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

}
