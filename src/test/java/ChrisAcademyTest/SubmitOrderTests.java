package ChrisAcademyTest;
import java.io.IOException;
import java.util.HashMap;
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
	public void submitOrder (HashMap<String,String> input) throws IOException, InterruptedException{
		// purpose is to get rid of object creation
		
		
		ProductPage productPage = landingPage.logiApplication(input.get("email"),input.get("password"));
		
		
		//ProductPage  productPage = new ProductPage(driver);
		List<WebElement> products =	productPage.getProductList();
		productPage.addProductToCart(input.get("targetProductName"));
		
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
		
		HashMap<String,String> map = new HashMap<String,String> ();
		map.put("email", "christophodibo@yahoo.com");
		map.put("password", "Father70");
		map.put("targetProductName", "ZARA COAT 3");
		
		HashMap<String,String> map1 = new HashMap<String,String> ();
		map1.put("email", "anshika@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("targetProductName", "ADIDAS ORIGINAL");
		
		HashMap<String,String> map2 = new HashMap<String,String> ();
		map2.put("email", "shetty@gmail.com");
		map2.put("password", "Iamking@000");
		map2.put("targetProductName", "ADIDAS ORIGINAL");
		
	return	new Object[][] {{map}, {map1},{map2}};
	}

}
