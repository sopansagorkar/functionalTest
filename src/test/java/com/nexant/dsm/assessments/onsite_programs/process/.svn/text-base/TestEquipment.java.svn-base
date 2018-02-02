package com.nexant.dsm.assessments.onsite_programs.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by tshakirov on 2/4/2015.
 */
public class TestEquipment {

    private WebDriver driver;
    private String programName = null;

    @BeforeClass
    public void setup() {
        driver = Driver.get();
    }

    @Test
    public void addAssociations(){
        SeleniumUtils.waitForClickable(driver, Locator.Assessments.EQUIPMENT, 3000);
        SeleniumUtils.click(driver, Locator.Assessments.EQUIPMENT);
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Audit Programs");
        SeleniumUtils.assertElementExists(driver, Locator.Assessments.AS_ADD_ASSOCIATION);
        SeleniumUtils.waitForClickable(driver, Locator.Assessments.AS_ADD_ASSOCIATION, 3000);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_ADD_ASSOCIATION);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Add Association"));
        SelectUtils.clickItemOfDropDownWithTextInput(driver, Locator.Assessments.AS_ASS_EQU_TYPE, "AC");
        SelectUtils.clickItemOfDropDownWithTextInput(driver, Locator.Assessments.AS_ASS_BASELINE, "nm [nm]");
        SelectUtils.clickItemOfDropDownWithTextInput(driver, Locator.Assessments.AS_ASS_EQU_ASS_TYPE, "Proposed Equipment");
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_ASS_ITEM);
        SeleniumUtils.waitForClickable(driver, Locator.Assessments.AS_ASSOCIATION_ADD, 3000);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_ASSOCIATION_ADD);
    }
}
