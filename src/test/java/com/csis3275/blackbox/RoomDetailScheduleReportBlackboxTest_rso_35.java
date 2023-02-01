package com.csis3275.blackbox;

import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.After;
//import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
//import static org.junit.Assert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class RoomDetailScheduleReportBlackboxTest_rso_35 {

	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@Before
	public void setUp() {

		// Chrome Driver
		System.setProperty("webdriver.chrome.driver", "C:/Programs/ChromeDriver/chromedriver.exe");

		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();

		// Implicitwait (time to wait after each page load)
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	}

	@After
	public void tearDown() {
		driver.quit();
	}

	public String waitForWindow(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Set<String> whNow = driver.getWindowHandles();
		Set<String> whThen = (Set<String>) vars.get("window_handles");
		if (whNow.size() > whThen.size()) {
			whNow.removeAll(whThen);
		}
		return whNow.iterator().next();
	}

	/**
	 * Test the report generation function by selecting the first Room C101 and setting the date 11/24/2020.
	 * To check the generated report, it checks if the report title shows the correct Room number and the selected date.
	 * @throws Exception Generic exception caught and thrown up
	 */
	@Test
	public void testReportFunctionWithRoomC101AndDate20201124() throws Exception {
		
		driver.get("http://localhost:8080/");
		driver.manage().window().maximize();
		
	    WebDriverWait waitElement = new WebDriverWait(driver, 30); //Wait Driver obj		

	    //Login
		driver.findElement(By.id("lmail")).click();
		driver.findElement(By.id("lmail")).sendKeys("rsasouza@gmail.com");
		driver.findElement(By.id("lpassword")).sendKeys("test");
	    driver.findElement(By.xpath("//form[@id=\'login\']/button")).click();

	    //Click on the Feature button using JS
	    waitElement.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".row:nth-child(7) > .col:nth-child(3) > .btn")));
	    WebElement featureElement = driver.findElement(By.cssSelector(".row:nth-child(7) > .col:nth-child(3) > .btn"));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();" , featureElement);	    

	    //Fill the Room and Date select form
	    waitElement.until(ExpectedConditions.elementToBeClickable(By.id("roomId")));
	    driver.findElement(By.id("roomId")).click();
		{
			WebElement dropdown = driver.findElement(By.id("roomId"));
			dropdown.findElement(By.xpath("//option[. = 'C101']")).click();
		}
		driver.findElement(By.id("roomId")).click();

		waitElement.until(ExpectedConditions.elementToBeClickable(By.id("selectedDate")));		
		//driver.findElement(By.id("selectedDate")).click();
		driver.findElement(By.id("selectedDate")).sendKeys("11/24/2020");
		
		//Run report
		waitElement.until(ExpectedConditions.elementToBeClickable(By.id("show_report")));
		driver.findElement(By.id("show_report")).click();
		
		//Check report result
		driver.findElement(By.cssSelector(".row:nth-child(2)")).click();
		waitElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\'roomDetailScheduleReport_Report_Section\']/div[2]/p")));
		//Thread.sleep(5000); //Pause
		assertThat(driver.findElement(By.xpath("//div[@id=\'roomDetailScheduleReport_Report_Section\']/div[2]/p"))
				.getText(), is("Room Number: C101 - Date: Tuesday - Nov 24, 2020"));
		
		//Logout
		driver.findElement(By.linkText("logout")).click();
		
	}
	
	/**
	 * Test the report generation function by selecting the first Room C101 and setting the date 11/24/2020 and then running the report for the next day 
	 * using the ShowNextDay Button.
	 * To check the generated report, it checks if the report title shows the correct Room number and the selected date.
	 * @throws Exception Generic exception caught and thrown up 
	 */
	@Test
	public void testNextDayReportFunctionWithRoomC101AndDate20201124() throws Exception {
		
		driver.get("http://localhost:8080/");
		driver.manage().window().maximize();
		
	    WebDriverWait waitElement = new WebDriverWait(driver, 30); //Wait Driver obj		

	    //Login
		driver.findElement(By.id("lmail")).click();
		driver.findElement(By.id("lmail")).sendKeys("rsasouza@gmail.com");
		driver.findElement(By.id("lpassword")).sendKeys("test");
	    driver.findElement(By.xpath("//form[@id=\'login\']/button")).click();

	    //Click on the Feature button using JS
	    waitElement.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".row:nth-child(7) > .col:nth-child(3) > .btn")));
	    WebElement featureElement = driver.findElement(By.cssSelector(".row:nth-child(7) > .col:nth-child(3) > .btn"));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();" , featureElement);	    

	    //Fill the Room and Date select form
	    waitElement.until(ExpectedConditions.elementToBeClickable(By.id("roomId")));
	    driver.findElement(By.id("roomId")).click();
		{
			WebElement dropdown = driver.findElement(By.id("roomId"));
			dropdown.findElement(By.xpath("//option[. = 'C101']")).click();
		}
		driver.findElement(By.id("roomId")).click();

		waitElement.until(ExpectedConditions.elementToBeClickable(By.id("selectedDate")));		
		//driver.findElement(By.id("selectedDate")).click();
		driver.findElement(By.id("selectedDate")).sendKeys("11/24/2020");
		
		//Run report for the set date
		waitElement.until(ExpectedConditions.elementToBeClickable(By.id("show_report")));
		driver.findElement(By.id("show_report")).click();
		
		//Run report for the Next Day using the button
		waitElement.until(ExpectedConditions.elementToBeClickable(By.id("show_next_day")));
		driver.findElement(By.id("show_next_day")).click();		
		
		//Check report result
		//driver.findElement(By.cssSelector(".row:nth-child(2)")).click();
		waitElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\'roomDetailScheduleReport_Report_Section\']/div[2]/p")));
		//Thread.sleep(5000); //Pause
		assertThat(driver.findElement(By.xpath("//div[@id=\'roomDetailScheduleReport_Report_Section\']/div[2]/p"))
				.getText(), is("Room Number: C101 - Date: Wednesday - Nov 25, 2020"));
		
		//Logout
		driver.findElement(By.linkText("logout")).click();		

	}
	
	/**
	 * Test the report generation function by selecting the first Room C101 and setting the date 11/24/2020 and then running the report for the previous day 
	 * using the ShowPreviousDay Button.
	 * To check the generated report, it checks if the report title shows the correct Room number and the selected date.
	 * @throws Exception Generic exception caught and thrown up
	 */
	@Test
	public void testPreviousDayReportFunctionWithRoomC101AndDate20201124() throws Exception {
		
		driver.get("http://localhost:8080/");
		driver.manage().window().maximize();
		
	    WebDriverWait waitElement = new WebDriverWait(driver, 30); //Wait Driver obj		

	    //Login
		driver.findElement(By.id("lmail")).click();
		driver.findElement(By.id("lmail")).sendKeys("rsasouza@gmail.com");
		driver.findElement(By.id("lpassword")).sendKeys("test");
	    driver.findElement(By.xpath("//form[@id=\'login\']/button")).click();

	    //Click on the Feature button using JS
	    waitElement.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".row:nth-child(7) > .col:nth-child(3) > .btn")));
	    WebElement featureElement = driver.findElement(By.cssSelector(".row:nth-child(7) > .col:nth-child(3) > .btn"));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();" , featureElement);	    

	    //Fill the Room and Date select form
	    waitElement.until(ExpectedConditions.elementToBeClickable(By.id("roomId")));
	    driver.findElement(By.id("roomId")).click();
		{
			WebElement dropdown = driver.findElement(By.id("roomId"));
			dropdown.findElement(By.xpath("//option[. = 'C101']")).click();
		}
		driver.findElement(By.id("roomId")).click();

		waitElement.until(ExpectedConditions.elementToBeClickable(By.id("selectedDate")));		
		//driver.findElement(By.id("selectedDate")).click();
		driver.findElement(By.id("selectedDate")).sendKeys("11/24/2020");
		
		//Run report for the set date
		waitElement.until(ExpectedConditions.elementToBeClickable(By.id("show_report")));
		driver.findElement(By.id("show_report")).click();
		
		//Run report for the Previous Day using the button
		waitElement.until(ExpectedConditions.elementToBeClickable(By.id("show_previous_day")));
		driver.findElement(By.id("show_previous_day")).click();		
		
		//Check report result
		//driver.findElement(By.cssSelector(".row:nth-child(2)")).click();
		waitElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\'roomDetailScheduleReport_Report_Section\']/div[2]/p")));
		//Thread.sleep(5000); //Pause
		assertThat(driver.findElement(By.xpath("//div[@id=\'roomDetailScheduleReport_Report_Section\']/div[2]/p"))
				.getText(), is("Room Number: C101 - Date: Monday - Nov 23, 2020"));
		
		//Logout
		driver.findElement(By.linkText("logout")).click();		

	}	
	
}
