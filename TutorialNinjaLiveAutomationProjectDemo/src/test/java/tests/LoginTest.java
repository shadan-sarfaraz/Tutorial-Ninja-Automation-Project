package tests;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.HeaderOptions;
import pages.LoginPage;
import pages.MyAccountPage;
import utils.CommonUtilities;

public class LoginTest extends Base {
	public WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = openBrowserAndApplicationPageURL();
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccountDropMenu();
		loginPage = headerOptions.selectLoginOption();
	}

	@Test(priority = 1)
	public void verifyLoginIntoApplicationUsingValidCredentials() {
		Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
		loginPage.enterEmail(prop.getProperty("existingEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		myAccountPage = loginPage.clickOnLoginButton();
		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());

	}

	@Test(priority = 2)
	public void verifyLoginIntoApplicationUsingInValidCredentials() {
		loginPage.enterEmail(prop.getProperty("invalidEmailOne"));
		loginPage.enterPassword(prop.getProperty("mismachingPassword"));
		myAccountPage = loginPage.clickOnLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getPageLevelWarning(), expectedWarning);

	}

	@Test(priority = 3)
	public void verifyLoginIntoApplicationUsingInValidEmailAndValidPassword() {
		loginPage.enterEmail(CommonUtilities.generateBrandNewEmail());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		myAccountPage = loginPage.clickOnLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getPageLevelWarning(), expectedWarning);

	}

	@Test(priority = 4)
	public void verifyLoginIntoApplicationUsingValidEmailAndInValidPassword() {
		loginPage.enterEmail(prop.getProperty("existingEmail"));
		loginPage.enterPassword(prop.getProperty("mismachingPassword"));
		myAccountPage = loginPage.clickOnLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getPageLevelWarning(), expectedWarning);
	}

	@Test(priority = 5)
	public void verifyLoginIntoApplicationWithoutProvidingAnyCredentials() {
		myAccountPage = loginPage.clickOnLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getPageLevelWarning(), expectedWarning);
	}

	@Test(priority = 6)
	public void verifyForgottonPasswordOptionIsAvailable() {
		forgottenPasswordPage = loginPage.clickOnForgottonPasswardLink();
		Assert.assertTrue(forgottenPasswordPage.didWeNavigateToForgottenPasswardPage());
	}

	@Test(priority = 7)
	public void verifyLoginIntoTheApplicationUsingKeyboardKeys() {
		actions = performKeyboardActions(getActions(driver), Keys.TAB, 23);
		actions = typeTextUsingActions(actions, prop.getProperty("existingEmail"));
		actions = performKeyboardActions(getActions(driver), Keys.TAB, 1);
		actions = typeTextUsingActions(actions, prop.getProperty("validPassword"));
		actions = performKeyboardActions(getActions(driver), Keys.TAB, 2);
		actions = typeTextUsingActions(actions, prop.getProperty("validPassword"));
		performKeyboardActions(actions, Keys.ENTER, 1);
		myAccountPage = new MyAccountPage(driver);
		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());
	}

	@Test(priority = 8)
	public void verifyPlaceHoldersOfFieldsInLoginPage() {
		Assert.assertEquals(loginPage.getEmailFieldPlaceHolderText(), "E-Mail Address");
		Assert.assertEquals(loginPage.getPasswordFieldPlaceHolderText(), "Password");
	}

	@Test(priority = 9)
	public void verifyBrowsingBackAfterLogin() {
		myAccountPage = loginPage.loginIntoApplication(prop.getProperty("existingEmail"),
				prop.getProperty("validPassword"));
		navigateBackInBrowser(myAccountPage.getDriver());
		refreshPage(driver);
		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());
	}

	@Test(priority = 10)
	public void verifyBrowsingBackAfterLogout() {
		myAccountPage = loginPage.loginIntoApplication(prop.getProperty("existingEmail"),
				prop.getProperty("validPassword"));
		headerOptions = myAccountPage.getHeaderOptions();
		accountLogoutPage = headerOptions.selectLogoutOption();
		navigateBackInBrowser(accountLogoutPage.getDriver());
		refreshPage(accountLogoutPage.getDriver());
		loginPage = accountLogoutPage.getLoginPage();
		Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
	}

	@Test(priority = 11)
	public void verifyLoginIntoAccountUsingInactiveCredentials() {
		myAccountPage = loginPage.loginIntoApplication(prop.getProperty("inactiveEmail"),
				prop.getProperty("validPassword"));
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getPageLevelWarning(), expectedWarning);
	}

	@Test(priority = 12)
	public void verifyNumberOfUnsuccessfulLoginAttempt() {
		String invalidEmail = CommonUtilities.generateBrandNewEmail();
		for (int i = 0; i < 6; i++) {
			myAccountPage = loginPage.loginIntoApplication(invalidEmail, prop.getProperty("mismachingPassword"));
		}
		String expectedWarning = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
		Assert.assertEquals(loginPage.getPageLevelWarning(), expectedWarning);
	}

	@Test(priority = 13)
	public void verifyloginPasswordFieldForVisibility() {
		Assert.assertEquals(loginPage.getPasswordFieldDomAttribute("type"), "password");
	}

	@Test(priority = 14, enabled = false)
	public void verifyCopyingOfTextIntoPasswordField() {
		loginPage = new LoginPage(driver);
		loginPage.enterPassword(prop.getProperty("validPassword"));
//		copyingTextUsingKeyboadKeys(loginPage.getDriver());
	}

	@Test(priority = 15)
	public void verifyPasswordIsNotVisisbleInPageSource() throws InterruptedException {
		loginPage.enterPassword(prop.getProperty("randomPassword"));
		Assert.assertFalse(getPageSourceCode(loginPage.getDriver()).contains(prop.getProperty("randomPassword")));
		loginPage.clickOnLoginButton();
		Assert.assertFalse(getPageSourceCode(loginPage.getDriver()).contains(prop.getProperty("randomPassword")));

	}

	@Test(priority = 16)
	public void verifyLoggingIntoApplicationUsingChangedPassword() {
		myAccountPage = loginPage.loginIntoApplication(prop.getProperty("existingEmailTwo"),
				prop.getProperty("validPasswordTwo"));
		changePasswordPage = myAccountPage.clickOnChangeYourPasswordOption();
		changePasswordPage.enterNewPasswordIntoPasswordField(prop.getProperty("validPasswordThree"));
		changePasswordPage.enterNewPasswordIntoPasswordConfirmField(prop.getProperty("validPasswordThree"));
		myAccountPage = changePasswordPage.clickOnContinueButton();
		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());
		String expectedMessage = "Success: Your password has been successfully updated.";
		Assert.assertEquals(expectedMessage, myAccountPage.getPageLevelSuccessMessage());
		rightColumnOptions = myAccountPage.getRightColumnOptions();
		accountLogoutPage = rightColumnOptions.clickOnLogoutOption();
		homePage = accountLogoutPage.clickOnContinueButton();
		headerOptions = homePage.getHeaderOptions();
		loginPage = headerOptions.navigateToLoginPage();
		loginPage.loginIntoApplication(prop.getProperty("existingEmailTwo"), prop.getProperty("validPasswordTwo"));
		expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getPageLevelWarning(), expectedMessage);
		myAccountPage = loginPage.loginIntoApplication(prop.getProperty("existingEmailTwo"),
				prop.getProperty("validPasswordThree"));
		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());
		swapPassword(prop);
	}

	@Test(priority = 17)
	public void verifyLoginPageNavigation() {

		headerOptions = loginPage.getHeaderOptions();
		contactUsPage = headerOptions.selectPhoneIconOption();
		Assert.assertTrue(getPageTitle(headerOptions.getDriver()).equals("Contact Us"));
		navigateBackInBrowser(contactUsPage.getDriver());

		loginPage = headerOptions.selectHeartIconOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = headerOptions.selectWishListIconOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		shopingCart = headerOptions.selectShoppingCartIconOption();
		Assert.assertEquals(getPageTitle(shopingCart.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shopingCart.getDriver());

		shopingCart = headerOptions.selectShoppingCartHeaderOption();
		Assert.assertEquals(getPageTitle(shopingCart.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shopingCart.getDriver());

		shopingCart = headerOptions.selectCheckoutIcon();
		Assert.assertEquals(getPageTitle(shopingCart.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shopingCart.getDriver());

		shopingCart = headerOptions.selectCheckoutOption();
		Assert.assertEquals(getPageTitle(shopingCart.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shopingCart.getDriver());

		homePage = headerOptions.selectLogo();
		Assert.assertEquals(getPageTitle(homePage.getDriver()), "Your Store");
		navigateBackInBrowser(homePage.getDriver());

		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertEquals(getPageTitle(searchPage.getDriver()), "Search");
		navigateBackInBrowser(searchPage.getDriver());

		homePage = headerOptions.selectHomeBreadcrumbOption();
		Assert.assertEquals(getPageTitle(homePage.getDriver()), "Your Store");
		navigateBackInBrowser(homePage.getDriver());

		loginPage = headerOptions.selectAccountBreadcrumbOptionWithoutLogin();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = loginPage.selectLoginBreadcrumbOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		registerPage = loginPage.clickOnContinueButton();
		Assert.assertEquals(getPageTitle(registerPage.getDriver()), "Register Account");
		navigateBackInBrowser(registerPage.getDriver());

		forgottonPasswordPage = loginPage.clickOnForgottonPasswardLink();
		Assert.assertTrue(forgottonPasswordPage.didWeNavigateToForgottenPasswardPage());
		navigateBackInBrowser(forgottonPasswordPage.getDriver());

		loginPage.clickOnLoginButton();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		rightColumnOptions = loginPage.getRightColumnOptions();
		loginPage = rightColumnOptions.clickOnLoginOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		rightColumnOptions.clickOnRegisterOption();
		Assert.assertEquals(getPageTitle(registerPage.getDriver()), "Register Account");
		navigateBackInBrowser(registerPage.getDriver());

		forgottenPasswordPage = rightColumnOptions.clickOnForgottenPasswordOption();
		Assert.assertEquals(getPageTitle(forgottenPasswordPage.getDriver()), "Forgot Your Password?");
		navigateBackInBrowser(forgottenPasswordPage.getDriver());

		loginPage = rightColumnOptions.clickOnMyAccountOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnAddressBookOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnWishListOptionOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnOrderHistoryOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnDownloadOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnRecurringPaymentOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnRewardOptionOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnReturnOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnTransactionsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnNewsLetterOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		footerOptions = loginPage.getFooterOptions();
		aboutUsPage = footerOptions.clickOnAboutUsOption();
		Assert.assertEquals(getPageTitle(aboutUsPage.getDriver()), "About Us");
		navigateBackInBrowser(aboutUsPage.getDriver());

		deliveryInformationOptions = footerOptions.selectDeliveryInformationOption();
		Assert.assertEquals(getPageTitle(deliveryInformationOptions.getDriver()), "Delivery Information");
		navigateBackInBrowser(deliveryInformationOptions.getDriver());

		privacyPolicyPage = footerOptions.selectPrivacyPolicyOption();
		Assert.assertEquals(getPageTitle(privacyPolicyPage.getDriver()), "Privacy Policy");
		navigateBackInBrowser(privacyPolicyPage.getDriver());

		termsAndConditionPage = footerOptions.selectTermsAndConditionOption();
		Assert.assertEquals(getPageTitle(termsAndConditionPage.getDriver()), "Terms & Conditions");
		navigateBackInBrowser(termsAndConditionPage.getDriver());

		contactUsPage = footerOptions.selectContactUsOption();
		Assert.assertEquals(getPageTitle(contactUsPage.getDriver()), "Contact Us");
		navigateBackInBrowser(contactUsPage.getDriver());

		producReturnsPage = footerOptions.selectReturnsOption();
		Assert.assertEquals(getPageTitle(producReturnsPage.getDriver()), "Product Returns");
		navigateBackInBrowser(producReturnsPage.getDriver());

		siteMapPage = footerOptions.selectSiteMapOption();
		Assert.assertEquals(getPageTitle(siteMapPage.getDriver()), "Site Map");
		navigateBackInBrowser(siteMapPage.getDriver());

		brandPage = footerOptions.selectBrandsOption();
		Assert.assertEquals(getPageTitle(brandPage.getDriver()), "Find Your Favorite Brand");
		navigateBackInBrowser(brandPage.getDriver());

		giftCertificatePage = footerOptions.selectGiftCertificatesOption();
		Assert.assertEquals(getPageTitle(giftCertificatePage.getDriver()), "Purchase a Gift Certificate");
		navigateBackInBrowser(giftCertificatePage.getDriver());

		loginPage = footerOptions.selectaAffiliateOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Affiliate Program");
		navigateBackInBrowser(loginPage.getDriver());

		specialOfferPage = footerOptions.selectaSpecialsOption();
		Assert.assertEquals(getPageTitle(specialOfferPage.getDriver()), "Special Offers");
		navigateBackInBrowser(specialOfferPage.getDriver());

		myAccountPage = footerOptions.selectMyAccountOption();
		Assert.assertEquals(getPageTitle(myAccountPage.getDriver()), "Account Login");
		navigateBackInBrowser(specialOfferPage.getDriver());

		loginPage = footerOptions.selectOrderHistoryOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = footerOptions.selectWishListOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = footerOptions.selectNewsLetterOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

	}

	@Test(priority = 18)
	public void verifyDifferentWaysOfnavigatingToLoginpage() {

		Assert.assertTrue(loginPage.didWeNavigateToLoginPage());

		rightColumnOptions = loginPage.getRightColumnOptions();
		rightColumnOptions.clickOnLoginOption();
		Assert.assertTrue(loginPage.didWeNavigateToLoginPage());

		registerPage = loginPage.clickOnContinueButton();
		loginPage = registerPage.selectLoginPageOption();
		Assert.assertTrue(loginPage.didWeNavigateToLoginPage());

	}

	@Test(priority = 19)
	public void verifyLoginPageBreadcrumbURLTitleHeading() {

		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		Assert.assertEquals(getPageURL(loginPage.getDriver()), getBaseURL() + prop.getProperty("loginPageURL"));
		Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
		Assert.assertEquals(loginPage.getFirstHeading(), "New Customer");
		Assert.assertEquals(loginPage.getSecondHeading(), "Returning Customer");
	}

	@Test(priority = 20)
	public void verifyLoginPageUI() throws IOException {
		if (browserName.equalsIgnoreCase("chrome")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualLoginPageUI.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualLoginPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedLoginPageUI.png"));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxLoginPageUI.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxLoginPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedFirefoxLoginPageUI.png"));
		} else if (browserName.equalsIgnoreCase("edge")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeLoginPageUI.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeLoginPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedEdgeLoginPageUI.png"));
		}
	}

	@Test(priority = 21)
	public void verifyLoginInAllEnvironments() {

		Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
		loginPage.enterEmail(prop.getProperty("existingEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		myAccountPage = loginPage.clickOnLoginButton();
		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());
	}

}
