package mar2;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PropertyFiles {
	Properties p;
	FileInputStream fi;
	WebDriver driver;
	@BeforeTest
	public void setup()throws Throwable
	{
	p=new Properties();
	fi= new FileInputStream("OR.properties");
	p.load(fi);
	if(p.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
	
	driver=new ChromeDriver();
	driver.navigate().to(p.getProperty("url"));
	driver.manage().window().maximize();
	Thread.sleep(5000);
	}
	else if(p.getProperty("Browser").equalsIgnoreCase("firefox"))
	{
	driver=new FirefoxDriver();
	driver.navigate().to(p.getProperty("url"));
	driver.manage().window().maximize();
	Thread.sleep(5000);
	}
	else{
		System.out.println("browser value is not matching");
	}
	}
	@Test
	public void verifylogin()throws Throwable
	{
	
	driver.findElement(By.xpath(p.getProperty("objusername"))).sendKeys("Admin");
	driver.findElement(By.xpath(p.getProperty("objpassword"))).sendKeys("Qedge123!@#");
	driver.findElement(By.xpath(p.getProperty("objloginbtn"))).submit();
	Thread.sleep(5000);
	String expected="OrangeHRM";
	String actual=driver.getTitle();
	if(actual.contains(expected))
	{
		
		Reporter.log("Login Successful::"+expected+"   "+actual,true);
	}
	else{
		Reporter.log("Login fail::"+expected+"   "+actual,true);
	}
	}
	@AfterTest
	public void tearDown()
	{
	driver.close();	
		
	}
	}


		
		
