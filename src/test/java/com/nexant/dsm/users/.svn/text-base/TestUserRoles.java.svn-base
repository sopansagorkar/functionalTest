package com.nexant.dsm.users;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.dsm.libraries.customers.process.TestNewCustomerAdd;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by aabdollahi on 3/23/2015.
 */
@Test
public class TestUserRoles {

    private static WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = Driver.get();
    }

    @Test
    public void checkUserRoles() {
    	 SeleniumUtils.goToSubTab(driver, Locator.L_TAB_ADMIN, Locator.L_ADMIN_USER, "DSM Central: User Management");
      //  SeleniumUtils.goToTab(driver, Locator.L_TAB_USERS, "DSM Central: User Management");

        SeleniumUtils.setTextAndEnter(driver, By.id("keyword_search_textfield-inputEl"), "nexantsupport");
        SeleniumUtils.sleepMillis(250);
        SeleniumUtils.waitForAllInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.sleepMillis(250);

        SeleniumUtils.click(driver, Locator.anchorText("Support, Nexant"));

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.h2Text("User Management"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.id("editUser")); // make sure edit button is loaded.

        SeleniumUtils.sleepMillis(250);
        SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("User Roles"));
        SeleniumUtils.sleepMillis(250);
        SeleniumUtils.waitForAllInVisible(driver, Locator.divText("Loading..."));

        WebElement campaignManager = SeleniumUtils.waitForElementWithoutException(driver, By.xpath("//div[@id='selectedRolesList']//ul/li[text()[.= 'Campaign Manager']]"));
        if (campaignManager == null) {
            SeleniumUtils.click(driver, By.xpath("//div[@id='rolesList']//ul/li[text()[.= 'Campaign Manager']]"));
            SeleniumUtils.click(driver, By.id("saveAssignedRoles"));// click on the save button

            SeleniumUtils.waitForElement(driver, Locator.divText("Success"), 30); //wait for the confirm box to show up.

            SeleniumUtils.handleConfirmMsg(driver, "Success" , "Role save succeeded.", "OK");
        }

    }
    
}
