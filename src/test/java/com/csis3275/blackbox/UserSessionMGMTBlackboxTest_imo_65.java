package com.csis3275.blackbox;

/** UserSessionMGMTValidation_imo_65
 * 
 * WebDriverWait wait = new WebDriverWait(driver, 40);
	wait.until(ExpectedConditions.presenceOfElementLocated((By.linkText("User Management"))));
 */

//Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.After;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;
import java.net.URL;
import static org.hamcrest.MatcherAssert.*;

public class UserSessionMGMTBlackboxTest_imo_65 {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:/Programs/ChromeDriver/chromedriver.exe");

		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();

		// Implicitwait (time to wait after each page load)
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@After
	public void tearDown() {
		driver.findElement(By.xpath("//a[contains(@href, \'/login/out\')]")).click();
		driver.quit();
	}

	//@Ignore
	@Test
	public void accessasAdmintoaFeature() throws InterruptedException {
		driver.get("http://localhost:8080/");
		//driver.findElement(By.xpath("//a[contains(@href, \'/login/\')]")).click();
		driver.findElement(By.id("lmail")).click();
		driver.findElement(By.id("lmail")).sendKeys("rsasouza@gmail.com");
		driver.findElement(By.id("lpassword")).sendKeys("test");
		driver.findElement(By.xpath("//form[@id=\'login\']/button")).click();
		//Thread.sleep(5000); //Pause
		driver.findElement(By.linkText("User Management")).click();
		vars.put("http://localhost:8080/UserMGMT/showUsers", js.executeScript("return window.location.href"));
		driver.findElement(By.xpath("//a[contains(@href, \'/login/out\')]")).click();
	}

	//@Ignore
	@Test
	public void professoruseadminlevelFeature() throws InterruptedException {
		driver.get("http://localhost:8080/");
		driver.findElement(By.id("lmail")).click();
		driver.findElement(By.id("lmail")).sendKeys("bcorbyn21@usatoday.com");
		driver.findElement(By.id("lpassword")).click();
		driver.findElement(By.id("lpassword")).sendKeys("fQY4Sv");
		driver.findElement(By.xpath("//button[@type=\'submit\']")).click();
		//Thread.sleep(5000); //Pause
		driver.findElement(By.linkText("User Management")).click();
		vars.put("User does not have sufficient privilege to access this feature!",
				js.executeScript("document.getElementById(\"warning\").innerHTML"));
		driver.findElement(By.xpath("//a[contains(@href, \'/login/out\')]")).click();
	}

	//@Ignore
	@Test
	public void accessasProfessortoastudentlevelfeature() {
		driver.get("http://localhost:8080/");
		driver.findElement(By.id("lmail")).click();
		driver.findElement(By.id("lmail")).sendKeys("bcorbyn21@usatoday.com");
		driver.findElement(By.id("lpassword")).sendKeys("fQY4Sv");
		driver.findElement(By.xpath("//button[@type=\'submit\']")).click();
		vars.put("http://localhost:8080/showRoomListSchedule/", js.executeScript("return window.location.href"));
		driver.findElement(By.xpath("//a[contains(@href, \'/login/out\')]")).click();
	}

	//@Ignore
	@Test
	public void accessasstudenttoAdminFeature() throws InterruptedException {
		driver.get("http://localhost:8080/");
		driver.findElement(By.id("lmail")).click();
		driver.findElement(By.id("lmail")).sendKeys("mthurman23@cbsnews.com");
		driver.findElement(By.id("lpassword")).sendKeys("wEX7D4");
		driver.findElement(By.xpath("//button[@type=\'submit\']")).click();
		//Thread.sleep(5000); //Pause
		driver.findElement(By.linkText("User Management")).click();
		vars.put("User does not have sufficient privilege to access this feature!",
				js.executeScript("document.getElementById(\"warning\").innerHTML"));
		driver.findElement(By.xpath("//a[contains(@href, \'/login/out\')]")).click();
	}

	//@Ignore
	@Test
	public void admindisablesession() throws InterruptedException {
		driver.get("http://localhost:8080/");
		driver.manage().window().setSize(new Dimension(974, 1040));
		driver.findElement(By.id("lmail")).click();
		driver.findElement(By.id("lmail")).sendKeys("rsasouza@gmail.com");
		driver.findElement(By.id("lpassword")).sendKeys("test");
		driver.findElement(By.xpath("//form[@id=\'login\']/button")).click();
		//Thread.sleep(5000); //Pause
		driver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div[2]/a")).click();
		driver.findElement(By.xpath("//a[contains(text(),\'Invalidate\')]")).click();
		driver.findElement(By.cssSelector(".navbar-brand > .float-left")).click();
		
		driver.findElement(By.linkText("Session Management")).click();
		vars.put("User Session expired or is invalid! Please, login again.",
				js.executeScript("document.getElementById(\"warning\").innerHTML"));
	}
}
