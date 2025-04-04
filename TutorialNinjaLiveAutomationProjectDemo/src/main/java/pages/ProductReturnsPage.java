package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class ProductReturnsPage extends RootPage {

	WebDriver driver;

	public ProductReturnsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@*='View']")
	private WebElement viewOption;
	
	public ReturnInformationPage clickOnViewOptions() {
		elementUtilities.clickOnElement(viewOption);
		return new ReturnInformationPage(driver);
	}

}
