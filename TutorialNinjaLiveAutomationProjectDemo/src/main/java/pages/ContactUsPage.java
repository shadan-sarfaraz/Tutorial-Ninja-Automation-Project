package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class ContactUsPage extends RootPage {
	WebDriver driver;

	public ContactUsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

}
