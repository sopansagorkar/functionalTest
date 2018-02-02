package com.nexant.dsm.marketing.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tim on 10/9/2014.
 */
public class TestCreateCampaign {

    private static WebDriver driver;
    public static String campaignName = null;
    private static String todaysDate = null;

    @BeforeClass
    public void setUp(){
        driver = Driver.get();
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        todaysDate = formatter.format(date);
    }


    public static void openSavedCampaign(String campaignName){

        driver = Driver.get();
        SeleniumUtils.goToTab(driver, Locator.L_TAB_MARKETING, "DSM Central: Campaign");

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Marketing.MR_CAMPAIGNS_TAB);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Marketing.MR_CAMPAIGNS_TAB);

        Assert.assertNotNull(campaignName, "program name cannot be null");

        SelectUtils.clickFirstItemOfBoundList(driver, By.xpath("//div[contains(@class, 'campaignSearchContainer')]//input[@role='combobox']"));
        SeleniumUtils.setText(driver,  By.xpath("//div[contains(@class, 'campaignSearchContainer')]//input[@role='textbox']"), campaignName);
        SeleniumUtils.sleepMillis(500);

        for(int i=0; i< 5; i++) {
            SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Search"));
            SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
            WebElement el = SeleniumUtils.waitForElementWithoutException(driver, By.linkText(campaignName));
            if (el != null) {
                break;
            } else {
                SeleniumUtils.sleepSecs(3);
            }
        }

        SeleniumUtils.clickUsingJavascript(driver, By.linkText(campaignName));

        SeleniumUtils.waitForClickable(driver, Locator.anchorText(campaignName)); // make sure we are on the new page.
        SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyle("Participants"));
        SeleniumUtils.sleepSecs(1);
    }

    @Test
    public void createCampaign(){

        SeleniumUtils.goToTab(driver, Locator.L_TAB_MARKETING, "DSM Central: Campaign");

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Marketing.MR_CAMPAIGNS_TAB);
        SeleniumUtils.click(driver, Locator.Marketing.MR_CAMPAIGNS_TAB);


        SeleniumUtils.waitForClickable(driver, Locator.Marketing.MR_SHOW_ADDED_BY_YOU);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Marketing.MR_SHOW_ADDED_BY_YOU);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.clickUsingJavascript(driver, Locator.Marketing.MR_SHOW_COMPLETED);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.clickUsingJavascript(driver, Locator.Marketing.MR_SHOW_IN_PROGRESS);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.clickUsingJavascript(driver, Locator.Marketing.MR_SHOW_ALL);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.clickUsingJavascript(driver, Locator.Marketing.ED);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.clickUsingJavascript(driver, Locator.Marketing.SD);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.clickUsingJavascript(driver, Locator.Marketing.Type);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.clickUsingJavascript(driver, Locator.Marketing.Name);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.clickUsingJavascript(driver, Locator.Marketing.LUD);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));


        SeleniumUtils.sleepSecs(1);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Marketing.MR_CREATE_CAMPAIGN_BTN);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Marketing.MR_CREATE_CAMPAIGN_BTN);

        //make sure popup is loaded
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Create Campaign"));

        campaignName = "MR" + System.currentTimeMillis();

        // set 'Name'
        SeleniumUtils.setText(driver, Locator.Marketing.MR_NAME_TXT, campaignName);
        // set 'Objective'
        SeleniumUtils.setText(driver, Locator.Marketing.MR_OBJECTIVE, "Test Objective");
        // set 'Description'
        SeleniumUtils.setText(driver, Locator.Marketing.MR_DESCRIPTION, "Test Description");
        // set 'Start Date'
        SeleniumUtils.setText(driver, Locator.Marketing.MR_START_DATE, todaysDate);

        SeleniumUtils.sleepSecs(1);

        // set 'Campaign Manager'
        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, Locator.Marketing.MR_CAMPAIGN_MANAGER);

        // set 'Related Programs'
        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, Locator.Marketing.MR_RELATED_PROGS);
        // set 'Support Staff'
        //    Select_List.clickFirstItemOfDropDownWithTextInput(driver, Locator.Marketing.MR_SUPPORT_STAFF);
        // click 'Save button'
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanText("Save"));
        SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("Save"));

        SeleniumUtils.handleConfirmMsg(driver, "Success", "Campaign Created", "OK");

    }


    @Test
    public void addActivity(){


        openSavedCampaign(campaignName);

        // click 'Add' button
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Marketing.MR_ADD_BTN);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Marketing.MR_ADD_BTN);
        // check if 'Add Activity' pop up appeared
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Add Activity"));
        //   SeleniumUtils.assertElementInnerHtmlStartsWithWithWait(driver, Locator.Marketing.MR_ACTIVITY_HEADER, "Add Activity");
        // set 'Title'
        SeleniumUtils.setText(driver, Locator.Marketing.MR_TITLE, campaignName);
        // set 'Status'
        SelectUtils.clickItemName(driver, Locator.Marketing.MR_STATUS, "Pending");
        // set 'Description'
        SeleniumUtils.setText(driver, Locator.Marketing.MR_DESCRIPTION_2, Locator.Text.DESCRIPTION);
        // set 'Priority'
        SelectUtils.clickItemName(driver, Locator.Marketing.MR_PRIORITY, "High");
        // set 'Need By Date'
        SelectUtils.selectDateInTheFuture(driver, Locator.Marketing.MR_NEED_BY_DATE, 2, 3);
        // set 'Percent Complete'
        SeleniumUtils.setText(driver, Locator.Marketing.MR_PR_COMPLETE, "100");
        // set 'Assigned To'
        //    Select_List.clickItemName(driver, Locator.Marketing.MR_ASSIGNED_TO);
        // set 'Notes'
        SeleniumUtils.setText(driver, Locator.Marketing.MR_NOTES, campaignName + " automation");
        // click 'Save'
        SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("Save"));
        SeleniumUtils.handleConfirmMsg(driver, "Success", "Activity Saved", "OK");
    }

    @Test
    public void addEmail(){

        SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("Emails"));
        
        // check we have the expected elements on the page
        SeleniumUtils.waitForClickable(driver, Locator.spanText("Email Templates")); // this is the sortable heading
        SeleniumUtils.waitForClickable(driver, Locator.spanText("Date")); // this is the sortable heading

        SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("+ Add"));

        String emailName = "Email" + System.currentTimeMillis();

        SeleniumUtils.setText(driver, Locator.Marketing.MR_EMAIL_NAME_TXT, emailName);

        SeleniumUtils.setText(driver, Locator.Marketing.MR_SUBJECT_TXT, "Automation");

        SeleniumUtils.setText(driver, Locator.Marketing.MR_BODY_AREA, "Automation Test");

        SeleniumUtils.doubleClick(driver, Locator.Marketing.MR_NAME_TOKEN);

        SeleniumUtils.doubleClick(driver, Locator.Marketing.MR_NAME_TOKEN);

        SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("Save"));

        SeleniumUtils.handleConfirmMsg(driver, "Success", "Email Template Saved", "OK");

        // it should be saved into the list of emails
        SeleniumUtils.waitForClickable(driver, Locator.anchorText(emailName));
        
    }
}
