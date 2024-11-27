package rah;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
public class StandAlone {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.id("userEmail")).sendKeys("christophodibo@yahoo.com");
		driver.findElement(By.id("userPassword")).sendKeys("Father70");
		driver.findElement(By.id("login")).click();
		
		
		String targetProductName = "ZARA COAT 3";
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		//products.stream().filter(product->product.getText().equalsIgnoreCase("ZARA COAT 3"))
	/*	WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(targetProductName)).findFirst().orElse(null);
	
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();*/
		
		// Specify the product name to click
		
		 // Iterate through the product list
	        for (WebElement product : products) {
	            // Check if the product matches the desired name
	            if (product.getText().contains(targetProductName)) {
	                // Click on the matching product
	              //  product.click();
	                product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	                System.out.println("Clicked on the product: " + targetProductName);
	                break;
	            }
	        }
			//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
			/*wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating"))); */
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
//			List<WebElement> cartProducts =  driver.findElements(By.cssSelector(".cartSection h3"));
//			boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(targetProductName));
//			Assert.assertTrue(match);
			Thread.sleep(3000);
			driver.findElement(By.cssSelector(".totalRow button")).click();
			driver.findElement(By.cssSelector(".form-group input")).sendKeys("us");
			Thread.sleep(3000);
			
			 List<WebElement> options = driver.findElements(By.cssSelector(".ta-results button")); // Update with correct locator

            // Iterate over each option and perform actions
            for (WebElement option : options) {
                System.out.println("Option: " + option.getText()); // Print the option text

                // Example: Select a specific option
                if (option.getText().contains("Austria")){
                    option.click();
                    break; // Exit the loop once the desired option is found and clicked
                }
            }
			 
			 
            driver.findElement(By.cssSelector(".action__submit")).click();	 
			
			
			
	}

}
