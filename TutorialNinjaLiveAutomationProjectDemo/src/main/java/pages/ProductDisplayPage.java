package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class ProductDisplayPage extends RootPage {
	WebDriver driver;

	public ProductDisplayPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "button-cart")
	private WebElement addtoCartButton;

	@FindBy(id = "//div[@class='alert alert-success alert-dismissible']/a[text='shopping cart")
	private WebElement shopingCartOption;

	public void clickOnAddToCartButton() {
		elementUtilities.clickOnElement(addtoCartButton);
	}

	public boolean didWeNavigateToProductDisplayPage() {
		return elementUtilities.isElementDisplayed(addtoCartButton);
	}

	public ShoppingCartPage selectShopingCartOptionOnTheSuccessMessage() {
		elementUtilities.waitForElementAndClick(shopingCartOption, 10);
		return new ShoppingCartPage(driver);
	}

}
