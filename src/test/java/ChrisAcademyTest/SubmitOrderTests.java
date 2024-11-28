package ChrisAcademyTest;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.OrdersPage;
import pageObjects.ProductPage;
public class SubmitOrderTests extends BaseTest {

	public String targetProductName = "ZARA COAT 3";
	
	@Test(dataProvider = "getData",groups= {"PurchaseOrder"})
	public void submitOrder (String email,String password, String targetProductName) throws IOException, InterruptedException{
		// purpose is to get rid of object creation
		
		
		ProductPage productPage = landingPage.logiApplication(email, password);
		
		
		//ProductPage  productPage = new ProductPage(driver);
		List<WebElement> products =	productPage.getProductList();
		productPage.addProductToCart(targetProductName);
		
		Thread.sleep(3000);
		CartPage cartPage =productPage.goToCartPage();
		//CartPage cartPage = new CartPage(driver);
		Thread.sleep(3000);
		// Call the method from the Page Object and verify
        Boolean isProductDisplayed = cartPage.verifySingleProductDisplayed();
        Assert.assertTrue(isProductDisplayed, "The product should be displayed, but it is not.");
		Thread.sleep(5000);
		
		CheckoutPage checkoutPage=	cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage =	checkoutPage.submitOrder();
		Thread.sleep(5000);
	String confirmationMessage =	confirmationPage.getConfirmationMessage();
	Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
			}
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest() {
		ProductPage productPage = landingPage.logiApplication("christophodibo@yahoo.com", "Father70");
		OrdersPage ordersPage =	productPage.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplayedName(targetProductName));
	}
	
	
	@DataProvider
	public Object[][] getData() {
	return	new Object[][] { {"christophodibo@yahoo.com","Father70","ZARA COAT 3"},{"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL"}  ,{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}    };
	}

}
