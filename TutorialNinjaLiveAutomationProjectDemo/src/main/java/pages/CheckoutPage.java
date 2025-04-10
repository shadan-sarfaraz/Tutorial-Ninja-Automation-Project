package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class CheckoutPage extends RootPage {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "button-payment-address")
	private WebElement billingDetailsContinueButton;

	@FindBy(id = "button-shipping-address")
	private WebElement deliveryDetailsContinueButton;

	@FindBy(id = "button-shipping-method")
	private WebElement deliveryMethodContinueButton;

	@FindBy(name = "agree")
	private WebElement termsAndConditionOptio;

	@FindBy(id = "button-payment-method")
	private WebElement paymentMethodContinueButton;

	@FindBy(id = "button-confirm")
	private WebElement confirmOrderButton;

	public void clickOnConfirmOrderButton() {
		elementUtilities.waitForElementAndClick(confirmOrderButton, 10);
	}

	public void clickOnPaymentMethodContinueButton() {
		elementUtilities.clickOnElement(paymentMethodContinueButton);
	}

	public void selectTermsAndConditionOptio() {
		elementUtilities.waitForElementAndClick(termsAndConditionOptio, 10);
	}

	public void clickOnDeliveryMethodContinueButton() {
		elementUtilities.waitForElementAndClick(deliveryMethodContinueButton, 10);
	}

	public void clickOnDeliveryDetailsContinueButton() {
		elementUtilities.waitForElementAndClick(deliveryDetailsContinueButton, 10);
	}

	public void clickOnBillingDetailsContinueButton() {
		elementUtilities.clickOnElement(billingDetailsContinueButton);
	}

}
