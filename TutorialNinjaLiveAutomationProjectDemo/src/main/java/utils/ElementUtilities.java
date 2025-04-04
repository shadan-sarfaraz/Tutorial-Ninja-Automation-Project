package utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ElementUtilities {

	WebDriver driver;
	Actions action;
	Select select;

	public ElementUtilities(WebDriver driver) {
		this.driver = driver;
	}

	public List<String> getTextOfElements(List<WebElement> items) {
	    List<String> itemNames = new ArrayList<>(); // Initialize the list properly
	    for (WebElement item : items) {
	        itemNames.add(getElementText(item));
	    }
	    return itemNames;
	}
	public void selectOptionFromDropdownFieldUsingIndex(WebElement element, int optionIndex) {
		if (isElementDisplayedOnPage(element) && element.isEnabled()) {
			select = new Select(element);
			select.selectByIndex(optionIndex);
		}
	}

	public void selectOptionFromDropdownFieldUsingVisibleText(WebElement element, String optionText) {
		if (isElementDisplayedOnPage(element) && element.isEnabled()) {
			select = new Select(element);
			select.selectByVisibleText(optionText);
		}
	}

	public void clickOnElement(WebElement element) {
		if (isElementDisplayedOnPage(element) && element.isEnabled()) {
			element.click();
		}
	}

	public String getElementText(WebElement element) {
		String elementText = "";
		if (isElementDisplayedOnPage(element)) {
			elementText = element.getText();
		}
		return elementText;
	}

	public boolean isElementDisplayed(WebElement element) {
		boolean b = false;
		try {
			b = element.isDisplayed();
		} catch (NoSuchElementException e) {
			b = false;
		}
		return b;
	}

	private boolean isElementDisplayedOnPage(WebElement element) {
		boolean b = false;
		b = element.isDisplayed();
		return b;
	}

	public boolean isElementSelected(WebElement element) {
		boolean b = false;
		if (isElementDisplayedOnPage(element)) {
			b = element.isSelected();
		}
		return b;
	}

	public String getElementDomAttribute(WebElement element, String attributeName) {
		if (!element.getDomAttribute(attributeName).equals(null)) {

		}
		return element.getDomAttribute(attributeName);
	}

	public String getElementDomProperty(WebElement element, String attributeName) {
		return element.getDomProperty(attributeName);
	}

	public String getElementCSSValue(WebElement element, String cssPropertyName) {
		String value = "";
		value = element.getCssValue(cssPropertyName);
		return value;
	}

	public void clearTextFromElement(WebElement element) {
		if (isElementDisplayedOnPage(element) && element.isEnabled()) {
			element.clear();
		}
	}

	public void enterTextIntoElement(WebElement element, String text) {
		if (isElementDisplayedOnPage(element) && element.isEnabled()) {
			clearTextFromElement(element);
			element.sendKeys(text);
		}
	}

	public int getElementCount(List<WebElement> elements) {
		int count = 0;
		try {
			count = elements.size();
		} catch (NoSuchElementException e) {
			count = 0;
		}
		return count;
	}
}
