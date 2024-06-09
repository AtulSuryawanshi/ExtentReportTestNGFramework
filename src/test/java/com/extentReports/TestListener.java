package com.extentReports;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {
	
	// Screenshot class object
	GetScreenshotPath getScreenPath = new GetScreenshotPath();
	// ExtentReport class object
	ExtentReports extent = ExtentReportsConfig.extentinitilization();
	//ExtentTest class initilization
	ExtentTest test;
	//Thread safe class
	private static ThreadLocal<ExtentTest> extentThreadSafeTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
		extentThreadSafeTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		test.log(Status.PASS, "Test case passed: " + result.getMethod().getMethodName());
		extentThreadSafeTest.set(test);
	}

	@Override
	public void onTestFailure(ITestResult result) {

		WebDriver driver = null;
		Object testObject = result.getInstance();
		Class clazz = result.getTestClass().getRealClass();

		try {

			driver = (WebDriver) clazz.getDeclaredField("driver").get(testObject);

		} catch (Exception e) {

			System.out.println("Failuer with screenshot capture" + e);
		}

		test.fail(result.getThrowable());
		extentThreadSafeTest.set(test);

		try {
			
			test.addScreenCaptureFromPath(
					getScreenPath.captureScreenshotFromPath(result.getMethod().getMethodName(), driver));

		} catch (IOException e) {

			System.out.println("Failuer with screenshot capture" + e);
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		test.log(Status.SKIP, "Test sckipped");
		extentThreadSafeTest.set(test);

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
		test.log(Status.FAIL, "Test failure due to timeout!");
		extentThreadSafeTest.set(test);


	}

	@Override
	public void onFinish(ITestContext context) {

		extent.flush();
	}

}
