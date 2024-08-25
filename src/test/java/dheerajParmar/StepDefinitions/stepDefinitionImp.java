package dheerajParmar.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import dheerajParmar.TestComponents.BaseTest;
import dheerajParmar.pageobjects.CartPage;
import dheerajParmar.pageobjects.ChechkOutPage;
import dheerajParmar.pageobjects.ConfirmationPage;
import dheerajParmar.pageobjects.LandingPage;
import dheerajParmar.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionImp extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String email, String Password) {
		productCatalogue = landingPage.loginApplication(email, Password);
	}

	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) {
		List<WebElement> products = productCatalogue.getproductsList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		ChechkOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.slectCountry("india");
		confirmationPage = checkOutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on confirmation page")
	public void message_displayed_confirmationPage(String message) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displayed(String message) {
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
		driver.close();
	}

}
