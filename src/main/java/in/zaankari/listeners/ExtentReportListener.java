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

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import in.zaankari.factory.Factory;


public class ExtentReportListener implements ITestListener{

//	private static final String OUTPUT_FOLDER = "./build/";
//	private static final String FILE_NAME = "TestExecutionReport.html";
//	private static ExtentReports extentReports;

	Factory f = new Factory();
	WebDriver edriver;
	
	public static ExtentTest extentTest;
//	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	private static ExtentReports extent = init();
	
	
	private static ExtentReports init() {
		
		String OUTPUT_FOLDER = "./build/";
		String FILE_NAME = "TestExecutionReport.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(reporter);
		
		reporter.config().setReportName("Automation Test Results11");
		
		extentReports.setSystemInfo("System", "MAC");
		extentReports.setSystemInfo("Author", "Naveen AutomationLabs");
		extentReports.setSystemInfo("Build#", "1.1");

		return extentReports;
	}

	public synchronized void onStart(ITestContext context) {
		
		System.out.println(context.getStartDate());
		System.out.println("Test Suite started! extent report on start");
	}

	public synchronized void onFinish(ITestContext context) {
		System.out.println(("Test Suite is ending!"));
		extent.flush();
//		test.remove();
	}

	public synchronized void onTestStart(ITestResult result) {

		ITestContext context = result.getTestContext();
		edriver = (WebDriver) context.getAttribute("WebDriver");
		
		String methodName = result.getMethod().getMethodName(); // method name is ame of method
		String qualifiedName = result.getMethod().getQualifiedName();// qualified name is name od method along with path that is com.qa.
		int last = qualifiedName.lastIndexOf(".");
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");
		String className = qualifiedName.substring(mid + 1, last);

		System.out.println(methodName + " started!");
		
		extentTest = extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());

		extentTest.assignCategory(result.getTestContext().getSuite().getName());
//		extentTest.assignCategory(className);
		
		extentTest.getModel().setStartTime(getTime(result.getStartMillis()));
		
//		test.set(extentTest);
//		test.get().getModel().setStartTime(getTime(result.getStartMillis()));
	}

	public synchronized void onTestSuccess(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " passed!"));
	//	extentTest.pass("Test passed");

		//Markup
		Markup m = MarkupHelper.createLabel("Test Pass", ExtentColor.BLUE);
		extentTest.pass(m);	
		extentTest.getModel().setEndTime(getTime(result.getEndMillis()));
	}

	public synchronized void onTestFailure(ITestResult result) {

//		ITestContext context = result.getTestContext();
//		edriver = (WebDriver) context.getAttribute("WebDriver");
		
		System.out.println((result.getMethod().getMethodName() + " failed!"));
//		test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
//		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
		extentTest.fail(result.getThrowable(),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		extentTest.getModel().setEndTime(getTime(result.getEndMillis()));
//		extentTest.log(Status.FAIL, "fauiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiilllllllllllllllllllllllllllllllllllllllllllllll log");

		System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");
		int l = result.getParameters().length;
		
		StringBuilder s = new StringBuilder();

		for(int i=0;i<l;i++)
		{
			System.out.println(result.getParameters()[i]);
			s.append(result.getParameters()[i] + ",");
		}
		extentTest.log(Status.INFO,"Method Parameter passed " +s.toString());
	}

	public synchronized void onTestSkipped(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " skipped!"));
//		test.get().skip(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
//		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
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
		try 
		{
			FileUtils.copyFile(scr, Dest);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return Path;
	}
}
