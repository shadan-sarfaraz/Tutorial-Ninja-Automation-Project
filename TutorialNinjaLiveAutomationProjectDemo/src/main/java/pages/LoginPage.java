package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class LoginPage extends RootPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='btn btn-primary'][text()='Continue']")
	private WebElement continueButton;

	@FindBy(id = "input-email")
	private WebElement emailField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Login']")
	private WebElement loginBreadcrumb;

	@FindBy(linkText = "Forgotten Password")
	private WebElement forgottonPasswordLink;

	@FindBy(xpath = "(//div[@id='content']//h2)[1]")
	private WebElement firstHeading;

	@FindBy(xpath = "(//div[@id='content']//h2)[2]")
	private WebElement secondHeading;

	public String getFirstHeading() {
		return elementUtilities.getElementText(firstHeading);
	}

	public String getSecondHeading() {
		return elementUtilities.getElementText(secondHeading);
	}

	public String getEmailFieldPlaceHolderText() {
		return elementUtilities.getElementDomProperty(emailField, "placeholder");
	}

	public String getPasswordFieldPlaceHolderText() {
		return elementUtilities.getElementDomProperty(passwordField, "placeholder");
	}

	public ForgottenPasswordPage clickOnForgottonPasswardLink() {
		elementUtilities.clickOnElement(forgottonPasswordLink);
		return new ForgottenPasswordPage(driver);
	}

	public boolean didWeNavigateToLoginPage() {
		return elementUtilities.isElementDisplayed(loginBreadcrumb);
	}

	public RegisterPage clickOnContinueButton() {
		elementUtilities.clickOnElement(continueButton);
		return new RegisterPage(driver);
	}

	public MyAccountPage clickOnLoginButton() {
		elementUtilities.clickOnElement(loginButton);
		return new MyAccountPage(driver);
	}

	public LoginPage selectLoginBreadcrumbOption() {
		elementUtilities.clickOnElement(loginBreadcrumb);
		return new LoginPage(driver);
	}

	public void enterEmail(String emailText) {
		elementUtilities.enterTextIntoElement(emailField, emailText);
	}

	public void enterPassword(String passwordText) {
		elementUtilities.enterTextIntoElement(passwordField, passwordText);
	}

	public MyAccountPage loginIntoApplication(String emailText, String passwordText) {
		elementUtilities.enterTextIntoElement(emailField, emailText);
		elementUtilities.enterTextIntoElement(passwordField, passwordText);
		elementUtilities.clickOnElement(loginButton);
		return new MyAccountPage(driver);
	}

	public String getPasswordFieldDomAttribute(String attribute) {
		return elementUtilities.getElementDomAttribute(passwordField, attribute);
	}
}
