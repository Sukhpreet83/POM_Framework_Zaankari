package in.zaankari.listeners;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.reporters.*;

import com.aventstack.extentreports.Status;

public class TestNGReportListener implements ITestListener{

	WebDriver edriver;

	
	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		ITestContext context = result.getTestContext();
		edriver = (WebDriver) context.getAttribute("WebDriver");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		Reporter.log("Pass");
		Reporter.getCurrentTestResult().setStatus(result.SUCCESS);
	}

	@Override
	public void onTestFailure(ITestResult result) {

		Reporter.log("Fail oijiuoiouio");
		Reporter.getCurrentTestResult().setStatus(result.SUCCESS);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	
	
	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
	
	
	public String getScreenshot()
	{
		
		File scr =((TakesScreenshot)edriver).getScreenshotAs(OutputType.FILE);
		String Path = System.getProperty("user.dir")+"/screenshot/" + System.currentTimeMillis()+".png";
		
		File Dest = new File(Path);
		try {
			FileUtils.copyFile(scr, Dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Path;
		
	}
	
}
