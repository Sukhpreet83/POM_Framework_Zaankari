package in.zaankari.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import in.zaankari.utils.ElementUtil;
import in.zaankari.utils.JavaScriptUtil;

/**
 * @author :-  Sukhpreet
 * @description :- This Class contains the functionality of home Page 
 * @classDetails:- framework -> in.zaankari.pages -> HomePage.java
 */
public class HomePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	
	private By home = By.xpath("//span[text()=' Home ']");
	private By loginButton = By.xpath("//i[text()='person']");
	private By linkMenu = By.xpath("//ul[@class = 'qa-nav-main-list']/li");
	private By SearchTextBox = By.xpath("//input[@name='q']");
	private By SubmitButton = By.xpath("//button[@type='submit']");
	
	private By DivPop = By.xpath("//div[@id='qa-nav-user']");
	
	private By person = By.xpath("//i[text()='person']");
	private By register = By.xpath("//a[@href='./register?to=']");
	
	/**
	 * @author :- Sukhpreet
	 * @description :- This Constructor is use to initialize the driver, Element utility and Java Scripts Utility  
	 * @methodDetails :- framework -> in.zaankari.pages -> HomePage.java
	 */
	public HomePage(WebDriver driver)
	{
		this.driver = driver; 
		elementUtil = new ElementUtil(driver); 
		jsUtil = new JavaScriptUtil(driver);
	}

	public String PageTitle()
	{
		return elementUtil.doGetTitle();
		//return driver.getTitle();
	}
	
	
	public void nagivateToRegisterPage() {
	
		
//		elementUtil.doClick(person);
//		elementUtil.waitForAttributeToBe(DivPop, "class", "active", 10);
//		elementUtil.doClick(register);

//		while(!elementUtil.doGetAttribute(DivPop, "class").equalsIgnoreCase("active"))
//			elementUtil.waitForElementVisible(DivPop, 10);
		
		jsUtil.clickElementByJS(elementUtil.getElement(person));
		jsUtil.clickElementByJS(elementUtil.getElement(register));

		
//		return new RegistrationsPage(driver);
	}
}
