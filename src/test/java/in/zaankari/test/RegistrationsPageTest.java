package in.zaankari.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import in.zaankari.pages.RegistrationsPage;
import in.zaankari.utils.Constants;
import in.zaankari.utils.Errors;
import in.zaankari.utils.ExcelUtil;

public class RegistrationsPageTest extends BaseTest{

	@BeforeClass
	public void regSetup() {
//		rp = hp.nagivateToRegisterPage();
		rp = new RegistrationsPage(driver); 
	}	

	
//	@Test
//	public void isRegHeaderDisplayed()
//	{
//		hp.nagivateToRegisterPage();
//		String actual = rp.RegistrationPageHeader();
//		System.out.println("actual = " + actual);
//		Assert.assertEquals(actual,"Register as a new user");
//		
//	}
	
	@Test
	public void registrationTest()
	{
		hp.nagivateToRegisterPage();
		Assert.assertTrue(rp.Registration("Test0", "test@1234","test0@zaankari.in" , "qtest"), Errors.REGISTRATION_ERROR_MESSAGE);
	}
	

//	@DataProvider(name = "RegDataDP")
//	public Object[][] RegistrationData()
//	{
//		return new Object[][]
//				{
//					{"Test1d0", "test@1234","test1d@zaankari.in" , "q1test"},
//					{"Test2a", "test@1234","test2@zaankari.in" , "q2test"},
//				};
//	}
////	
//	
//	
//	@DataProvider(name = "RegDataDP1")
//	public Object[][] RegistrationData1()
//	{
//		return new ExcelUtil().getTestData(prop.getProperty("ExcelLoc"),Constants.REGISTER_SHEET_NAME);
//	}
//	
//	
//	
//	@Test(dataProvider="RegDataDP")
//	public void registrationTest1(String fn,String pw,String email,String city)
//	{
//		hp.nagivateToRegisterPage();
//		Assert.assertTrue(rp.Registration(fn, pw, email , city), Errors.REGISTRATION_ERROR_MESSAGE);
//	}
}
