package com.nexant.dsm.marketing.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.dsm.libraries.customers.process.TestNewCustomerAdd;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Tim on 1/19/2015.
 */
public class TestLeads
{
    private static WebDriver driver;
    private String campaignName;
    private String companyName; 

    @BeforeClass
    public void setUp(){
        driver = Driver.get();
        
        campaignName = TestCreateCampaign.campaignName;
        companyName = TestNewCustomerAdd.savedCompanyName;
        
        if(campaignName == null) throw new RuntimeException("requires that a campaign was added in TestCreateCampaign");
        if(companyName == null) throw new RuntimeException("requires that a company was added in TestNewCustomerAdd");
    }

    @Test
    public void addOpportunity(){

        TestCreateCampaign.openSavedCampaign(campaignName);

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Leads"));
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));

        
        // check the elements on the page - there should be 4 filters 
        SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("All"));
        SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("Added by You"));
        SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("In Progress"));
        SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("Completed"));

        // check the elements on the page - there should be 3 pagination options 
        SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("20"));
        SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("50"));
        SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("100"));

        // there should be one lead, with the saved company name
        SeleniumUtils.waitForClickable(driver, Locator.anchorText(companyName));
        SeleniumUtils.waitForClickable(driver, Locator.anchorText("Delete"));
        
        
        // now click on the lead
        SeleniumUtils.click(driver, By.xpath("//div[@id='campaign-main-body-body']//table[1]//td[1]//a")); // pick the first row



        //Make sure page is loaded
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.traksmartButtonAnchorStyle("Opportunities"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.traksmartButtonAnchorStyle("Activities"));


        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Add Opportunity")); // click on the button Add Opportunity


        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Add Opportunity")); // make sure on popup

        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, Locator.Marketing.MR_PROGRAM_DD);

        SelectUtils.clickItemOfDropDownWithTextInput(driver, Locator.Marketing.MR_STATUS_DD_OPP, "Pending");

        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, Locator.Marketing.MR_ASSIGNED_TO_OPP);

        SeleniumUtils.setText(driver, Locator.Marketing.MR_COMMENTS, Locator.Text.DESCRIPTION);

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Save"));


        SeleniumUtils.handleConfirmMsg(driver, "Success", "Opportunity save succeeded.", "OK");
        SeleniumUtils.sleepSecs(1);
    }

    @Test
    public void addNotesForLeads(){

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Add Notes for Leads")); // click on the button "Add Notes for Lead"

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Notes"));

        SeleniumUtils.setText(driver, Locator.Marketing.MR_CONTACTED_BY_NOTES, Locator.Text.CONTACTEDBY);

        SeleniumUtils.setText(driver, Locator.Marketing.MR_SUBJECT_NOTES, Locator.Text.L_SUBJECT);

        SeleniumUtils.setText(driver, Locator.Marketing.MR_NOTES_NOTES, Locator.Text.L_NOTE);

        SeleniumUtils.click(driver, Locator.Marketing.MR_F_UP_CHECK);

        SeleniumUtils.setText(driver, Locator.Marketing.MR_EMAIL_NOTES, Locator.Text.EMAIL);

        SeleniumUtils.setText(driver, Locator.Marketing.MR_TEXT_NOTES, Locator.Text.L_EMAIL_TEXT);

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Save"));

    }

}
