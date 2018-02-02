package com.nexant.dsm.libraries.dsmcosteffectiveness.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.dsm.libraries.measures.process.TestCreateCategoryTypeSubType;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: vkamenev
 * Date: 8/28/13
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */

public class TestAddNewShape {
    private static WebDriver driver;
    private File dir;
    private String path;

    public static String savedShapeName;

    @BeforeMethod
    public void setup() {
        dir = new File("data");
        path = dir.getAbsolutePath();

        driver = Driver.get();
        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_LIBRARIES, Locator.L_LIBRARIES_DSM_COST_EFFECTIVENESS, "DSM Central: DSM Cost Effectiveness");
        // should have this link (to know we are on the right page)
        SeleniumUtils.waitForClickable(driver, By.linkText("DSM Cost Effectiveness"));

        // Sort Shapes
        SeleniumUtils.waitForClickable(driver, By.xpath("//a/span[text()[.='Name']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Name']]"));
        SeleniumUtils.waitForClickable(driver, By.xpath("//a/span[text()[.='Last Updated']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Last Updated']]"));

        // check we are on the page, check all the header links
        SeleniumUtils.waitForClickable(driver, Locator.anchorTextContainsWithinAnyWithClass("header_menu", "Shapes")); // using contains here since there are two &nbsp; that seem hard to match
        SeleniumUtils.waitForClickable(driver, Locator.anchorTextWithinAnyWithClass("header_menu", "Costs"));
        SeleniumUtils.waitForClickable(driver, Locator.anchorTextWithinAnyWithClass("header_menu", "Rates"));
        SeleniumUtils.waitForClickable(driver, Locator.anchorTextWithinAnyWithClass("header_menu", "Other"));
        SeleniumUtils.waitForClickable(driver, Locator.anchorTextWithinAnyWithClass("header_menu", "Customers"));
        
        SeleniumUtils.waitForClickable(driver, Locator.inputWithValue("+ Add New Shape"));

