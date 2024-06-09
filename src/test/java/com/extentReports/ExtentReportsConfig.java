package com.extentReports;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsConfig {

	static ExtentReports extent;
	
	static String folderName;

	public static ExtentReports extentinitilization() {
		
		//Capture System time
		String systemTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		folderName = "\\ExtentReports" + systemTime;
		
		//Extent report path configuration
		String path = System.getProperty("user.dir") + folderName + "\\Test_Automation_Results.html";
		System.out.println(path);
		
		// extent configuration
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Opencart Web Automation Extent Reports");
		reporter.config().setDocumentTitle("Automation Results");

		// extent implementation
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Test Engineer", "Atul");
		extent.setSystemInfo("Environment", "SIT");
		extent.setSystemInfo("Browser", "Chrome");

		return extent;
	}
}
