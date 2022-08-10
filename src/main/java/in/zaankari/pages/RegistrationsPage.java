package in.zaankari.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import in.zaankari.utils.Constants;
import in.zaankari.utils.ElementUtil;
import in.zaankari.utils.JavaScriptUtil;

public class RegistrationsPage {

	WebDriver driver;
	ElementUtil elementUtil;
	
	private By RegHeader = By.xpath("//h1[contains(text(),'Register as a new user')]");
	private By Username = By.xpath("//input[@id='handle']");
	private By Password = By.xpath("//input[@id='password']");
	private By Email = By.xpath("//input[@id='email']");
	private By city = By.xpath("//input[@id='usercity']");
	
	private By recaptcha = By.xpath("//span[@id='recaptcha-anchor']");
	private By Agree = By.xpath("//input[@id='terms']");
	private By RegButton = By.xpath("//input[@value='Register']");
	
	private By EmailConf = By.xpath("//h1[contains(text(), 'Email Address Confirmation')]");
	
	private By logoutmain = By.xpath("//img[@src='./?qa=image&qa_blobid=8190970936983512754&qa_size=52']");
	private By logout = By.xpath("//a[@href='./logout']");
	
	public RegistrationsPage(WebDriver driver) {
		this.driver = driver; 
		elementUtil = new ElementUtil(driver); 
	}

	
	public String RegistrationPageHeader()
	{
		return elementUtil.doGetText(RegHeader);
	}
	
	public boolean Registration(String un,String pw, String mail, String Cy)
	{
		elementUtil.doSendKeys(Username, un);
		elementUtil.doSendKeys(Password, pw);
		elementUtil.doSendKeys(Email, mail);
		elementUtil.doSendKeys(city, Cy);
		
//		elementUtil.waitForFrameAndSwitchToIt(elementUtil.getElement(By.xpath("//iframe[@title='reCAPTCHA']")), 10);
//		elementUtil.doClick(recaptcha);
//		driver.switchTo().defaultContent();
		
		elementUtil.doClick(Agree);
		elementUtil.doClick(RegButton);
		
		String message = elementUtil.doGetText(EmailConf);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
				e1.printStackTrace();
		}
		
		if(message.contains(Constants.REGISTER_SUCCESS_MESSAGE))
		{
			elementUtil.doClick(logoutmain);
			try 
			{
				Thread.sleep(5000);
				elementUtil.doClick(logout);
				Thread.sleep(5000);
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			return true;
		}
		return false;
					
	}
	
}
