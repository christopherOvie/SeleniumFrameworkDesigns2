package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import abstractComponent.AbstractComponents;

public class CartPage extends AbstractComponents{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProduct;
	
	
	public Boolean verifyProductsDisplay(String targetProductName) {
	Boolean match =	cartProducts.stream().anyMatch(product ->product.getText().equalsIgnoreCase(targetProductName));
	return match;
	}
	
	public Boolean verifyProductDisplay(String targetProductName) {
		Boolean match =	cartProduct.stream().anyMatch(product ->product.getText().equalsIgnoreCase(targetProductName));
		return match;
		}
	
	/*public CartPage goToCartPage() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(cartHeader));
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
		
	}*/
	
public CheckoutPage goToCheckout() {
	checkoutEle.click();
	return new CheckoutPage(driver);
}
}