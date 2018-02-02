package com.nexant.dsm.libraries.measures.process;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;


/**
 * Created by VKamenev on 5/23/2014.
 */
@Test(groups={"CategoryTypes"})
public class TestCreateCategoryTypeSubType {
    private static WebDriver driver;

    public static String savedCategoryName ;
    public static String savedCategoryTypeName;
    public static String savedSubTypeName;

    public static String savedMeasureName;
    
    public static String incentiveAttribute="Incentive_Customer";

    @BeforeClass
    public void setup() {
        driver = Driver.get();
    }

    @Test
    public void testAddCategory() {

        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_LIBRARIES, Locator.L_LIBRARIES_MEASURES, "DSM Central: Measure Library");

        // Sort Measures
        SeleniumUtils.waitForClickable(driver, By.xpath("//a/span[text()[.='Last Updated']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Last Updated']]"));
        SeleniumUtils.waitForClickable(driver, By.xpath("//a/span[text()[.='Sub-type']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Sub-type']]"));
        SeleniumUtils.waitForClickable(driver, By.xpath("//a/span[text()[.='Type']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Type']]"));
        SeleniumUtils.waitForClickable(driver, By.xpath("//a/span[text()[.='Category']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Category']]"));

        SeleniumUtils.click(driver, Locator.Measure_PSEG.L_MANAGE_TAXONOMY);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Manage Taxonomy"));

        By by = Locator.traksmartButtonAnchorStyle("+ Add Category");

        SeleniumUtils.waitForElementHasNotClass(driver, by, "x-btn-disabled");
        SeleniumUtils.click(driver, by);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Add new Measure Category"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Measure.ADD_NEW_MESAURE_CATEGORY_POPUP_LOCATOR);

        // add a unique category name, but we want it to show up first when ordered by name
        savedCategoryName = "cat" + (3011309597688L-System.currentTimeMillis());
        SeleniumUtils.setText(driver, Locator.Measure_PSEG.L_MEASURE_CATEGORY_NAME, savedCategoryName);

        // set description
        SeleniumUtils.setText(driver, Locator.Measure_PSEG.L_DESCRIPTION, "Description");

        // select status
        SelectUtils.clickItemName(driver, Locator.Measure_PSEG.L_STATUS, "Active");
        // save
        SeleniumUtils.clickUsingJavascript(driver, Locator.Measure_PSEG.L_SAVE_CATEGORY);

        // check popup goes away
        SeleniumUtils.waitForInVisible(driver, Locator.Measure.ADD_NEW_MESAURE_CATEGORY_POPUP_LOCATOR);

        // check it is saved
        By pathOfNewCategory = By.xpath("(//div/ul/li[text()[starts-with(.,'" + savedCategoryName + "')]])");

        SeleniumUtils.waitForInVisible(driver, By.xpath("//div[@id='categoryListComp']//div[contains(@class, 'x-mask')]"));
        SeleniumUtils.clickUsingJavascript(driver, pathOfNewCategory);

