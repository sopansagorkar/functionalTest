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

/**
 * Created by Tim on 1/19/2015.
 */
public class TestParticipants {

    private static WebDriver driver;
    public static String campaignName = null;

    @BeforeClass
    public void setUp(){
        driver = Driver.get();
        campaignName = TestCreateCampaign.campaignName;
        if(campaignName == null) throw new RuntimeException("must have a saved campaign from TestCreateCampaign");
    }

    /**
     * Store the saved target list name
     */
    private String targetListName = null;
    
    @Test
    public void addNewTargetList() {

        TestCreateCampaign.openSavedCampaign(campaignName);

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Participants"));
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyle("Schedule Email"));//just to be sure page is loaded
        SeleniumUtils.sleepSecs(1);

        SeleniumUtils.clickSplitButton(driver, Locator.traksmartButtonAnchorStyle("Options"));

        //click on the add new target list button right above the dropdown
        SeleniumUtils.click(driver, By.linkText("Add New Target List"));

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Target List"));

        targetListName = "targetList" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, By.name("targetListName"), targetListName);

        By firstCheckbox = By.xpath("//div[contains(@class, 'targetListCls')][1]//table[1]/tbody/tr/td[1]");
        SeleniumUtils.click(driver, firstCheckbox);

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Include and Save"));

        SeleniumUtils.sleepMillis(100);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Saving Campaign"));
        SeleniumUtils.sleepMillis(500);

        SeleniumUtils.handleConfirmMsg(driver, "Success", "Campaign TargetList save succeeded.", "OK");

        WebElement tabTitle = SeleniumUtils.click(driver, Locator.spanTextContains("Included Members"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanText("Remove and Save")); //make sure on the right tab

        String title = tabTitle.getText();
        Assert.assertTrue(title != "Included Members (0)", "There should be at least one included member.");

        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.click(driver, By.xpath("//div[contains(@class, 'includedMembersGrid')][1]//span[text()[.='Close']]/../../.."));
    }

    @Test
    public void addParticipants(){

    	if(targetListName == null) throw new RuntimeException("must have added a target list in addNewTargetList");
    	
        // click Participants btn
        By by1 =  Locator.traksmartButtonAnchorStyle("Participants");
        SeleniumUtils.waitForElement(driver, by1);
        SeleniumUtils.click(driver, by1);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyle("Schedule Email"));//just to be sure page is loaded

        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.clickSplitButton(driver, Locator.traksmartButtonAnchorStyle("Options")); // click Options

        By by = By.xpath("//label/span[text()[contains(.,'Select Existing:')]]/../..//input[@type='text']");

        SeleniumUtils.sleepSecs(1);
        SelectUtils.clickItemOfDropDownWithTextInput(driver, by, targetListName);

        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));

        // the item should be saved into the target list
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.waitForClickable(driver, Locator.anchorText(targetListName));
    }

    @Test
    public void markAsALead(){

    	// select the participant
        SeleniumUtils.clickUsingJavascript(driver,  By.xpath("//div[@id='campaign-main-body-body']//table/tbody/tr[2]/td[1]//a"));

        // check page elements
        SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyle("Mark as Lead"));
        SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyle("Add Notes"));
        SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyle("Save"));
        SeleniumUtils.assertElementExists(driver, Locator.divText("Details"));
        SeleniumUtils.assertElementExists(driver, Locator.divText("Preferences"));
        SeleniumUtils.waitForAllInVisible(driver, Locator.divText("Loading..."));

        // click mark as lead
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Mark as Lead"));

        // make sure we are on popup
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Mark as lead")); 
        SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("Mark as lead", "Cancel"));
        
        SeleniumUtils.sleepSecs(1);

        SelectUtils.clickItemOfDropDownWithTextInput(driver, By.xpath("//input[@name='leadQualifierId']"), "Hot");

        SelectUtils.clickItemOfDropDownWithTextInput(driver, By.xpath("//input[@name='leadStatusId']"), "Pending");

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopup("Save"));

        SeleniumUtils.handleConfirmMsg(driver, "Success", "Marked As Lead", "OK");

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Save"));

        SeleniumUtils.handleConfirmMsg(driver, "Success", "Preferences Saved Successfully", "OK");

        SeleniumUtils.clickUsingJavascript(driver, By.partialLinkText(campaignName));

    }
}
