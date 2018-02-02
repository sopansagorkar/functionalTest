package com.nexant.dsm.libraries.customers.process;

import com.nexant.common.ExtJsUtils;
import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.dsm.libraries.serviceprovider.TestNewServiceProvider;
import com.nexant.webdriver.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by VKamenev on 5/30/2014.
 */
@Test
public class TestNewCustomerAdd {

    private WebDriver driver = null;

    public static String savedCustomerNumber ;
    public static String savedCustomerFirstName;
    public static String savedCustomerLastName;

    public static String savedCompanyNumber;
    public static String savedCompanyName;

    @BeforeClass
    public void getDriver(){
        driver = Driver.get();
    }
    
    /**
     * Test the customer tab landing page.
     * Assert we have the customer library, and listing of customers
     */
    @Test
    public void testCustomerLibraryLandingPage() {

        // click libraries -> customers
        SeleniumUtils.goToTab(driver, Locator.L_TAB_CUSTOMERS,  "DSM Central: Customer Library");
        
        // validate the customer library page
        
        // we should have a heading of customer library
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.h2Text("Customer Library"));
        
        // we should have show 20, 50 and 100 page size
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.textWithinElementWithClass("pager_count", "Show:"));
        SeleniumUtils.waitForClickable(driver, Locator.anchorTextWithinDivWithClass("pager_count", "20"));
        SeleniumUtils.waitForClickable(driver, Locator.anchorTextWithinDivWithClass("pager_count", "50"));
        SeleniumUtils.waitForClickable(driver, Locator.anchorTextWithinDivWithClass("pager_count", "100"));
        
        // DSMC-11092 and DSMC-9679 - we should have sorting for Customer Number, Company Name, First Name, Last Name, and Last Updated
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.textWithinElementWithClass("gridSort", "Sort By:"));
        SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanTextWithinDivWithClass("gridSort", "Customer Number"));
        SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanTextWithinDivWithClass("gridSort", "Company Name"));
        SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanTextWithinDivWithClass("gridSort", "First Name"));
        SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanTextWithinDivWithClass("gridSort", "Last Name"));
        SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanTextWithinDivWithClass("gridSort", "Last Updated"));
    }
    
    public static void goToAddCustomer(WebDriver driver){
        // click libraries -> customers
        SeleniumUtils.goToTab(driver, Locator.L_TAB_CUSTOMERS,  "DSM Central: Customer Library");

        // click add customer
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Customer.L_ADD_NEW_CUSTOMER);
        SeleniumUtils.click(driver, Locator.Customer.L_ADD_NEW_CUSTOMER);

        // check we are on the new customer screen
        SeleniumUtils.waitForVisible(driver, Locator.labelText("New Customer"), 30);
        SeleniumUtils.assertPageTitle(driver, "DSM Central: New Customer");
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.h2Text("Bill Accounts - Sites - Meters"));
    }

    public static void goToCustomer(WebDriver driver, String customerNumber){
        // click libraries -> customers
        SeleniumUtils.goToTab(driver, Locator.L_TAB_CUSTOMERS,  "DSM Central: Customer Library");

        SelectUtils.clickItemOfDropDownWithTextInput(driver, By.xpath("//*[@id='parameter_search_container']//input[@role='combobox']"), "Customer Number");
        SeleniumUtils.setText(driver, By.xpath("//*[@id='parameter_search_container']//input[@role='textbox']"), customerNumber);
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Search"));
        SeleniumUtils.sleepMillis(250);
        SeleniumUtils.waitForAllInVisible(driver, Locator.divText("Loading..."));
        
        SeleniumUtils.click(driver, Locator.anchorText(customerNumber));
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Customer Detail");
        
        SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyle("CIS"));
        SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyle("Projects"));
        SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyle("Notes"));
        SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyle("Appointments"));
        SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyle("Usage"));
    }

    /**
     * Test adding a customer without setting a customer number fails
     */
    @Test
    public void testCustomerNumberRequired() {
    	
    	goToAddCustomer(driver);

        // fill in form
        // default is residential
        SeleniumUtils.setText(driver, Locator.Customer.L_ADDRESS_1, Locator.Text.ADDRESS1);
        SeleniumUtils.setText(driver, Locator.Customer.L_ADDRESS_2, Locator.Text.ADDRESS2);
        SeleniumUtils.setText(driver, Locator.Customer.L_CITY, Locator.Text.CITY);
        SeleniumUtils.setText(driver, Locator.Customer.L_EMAIL_ADDRESS, Locator.Text.EMAIL);
        SeleniumUtils.setText(driver, Locator.Customer.L_COUNTRY, Locator.Text.COUNTRY);
        SeleniumUtils.setText(driver, Locator.Customer.L_DESCRIPTION, Locator.Text.DESCRIPTION);
        SeleniumUtils.setText(driver, Locator.Customer.L_ZIP, Locator.Text.ZIP_CODE);
        SelectUtils.clickItemName(driver, Locator.Customer.L_STATE_DRPBX, Locator.Text.STATE);

        // save
        SeleniumUtils.clickUsingJavascript(driver, Locator.Customer.L_SUBMIT_BTN);

        // check the warning is shown
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divTextContains(Locator.Text.ERROR_PLEASE_FIX_THE_VALIDATION_ERRORS));
        
        // check the field is marked with an error
        SeleniumUtils.assertFieldNameHasErrorMarking(driver, "customerNumber");
    }

    /**
     * The first name and last name validation is done differently, since the field is hidden for sectors other than residential.
     */
    @Test
    public void testResidentialCustomerFirstNameLastNameRequired() {

    	goToAddCustomer(driver);

        // fill in form
        // default is residential
        SeleniumUtils.setText(driver, Locator.Customer.L_CUSTOMER_NUMBER, "test");
        SeleniumUtils.setText(driver, Locator.Customer.L_ADDRESS_1, Locator.Text.ADDRESS1);
        SeleniumUtils.setText(driver, Locator.Customer.L_ADDRESS_2, Locator.Text.ADDRESS2);
        SeleniumUtils.setText(driver, Locator.Customer.L_CITY, Locator.Text.CITY);
        SeleniumUtils.setText(driver, Locator.Customer.L_EMAIL_ADDRESS, Locator.Text.EMAIL);
        SeleniumUtils.setText(driver, Locator.Customer.L_COUNTRY, Locator.Text.COUNTRY);
        SeleniumUtils.setText(driver, Locator.Customer.L_DESCRIPTION, Locator.Text.DESCRIPTION);
        SeleniumUtils.setText(driver, Locator.Customer.L_ZIP, Locator.Text.ZIP_CODE);
        SelectUtils.clickItemName(driver, Locator.Customer.L_STATE_DRPBX, Locator.Text.STATE);

        // save
        SeleniumUtils.clickUsingJavascript(driver, Locator.Customer.L_SUBMIT_BTN);

        // assert validation error
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divTextContains("First Name is required"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divTextContains("Last Name is required"));
    }


    @Test
    public void testAddNewCustomer() {

    	goToAddCustomer(driver);

        // fill in form
        // default is residential
        savedCustomerNumber = "cust" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Customer.L_CUSTOMER_NUMBER, savedCustomerNumber);

        savedCustomerFirstName = "f" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Customer.L_FIRST_NAME, savedCustomerFirstName);

        savedCustomerLastName = "l" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Customer.L_LAST_NAME, savedCustomerLastName);

        SeleniumUtils.setText(driver, Locator.Customer.L_ADDRESS_1, Locator.Text.ADDRESS1);
        SeleniumUtils.setText(driver, Locator.Customer.L_ADDRESS_2, Locator.Text.ADDRESS2);
        SeleniumUtils.setText(driver, Locator.Customer.L_CITY, Locator.Text.CITY);
        SeleniumUtils.setText(driver, Locator.Customer.L_EMAIL_ADDRESS, Locator.Text.EMAIL);
        SeleniumUtils.setText(driver, Locator.Customer.L_COUNTRY, Locator.Text.COUNTRY);
        SeleniumUtils.setText(driver, Locator.Customer.L_DESCRIPTION, Locator.Text.DESCRIPTION);
        SeleniumUtils.setText(driver, Locator.Customer.L_ZIP, Locator.Text.ZIP_CODE);
        SeleniumUtils.setText(driver, Locator.Customer.L_LATITUDE, Locator.Text.LATITUDE);
        SeleniumUtils.setText(driver, Locator.Customer.L_LONGITUDE, Locator.Text.LONGITUDE);
        SeleniumUtils.setText(driver, Locator.Customer.L_PRIMARY_COUNTRY_CODE, Locator.Text.PRIMARY_COUNTRY_CODE);
        SeleniumUtils.setText(driver, Locator.Customer.L_PRIMARY_AREA_CODE, Locator.Text.PRIMARY_AREA_CODE);
        SeleniumUtils.setText(driver, Locator.Customer.L_PRIMARY_PREFIX_CODE, Locator.Text.PRIMARY_PREFIX_CODE);
        SeleniumUtils.setText(driver, Locator.Customer.L_PRIMARY_SUFFIX_CODE, Locator.Text.PRIMARY_SUFFIX_CODE);
        SeleniumUtils.setText(driver, Locator.Customer.L_PRIMARY_EXT_CODE, Locator.Text.PRIMARY_EXT_CODE);
        SeleniumUtils.setText(driver, Locator.Customer.L_ALT_COUNTRY_CODE, Locator.Text.ALT_COUNTRY_CODE);
        SeleniumUtils.setText(driver, Locator.Customer.L_ALT_AREA_CODE, Locator.Text.ALT_AREA_CODE);
        SeleniumUtils.setText(driver, Locator.Customer.L_ALT_PREFIX_CODE, Locator.Text.ALT_PREFIX_CODE);
        SeleniumUtils.setText(driver, Locator.Customer.L_ALT_SUFFIX_CODE, Locator.Text.ALT_SUFFIX_CODE);
        SeleniumUtils.setText(driver, Locator.Customer.L_ALT_EXT_CODE, Locator.Text.ALT_EXT_CODE);
        SelectUtils.clickItemName(driver, Locator.Customer.L_STATE_DRPBX, Locator.Text.STATE);

        // save
        SeleniumUtils.clickUsingJavascript(driver, Locator.Customer.L_SUBMIT_BTN);

        // verify we are on the saved customer page
        // Check the breadcrumb: Library -> Customers -> lastname, firstname
        SeleniumUtils.waitForClickable(driver, By.linkText(savedCustomerLastName + ", " + savedCustomerFirstName));

        // verify the customer title "lastname, firstname"
        SeleniumUtils.waitForElement(driver, Locator.labelTextContains(savedCustomerLastName + ", " + savedCustomerFirstName));
    }
    
    /**
     * A company can be added through the customer library, by selecting Sector=Commercial, the company name appears
     */
    @Test
    public void testAddNewCompany() {
    	Assert.assertNotNull(TestNewServiceProvider.savedServiceProviderName, "this test required a new service provider created in TestNewServiceProvider");
    	
    	goToAddCustomer(driver);

        // fill in form
        // select sector = Commercial
        SelectUtils.clickItemName(driver, Locator.Customer.L_SECTOR, "Commercial");


        String companyNumber = "co" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Customer.L_CUSTOMER_NUMBER, companyNumber);

        String companyName = "company " + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Customer.L_COMPANY_NAME, companyName);

        SeleniumUtils.setText(driver, Locator.Customer.L_ADDRESS_1, Locator.Text.ADDRESS1);
        SeleniumUtils.setText(driver, Locator.Customer.L_ADDRESS_2, Locator.Text.ADDRESS2);
        SeleniumUtils.setText(driver, Locator.Customer.L_CITY, Locator.Text.CITY);
        SeleniumUtils.setText(driver, Locator.Customer.L_EMAIL_ADDRESS, Locator.Text.EMAIL);
        SeleniumUtils.setText(driver, Locator.Customer.L_COUNTRY, Locator.Text.COUNTRY);
        SeleniumUtils.setText(driver, Locator.Customer.L_DESCRIPTION, Locator.Text.DESCRIPTION);
        SeleniumUtils.setText(driver, Locator.Customer.L_ZIP, Locator.Text.ZIP_CODE);
        SeleniumUtils.setText(driver, Locator.Customer.L_LATITUDE, Locator.Text.LATITUDE);
        SeleniumUtils.setText(driver, Locator.Customer.L_LONGITUDE, Locator.Text.LONGITUDE);
        SeleniumUtils.setText(driver, Locator.Customer.L_PRIMARY_COUNTRY_CODE, Locator.Text.PRIMARY_COUNTRY_CODE);
        SeleniumUtils.setText(driver, Locator.Customer.L_PRIMARY_AREA_CODE, Locator.Text.PRIMARY_AREA_CODE);
        SeleniumUtils.setText(driver, Locator.Customer.L_PRIMARY_PREFIX_CODE, Locator.Text.PRIMARY_PREFIX_CODE);
        SeleniumUtils.setText(driver, Locator.Customer.L_PRIMARY_SUFFIX_CODE, Locator.Text.PRIMARY_SUFFIX_CODE);
        SeleniumUtils.setText(driver, Locator.Customer.L_PRIMARY_EXT_CODE, Locator.Text.PRIMARY_EXT_CODE);
        SeleniumUtils.setText(driver, Locator.Customer.L_ALT_COUNTRY_CODE, Locator.Text.ALT_COUNTRY_CODE);
        SeleniumUtils.setText(driver, Locator.Customer.L_ALT_AREA_CODE, Locator.Text.ALT_AREA_CODE);
        SeleniumUtils.setText(driver, Locator.Customer.L_ALT_PREFIX_CODE, Locator.Text.ALT_PREFIX_CODE);
        SeleniumUtils.setText(driver, Locator.Customer.L_ALT_SUFFIX_CODE, Locator.Text.ALT_SUFFIX_CODE);
        SeleniumUtils.setText(driver, Locator.Customer.L_ALT_EXT_CODE, Locator.Text.ALT_EXT_CODE);
        SelectUtils.clickItemName(driver, Locator.Customer.L_STATE_DRPBX, Locator.Text.STATE);
        SelectUtils.clickItemName(driver, Locator.Customer.L_SERVICE_PROVIDER, TestNewServiceProvider.savedServiceProviderName);

        // save
        SeleniumUtils.clickUsingJavascript(driver, Locator.Customer.L_SUBMIT_BTN);


        SeleniumUtils.waitForElement(driver, Locator.anchorText(companyName), 30);
        // verify we are on the saved customer page
        // Check the breadcrumb: Library -> Customers -> company name
        SeleniumUtils.waitForClickable(driver, By.linkText(companyName));

        // verify the customer title "company name"
        //SeleniumUtils.waitForElement(driver, Locator.labelText(companyName));

        savedCompanyNumber = companyNumber;
        savedCompanyName = companyName;
    }

    public static String savedBillAccountNumber;

    @Test
    public void testAddBillAccount() throws Exception {

    	if(savedCompanyNumber == null) throw new RuntimeException("no saved company number"); 
    	
        // click add bill account
        SeleniumUtils.click(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_BTN);

        // verify the popup comes up
        By popup = Locator.divText("New Bill Account");
        SeleniumUtils.waitForElement(driver, popup);

        String billAccountNumber = "bc" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_NUMBER, billAccountNumber);

        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_NAME, Locator.Text.NAME);
        SelectUtils.clickItemName(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_ACTIVE_INDICATOR_FIELD, "Active");

        
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanText("Revenue Class:")); // check we have the revenue class label
        SeleniumUtils.sleepSecs(1);
        SelectUtils.clickFirstItemOfBoundList(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_REVENUE_CLASS);
        
        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_ACTIVATION_DATE, Locator.Text.DATE_FROM);
        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_TERMINATION_DATE, Locator.Text.DATE_TO);
        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_DESCRIPTION, Locator.Text.DESCRIPTION);
        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_ADDRESS1, Locator.Text.ADDRESS1);
        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_ADDRESS2, Locator.Text.ADDRESS2);
        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_CITY, Locator.Text.CITY);
        SelectUtils.clickItemName(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_STATE, Locator.Text.STATE);
        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_COUNTRY, Locator.Text.COUNTRY);
        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_ZIP, Locator.Text.ZIP_CODE);
        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_LATITUDE, Locator.Text.LATITUDE);
        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_LONGITUDE, Locator.Text.LONGITUDE);

        // save
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("New Bill Account", "Save"));
        
        // verify popup goes away
        SeleniumUtils.waitForInVisible(driver, popup);

        // check we have a saved link
        SeleniumUtils.waitForClickable(driver, By.linkText(billAccountNumber));
        
        SeleniumUtils.sleepMillis(250);

        // check we have an add site link
        SeleniumUtils.waitForClickable(driver, Locator.anchorTextContains("+ Add Site"));

        savedBillAccountNumber = billAccountNumber;
    }

    public static String savedSiteNumber;
    public static String savedSiteDunNumber;
    public static String savedSiteAddress;

    @Test
    public void testAddNewSite() {

    	// assuming a bill account has been added, the + Add Site button shows up
    	By addSiteLink = Locator.anchorTextContains("+ Add Site");
        SeleniumUtils.waitForClickable(driver, addSiteLink);
    	SeleniumUtils.scrollToElement(driver, addSiteLink);
        SeleniumUtils.click(driver, addSiteLink);

        // verify the popup comes up
        By popup = Locator.divText("New Site");
        SeleniumUtils.waitForElement(driver, popup);

        String siteDunNumber = "dun" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("New Site", "dunsNumber"), siteDunNumber);

        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("New Site", "partyAddressName"), Locator.Text.NAME);
        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("New Site", "addressLine2"), Locator.Text.ADDRESS2);
        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("New Site", "country"), Locator.Text.COUNTRY);
        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("New Site", "postalCode"), Locator.Text.ZIP_CODE);
        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("New Site", "latitude"), Locator.Text.LATITUDE);
        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("New Site", "longitude"), Locator.Text.LONGITUDE);
        SelectUtils.clickItemName(driver, Locator.inputWithNameOnPopupTitle("New Site", "state"), Locator.Text.STATE);
      

        // save
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("New Site", "Save"));

        // error message about required fields should show
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divTextContains(Locator.Text.ERROR_PLEASE_FIX_THE_VALIDATION_ERRORS));
        SeleniumUtils.assertInputNameOnPopupTitleHasErrorMarking(driver, "New Site", "siteNumber");
        SeleniumUtils.assertInputNameOnPopupTitleHasErrorMarking(driver, "New Site", "addressLine1");
        SeleniumUtils.assertInputNameOnPopupTitleHasErrorMarking(driver, "New Site", "city");


        // correct the errors and save again
        String siteNumber = "site" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("New Site", "siteNumber"), siteNumber);
        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("New Site", "addressLine1"), Locator.Text.ADDRESS1);
        
        String city = "San Mateo";
        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("New Site", "city"), city);

        // save it again
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("New Site", "Save"));

        // waiting for the popup to go away
        SeleniumUtils.waitForInVisible(driver, popup);
        
        // there is a loading box when the popup goes away
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));

        // check the site is entered under the bill account
        SeleniumUtils.waitForElement(driver, By.linkText(siteNumber), 30);

        // check it is saved with a link
        SeleniumUtils.assertElementExistsWithWait(driver, By.linkText(siteNumber));
        
        SeleniumUtils.sleepMillis(250);

        // check we have an add meter link
        SeleniumUtils.waitForClickable(driver, Locator.anchorTextContains("+ Add Meter"));

        savedSiteNumber = siteNumber;
        savedSiteDunNumber = siteDunNumber;
        savedSiteAddress=Locator.Text.ADDRESS1+", "+Locator.Text.ADDRESS2+", "+city+", "+Locator.Text.STATE+", "+Locator.Text.ZIP_CODE+", "+Locator.Text.COUNTRY;
    }

    @Test
    public void testAppointmentsSorting(){
        SeleniumUtils.waitForClickable(driver, Locator.CustomerAppointment.CUSTOMER_APPOINTMENT_LINK);
        SeleniumUtils.click(driver, Locator.CustomerAppointment.CUSTOMER_APPOINTMENT_LINK);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.CustomerAppointment.APPOINTMENT_SORT_BY_NAME);
        SeleniumUtils.click(driver, Locator.CustomerAppointment.APPOINTMENT_SORT_BY_NAME);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.CustomerAppointment.APPOINTMENT_SORT_BY_GROUP);
        SeleniumUtils.click(driver, Locator.CustomerAppointment.APPOINTMENT_SORT_BY_GROUP);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.CustomerAppointment.APPOINTMENT_SORT_BY_TYPE);
        SeleniumUtils.click(driver, Locator.CustomerAppointment.APPOINTMENT_SORT_BY_TYPE);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.CustomerAppointment.APPOINTMENT_SORT_BY_PHONE);
        SeleniumUtils.click(driver, Locator.CustomerAppointment.APPOINTMENT_SORT_BY_PHONE);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.CustomerAppointment.APPOINTMENT_SORT_BY_EMAIL);
        SeleniumUtils.click(driver, Locator.CustomerAppointment.APPOINTMENT_SORT_BY_EMAIL);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.CustomerAppointment.APPOINTMENT_SORT_BY_ADDRESS);
        SeleniumUtils.click(driver, Locator.CustomerAppointment.APPOINTMENT_SORT_BY_ADDRESS);
