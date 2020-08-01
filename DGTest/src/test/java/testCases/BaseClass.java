package testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	protected static WebDriver driver;

	//initialise chrome driver
	@BeforeClass
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}

	//set driver type and properties
	@BeforeMethod
	public void setupTest() {
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("--start-maximized");
		options.addArguments("--headless");
		options.addArguments("window-size=1920,1080");
		driver = new ChromeDriver(options);
	}

	//close browser after test
	@AfterMethod
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	public static void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\screenshots\\" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
}
