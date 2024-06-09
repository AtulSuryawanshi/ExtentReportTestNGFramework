package com.extentReports;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class GetScreenshotPath {
	
	WebDriver driver;
	
	public String captureScreenshotFromPath(String TestCaseName, WebDriver driver) throws IOException {
		
		String folderName = ExtentReportsConfig.folderName;
		
		//capture screenshot as file
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		//create the path to store screenshot
		String destpath = System.getProperty("user.dir") + folderName +"\\"+TestCaseName + ".png";
		File file = new File(destpath);
		
		//storing the screenshot to target folder
		FileUtils.copyFile(source, file);
		
		return destpath;
	}

}
