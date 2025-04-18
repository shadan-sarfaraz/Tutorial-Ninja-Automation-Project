package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class RegisterPage extends RootPage {

	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-email")
	private WebElement emailField;

	@FindBy(id = "input-telephone")
	private WebElement telephoneField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordField;

	@FindBy(name = "agree")
	private WebElement privacyPolicyField;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//label[normalize-space()='Yes']")
	private WebElement yesNewsLetterOption;

	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;

	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;

	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;

	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;

	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;

	@FindBy(xpath = "//input[@id='input-confirm']/following-sibling::div")
	private WebElement passwordConfirmationWarning;

	@FindBy(xpath = "//input[@name='newsletter'][@value='0']")
	private WebElement noNewsLetterOption;

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Register']")
	private WebElement registerPageBreadcrumb;

	@FindBy(css = "label[for='input-firstname']")
	private WebElement firstNameFieldLabel;

	@FindBy(css = "label[for='input-lastname']")
	private WebElement lastNameFieldLabel;

	@FindBy(css = "label[for='input-email']")
	private WebElement emailFieldLabel;

	@FindBy(css = "label[for='input-password']")
	private WebElement passwordFieldLabel;

	@FindBy(css = "label[for='input-telephone']")
	private WebElement telephoneFieldLabel;

	@FindBy(css = "label[for='input-confirm']")
	private WebElement passwordConfirmFieldLabel;

	@FindBy(css = "div[class='pull-right']")
	private WebElement privacyPolicyFieldFieldLabel;

	@FindBy(linkText = "login page")
	private WebElement loginOption;

	public AccountSuccessPage registerAnAccount(String firstName, String lastName, String emailText,
			String telephoneNumber, String validPassword) {
		enterTextIntoFirstNameField(firstName);
		enterTextIntoLastNameField(lastName);
		enterTextIntoEmailField(emailText);
		enterTelephone(telephoneNumber);
		enterPassword(validPassword);
		enterConfirmPassword(validPassword);
		selectPrivacyPolicy();
		return clickOnContinueButton();
	}

	public LoginPage selectLoginPageOption() {
		elementUtilities.clickOnElement(loginOption);
		return new LoginPage(driver);
	}

	public String getPasswordConfirmFieldDomAttribute(String attributeName) {
		return elementUtilities.getElementDomAttribute(confirmPasswordField, attributeName);
	}

	public String getPasswordFieldDomAttribute(String attributeName) {
		return elementUtilities.getElementDomAttribute(passwordField, attributeName);
	}

	public boolean isPrivacyFieldSelected() {
		return elementUtilities.isElementSelected(privacyPolicyFieldFieldLabel);
	}

	public String getContinueButtonCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(continueButton, propertyName);
	}

	public String getPasswordConfirmFieldCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(confirmPasswordField, propertyName);
	}

	public String getPasswordFieldCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(passwordField, propertyName);
	}

	public String getTelephoneFieldCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(telephoneField, propertyName);
	}

	public String getEmailFieldCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(emailField, propertyName);
	}

	public String getFirstNameCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(firstNameField, propertyName);
	}

	public String getLastNameCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(lastNameField, propertyName);
	}

	public void clearPasswordField() {
		elementUtilities.clearTextFromElement(passwordField);
	}

	public void clearTelephoneField() {
		elementUtilities.clearTextFromElement(telephoneField);
	}

	public void clearFirstnameField() {
		elementUtilities.clearTextFromElement(firstNameField);
	}

	public void clearLastnameField() {
		elementUtilities.clearTextFromElement(lastNameField);
	}

	public boolean passwordFieldWarningMessageIsDisplayed() {
		return elementUtilities.isElementDisplayed(passwordWarning);
	}

	public boolean telephoneFieldWarningMessageIsDisplayed() {
		return elementUtilities.isElementDisplayed(telephoneWarning);
	}

	public boolean emailFieldWarningMessageIsDisplayed() {
		return elementUtilities.isElementDisplayed(emailWarning);
	}

	public boolean firstNameWarningMessageIsDisplayed() {
		return elementUtilities.isElementDisplayed(firstNameWarning);
	}

	public boolean lastNameWarningMessageIsDisplayed() {
		return elementUtilities.isElementDisplayed(lastNameWarning);
	}

	public WebElement getPrivacyPolicyFieldFieldLabelWebElement() {

		return privacyPolicyFieldFieldLabel;
	}

	public WebElement getPasswordConfirmFieldLabelWebElement() {
		return passwordConfirmFieldLabel;
	}

	public WebElement getPasswordFieldLabelWebElement() {
		return passwordFieldLabel;
	}

	public WebElement getTelephoneFieldLabelWebElement() {
		return telephoneFieldLabel;
	}

	public WebElement getEmailFieldLabelWebElement() {
		return emailFieldLabel;
	}

	public WebElement getLastNameFieldLabelWebElement() {
		return lastNameFieldLabel;
	}

	public WebElement getFirstNameFieldLabelWebElement() {
		return firstNameFieldLabel;
	}

	public String getFirstNameFieldPlaceHolderText() {
		return elementUtilities.getElementDomProperty(firstNameField, "placeholder");
	}

	public String getLastNameFieldPlaceHolderText() {
		return elementUtilities.getElementDomProperty(lastNameField, "placeholder");
	}

	public String getEmailFieldPlaceHolderText() {
		return elementUtilities.getElementDomProperty(emailField, "placeholder");
	}

	public String getTelephoneFieldPlaceHolderText() {
		return elementUtilities.getElementDomProperty(telephoneField, "placeholder");
	}

	public String getPasswordFieldPlaceHolderText() {
		return elementUtilities.getElementDomProperty(passwordField, "placeholder");
	}

	public String getPasswordConfirmFieldPlaceHolderText() {
		return elementUtilities.getElementDomProperty(confirmPasswordField, "placeholder");
	}

	public void clearEmailField() {
		elementUtilities.clearTextFromElement(emailField);
	}

	public String getEmailValidationMessage() {
		return elementUtilities.getElementDomProperty(emailField, "validationMessage");
	}

	public RegisterPage selectRegisterBreadcrumbOption() {
		elementUtilities.clickOnElement(registerPageBreadcrumb);
		return new RegisterPage(driver);
	}

	public boolean didWeNavigateToRegisterPage() {
		return elementUtilities.isElementDisplayed(registerPageBreadcrumb);
	}

	public String getPasswordConfiramtionFieldyWarning() {
		return elementUtilities.getElementText(passwordConfirmationWarning);
	}

	public String getPasswordWarning() {
		return elementUtilities.getElementText(passwordWarning);
	}

	public String getTelephoneWarning() {
		return elementUtilities.getElementText(telephoneWarning);
	}

	public String getFirstNameWarning() {
		return elementUtilities.getElementText(firstNameWarning);
	}

	public String getLastNameWarning() {
		return elementUtilities.getElementText(lastNameWarning);
	}

	public String getEmailWarning() {
		return elementUtilities.getElementText(emailWarning);
	}

	public void selectYesNewsLetterOption() {
		elementUtilities.clickOnElement(yesNewsLetterOption);
	}

	public void selectNoNewsLetterOption() {
		elementUtilities.clickOnElement(noNewsLetterOption);
	}

	public void enterTextIntoFirstNameField(String firstNameText) {
		elementUtilities.enterTextIntoElement(firstNameField, firstNameText);
	}

	public void enterTextIntoLastNameField(String lastNameText) {
		elementUtilities.enterTextIntoElement(lastNameField, lastNameText);
	}

	public void enterTextIntoEmailField(String emailText) {
		elementUtilities.enterTextIntoElement(emailField, emailText);
	}

	public void enterTelephone(String telephoneText) {
		elementUtilities.enterTextIntoElement(telephoneField, telephoneText);
	}

	public void enterPassword(String passwordText) {
		elementUtilities.enterTextIntoElement(passwordField, passwordText);
	}

	public void enterConfirmPassword(String confirmPasswordText) {
		elementUtilities.enterTextIntoElement(confirmPasswordField, confirmPasswordText);
	}

	public void selectPrivacyPolicy() {
		elementUtilities.clickOnElement(privacyPolicyField);
	}

	public AccountSuccessPage clickOnContinueButton() {
		elementUtilities.clickOnElement(continueButton);
		return new AccountSuccessPage(driver);
	}

}
