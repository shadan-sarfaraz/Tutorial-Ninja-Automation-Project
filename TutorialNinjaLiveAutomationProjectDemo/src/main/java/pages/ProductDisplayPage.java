package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;
import utils.CommonUtilities;

public class ProductDisplayPage extends RootPage {
	WebDriver driver;

	public ProductDisplayPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "button-cart")
	private WebElement addtoCartButton;

	@FindBy(xpath = "//a[normalize-space()='shopping cart']")
	private WebElement shopingCartOption;

	@FindBy(xpath = "//a[normalize-space()='wish list']")
	private WebElement wishListOption;

	@FindBy(xpath = "//a[normalize-space()='product comparison']")
	private WebElement productComperisonOption;

	public ShoppingCartPage clickOnAddToCartButtonAndSelectShoppingCartOptions() {
		clickOnAddToCartButton();
		selectShopingCartOptionOnTheSuccessMessage();
		return new ShoppingCartPage(driver);
	}

	public void clickOnAddToCartButton() {
		elementUtilities.clickOnElement(addtoCartButton);
	}

	public boolean didWeNavigateToProductDisplayPage() {
		return elementUtilities.isElementDisplayed(addtoCartButton);
	}

	public ShoppingCartPage selectShopingCartOptionOnTheSuccessMessage() {
		elementUtilities.waitForElementAndClick(shopingCartOption, CommonUtilities.AVERAGE_TIME);
		return new ShoppingCartPage(driver);
	}

	public boolean isShoppingCartOptionDisplayedOnTheSuccessMessage() {
		return elementUtilities.waitAndCheckElementDisplayStatus(shopingCartOption, CommonUtilities.AVERAGE_TIME);
	}

	public boolean isWishListOptionDisplayedOnTheSuccessMessage() {
		return elementUtilities.waitAndCheckElementDisplayStatus(wishListOption, CommonUtilities.AVERAGE_TIME);
	}

	public boolean isProductComperisonOptionDisplayedOnTheSuccessMessage() {
		return elementUtilities.waitAndCheckElementDisplayStatus(productComperisonOption, CommonUtilities.AVERAGE_TIME);
	}

}
