package in.zaankari.test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import in.zaankari.utils.Constants;
import in.zaankari.utils.Errors;

public class HomePageTest extends BaseTest{

	@Test(description="home page title desc")
	public void PageTitleTest()
	{
		String title = hp.PageTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE,Errors.TITLE_ERROR_MESSAGE);
	}

}