        // check the item shows up below
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div[text()[.='" + savedCategoryName + "']]"));
    }


    @Test(dependsOnMethods={"testAddCategory"})
    public void testAddType() {

        By by = Locator.traksmartButtonAnchorStyle("+ Add Type");
        SeleniumUtils.waitForElementHasNotClass(driver, by, "x-btn-disabled");
        // select the saved category above
        SeleniumUtils.clickUsingJavascript(driver, by);

        // check we are on the add type popup
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Measure.ADD_NEW_MESAURE_TYPE_POPUP_LOCATOR);

        //Generate number
        savedCategoryTypeName = "ct" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Measure_PSEG.L_ADD_TYPE_NAME, savedCategoryTypeName);

        SeleniumUtils.setText(driver, Locator.Measure_PSEG.L_ADD_TYPE_DESCRIPTION, "Automation Description");

        // select status
        SelectUtils.clickItemName(driver, Locator.Measure_PSEG.L_ADD_TYPE_STATUS, "Active");
        // save
        SeleniumUtils.clickUsingJavascript(driver, Locator.Measure_PSEG.L_SAVE_TYPE);

        // check the popup goes away
        SeleniumUtils.waitForInVisible(driver, Locator.Measure.ADD_NEW_MESAURE_TYPE_POPUP_LOCATOR);

        // check the new type is in the list
        By pathOfNewCategoryType = By.xpath("(//div/ul/li[text()[starts-with(.,'" + savedCategoryTypeName + "')]])");

        SeleniumUtils.waitForInVisible(driver, By.xpath("//div[@id='typeListComp']//div[contains(@class, 'x-mask')]"));

        SeleniumUtils.clickUsingJavascript(driver, pathOfNewCategoryType);

        // check the item shows up below
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div[text()[.='" + savedCategoryTypeName + "']]"));
    }

    @Test(dependsOnMethods={"testAddType"})
    public void testAddSubType() {

        // select the type
        By by = Locator.traksmartButtonAnchorStyle("+ Add Sub Type");
        SeleniumUtils.waitForElementHasNotClass(driver, by, "x-btn-disabled");
        // select the saved category above
        SeleniumUtils.clickUsingJavascript(driver, by);


        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Measure.ADD_NEW_MESAURE_SUBTYPE_POPUP_LOCATOR);

        //Generate number
        savedSubTypeName = "st" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Measure_PSEG.L_ADD_SUB_TYPE_NAME, savedSubTypeName);
        SeleniumUtils.setText(driver, Locator.Measure_PSEG.L_ADD_SUB_TYPE_DESCRIPTION, "Automation");
        // select status
        SelectUtils.clickItemName(driver, Locator.Measure_PSEG.L_ADD_SUB_TYPE_STATUS, "Active");
        // save
        SeleniumUtils.clickUsingJavascript(driver, Locator.Measure_PSEG.L_SAVE_SUB_TYPE);

        // check popup goes away
        SeleniumUtils.waitForInVisible(driver, Locator.Measure.ADD_NEW_MESAURE_SUBTYPE_POPUP_LOCATOR);

        // check we have the item in the list
        By pathOfNewCategorySubType = By.xpath("(//div/ul/li[text()[starts-with(.,'" + savedSubTypeName + "')]])");

        By mask = By.xpath("//div[@id='subTypeListComp']//div[contains(@class, 'x-mask')]");
        
        SeleniumUtils.waitForInVisible(driver, mask);
        SeleniumUtils.clickUsingJavascript(driver, pathOfNewCategorySubType);
        
        // check the item shows up below
        //SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div[text()[.='" + savedSubTypeName + "']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText(savedSubTypeName));
    }

    @Test(dependsOnMethods={"testAddSubType"})
    public void testAddTrackingField() {

        SeleniumUtils.click(driver, Locator.spanText("TRACKING FIELDS")); //make sure we are on the tracking tab
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.click(driver, By.id("trackFieldButton")); // + Add button (use id since there are two buttons with + Add)

        // check we ge the popup
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Available Tracking Fields"));
        SeleniumUtils.assertElementExists(driver, Locator.spanText("Attribute Name:"));

        //select first checkbox on the x-window div
        By by = By.xpath("//div[contains(@class, 'x-window')]//table[contains(@class, 'x-grid-item')]/tbody/tr/td[2]/div");
        WebElement tableRow = SeleniumUtils.click(driver, by);

        //remember the attribute value of the selected row
        String attributeText = tableRow.getText();

       //select incentive attribute - Incentive Customer
        By incentiveBy = Locator.divText(incentiveAttribute);
        WebElement incentiveAttributeRow = SeleniumUtils.click(driver, incentiveBy);

        //remember the attribute value of the selected row
       String incentiveAttributeText = incentiveAttributeRow.getText();

        //select the tracking field
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Measure.TRACKING_FIELD);
   //     SeleniumUtils.click(driver, Locator.Measure.ADD_NEW_TRACKING_FIELD);


        //click save
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("Available Tracking Fields", "Save"));
        
        SeleniumUtils.assertElementInVisibleWithWait(driver, Locator.divText("Available Tracking Fields"));

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText(attributeText));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText(incentiveAttributeText));

    }

    @Test(dependsOnMethods={"testAddTrackingField"})
    public void testAddAttributes() {

        SeleniumUtils.click(driver, Locator.spanText("ATTRIBUTES")); //make sure we are on the Attributes tab
        SeleniumUtils.sleepSecs(1);
        
        SeleniumUtils.click(driver, By.id("attributeButton")); // + Add button (use id since there are two buttons with + Add)
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Available Attributes"));
        SeleniumUtils.assertElementExists(driver, Locator.spanText("Attribute Name:"));

        //select first checkbox on the x-window div
        By by = By.xpath("//div[contains(@class, 'x-window')]//table[contains(@class, 'x-grid-item')]/tbody/tr/td[2]/div");
        WebElement tableRow = SeleniumUtils.click(driver, by);

        //remember the attribute value of the selected row
        String attributeText = tableRow.getText();

        //click save
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("Available Attributes", "Save"));
        SeleniumUtils.assertElementInVisibleWithWait(driver, Locator.divText("Available Attributes"));

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText(attributeText));
    }

    @Test(dependsOnMethods={"testAddAttributes"})
    public void testAddNewMeasure() {

        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_LIBRARIES, Locator.L_LIBRARIES_MEASURES, "DSM Central: Measure Library");

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("+ Add New Measure"));

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Measure Details"));

        SeleniumUtils.sleepMillis(200);
        SelectUtils.clickItemName(driver, By.xpath("//input[@name='measureDefinitionStatusId']"), "Active");
        SeleniumUtils.sleepMillis(200);
        savedMeasureName = "measure" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, By.xpath("//input[@name='name']"), savedMeasureName);
        SeleniumUtils.setText(driver, By.xpath("//input[@name='description']"), "measure description");

        SeleniumUtils.setText(driver, By.id("effectiveStartDate-inputEl"), Locator.Text.DATE_FROM);
        SeleniumUtils.setText(driver, By.id("effectiveEndDate-inputEl"), Locator.Text.DATE_TO);

        SeleniumUtils.sleepMillis(200);
        SelectUtils.clickItemName(driver, By.xpath("//input[@name='measureCategoryId']"), savedCategoryName);
        SeleniumUtils.sleepSecs(1);
        SelectUtils.clickItemName(driver, By.xpath("//input[@name='measTypeId']"), savedCategoryTypeName);
        SeleniumUtils.sleepSecs(1);
        SelectUtils.clickItemName(driver, By.xpath("//input[@name='measSubTypeId']"), savedSubTypeName);
        SeleniumUtils.sleepMillis(200);


        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Save"));

        SeleniumUtils.waitForElement(driver, Locator.divText(savedMeasureName));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText(savedMeasureName));
        SeleniumUtils.sleepMillis(200);
        SeleniumUtils.click(driver, Locator.divText(savedMeasureName));

        SeleniumUtils.sleepMillis(200);
    }

    @Test(dependsOnMethods={"testAddAttributes"})
    public void testAddNewLongMeasure() {

        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_LIBRARIES, Locator.L_LIBRARIES_MEASURES, "DSM Central: Measure Library");

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("+ Add New Measure"));

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Measure Details"));

        SeleniumUtils.sleepMillis(200);
        SelectUtils.clickItemName(driver, By.xpath("//input[@name='measureDefinitionStatusId']"), "Active");
        SeleniumUtils.sleepMillis(200);
        savedMeasureName = "measure" + System.currentTimeMillis();
        String longMeasureName = StringUtils.rightPad(savedMeasureName, 512, " Name");
        SeleniumUtils.setText(driver, By.xpath("//input[@name='name']"), longMeasureName);
        SeleniumUtils.setText(driver, By.xpath("//input[@name='description']"), StringUtils.rightPad(savedMeasureName, 2000, " Desc"));

        SeleniumUtils.setText(driver, By.id("effectiveStartDate-inputEl"), Locator.Text.DATE_FROM);
        SeleniumUtils.setText(driver, By.id("effectiveEndDate-inputEl"), Locator.Text.DATE_TO);

        SeleniumUtils.sleepMillis(200);
        SelectUtils.clickItemName(driver, By.xpath("//input[@name='measureCategoryId']"), savedCategoryName);
        SeleniumUtils.sleepSecs(1);
        SelectUtils.clickItemName(driver, By.xpath("//input[@name='measTypeId']"), savedCategoryTypeName);
        SeleniumUtils.sleepSecs(1);
        SelectUtils.clickItemName(driver, By.xpath("//input[@name='measSubTypeId']"), savedSubTypeName);
        SeleniumUtils.sleepMillis(200);


        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Save"));

        SeleniumUtils.waitForElement(driver, Locator.divText(longMeasureName));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText(longMeasureName));
        SeleniumUtils.sleepMillis(200);
        SeleniumUtils.click(driver, Locator.divText(longMeasureName));

        SeleniumUtils.sleepMillis(200);

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("ATTRIBUTES"));
        //SeleniumUtils.click(driver, By.cssSelector(".x-grid-row-expander").);
        SeleniumUtils.sleepMillis(200);

        List<WebElement> list = driver.findElements(By.cssSelector(".x-grid-row-expander"));

        for(WebElement we:list){
            if(we.isDisplayed()){
                we.click();
                break;
            }
        }

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle(" Add "));

        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("ADD", "name"), StringUtils.rightPad(savedMeasureName, 512, " MAVP_Name") );
        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("ADD", "value"), StringUtils.rightPad(savedMeasureName, 10000, " MAVP_Value"));
        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("ADD", "source"), StringUtils.rightPad(savedMeasureName, 2000, " MAVP_Source"));

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("ADD", "Save"));
        SeleniumUtils.sleepMillis(200);
    }

    @Test(dependsOnMethods={"testAddAttributes"})
    public void testAddNewUTF8Measure() {

        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_LIBRARIES, Locator.L_LIBRARIES_MEASURES, "DSM Central: Measure Library");

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("+ Add New Measure"));

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Measure Details"));

        SeleniumUtils.sleepMillis(200);
        SelectUtils.clickItemName(driver, By.xpath("//input[@name='measureDefinitionStatusId']"), "Active");
        SeleniumUtils.sleepMillis(200);
        savedMeasureName = "measure" + System.currentTimeMillis();
        String longMeasureName = StringUtils.rightPad(savedMeasureName, 100, " Name_© ® ™ • ½ ¼ ¾ ⅓ ⅔ † ‡ µ ¢ £ € « » ♠ ♣ ♥ ♦ ¿ �");
        SeleniumUtils.setText(driver, By.xpath("//input[@name='name']"), longMeasureName);
        SeleniumUtils.setText(driver, By.xpath("//input[@name='description']"), StringUtils.rightPad(savedMeasureName, 500, " Desc_© ® ™ • ½ ¼ ¾ ⅓ ⅔ † ‡ µ ¢ £ € « » ♠ ♣ ♥ ♦ ¿ �"));

        SeleniumUtils.setText(driver, By.id("effectiveStartDate-inputEl"), Locator.Text.DATE_FROM);
        SeleniumUtils.setText(driver, By.id("effectiveEndDate-inputEl"), Locator.Text.DATE_TO);

        SeleniumUtils.sleepMillis(200);
        SelectUtils.clickItemName(driver, By.xpath("//input[@name='measureCategoryId']"), savedCategoryName);
        SeleniumUtils.sleepSecs(1);
        SelectUtils.clickItemName(driver, By.xpath("//input[@name='measTypeId']"), savedCategoryTypeName);
        SeleniumUtils.sleepSecs(1);
        SelectUtils.clickItemName(driver, By.xpath("//input[@name='measSubTypeId']"), savedSubTypeName);
        SeleniumUtils.sleepMillis(200);


        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Save"));

        SeleniumUtils.waitForElement(driver, Locator.divText(longMeasureName));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText(longMeasureName));
        SeleniumUtils.sleepMillis(200);
        SeleniumUtils.click(driver, Locator.divText(longMeasureName));

        SeleniumUtils.sleepMillis(200);

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("ATTRIBUTES"));
        //SeleniumUtils.click(driver, By.cssSelector(".x-grid-row-expander").);
        SeleniumUtils.sleepMillis(200);

        List<WebElement> list = driver.findElements(By.cssSelector(".x-grid-row-expander"));

        for(WebElement we:list){
            if(we.isDisplayed()){
                we.click();
                break;
            }
        }

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle(" Add "));

        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("ADD", "name"), StringUtils.rightPad(savedMeasureName, 100, " MAVP_Name_© ® ™ • ½ ¼ ¾ ⅓ ⅔ † ‡ µ ¢ £ € « » ♠ ♣ ♥ ♦ ¿ �") );
        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("ADD", "value"), StringUtils.rightPad(savedMeasureName, 500, " MAVP_Value_© ® ™ • ½ ¼ ¾ ⅓ ⅔ † ‡ µ ¢ £ € « » ♠ ♣ ♥ ♦ ¿ �"));
        SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("ADD", "source"), StringUtils.rightPad(savedMeasureName, 200, " MAVP_Source_© ® ™ • ½ ¼ ¾ ⅓ ⅔ † ‡ µ ¢ £ € « » ♠ ♣ ♥ ♦ ¿ �"));

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("ADD", "Save"));
        SeleniumUtils.sleepMillis(200);

    }
}