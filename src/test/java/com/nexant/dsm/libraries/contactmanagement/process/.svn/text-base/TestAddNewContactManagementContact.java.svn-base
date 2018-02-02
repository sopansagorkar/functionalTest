package com.nexant.dsm.libraries.contactmanagement.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups={"AddContact"})
public class TestAddNewContactManagementContact {

    private static WebDriver driver;

    // saved
    public static String str_customer_first_name;
    public static String str_customer_last_name;


    @BeforeClass
    public void setup() {
        driver = Driver.get();
    }

    @Test public void testAddNewContact() {

        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_LIBRARIES, Locator.L_LIBRARIES_CONTACT_MANAGEMENT, "DSM Central: Contact Management");

        SeleniumUtils.waitForElement(driver, Locator.ContactManagement.L_ADD_CONTACT_BTN);
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Contact Management");
        SeleniumUtils.sleepSecs(10);
        // Contacts
        SeleniumUtils.waitForClickable(driver, By.xpath("//a/span[text()[.='Last Updated']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Last Updated']]"));
        SeleniumUtils.waitForClickable(driver, By.xpath("//a/span[text()[.='Company Name']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Company Name']]"));
        SeleniumUtils.waitForClickable(driver, By.xpath("//a/span[text()[.='Email']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Email']]"));
        SeleniumUtils.waitForClickable(driver, By.xpath("//a/span[text()[.='Last Name']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Last Name']]"));

        SeleniumUtils.click(driver, Locator.ContactManagement.L_ADD_CONTACT_BTN);

        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//label[text()[contains(., 'New Contact')]]"));

        SeleniumUtils.setText(driver, Locator.ContactManagement.L_SET_CONTACT_COMPANY_NAME, Locator.Text.COMPANY_NAME);
    	SeleniumUtils.setText(driver, Locator.ContactManagement.L_ADD_CONTACT_TITLE, Locator.Text.STRING);
    	SeleniumUtils.setText(driver, Locator.ContactManagement.L_ADD_CONTACT_EMAIL, Locator.Text.EMAIL);
    	SeleniumUtils.setText(driver, Locator.ContactManagement.L_ADD_CONTACT_SECONDARY_EMAIL, Locator.Text.EMAIL);
    	SeleniumUtils.setText(driver, Locator.ContactManagement.L_ADD_CONTACT_PHONE_COUNTRY_CODE, Locator.Text.ALT_COUNTRY_CODE);
    	SeleniumUtils.setText(driver, Locator.ContactManagement.L_ADD_CONTACT_PHONE_AREA_CODE, Locator.Text.ALT_AREA_CODE); 
    	SeleniumUtils.setText(driver, Locator.ContactManagement.L_ADD_CONTACT_PHONE_PREFIX_CODE, Locator.Text.ALT_PREFIX_CODE);
    	SeleniumUtils.setText(driver, Locator.ContactManagement.L_ADD_CONTACT_PHONE_SUFFIX_CODE, Locator.Text.ALT_SUFFIX_CODE);
    	SeleniumUtils.setText(driver, Locator.ContactManagement.L_ADD_CONTACT_PHONE_EXT_CODE, Locator.Text.ALT_EXT_CODE);
    	SeleniumUtils.setText(driver, Locator.ContactManagement.L_ADD_CONTACT_ADDRESS1, Locator.Text.ADDRESS1);
    	SeleniumUtils.setText(driver, Locator.ContactManagement.L_ADD_CONTACT_ADDRESS2, Locator.Text.ADDRESS2);
    	SeleniumUtils.setText(driver, Locator.ContactManagement.L_ADD_CONTACT_CITY, Locator.Text.CITY); 
    	SeleniumUtils.setText(driver, Locator.ContactManagement.L_ADD_CONTACT_COUNTRY, Locator.Text.COUNTRY);
    	SeleniumUtils.setText(driver, Locator.ContactManagement.L_ADD_CONTACT_POSTAL_CODE, Locator.Text.ZIP_CODE); 
        SelectUtils.clickItemName(driver, Locator.ContactManagement.L_ADD_CONTACT_STATE, Locator.Text.STATE);

        
        SeleniumUtils.clickUsingJavascript(driver, Locator.ContactManagement.L_ADD_CONTACT_SAVE_BTN);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divTextContains(Locator.Text.ERROR_PLEASE_FIX_THE_VALIDATION_ERRORS));
        SeleniumUtils.assertFieldNameHasErrorMarking(driver, "firstName");
        SeleniumUtils.assertFieldNameHasErrorMarking(driver, "lastName");

        
        str_customer_first_name = "fn" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.ContactManagement.L_ADD_CONTACT_FIRST_NAME, str_customer_first_name);

        str_customer_last_name = "ln" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.ContactManagement.L_ADD_CONTACT_LAST_NAME, str_customer_last_name);

        SeleniumUtils.clickUsingJavascript(driver, Locator.ContactManagement.L_ADD_CONTACT_SAVE_BTN);
        
        // verify the save
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//label[text()[contains(.,'" + str_customer_last_name + ", " + str_customer_first_name + "')]]"));
    }
}