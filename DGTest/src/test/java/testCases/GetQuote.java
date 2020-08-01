package testCases;

import org.testng.annotations.Test;

import dataFactory.TestData;
import pageObjects.DGProducts;

public class GetQuote extends BaseClass {
	
	String url="https://www.domesticandgeneral.com/products";
	TestData td = new TestData();

	//test to purchase a product and verify the price
	@Test
	public void purchaseProduct() throws Exception {
		DGProducts.init(driver, url)
		.selectProduct()
		.selectDropdownCondition(td.Condition)
		.selectDropdownGuarantee(td.Guarantee)
		.selectDropdownGuaranteeLeft(td.GuaranteeLeft)
		.chooseManufacturer(td.Manufacturer)
		.boughtDate(td.PurchasedDate)
		.selectCost(td.Cost)
		.clickGetQuote()
		.clickAddToBasket()
		.viewCartFromShoppingPage()
		.verifyNameAndPrice();
	}
}
