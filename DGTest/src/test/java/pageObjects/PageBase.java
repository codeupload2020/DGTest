package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {


	protected static WebDriver driver;

	//constructor to initialise this class
	public PageBase (WebDriver driver) {
		PageBase.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
	}

	//customised click method
	public static void click (WebElement elem) {		
		waitForElementVisibility(elem);
		elem.click();
	}

	//customised sendKeys method
	public static void setText (WebElement elem, String text) {		
		waitForElementVisibility(elem);
		elem.clear();
		elem.sendKeys(text);
		elem.sendKeys(Keys.TAB);
	}

	//customised select dropDown method
	public static void selectDropdown (WebElement elem, String value) {		
		waitForElementVisibility(elem);
		Select sel=new Select(elem);
		sel.selectByValue(value);
	}

	//method to wait for element
	public static void waitForElementVisibility(WebElement elem){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(elem));
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		waiting(150);
	}

	//fixed wait
	public static void waiting(int mil) {
		try {
			Thread.sleep(mil);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//method to get element value
	public static String getValue(WebElement elem) {
		waitForElementVisibility(elem);
		return elem.getText();
	}

}
