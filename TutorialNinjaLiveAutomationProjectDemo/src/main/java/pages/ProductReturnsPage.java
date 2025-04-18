package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;
import utils.CommonUtilities;

public class ProductReturnsPage extends RootPage {

	WebDriver driver;

	public ProductReturnsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@*='View']")
	private WebElement viewOption;
	
	@FindBy(name = "return_reason_id")
	private WebElement reasonForReturn;

	@FindBy(linkText = "My Account")
	private WebElement myAccountBreadCrumb;
	
	@FindBy(css = "input[value='Submit']")
	private WebElement submitButton;
	
	public void clickOnSubmitButton() {
		elementUtilities.clickOnElement(submitButton);
	}
	
	public void selectFirstReasonForReturn() {
		elementUtilities.clickOnElement(reasonForReturn);
	}

	public MyAccountPage clickOnMyAccountBreadCrumb() {
		elementUtilities.waitForElementAndClick(myAccountBreadCrumb, CommonUtilities.MIN_TIME);
		return new MyAccountPage(driver);
	}

	public ReturnInformationPage clickOnViewOptions() {
		elementUtilities.clickOnElement(viewOption);
		return new ReturnInformationPage(driver);
	}

}
