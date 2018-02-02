/**
 * 
 */
package com.nexant.dsm.publicinterface;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.common.pageObjects.login.Login;
import com.nexant.dsm.libraries.customers.process.TestNewCustomerAdd;
import com.nexant.dsm.libraries.serviceprovider.TestNewServiceProvider;
import com.nexant.webdriver.Driver;

/**
 * @author Rishi Shukla
 *
 */
@Test(groups={"Main"})
public class TestPublicInterfaceRegistration {
	
    private static WebDriver driver;

    public static String savedPICustomerNumber = null;
    public static String savedPICustomerFirstName = null;
    public static String savedPICustomerLastName = null;
    public static String savedPIBillAccountNumber = null;

    @BeforeClass
    public void getDriver(){
        driver = Driver.get();
    }

    @Test
    public void testAddNewPICustomerWithBillAccount() {
    	Assert.assertNotNull(TestNewServiceProvider.savedServiceProviderName, "this test required a saved service provider from TestNewServiceProvider");
    	
    	TestNewCustomerAdd.goToAddCustomer(driver);

        // fill in form
        // default is residential
        String piCustomerNumber = "cust" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Customer.L_CUSTOMER_NUMBER, piCustomerNumber);

        String piCustomerFirstName = "PIFname";
        SeleniumUtils.setText(driver, Locator.Customer.L_FIRST_NAME,  piCustomerFirstName);

        String piCustomerLastName = "LName" ;
        SeleniumUtils.setText(driver, Locator.Customer.L_LAST_NAME, piCustomerLastName);

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

        // verify we are on the saved customer page
        // Check the breadcrumb: Library -> Customers -> firstname, lastname
        SeleniumUtils.waitForClickable(driver, By.linkText(piCustomerLastName + ", " + piCustomerFirstName));

        // verify the customer title "firstname, lastname"
        SeleniumUtils.waitForElement(driver, Locator.labelTextContains(piCustomerLastName + ", " + piCustomerFirstName));
        // click add bill account
        SeleniumUtils.click(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_BTN);

        // verify the popup comes up
        By popup = Locator.divText("New Bill Account");
        SeleniumUtils.waitForElement(driver, popup);

