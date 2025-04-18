package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class AffiliatePage extends RootPage {
	WebDriver driver;

	public AffiliatePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "input-company")
	private WebElement companyField;

	@FindBy(id = "input-cheque")
	private WebElement chequePayeeNameField;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//input[@name='agree']")
	private WebElement aboutUsCheckBox;

	public void selectAboutUsCheckBox() {
		elementUtilities.clickOnElement(aboutUsCheckBox);
	}

	public MyAccountPage clickOnContinueButton() {
		elementUtilities.clickOnElement(continueButton);
		return new MyAccountPage(driver);
	}

	public void enterChequePayeeName(String chequePayeeNameText) {
		elementUtilities.enterTextIntoElement(chequePayeeNameField, chequePayeeNameText);

	}

	public void enterCompanyName(String companyText) {
		elementUtilities.enterTextIntoElement(companyField, companyText);
	}
}
