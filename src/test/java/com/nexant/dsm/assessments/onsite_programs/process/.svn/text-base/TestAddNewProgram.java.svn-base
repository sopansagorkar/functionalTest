package com.nexant.dsm.assessments.onsite_programs.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * Created by tshakirov on 1/27/2015.
 */
public class TestAddNewProgram {

    private WebDriver driver;
    private String programName = null;
    private String assessPrefix = null;

    @BeforeClass
    public void setup() {
        driver = Driver.get();
    }

    @Test
    public void navigate(){
        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_ASSESSMENTS, Locator.AS_ASSESSMENTS_ONSITE_PROGRAMS, "DSM Central: All Programs");
        SeleniumUtils.assertPageTitle(driver, "DSM Central: All Programs");
    }

    @Test
    public void addProgram(){
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_ADD_NEW_PROGRAM);
        SeleniumUtils.assertPageTitle(driver, "DSM Central: New Program");
        programName = "AS_PROG" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Assessments.AS_PROG_NAME, programName);
        SeleniumUtils.setText(driver, Locator.Assessments.AS_PROG_DESCRIPTION, Locator.Text.DESCRIPTION);
        //SelectUtils.clickItemOfDropDownWithTextInput(driver, By.xpath("//div[@class='form_content']//input[@name='assessmentType']"), "Residential");
        SelectUtils.clickFirstItemOfBoundList(driver, By.xpath("//div[@class='form_content']//input[@name='assessmentType']"));
        SelectUtils.enterTextToSelect(driver, Locator.Assessments.AS_SERVICE_TYPE, "Electric Program");
        SelectUtils.clickItemOfDropDownWithTextInput(driver, By.xpath("//div[@class='form_content']//input[@name='programStatus']"), "Active");
        assessPrefix = "AS_PREFIX" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Assessments.AS_NUM_PREFIX, assessPrefix);
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.clickUsingJavascript(driver, Locator.traksmartButtonAnchorStyle("Save"));
        SeleniumUtils.handleConfirmMsg(driver, "Status", "Changes saved successfully.", "OK");
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Assessments.AS_ATTR_GROUP);
    }

    @Test(dependsOnMethods={"addProgram"})
    public void addConfigParams(){
        SeleniumUtils.assertPageTitle(driver, "DSM Central: New Program");


        SelectUtils.enterTextToSelect(driver, Locator.Assessments.AS_PROG_MANAGER, "Support Nexant");
        SelectUtils.enterTextToSelect(driver, Locator. Assessments.AS_PROJ_MANAGER, "Support Nexant");
        SelectUtils.enterTextToSelect(driver, Locator.Assessments.AS_APP_USER, "Support Nexant");

        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_ADD_BTN);
        SeleniumUtils.setText(driver, Locator.Assessments.AS_LABEL, "INCLUDE_INSTALL");
        SeleniumUtils.setText(driver, Locator.Assessments.AS_VALUE, "True");
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_UPDATE);

        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_ADD_BTN);
        SeleniumUtils.clearThenSetText(driver, Locator.Assessments.AS_LABEL, "INCLUDE_MEASURES");
        SeleniumUtils.clearThenSetText(driver, Locator.Assessments.AS_VALUE, "TRUE");
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_UPDATE);

        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_ADD_BTN);
        SeleniumUtils.clearThenSetText(driver, Locator.Assessments.AS_LABEL, "MEASURE_LOOKUP_BUILDING_TYPE_ATTRIBUTE");
        SeleniumUtils.clearThenSetText(driver, Locator.Assessments.AS_VALUE, "ADT_Facility_Type");
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_UPDATE);

        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_ADD_BTN);
        SeleniumUtils.clearThenSetText(driver, Locator.Assessments.AS_LABEL, "SAVINGS_REPORT_CONFIG");
        SeleniumUtils.clearThenSetText(driver, Locator.Assessments.AS_VALUE, "REPORT");
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_UPDATE);

        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_ADD_BTN);
        SeleniumUtils.clearThenSetText(driver, Locator.Assessments.AS_LABEL, "THEME");
        SeleniumUtils.clearThenSetText(driver, Locator.Assessments.AS_VALUE, "ouc_sc_water");
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_UPDATE);


        SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyle("Save"));
        SeleniumUtils.clickUsingJavascript(driver, Locator.traksmartButtonAnchorStyle("Save"));
        SeleniumUtils.handleConfirmMsg(driver, "Status", "Changes saved successfully.", "OK");
        SeleniumUtils.assertElementNotExists(driver, Locator.divText("Changes saved successfully."));
    }

    @Test(dependsOnMethods={"addProgram"})
    public void addAttributeGroups(){
        SeleniumUtils.waitForClickable(driver, Locator.Assessments.AS_ATTR_GROUP);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_ATTR_GROUP);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Manage Screen"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.traksmartButtonAnchorStyleOnPopup("Add"));

        // there is a loading box here that is hard to see since it is quite quick.
        // a test failed here due to element not clickable due to the ext mask (loading...)
        SeleniumUtils.sleepMillis(250);
        SeleniumUtils.waitForAllInVisible(driver, Locator.anyText("Loading..."));

        /*----- Adding first Row -------*/
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("Manage Screen", "Add"));
        SeleniumUtils.setText(driver, Locator.Assessments.AS_ATTR_NAME, "Business Information");
        SeleniumUtils.setText(driver, Locator.Assessments.AS_ATTR_DESCR, "Business Description");
        SeleniumUtils.clearThenSetText(driver, Locator.Assessments.AS_ATTR_ORDER, "1");
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_UPDATE);
        //Asserting added data
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Business Information"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Business Description"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("1"));


        /*----- Adding second Row -------*/
        SeleniumUtils.sleepMillis(500);
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("Manage Screen", "Add"));
        SeleniumUtils.setText(driver, Locator.Assessments.AS_ATTR_NAME, "Audit Information");
        SeleniumUtils.setText(driver, Locator.Assessments.AS_ATTR_DESCR, "Audit Information Description");
        SeleniumUtils.clearThenSetText(driver, Locator.Assessments.AS_ATTR_ORDER, "2");
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_UPDATE);
        //Asserting added data
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Audit Information"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Audit Information Description"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("2"));


        /*----- Adding third Row -------*/
        SeleniumUtils.sleepMillis(500);
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("Manage Screen", "Add"));
        SeleniumUtils.setText(driver, Locator.Assessments.AS_ATTR_NAME, "Meter Reading");
        SeleniumUtils.setText(driver, Locator.Assessments.AS_ATTR_DESCR, "Meter Reading Description");
        SeleniumUtils.clearThenSetText(driver, Locator.Assessments.AS_ATTR_ORDER, "3");
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_UPDATE);
        //Asserting added data
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Meter Reading"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Meter Reading Description"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("3"));

        /*----- Adding fourth Row -------*/
        SeleniumUtils.sleepMillis(500);
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("Manage Screen", "Add"));
        SeleniumUtils.setText(driver, Locator.Assessments.AS_ATTR_NAME, "Review and Signature");
        SeleniumUtils.setText(driver, Locator.Assessments.AS_ATTR_DESCR, "Review and Signature Description");
        SeleniumUtils.clearThenSetText(driver, Locator.Assessments.AS_ATTR_ORDER, "4");
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_UPDATE);
        //Asserting added data
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Review and Signature"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Review and Signature Description"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("4"));



        /*--------- Saving the manage screen pop up -----------*/
        SeleniumUtils.waitForClickable(driver, Locator.Assessments.AS_ATTR_SAVE_BTN);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_ATTR_SAVE_BTN);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("Status","OK"));
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("Status","OK"));
       // SeleniumUtils.handleConfirmMsg(driver, "Confirm", "", "Yes");
       //SeleniumUtils.assertElementNotExists(driver, Locator.divText("Confirm"));

    }

    @Test(dependsOnMethods={"addAttributeGroups"})
    public void addAssociationType(){
        SeleniumUtils.waitForClickable(driver, Locator.Assessments.AS_ASS_TYPE);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_ASS_TYPE);
        
        // there is a loading box here that is hard to see since it is quite quick.
        // a test failed here due to element not clickable due to the ext mask (loading...)
        SeleniumUtils.sleepMillis(250);
        SeleniumUtils.waitForAllInVisible(driver, Locator.anyText("Loading..."));
        
        //Asserting UI elements
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Association Types"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("Association Types","Remove"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("Association Types","Save"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("Association Types","Cancel"));

        //Asserting first Row and not adding any more rows because it will be visible to all other programs
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("Association Types","Add"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("Association Types","Update"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("Association Types","Cancel"));
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("Association Types","Remove"));

        SeleniumUtils.waitForClickable(driver, Locator.Assessments.AS_ASS_SAVE);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_ASS_SAVE);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Changes saved successfully."));
        SeleniumUtils.handleConfirmMsg(driver, "Status", "Changes saved successfully.", "OK");
    }

    @Test(dependsOnMethods={"addAssociationType"})
    public void testAttributes(){
        SeleniumUtils.waitForClickable(driver, Locator.Assessments.AS_ATTR);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_ATTR);
        //Assertion on the page
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div[@id='summary']//input[@role='combobox']"));

        //By.xpath("//div[@id='summary']//*[text().='Select Screen:']/../..//input")

        SeleniumUtils.assertPageTitle(driver, "DSM Central: All Programs");
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.traksmartButtonAnchorStyle("Save"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.traksmartButtonAnchorStyle("Cancel"));

        //Going to Admin module to see if Space attributes exists.
        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_ADMIN, Locator.L_ADMIN_SYSTEM, "DSM Central: Administration");
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Administration");
        SeleniumUtils.doubleClick(driver, Locator.spanText("Attributes"));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.click(driver, Locator.spanText("Assessment Space Attributes"));

        //Adding the Assessment space Attributes in Admin module to be visible under Space dropdown.
        createAssessSpAttrIfNotExist("QA SP signature","QA SP signature desc", "FILE","signature","Default","FileUploadProperties");
        
        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_ADMIN, Locator.L_ADMIN_SYSTEM, "DSM Central: Administration");
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Administration");
        SeleniumUtils.doubleClick(driver, Locator.spanText("Attributes"));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.click(driver, Locator.spanText("Assessment Space Attributes"));
        createAssessSpAttrIfNotExist("QA SP numeric field","QA SP numeric field desc", "INTEGER","numeric field","Default","NumberProperties");
        
        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_ADMIN, Locator.L_ADMIN_SYSTEM, "DSM Central: Administration");
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Administration");
        SeleniumUtils.doubleClick(driver, Locator.spanText("Attributes"));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.click(driver, Locator.spanText("Assessment Space Attributes"));
        createAssessSpAttrIfNotExist("QA SP photo","QA SP photo desc", "FILE","photo","Default","FileUploadProperties");
        
        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_ADMIN, Locator.L_ADMIN_SYSTEM, "DSM Central: Administration");
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Administration");
        SeleniumUtils.doubleClick(driver, Locator.spanText("Attributes"));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.click(driver, Locator.spanText("Assessment Space Attributes"));
        createAssessSpAttrIfNotExist("QA SP text field","QA SP text field desc", "STRING","text field","Default","StringProperties");
        
        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_ADMIN, Locator.L_ADMIN_SYSTEM, "DSM Central: Administration");
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Administration");
        SeleniumUtils.doubleClick(driver, Locator.spanText("Attributes"));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.click(driver, Locator.spanText("Assessment Space Attributes"));
        createAssessSpAttrIfNotExist("QA SP text area field","QA SP text area field desc", "STRING","text area field","Default","StringProperties");
        
        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_ADMIN, Locator.L_ADMIN_SYSTEM, "DSM Central: Administration");
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Administration");
        SeleniumUtils.doubleClick(driver, Locator.spanText("Attributes"));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.click(driver, Locator.spanText("Assessment Space Attributes"));
        createAssessSpAttrIfNotExist("QA SP BigDecimal","QA SP BigDecimal desc", "BIGDECIMAL","numeric field","Default","NumberProperties");
        
        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_ADMIN, Locator.L_ADMIN_SYSTEM, "DSM Central: Administration");
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Administration");
        SeleniumUtils.doubleClick(driver, Locator.spanText("Attributes"));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.click(driver, Locator.spanText("Assessment Space Attributes"));
        createAssessSpAttrIfNotExist("QA SP Selection List", "QA SP Selection List desc", "STRING", "selectionList", "Default", "StringProperties", "Value 1", "Value Desc 1");

        //Coming back to Assessments programs
       SeleniumUtils.goToSubTab(driver, Locator.L_TAB_ASSESSMENTS, Locator.AS_ASSESSMENTS_ONSITE_PROGRAMS, "DSM Central: All Programs");
        SeleniumUtils.assertPageTitle(driver, "DSM Central: All Programs");
        //Search for the current program
        SeleniumUtils.setTextAndEnter(driver, Locator.inputWithName("keyword_search"),programName);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorText(programName));
        //Click on the program searched
        SeleniumUtils.clickUsingJavascript(driver, Locator.anchorText(programName));

        SeleniumUtils.waitForClickable(driver, Locator.Assessments.AS_ATTR);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_ATTR);

        SelectUtils.clickItemName(driver, By.xpath("//div[@id='summary']/div/div[1]/div[1]//input"), "Space Attributes");

        SeleniumUtils.sleepSecs(2);
        //Assertion
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.traksmartButtonAnchorStyle("Save"));

        //Drag and drop the attributes
        SeleniumUtils.dragAndDrop(driver, Locator.spanText("QA SP signature desc"), Locator.Assessments.AS_ATTR_DROP_TARGET);
        SeleniumUtils.sleepSecs(3);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("QA SP signature desc"));
        
        SeleniumUtils.dragAndDrop(driver, Locator.spanText("QA SP numeric field desc"), Locator.Assessments.AS_ATTR_DROP_TARGET);
        SeleniumUtils.sleepSecs(3);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("QA SP numeric field desc"));
        
        SeleniumUtils.dragAndDrop(driver, Locator.spanText("QA SP photo desc"), Locator.Assessments.AS_ATTR_DROP_TARGET);
        SeleniumUtils.sleepSecs(3);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("QA SP photo desc"));
        
        SeleniumUtils.dragAndDrop(driver, Locator.spanText("QA SP text field desc"), Locator.Assessments.AS_ATTR_DROP_TARGET);
        SeleniumUtils.sleepSecs(3);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("QA SP text field desc"));
        
        SeleniumUtils.dragAndDrop(driver, Locator.spanText("QA SP text area field desc"), Locator.Assessments.AS_ATTR_DROP_TARGET);
        SeleniumUtils.sleepSecs(3);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("QA SP text area field desc"));
        
        SeleniumUtils.dragAndDrop(driver, Locator.spanText("QA SP BigDecimal desc"), Locator.Assessments.AS_ATTR_DROP_TARGET);
        SeleniumUtils.sleepSecs(3);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("QA SP BigDecimal desc"));
        
        SeleniumUtils.dragAndDrop(driver, Locator.spanText("QA SP Selection List desc"), Locator.Assessments.AS_ATTR_DROP_TARGET);
        SeleniumUtils.sleepSecs(3);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("QA SP Selection List desc"));
        
        SeleniumUtils.setText(driver, Locator.Assessments.AS_ATTR_HINT_TEXT, "Hint");
        SeleniumUtils.waitForClickable(driver, Locator.Assessments.AS_ATTR_SAVE);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_ATTR_SAVE);
        SeleniumUtils.handleConfirmMsg(driver, "Status", "Changes saved successfully.", "OK");

    }

    private void createAssessSpAttrIfNotExist(String attrName, String label, String dataType, String uiElement, String category, String propertyConfig){
        createAssessSpAttrIfNotExist(attrName, label, dataType, uiElement ,category ,propertyConfig, null, null);
    }



    private void createAssessSpAttrIfNotExist(String attrName, String label, String dataType, String uiElement, String category, String propertyConfig, String value , String description ){
        // search for attribute
        SelectUtils.clickItemName(driver, By.xpath("//input[@id='admin_attributes_search_combobox-inputEl']"), "Attribute Name");
        SeleniumUtils.clearThenSetTextAndEnter(driver, By.xpath("//input[@role='textbox']"), attrName);
        SeleniumUtils.sleepSecs(2);

        //Getting value for Attribute from table
        int rowCount = driver.findElements(By.xpath("//div[@id='right-container']//table")).size();

        //Comparing the value to be not existing in the table
            if (rowCount < 1){
                //Create attribute
                SeleniumUtils.clickUsingJavascript(driver, Locator.traksmartButtonAnchorStyle("Add"));
                SeleniumUtils.setText(driver, Locator.inputWithName("attributeName"), attrName);
                SeleniumUtils.setText(driver, Locator.textareaWithName("description"), label);
                SeleniumUtils.setTextAndEnter(driver, Locator.inputWithName("dataType"), dataType);
                SeleniumUtils.clearThenSetTextAndEnter(driver, Locator.inputWithName("elementName"), uiElement);

                if (uiElement.equalsIgnoreCase("selectionList")){
                    SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanText("Options:"));
                    SeleniumUtils.clickUsingJavascript(driver, By.xpath("//span[text()[.='Options:']]/../../div/div/div[2]"));
                    SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanTextWithinPopupTitle("Options(Add/Update/Remove)","Add"));
                    SeleniumUtils.clickUsingJavascript(driver, Locator.spanTextWithinPopupTitle("Options(Add/Update/Remove)","Add"));
                    SeleniumUtils.assertElementExistsWithWait(driver, Locator.inputWithNameOnPopupTitle("Options(Add/Update/Remove)","lookupTypeValue") );
                    SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("Options(Add/Update/Remove)","lookupTypeValue"), value);
                    SeleniumUtils.setText(driver, Locator.inputWithNameOnPopupTitle("Options(Add/Update/Remove)","description"), description);
                    SeleniumUtils.clickUsingJavascript(driver, Locator.spanTextWithinPopupTitle("Options(Add/Update/Remove)","Apply"));
                }
                SeleniumUtils.clearThenSetTextAndEnter(driver, Locator.inputWithName("category"), category);
                SeleniumUtils.clearThenSetTextAndEnter(driver, Locator.inputWithName("configElement"), propertyConfig);
                //SeleniumUtils.assertElementExistsWithWait(driver, Locator.inputWithName(propertyConfig));
                SeleniumUtils.clickUsingJavascript(driver, Locator.traksmartButtonAnchorStyle("Create"));
                SeleniumUtils.handleConfirmMsg(driver, "Success", "Record Saved Successfully", "OK");
                SeleniumUtils.assertElementNotExists(driver, Locator.divText("Changes saved successfully."));
            }
            SeleniumUtils.sleepSecs(3);


    }




}
