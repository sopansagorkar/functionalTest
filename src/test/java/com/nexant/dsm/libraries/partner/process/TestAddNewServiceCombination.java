package com.nexant.dsm.libraries.partner.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = {"Partner Combination"})
public class TestAddNewServiceCombination {

	private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        driver = Driver.get();
    }

    @Test
    public void testAddNewServiceCombination() {

        // we should have a previously saved service_type
        String savedServiceType = TestManagePartnersElements.savedServiceType;
        Assert.assertNotNull(savedServiceType, "we should have a previously saved service type");

        String savedServiceSpecialty = TestManagePartnersElements.savedSpecialtyName;
        Assert.assertNotNull(savedServiceSpecialty, "we should have a previously saved specialty");

        By button = By.cssSelector("[value='+Add Service Combination']");
        SeleniumUtils.click(driver,  button);

        // check we got the popup
        By popupLocator = Locator.divText("Add New Service Combination");
        SeleniumUtils.waitForVisible(driver, popupLocator);

        // these 4 drop downs are loaded in the background
        SeleniumUtils.sleepSecs(2); // for now, put a delay, since clicking the drop downs immediately does not work
        SelectUtils.clickItemOfDropDownWithTextInput(driver, Locator.Partner.serviceTypes, savedServiceType);
        SelectUtils.clickItemOfDropDownWithTextInput(driver, Locator.Partner.serviceTypeSpecialties, savedServiceSpecialty);
        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, Locator.Partner.sector);
        SelectUtils.clickItemOfDropDownWithTextInput(driver, Locator.Partner.state_comb, "TX");

        SeleniumUtils.click(driver, Locator.Partner.saveNote);

        // check the popup is gone
        SeleniumUtils.waitForInVisible(driver, popupLocator);

        // check we saved 
        SeleniumUtils.waitForClickable(driver, By.linkText(savedServiceType));

        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/span[text()[.='Service Type']]"));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='Service Type']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/span[text()[.='Specialty']]"));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='Specialty']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/span[text()[.='Sector']]"));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='Sector']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/span[text()[.='State']]"));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='State']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/span[text()[.='Last Update']]"));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='Last Update']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/span[text()[.='By']]"));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='By']]"));

    }
}
