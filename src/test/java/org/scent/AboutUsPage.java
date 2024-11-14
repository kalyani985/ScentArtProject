package org.scent;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AboutUsPage {
	WebDriver driver;

	@FindBy(xpath = "(//a[contains(text(),'About Us')])[1]")
	WebElement aboutUsButton;

	@FindBy(xpath = "//h2[contains(text(),'AREAS OF SCENTING')]")
	WebElement areasOfScentingSection;

	@FindBy(linkText = "Hotels")
	WebElement hotels;

	@FindBy(linkText = "Offices")
	WebElement offices;

	@FindBy(xpath = "//a[contains(text(),'Airports Lounges')]")
	WebElement airports;

	public AboutUsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void navigateToAboutUs() {
		aboutUsButton.click();
	}

	public void scrollToAreasOfScenting() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", areasOfScentingSection);
	}

	public void verifyAreaDescriptions() {
		hotels.click();
		assert areasOfScentingSection.isDisplayed();
		offices.click();
		assert areasOfScentingSection.isDisplayed();
		airports.click();
		assert areasOfScentingSection.isDisplayed();
	}


}
