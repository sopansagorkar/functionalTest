package com.nexant.dsm.programs.process;

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
 * Created with IntelliJ IDEA.
 * User: VKamenev
 * Date: 8/29/13
 * Time: 9:34 AM
 * To change this template use File | Settings | File Templates.
 */
@Test(groups = {"CreateNewProgram"})
public class TestAddNewProgram {

    public static String savedProgramName;

    private static WebDriver driver = Driver.get();

    /**
     * A helper method to open the program saved by this test
     */
    public static void openSavedProgram() {
        openSavedProgram(savedProgramName);
    }

    /**
     * A helper method to open a program.
     * It navigates to the programs page, searching for the program, and opens the program summary page.
     * @param programName
     */
    public static void openSavedProgram(String programName){
        Assert.assertNotNull(programName, "program name cannot be null");

        SeleniumUtils.goToTab(driver, Locator.L_TAB_PROGRAMS, "DSM Central: All Programs");
        //Search for the newly created Program

        // wait for the page to load
        SeleniumUtils.waitForElement(driver, Locator.Programs.L_SEARCH);
        SeleniumUtils.sleepSecs(1); // it takes a while for the initial loading box to show up
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."), 60); // wait for the main list to be loaded before searching
        SeleniumUtils.sleepMillis(200);

        // enter the program name and search
        for(int i=0; i< 5; i++) {
            SeleniumUtils.clearThenSetTextAndEnter(driver, Locator.Programs.L_SEARCH, programName);
            SeleniumUtils.sleepMillis(500);
            SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."), 60);
            //WebElement el = SeleniumUtils.waitForElementWithoutException(driver, Locator.anchorText(programName));
            WebElement el = SeleniumUtils.waitForClickableWithoutException(driver, Locator.anchorText(programName));
            if (el != null) {
                break;
            } else {
                SeleniumUtils.sleepSecs(3);
            }
        }
        
        // click on the program
        SeleniumUtils.clickUsingJavascript(driver, Locator.anchorText(programName));

        // verify we are on the program detail page
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Program Detail");
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorText("Program Enroll Setting"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorText(programName));
    }

    @BeforeClass
    public void setup() {
        driver = Driver.get();
    }

    @Test
    public static void testCreateNewProgram() {

        SeleniumUtils.goToTab(driver, Locator.L_TAB_PROGRAMS, "DSM Central: All Programs");

        SeleniumUtils.click(driver, Locator.Programs.L_Add_New_Program);

        // wait for the page to come up
        SeleniumUtils.assertPageTitle(driver, "DSM Central: New Program");
        SeleniumUtils.waitForElement(driver, Locator.Programs.L_ADD_PROGRAM_NAME);

        // basic program info
        savedProgramName = "program " + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Programs.L_ADD_PROGRAM_NAME, savedProgramName);
        SeleniumUtils.setText(driver, Locator.Programs.L_ADD_PROGRAM_DESCRIPTION, Locator.Text.DESCRIPTION);

        SelectUtils.clickItemOfDropDownWithTextInput(driver, By.id("program_serviceType_field_combobox-inputEl"), "Electric");
        SelectUtils.clickItemOfDropDownWithTextInput(driver, By.id("program_serviceType_field_combobox-inputEl"), "Gas");
        SelectUtils.clickItemOfDropDownWithTextInput(driver, By.id("program_serviceType_field_combobox-inputEl"), "Renewable");
        SelectUtils.clickItemOfDropDownWithTextInput(driver, By.id("program_serviceType_field_combobox-inputEl"), "Water");

        SelectUtils.clickItemName(driver, By.id("program_type_field-inputEl"), "Education and Outreach");
        SelectUtils.clickItemName(driver, By.id("program_status_field-inputEl"), "Active");

        String[] sectors = {"Agriculture", "Commercial", "Industrial", "Irrigation", "Mining", "Non-Residential",
                "Public Authority", "Residential", "Street Light Public Authority", "Transportation" };

        for (int i = 0; i < sectors.length; i++) {
            SelectUtils.enterTextToSelect(driver, By.id("program_sectors_field_combobox-inputEl"), sectors[i]);
            SeleniumUtils.sleepMillis(500);
        }


        SelectUtils.clickItemName(driver, By.id("program_defaultsector_field-inputEl"), "Residential");
        SeleniumUtils.setText(driver, Locator.Programs.DATE_FROM, Locator.Text.DATE_FROM);
        SeleniumUtils.setText(driver, Locator.Programs.DATE_TO, Locator.Text.DATE_TO);
        SeleniumUtils.click(driver, Locator.Programs.TRACK_RENEWABLE_GENERATION);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Programs.ADD_NEW_SERVICE_POINT);
        SeleniumUtils.setText(driver, By.id("service_point_0_input"), "12345");
        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, By.id("program_service_territory_field_combobox-inputEl"));

        SeleniumUtils.clickUsingJavascript(driver, Locator.Programs.ADD_NEW_GOAL);
        // program goals

        String[] metric = {"Water Savings (Gal)",  "Therm Savings",  "kW Reduction", "kWh Savings"};
        for(int i = 0; i < metric.length; i++){
            SelectUtils.clickItemName(driver, By.id("program_metric_"+i+"_input"), metric[i]);
            SeleniumUtils.setText(driver, By.xpath("//input[@componentid='program_goal_"+i+"_field']"), Locator.Programs.goal_txt);
            SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, By.id("program_svc_terr_"+i+"_input"));
            SelectUtils.clickItemName(driver, By.id("program_sector_"+i+"_input"), "Residential");
            SeleniumUtils.setText(driver, By.xpath("//input[@componentid='program_goal_startdate_"+i+"_field']"), Locator.Text.DATE_FROM);
            SeleniumUtils.setText(driver, By.xpath("//input[@componentid='program_goal_enddate_"+i+"_field']"), Locator.Text.DATE_TO);
        }

        // internal users
        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, By.id("program_programmgrs_0_field_combobox-inputEl"));

        SelectUtils.enterTextToSelect(driver, By.id("program_supportstaff_0_field_combobox-inputEl"), Locator.Programs.Support_Staff_Name);
        SelectUtils.enterTextToSelect(driver, By.id("program_projectmgrs_0_field_combobox-inputEl"), Locator.Programs.Project_Managers_Name);
        // project settings
        SeleniumUtils.setText(driver, Locator.Programs.Project_Number_Prefix, Locator.Programs.Project_Number_Prefix_Name);
        SeleniumUtils.click(driver, Locator.Programs.Allow_Project_Copy);
        SeleniumUtils.setText(driver, Locator.Programs.Comments_program, Locator.Text.DESCRIPTION);
        // save
        SeleniumUtils.click(driver, Locator.Programs.Save_btn);
        // wait for the confirmation dialog

        SeleniumUtils.handleConfirmMsg(driver, "Program Saved", "The program was successfully saved.", "OK", 120);

        SeleniumUtils.sleepMillis(200);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));

        // check we are on the program list page now
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("(//div[@class='title'])[text()[contains(.,'All Programs')]]"));
        SeleniumUtils.assertPageTitle(driver, "DSM Central: All Programs");
    }
}
