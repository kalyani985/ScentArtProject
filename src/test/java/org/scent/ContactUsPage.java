package org.scent;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactUsPage {
	WebDriver driver;

	@FindBy(linkText = "Our Services")
	WebElement ourServicesButton;

	@FindBy(linkText = "Contact Us")
	WebElement contactUsButton;

	@FindBy(xpath = "//form[@name='New Form']")
	WebElement contactForm;

	@FindBy(xpath = "//iframe[contains(@src, 'maps')]")
	WebElement mapLocation;

	@FindBy(xpath = "//input[@id='form-field-name']")
	WebElement nameField;

	@FindBy(xpath = "//input[@id='form-field-email']")
	WebElement companyName;

	@FindBy(id = "form-field-message")
	WebElement phoneNumber;

	@FindBy(id = "form-field-field_ea2bfcd")
	WebElement emailField;

	@FindBy(id = "form-field-field_67f8bfa")
	WebElement country;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement submitBtn;

	@FindBy(xpath = "//input[@id='form-field-message']//following-sibling::span")
	WebElement phoneErrorMsg;

	@FindBy(css = ".elementor-field-required")
	WebElement companyErrorMessage;

	@FindBy(xpath = "/html/body/div[2]/section[2]/div/div[2]/div/div/div/form/div[2]")
	WebElement errorMessage;

	

	public ContactUsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void setEmailField(WebElement emailField) {
		this.emailField = emailField;
	}

	public WebElement getContactForm() {
		return contactForm;
	}

	public WebElement getmapLocation() {
		return mapLocation;
	}

	public void navigateToOurServices() {
		ourServicesButton.click();
	}

	public void scrollToContactUs() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", contactUsButton);
	}

	public void navigateToContactUsPage() {
		contactUsButton.click();

	}

	public void verifyContactFormAndMap() {
		assert contactForm.isDisplayed();
		assert mapLocation.isDisplayed();
	}

	public WebElement getNameField() {
		return nameField;
	}

	public WebElement getCompanyName() {
		return companyName;
	}

	public WebElement getPhoneNumber() {
		return phoneNumber;
	}

	public WebElement getEmailField() {
		return emailField;
	}

	public WebElement getCountry() {
		return country;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}

	public WebElement getErrorMessage() {
		return errorMessage;
	}

	public WebElement getPhoneErrorMsg() {
		return phoneErrorMsg;
	}

	public String getErrorMsg(String str) {
		String errorMsg = errorMessage.getAttribute("textContent");
		String txterrorMsg = errorMessage.getText();
		System.out.printf("ErrorMsg of below send Btn", txterrorMsg);
		return errorMsg;

	}

	public void clearFormData() {
		nameField.clear();
		emailField.clear();
		phoneNumber.clear();
	}


}
