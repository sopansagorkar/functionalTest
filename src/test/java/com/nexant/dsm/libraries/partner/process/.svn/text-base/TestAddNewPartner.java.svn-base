package com.nexant.dsm.libraries.partner.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = {"New Partner"})
public class TestAddNewPartner {

    public static String savedPartnerCompanyName;
    public static String savedPartnerRemittenceNumber;
    public static String savedPartnerNumber;

    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        driver = Driver.get();
    }

    @Test
    public void testAddNewPartner() {
        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_LIBRARIES, Locator.L_LIBRARIES_PARTNERS, "DSM Central: Partner Library");

        // Show
        SeleniumUtils.waitForClickable(driver, By.xpath("//a[text()[.='100']]"));
        SeleniumUtils.click(driver, By.xpath("//a[text()[.='100']]"));
        SeleniumUtils.waitForClickable(driver, By.xpath("//a[text()[.='50']]"));
        SeleniumUtils.click(driver, By.xpath("//a[text()[.='50']]"));
        SeleniumUtils.waitForClickable(driver, By.xpath("//a[text()[.='20']]"));
        SeleniumUtils.click(driver, By.xpath("//a[text()[.='20']]"));

        // Sort Partners
        SeleniumUtils.waitForClickable(driver, By.xpath("//a/span[text()[.='Last Updated']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Last Updated']]"));
        SeleniumUtils.waitForClickable(driver, By.xpath("//a/span[text()[.='Active']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Active']]"));
        SeleniumUtils.waitForClickable(driver, By.xpath("//a/span[text()[.='City']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='City']]"));
        SeleniumUtils.waitForClickable(driver, By.xpath("//a/span[text()[.='Name']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Name']]"));

        // click add
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Partner.L_ADD_PARTNER_BTN);

        SeleniumUtils.click(driver, Locator.Partner.L_ADD_PARTNER_BTN);
        SeleniumUtils.waitForElement(driver, Locator.Partner.L_PARTNER_ID);

        SeleniumUtils.assertPageTitle(driver, "DSM Central: Partner Details");


        // fill in the form
        savedPartnerCompanyName = "partnerco" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Partner.L_PARTNER_ID, savedPartnerCompanyName);
        SeleniumUtils.setText(driver, Locator.Partner.L_ADDRESS_1, Locator.Text.ADDRESS1);
        SeleniumUtils.setText(driver, Locator.Partner.L_ADDRESS_2, Locator.Text.ADDRESS2);
        SeleniumUtils.setText(driver, Locator.Partner.L_CITY, Locator.Text.CITY);
        SeleniumUtils.setText(driver, Locator.Partner.L_ZIP, Locator.Text.ZIP_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.L_COUNTRY, Locator.Text.COUNTRY);
        SeleniumUtils.setText(driver, Locator.Partner.L_DESCRIPTION, Locator.Text.DESCRIPTION);
        SeleniumUtils.setText(driver, Locator.Partner.L_WEBSITE, Locator.Text.WEBSITE);

        SeleniumUtils.setText(driver, Locator.Partner.L_phoneCountryCode, Locator.Text.PRIMARY_COUNTRY_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.L_Add_Phone_Area, Locator.Text.PRIMARY_AREA_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.L_Add_Phone_Prefix, Locator.Text.PRIMARY_PREFIX_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.L_Add_Phone_Suffix, Locator.Text.PRIMARY_SUFFIX_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.L_Add_Phone_Ext, Locator.Text.PRIMARY_EXT_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.L_faxCountryCode, Locator.Text.ALT_COUNTRY_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.L_Add_fax_Area, Locator.Text.ALT_AREA_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.L_Add_fax_Prefix, Locator.Text.ALT_PREFIX_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.L_Add_fax_Suffix, Locator.Text.ALT_SUFFIX_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.L_Add_fax_Ext, Locator.Text.ALT_EXT_CODE);

        savedPartnerRemittenceNumber = "remit" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Partner.remittanceNumber, savedPartnerRemittenceNumber);

        savedPartnerNumber = "pn" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Partner.partnerNumber, savedPartnerNumber);

        SelectUtils.enterTextToSelect(driver, Locator.Partner.STATUS, "Active");

        SelectUtils.enterTextToSelect(driver, Locator.Partner.L_STATE, "CA");

        // save
        SeleniumUtils.click(driver, Locator.Partner.savePartner);

        // check the partner is added
        // the partner company is in the breadcrump as a link
        SeleniumUtils.assertElementExistsWithWait(driver, By.linkText(savedPartnerCompanyName));

        // the partner company name is displayed 
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanText(savedPartnerCompanyName));

        // the partner number is also displayed 
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanText(savedPartnerNumber));

        // the remittence nubmer is also displayed
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanText(savedPartnerRemittenceNumber));
    }
}
