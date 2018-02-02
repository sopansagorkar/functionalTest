package com.nexant.dsm.libraries.partner.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = {"Manage Partners Elements"})
public class TestManagePartnersElements {

    public static String savedServiceType;
    public static String savedServiceTypeDescription;

    public static String savedSpecialtyName;
    public static String savedSpecialtyDescription;

    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        driver = Driver.get();
    }

    @Test
    public void testAddServiceType() {

        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_LIBRARIES, Locator.L_LIBRARIES_PARTNERS, "DSM Central: Partner Library");

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Partner.L_MANAGE_PARTNER_ELEMENTS_BTN);
        SeleniumUtils.waitForClickable(driver, Locator.Partner.L_MANAGE_PARTNER_ELEMENTS_BTN);
        SeleniumUtils.click(driver, Locator.Partner.L_MANAGE_PARTNER_ELEMENTS_BTN);

        // check page elements
        SeleniumUtils.sleepMillis(250);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        
        SeleniumUtils.waitForClickable(driver, Locator.anchorTextContains("Service Type"));
        SeleniumUtils.waitForClickable(driver, Locator.anchorTextContains("Specialties"));
        SeleniumUtils.waitForClickable(driver, Locator.spanText("Service Type"));
        SeleniumUtils.waitForClickable(driver, Locator.spanText("Description"));
        SeleniumUtils.waitForClickable(driver, Locator.spanText("Active Indicate"));
        SeleniumUtils.waitForClickable(driver, Locator.spanText("Updated Date"));
        SeleniumUtils.waitForClickable(driver, By.cssSelector("[value='+ Service Type']"));

        // click add service type
        SeleniumUtils.click(driver, By.cssSelector("[value='+ Service Type']"));

        // check popup
        By popup = Locator.divText("New Service Type");
        SeleniumUtils.waitForElement(driver, popup);

        // fill in form

        // drop down might cover the save button. do it first.
        SelectUtils.clickItemName(driver, By.xpath("//input[@componentid='activeFlag']"), "Active");

        savedServiceType = "stype" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, By.xpath("//input[@componentid='serviceType']"), savedServiceType);

        savedServiceTypeDescription = "This is description for service type created by automated script.";
        SeleniumUtils.setText(driver, By.xpath("//textarea[@componentid='description']"), savedServiceTypeDescription);


        // save
       // SeleniumUtils.click(driver, By.xpath("//a/span/span/span[text()[.='Save']]/.."));
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Save"));

        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));

        // check popup goes away
        SeleniumUtils.waitForInVisible(driver, popup);

        SeleniumUtils.assertPageTitle(driver, "DSM Central: Partner Elements");
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));

        // this is paged, sort to newest first
        By orderByDate = By.xpath("//div/span[text()[.='Updated Date']]");
        SeleniumUtils.click(driver, orderByDate);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.click(driver, orderByDate);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));

        // check it is saved as a link with the name
        SeleniumUtils.waitForClickable(driver, By.linkText(savedServiceType));

        // check the description is displayed
        SeleniumUtils.waitForElement(driver, Locator.divText(savedServiceTypeDescription));

        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='Active Indicate']]"));
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='Description']]"));
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='Service Type']]"));
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));

    }


    @Test
    public void testAddSpeciality() {
        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_LIBRARIES, Locator.L_LIBRARIES_PARTNERS, "DSM Central: Partner Library");

        SeleniumUtils.waitForClickable(driver, By.cssSelector("[value='+ Manage Partner Elements']"));
        SeleniumUtils.click(driver, By.cssSelector("[value='+ Manage Partner Elements']"));

        SeleniumUtils.assertPageTitle(driver, "DSM Central: Partner Elements");
        // click on the specialties link (the text of this link has whitespace, so we use text contains)
        SeleniumUtils.click(driver, By.xpath("//a[text()[contains(.,'Specialties')]]"));
        SeleniumUtils.sleepMillis(250);

        // click on add speciality
        SeleniumUtils.assertElementExistsWithWait(driver, By.cssSelector("[value='+ Specialty']"));

        // verify teh breadcrumb is updated
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorTextWithinDivId("title", "Specialties"));

        // click add specilty
        SeleniumUtils.click(driver, By.cssSelector("[value='+ Specialty']"));

        // check popup
        By popup = Locator.divText("New Specialty");
        SeleniumUtils.waitForElement(driver, popup);


        // fill in form
        savedSpecialtyName = "specialty" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, By.xpath("//input[@componentid='specialtyName']"), savedSpecialtyName);

        savedSpecialtyDescription = "Specialty description created by automated script.";
        SeleniumUtils.setText(driver, By.xpath("//textarea[@componentid='description']"), savedSpecialtyDescription);

        SelectUtils.clickItemName(driver, By.xpath("//input[@componentid='activeFlag']"), "Active");

        // save
        By save = By.xpath("//input[@value='Save']");
        SeleniumUtils.click(driver, save);

        // check popup goes away
        SeleniumUtils.waitForInVisible(driver, popup);


        // this is paged, sort to newest first
        By orderByDate = By.xpath("//span[text()[.='Last Updated']]");
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.click(driver, orderByDate);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.click(driver, orderByDate);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));

        // check it is saved as a link with the name
        SeleniumUtils.waitForClickable(driver, By.linkText(savedSpecialtyName));

        // check the description is displayed
        SeleniumUtils.waitForElement(driver, Locator.divText(savedSpecialtyDescription));

        SeleniumUtils.click(driver, By.xpath("//span[text()[.='Active Indicator']]"));
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.click(driver, By.xpath("//span[text()[.='Description']]"));
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.click(driver, By.xpath("//span[text()[.='Service Type']]"));
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));

    }
}
