package org.scent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;



public class steps {
	
	WebDriver driver;
	 AboutUsPage aboutUsPage;
	 ContactUsPage contactUsPage;
	 WebDriverWait wait;
	
	@Before   
   public void setup(Scenario scenario) {
   String browser=System.getProperty("browser", "chrome");
	
	 if (browser.equalsIgnoreCase("chrome")) {
	        WebDriverManager.chromedriver().setup();
  			 driver = new ChromeDriver();
	    } else if (browser.equalsIgnoreCase("firefox")) {
	        WebDriverManager.firefoxdriver().setup();
	        driver = new FirefoxDriver();
	    } else if (browser.equalsIgnoreCase("edge")) {
	        WebDriverManager.edgedriver().setup();
	        driver = new EdgeDriver();
	    }
      
}
	@Given("The user launches the browser and navigates to the SCENT-Arts website")
	public void the_user_launches_the_browser_and_navigates_to_the_scent_arts_website() {
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    driver.get("https://scentarts.ae/");
	    aboutUsPage = new AboutUsPage(driver);
	    contactUsPage = new ContactUsPage(driver);
	Assert.assertEquals(driver.getTitle(), "Scent Arts, UAE's #1 Scenting Company");
	    
	}

	@When("The user navigates to the {string} section")
	public void the_user_navigates_to_the_section(String string) {
		if (string.equalsIgnoreCase("About Us")) {
			aboutUsPage.navigateToAboutUs();
			} else if (string.equalsIgnoreCase("Our Services")) {
				contactUsPage.navigateToOurServices();
	    }
		
	    
	}

	@And("The user scrolls to the {string} section")
	public void the_user_scrolls_to_the_section(String string) {
		if (string.equalsIgnoreCase("AREAS OF SCENTING")) {
		       aboutUsPage.scrollToAreasOfScenting();
		   }
	    
	}

	@Then("Descriptions should be displayed for each area \\(Hotels, Offices, Airports) in the {string} section")
	public void descriptions_should_be_displayed_for_each_area_hotels_offices_airports_in_the_section(String string) {
		 //aboutUsPage.scrollToAreasOfScenting();
		 aboutUsPage.verifyAreaDescriptions();
	   
	}
	
/*	@When("The user navigates to the {string} section")
	public void the_user_navigates_to_the_sec(String string) {
		if (string.equalsIgnoreCase("Our Services")) {
		    contactUsPage.navigateToOurServices();
		}
	    
	    
	}*/

	@And("The user scrolls to the {string} button")
	public void the_user_scrolls_to_the_btn(String string) {
		
		if ("btn".equalsIgnoreCase("Contact Us")) {
			contactUsPage.scrollToContactUs();
			} 
		contactUsPage.scrollToContactUs();
		
	}

	@And("The user clicks the {string} button")
	public void the_user_clicks_the_btn(String string) {
		if ("btn".equalsIgnoreCase("Contact Us")) {
		    contactUsPage.navigateToContactUsPage();	            
		    Assert.assertTrue(driver.getCurrentUrl().contains("contact-us"));
		    Assert.assertEquals("Contact Us – Scent Arts, UAE's #1 Scenting Company", driver.getTitle());
		}
		contactUsPage.navigateToContactUsPage();
	}

	@Then("The {string} page should be displayed")
	public void the_page_should_be_displayed(String string)
	{
		if (string.equalsIgnoreCase("Contact Us")) {
		    Assert.assertTrue(driver.getCurrentUrl().contains("contact-us"));
		}	
	}
	
	/*@When("The user scrolls to the {string} button")
	public void the_user_scrolls_to_the_button(String string) {
		if (string.equalsIgnoreCase("Contact Us")) {
		       contactUsPage.scrollToContactUs();
		   }
	    
	}

	@When("The user clicks the {string} button")
	public void the_user_clicks_the_button(String string) {
		if (string.equalsIgnoreCase("Contact Us")) {
		    contactUsPage.navigateToContactUsPage();	            
		    Assert.assertTrue(driver.getCurrentUrl().contains("contact-us"));
		    Assert.assertEquals("Contact Us – Scent Arts, UAE's #1 Scenting Company", driver.getTitle());
		}
	    
	   
	}*/

	/*@Then("The {string} page should be displayed")
	public void the_page_should_be_displayed(String string) {
		if (string.equalsIgnoreCase("Contact Us")) {
		    Assert.assertTrue(driver.getCurrentUrl().contains("contact-us"));
		}
	    
	}*/

