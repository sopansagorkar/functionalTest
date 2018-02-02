package com.nexant.dsm.planning.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.dsm.programs.process.TestAddNewProgram;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Calendar;

/**
 * Created by Tim on 10/15/2014.
 */
public class TestCreateProgramWithExisting {
    private static WebDriver driver;

    private String programName = null;
    
    @BeforeClass
    public void setUp(){
        driver = Driver.get();

        Assert.assertNotNull(TestAddNewProgram.savedProgramName, "program name should be not null, TestAddNewProgram must run first");
        programName = TestAddNewProgram.savedProgramName;
    }

    @Test
    public void createProgram(){

        SeleniumUtils.goToTab(driver, Locator.L_TAB_PLANNING, "DSM Central: All Programs");

        // click 'Create Program' btn
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Planning.PL_CREATE_PROG_BTN);
        SeleniumUtils.click(driver, Locator.Planning.PL_CREATE_PROG_BTN);

        // select Existing program
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Planning.PL_CHOOSE_EXISTING);

        //SelectUtils.enterTextToSelect(driver, Locator.Planning.PL_CHOOSE_EXISTING, programName);

        //this is to get around the ext bug that you cannot press space while typing in the box.
        WebElement found = SelectUtils.findBoundList(driver, Locator.Planning.PL_CHOOSE_EXISTING);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Searching..."));
        String id = found.getAttribute("id");
        By itemBy = By.xpath("//*[@id='" + id + "']/ul/li/div/div/span[text()[.='" + programName + "']]");
        WebElement theItem = SeleniumUtils.waitForClickable(driver, itemBy);
        theItem.click();

        int year = Calendar.getInstance().get(Calendar.YEAR);
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

        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.assertElementExistsOrCloseErrorMsg(driver, Locator.divText(programName));


        TestCreateNewProgram.openSavedProgram(programName);
    }
}
