package ChrisAcademyTest;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.ProductPage;
public class ErrorValidationTest extends BaseTest {

	
	@Test(groups= {"ErrorHandling"})
	
	public void loginErrorValidation () throws IOException, InterruptedException{
		
		
	      landingPage.logiApplication("christophodibo@yahoo.com", "Fath66666670");
		
	     
		String x =landingPage.getErrorMessage();
		System.out.println(x);
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
	
	}
	
	@Test
	
	public void productErrorValidation () throws IOException, InterruptedException{
		// purpose is to get rid of object creation
		
		String targetProductName = "ZARA COAT 3";
		ProductPage productPage = landingPage.logiApplication("christophodibo@yahoo.com", "Father70");
		List<WebElement> products =	productPage.getProductList();
		productPage.addProductToCart(targetProductName);
		
		Thread.sleep(3000);
		CartPage cartPage =productPage.goToCartPage();
	
		Thread.sleep(3000);
		// Call the method from the Page Object and verify
        Boolean isProductDisplayed = cartPage.verifySingleProductDisplayed();
        Assert.assertTrue(isProductDisplayed, "The product should be displayed, but it is not.");
       // Assert.assertFalse(isProductDisplayed, "The product should be displayed");
	

	}

}
