package com.extentReports;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GreenKartTests {

	WebDriver driver;

	@BeforeMethod
	public void greenKartPrerequisite() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\ATUL\\Preplaced\\Preplaced Testing Workplace\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}

	@Test
	public void grenCartLogin() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));

		driver.findElement(By.xpath("//div[@class='products']//div[1]//div[3]//button[1]")).click();

		driver.findElement(By.xpath("//img[@alt='Cart']")).click();

		driver.findElement(By.xpath("//button[normalize-space()='PROCEED TO CHECKOUT']")).click();

		driver.findElement(By.xpath("//button[normalize-space()='Place Order']")).click();

		Select sel = new Select(driver.findElement(By.xpath("//div[@class='wrapperTwo']//div//select")));
		sel.selectByValue("India");

		driver.findElement(By.xpath("//input[@type='checkbox']")).click();

		driver.findElement(By.xpath("//button[normalize-space()='Proceed']")).click();

		String orderSuccessMessage = driver
				.findElement(By.xpath("//span[contains(text(),'Thank you, your order has been placed successfully')]"))
				.getText();

		System.out.println(orderSuccessMessage);

	}

	@Test
	public void grenCartLogin2() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));

		driver.findElement(By.xpath("//div[@class='products']//div[1]//div[3]//button[1]")).click();

		driver.findElement(By.xpath("//img[@alt='Cart']")).click();

		driver.findElement(By.xpath("//button[normalize-space()='PROCEED TO CHECKOUT']")).click();

		driver.findElement(By.xpath("//button[normalize-space()='Place Order']")).click();

		Select sel = new Select(driver.findElement(By.xpath("//div[@class='wrapperTwo']//div//select")));
		sel.selectByValue("India");

		driver.findElement(By.xpath("//*[@type='checkbox']")).click();

		driver.findElement(By.xpath("//button[normalize-space()='Proceed']")).click();

		String orderSuccessMessage = driver
				.findElement(By.xpath("//span[contains(text(),'Thank you, your order has been placed successfully')]"))
				.getText();

		Assert.assertEquals("Thank you, your order has been placed successfully", orderSuccessMessage);

		System.out.println(orderSuccessMessage);

	}

	@AfterMethod
	public void greenKartTearDown() {

		driver.close();
	}

}
