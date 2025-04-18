package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class RightColumnOptions extends RootPage {

	WebDriver driver;

	public RightColumnOptions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//aside[@id='column-right']//a[text()='Register']")
	private WebElement registerOption;

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Logout']")
	private WebElement logoutOption;

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Login']")
	private WebElement loginOption;

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Forgotten Password']")
	private WebElement forgottenPasswordOption;

	@FindBy(xpath = "//a[@class='list-group-item'][text()='My Account']")
	private WebElement myAccountOption;

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Address Book']")
	private WebElement addressBookOption;

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Wish List']")
	private WebElement wishListOptionOption;

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Order History']")
	private WebElement orderHistoryOption;

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Downloads']")
	private WebElement downloadOption;

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Recurring payments']")
	private WebElement recurringPaymentsOption;

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Reward Points']")
	private WebElement rewardPointOption;

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Returns']")
	private WebElement returnsOption;

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Transactions']")
	private WebElement transactionsOption;

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Newsletter']")
	private WebElement newsLetterOption;

	public boolean isLogoutOptionDisplayed() {
		return elementUtilities.isElementDisplayed(logoutOption);
	}

	public AccountLogoutPage clickOnLogoutOption() {
		elementUtilities.clickOnElement(logoutOption);
		return new AccountLogoutPage(driver);
	}

	public LoginPage clickOnNewsLetterOption() {
		elementUtilities.clickOnElement(newsLetterOption);
		return new LoginPage(driver);
	}

	public LoginPage clickOnTransactionsOption() {
		elementUtilities.clickOnElement(transactionsOption);
		return new LoginPage(driver);
	}

	public LoginPage clickOnReturnOption() {
		elementUtilities.clickOnElement(returnsOption);
		return new LoginPage(driver);
	}

	public LoginPage clickOnRewardOptionOption() {
		elementUtilities.clickOnElement(rewardPointOption);
		return new LoginPage(driver);
	}

	public LoginPage clickOnRecurringPaymentOption() {
		elementUtilities.clickOnElement(recurringPaymentsOption);
		return new LoginPage(driver);
	}

	public LoginPage clickOnDownloadOption() {
		elementUtilities.clickOnElement(downloadOption);
		return new LoginPage(driver);
	}

	public LoginPage clickOnOrderHistoryOption() {
		elementUtilities.clickOnElement(orderHistoryOption);
		return new LoginPage(driver);
	}

	public LoginPage clickOnWishListOptionOption() {
		elementUtilities.clickOnElement(wishListOptionOption);
		return new LoginPage(driver);
	}

	public LoginPage clickOnAddressBookOption() {
		elementUtilities.clickOnElement(addressBookOption);
		return new LoginPage(driver);
	}

	public LoginPage clickOnMyAccountOption() {
		elementUtilities.clickOnElement(myAccountOption);
		return new LoginPage(driver);
	}

	public MyAccountPage clickOnMyAccountOptionAfterLogin() {
		elementUtilities.clickOnElement(myAccountOption);
		return new MyAccountPage(driver);
	}

	public ForgottenPasswordPage clickOnForgottenPasswordOption() {
		elementUtilities.clickOnElement(forgottenPasswordOption);
		return new ForgottenPasswordPage(driver);
	}

	public LoginPage clickOnLoginOption() {
		elementUtilities.clickOnElement(loginOption);
		return new LoginPage(driver);
	}

	public boolean didWeGetLoggedIn() {
		return elementUtilities.isElementDisplayed(logoutOption);
	}

	public RegisterPage clickOnRegisterOption() {
		elementUtilities.clickOnElement(registerOption);
		return new RegisterPage(driver);
	}

}
