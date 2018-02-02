package com.nexant.dsm.planning.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.dsm.libraries.measures.process.TestCreateCategoryTypeSubType;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Calendar;

/**
 * Created by Tim on 9/9/2014.
 */
public class TestCreateNewProgram {
    private static WebDriver driver;

    public static String savedProgramName = null;

    @BeforeClass
    public void setUp(){
        driver = Driver.get();
    }

    public static void openSavedProgram() {

        openSavedProgram(savedProgramName);
    }

    public static void openSavedProgram(String programName){

        driver = Driver.get();
        SeleniumUtils.goToTab(driver, Locator.L_TAB_PLANNING, "DSM Central: All Programs");

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Planning.PL_CREATE_PROG_BTN);

        SeleniumUtils.click(driver, Locator.Planning.PL_SORT_LAST_UPDATED);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Planning.PL_TYPE_DD);
        SelectUtils.clickFirstItemOfBoundList(driver, Locator.Planning.PL_TYPE_DD);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Planning.PL_KEYWORD_TXT);
        Assert.assertNotNull(programName, "program name cannot be null");
        SeleniumUtils.setText(driver,  Locator.Planning.PL_KEYWORD_TXT, programName);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Planning.PL_SEARCH_BTN);

        for(int i=0; i< 5; i++) {
            SeleniumUtils.click(driver, Locator.Planning.PL_SEARCH_BTN);
            SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
            WebElement el = SeleniumUtils.waitForClickableWithoutException(driver, Locator.anchorText(programName));
            if (el != null) {
                break;
            } else {
                SeleniumUtils.sleepSecs(3);
            }
        }

        SeleniumUtils.click(driver, Locator.anchorText(programName));

        SeleniumUtils.assertPageTitle(driver, "DSM Central: Planning Program Details", 30);
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//label[text()[contains(.,'" + programName + "')]]"));
    }

    @Test
    public void createNewProgram(){

        SeleniumUtils.goToTab(driver, Locator.L_TAB_PLANNING, "DSM Central: All Programs");
        // Verify that '+ Create Program' exists
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Planning.PL_CREATE_PROG_BTN);
        // click '+ Create Program' btn
        SeleniumUtils.clickUsingJavascript(driver, Locator.Planning.PL_CREATE_PROG_BTN);
        // Verify that '+ Create Program' btn was clicked and we are on the next page
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Planning Program Details");
        // Fill Out the form
        String programName = "PR" + System.currentTimeMillis();
        // set 'Prog Name'
        SeleniumUtils.setText(driver, Locator.Planning.PL_NAME_FIELD, programName);
        // set 'Description'
        SeleniumUtils.setText(driver, Locator.Planning.PL_DESCRIPTION_FIELD, Locator.Text.DESCRIPTION);
        // set 'Type'
        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, Locator.Planning.PL_TYPE_DD_FIELD);
        // set 'Sector'
        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, Locator.Planning.PL_SECTORS_DD_FIELD);
		//set service type
        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, Locator.Planning.PL_SERVICE_TYPE_DD_FIELD);

        // above populates the second drop down, so wait a little bit for the javascript to finish
        SeleniumUtils.sleepMillis(500);
        
        
        int year = Calendar.getInstance().get(Calendar.YEAR);

        // set 'Default Sector'
        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, Locator.Planning.PL_DEFAULT_SECTOR_FIELD);
        // set 'PL Start Year'
        SeleniumUtils.setText(driver, Locator.Planning.PL_START_YEAR, String.valueOf(year));
        // set 'PL End Year'
        SeleniumUtils.setText(driver, Locator.Planning.PL_END_YEAR, String.valueOf(year + 6));
        // set 'Prog Start Year'
        SeleniumUtils.setText(driver, Locator.Planning.PL_PROG_START_YEAR, String.valueOf(year));
        // set 'Prog End Year'
        SeleniumUtils.setText(driver, Locator.Planning.PL_PROG_END_YEAR, String.valueOf(year + 6));

        SeleniumUtils.assertElementExists(driver, Locator.Planning.PL_SAVE_PROG_BTN);

        SeleniumUtils.click(driver, Locator.Planning.PL_SAVE_PROG_BTN);

        SeleniumUtils.assertElementExistsOrCloseErrorMsg(driver, Locator.divText(programName));

        savedProgramName = programName;
    }

    @Test
    public void addParameters(){

    	if(savedProgramName == null) throw new RuntimeException("a planning program must first be saved");
    	
        openSavedProgram(savedProgramName);

        SeleniumUtils.assertPageTitle(driver, "DSM Central: Planning Program Details");

        
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.traksmartButtonAnchorStyle("Overview"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.traksmartButtonAnchorStyle("Input"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.traksmartButtonAnchorStyle("Budgeting"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.traksmartButtonAnchorStyle("Analyze"));

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Cost Effectiveness Parameters"));

        
        WebElement costEffectiveParamsDiv = SeleniumUtils.waitForElement(driver, By.xpath("//div[text()[.='Cost Effectiveness Parameters']]/../../../.."));
        SeleniumUtils.assertElementIsClickable(driver, Locator.traksmartButtonAnchorStyleOnPopupId(costEffectiveParamsDiv.getAttribute("id"), "Edit"));

        // click 'Edit' on cost effectiveness params (there are two edit buttons on the page, so we need to pick the cost effectiveness parameters button)
        SeleniumUtils.clickUsingJavascript(driver, Locator.traksmartButtonAnchorStyleOnPopupId(costEffectiveParamsDiv.getAttribute("id"), "Edit"));

        // This page eagerly retrieves the data. Need to wait a bit for bound list to get created.
        SeleniumUtils.sleepSecs(4);

        // set 'Marginal Cost'
        SeleniumUtils.waitForClickable(driver, Locator.Planning.PL_MARGINAL_COST);
        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, Locator.Planning.PL_MARGINAL_COST);
        // set 'Marginal Rate'
        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, Locator.Planning.PL_MARGINAL_RATE);
        // set 'System Input'
        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, Locator.Planning.PL_SYSTEMS_INPUT);
        // set 'Customer Forecast' (nothing in this drop down?)
        //SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, Locator.Planning.PL_CUSTOMERS_FORECAST);

        // set 'Marginal Cost'
        SeleniumUtils.waitForClickable(driver, Locator.Planning.PL_GAS_MARGINAL_COST);
        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, Locator.Planning.PL_GAS_MARGINAL_COST);
        // set 'Marginal Rate'
        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, Locator.Planning.PL_GAS_MARGINAL_RATE);
        // set 'System Input'
        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, Locator.Planning.PL_GAS_SYSTEMS_INPUT);
        // set 'Customer Forecast' (nothing in this drop down?)
        //SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, Locator.Planning.PL_GAS_CUSTOMERS_FORECAST);
        
        
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopupId(costEffectiveParamsDiv.getAttribute("id"), "Save"));
        
        SeleniumUtils.sleepMillis(500);
        SeleniumUtils.waitForAllInVisible(driver, Locator.divText("Saving Details..... Please wait"));

        SeleniumUtils.sleepMillis(500);
        
        // just check the edit button is back (in the future we should refactor this to select an exact cost, input and rate, so we can verify things
        SeleniumUtils.assertElementIsClickable(driver, Locator.traksmartButtonAnchorStyleOnPopupId(costEffectiveParamsDiv.getAttribute("id"), "Edit"));

    }
    
    @Test
    public void editMeasures(){
        if(savedProgramName == null) throw new RuntimeException("a program must be created using TestCreateNewProgram first");
        if(TestCreateCategoryTypeSubType.savedCategoryName == null) throw new RuntimeException("a measure category must be created first using TestCreateCategoryTypeSubType");
        if(TestCreateCategoryTypeSubType.savedCategoryTypeName == null) throw new RuntimeException("a measure category type must be created first using TestCreateCategoryTypeSubType");
        if(TestCreateCategoryTypeSubType.savedSubTypeName == null) throw new RuntimeException("a measure category subtype must be created first using TestCreateCategoryTypeSubType");

        openSavedProgram();

        SeleniumUtils.waitForClickable(driver, By.xpath("//div[text()[.='Measures']]/../a[1]")); // click on edit measures button
        SeleniumUtils.click(driver, By.xpath("//div[text()[.='Measures']]/../a[1]"));

        SeleniumUtils.assertPageTitle(driver, "DSM Central: Planning Program Details");

        SeleniumUtils.waitForElement(driver, Locator.anchorText("Select Measures"));

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divContainsClass("x-form-arrow-trigger-default"));
        SeleniumUtils.click(driver, Locator.divContainsClass("x-form-arrow-trigger-default"));

        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.waitOnProgressBar(driver, 120);
        SeleniumUtils.sleepSecs(2);

        SeleniumUtils.moveMouseWithWait(driver, Locator.spanTextContains(TestCreateCategoryTypeSubType.savedCategoryName));
        SeleniumUtils.moveMouseWithWait(driver, Locator.spanTextContains(TestCreateCategoryTypeSubType.savedCategoryName));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.moveThenClick(driver, Locator.spanTextContains(TestCreateCategoryTypeSubType.savedCategoryTypeName), Locator.spanTextContains(TestCreateCategoryTypeSubType.savedSubTypeName));

        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.scrollToElement(driver, Locator.spanTextContains("Search All"));

        // click search All
        SeleniumUtils.clickUsingJavascript(driver, Locator.spanTextContains("Search All"));
        SeleniumUtils.sleepSecs(2);

        // check a box on line 1
        SeleniumUtils.click(driver, By.xpath("//div[contains(@class, 'locked_grid_with_checkbox')][1]//table[1]/tbody/tr/td[1]"));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.dragAndDrop(driver, By.xpath("//div/span[text()[contains(.,'Type')]]"), By.cssSelector("#dropPromptText"));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("Include Selected"));


        // pop up should appear
        SeleniumUtils.handleConfirmMsg(driver, "Status", "Measures added to program successfully.", "OK");

        SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("Done Measure Editing"));

        // make sure we are back on the planning tab
        SeleniumUtils.waitForElement(driver, By.cssSelector("#planningTitle")); // label with program name content
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorText(savedProgramName));
    }

}
