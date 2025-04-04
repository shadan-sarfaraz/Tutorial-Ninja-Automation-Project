package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class MyAccountInformationPage extends RootPage {

	WebDriver driver;

	public MyAccountInformationPage(WebDriver driver) {
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

	public String getTelephoneDomAttribute(String attributeName) {
		return elementUtilities.getElementDomAttribute(telephoneField, attributeName);
	}

	public String getEmailDomAttribute(String attributeName) {
		return elementUtilities.getElementDomAttribute(emailField, attributeName);
	}

	public String getEmailDomPropperty(String propertyName) {
		return elementUtilities.getElementDomProperty(emailField, propertyName);
	}

	public String getLastNameDomAttribute(String attributeName) {
		return elementUtilities.getElementDomAttribute(lastNameField, attributeName);
	}

	public String getFirstNameDomAttribute(String attributeName) {
		return elementUtilities.getElementDomAttribute(firstNameField, attributeName);
	}

}
