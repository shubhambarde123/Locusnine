package Selenium;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ibibo 
{
	public static void main(String[] args) throws InterruptedException, InvalidFormatException, IOException 
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		//Navigate to Goibibo website
		driver.get("https://www.goibibo.com/");
		
		Map<String, String> hashmap = excel.dataFetchFromExcel("Goibibo", 1);
		
		//Enter Source City
		driver.findElement(By.id("gosuggest_inputSrc")).sendKeys(hashmap.get("From"));
		driver.findElement(By.xpath("//span[normalize-space()='Pune']")).click();
		
		//Enter Destination City
		driver.findElement(By.id("gosuggest_inputDest")).sendKeys(hashmap.get("Destination"));
		driver.findElement(By.xpath("//span[normalize-space()='Delhi']")).click();
		
		//Select Departure Date
		driver.findElement(By.xpath("//input[@placeholder='Departure']")).click();
		driver.findElement(By.id("fare_20190607")).click();
		
		//Click on Search Button
		driver.findElement(By.id("gi_search_btn")).click();
		
		//Mouse Hover on Airlines Filter
		WebElement element = driver.findElement(By.id("airlinesFilter")); 
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
		
		//Select Air India 
		WebElement subelement =driver.findElement(By.xpath("//label[contains(text(),'Air India')]"));
		action.moveToElement(subelement);
		action.click();
		action.perform();
		
		js.executeScript("window.scrollBy(0,100)");
		// Click on Book button
		driver.findElement(By.xpath("//div[contains(@class,'width100 fl greyBg')]//div[4]//div[2]//div[2]//div[1]//div[1]//span[1]//span[1]//input[1]")).click();
		
		//Scroll Down
		js.executeScript("window.scrollBy(0,300)");
		
		//Deslect the Charity checkbox
		WebElement element1 = driver.findElement(By.name("charityCheckbox"));
		if(element1.isSelected())
		{
			element1.click();
		}
		
		WebElement element2 = driver.findElement(By.name("insuranceRadio"));
		if(element2.isSelected())
		{
			element2.click();
		}
		
		js.executeScript("window.scrollBy(0,800)");
		
		//Select Title
		Select select = new Select(driver.findElement(By.id("Adulttitle1")));
		select.selectByVisibleText(hashmap.get("Title"));
		
		//Enter First Name
		driver.findElement(By.id("AdultfirstName1")).sendKeys(hashmap.get("FirstName"));
		
		//Enter Middle Name
		driver.findElement(By.id("AdultmiddleName1")).sendKeys(hashmap.get("MiddleName"));
		
		//Enter Last Name
		driver.findElement(By.id("AdultlastName1")).sendKeys(hashmap.get("LastName"));
		
		//Enter Email
		driver.findElement(By.id("email")).sendKeys(hashmap.get("EmailId"));
		
		//Enter Mobile Number
		driver.findElement(By.id("mobile")).sendKeys(hashmap.get("MobileNumber"));
		
		//Click on Proceed Button 
		driver.findElement(By.xpath("//button[contains(@class,'button orange col-md-3 fr large')]")).click();
		
		//Quit the browser 
		driver.quit();
	}
}
