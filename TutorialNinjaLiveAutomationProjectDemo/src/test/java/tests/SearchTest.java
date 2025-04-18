package tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Base;
import pages.FooterOptions;
import pages.HeaderOptions;
import pages.ProductComparisonPage;
import pages.ProductDisplayPage;
import pages.SearchPage;
import utils.CommonUtilities;

public class SearchTest extends Base {
	public WebDriver driver;

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
		navigateToPage(getBaseURL() + prop.getProperty("contactUsPage"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("registerPageURL"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("loginPageURL"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		registerPage = headerOptions.selectRegisterOption();
		String emailAddress = CommonUtilities.generateBrandNewEmail();
		accountSuccessPage = registerPage.registerAnAccount(prop.getProperty("firstName"), prop.getProperty("lastname"),
				emailAddress, prop.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		rightColumnOptions = accountSuccessPage.getRightColumnOptions();
		myAccountPage = rightColumnOptions.clickOnMyAccountOptionAfterLogin();
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
		searchPage = headerOptions.enterProductAndClickOnSearchButton(prop.getProperty("existingProduct"));
		productDisplayPage = searchPage.clickOnProductOneName();
		shoppingCartPage = productDisplayPage.clickOnAddToCartButtonAndSelectShoppingCartOptions();
		checkoutPage = shoppingCartPage.clickOnCheckoutButton();
		checkoutSuccessPage = checkoutPage.placeOrder();
		homePage = checkoutSuccessPage.clickOnContinueButton();
		navigateToPage(getBaseURL() + prop.getProperty("myAccountPage"));
		orderHistoryPage = myAccountPage.clickOnViewYourOrderHistoryOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		orderInformationpage = orderHistoryPage.selectViewOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		productReturnsPage = orderInformationpage.selectReturnOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		productReturnsPage.selectFirstReasonForReturn();
		productReturnsPage.clickOnSubmitButton();
		rightColumnOptions = productReturnsPage.getRightColumnOptions();
		rightColumnOptions.clickOnMyAccountOption();
		myAccountPage = productReturnsPage.clickOnMyAccountBreadCrumb();
		myAccountPage.clickOnDownloadsOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("myAccountPage"));
		myAccountPage.clickOnRewardPointsOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		productReturnsPage = myAccountPage.clickOnViewYourReturnRequestsOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		returnInformationPage = productReturnsPage.clickOnViewOptions();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		myAccountPage = returnInformationPage.clickOnAccountBreadcrumb();
		myAccountPage.clickOnYourTransactionOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		myAccountPage.clickOnRecurringPaymentsOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		affiliatePage = myAccountPage.clickOnEditYourAffliateInformationOptions();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		affiliatePage.enterChequePayeeName(prop.getProperty("firstName"));
		affiliatePage.selectAboutUsCheckBox();
		myAccountPage = affiliatePage.clickOnContinueButton();
		myAccountPage.clickOnCustomAffiliateTrackingOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		myAccountPage.clickOnSubscribeOrUnsubscribeToNewsLetterOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		rightColumnOptions = myAccountPage.getRightColumnOptions();
		accountLogoutPage = rightColumnOptions.clickOnLogoutOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("aboutUsPage"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("deliverayInformationPage"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("privacyPolicyPage"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("termsAndConditionPage"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("brandsPage"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("siteMapPage"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("desktopCategoryPage"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("pcSubCategoryPage"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("macSubCategoryPage"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("laptopsAndNotebookCategoryPage"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("macsSubCategoryPage"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("windowsSubCategoryPage"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("componentsCategoryPage"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("miceAndTrackballsSubCategoryPage"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("monitersCategoryPage"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("affliateLoginPage"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("specialOffersPage"));
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		searchPage = headerOptions.enterProductAndClickOnSearchButton(prop.getProperty("existingProduct"));
		productDisplayPage = searchPage.clickOnProductOneName();
		shoppingCartPage = productDisplayPage.clickOnAddToCartButtonAndSelectShoppingCartOptions();
		guestCheckoutPage = shoppingCartPage.clickOnCheckoutButtonWithoutLogin();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
	}

	@Test(priority = 17)
	public void verifyNavigatingToSearchPageFromSiteMapPage() {
		footerOptions = new FooterOptions(headerOptions.getDriver());
		siteMapPage = footerOptions.selectSiteMapOption();
		searchPage = siteMapPage.clickOnSearchOption();
		Assert.assertTrue(searchPage.didWeNavigateToSurchResultPage());
	}

	@Test(priority = 18)
	public void verifyBreadcrumbOptionsInSearchResultPage() {
		searchPage = headerOptions.enterProductAndClickOnSearchButton(prop.getProperty("existingProduct"));
		searchPage.clickOnBreadcrumb();
		Assert.assertTrue(searchPage.didWeNavigateToSurchResultPage());
	}

	@Test(priority = 19)
	public void verifyUsingAllOptionsOnSearchResultPageUsingKeyboardKeys() {
		searchPage = headerOptions.clickOnSearchButton();
		actions = performKeyboardActions(getActions(driver), Keys.TAB, 21);
		actions = typeTextUsingActions(actions, prop.getProperty("existingProduct"));
		actions = performKeyboardActions(getActions(driver), Keys.TAB, 1);
		actions = performKeyboardActions(getActions(driver), Keys.ARROW_DOWN, 1);
		actions = performKeyboardActions(getActions(driver), Keys.TAB, 1);
		actions = performKeyboardActions(getActions(driver), Keys.SPACE, 1);
		actions = performKeyboardActions(getActions(driver), Keys.TAB, 2);
		actions = performKeyboardActions(getActions(driver), Keys.ENTER, 1);
		Assert.assertTrue(searchPage.isProductDisplayedInSearchResult());
		actions = performKeyboardActions(getActions(driver), Keys.TAB, 21);
		actions = typeTextUsingActions(actions, prop.getProperty("textInProductDescription"));
		actions = performKeyboardActions(getActions(driver), Keys.TAB, 3);
		actions = performKeyboardActions(getActions(driver), Keys.SPACE, 1);
		actions = performKeyboardActions(getActions(driver), Keys.TAB, 1);
		actions = performKeyboardActions(getActions(driver), Keys.ENTER, 1);
		Assert.assertTrue(searchPage.isProductFromCorrectCategoryDisplayedInSearchResult());
		actions = performKeyboardActions(getActions(driver), Keys.TAB, 26);
		actions = performKeyboardActions(getActions(driver), Keys.ENTER, 1);
		Assert.assertTrue(searchPage.isProductFromCorrectCategoryDisplayedInSearchResult());
		actions = performKeyboardActions(getActions(driver), Keys.TAB, 1);
		actions = performKeyboardActions(getActions(driver), Keys.ENTER, 1);
		Assert.assertTrue(searchPage.isProductFromCorrectCategoryDisplayedInSearchResult());
		actions = performKeyboardActions(getActions(driver), Keys.TAB, 1);
		actions = performKeyboardActions(getActions(driver), Keys.ENTER, 1);
		productComparisonPage = new ProductComparisonPage(searchPage.getDriver());
		Assert.assertTrue(productComparisonPage.didWeNaviagteToProductComparisionPage());
		navigateBackInBrowser(productComparisonPage.getDriver());
		actions = performKeyboardActions(getActions(driver), Keys.TAB, 1);
		actions = performKeyboardActions(getActions(driver), Keys.ARROW_DOWN, 1);
		Assert.assertTrue(searchPage.isProductFromCorrectCategoryDisplayedInSearchResult());
		actions = performKeyboardActions(getActions(driver), Keys.TAB, 30);
		actions = performKeyboardActions(getActions(driver), Keys.ARROW_DOWN, 1);
		Assert.assertTrue(searchPage.isProductFromCorrectCategoryDisplayedInSearchResult());
		actions = performKeyboardActions(getActions(driver), Keys.TAB, 31);
		actions = performKeyboardActions(getActions(driver), Keys.ENTER, 1);
		productDisplayPage = new ProductDisplayPage(searchPage.getDriver());
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
		actions = performKeyboardActions(getActions(driver), Keys.TAB, 1);
		actions = performKeyboardActions(getActions(driver), Keys.ENTER, 1);
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
		actions = performKeyboardActions(getActions(driver), Keys.TAB, 1);
		actions = performKeyboardActions(getActions(driver), Keys.ENTER, 1);
		Assert.assertTrue(productDisplayPage.isShoppingCartOptionDisplayedOnTheSuccessMessage());
		actions = performKeyboardActions(getActions(driver), Keys.TAB, 1);
		actions = performKeyboardActions(getActions(driver), Keys.ENTER, 1);
		Assert.assertTrue(productDisplayPage.isWishListOptionDisplayedOnTheSuccessMessage());
		actions = performKeyboardActions(getActions(driver), Keys.TAB, 1);
		actions = performKeyboardActions(getActions(driver), Keys.ENTER, 1);
		Assert.assertTrue(productDisplayPage.isProductComperisonOptionDisplayedOnTheSuccessMessage());
	}

	@Test(priority = 20)
	public void verifySearchPageURLTitleAndHeadingTest() {
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertEquals(searchPage.getPageHeading(), "Search");
		Assert.assertEquals(getPageURL(searchPage.getDriver()), getBaseURL() + prop.getProperty("searchPageURL"));
		Assert.assertEquals(getPageTitle(searchPage.getDriver()), "Search");
	}

	@Test(priority = 21)
	public void verifySearchPageUI() {
		searchPage = headerOptions.clickOnSearchButton();
		if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("edge")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualSearchPageUI.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualSearchPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedSearchPageUI.png"));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxSearchPageUI.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxSearchPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedFirefoxSearchPageUI.png"));
		} else if (browserName.equalsIgnoreCase("edge")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeSearchPageUI.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeSearchPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedEdgeSearchPageUI.png"));
		}
	}

	@Test(priority = 22)
	public void vverifySearchFunctionalityInAllEnvironment() {
		searchPage = headerOptions.enterProductAndClickOnSearchButton(prop.getProperty("existingProductThree"));
		Assert.assertTrue(searchPage.didWeNavigateToSurchResultPage());
		Assert.assertTrue(searchPage.isProductHavingTextInItsDescriptionDisplayedInSearchResult());
	}
}
