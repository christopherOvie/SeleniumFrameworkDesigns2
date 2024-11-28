package abstractComponent;

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

import pageObjects.CartPage;
import pageObjects.OrdersPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*=myorder]")
	WebElement orderHeader;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productTitles;
	
	public void waitForElementToAppear(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	

}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(WebElement  ele) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele)); 
		
	}
//	public Boolean verifyProductDisplay(String targetProductName) {
//	Boolean match =	productTitles.stream().anyMatch(product ->product.getText().equalsIgnoreCase(targetProductName));
//	return match;
//	}
	public CartPage goToCartPage() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(cartHeader));
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
		
	}
	public OrdersPage goToOrdersPage() {
		orderHeader.click();
		OrdersPage ordersPage =	new OrdersPage(driver);
		return ordersPage;
	}
	 public static void clickElementUsingJS(WebDriver driver, String cssSelector) {
	        // Find the element by CSS selector
	        WebElement element = driver.findElement(By.cssSelector(cssSelector));
	        
	        // Execute JavaScript to click the element
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	    }
	 
	 public static void clickElementUsingJS(WebDriver driver, WebElement element) {
	        // Cast the driver to JavascriptExecutor and execute the click
	        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	        jsExecutor.executeScript("arguments[0].click();", element);
	    }
}