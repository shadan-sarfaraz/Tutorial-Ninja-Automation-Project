package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class MyAccountPage extends RootPage {

	WebDriver driver;

	public MyAccountPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Edit your account information")
	private WebElement editYourAccountInformationOption;

	@FindBy(linkText = "Subscribe / unsubscribe to newsletter")
	private WebElement subscribeUnsubscribeToNewsLetterOption;

	@FindBy(linkText = "Change your password")
	private WebElement changeYourPasswordOption;

	@FindBy(linkText = "Modify your address book entries")
	private WebElement modifyYourAddressBookEnteries;

	@FindBy(linkText = "Modify your wish list")
	private WebElement modifyYourWishListOption;

	@FindBy(linkText = "View your order history")
	private WebElement viewYourOrderHistoryOption;

	@FindBy(linkText = "Downloads")
	private WebElement downloadsOption;

	@FindBy(xpath = "//a[normalize-space()='Your Reward Points']")
	private WebElement yourRewardPointsOption;

	@FindBy(linkText = "View your return requests")
	private WebElement viewYourReturnRequestsOption;

	@FindBy(linkText = "Your Transactions")
	private WebElement yourTrasactionOptions;

	@FindBy(linkText = "Recurring payments")
	private WebElement recurringPaymentsOptions;

	@FindBy(linkText = "Register for an affiliate account")
	private WebElement registerForAnAffliateAccountOptions;

	@FindBy(linkText = "Edit your affiliate information")
	private WebElement editYorAnAffliateInformationOptions;

	@FindBy(linkText = "Edit your affiliate information")
	private WebElement customAffiliateTrackingOption;

	public AffiliateTrackingPage clickOnCustomAffiliateTrackingOption() {
		elementUtilities.clickOnElement(customAffiliateTrackingOption);
		return new AffiliateTrackingPage(driver);
	}

	public AffiliatePage clickOnEditYourAffliateInformationOptions() {
		elementUtilities.clickEitherOfTheElements(registerForAnAffliateAccountOptions,
				editYorAnAffliateInformationOptions);
		return new AffiliatePage(driver);
	}

	public RecurringPaymentsPage clickOnRecurringPaymentsOption() {
		elementUtilities.clickOnElement(recurringPaymentsOptions);
		return new RecurringPaymentsPage(driver);
	}

	public YourTransactionPage clickOnYourTransactionOption() {
		elementUtilities.clickOnElement(yourTrasactionOptions);
		return new YourTransactionPage(driver);
	}

	public ProductReturnsPage clickOnViewYourReturnRequestsOption() {
		elementUtilities.clickOnElement(viewYourReturnRequestsOption);
		return new ProductReturnsPage(driver);
	}

	public RewardPointsPage clickOnRewardPointsOption() {
		elementUtilities.clickOnElement(yourRewardPointsOption);
		return new RewardPointsPage(driver);
	}

	public DownloadsPage clickOnDownloadsOption() {
		elementUtilities.clickOnElement(downloadsOption);
		return new DownloadsPage(driver);
	}

	public OrderHistoryPage clickOnViewYourOrderHistoryOption() {
		elementUtilities.waitForElementAndClick(viewYourOrderHistoryOption, 10);
		return new OrderHistoryPage(driver);
	}

	public MyWishListPage clickOnModifyYourWishListOption() {
		elementUtilities.clickOnElement(modifyYourWishListOption);
		return new MyWishListPage(driver);
	}

	public AddressBookPage clickOnModifyYourAddressBookEnteriesOptions() {
		elementUtilities.clickOnElement(modifyYourAddressBookEnteries);
		return new AddressBookPage(driver);
	}

	public NewsLetterPage clickOnSubscribeOrUnsubscribeToNewsLetterOption() {
		elementUtilities.clickOnElement(subscribeUnsubscribeToNewsLetterOption);
		return new NewsLetterPage(driver);
	}

	public boolean didWeNavigateToMyAccountPage() {
		return elementUtilities.isElementDisplayed(editYourAccountInformationOption);
	}

	public MyAccountInformationPage clickOnEditYourAccountInformationLink() {
		elementUtilities.clickOnElement(editYourAccountInformationOption);
		return new MyAccountInformationPage(driver);
	}

	public ChangePasswordPage clickOnChangeYourPasswordOption() {
		elementUtilities.clickOnElement(changeYourPasswordOption);
		return new ChangePasswordPage(driver);
	}
}
