package reportManager;

import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import testCases.BaseClass;


public class TestListener extends BaseClass implements ITestListener {

	//Extent Report Declarations
	protected static ExtentReports extent = ExtentManager.createInstance();
	protected static ExtentTest logger;
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " started!"));
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		logger=extent.createTest(result.getName()); // create new entry in the report
		logger.log(Status.PASS,MarkupHelper.createLabel(result.getName(),ExtentColor.GREEN));
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				BaseClass.captureScreen(driver, result.getName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logger=extent.createTest(result.getName()); // create new entry in the report
		logger.log(Status.FAIL,MarkupHelper.createLabel(result.getName(),ExtentColor.RED)); // send the passed information to the report with GREEN color highlighted

		String screenshotPath=System.getProperty("user.dir")+"\\screenshots\\"+result.getName()+".png";
		File f = new File(screenshotPath); 
		if(f.exists())
		{
			try {
				logger.fail("Screenshot is below: " + logger.addScreenCaptureFromPath(screenshotPath));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger=extent.createTest(result.getName()); // create new entry in the report
		logger.log(Status.SKIP,MarkupHelper.createLabel(result.getName(),ExtentColor.ORANGE));
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}
	
	@Override
	public void onStart(ITestContext context) {
		System.out.println("Test is starting...");
	}
	
	@Override
	public void onFinish(ITestContext context) {
		System.out.println(("Test is ending..."));
		extent.flush();
	}
}
