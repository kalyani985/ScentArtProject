Feature: SCENT-Arts Website Functionalities
  Validate various sections and functionalities of the SCENT-Arts website.

  Background:
    Given The user launches the browser and navigates to the SCENT-Arts website

  @verifyAreasOfScenting
  Scenario: Verify "AREAS OF SCENTING"
    When The user navigates to the "About Us" section
    And The user scrolls to the "AREAS OF SCENTING" section
    Then Descriptions should be displayed for each area (Hotels, Offices, Airports) in the "AREAS OF SCENTING" section

  @navigateToContactUs
  Scenario: Navigate to "Contact Us" from "Our Services"
    When The user navigates to the "Our Services" section
    And The user scrolls to the "Contact Us" button
    And The user clicks the "Contact Us" button
    Then The "Contact Us" page should be displayed

  @verifyContactUsPageFunctionality
  Scenario: Verify "Contact Us" Page Functionality
    When The user clicks the "Contact Us" button
    Then The "Contact Us" page should be displayed
    And All functionalities on the "Contact Us" page such as form fields, submit button, and map are functional