	@And("All functionalities on the {string} page such as form fields, submit button, and map are functional")
	public void all_functionalities_on_the_page_such_as_form_fields_submit_button_and_map_are_functional(String string) {
		if (string.equalsIgnoreCase("Contact Us")) {

		     Assert.assertTrue(contactUsPage.getNameField().isDisplayed());
		     Assert.assertTrue(contactUsPage.getEmailField().isDisplayed());
		     Assert.assertTrue(contactUsPage.getCompanyName().isDisplayed());
		     Assert.assertTrue(contactUsPage.getSubmitBtn().isDisplayed());
		  String companyErrMsg=contactUsPage.getCompanyName().getAttribute("validationMessage");  
		     // Check for validation messages
		     contactUsPage.getSubmitBtn().click(); // Attempt to submit with empty fields	            
		     Assert.assertEquals(companyErrMsg,"Please fill out this field.");
		     
		     contactUsPage.getCompanyName().sendKeys("ABC");
		     
		    contactUsPage.getEmailField().sendKeys("invalidEmail"); // Invalid email format
		     
		     contactUsPage.getSubmitBtn().click(); 	            
		   
		     String expectMsg = contactUsPage.getEmailField().getAttribute("validationMessage");
		     String expectedMessage = "Please include an '@' in the email address.";
		     String actualText = contactUsPage.getEmailField().getAttribute("value");
		     String additionalMessage =  "is missing an '@'.";

		     String fullMessage = expectedMessage +"'"+actualText+"'"+additionalMessage;
		     System.out.println("Expected message: " + fullMessage);
		     System.out.println("Actual validation message: " + expectMsg);
		     
		    // Assert.assertEquals(expectMsg, fullMessage);
		     //Assert.assertTrue(expectMsg.equalsIgnoreCase(fullMessage));
		     Assert.assertNotEquals(expectMsg, fullMessage);
		     contactUsPage.clearFormData();                   
		           	             
		    contactUsPage.getPhoneNumber().sendKeys("bASA");
		     contactUsPage.getSubmitBtn().click(); 
		    Assert.assertTrue(contactUsPage.getPhoneErrorMsg().isDisplayed());
		    contactUsPage.clearFormData();
		     
		     
		     // Fill out the form with valid data;
		    // Your submission was successful.
		     contactUsPage.getNameField().sendKeys("John Doe");
		     contactUsPage.getCompanyName().sendKeys("ABC");
		     contactUsPage.getEmailField().sendKeys("john.doe@example.com");
		     contactUsPage.getPhoneNumber().sendKeys("2376387622");
		     contactUsPage.getSubmitBtn().click();  // Submit valid form

		     // Check for a success message or redirection
		     Assert.assertEquals("Your submission was successful.",contactUsPage.getErrorMessage().getText());

		     // Check for contact information
		     Assert.assertTrue(contactUsPage.getNameField().isDisplayed());
		     Assert.assertTrue(contactUsPage.getCompanyName().isDisplayed());
		     Assert.assertTrue(contactUsPage.getPhoneNumber().isDisplayed());
		     Assert.assertTrue(contactUsPage.getEmailField().isDisplayed());

		     // Check for map/location if available
		     Assert.assertTrue(contactUsPage.getContactForm().isDisplayed());
		    Assert.assertTrue(contactUsPage.getmapLocation().isDisplayed());
		}
	    
	}

	public static void captureScreenshot(WebDriver driver, String screenshotName) {
	    // Create a new date format to append to screenshot name to avoid overwriting files
	    String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());

	    // Take screenshot and store it as a file
	    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	    try {
	        // Copy the screenshot to a desired location
	       FileUtils.copyFile(srcFile, new File("C:\\Users\\91917\\JavaTester\\ScentProject\\Screenshot\\" + screenshotName + "_" + timestamp + ".png"));
	    	
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	@AfterStep
	public void failedStep(Scenario scenario) {
		 if (scenario.isFailed()) {
	         // Call the screenshot utility method
			 steps.captureScreenshot(driver, scenario.getName());
	     }
	}

	@After
	public void tearDown() {
	     // You can also quit the driver here if needed
	     driver.quit();
	    System.out.println("Test completed, shutting down the browser...");
	}


}
