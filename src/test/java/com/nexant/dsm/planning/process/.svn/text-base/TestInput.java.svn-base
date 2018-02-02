package com.nexant.dsm.planning.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Tim on 9/15/2014.
 */
public class TestInput {

    private WebDriver driver = null;
    private String programName = null;

    @BeforeClass
    public void setUp(){
        driver = Driver.get();
        programName = TestCreateNewProgram.savedProgramName;
    }

    @Test
    public void selectInput(){

        TestCreateNewProgram.openSavedProgram();

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Planning.PL_INPUT_LINK);

        SeleniumUtils.clickUsingJavascript(driver, Locator.Planning.PL_INPUT_LINK);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
    }

    @Test
    public void addDataToEligibilityTable(){
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Planning.PL_ELIGIBILITY_BTH);

        SeleniumUtils.clickUsingJavascript(driver, Locator.Planning.PL_ELIGIBILITY_BTH);

        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));

        SeleniumUtils.sleepSecs(2);
        SeleniumUtils.waitForElement(driver, Locator.Planning.PL_ELIGIBILITY_ROW);
        SeleniumUtils.doubleClick(driver, Locator.Planning.PL_ELIGIBILITY_ROW);


        SelectUtils.clickFirstItemOfBoundList(driver, Locator.Planning.PL_SECTOR_DD);
        SelectUtils.clickFirstItemOfBoundList(driver, Locator.Planning.PL_FACILITY_TYPE_DD);
        SelectUtils.clickFirstItemOfBoundList(driver, Locator.Planning.PL_CONSTRUCTION_TYPE_DD);
        SelectUtils.clickFirstItemOfBoundList(driver, Locator.Planning.PL_EQUIPMENT_DD);

        SeleniumUtils.waitForClickable(driver, Locator.spanText("Update"));

        SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("Update"));
    }

    @Test
    public void addDataToParticipationTable(){
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Planning.PL_PARTICIPATION_BTH);

        SeleniumUtils.clickUsingJavascript(driver, Locator.Planning.PL_PARTICIPATION_BTH);

        // this next panel slides in, if we click too early we get a StateElementReferenceException 
        SeleniumUtils.sleepSecs(3);
        SeleniumUtils.doubleClick(driver, Locator.Planning.PL_ELIGIBILITY_ROW);

        SeleniumUtils.setText(driver, Locator.Planning.PL_START_TXT, "5");
        SeleniumUtils.setText(driver, Locator.Planning.PL_MAX_TXT, "5");
        SeleniumUtils.setText(driver, Locator.Planning.PL_INCREMENT_TXT, "5");
        SelectUtils.clickFirstItemOfBoundList(driver, Locator.Planning.PL_UNIT_DD);
        SeleniumUtils.setText(driver, Locator.Planning.PL_NO_YEARS_TXT, "5");
        SeleniumUtils.setText(driver, Locator.Planning.PL_QTY_CUSTOMERS_TXT, "5");

        SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("Update"));

    }

    @Test
    public void addDataToCostTable(){
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Planning.PL_COST_BTH);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Planning.PL_COST_BTH);

        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.doubleClick(driver, Locator.Planning.PL_ELIGIBILITY_ROW);

        SelectUtils.clickFirstItemOfBoundList(driver, Locator.Planning.PL_CAP_TYPE);
        SeleniumUtils.setText(driver, By.xpath("//input[@name='cCapValue']"), "4");

        SeleniumUtils.waitForClickable(driver, Locator.spanText("Update"));
        SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("Update"));

    }

}
