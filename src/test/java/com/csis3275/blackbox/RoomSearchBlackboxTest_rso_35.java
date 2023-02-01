package com.csis3275.blackbox;

import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class RoomSearchBlackboxTest_rso_35 {

	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@Before
	public void setUp() {

		// Chrome Driver
		System.setProperty("webdriver.chrome.driver", "c:/temp/chromedriver.exe"); 
		//Scott - "c:/temp/chromedriver.exe"
		//Ricardo - "C:/Programs/ChromeDriver/chromedriver.exe"

		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();

		// Implicitwait (time to wait after each page load)
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

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
	 * Test searching for only available rooms for reservation located in the Library building and available between 02/25/2021 2:00pm and 4:00pm.
	 * To check the results, the room S101 should be in the returned list and room S107 should not be in the list.
	 * @throws Exception Generic exception caught and thrown up
	 */
	@Test
	public void testOnlyAvailableRoomsSearchConfirmAvailableRoom() throws Exception {
		
		driver.get("http://localhost:8080/");
		driver.manage().window().maximize();
		
	    WebDriverWait waitElement = new WebDriverWait(driver, 30); //Wait Driver obj		

	    //Login
		driver.findElement(By.id("lmail")).click();
		driver.findElement(By.id("lmail")).sendKeys("rsasouza@gmail.com");
		driver.findElement(By.id("lpassword")).sendKeys("test");
	    driver.findElement(By.xpath("//form[@id=\'login\']/button")).click();

	    //Click on the Feature button using JS
	    waitElement.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".row:nth-child(7) > .col:nth-child(1) > .btn")));
	    WebElement featureElement = driver.findElement(By.cssSelector(".row:nth-child(7) > .col:nth-child(1) > .btn"));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();" , featureElement);	 		
		
	    //Select the Building and set Dates
	    waitElement.until(ExpectedConditions.elementToBeClickable(By.id("building")));
	    driver.findElement(By.id("building")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("building"));
	      dropdown.findElement(By.xpath("//option[. = 'Library']")).click();
	    }
	    driver.findElement(By.id("onlyAvailableChkBx")).click();

	    waitElement.until(ExpectedConditions.elementToBeClickable(By.id("desiredStartDatetime")));
	    //driver.findElement(By.id("desiredStartDatetime")).sendKeys("2021-02-25T14:00");
	    driver.findElement(By.id("desiredStartDatetime")).sendKeys("02/25/2021");
	    driver.findElement(By.id("desiredStartDatetime")).sendKeys(Keys.TAB);
	    driver.findElement(By.id("desiredStartDatetime")).sendKeys("0200PM");	    	    

	    waitElement.until(ExpectedConditions.elementToBeClickable(By.id("desiredEndDatetime")));	    
	    driver.findElement(By.id("desiredEndDatetime")).sendKeys("02/25/2021");
	    driver.findElement(By.id("desiredEndDatetime")).sendKeys(Keys.TAB);
	    driver.findElement(By.id("desiredEndDatetime")).sendKeys("0400PM");

	    //Run the Searching
		waitElement.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".mr-2")));
	    driver.findElement(By.cssSelector(".mr-2")).click();

		//Thread.sleep(5000); //Pause	    

		//Check table result	    
	    assertThat(driver.findElement(By.xpath("//html/body/div/div[3]/table")).getText(), containsString("S101"));

	    //Logout	    
	    driver.findElement(By.linkText("logout")).click();
	  }	
	
	/**
	 * Test searching for only available rooms for reservation located in the Library building and available between 02/25/2021 2:00pm and 4:00pm.
	 * To check the results, the room S107 should not be in the list.
	 * @throws Exception Generic exception caught and thrown up
	 */
	@Test
	public void testOnlyAvailableRoomsSearchConfirmNotAvailableRoom() throws Exception {
		
		driver.get("http://localhost:8080/");
		driver.manage().window().maximize();
		
	    WebDriverWait waitElement = new WebDriverWait(driver, 30); //Wait Driver obj		

	    //Login
		driver.findElement(By.id("lmail")).click();
		driver.findElement(By.id("lmail")).sendKeys("rsasouza@gmail.com");
		driver.findElement(By.id("lpassword")).sendKeys("test");
	    driver.findElement(By.xpath("//form[@id=\'login\']/button")).click();

	    //Click on the Feature button using JS
	    waitElement.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".row:nth-child(7) > .col:nth-child(1) > .btn")));
	    WebElement featureElement = driver.findElement(By.cssSelector(".row:nth-child(7) > .col:nth-child(1) > .btn"));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();" , featureElement);	 		
		
	    //Select the Building and set Dates
	    waitElement.until(ExpectedConditions.elementToBeClickable(By.id("building")));
	    driver.findElement(By.id("building")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("building"));
	      dropdown.findElement(By.xpath("//option[. = 'Library']")).click();
	    }
	    driver.findElement(By.id("onlyAvailableChkBx")).click();

	    waitElement.until(ExpectedConditions.elementToBeClickable(By.id("desiredStartDatetime")));
	    //driver.findElement(By.id("desiredStartDatetime")).sendKeys("2021-02-25T14:00");
	    driver.findElement(By.id("desiredStartDatetime")).sendKeys("02/25/2021");
	    driver.findElement(By.id("desiredStartDatetime")).sendKeys(Keys.TAB);
	    driver.findElement(By.id("desiredStartDatetime")).sendKeys("0200PM");	    	    

	    waitElement.until(ExpectedConditions.elementToBeClickable(By.id("desiredEndDatetime")));	    
	    driver.findElement(By.id("desiredEndDatetime")).sendKeys("02/25/2021");
	    driver.findElement(By.id("desiredEndDatetime")).sendKeys(Keys.TAB);
	    driver.findElement(By.id("desiredEndDatetime")).sendKeys("0400PM");

	    //Run the Searching
		waitElement.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".mr-2")));
	    driver.findElement(By.cssSelector(".mr-2")).click();

		//Thread.sleep(5000); //Pause	    

		//Check table result	    
	    assertThat(driver.findElement(By.xpath("//html/body/div/div[3]/table")).getText(), not(containsString("S107")));
	    
		//Logout	    
	    driver.findElement(By.linkText("logout")).click();
	  }			

}