//        SeleniumUtils.click(driver, Locator.CustomerAppointment.APPOINTMENT_SORT_BY_A_TIME);
//        SeleniumUtils.click(driver, Locator.CustomerAppointment.APPOINTMENT_SORT_BY_P_TIME);
//        SeleniumUtils.scrollToElement(driver, Locator.CustomerAppointment.APPOINTMENT_SORT_BY_NOTES);
//        SeleniumUtils.click(driver, Locator.CustomerAppointment.APPOINTMENT_SORT_BY_NOTES);
//        SeleniumUtils.click(driver, Locator.CustomerAppointment.APPOINTMENT_SORT_BY_ALT_TIME);
//        SeleniumUtils.click(driver, Locator.CustomerAppointment.APPOINTMENT_SORT_BY_ALT_TIME_2);
//        SeleniumUtils.click(driver, Locator.CustomerAppointment.APPOINTMENT_SORT_BY_STATUS);
    }

    
    static String savedMeterNumber = null;
    
    
    @Test
    public void testAddNewMeter() {

    	// assuming the add site completed, the + Add Meter button should show up
    	By addMeterLink = Locator.anchorTextContains("+ Add Meter");
    	
        SeleniumUtils.waitForClickable(driver, addMeterLink);
    	SeleniumUtils.scrollToElement(driver, addMeterLink);
        SeleniumUtils.click(driver, addMeterLink);

        // verify the popup comes up
        By popup = Locator.divText("New Meter");
        SeleniumUtils.waitForElement(driver, popup);

        // fill in the form
        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("New Meter", "servicePointTypeCode"), Locator.Text.STRING);
        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("New Meter", "addressLine1"), Locator.Text.ADDRESS1);
        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("New Meter", "addressLine2"), Locator.Text.ADDRESS2);
        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("New Meter", "city"), Locator.Text.CITY);
        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("New Meter", "country"), Locator.Text.COUNTRY);
        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("New Meter", "postalCode"), Locator.Text.ZIP_CODE);
        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("New Meter", "latitude"), Locator.Text.LATITUDE);
        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("New Meter", "longitude"), Locator.Text.LONGITUDE);
        SelectUtils.clickItemName(driver, Locator.inputWithNameOnPopupTitle("New Meter", "state"), Locator.Text.STATE);

        // save missing required fields
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("New Meter", "Save"));
        
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divTextContains(Locator.Text.ERROR_PLEASE_FIX_THE_VALIDATION_ERRORS));
        SeleniumUtils.assertFieldNameHasErrorMarking(driver, "meterNum");

        // fill in required fields
        String meterNumber = "meter" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("New Meter", "meterNum"), meterNumber);

        // save again
       // SeleniumUtils.clickUsingJavascript(driver, Locator.Meter.L_ADD_METER_SAVE_BTN);
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("New Meter", "Save"));

        SeleniumUtils.sleepSecs(3);
        // pop up should go away
        SeleniumUtils.waitForInVisible(driver, popup);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));


        // should have saved, and have a link to open the meter
        SeleniumUtils.waitForElement(driver, Locator.anchorText(meterNumber));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorText(meterNumber));
        
        savedMeterNumber = meterNumber;
    }
    
    
    @Test
    public void testSaveInactiveBillAccount() throws InterruptedException {
        // click add bill account
        SeleniumUtils.click(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_BTN);

        // verify the popup comes up
        By popup = Locator.divText("New Bill Account");
        SeleniumUtils.waitForElement(driver, popup);

        String inactiveBillAccountNumber = "bci" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_NUMBER, inactiveBillAccountNumber);

        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_NAME, Locator.Text.NAME);
        SelectUtils.clickItemName(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_ACTIVE_INDICATOR_FIELD, "Inactive");

        //      Select_List.clickFirstItemOfBoundList(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_REVENUE_CLASS);
        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_ACTIVATION_DATE, Locator.Text.DATE_FROM);
        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_TERMINATION_DATE, Locator.Text.DATE_TO);
        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_DESCRIPTION, Locator.Text.DESCRIPTION);
        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_ADDRESS1, Locator.Text.ADDRESS1);
        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_ADDRESS2, Locator.Text.ADDRESS2);
        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_CITY, Locator.Text.CITY);
        SelectUtils.clickItemName(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_STATE, Locator.Text.STATE);
        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_COUNTRY, Locator.Text.COUNTRY);
        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_ZIP, Locator.Text.ZIP_CODE);
        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_LATITUDE, Locator.Text.LATITUDE);
        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_LONGITUDE, Locator.Text.LONGITUDE);
        // save
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("New Bill Account", "Save"));

        // verify popup goes away
        SeleniumUtils.waitForInVisible(driver, popup);

        // check we have a saved link
        SeleniumUtils.waitForClickable(driver, By.linkText(inactiveBillAccountNumber));
    }

    public static String savedContactNumber = null;
    
    @Test
    public void testAddNewContact() {

        SeleniumUtils.click(driver, Locator.Contact.L_ADD_CONTACT_BTN);

        // verify the popup comes up
        By popup = Locator.divText("New Contact");
        SeleniumUtils.waitForElement(driver, popup);

        // fill in form
        String contactNumber = "c" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Contact.L_ADD_CONTACT_NUMBER, contactNumber);
        SeleniumUtils.setText(driver, Locator.Contact.L_ADD_CONTACT_TITLE, Locator.Text.STRING);
        SeleniumUtils.setText(driver, Locator.Contact.L_ADD_CONTACT_EMAIL, Locator.Text.EMAIL);
        SeleniumUtils.setText(driver, Locator.Contact.L_ADD_CONTACT_PHONE_COUNTRY_CODE, Locator.Text.PRIMARY_COUNTRY_CODE);
        SeleniumUtils.setText(driver, Locator.Contact.L_ADD_CONTACT_PHONE_AREA_CODE, Locator.Text.PRIMARY_AREA_CODE);
        SeleniumUtils.setText(driver, Locator.Contact.L_ADD_CONTACT_PHONE_PREFIX_CODE, Locator.Text.PRIMARY_PREFIX_CODE);
        SeleniumUtils.setText(driver, Locator.Contact.L_ADD_CONTACT_PHONE_SUFFIX_CODE, Locator.Text.PRIMARY_SUFFIX_CODE);
        SeleniumUtils.setText(driver, Locator.Contact.L_ADD_CONTACT_PHONE_EXT_CODE, Locator.Text.PRIMARY_EXT_CODE);
        SeleniumUtils.setText(driver, Locator.Contact.L_ADD_CONTACT_ADDRESS1, Locator.Text.ADDRESS1);
        SeleniumUtils.setText(driver, Locator.Contact.L_ADD_CONTACT_ADDRESS2, Locator.Text.ADDRESS2);
        SeleniumUtils.setText(driver, Locator.Contact.L_ADD_CONTACT_CITY, Locator.Text.CITY);
        SeleniumUtils.setText(driver, Locator.Contact.L_ADD_CONTACT_COUNTRY, Locator.Text.COUNTRY);
        SeleniumUtils.setText(driver, Locator.Contact.L_ADD_CONTACT_POSTAL_CODE, Locator.Text.ZIP_CODE);
        SeleniumUtils.setText(driver, Locator.Contact.L_ADD_CONTACT_COMMENTS, Locator.Text.DESCRIPTION);

        SelectUtils.clickItemName(driver, Locator.Contact.L_ADD_CONTACT_STATE, Locator.Text.STATE);

        // save
        SeleniumUtils.click(driver, Locator.Contact.L_ADD_CONTACT_SAVE_BTN);

        
        // verify errors for first name and last name
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divTextContains(Locator.Text.ERROR_PLEASE_FIX_THE_VALIDATION_ERRORS));
        SeleniumUtils.assertFieldIdHasErrorMarking(driver, "contactFirstName-inputEl");
        SeleniumUtils.assertFieldIdHasErrorMarking(driver, "contactLastName-inputEl");

        // fix errors and save again
        String firstName = Locator.Text.FIRST_NAME;
        String lastName = Locator.Text.LAST_NAME;
        SeleniumUtils.setText(driver, Locator.Contact.L_ADD_CONTACT_FIRST_NAME, firstName);
        SeleniumUtils.setText(driver, Locator.Contact.L_ADD_CONTACT_LAST_NAME, lastName);

        SeleniumUtils.click(driver, Locator.Contact.L_ADD_CONTACT_SAVE_BTN);

        SeleniumUtils.waitForInVisible(driver, popup);
        SeleniumUtils.waitForElement(driver, By.linkText(firstName), 30);

        // check first name is clickable (saved)
        SeleniumUtils.assertElementExistsWithWait(driver, By.linkText(firstName));
        
        savedContactNumber = contactNumber; 
    }
    
    
    @Test
    public void testProjectList() {
    	SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Projects"));

    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.anyText("Project List"), "The text 'Project List' should be shown as the title");
    	
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.anyText("Sort By:"), "The table should have a 'Sort By:' label");
    	
    	// check all the sorting headers
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorWithSpanText("Last Updated"));
        SeleniumUtils.click(driver, Locator.anchorWithSpanText("Last Updated"));
        SeleniumUtils.waitForLoading(driver);
        
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorWithSpanText("Created"));
        SeleniumUtils.click(driver, Locator.anchorWithSpanText("Created"));
        SeleniumUtils.waitForLoading(driver);
        
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorWithSpanText("Status"));
        SeleniumUtils.click(driver, Locator.anchorWithSpanText("Status"));
        SeleniumUtils.waitForLoading(driver);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorWithSpanText("Project"));
        SeleniumUtils.click(driver, Locator.anchorWithSpanText("Project"));
        SeleniumUtils.waitForLoading(driver);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorWithSpanText("Program"));
        SeleniumUtils.click(driver, Locator.anchorWithSpanText("Program"));
        SeleniumUtils.waitForLoading(driver);

        
        // test the new Project link (DSMC-11552)
        // for now, just check the box and links to very the feature is there
        // a lot of configuration is required to successfully create a project (program is public and customer is eligible)
        SeleniumUtils.click(driver, Locator.Customer.CREATE_PROJECT);
        
        String createProjectPopupTitle = "Select Program to Create Project";
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divTextContains(createProjectPopupTitle));
        
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anyTextWithinPopupTitle(createProjectPopupTitle, "Select Program"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anyTextWithinPopupTitle(createProjectPopupTitle, "Bill Account #:"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anyTextWithinPopupTitle(createProjectPopupTitle, "Site:"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anyTextWithinPopupTitle(createProjectPopupTitle, "Meter:"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anyTextWithinPopupTitle(createProjectPopupTitle, "Select Contact:"));
     
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle(createProjectPopupTitle, "OK"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle(createProjectPopupTitle, "Cancel"));
        
        // click cancel
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle(createProjectPopupTitle, "Cancel"));
        SeleniumUtils.assertElementInVisibleWithWait(driver, Locator.divTextContains(createProjectPopupTitle));
    }

    @Test
    public void testAddNote() {
    	
    	// click on Notes
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.traksmartButtonAnchorStyle("Notes"));
    	SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Notes"));

        // Sort Notes
        SeleniumUtils.sleepSecs(2);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorWithSpanText("Project"));
        SeleniumUtils.click(driver, Locator.anchorWithSpanText("Project"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorWithSpanText("Program"));
        SeleniumUtils.click(driver, Locator.anchorWithSpanText("Program"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorWithSpanText("Contact By"));
        SeleniumUtils.click(driver, Locator.anchorWithSpanText("Contact By"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorWithSpanText("Contact Date"));
        SeleniumUtils.click(driver, Locator.anchorWithSpanText("Contact Date"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorWithSpanText("Note Type"));
        SeleniumUtils.click(driver, Locator.anchorWithSpanText("Note Type"));
        SeleniumUtils.sleepSecs(2);
    	
    	// check elements on the page, should have sub headings for customer notes and site notes
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.h2Text("Customer"));
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.h2Text("Site"));
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.inputWithValue("+ Add Customer Note"));
    	
    	// check headings of customer notes table
    	SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("Note Type"));
    	SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("Contact Date"));
    	SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("Contact By"));
    	SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("Program"));
    	SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("Project"));
    	
    	
    	// add a note
    	SeleniumUtils.click(driver, Locator.inputWithValue("+ Add Customer Note"));

        // verify the popup comes up
    	String popupTitle = "Add Note";
        By popup = Locator.divText(popupTitle);
        SeleniumUtils.waitForElement(driver, popup);
        
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.inputWithNameOnPopupTitle(popupTitle, "contactDateString"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.inputWithNameOnPopupTitle(popupTitle, "contactedBy"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.inputWithNameOnPopupTitle(popupTitle, "noteTypeId"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.inputWithNameOnPopupTitle(popupTitle, "subject"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.textareaWithNameOnPopupTitle(popupTitle, "notes"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.inputWithNameOnPopupTitle(popupTitle, "programId"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.inputWithNameOnPopupTitle(popupTitle, "projectId"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.inputWithValueOnPopupTitle(popupTitle, "Save"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.inputWithValueOnPopupTitle(popupTitle, "Cancel"));

        String contactedBy = "cby" + System.currentTimeMillis();

        // fill in the form
        SeleniumUtils.setText(driver, Locator.Note.L_ADD_NOTE_CONTACTED_BY, contactedBy);

        String subject = "my subject " + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Note.L_ADD_NOTE_SUBJECT, subject);

        String desc = "my description " + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Note.L_ADD_NOTE, desc);

        
        // check savings without a date is an error
        SeleniumUtils.clear(driver, Locator.Note.L_ADD_DATE);
        SeleniumUtils.click(driver, Locator.Note.L_ADD_NOTE_SAVE_BTN);

        // check there is an error in saving
        SeleniumUtils.assertFieldNameHasErrorMarking(driver, "contactDateString");

        // fill in a correct date
        SeleniumUtils.setText(driver, Locator.Note.L_ADD_DATE, Locator.Text.DATE_FROM);

        // fill in the note type
        SelectUtils.clickItemName(driver, Locator.Note.L_ADD_NOTE_TYPE_OF_CONTACT, "Note");

        // save
        SeleniumUtils.click(driver, Locator.inputWithValueOnPopupTitle(popupTitle, "Save")); // save button

        // check popup goes away
        SeleniumUtils.waitForInVisible(driver, popup);

        // check note is saved
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.liTextContains(subject));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divTextContains(desc));
    }
    
    @Test
    public void testAddProjectNote() {
    	SeleniumUtils.click(driver, Locator.Note.L_ADD_CUTOMER_NOTE_HEADER);
        SeleniumUtils.click(driver, Locator.Note.L_ADD_NOTE_BTN);

        // verify the popup comes up
        By popup = Locator.divText("Add Note");
        SeleniumUtils.waitForElement(driver, popup);

        String subject = "test project note subject " + System.currentTimeMillis();
        String contactedBy = "contacted by " + System.currentTimeMillis();
        String note = "test note " + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Note.L_ADD_NOTE_CONTACTED_BY, contactedBy);
        SeleniumUtils.setText(driver, Locator.Note.L_ADD_NOTE_SUBJECT, subject);
        SeleniumUtils.setText(driver, Locator.Note.L_ADD_NOTE, note);

        // clear the date for an error 
        SeleniumUtils.clear(driver, Locator.Note.L_ADD_DATE);

        // save
        SeleniumUtils.clickUsingJavascript(driver, Locator.Note.L_ADD_NOTE_SAVE_BTN);

        // check errors are shown
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divTextContains(Locator.Text.ERROR_PLEASE_FIX_THE_VALIDATION_ERRORS));
        SeleniumUtils.assertFieldNameHasErrorMarking(driver, "contactDateString");

        
        // set correct date
        SeleniumUtils.setText(driver, Locator.Note.L_ADD_DATE, Locator.Text.DATE_FROM);

        // save
        SeleniumUtils.clickUsingJavascript(driver, Locator.Note.L_ADD_NOTE_SAVE_BTN);

        // check errors are shown
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divTextContains("Type of Contact is required"));
        
        // set type
        SelectUtils.clickItemName(driver, Locator.Note.L_ADD_NOTE_TYPE_OF_CONTACT, "Project");

        // save
        SeleniumUtils.clickUsingJavascript(driver, Locator.Note.L_ADD_NOTE_SAVE_BTN);

        // check successfull save
        SeleniumUtils.waitForInVisible(driver, popup);
        SeleniumUtils.waitForElement(driver, Locator.liTextContains(subject), 30);

        // all these should show up on the page
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.liTextContains(subject));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divTextContains(note));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.liTextContains(contactedBy));
    }

    @Test
    public void testAddNoteWithFollowUp() {

        SeleniumUtils.click(driver, Locator.Note.L_ADD_NOTE_BTN);

        // verify the popup comes up
        By popup = Locator.divText("Add Note");
        SeleniumUtils.waitForElement(driver, popup);

        SeleniumUtils.setText(driver, Locator.Note.L_ADD_NOTE_CONTACTED_BY, Locator.Text.FIRST_NAME);
        SelectUtils.clickItemName(driver, Locator.Note.L_ADD_NOTE_TYPE_OF_CONTACT, "Note");
        SeleniumUtils.setText(driver, Locator.Note.L_ADD_NOTE_SUBJECT, Locator.Text.STRING);
        SeleniumUtils.setText(driver, Locator.Note.L_ADD_NOTE, Locator.Text.DESCRIPTION);

        // follow up
        SeleniumUtils.click(driver, Locator.Note.L_ADD_NOTE_FOLLOW_UP);
        SeleniumUtils.setText(driver, Locator.Note.L_ADD_NOTE_EMAIL, Locator.Text.EMAIL);
        SeleniumUtils.setText(driver, Locator.Note.L_ADD_NOTE_EMAIL_TEXT, Locator.Text.DESCRIPTION);

        // follow up date has to be in the future, not just any random calendar selection (should make a test to ensure past dates are errors)
        SelectUtils.selectDateInTheFuture(driver, By.xpath("//div[@id='followUpDateString-trigger-picker']"), 4, 5); // //spanToday
        SeleniumUtils.clickUsingJavascript(driver, Locator.Note.L_ADD_NOTE_SAVE_BTN);

        // popup needs to go away
        SeleniumUtils.waitForInVisible(driver, popup);
    }

    
    @Test
    public void testSiteAddNote() {
    	
    	Assert.assertNotNull(savedSiteAddress, "This test depends on TestAddNewSite for a saved site");
    	
    	// click on Notes
    	SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Notes"));
    	
    	// check elements on the page, should have sub headings for customer notes and site notes
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.h2Text("Customer"));
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.h2Text("Site"));
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.inputWithValue("+ Add Customer Note"));

    	// switch to site notes
    	SeleniumUtils.click(driver, Locator.h2Text("Site"));
    	
    	// check elements on the page
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanText("Site Address"));
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanText("Subject"));
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanText("Notes"));
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanText("Updated By"));
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanText("Updated Date"));
    	
    	SeleniumUtils.sleepMillis(250); // small sleep since this button is displayed through javascript with a small delay
    	SeleniumUtils.waitForClickable(driver, Locator.inputWithValue("+ Add Site Note"));
    	
        SeleniumUtils.click(driver, Locator.Note.L_ADD_SITE_NOTE_BTN);

        // verify the popup comes up
        String popupTitle = "Site Notes";
        By popup = Locator.divText(popupTitle);
        SeleniumUtils.waitForElement(driver, popup);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.inputWithNameOnPopupTitle(popupTitle, "subject"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.textareaWithNameOnPopupTitle(popupTitle, "note"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.inputWithNameOnPopupTitle(popupTitle, "siteNumber"));
        SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle(popupTitle, "Close"));
        SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle(popupTitle, "Save"));

        String subject = "my subject " + System.currentTimeMillis();
        // fill in the form
        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("Site Notes", "subject"), subject);

        String desc = "my description " + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.textareaWithNameOnPopupTitle("Site Notes", "note"), desc);

        // fill in the note type
        SelectUtils.clickItemName(driver, Locator.inputWithNameOnPopupTitle("Site Notes", "siteNumber"), savedSiteAddress);

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("Site Notes", "Save")); // save button

        // check popup goes away
        SeleniumUtils.waitForInVisible(driver, popup);
        
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Success"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Notes Saved Successfully"));
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("OK"));  
        
        // verify the note is added
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText(subject));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.traksmartButtonAnchorStyle("Edit"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.traksmartButtonAnchorStyle("Delete"));
    }

    
	@Test
	/**
	 *
	 * UI Test for customer bill usage.
	 * We are not testing the data coz the data is uploaded.
	 * Only testing if the site created for the customer does exists and click on the link and show the meters and confirm if meter also exists...
	 *
	 * Unlike other customer test cases - we are starting from the scratch by searching that customer, loading details of the customer and then going to usage.
	 */
	public void testCustomerUsage() {

		Assert.assertNotNull(savedCompanyNumber, "this test depends on saved company number");
		Assert.assertNotNull(savedMeterNumber, "this test depends on saved meter");
		
		SeleniumUtils.goToTab(driver, Locator.L_TAB_CUSTOMERS,  "DSM Central: Customer Library");

		//search for the group
		ExtJsUtils.setExtComponentValue(driver, ExtJsUtils.ExtCmpType.combo, ExtJsUtils.ExtLocatorType.fieldLabel, "", "Customer Number");

		//wait for a second so that parameter is selected, otherwise the combo waits for store to load and then sets the value which will clear the search
		// item value.
		SeleniumUtils.sleepSecs(1);
		ExtJsUtils.setExtComponentValue(driver, ExtJsUtils.ExtCmpType.textfield, ExtJsUtils.ExtLocatorType.cls, "searchItemValue",
				savedCompanyNumber);

		SeleniumUtils.sleepSecs(1);
		WebElement searchButton = ExtJsUtils.getExtComponent(driver, ExtJsUtils.ExtCmpType.button, ExtJsUtils.ExtLocatorType.text, "Search");
		SeleniumUtils.sleepSecs(1);
		SeleniumUtils.clickUsingJavascript(driver, searchButton);

		//wait for search results
		SeleniumUtils.sleepSecs(1);
		SeleniumUtils.waitForElement(driver, Locator.anchorTextContains(savedCompanyNumber));

		//click settings link
		SeleniumUtils.clickUsingJavascript(driver, Locator.anchorTextContains(savedCompanyNumber));


		SeleniumUtils.assertElementExistsWithWait(driver, Locator.CustomerUsage.L_CUSTOMER_USAGE_LINK);

		SeleniumUtils.click(driver, Locator.CustomerUsage.L_CUSTOMER_USAGE_LINK);

		SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Meters"));

		SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText(Locator.Text.NAME));

		SeleniumUtils.click(driver, Locator.divText(Locator.Text.NAME));

		SeleniumUtils.assertElementExistsWithWait(driver, Locator.divTextContains(savedMeterNumber));

	}

}