        SeleniumUtils.waitForAllInVisible(driver, Locator.divText("Loading..."));
    }

    @Test
    public void testAddShape() {
        // click add new shape
        SeleniumUtils.click(driver,  By.id("measure_library_top_button_right"));

        // verify we have the add popup - this is unstable
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Create/Edit LoadShape"));

        // name must be unique
        savedShapeName = "shape" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.DSM_CE.L_NAME, savedShapeName);
        SeleniumUtils.setText(driver, Locator.DSM_CE.L_DESCRIPTION, Locator.Text.DESCRIPTION);

        SeleniumUtils.click(driver, Locator.DSM_CE.L_IS_SYSTEM);

        // set the type
        String type = TestCreateCategoryTypeSubType.savedCategoryTypeName;
        Assert.assertNotNull(type, "should have saved a category type from a previous test");
        SelectUtils.enterTextToSelect(driver, Locator.DSM_CE.L_TYPE, type);

        // set the sub type
        String subType = TestCreateCategoryTypeSubType.savedSubTypeName;
        //	String subType = "Dust Collection";
        Assert.assertNotNull(subType, "should have saved a sub type from a previous test");
        SelectUtils.enterTextToSelect(driver, Locator.DSM_CE.L_SUBTYPE, subType);

        // set the sector
        SelectUtils.enterTextToSelect(driver, Locator.DSM_CE.L_SECTOR, Locator.Text.SECTOR);

        SeleniumUtils.setText(driver, Locator.DSM_CE.L_CLIMATE_ZONE, Locator.Text.STRING);

       String shapeFile = path + File.separatorChar + "Shape1.xlsx";
        SeleniumUtils.waitForElement(driver, By.xpath("//input[@name='shapeFile']")).sendKeys(shapeFile);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanText("Save"));
        SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("Save"));

       SeleniumUtils.sleepSecs(60);
       SeleniumUtils.waitForInVisible(driver, Locator.divText("Uploading your data..."), 60);

        By confirmBox = Locator.divText("Result");
        SeleniumUtils.assertElementExistsWithWait(driver, confirmBox, 60);
        
        SeleniumUtils.handleConfirmMsg(driver, "Result", "Successfully processed the record", "OK");
    }

    @Test
    public void testUploadCostFiles() {
        SeleniumUtils.click(driver, Locator.anchorText("Costs"));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.sleepSecs(1);

        //WebElement costs = SeleniumUtils.waitForElementWithoutException(driver, By.xpath("//div[@id='marginalcostgridpanel-body']"), 5);

        /*if (costs == null)*/ 
            //upload the cost files
        	SeleniumUtils.waitForClickable(driver, By.id("measure_library_top_button_right"));
            SeleniumUtils.click(driver, By.id("measure_library_top_button_right")); //+ Add New Cost

            // make sure on the right popup.
            SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Create/Edit Cost"));
            SeleniumUtils.sleepSecs(1);
            SeleniumUtils.setText(driver, By.xpath("//input[@name='marginalCostName']"), "testCosts" + System.currentTimeMillis());

            /*String costPath = path + File.separatorChar + "Cost.xlsx";
            SeleniumUtils.waitForElement(driver, By.xpath("//input[@name='file']")).sendKeys(costPath); //file Browse Button for energy cost
            String capaCityCost = path + File.separator + "capacity_cost.xlsx";
            SeleniumUtils.waitForElement(driver, By.xpath("//input[@name='capacitiveCostFile']")).sendKeys(capaCityCost); //file Browse Button for capacity cost
*/
            SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Save"));

            SeleniumUtils.sleepSecs(20);
            SeleniumUtils.waitForInVisible(driver, Locator.divText("Uploading your data..."), 60);

            SeleniumUtils.handleConfirmMsg(driver, "Result", "Successfully processed the record", "OK");
            SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));

            SeleniumUtils.sleepSecs(1);
            

    }

    @Test
    public void testUploadRatesFiles(){
        SeleniumUtils.click(driver, Locator.anchorText("Rates"));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.sleepSecs(1);

        //upload the rates file
        SeleniumUtils.waitForClickable(driver, By.id("measure_library_top_button_right"));
        SeleniumUtils.click(driver, By.id("measure_library_top_button_right")); //+ Add New Rate

        // make sure on the right popup.
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Create/Edit Rate"));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.setText(driver, By.xpath("//input[@name='marginalRateName']"), "testRates" + System.currentTimeMillis());

        String ratePath = path + File.separatorChar + "Rates.xlsx";
        SeleniumUtils.waitForElement(driver, By.xpath("//input[@name='rateFile']")).sendKeys(ratePath); //file Browse Button

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Save"));

        SeleniumUtils.sleepSecs(20);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Uploading your data..."), 60);

        SeleniumUtils.handleConfirmMsg(driver, "Result", "Successfully processed the record", "OK");
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));

        SeleniumUtils.sleepSecs(1);
    }

    @Test
    public void testOther(){
        SeleniumUtils.click(driver, By.linkText("Other"));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.sleepSecs(1);

        //Add new input
        SeleniumUtils.waitForClickable(driver, By.id("measure_library_top_button_right"));
        SeleniumUtils.click(driver, By.id("measure_library_top_button_right")); //+ Add New Input

        // make sure on the right page
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorText("Add New Input"));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.setText(driver, By.xpath("//input[@name='inputName']"), "testNewInput" + System.currentTimeMillis());

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Save"));

        SeleniumUtils.sleepSecs(30);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Uploading your data..."), 60);
        SeleniumUtils.sleepSecs(5);

        SeleniumUtils.handleConfirmMsg(driver, "Data saved succesfully", "Other DSM Inputs save succeeded.", "OK");
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));

        SeleniumUtils.sleepSecs(2);
    }

    @Test
    public void testUploadCustomersFiles(){
        SeleniumUtils.click(driver, By.linkText("Customers"));
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));

        SeleniumUtils.sleepSecs(1);

        SeleniumUtils.waitForClickable(driver, By.id("measure_library_top_button_right"));
        SeleniumUtils.click(driver, By.id("measure_library_top_button_right")); //+ Add New Forecast

        // make sure on the right popup.
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Create/Edit Forecast"));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.setText(driver, By.xpath("//input[@name='planningCustForecastLibName']"), "testForecast" + System.currentTimeMillis());

        String customerForecastPath = path + File.separatorChar + "CustomersForecast.xlsx";
        SeleniumUtils.waitForElement(driver, By.xpath("//input[@name='forecastFile']")).sendKeys(customerForecastPath); //file Browse Button

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.traksmartButtonAnchorStyle("Save"));
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Save"));

        SeleniumUtils.sleepSecs(30);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Uploading your data..."), 60);
        SeleniumUtils.sleepSecs(10);

        SeleniumUtils.handleConfirmMsg(driver, "Result", "Successfully processed the record", "OK");
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));

        SeleniumUtils.sleepSecs(1);
    }

}