        String piCustomerBillAccountNumber = "bc" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Bill_Account.L_ADD_BILL_ACCOUNT_NUMBER, piCustomerBillAccountNumber);

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
        SeleniumUtils.waitForClickable(driver, By.linkText(piCustomerBillAccountNumber));
        
        savedPICustomerNumber = piCustomerNumber;
        savedPICustomerFirstName = piCustomerFirstName;
        savedPICustomerLastName = piCustomerLastName;
        savedPIBillAccountNumber = piCustomerBillAccountNumber;
    }
    
    static String savedEmail = null;
    static String savedPassword = null;
    
    
    /**
     * Can pass 
     * 1. http://host:port/traksmart4/unprotected/login.do  (legacy)
     * 2. http://host:port/traksmart4/public/registration.do  (legacy)
     * 3. http://host:port/traksmart4/
     * 4. http://host:port/traksmart4
     * 5. http://host:port/
     * 6. http://host:port
     * 7. host:port
     * @param siteAddressParam
     * @return
     */
    private static String getPublicInterfaceRegistrationPage(String siteAddressParam){
    	
    	if(siteAddressParam == null) siteAddressParam = "http://localhost:8080";
    	
    	if(!siteAddressParam.startsWith("http")) siteAddressParam = "http://" + siteAddressParam;
    	
    	if(siteAddressParam.endsWith("/traksmart4/unprotected/login.do")){
    		return (siteAddressParam.replace("unprotected/login.do", "public/registration.do"));
    		
        }else if(siteAddressParam.endsWith("/traksmart4/public/registration.do")){
        	return siteAddressParam;
        	
        }else if(siteAddressParam.endsWith("/traksmart4/")){
        	return (siteAddressParam+"public/registration.do");
        	
        }else if(siteAddressParam.endsWith("/traksmart4")){
        	return (siteAddressParam+"/public/registration.do");

        }else if(siteAddressParam.endsWith("/")){
        	return (siteAddressParam+"/traksmart4/public/registration.do");

        }else {
        	return siteAddressParam + "/traksmart4/public/registration.do";
        }
    }

    @Parameters({"SiteAddress" ,"Login","Password"})
    public void testRegistration(String siteAddressParam, String loginParam, String passwordParam ) {
    	Assert.assertNotNull(savedPIBillAccountNumber, "this test requires a saved bill account from customer library");
    	Assert.assertNotNull(savedPICustomerLastName, "this test requires a saved last name customer library");
    	Assert.assertNotNull(TestNewServiceProvider.savedServiceProviderName, "this test requires a saved service provider from TestNewServiceProvider.");
    	
    	String piSiteAddress = getPublicInterfaceRegistrationPage(siteAddressParam);
        driver.get(piSiteAddress);
        String email ="mail"+System.currentTimeMillis()+"@xyzemail.com";
        String passwd = "Test.123.pi";
        
        SeleniumUtils.setText(driver, Locator.PICustomerRegistrationAndLogin.L_ACCOUNT_NUMBER, savedPIBillAccountNumber);
        SelectUtils.clickItemName(driver, Locator.PICustomerRegistrationAndLogin.L_SERVICE_PROVIDER, TestNewServiceProvider.savedServiceProviderName);
        SeleniumUtils.setText(driver, Locator.PICustomerRegistrationAndLogin.L_LAST_NAME, savedPICustomerLastName);
        SeleniumUtils.setText(driver, Locator.PICustomerRegistrationAndLogin.L_ZIP_CODE, Locator.Text.ZIP_CODE);
        SeleniumUtils.setText(driver, Locator.PICustomerRegistrationAndLogin.L_EMAIL, email);
        SeleniumUtils.setText(driver, Locator.PICustomerRegistrationAndLogin.L_PASSWORD, passwd);
        SeleniumUtils.setText(driver, Locator.PICustomerRegistrationAndLogin.L_CONFIRM_PASSWORD, passwd);
        SelectUtils.clickItemName(driver, Locator.inputWithName("securityId"), Locator.escapeTextValueOfXpath("What is your mother's maiden name?")); // use concat to escape the quote
        SeleniumUtils.setText(driver, Locator.inputWithName("securityAnswer"), "Test Mum");
        SeleniumUtils.click(driver, Locator.PICustomerRegistrationAndLogin.L_SIGN_UP_BTN);
        
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divTextContains("Your registration was successful! An activation email has been sent to the email address you provided."));
        savedEmail = email;
        savedPassword = passwd;
    }

    
    @Parameters({"SiteAddress" ,"Login","Password"})
    public void testActivateUser(String siteAddressParam, String loginParam, String passwordParam) {
    	Assert.assertNotNull(savedEmail, "this test requires a saved email from registration");
    	Assert.assertNotNull(savedPICustomerFirstName, "this test requires a saved first name from registration");
    	Assert.assertNotNull(savedPICustomerLastName, "this test requires a saved last name from registration");
    	
    	// go back to the internal login to active the user
    	Driver.get().get(Login.getInternalLoginPath(siteAddressParam));
        SeleniumUtils.setText(driver, Locator.Login.L_LOGIN, loginParam);
        SeleniumUtils.clearThenSetText(driver, Locator.Login.L_PASSWORD, passwordParam);
        SeleniumUtils.click(driver, Locator.Login.L_LOGIN_BTN);

        
        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_ADMIN, Locator.L_ADMIN_USER, "DSM Central: User Management");
        //  SeleniumUtils.goToTab(driver, Locator.L_TAB_USERS, "DSM Central: User Management");

        SeleniumUtils.setTextAndEnter(driver, By.id("keyword_search_textfield-inputEl"), savedEmail);
        SeleniumUtils.sleepMillis(250);
        String name = savedPICustomerLastName + ", " + savedPICustomerFirstName;
        SeleniumUtils.click(driver, Locator.anchorText(name));
        
        
        // check we have the save and cancel buttons
        SeleniumUtils.assertElementExistsWithWait(driver, By.id("editUser")); // make sure edit button is loaded.
        SeleniumUtils.waitForClickable(driver, Locator.inputWithValue("Edit"));
        SeleniumUtils.clickUsingJavascript(driver, By.id("editUser"));
        

        SeleniumUtils.waitForClickable(driver, Locator.inputWithValue("Save"));
        SeleniumUtils.waitForClickable(driver, Locator.inputWithValue("Cancel"));

        SelectUtils.clickFirstItemOfBoundList(driver, Locator.inputWithName("activeFlag-inputEl"));
        SeleniumUtils.clickUsingJavascript(driver, Locator.inputWithValue("Save"));
        SeleniumUtils.sleepSecs(5);
        
        // page will refresh, and edit button should show, and user should be active
        SeleniumUtils.waitForClickable(driver, Locator.inputWithValue("Edit"));
        SeleniumUtils.waitForElement(driver, Locator.anyTextWithinId("UserDetailSummary", "Active"));
        
        SeleniumUtils.click(driver, Locator.anchorText("Logout"));
        
    }
    
    @Parameters({"SiteAddress"})
    public void testPublicLogin(String siteAddressParam) {
    	Assert.assertNotNull(savedEmail, "this test requires a saved email from registration");

    	String piSiteAddress = getPublicInterfaceRegistrationPage(siteAddressParam);
    	driver.get(piSiteAddress);
    	SeleniumUtils.setText(driver, Locator.PICustomerRegistrationAndLogin.L_LOGIN_USERNAME_EMAIL, savedEmail);
    	SeleniumUtils.clearThenSetText(driver, Locator.PICustomerRegistrationAndLogin.L_LOGIN_USER_PWD, savedPassword);
    	SeleniumUtils.click(driver, Locator.inputWithValue("Login"));
    	
    	// check page title after login
    	SeleniumUtils.assertPageTitle(driver, "DSM Central: TrakSmart");
    	
    	// check the welcome message
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.anyContainsText("Welcome, " + savedEmail));
    	
    	// check the logout button
    	SeleniumUtils.waitForClickable(driver, Locator.anchorText("Logout"));
    	
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.anyContainsText("Eligible Rebates & Incentives"));
    	//SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyle("ELIGIBLE REBATES (0)"));
    	//SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyle("ELIGIBLE REBATES (0)"));
    	
    	
    }
}
