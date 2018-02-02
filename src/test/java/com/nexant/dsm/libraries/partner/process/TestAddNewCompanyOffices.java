package com.nexant.dsm.libraries.partner.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = {"Partner Office"})
public class TestAddNewCompanyOffices {

    public static String savedOfficeName;

    private static WebDriver driver;


    @BeforeClass
    public static void setup() {
        driver = Driver.get();
    }

    @Test
    public void testAddOffice() {

        By addCompanyOfficesButton = By.cssSelector("[value= '+Add Company Offices']");
        SeleniumUtils.click(driver, addCompanyOfficesButton);

        // assert we get the popup
        By popup = Locator.divText("Add New Company Office");
        SeleniumUtils.assertElementExistsWithWait(driver, popup);

        savedOfficeName = "office" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Partner.officeName, savedOfficeName);

        SeleniumUtils.setText(driver, Locator.Partner.addressLine1, Locator.Text.ADDRESS1);
        SeleniumUtils.setText(driver, Locator.Partner.addressLine2, Locator.Text.ADDRESS2);
        SeleniumUtils.setText(driver, Locator.Partner.city, Locator.Text.CITY);
        SeleniumUtils.setText(driver, Locator.Partner.postalCode, Locator.Text.ZIP_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.country, Locator.Text.COUNTRY);
        SeleniumUtils.setText(driver, Locator.Partner.description, Locator.Text.DESCRIPTION);
        SelectUtils.clickItemName(driver, Locator.Partner.CO_STATE, Locator.Text.STATE);

        SeleniumUtils.setText(driver, Locator.Partner.phoneCountryCode, Locator.Text.PRIMARY_COUNTRY_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.phoneAreaCode, Locator.Text.PRIMARY_AREA_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.phonePrefix, Locator.Text.PRIMARY_PREFIX_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.phoneSuffix, Locator.Text.PRIMARY_SUFFIX_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.phoneExtension, Locator.Text.PRIMARY_EXT_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.faxCountryCode, Locator.Text.ALT_COUNTRY_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.faxAreaCode, Locator.Text.ALT_AREA_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.faxPrefix, Locator.Text.ALT_PREFIX_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.faxSuffix, Locator.Text.ALT_SUFFIX_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.faxExtension, Locator.Text.ALT_EXT_CODE);

        SeleniumUtils.click(driver, Locator.Partner.L_SAVE_COMPANY_OFFICE);

        // check the popup goes away
        SeleniumUtils.waitForInVisible(driver, popup);

        // check we have added the office
        SeleniumUtils.waitForClickable(driver, By.linkText(savedOfficeName));

        // Sort Offices
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/span[text()[.='Office Name']]"));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='Office Name']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/span[text()[.='Address']]"));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='Address']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/span[text()[.='City']]"));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='City']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/span[text()[.='State']]"));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='State']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/span[text()[.='Zip Code']]"));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='Zip Code']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/span[text()[.='Phone Number']]"));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='Phone Number']]"));

    }
}
