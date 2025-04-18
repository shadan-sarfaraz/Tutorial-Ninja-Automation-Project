package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.HeaderOptions;
import utils.CommonUtilities;

public class LogoutTest extends Base {
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver = openBrowserAndApplicationPageURL();
		headerOptions = new HeaderOptions(driver);
	}

	@Test(priority = 1)
	public void verifyLoggingOutUsingMyAccountLogoutOption() {
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.loginIntoApplication(prop.getProperty("existingEmail"),
				prop.getProperty("validPassword"));
		headerOptions = loginPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		accountLogoutPage = headerOptions.selectLogoutOption();
		Assert.assertTrue(accountLogoutPage.didWeNavigateToAccountLogoutPage());
		headerOptions = accountLogoutPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		Assert.assertTrue(headerOptions.isLoginOptionIsDisplayedInDropdown());
		accountLogoutPage = headerOptions.getAccountLogoutPage();
		homePage = accountLogoutPage.clickOnContinueButton();
		Assert.assertEquals(getPageTitle(accountLogoutPage.getDriver()), "Your Store");
	}

	@Test(priority = 2)
	public void verifyLoggingOutUsingRightColumnLogoutOption() {
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.loginIntoApplication(prop.getProperty("existingEmail"),
				prop.getProperty("validPassword"));
		rightColumnOptions = myAccountPage.getRightColumnOptions();

		accountLogoutPage = rightColumnOptions.clickOnLogoutOption();
		Assert.assertTrue(accountLogoutPage.didWeNavigateToAccountLogoutPage());
		headerOptions = accountLogoutPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		Assert.assertTrue(headerOptions.isLoginOptionIsDisplayedInDropdown());
		accountLogoutPage = headerOptions.getAccountLogoutPage();
		homePage = accountLogoutPage.clickOnContinueButton();
		Assert.assertEquals(getPageTitle(accountLogoutPage.getDriver()), "Your Store");

	}

	@Test(priority = 3)
	public void verifyLoggingOutAndBrowsingBack() {

		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.loginIntoApplication(prop.getProperty("existingEmail"),
				prop.getProperty("validPassword"));
		headerOptions = loginPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		accountLogoutPage = headerOptions.selectLogoutOption();
		navigateBackInBrowser(accountLogoutPage.getDriver());
		refreshPage(accountLogoutPage.getDriver());
		Assert.assertTrue(headerOptions.isLoginOptionIsDisplayedInDropdown());
	}

	@Test(priority = 4)
	public void verifyNoLogoutOptionBeforeLoginInMyDropMenu() {
		headerOptions.clickOnMyAccountDropMenu();
		Assert.assertFalse(headerOptions.isLogoutOptionIsDisplayedInDropdown());
	}

	@Test(priority = 5)
	public void verifyNoLogoutOptionBeforeLoginInRightColumnOptions() {
		headerOptions.clickOnMyAccountDropMenu();
		registerPage = headerOptions.selectRegisterOption();
		rightColumnOptions = registerPage.getRightColumnOptions();
		Assert.assertFalse(rightColumnOptions.isLogoutOptionDisplayed());
	}

	@Test(priority = 6)
	public void verifylogginImmidiatlyAfterLogout() {
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.loginIntoApplication(prop.getProperty("existingEmail"),
				prop.getProperty("validPassword"));
		headerOptions = loginPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		accountLogoutPage = headerOptions.selectLogoutOption();
		headerOptions = loginPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.loginIntoApplication(prop.getProperty("existingEmail"),
				prop.getProperty("validPassword"));
		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());
	}

	@Test(priority = 7)
	public void verifyBreadcrumbtitleURLAndHeadingofAccountLogoutPage() {
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.loginIntoApplication(prop.getProperty("existingEmail"),
				prop.getProperty("validPassword"));
		headerOptions = loginPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		accountLogoutPage = headerOptions.selectLogoutOption();
		Assert.assertEquals(getPageTitle(accountLogoutPage.getDriver()), "Account Logout");
		Assert.assertEquals(getPageURL(accountLogoutPage.getDriver()), prop.getProperty("logoutPageURL"));
		Assert.assertTrue(accountLogoutPage.didWeNavigateToAccountLogoutPage());
		Assert.assertEquals(accountLogoutPage.getPageHeading(), "Account Logout");
	}

	@Test(priority = 8)
	public void verifyLogoutPageUI() throws IOException {
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.loginIntoApplication(prop.getProperty("existingEmail"),
				prop.getProperty("validPassword"));
		headerOptions = loginPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		if (browserName.equalsIgnoreCase("chrome")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualLogoutOptions.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualLogoutOptions.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedLogoutOptions.png"));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxLogoutOptions.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxLogoutOptions.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedFirefoxLogoutOptions.png"));
		} else if (browserName.equalsIgnoreCase("edge")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeLogoutOptions.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeLogoutOptions.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedEdgeLogoutOptions.png"));
		}

		accountLogoutPage = headerOptions.selectLogoutOption();
		if (browserName.equalsIgnoreCase("chrome")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualLogoutPageUI.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualLogoutPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedLogoutPageUI.png"));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxLogoutPageUI.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxLogoutPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedFirefoxLogoutPageUI.png"));
		} else if (browserName.equalsIgnoreCase("edge")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeLogoutPageUI.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeLogoutPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedEdgeLogoutPageUI.png"));
		}
	}

	@Test(priority = 9)
	public void verifyLogoutFunctionalityInAllSupportedEnvironments() {
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.loginIntoApplication(prop.getProperty("existingEmail"),
				prop.getProperty("validPassword"));
		headerOptions = loginPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		accountLogoutPage = headerOptions.selectLogoutOption();
		Assert.assertTrue(accountLogoutPage.didWeNavigateToAccountLogoutPage());
		accountLogoutPage = headerOptions.getAccountLogoutPage();
		homePage = accountLogoutPage.clickOnContinueButton();
		Assert.assertEquals(getPageTitle(accountLogoutPage.getDriver()), "Your Store");
	}

}
