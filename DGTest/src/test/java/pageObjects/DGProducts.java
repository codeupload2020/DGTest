package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


public class DGProducts extends PageBase {
	
	String expectedProduct;
	String expectedPrice;

	public DGProducts (WebDriver driver) {
		super(driver);
	}

	//constructor to initialise class with driver
	public static DGProducts init (WebDriver driver, String url) {
		driver.get(url);
		System.out.println("Visit https://www.domesticandgeneral.com/products");
		return new DGProducts(driver);
	}

	//another constructor without driver
	public static DGProducts noDriver () {
		return new DGProducts(driver);
	}


	//freezer product
	@FindBy(id="topCat_12708")
	private WebElement freezer;
	@FindBy(xpath="//a[@class='close']") WebElement closeCookies;
	public DGProducts selectProduct() {
		System.out.println("Selecting a product");
		PageBase.click(closeCookies);
		PageBase.click(freezer);
		return this;
	}

	//condition of product
	@FindBy(id="condition")
	private WebElement condition;
	public DGProducts selectDropdownCondition(String str) {
		PageBase.selectDropdown(condition,  str);
		return this;
	}

	//if guarantee is active
	@FindBy(id="guarantee")
	private WebElement guarantee;
	public DGProducts selectDropdownGuarantee(String str) {
		PageBase.selectDropdown(guarantee,  str);
		return this;
	}

	//if active how much is left
	@FindBy(id="guarantee_yearsLeft")
	private WebElement guaranteeLeft;
	public DGProducts selectDropdownGuaranteeLeft(String str) {
		PageBase.selectDropdown(guaranteeLeft,  str);
		return this;
	}

	//product brand name
	@FindBy(id="manufacturer")
	private WebElement manufacturer;
	public DGProducts chooseManufacturer(String str) {
		PageBase.setText(manufacturer, str);
		return this;
	}

	//date product was bought
	@FindBy(id="bought")
	private WebElement bought;
	public DGProducts boughtDate(String str) {
		PageBase.setText(bought, str);
		return this;
	}

	//purchased price range
	@FindBy(id="cost")
	private WebElement cost;
	public DGProducts selectCost(String str) {
		PageBase.selectDropdown(cost,  str);
		return this;
	}

	//get the quote button
	@FindBy(xpath="//input[@type='submit']")
	private WebElement getQuote;
	public DGProducts clickGetQuote() {
		System.out.println("Answered all the questions, now clicking on ‘Get Quote’");
		PageBase.click(getQuote);
		return this;
	}

	//add to basket
	//capture expected product name
	//capture expected premium
	@FindBy(id="add2CartBtn")
	private WebElement addToBasket;
	@FindBy(xpath="//h1/em")
	private WebElement expectedProductName;
	@FindBy(xpath="//span[@class='paymentTypePrice']/span")
	private WebElement expectedPriceAmount;
	public DGProducts clickAddToBasket() {
		expectedProduct = getValue(expectedProductName);
		expectedPrice = "£"+getValue(expectedPriceAmount);
		System.out.println("Click on Add to Basket...");
		PageBase.click(addToBasket);
		return this;
	}	

	//view the basket
	@FindBy(xpath="(//a[@id='viewCartButton'])[1]")
	private WebElement viewCart;
	@FindBy(id="MiniShoppingCart_Label")
	private WebElement openBasket;
	public DGProducts viewCartFromShoppingPage() {
		System.out.println("Click View Basket...");
		PageBase.click(viewCart);
		return this;
	}
	
	//verify if the product and price match
	@FindBy(xpath="//span[@class='basketItemTitle']")
	private WebElement productName;
	@FindBy(xpath="//span[@class='productPrice']")
	private WebElement premium;
	public DGProducts verifyNameAndPrice() {
		String actualProduct = productName.getText();
		String actualPrice = premium.getText();
		Assert.assertEquals(actualProduct, expectedProduct, "Product name did not match...");
		Assert.assertEquals(actualPrice, expectedPrice, "Product premium did not match");
		System.out.println("Product and Price verified...");
		return this;
	}

}
