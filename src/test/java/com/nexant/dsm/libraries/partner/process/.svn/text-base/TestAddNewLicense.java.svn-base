package com.nexant.dsm.libraries.partner.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = {"Partner License"})
public class TestAddNewLicense {

    public static String savedLicenseName;
    public static String savedInactiveLicenseName;

    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        driver = Driver.get();
    }

    @Test
    public void testAddNewLicense() {

        By addLicenseButton = By.cssSelector("[value= '+Add License']");
        SeleniumUtils.click(driver, addLicenseButton);

        // assert the popup shows
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Add New License"));

        // fill in the form
        savedLicenseName = "lic" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Partner.licenseName, savedLicenseName);
        SelectUtils.clickItemName(driver, Locator.Partner.activeindicator, "Active");

        // save
        SeleniumUtils.click(driver, Locator.Partner.saveLicenseButton);

        SeleniumUtils.waitForInVisible(driver,  Locator.divText("Add New License"));

        SeleniumUtils.assertElementExistsWithWait(driver, By.linkText(savedLicenseName));
    }

    @Test
    public void testAddInactiveLicense() {

        By addLicenseButton = By.cssSelector("[value= '+Add License']");
        SeleniumUtils.click(driver, addLicenseButton);

        // assert the popup shows
        SeleniumUtils.assertElementExistsWithWait(driver,  Locator.divText("Add New License"));

        savedInactiveLicenseName = "licinactive" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Partner.licenseName, savedInactiveLicenseName);
        SelectUtils.clickItemName(driver, Locator.Partner.activeindicator, "Inactive");

        // save
        SeleniumUtils.click(driver, Locator.Partner.saveLicenseButton);

        SeleniumUtils.waitForInVisible(driver,  Locator.divText("Add New License"));

        SeleniumUtils.assertElementExistsWithWait(driver, By.linkText(savedInactiveLicenseName));

        // Sort Licenses
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/span[text()[.='Active Indicator']]"));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='Active Indicator']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/span[text()[.='License']]"));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='License']]"));

    }
}
