package travelSite.SeleniumPOC;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class HotWire {
	
	//private static final TimeUnit SECONDS = null;
	//private static final Function ExpectedConditions = null;
	WebDriver driver;
	WebDriverWait wait;
			
	@BeforeClass 
	public void init() {
		System.setProperty("webdriver.chrome.driver","/Users/subrat/Desktop/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test(priority = 0)
	public void launchURL()  {
		System.out.println("Test launchURL");
		driver.get("https://hotwire.com/");	
	}
	
	@Test(dependsOnMethods="launchURL")
	public void vacation() throws InterruptedException {
		System.out.println("Test Vacation");
		WebElement vacation = driver.findElement(By.xpath("(//a[contains(text(),'Vacations')])[1]"));	
		vacation.click();
		//System.out.println("iI am here");
		Thread.sleep(10000);
		}
	
	@Test(dependsOnMethods="vacation")
	public void selectOpt() throws InterruptedException {
		//System.out.println("Select Hotel");
		//WebElement hotel = driver.findElement(By.className("farefinder-option-label hw-body-1"));
		//hotel.click();
		//System.out.println("select Flight");	
		System.out.println("Selet Car");
		WebElement car = (new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='farefinder-package']//button[contains(@ng-click,'car')]"))));
		car.click();
	}
/*	
	@Test(dependsOnMethods="selectOpt")
	public void selectFlyFromAddress() {
		
		System.out.println("Enter address of the city fly from");

		WebElement FlyFrom = (new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("farefinder-package-origin-location-input"))));	
		FlyFrom.click();
		FlyFrom.sendKeys("SFO"); 		
		WebElement firstOpt = (new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'San Francisco, CA')]"))));	
		firstOpt.click();
		 		
		//FlyFrom.sendKeys(Keys.DOWN);
		//FlyFrom.sendKeys(Keys.RETURN);
		//Actions builder = new Actions(driver);
		//builder.moveToElement(FlyFrom).sendKeys(Keys.ARROW_DOWN).click().build().perform();	
	}
	
	@Test(dependsOnMethods="selectFlyFromAddress")
	public void selectFlyToAddress() {
		System.out.println("Enter address of the city fly to ");
		WebElement FlyTo = (new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("farefinder-package-destination-location-input"))));	
		FlyTo.click();
		FlyTo.sendKeys("LAX");
		WebElement firstOpt1 = (new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Los Angeles, CA')]"))));		
		firstOpt1.click();		
	}
	
	
	// Calender Code from Date
	
	@Test(dependsOnMethods="selectFlyToAddress")
	public void fromDate() {
		
        //Declare a implicit wait for synchronisation
        //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
 
        //Get Today's number
        String today = getDepartureDay();
        System.out.println("Departure Date: " + today + "\n");
        
        WebElement depDate = driver.findElement(By.xpath("//input[@id='farefinder-package-startdate-input']"));
        depDate.sendKeys(Keys.chord(Keys.CONTROL, "a"), today);
	}
	
	
	@Test(dependsOnMethods="fromDate")
	public void departureTime() {
		System.out.println("Departure is in Evening");
		Select pickuptime = new Select(driver.findElement(By.id("farefinder-package-pickuptime-input")));
		pickuptime.selectByVisibleText("Evening");				
	}
	
	
	
	//Calenders Code to Date
	
	@Test(dependsOnMethods="departureTime")
	public void toDate() {
		
        //Declare a implicit wait for synchronisation
        //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
 
        //Get Today's number
        String returnDate = getArrivalDay();
        System.out.println("Arrival Date: " + returnDate + "\n");
        
        WebElement depDate = driver.findElement(By.xpath("//input[@id='farefinder-package-enddate-input']"));
        depDate.sendKeys(Keys.chord(Keys.CONTROL, "a"), returnDate);
	}
	
	
	@Test(dependsOnMethods="toDate")
	public void arrivalTime() {
		System.out.println("Arrival is in Morning");
		Select pickuptime = new Select(driver.findElement(By.id("farefinder-package-dropofftime-input")));
		pickuptime.selectByVisibleText("Morning");				
	}
	
	@Test(dependsOnMethods="arrivalTime")
	public void findDeal() {
		System.out.println("Click to Find a Deal");
		WebElement dealButton = driver.findElement(By.id("farefinder-package-search-button"));
		dealButton.click();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		WebElement pageLoad = (new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='sort-options nobullet']//li[2]//button[1]"))));
		
		
		org.testng.Assert.assertEquals(pageLoad.getText(), "Featured");
		System.out.println("Search Results displayed");
	}
	*/
	
	@AfterClass
	public void teardown() {
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.quit();
	}
	
	private String getDepartureDay() {
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(new Date());
	    cal.add(Calendar.DATE, 1);
	    String newDate = dateFormat.format(cal.getTime());
	    
	    return newDate;
		
		
	}
	
	private String getArrivalDay() {
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(new Date());
	    cal.add(Calendar.DATE+1, 21);
	    String newDate = dateFormat.format(cal.getTime());
	    
	    return newDate;
		
		
	}
}