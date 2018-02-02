package com.nexant.dsm.planning.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Calendar;

/**
 * Created by Tim on 9/16/2014.
 */
public class TestBudgeting {
    private WebDriver driver = null;

    @BeforeMethod
    public void setUp(){
        driver = Driver.get();
        
        if(TestCreateNewProgram.savedProgramName == null) throw new RuntimeException("this test requires a program set up in TestCreateNewProgram");
        
        TestCreateNewProgram.openSavedProgram(TestCreateNewProgram.savedProgramName);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Planning.PL_BUDGETING_LINK);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Planning.PL_BUDGETING_LINK);
        
        // check the page has the required elements
        SeleniumUtils.waitForElement(driver, Locator.divText("Budget Cost Reference Parameters"));
        SeleniumUtils.waitForElement(driver, Locator.divText("Budget Cost Categories"));
    }

    @Test
    public void testBudgetCostReference() {

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Maintain Sets"));

        //make sure we are on the manage budget cost reference
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.labelText("Planning Budget Cost Reference Set Maintenance"));

        // there is a loading box here that is hard to see since it is quite quick.
        // a test failed here due to element not clickable due to the ext mask
        SeleniumUtils.sleepMillis(250);
        SeleniumUtils.waitForAllInVisible(driver, Locator.anyText("Loading..."));
        
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Add New Set"));

        SeleniumUtils.waitForClickable(driver, Locator.inputWithName("name"));
        SeleniumUtils.waitForClickable(driver, Locator.inputWithName("hoursPerYear"));
        
        String name = "testBudgetCostRef";
        SeleniumUtils.setText(driver, Locator.inputWithName("name"), name);

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Save"));

        SeleniumUtils.handleConfirmMsg(driver, "Success", "Reference set saved successfully", "OK");

        // check we have saved it
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.waitForClickable(driver, Locator.divText(name));

        // click back to the program
        SeleniumUtils.click(driver, Locator.anchorText(TestCreateNewProgram.savedProgramName));
    }

    @Test
    public void editBudgeting(){

        //Edit Budget Cost Reference
        // click on the edit button
        SeleniumUtils.click(driver, By.xpath("//div[text()[.='Budget Cost Reference Parameters']]/../a[2]"));

        SeleniumUtils.sleepSecs(1);
        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, By.xpath("//*[@name='planningBudgetCostReferenceId']"));

        SeleniumUtils.sleepSecs(1);
        // edit on the save button
        SeleniumUtils.clickUsingJavascript(driver, By.xpath("//div[text()[.='Budget Cost Reference Parameters']]/../a[3]"));

        // Edit Budget Cost Categories
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanText("Add"));
        SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("Add"));

        SeleniumUtils.sleepSecs(3);

        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, By.xpath("//*[@name='budgetCategoryId']"));
        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, By.xpath("//*[@name='methodology']"));
        SeleniumUtils.clear(driver, By.xpath("//*[@name='value1']"));
        SeleniumUtils.setText(driver, By.xpath("//*[@name='value1']"), String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));

        SeleniumUtils.clickUsingJavascript(driver, By.linkText("Update"));


    }

}
