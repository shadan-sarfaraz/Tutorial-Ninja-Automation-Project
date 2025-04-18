package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class DownloadsPage extends RootPage {
	WebDriver driver;

	public DownloadsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "My Account")
	private WebElement myAccountBreadCrumb;

	public MyAccountPage clickOnMyAccountBreadCrumb() {
		elementUtilities.clickOnElement(myAccountBreadCrumb);
		return new MyAccountPage(driver);
	}

}
