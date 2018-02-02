package com.nexant.dsm.admin;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.common.pageObjects.login.Login;
import com.nexant.dsm.Suit;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by aabdollahi on 2/27/2015.
 */
public class TestRoles {

    private static WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = Driver.get();
    }

    @Test
    public void checkRoleEntitlements(){
    	// SeleniumUtils.click(driver, Locator.L_TAB_ADMIN, 30);
    	 SeleniumUtils.goToSubTab(driver, Locator.L_TAB_ADMIN, Locator.L_ADMIN_SYSTEM, "DSM Central: Administration");

        SeleniumUtils.doubleClick(driver, Locator.spanText("Security"));
        SeleniumUtils.sleepSecs(1);

        SeleniumUtils.click(driver, Locator.spanText("Role Entitlements"));
        
        // wait for the add button to show up
        SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyle("Add"));
        
        // wait for the loading to go away
        SeleniumUtils.waitForAllInVisible(driver, Locator.divText("Loading..."));

        SeleniumUtils.sleepSecs(2);
        
        // click on 100 page size
        SeleniumUtils.click(driver, Locator.anchorText("100"));
        SeleniumUtils.sleepMillis(250);
        SeleniumUtils.waitForAllInVisible(driver, Locator.divText("Loading..."));
        
        //search for administrator roles
        SelectUtils.clickItemOfDropDownWithTextInput(driver, By.id("admin_attributes_search_combobox-inputEl"), "Role Name");

        By searchTextBox = By.xpath("//div[@id='admin_attributes_search_combobox']/../div[2]//input");
        SeleniumUtils.setText(driver, searchTextBox, "Administrator");

        SeleniumUtils.click(driver, Locator.divContainsClass("x-form-search-trigger-default"));

        //before this search all options are available on the page. It is important that we make sure the search is done and then check
        SeleniumUtils.waitForAllInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.sleepSecs(1);

        WebElement schedulingRW = SeleniumUtils.waitForElementWithoutException(driver, Locator.divText("SCHEDULING_READ_WRITE"));
        if (schedulingRW == null) {
            addEntitlementToRole("Administrator", "SCHEDULING_READ_WRITE");
        }

        WebElement planningRW = SeleniumUtils.waitForElementWithoutException(driver, Locator.divText("PLANNING_READ_WRITE"));
        if (planningRW == null) {
            addEntitlementToRole("Administrator", "PLANNING_READ_WRITE");
        }

        WebElement assessmentsRW = SeleniumUtils.waitForElementWithoutException(driver, Locator.divText("ASSESSMENT_READ_WRITE"));
        if (assessmentsRW == null) {
            addEntitlementToRole("Administrator", "ASSESSMENT_READ_WRITE");
        }

        WebElement campaignRW = SeleniumUtils.waitForElementWithoutException(driver, Locator.divText("CAMPAIGN_MODULE_READ_WRITE"));
        if (campaignRW == null) {
            addEntitlementToRole("Administrator", "CAMPAIGN_MODULE_READ_WRITE");
        }

        // needed for payments
        WebElement paymentModuleRW = SeleniumUtils.waitForElementWithoutException(driver, Locator.divText("PAYMENT_MODULE_READ_WRITE"));
        if (paymentModuleRW == null) {
            addEntitlementToRole("Administrator", "PAYMENT_MODULE_READ_WRITE");
        }

        if (schedulingRW == null || planningRW == null || assessmentsRW == null || campaignRW == null || paymentModuleRW == null) {
            //re-login for tabs to show up.

            SeleniumUtils.click(driver, Locator.anchorText("Logout"));
            SeleniumUtils.assertPageTitle(driver, "DSM Central: Login");
            Login.login(driver, Suit.siteAddress, Suit.login, Suit.password);

        }

        SeleniumUtils.sleepSecs(1);
    }

    private void addEntitlementToRole(String role, String entitlement) {
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Add")); // click on the Add button
        SeleniumUtils.waitForElement(driver, Locator.divText("Add/Edit")); // make sure popup appears

        SeleniumUtils.sleepSecs(1);
        SelectUtils.clickItemOfDropDownWithTextInput(driver, By.xpath("//input[@name='name']"), role);
        SelectUtils.clickItemOfDropDownWithTextInput(driver, By.xpath("//input[@name='entitlement']"), entitlement);
        SeleniumUtils.sleepSecs(1);

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Create"));
        
        SeleniumUtils.handleConfirmMsg(driver, "Success", "Record Saved Successfully", "OK");
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText(entitlement));

    }
}
