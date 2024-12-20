package ChrisAcademyTest;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageObjects.LandingPage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
public class StandAlone2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		LandingPage landingPage= new LandingPage(driver);
		landingPage.goTo();
		landingPage.logiApplication("christophodibo@yahoo.com", "Father70");
		
		
		String targetProductName = "ZARA COAT 3";
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(targetProductName)).findFirst().orElse(null);
	
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
			
			//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating"))); 
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); 
			//Thread.sleep(5000);
			//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
			
			// Wait until the element is clickable and click it
            WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink*='cart']")));
            cartButton.click();
			List<WebElement> cartProducts =  driver.findElements(By.cssSelector(".cartSection h3"));
			Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(targetProductName));
		    // Assert.assertTrue(match);
		    // Assert.assertTrue(match, targetProductName);
			//Thread.sleep(3000);
			//driver.findElement(By.cssSelector(".totalRow button1")).click();
			 WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".totalRow button")));
			 checkoutButton.click();
			
			Actions a = new Actions(driver);
			a.sendKeys(driver.findElement(By.cssSelector("[placeholder ='Select Country']")), "India").build().perform();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
			//driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)"));
			
			// Wait until the element is clickable and click it
            WebElement countryDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ta-item:nth-of-type(2)")));
            countryDropdown.click();
			//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
			/*driver.findElement(By.cssSelector(".form-group input")).sendKeys("us");
			//Thread.sleep(3000);
			
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
			 */
			 
            driver.findElement(By.cssSelector(".action__submit")).click();	
            
            WebElement heroTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
            String confirmMessage = heroTextElement.getText();
            
            //String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
           // Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
           Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
			
			
			
	}

}
