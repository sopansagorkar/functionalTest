package com.nexant.dsm.libraries.contactmanagement.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by tshakirov on 10/23/15.
 */
public class TestContactManagementReminder {

    private static WebDriver driver = null;

    @BeforeClass
    public void setup() {
        driver = Driver.get();
    }

    @Test
    public void testSortReminder() {

        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_LIBRARIES, Locator.L_LIBRARIES_CONTACT_MANAGEMENT, "DSM Central: Contact Management");

        SeleniumUtils.waitForClickable(driver, Locator.ContactManagement.L_CONTACT_M_REMINDER);
        SeleniumUtils.click(driver, Locator.ContactManagement.L_CONTACT_M_REMINDER);

        SeleniumUtils.assertPageTitle(driver, "DSM Central: Contact Management");

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.ContactManagement.REMINDER_SORT_BY_REMINDER_DATE);
        SeleniumUtils.click(driver, Locator.ContactManagement.REMINDER_SORT_BY_REMINDER_DATE);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.ContactManagement.REMINDER_SORT_BY_NAME);
        SeleniumUtils.click(driver, Locator.ContactManagement.REMINDER_SORT_BY_NAME);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.ContactManagement.REMINDER_SORT_BY_ENTERED_BY);
        SeleniumUtils.click(driver, Locator.ContactManagement.REMINDER_SORT_BY_ENTERED_BY);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.ContactManagement.REMINDER_SORT_BY_DATE_ENTERED);
        SeleniumUtils.click(driver, Locator.ContactManagement.REMINDER_SORT_BY_DATE_ENTERED);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.ContactManagement.REMINDER_SORT_BY_EMAIL);
        SeleniumUtils.click(driver, Locator.ContactManagement.REMINDER_SORT_BY_EMAIL);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.ContactManagement.REMINDER_SORT_BY_STATUS);
        SeleniumUtils.click(driver, Locator.ContactManagement.REMINDER_SORT_BY_STATUS);
    }

    @Test
    public void testDeleteReminder(){}

}
