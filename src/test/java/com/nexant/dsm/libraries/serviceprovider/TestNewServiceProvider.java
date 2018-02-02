package com.nexant.dsm.libraries.serviceprovider;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by VKamenev on 5/30/2014.
 */
@Test
public class TestNewServiceProvider {

    private WebDriver driver = null;

    public static String savedServiceProviderNumber;
    public static String savedServiceProviderName;

    @BeforeClass
    public void setup() {
        driver = Driver.get();
    }

    @Test
    public void testAddNewServiceProvider() {

        SeleniumUtils.waitForElement(driver, Locator.L_TAB_LIBRARIES, 30);
    	// click libraries -> Service Providers
        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_LIBRARIES, Locator.L_LIBRARIES_SERVICE_PROVIDERS, "DSM Central: Service Provider");

        //ClickTHRU Sort Bys
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.ServiceProvider.LSP_SORT_BY_LAST_UPDATED);
        SeleniumUtils.click(driver, Locator.ServiceProvider.LSP_SORT_BY_LAST_UPDATED);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.ServiceProvider.LSP_SORT_BY_ACTIVE);
        SeleniumUtils.click(driver, Locator.ServiceProvider.LSP_SORT_BY_ACTIVE);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.ServiceProvider.LSP_SORT_BY_STATE);
        SeleniumUtils.click(driver, Locator.ServiceProvider.LSP_SORT_BY_STATE);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.ServiceProvider.LSP_SORT_BY_CITY);
        SeleniumUtils.click(driver, Locator.ServiceProvider.LSP_SORT_BY_CITY);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.ServiceProvider.LSP_SORT_BY_NAME);
        SeleniumUtils.click(driver, Locator.ServiceProvider.LSP_SORT_BY_NAME);

        // click add service provider
        SeleniumUtils.click(driver, Locator.ServiceProvider.L_ADD_SERVICE_PROVIDER);

        // fill in form
        // default is residential
        savedServiceProviderNumber = "spnum" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_SERVICE_PROVIDER_NUMBER, savedServiceProviderNumber);

        savedServiceProviderName = "spname" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_SERVICE_PROVIDER_NAME, savedServiceProviderName);

        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_ADDRESS_1, Locator.Text.ADDRESS1);
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_ADDRESS_2, Locator.Text.ADDRESS2);
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_CITY, Locator.Text.CITY);
        SeleniumUtils.assertElementExists(driver, Locator.ServiceProvider.L_STATE);
        SeleniumUtils.click(driver, Locator.ServiceProvider.L_STATE);
        SeleniumUtils.click(driver, Locator.ServiceProvider.L_STATE1);
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_COUNTRY, Locator.Text.COUNTRY);
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_BRANDKEY, Locator.Text.L_SERVPROV_BRANDKEY);
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_DISTRICT_NUMBER, Locator.Text.L_SERVPROV_DISTRNUM);
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_DESCRIPTION, Locator.Text.DESCRIPTION);
        SelectUtils.clickItemOfDropDownWithTextInput(driver, By.id("activeFlag-inputEl"), "Active");

        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_PHONE_COUNTRY, Locator.Text.PRIMARY_COUNTRY_CODE);
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_PHONE_AREA, Locator.Text.PRIMARY_AREA_CODE);
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_PHONE_PREFIX, Locator.Text.PRIMARY_PREFIX_CODE);
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_PHONE_SUFFIX, Locator.Text.PRIMARY_SUFFIX_CODE);
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_PHONE_EXT, Locator.Text.PRIMARY_EXT_CODE);
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_URL, Locator.Text.L_SERVPROV_URL);
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_ZIP, Locator.Text.ZIP_CODE);
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_FAX_COUNTRY, Locator.Text.ALT_COUNTRY_CODE);
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_FAX_AREA, Locator.Text.ALT_AREA_CODE);
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_FAX_PREFIX, Locator.Text.ALT_PREFIX_CODE);
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_FAX_SUFFIX, Locator.Text.ALT_SUFFIX_CODE);

        // save
        SeleniumUtils.clickUsingJavascript(driver, Locator.ServiceProvider.L_SAVE_BTN);


        SeleniumUtils.handleConfirmMsg(driver, "Success", "Service Provider saved successfully", "OK");

        // verify we have the saved service provider
        // Check the breadcrumb: Library -> Customers -> savedServiceProviderName
        SeleniumUtils.waitForClickable(driver, By.linkText(savedServiceProviderName));

        // verify have expected buttons for edit and add new contact
        SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyle("Edit"));
        SeleniumUtils.waitForClickable(driver, By.xpath("//input[@type='button' and @value='+ Add New Contact']"));

        
    }
    @Test
    public void addNewContact()
    {
    	// Click on Add New Contact
        SeleniumUtils.click(driver, Locator.ServiceProvider.L_SERVPROV_ADCN);
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_SERVPROV_ADCN_CONTACT_NUMB, Locator.Text.L_CONTACT_NUMBER);
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_SERVPROV_ADCN_FIRSTNM, Locator.Text.FIRST_NAME);
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_SERVPROV_ADCN_LASTNM, Locator.Text.LAST_NAME);
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_SERVPROV_ADCN_EMAIL, Locator.Text.EMAIL);
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_SERVPROV_ADCN_ADDR1, Locator.Text.ADDRESS1);
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_SERVPROV_ADCN_ADDR2, Locator.Text.ADDRESS2);
        SeleniumUtils.setText(driver, Locator.ServiceProvider.L_SERVPROV_ADCN_CITY, Locator.Text.CITY);
        SelectUtils.clickFirstItemOfBoundList(driver, By.id("state_contact-inputEl"));
        SeleniumUtils.setText(driver, By.id("country_contact-inputEl"), Locator.Text.COUNTRY);
        SeleniumUtils.setText(driver, By.id("postalCode_contact-inputEl"), Locator.Text.ZIP_CODE);
        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, By.id("primaryContact-inputEl"));

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("Add New Contact", "Save"));
        
        SeleniumUtils.handleConfirmMsg(driver, "Success", "Contact saved Successfully.", "OK");

        // Sort Contacts
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/div/span[text()[.='First Name']]"));
        SeleniumUtils.click(driver, By.xpath("//div/div/span[text()[.='First Name']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/div/span[text()[.='Last Name']]"));
        SeleniumUtils.click(driver, By.xpath("//div/div/span[text()[.='Last Name']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/div/span[text()[.='Email']]"));
        SeleniumUtils.click(driver, By.xpath("//div/div/span[text()[.='Email']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/div/span[text()[.='Primary']]"));
        SeleniumUtils.click(driver, By.xpath("//div/div/span[text()[.='Primary']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/div/span[text()[.='Last Update']]"));
        SeleniumUtils.click(driver, By.xpath("//div/div/span[text()[.='Last Update']]"));

    }    

}
