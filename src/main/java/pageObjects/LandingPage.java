package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponent.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		
		super(driver);
		//initialisation
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	;
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	

	@FindBy(xpath ="//div[@id='toast-container']/div/div")
			WebElement errorMessage1;
	
	
	@FindBy(css= "div[aria-label='Incorrect email or password.']")
	WebElement errorMessage;
	public ProductPage logiApplication(String email, String passwd) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(passwd);
		submit.click();
		ProductPage productPage = new ProductPage(driver);
		return productPage;
	}
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
