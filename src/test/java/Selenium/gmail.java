package Selenium;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class gmail 
{
	public static void main(String[] args) throws InterruptedException, InvalidFormatException, IOException 
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.SECONDS);
		
		//navigate to Gmail Login Page
		driver.get("https://www.gmail.com");
		
		Map<String, String> hash = excel.dataFetchFromExcel("Gmail", 1);
		
		//Enter emailId and click on Next button
		driver.findElement(By.id("identifierId")).sendKeys(hash.get("LoginEmailId"));
		driver.findElement(By.id("identifierNext")).click();
		
		//Enter password and click on Next button
		driver.findElement(By.name("password")).sendKeys(hash.get("Password"));
		driver.findElement(By.id("passwordNext")).click();
		
		driver.findElement(By.className("gb_ve")).click();
		driver.findElement(By.xpath("//a[@id='gb23']//span[@class='gb_k']")).click();
		
		//Navigate to another window
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        
        String actualPageTitle = "Inbox - aabcc9886@gmail.com - Gmail";
        String expectedpageTitle = driver.getTitle();
        Assert.assertEquals(actualPageTitle, expectedpageTitle);
        
		//Click on Compose button
		driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji T-I-KE L3']")).click();
		
		//Enter to email id
		driver.findElement(By.name("to")).sendKeys(hash.get("ToEmailId"));
		
		//Enter subject
		driver.findElement(By.xpath("//input[@placeholder='Subject']")).sendKeys(hash.get("Subject"));
		
		//Enter body
		driver.findElement(By.xpath("//div[@class='Am Al editable LW-avf']")).sendKeys(hash.get("Body"));
		
		//Click on Send Button
		driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']")).click();
		
		Thread.sleep(5000);
		
		String actualText = "Message sent.";
		String expectedText = driver.findElement(By.xpath("//span[@class='bAq']")).getText();
		
		Assert.assertEquals(actualText, expectedText);
		
		//Click on icon
		driver.findElement(By.xpath("//a[@class='gb_x gb_Ea gb_f']")).click();
		
		//Click on Sign out button
		driver.findElement(By.id("gb_71")).click();
		
		String actualPageTitleSignOut = "Gmail";
		String expectedPageTitleSignOut = driver.getTitle();
		
		Assert.assertEquals(actualPageTitleSignOut, expectedPageTitleSignOut);
		
		//Quit the browser 
		driver.quit();
	}
}

