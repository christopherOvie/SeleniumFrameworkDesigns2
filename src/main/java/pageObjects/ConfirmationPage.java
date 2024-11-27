package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import abstractComponent.AbstractComponents;

public class ConfirmationPage extends AbstractComponents{
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		
		super(driver);
		//initialisation
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
}
