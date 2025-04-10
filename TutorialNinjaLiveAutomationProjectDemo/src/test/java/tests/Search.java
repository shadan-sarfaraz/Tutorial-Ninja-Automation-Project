package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Base;
import pages.HeaderOptions;
import pages.SearchPage;
import utils.CommonUtilities;

public class Search extends Base {

	@BeforeMethod
	public void setup() {
		driver = openBrowserAndApplicationPageURL();
		headerOptions = new HeaderOptions(driver);
	}

	@Test(priority = 1)
	public void verifySearchWithAnExistingProduct() {
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingProduct"));
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSurchResultPage());
		Assert.assertTrue(searchPage.isProductDisplayedInSearchResult());
	}

	@Test(priority = 2)
	public void verifySearchWithNonExistingProduct() {
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("nonExistingProduct"));
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSurchResultPage());
		Assert.assertEquals(searchPage.getNoProductMessage(), "There is no product that matches the search criteria.");
	}

	@Test(priority = 3)
	public void verifySearchWithoutEnteringAnyProduct() {
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSurchResultPage());
		Assert.assertEquals(searchPage.getNoProductMessage(), "There is no product that matches the search criteria.");
	}

	@Test(priority = 4)
	public void verifyProductSearchAfterLogin() {
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.loginIntoApplication(prop.getProperty("existingEmailTwo"),
				prop.getProperty("validPasswordTwo"));
		headerOptions = myAccountPage.getHeaderOptions();
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingProduct"));
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSurchResultPage());
		Assert.assertTrue(searchPage.isProductDisplayedInSearchResult());
	}

	@Test(priority = 5)
	public void verifyProductSearchResultingMultipleSearch() {

		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingProductTwo"));
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSurchResultPage());
		System.out.println(searchPage.getProductsCount());
		Assert.assertTrue(searchPage.getProductsCount() > 0);
	}

	@Test(priority = 6)
	public void verifySearchFunctionalityFieldPlaceholders() {
		Assert.assertEquals(headerOptions.getSearchBoxFieldPlaceholderText(), "Search");
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertEquals(searchPage.getSearchCriteriaFieldPlaceholderText(), "Keywords");
	}

	@Test(priority = 7)
	public void verifySearchFunctionalityusingSearchCriteriaField() {
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterProductIntoSearchCriteriaField(prop.getProperty("existingProduct"));
		searchPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.isProductDisplayedInSearchResult());
	}

	@Test(priority = 8)
	public void verifySearchUsingTextInProductDescription() {
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterTextInProductDescriptionIntoSearchCriteriaField(prop.getProperty("textInProductDescription"));
		searchPage.selectSearchInProductDescriptionField();
		searchPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.isProductHavingTextInItsDescriptionDisplayedInSearchResult());
	}

	@Test(priority = 9)
	public void verifySearchBySelectingTheCategory() {
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterProductIntoSearchCriteriaField(prop.getProperty("existingProductThree"));
		searchPage.selectOptionFromDropdownField(
				CommonUtilities.convertToInteger(prop.getProperty("correctCategoryIndex")));
		searchPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.isProductFromCorrectCategoryDisplayedInSearchResult());
		searchPage.selectOptionFromDropdownField(
				CommonUtilities.convertToInteger(prop.getProperty("wrongCategoryIndex")));
		searchPage.clickOnSearchButton();
		Assert.assertEquals(searchPage.getNoProductMessage(), "There is no product that matches the search criteria.");
	}

	@Test(priority = 10)
	public void verifySearchBySelectingToSearchInSubCategory() {
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterProductIntoSearchCriteriaField(prop.getProperty("existingProductThree"));
		searchPage.selectOptionFromDropdownField(prop.getProperty("superCategory"));
		Assert.assertEquals(searchPage.getNoProductMessage(), "There is no product that matches the search criteria.");
		searchPage.selectToSearchInSubCategories();
		searchPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.isProductFromCorrectCategoryDisplayedInSearchResult());
	}

	@Test(priority = 11)
	public void verifyListAndGridViewsInSearchResultsPageHavingOneProduct() {
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingProductThree"));
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.selectListOption();
		Assert.assertTrue(searchPage.getProductsCount() == 1);
		searchPage.clickOnAddtoCartOption();
		String expectedMessage = "Success: You have added " + prop.getProperty("existingProductThree")
				+ " to your shopping cart!";
		Assert.assertTrue(searchPage.getPageLevelSuccessMessage().contains(expectedMessage));
		refreshPage(searchPage.getDriver());
		searchPage.ClickOnAddtoWishListOption();
		expectedMessage = "You must login or create an account to save " + prop.getProperty("existingProductThree");
		Assert.assertTrue(searchPage.getPageLevelSuccessMessage().contains(expectedMessage));
		refreshPage(searchPage.getDriver());
		searchPage.clickOnComapreThisProductOption();
		expectedMessage = "Success: You have added " + prop.getProperty("existingProductThree")
				+ " to your product comparison";
		Assert.assertTrue(searchPage.getPageLevelSuccessMessage().contains(expectedMessage));
		productDisplayPage = searchPage.clickOnProductOneImage();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
		searchPage = new SearchPage(driver);
		productDisplayPage = searchPage.clickOnProductOneName();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
		searchPage = new SearchPage(driver);
		searchPage.selectGridOption();
		Assert.assertTrue(searchPage.getProductsCount() == 1);
		refreshPage(searchPage.getDriver());
		searchPage.clickOnAddtoCartOption();
		expectedMessage = "Success: You have added " + prop.getProperty("existingProductThree")
				+ " to your shopping cart!";
		Assert.assertTrue(searchPage.getPageLevelSuccessMessage().contains(expectedMessage));
		refreshPage(searchPage.getDriver());
		searchPage.ClickOnAddtoWishListOption();
		expectedMessage = "You must login or create an account to save " + prop.getProperty("existingProductThree");
		Assert.assertTrue(searchPage.getPageLevelSuccessMessage().contains(expectedMessage));
		refreshPage(searchPage.getDriver());
		searchPage.clickOnComapreThisProductOption();
		expectedMessage = "Success: You have added " + prop.getProperty("existingProductThree")
				+ " to your product comparison";
		Assert.assertTrue(searchPage.getPageLevelSuccessMessage().contains(expectedMessage));
		productDisplayPage = searchPage.clickOnProductOneImage();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
		searchPage = new SearchPage(driver);
		productDisplayPage = searchPage.clickOnProductOneName();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
	}

	@Test(priority = 12)
	public void verifyListAndGridViewWhenMultipleProductsAreDisplayed() {
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingProductTwo"));
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertTrue(searchPage.getProductsCount() > 1);
		searchPage.selectListOption();
		Assert.assertTrue(searchPage.getProductsCount() > 1);
		searchPage.selectGridOption();
		Assert.assertTrue(searchPage.getProductsCount() > 1);
	}

	@Test(priority = 13)
	public void verifyNavigationToProductComparisionPageFromSearchResultsPage() {
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingProductThree"));
		searchPage = headerOptions.clickOnSearchButton();
		productComparisonPage = searchPage.selectProductCompareOption();
		productComparisonPage.didWeNaviagteToProductComparisionPage();
	}

	@Test(priority = 14)
	public void verifyAllSortingOptionInSearchResultsPage() {
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingProductThree"));
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.selectSortOptionInDropdownField("Default");
		Assert.assertTrue(searchPage.didProductDisplayedInAscendingOrder());
	}

	@Test(priority = 15)
	public void verifyShowProductByLimitingount() {
		SoftAssert softAssert = new SoftAssert();
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingProductTwo"));
		searchPage = headerOptions.clickOnSearchButton();
		String productLimitOne = "20";
		searchPage.selectOptionInShowDropdownField(productLimitOne);
		softAssert.assertTrue(searchPage.getProductsCount() == Integer.parseInt(productLimitOne));
		String productLimitTwo = "25";
		searchPage.selectOptionInShowDropdownField(productLimitTwo);
		softAssert.assertTrue(searchPage.getProductsCount() == Integer.parseInt(productLimitTwo));
		String productLimitThree = "50";
		searchPage.selectOptionInShowDropdownField(productLimitThree);
		softAssert.assertTrue(searchPage.getProductsCount() == Integer.parseInt(productLimitThree));
		String productLimitFour = "75";
		searchPage.selectOptionInShowDropdownField(productLimitFour);
		softAssert.assertTrue(searchPage.getProductsCount() == Integer.parseInt(productLimitFour));
		String productLimitFive = "100";
		searchPage.selectOptionInShowDropdownField(productLimitFive);
		softAssert.assertTrue(searchPage.getProductsCount() == Integer.parseInt(productLimitFive));
		softAssert.assertAll();
	}

	@Test(priority = 16)
	public void verifyDisplayingOfSearchFieldAndSearchButtonOnAllPagesOfTheApplication() {
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(prop.getProperty("contactUsPage"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(prop.getProperty("registerPageURL"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(prop.getProperty("loginPageURL"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.loginIntoApplication(prop.getProperty("existingEmail"),
				prop.getProperty("validPassword"));
		myAccountPage.clickOnEditYourAccountInformationLink();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		myAccountPage.clickOnChangeYourPasswordOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		addressBookPage = myAccountPage.clickOnModifyYourAddressBookEnteriesOptions();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		addAddressPage = addressBookPage.clickNewAddressButton();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		addressBookPage = addAddressPage.enterMandetoryFieldsAndAddress(prop.getProperty("firstName"),
				prop.getProperty("lastname"), prop.getProperty("textInProductDescription"), prop.getProperty("city"),
				prop.getProperty("postCode"));
		editAddressPage = addressBookPage.clickOnEditButton();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		myAccountPage = editAddressPage.clickOnAccountBreadcrumb();
		myAccountPage.clickOnModifyYourWishListOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		orderHistoryPage = myAccountPage.clickOnViewYourOrderHistoryOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingProduct"));
		searchPage = headerOptions.clickOnSearchButton();
		productDisplayPage = searchPage.clickOnProductOneName();
		productDisplayPage.clickOnAddToCartButton();
		shoppingCartPage = productDisplayPage.selectShopingCartOptionOnTheSuccessMessage();
		checkoutPage = shoppingCartPage.clickOnCheckoutButton();
		checkoutPage.clickOnBillingDetailsContinueButton();
		checkoutPage.clickOnDeliveryDetailsContinueButton();
		checkoutPage.clickOnDeliveryMethodContinueButton();
		checkoutPage.selectTermsAndConditionOptio();
		checkoutPage.clickOnPaymentMethodContinueButton();
		// orderInformationpage = orderHistoryPage.selectViewOption();
		// Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		headerOptions.clickOnMyAccountDropMenu();
		myAccountPage = headerOptions.selectMyaccountOption();
		myAccountPage.clickOnDownloadsOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		myAccountPage.clickOnRewardPointsOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		productReturnsPage = myAccountPage.clickOnViewYourReturnRequestsOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		returnInformationPage = productReturnsPage.clickOnViewOptions();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		returnInformationPage.clickOnAccountBreadcrumb();
		myAccountPage = headerOptions.selectMyaccountOption();
		myAccountPage.clickOnYourTransactionOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		myAccountPage.clickOnRecurringPaymentsOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		myAccountPage.clickOnEditYourAffliateInformationOptions();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		myAccountPage.clickOnCustomAffiliateTrackingOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		myAccountPage.clickOnSubscribeOrUnsubscribeToNewsLetterOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		navigateToPage(prop.getProperty("shopingCartPage"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(prop.getProperty("shopingCartPage"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
	}
}
