package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;
import utils.ElementUtilities;

public class HeaderOptions extends RootPage {

	WebDriver driver;

	public HeaderOptions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtilities = new ElementUtilities(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;

	@FindBy(linkText = "Login")
	private WebElement loginOption;

	@FindBy(linkText = "Register")
	private WebElement registerOption;

	@FindBy(xpath = "//i[@class='fa fa-phone']")
	private WebElement phoneIcon;

	@FindBy(xpath = "//i[@class='fa fa-heart']")
	private WebElement heartIcon;

	@FindBy(xpath = "//span[@class='hidden-xs hidden-sm hidden-md'][contains(text(),'Wish List')]")
	private WebElement wishListIcon;

	@FindBy(xpath = "//span[@class='hidden-xs hidden-sm hidden-md'][contains(text(),'Shopping Cart')]")
	private WebElement shoppingCartHeaderOption;

	@FindBy(xpath = "//span[text()='Shopping Cart']")
	private WebElement shoppingCartOption;

	@FindBy(xpath = "//i[@class='fa fa-share']")
	private WebElement checkOutHeaderOption;

	@FindBy(xpath = "//span[text()='Checkout']")
	private WebElement checkOutOption;

	@FindBy(linkText = "Qafox.com")
	private WebElement logo;

	@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
	private WebElement searchButton;

	@FindBy(linkText = "Logout")
	private WebElement logoutOption;

	@FindBy(name = "search")
	private WebElement searchBoxField;

	@FindBy(linkText = "My Account")
	private WebElement myAccountOption;

	public MyAccountPage selectMyaccountOption() {
		elementUtilities.clickOnElement(myAccountOption);
		return new MyAccountPage(driver);
	}

	public boolean isSearchBoxFieldDisplayed() {
		return elementUtilities.isElementDisplayed(searchBoxField);
	}

	public boolean areSearchBoxFieldAndSearchButtonDisplayed() {
		return isSearchButtonDisplayed() && isSearchBoxFieldDisplayed();
	}

	public boolean isSearchButtonDisplayed() {
		return elementUtilities.isElementDisplayed(searchButton);
	}

	public String getSearchBoxFieldPlaceholderText() {
		return elementUtilities.getElementDomAttribute(searchBoxField, "placeholder");
	}

	public void enterProductIntoSearchBoxField(String productName) {
		elementUtilities.enterTextIntoElement(searchBoxField, productName);
	}

	public boolean isLogoutOptionIsDisplayedInDropdown() {
		return elementUtilities.isElementDisplayed(logoutOption);
	}

	public boolean isLoginOptionIsDisplayedInDropdown() {
		return elementUtilities.isElementDisplayed(loginOption);
	}

	public AccountLogoutPage selectLogoutOption() {
		elementUtilities.clickOnElement(logoutOption);
		return new AccountLogoutPage(driver);
	}

	public SearchPage clickOnSearchButton() {
		elementUtilities.clickOnElement(searchButton);
		return new SearchPage(driver);
	}

	public HomePage selectLogo() {
		elementUtilities.clickOnElement(logo);
		return new HomePage(driver);
	}

	public ShopingCartPage selectCheckoutOption() {
		elementUtilities.clickOnElement(checkOutOption);
		return new ShopingCartPage(driver);
	}

	public ShopingCartPage selectCheckoutIcon() {
		elementUtilities.clickOnElement(checkOutHeaderOption);
		return new ShopingCartPage(driver);
	}

	public ShopingCartPage selectShoppingCartHeaderOption() {
		elementUtilities.clickOnElement(shoppingCartOption);
		return new ShopingCartPage(driver);
	}

	public ShopingCartPage selectShoppingCartIconOption() {
		elementUtilities.clickOnElement(shoppingCartHeaderOption);
		return new ShopingCartPage(driver);
	}

	public LoginPage selectWishListIconOption() {
		elementUtilities.clickOnElement(wishListIcon);
		return new LoginPage(driver);
	}

	public LoginPage selectHeartIconOption() {
		elementUtilities.clickOnElement(heartIcon);
		return new LoginPage(driver);
	}

	public LoginPage selectLoginOption() {
		elementUtilities.clickOnElement(loginOption);
		return new LoginPage(driver);
	}

	public ContactUsPage selectPhoneIconOption() {
		elementUtilities.clickOnElement(phoneIcon);
		return new ContactUsPage(driver);
	}

	public void clickOnMyAccountDropMenu() {
		elementUtilities.clickOnElement(myAccountDropMenu);
	}

	public RegisterPage selectRegisterOption() {
		elementUtilities.clickOnElement(registerOption);
		return new RegisterPage(driver);
	}

	public LoginPage navigateToLoginPage() {
		clickOnMyAccountDropMenu();
		return selectLoginOption();
	}

}
