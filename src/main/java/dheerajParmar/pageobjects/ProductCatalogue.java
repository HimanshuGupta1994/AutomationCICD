package dheerajParmar.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dheerajParmar.abstractComponents.abstractComponent;

public class ProductCatalogue extends abstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver); // we need child classes driver in our parent class abstractComponent as there
						// is a method which takes driver as a argument. So in all pageobjects package
						// classes since we are inheriting abstractComponent, we are providing it the
						// drivers of child classes using super keyword
		this.driver = driver;
		PageFactory.initElements(driver, this); // pageFactory is used only for driver.findElement construction.
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toast = By.cssSelector("#toast-container");

	public List<WebElement> getproductsList() {
		waitForElementToAppear(productsBy); // we cannot use pageFactory here because pageFactory used for
											// driver.findElement construction but here we have 'By'
		return products;
	}

	public WebElement getProductName(String productName) {
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toast);
		waitForElementToDisappear(spinner);
	}
}
