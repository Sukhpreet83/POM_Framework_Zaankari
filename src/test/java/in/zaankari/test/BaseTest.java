package in.zaankari.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import in.zaankari.factory.Factory;
import in.zaankari.listeners.WebDriverListener;
import in.zaankari.pages.HomePage;
import in.zaankari.pages.RegistrationsPage;

public class BaseTest {
	
	WebDriver driver1;
	Factory df;
	Properties prop;
	
	HomePage hp;
	RegistrationsPage rp;
	
	EventFiringWebDriver driver;

	@BeforeTest
	public void Setup(ITestContext context)
	{
		df = new Factory();
		prop = df.init_properties();
		driver1 = df.initDriver(prop);
		
		hp = new HomePage(driver1);
	
		context.setAttribute("WebDriver", driver1);
		System.out.println("base test before start");
		
		driver = new EventFiringWebDriver(driver1);
		WebDriverListener eCapture = new WebDriverListener();
		
		driver.register(eCapture);

		
	}
	
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}
	
}
