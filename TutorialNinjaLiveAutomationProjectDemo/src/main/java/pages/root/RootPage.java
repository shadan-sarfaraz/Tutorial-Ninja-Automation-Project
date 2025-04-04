package pages.root;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.AccountLogoutPage;
import pages.AccountSuccessPage;
import pages.FooterOptionsPage;
import pages.HeaderOptions;
import pages.HomePage;
import pages.LoginPage;
import pages.RightColumnOptions;
import utils.ElementUtilities;

public class RootPage {

	WebDriver driver;
	public ElementUtilities elementUtilities;

	public RootPage(WebDriver driver) {
		this.driver = driver;
		elementUtilities = new ElementUtilities(driver);
		PageFactory.initElements(driver, this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	@FindBy(xpath = "//div[@id='content']/h1")
	private WebElement pageHeading;

	@FindBy(xpath = "//i[@class='fa fa-home']")
	private WebElement homeBreadCrumb;

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Account']")
	private WebElement accountBreadCrumb;

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement pageLevelWarning;

	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement pageLevelSuccessMessage;

	public String getPageLevelWarning() {
		return elementUtilities.getElementText(pageLevelWarning);
	}

	public String getPageLevelSuccessMessage() {
		return elementUtilities.getElementText(pageLevelSuccessMessage);
	}

	public LoginPage selectAccountBreadcrumbOptionWithoutLogin() {
		elementUtilities.clickOnElement(accountBreadCrumb);
		return new LoginPage(driver);
	}

	public HomePage selectHomeBreadcrumbOption() {
		elementUtilities.clickOnElement(homeBreadCrumb);
		return new HomePage(driver);
	}

	public String getPageHeading() {
		return elementUtilities.getElementText(pageHeading);
	}

	public HeaderOptions getHeaderOptions() {
		return new HeaderOptions(driver);
	}

	public RightColumnOptions getRightColumnOptions() {
		return new RightColumnOptions(driver);
	}

	public AccountSuccessPage getAccountSuccessPage() {
		return new AccountSuccessPage(driver);
	}

	public FooterOptionsPage getFooterOptions() {
		return new FooterOptionsPage(driver);
	}

	public LoginPage getLoginPage() {
		return new LoginPage(driver);
	}

	public AccountLogoutPage getAccountLogoutPage() {
		return new AccountLogoutPage(driver);
	}

}
