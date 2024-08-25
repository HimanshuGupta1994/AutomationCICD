package dheerajParmar.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dheerajParmar.pageobjects.ProductCatalogue;
import dheerajParmar.TestComponents.BaseTest;
import dheerajParmar.pageobjects.CartPage;
import dheerajParmar.pageobjects.ChechkOutPage;
import dheerajParmar.pageobjects.ConfirmationPage;
import dheerajParmar.pageobjects.OrderPage;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalogue.getproductsList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		ChechkOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.slectCountry("india");
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("dheerajparmar014@gmail.com", "Dheeraj@123");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		// here getting the hashmap of data directly from json file using
		// getJsonDataToMap() method
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\dheereajParmar\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

//	@DataProvider
//	public Object[][] getData() throws IOException {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "dheerajparmar014@gmail.com");
//		map.put("password", "Dheeraj@123");
//		map.put("product", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "shetty@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("product", "ADIDAS ORIGINAL");
//	    return new Object[][] { { map}, { map1 } };
//  }

//	another approach for data provider method:

//	@DataProvider
//	public Object[][] getData1() {
//		return new Object[][] { { "dheerajparmar014@gmail.com", "Dheeraj@123", "ZARA COAT 3" },
//				{ "shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL" } };
//	}

}
