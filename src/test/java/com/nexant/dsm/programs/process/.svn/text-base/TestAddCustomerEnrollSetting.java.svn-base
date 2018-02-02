package com.nexant.dsm.programs.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.dsm.libraries.partner.process.TestManagePartnersElements;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: VKamenev
 * Date: 9/3/13
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
@Test
public class TestAddCustomerEnrollSetting {

    public static long customerID;
    public static String str_customer_id;
    private static WebDriver driver;


    @BeforeClass
    public static void setup() {
        driver = Driver.get();
    }

    @Test
    public void testAddCustomerEnrollSetting() {

        Assert.assertNotNull(TestAddNewProgram.savedProgramName, "need a saved program name");
        Assert.assertNotNull(TestManagePartnersElements.savedServiceType, "Need a saved service type from Test_Manage_Partners_Elements");
        Assert.assertNotNull(TestManagePartnersElements.savedSpecialtyName, "Need a saved specialty name from Test_Manage_Partners_Elements");

        TestAddNewProgram.openSavedProgram();

        SeleniumUtils.clickUsingJavascript(driver, Locator.Programs.L_Enroll);

        SeleniumUtils.waitForElement(driver, Locator.Programs.L_NAME);

        // verify we move to the program page
        Assert.assertEquals(driver.getTitle(), "DSM Central: Program Enroll Setting");

        customerID = System.currentTimeMillis();
        str_customer_id = String.valueOf(customerID);
        SeleniumUtils.clearThenSetText(driver, Locator.Programs.L_NAME, str_customer_id);
        SeleniumUtils.clearThenSetText(driver, Locator.Programs.L_DESCRIPTION, "Nexant iEnergy provides utilities with comprehensive business process management driving the effective rollout");
        SeleniumUtils.clearThenSetText(driver, Locator.Programs.L_publicSummary, "Just a summary string from automation tests");

        // add a new program category
        String programCategory = "progcat" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, By.id("newCategory-inputEl"), programCategory);
        SeleniumUtils.click(driver, By.xpath("//span[text()[.='Add Category']]/.."));

        SeleniumUtils.sleepSecs(1);

    }
    
    @Test(dependsOnMethods="testAddCustomerEnrollSetting")
    public void testAddCustomerEligibilitySetting() {

        // there is whitespace in the anchor link, so use contains
        SeleniumUtils.click(driver, By.id("varDecAddRowLink")); // By.xpath("//*[@id='eligibility_panel']//a[text()[contains(.,'+Add')]]"));

        // click here to get the row enabled for input
        SeleniumUtils.click(driver, Locator.divText("-Select Attribute-"));
        SeleniumUtils.sleepSecs(1);
        SelectUtils.clickItemOfDropDownWithTextInput(driver, By.xpath("//*[@id='eligibility_panel']//input[@name='condition_attribute_Combo']"), "Customer State/Province");

        SeleniumUtils.click(driver, By.xpath("//*[@id='eligibility_panel']//input[@name='operator_combo']"));
        SelectUtils.enterTextToSelect(driver, By.xpath("//*[@id='eligibility_panel']//input[@name='operator_combo']"), "=");

        SeleniumUtils.click(driver, By.xpath("//*[@id='eligibility_panel']//input[@name='condition_value_textfield']"));
        SeleniumUtils.clearThenSetText(driver, By.xpath("//*[@id='eligibility_panel']//input[@name='condition_value_textfield']"), "CA");

        SeleniumUtils.click(driver, By.xpath("//*[@id='eligibility_panel']//input[@name='logic_operator_combo']"));
        SeleniumUtils.clearThenSetText(driver, By.xpath("//*[@id='eligibility_panel']//input[@name='logic_operator_combo']"), "OR");
        SeleniumUtils.clickUsingJavascript(driver, By.xpath("//*[@id='eligibility_panel']//span[text()[.='Update']]/../../.."));

        SeleniumUtils.sleepSecs(1);
        
    }
    
    @Test(dependsOnMethods="testAddCustomerEnrollSetting")
    public void testAddPartnerEligibilitySetting() {

        // partner elegibility
        SeleniumUtils.click(driver, By.id("varDecAddRowLink_Partner"));
        // click on the blank row to get it enabled
        SeleniumUtils.click(driver, By.xpath("//*[@id='partnerEligibilityGrid-body']//tbody/tr"));

        SelectUtils.enterTextToSelect(driver, By.xpath("//*[@id='partner_eligibility_panel']//input[@name='serviceTypeId']"), TestManagePartnersElements.savedServiceType);
        SelectUtils.enterTextToSelect(driver, By.xpath("//*[@id='partner_eligibility_panel']//input[@name='operator_combo']"), TestManagePartnersElements.savedSpecialtyName);
        SelectUtils.enterTextToSelect(driver, By.xpath("//*[@id='partner_eligibility_panel']//input[@name='sectorId']"), "Residential");
        SelectUtils.enterTextToSelect(driver, By.xpath("//*[@id='partner_eligibility_panel']//input[@name='stateId']"), "CA");
        SelectUtils.enterTextToSelect(driver, By.xpath("//*[@id='partner_eligibility_panel']//input[@name='logic_operator_combo']"), "OR");
        SeleniumUtils.clickUsingJavascript(driver, By.xpath("//*[@id='partner_eligibility_panel']//span[text()[.='Update']]/../../.."));

        SeleniumUtils.sleepSecs(1);
    }
    
    @Test(dependsOnMethods={"testAddCustomerEnrollSetting", "testAddCustomerEligibilitySetting", "testAddPartnerEligibilitySetting" })
    public void testSave() {
        // save
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Save"));

        // verify
        SeleniumUtils.waitForElement(driver, Locator.divTextContains("Customer Enroll Setting save succeeded."));
    }
}
