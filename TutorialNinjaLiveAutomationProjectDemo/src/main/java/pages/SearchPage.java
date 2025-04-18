package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;
import utils.CommonUtilities;

public class SearchPage extends RootPage {
	WebDriver driver;

	public SearchPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Search']")
	private WebElement searchBreadcrumb;

	@FindBy(linkText = "HP LP3065")
	private WebElement existingProductOne;

	@FindBy(linkText = "iMac")
	private WebElement existingProductThree;

	@FindBy(xpath = "//input[@id='button-search']/following-sibling::p")
	private WebElement noProductMessage;

	@FindBy(xpath = "//div[@class='product-thumb']")
	private List<WebElement> productThumbnail;

	@FindBy(id = "input-search")
	private WebElement searchCriteriaField;

	@FindBy(id = "button-search")
	private WebElement searchButton;

	@FindBy(id = "description")
	private WebElement searchInproductDescriptionField;

	@FindBy(name = "category_id")
	private WebElement categoryDropdownField;

	@FindBy(name = "sub_category")
	private WebElement searchInSubCategoriesOption;

	@FindBy(id = "list-view")
	private WebElement listOption;

	@FindBy(id = "grid-view")
	private WebElement gridOption;

	@FindBy(xpath = "//div[@class='product-thumb']//span[text()='Add to Cart']")
	private WebElement addToCartOption;

	@FindBy(xpath = "//button[@*='Add to Wish List']")
	private WebElement addToWishlistOption;

	@FindBy(xpath = "//button[@*='Compare this Product']")
	private WebElement compareThisProductOption;

	@FindBy(xpath = "(//div[@class='product-thumb']//img)[1]")
	private WebElement productOneImage;

	@FindBy(xpath = "(//div[@class='product-thumb']//h4//a)[1]")
	private WebElement productOneName;

	@FindBy(id = "compare-total")
	private WebElement productCompareOption;

	@FindBy(id = "input-sort")
	private WebElement sortDropdownFiled;

	@FindBy(id = "//div[@class='product-thumb']//h4/a")
	private List<WebElement> sortedProducts;

	@FindBy(id = "input-limit")
	private WebElement showDropdownField;

	public boolean didProductDisplayedInAscendingOrder() {
		return CommonUtilities.areItemsInListAreInAscendingOrder(elementUtilities.getTextOfElements(sortedProducts));
	}

	public void selectSortOptionInDropdownField(String optionText) {
		elementUtilities.selectOptionFromDropdownFieldUsingVisibleText(sortDropdownFiled, optionText);
	}

	public void selectOptionInShowDropdownField(String optionText) {
		elementUtilities.selectOptionFromDropdownFieldUsingVisibleText(showDropdownField, optionText);
	}

	public ProductComparisonPage selectProductCompareOption() {
		elementUtilities.clickOnElement(productCompareOption);
		return new ProductComparisonPage(driver);
	}

	public void selectGridOption() {
		elementUtilities.clickOnElement(gridOption);
	}

	public ProductDisplayPage clickOnProductOneName() {
		elementUtilities.clickOnElement(productOneName);
		return new ProductDisplayPage(driver);
	}

	public ProductDisplayPage clickOnProductOneImage() {
		elementUtilities.clickOnElement(productOneImage);
		return new ProductDisplayPage(driver);
	}

	public void clickOnComapreThisProductOption() {
		elementUtilities.clickOnElement(compareThisProductOption);
	}

	public void ClickOnAddtoWishListOption() {
		elementUtilities.clickOnElement(addToWishlistOption);
	}

	public void clickOnAddtoCartOption() {
		elementUtilities.clickOnElement(addToCartOption);
	}

	public void selectListOption() {
		elementUtilities.clickOnElement(listOption);
	}

	public void selectToSearchInSubCategories() {
		elementUtilities.clickOnElement(searchInSubCategoriesOption);
	}

	public void selectOptionFromDropdownField(int optionIndex) {
		elementUtilities.selectOptionFromDropdownFieldUsingIndex(categoryDropdownField, optionIndex);
	}

	public void selectOptionFromDropdownField(String optionText) {
		elementUtilities.selectOptionFromDropdownFieldUsingVisibleText(categoryDropdownField, optionText);
	}

	public void selectSearchInProductDescriptionField() {
		elementUtilities.clickOnElement(searchInproductDescriptionField);
	}

	public void enterTextInProductDescriptionIntoSearchCriteriaField(String textInProductDescription) {
		elementUtilities.enterTextIntoElement(searchCriteriaField, textInProductDescription);
	}

	public void clickOnSearchButton() {
		elementUtilities.clickOnElement(searchButton);
	}

	public void enterProductIntoSearchCriteriaField(String productName) {
		elementUtilities.enterTextIntoElement(searchCriteriaField, productName);
	}

	public String getSearchCriteriaFieldPlaceholderText() {
		return elementUtilities.getElementDomAttribute(searchCriteriaField, "placeholder");
	}

	public boolean isProductFromCorrectCategoryDisplayedInSearchResult() {
		return elementUtilities.isElementDisplayed(productThumbnail.get(0));
	}

	public int getProductsCount() {
		return elementUtilities.getElementCount(productThumbnail);
	}

	public String getNoProductMessage() {
		return elementUtilities.getElementText(noProductMessage);
	}

	public boolean isProductDisplayedInSearchResult() {
		return elementUtilities.isElementDisplayed(existingProductOne);
	}

	public boolean isProductHavingTextInItsDescriptionDisplayedInSearchResult() {
		return elementUtilities.isElementDisplayed(existingProductThree);
	}

	public boolean didWeNavigateToSurchResultPage() {
		return elementUtilities.isElementDisplayed(searchBreadcrumb);
	}
	
	public SearchPage clickOnBreadcrumb() {
		elementUtilities.clickOnElement(searchBreadcrumb);
		return new SearchPage(driver);
	}

}
