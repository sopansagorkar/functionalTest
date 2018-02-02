package com.nexant.dsm.assessments.onsite_programs.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by tshakirov on 2/5/2015.
 */
public class TestMeasures {

    private WebDriver driver;
    private String programName = null;

    @BeforeClass
    public void setup() {
        driver = Driver.get();
    }

    @Test
    public void testMeasures(){
        SeleniumUtils.waitForClickable(driver, Locator.Assessments.MEASURES, 3000);
        SeleniumUtils.click(driver, Locator.Assessments.MEASURES);
        SelectUtils.enterTextToSelect(driver, Locator.Assessments.AS_M_CATEGORY, "All");
        SelectUtils.enterTextToSelect(driver, Locator.Assessments.AS_M_TYPE, "Energy Management");
        SelectUtils.enterTextToSelect(driver, Locator.Assessments.AS_M_SUB_TYPE, "Strategic Energy Management");
        SeleniumUtils.waitForElement(driver, Locator.spanText("vtest"));
        SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("vtest"));
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_M_SAVE);
        SeleniumUtils.waitForElement(driver, Locator.divText("Save Program Measure Associations Successful"));
        SeleniumUtils.handleConfirmMsg(driver, "Success", "Save Program Measure Associations Successful", "OK");
     //   SeleniumUtils.assertElementNotExists(driver,  Locator.divText("Save Program Measure Associations Successful"));
    }

    @Test
    public void testMeasureConfiguration(){
        SeleniumUtils.waitForClickable(driver, Locator.Assessments.MEASURE_CONF, 3000);
        SeleniumUtils.click(driver, Locator.Assessments.MEASURE_CONF);
        SeleniumUtils.clickUsingJavascript(driver, Locator.divText("kWh Savings"));
        SelectUtils.enterTextToSelect(driver, Locator.Assessments.AS_MC_FORMULA, "simple");
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_MC_SAVE);
        SeleniumUtils.waitForElement(driver, Locator.divText("Changes saved successfully."));
        SeleniumUtils.handleConfirmMsg(driver, "Status", "Changes saved successfully.", "OK");
   //     SeleniumUtils.assertElementNotExists(driver, Locator.divText("Changes saved successfully."));
    }

    @Test
    public void testMeasureIdentification(){
        SeleniumUtils.waitForClickable(driver, Locator.Assessments.MEASURE_IDENT, 3000);
        SeleniumUtils.click(driver, Locator.Assessments.MEASURE_IDENT);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.ASS_MI_ADD_IDENTIFICATION);
        SeleniumUtils.waitForElement(driver, Locator.divText("Measure Identification"));
        SelectUtils.enterTextToSelect(driver, Locator.Assessments.ASS_MI_SELECT_MEASURE, "vtest");
        SeleniumUtils.clickUsingJavascript(driver,  Locator.divText("qa1"));
        SeleniumUtils.waitForClickable(driver, Locator.Assessments.ASS_MI_SAVE, 5000);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.ASS_MI_SAVE);
        SeleniumUtils.waitForElement(driver, Locator.divText("Changes saved successfully."));
        SeleniumUtils.handleConfirmMsg(driver, "Status", "Changes saved successfully.", "OK");
     //   SeleniumUtils.assertElementNotExists(driver, Locator.divText("Changes saved successfully."));
    }
}
