package com.nexant.dsm.programs.process;

import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.dsm.libraries.measures.process.TestCreateCategoryTypeSubType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.webdriver.Driver;

import java.util.List;

/**
 * Test configuration of a program. 
 */
@Test(groups = {"Verify_Form_Config"})
public class TestProgramConfiguration {
    private static WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = Driver.get();
    }

    /**
     * DSMC-9883
     * Test form config assignment dialog.
     * For now this test just validates the dialog is there, and teh expected configuration elements are there.
     * (it is not testing a use case of assigning a form)
     * @throws Exception
     */
    @Test
    public void verifyFormConfigAssignment() throws Exception {
    	Assert.assertNotNull(TestAddNewProgram.savedProgramName, "required a saved program from TestAddNewProgram");

        TestAddNewProgram.openSavedProgram();

        //Click on the 'Forms Editor' link
        // SeleniumUtils.click(driver, Locator.anchorText("Forms Editor"));
        SeleniumUtils.clickUsingJavascript(driver, Locator.anchorTextContains("Forms Editor"));
        //Click on the link to open Form Assignment Configuration Window
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorText(TestAddNewProgram.savedProgramName));
        SeleniumUtils.clickUsingJavascript(driver, Locator.traksmartButtonAnchorStyle("Form Configurations"));
		SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanText("Configure Form Assignment"));
		SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("Configure Form Assignment"));
        //Select the value in all the 3 drop downs
        SelectUtils.clickItemName(driver, Locator.FormAssignment.TYPE, "Auto");
        SelectUtils.clickItemName(driver, Locator.FormAssignment.AUTO, "Distribute across Project Managers");
        SelectUtils.clickItemName(driver, Locator.FormAssignment.MANUAL, "Project Managers");
        //Click on Apply
        SeleniumUtils.click(driver, Locator.FormAssignment.APPLY_BTN);
    }


    /**
     * Test program measures assignment.
     * This will assign a saved measure to the program from the measures tab of the program summary.
     */
    @Test
    public void manageProgramMeasure() {
    	Assert.assertNotNull(TestAddNewProgram.savedProgramName, "This test requires a saved program from TestAddNewProgram");
    	Assert.assertNotNull(TestCreateCategoryTypeSubType.savedCategoryName, "This test requires a saved measure from TestCreateCategoryTypeSubType");

        TestAddNewProgram.openSavedProgram();

        SeleniumUtils.scrollToElement(driver, By.id("ameasures"));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.click(driver, By.id("ameasures")); // Measures Tab

        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        // click on Manage Measures button
        SeleniumUtils.click(driver, Locator.anchorText("Manage Measures"));
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Program Measures");
        // check we are on the Program Measures page
        SeleniumUtils.waitForElement(driver, Locator.divText("Program Measures"));

        SeleniumUtils.sleepSecs(2);

        Assert.assertNotNull(TestCreateCategoryTypeSubType.savedCategoryName, "Category cannot be null.");
        SelectUtils.clickItemOfDropDownWithTextInput(driver, By.id("measure_category_field-inputEl"), TestCreateCategoryTypeSubType.savedCategoryName);
        SeleniumUtils.sleepSecs(1);
        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, By.id("measure_type_field-inputEl"));
        SeleniumUtils.sleepSecs(1);
        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, By.id("measure_subtype_field-inputEl"));

        Assert.assertNotNull(TestCreateCategoryTypeSubType.savedMeasureName, "Measure Name cannot be null.");
        SeleniumUtils.setText(driver, By.id("measure_name1_field-inputEl"), TestCreateCategoryTypeSubType.savedMeasureName);	
        
        // click on search
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Search"));

        // click on the first measure in the measure list. This will send the measure to the right side box
        SeleniumUtils.click(driver, By.xpath("//div[@id='measure_list']//div/table[1]/tbody/tr"));

        SeleniumUtils.click(driver, By.xpath("//div[@id='name_buttons_ro']/input[1]")); // click on save button

        SeleniumUtils.handleConfirmMsg(driver, "Please Confirm", "Are you sure you want to save program measure association?", "OK");
        SeleniumUtils.handleConfirmMsg(driver, "Save Program Measure Associations Successful", "Measures save succeeded.", "OK");

        SeleniumUtils.assertPageTitle(driver, "DSM Central: Program Detail", 30);

        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.scrollToElement(driver, By.id("ameasures"));
        SeleniumUtils.sleepSecs(1);

        List<WebElement> l = SeleniumUtils.waitForElements(driver, By.xpath("//div[@id='structures']//div/table"));

        Assert.assertTrue(l != null && l.size() > 0);
        SeleniumUtils.sleepSecs(1);

    }

    
    public static String savedEmailNotification = null;
    
    /**
     * Test creating an email template for a program
     */
    @Test
    public void testAddEmailNotification() {
    	
    	Assert.assertNotNull(TestAddNewProgram.savedProgramName, "This test requires a saved program from TestAddNewProgram");

        TestAddNewProgram.openSavedProgram();

        SeleniumUtils.click(driver, Locator.anchorWithSpanText("Emails/Notifications"));

        SeleniumUtils.assertElementIsClickable(driver, Locator.anchorText(" Map Tokens "));
        SeleniumUtils.assertElementIsClickable(driver, Locator.anchorText("+ Add Notification "));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.containsTextWithinId("structures", "Email"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.containsTextWithinId("structures", "To"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.containsTextWithinId("structures", "Trigger"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.containsTextWithinId("structures", "Last Activity"));

        SeleniumUtils.click(driver, Locator.anchorText("+ Add Notification "));

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anyText("Configure New Email Notification"));
        SeleniumUtils.assertElementIsClickable(driver, Locator.traksmartButtonAnchorStyle("Save"));
        SeleniumUtils.assertElementIsClickable(driver, Locator.traksmartButtonAnchorStyle("Cancel"));


        String name = "testnotif" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.inputWithName("emailName"), name);
        SelectUtils.clickItemName(driver, Locator.inputWithName("to_user_group_field_combobox-inputEl"), "Other Emails");
        SeleniumUtils.setText(driver, Locator.inputWithName("to_other_email_field"), "unittest@nexant.com");
        SeleniumUtils.setText(driver, Locator.inputWithName("subject"), "test subject");
        SeleniumUtils.setText(driver, Locator.textareaWithName("emailBody"), "<html>testing html email</html>");

        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Save"));

        SeleniumUtils.assertPageTitle(driver, "DSM Central: Program Detail");
        SeleniumUtils.assertElementIsClickable(driver, Locator.anchorText(name));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anyText("[unittest@nexant.com]"));

        savedEmailNotification = name;
    }


    
    public static String savedFormula = null;
    
    /**
     * Test creating a formula for a program
     */
    @Test
    public void testAddFormula() {
    	
    	Assert.assertNotNull(TestAddNewProgram.savedProgramName, "This test requires a saved program from TestAddNewProgram");

        TestAddNewProgram.openSavedProgram();

        SeleniumUtils.click(driver, Locator.anchorWithSpanTextWithinId("structure", "Formulas"));
        SeleniumUtils.sleepMillis(500);

        SeleniumUtils.assertElementIsClickable(driver, Locator.anchorTextWithinId("structures", " Map Tokens "));
        SeleniumUtils.assertElementIsClickable(driver, Locator.anchorTextWithinId("structures", "+ Add Formula "));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.containsTextWithinId("structures", "Formula"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.containsTextWithinId("structures", "Formula Type"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.containsTextWithinId("structures", "Formula Definition"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.containsTextWithinId("structures", "Last Activity"));

        SeleniumUtils.click(driver, Locator.anchorTextWithinId("structure", "+ Add Formula "));

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anyText("New Formula"));
        SeleniumUtils.assertElementIsClickable(driver, Locator.inputWithValue("Save"));
        SeleniumUtils.assertElementCount(driver, Locator.inputWithValue("Save"), 2); // there should actually be 2 save buttons (top and bottom)
        SeleniumUtils.assertElementIsClickable(driver, Locator.inputWithValue("Cancel"));
        SeleniumUtils.assertElementCount(driver, Locator.inputWithValue("Cancel"), 2); // there should actually be 2 save buttons (top and bottom)


        String name = "testformula" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.inputWithName("formulaName"), name);
        SeleniumUtils.setText(driver, Locator.textareaWithName("formulaField-inputEl"), "1 + 1");

        // test the calculate button
        SeleniumUtils.click(driver, Locator.anchorText("Calculate"));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.assertElementExists(driver, Locator.textareaWithName("formulaResultField-inputEl"));
        // the value in EXT actually does not go into the textarea like html, it is found in the title attribute of a div!??
        SeleniumUtils.waitForElement(driver, Locator.byIdAndTitle("formulaResultField", "2"));

        // save
        SeleniumUtils.click(driver, Locator.inputWithValue("Save"));

        SeleniumUtils.assertPageTitle(driver, "DSM Central: Program Detail");
        SeleniumUtils.assertElementIsClickable(driver, Locator.anchorText(name));

        savedFormula = name;
    }

    /**
     * Test the stats and versions tab. 
     * This should come after adding a new program (TestAddNewProgram), and the above method to add a formula.
     */
    @Test
    public void testStatsAndVersionsTab() {

    	Assert.assertNotNull(TestAddNewProgram.savedProgramName, "This test requires a saved program from TestAddNewProgram");
        TestAddNewProgram.openSavedProgram();

    	SeleniumUtils.click(driver, Locator.anchorTextWithinDivId("PA_natigator", "Stats & Versions"));
    	SeleniumUtils.assertPageTitle(driver, "DSM Central: Stats & Versions");

    	// check the grid columns:
    	SeleniumUtils.waitForClickable(driver, Locator.anyTextWithinId("program_list", "Program Name"));
    	SeleniumUtils.waitForClickable(driver, Locator.anyTextWithinId("program_list", "Version"));
    	SeleniumUtils.waitForClickable(driver, Locator.anyTextWithinId("program_list", "Status"));
    	SeleniumUtils.waitForClickable(driver, Locator.anyTextWithinId("program_list", "Last Update"));
    	SeleniumUtils.waitForClickable(driver, Locator.anyTextWithinId("program_list", "Business Process ID"));

    	// check our program name exists in the table
    	SeleniumUtils.waitForClickable(driver, Locator.anchorTextContainsWithinId("program_list", TestAddNewProgram.savedProgramName));
    }

    /**
     * Test the structure tab. At this point there is no worlflow image.
     * But we want to ensure the links a there and working.
     * This should come after adding a new program (TestAddNewProgram), and the above method to add a formula.
     */
    @Test
    public void testStructureTab() {

    	Assert.assertNotNull(TestAddNewProgram.savedProgramName, "This test requires a saved program from TestAddNewProgram");
    	Assert.assertNotNull(savedFormula, "This test requires a saved formula from above");

        TestAddNewProgram.openSavedProgram();

    	SeleniumUtils.click(driver, Locator.anchorTextWithinDivId("PA_natigator", "Structure"));
    	SeleniumUtils.assertPageTitle(driver, "DSM Central: Program Structure");

    	SeleniumUtils.waitForElement(driver, Locator.h2Text("Structure"));

    	// verify we have 4 links at the top, with a "Launch:" prefix
    	SeleniumUtils.waitForClickable(driver, Locator.anyText("Launch:"));
    	SeleniumUtils.waitForClickable(driver, Locator.anchorText("Program Editor"));
    	SeleniumUtils.waitForClickable(driver, Locator.anchorText("Forms Preview"));
    	SeleniumUtils.waitForClickable(driver, Locator.anchorText("Export Upload Template"));
    	SeleniumUtils.waitForClickable(driver, Locator.anchorText("Export Void Template"));

    	// verify we have 5 tabs at the bottom
    	SeleniumUtils.waitForClickable(driver, Locator.anyTextWithinId("structure_tab_bar", "Forms"));
    	SeleniumUtils.waitForClickable(driver, Locator.anyTextWithinId("structure_tab_bar", "Measures"));
    	SeleniumUtils.waitForClickable(driver, Locator.anyTextWithinId("structure_tab_bar", "Formulas"));
    	SeleniumUtils.waitForClickable(driver, Locator.anyTextWithinId("structure_tab_bar", "Emails/Notifications"));
    	SeleniumUtils.waitForClickable(driver, Locator.anyTextWithinId("structure_tab_bar", "Templates"));


    	// click on the measures and verify page elements
    	SeleniumUtils.click(driver, Locator.anchorWithSpanTextWithinId("structure_tab_bar", "Measures"));
    	SeleniumUtils.sleepSecs(1);

    	// verify we have 1 link at the top, with a "Manage:" prefix
    	SeleniumUtils.waitForClickable(driver, Locator.textWithinId("tab_menu", "Manage:"));
    	SeleniumUtils.waitForClickable(driver, Locator.anchorTextWithinId("tab_menu", "Measures"));

    	// and one link under the measures tab
    	SeleniumUtils.waitForClickable(driver, Locator.anchorTextWithinId("structures", "Manage Measures"));



    	// click on the formulas and verify page elements
    	SeleniumUtils.click(driver, Locator.anchorWithSpanTextWithinId("structure_tab_bar", "Formulas"));
    	SeleniumUtils.sleepSecs(1);

    	// verify we have 2 link at the top, with a "Manage:" prefix
    	SeleniumUtils.waitForClickable(driver, Locator.anchorTextWithinId("tab_menu", "+ Add Formula "));
    	SeleniumUtils.waitForClickable(driver, Locator.anchorTextWithinId("tab_menu", " Map Tokens "));

    	// verify we have 2 links for the fomulas
    	SeleniumUtils.waitForClickable(driver, Locator.anchorTextWithinId("structures", "+ Add Formula "));
    	SeleniumUtils.waitForClickable(driver, Locator.anchorTextWithinId("structures", " Map Tokens "));

    	// DSMC-11987 - verify the links
    	SeleniumUtils.click(driver, Locator.anchorTextWithinId("tab_menu", "+ Add Formula "));
    	SeleniumUtils.waitForElement(driver, Locator.divText("New Formula"));
    	SeleniumUtils.click(driver, Locator.inputWithValue("Cancel"));
    	SeleniumUtils.waitForClickable(driver, Locator.anchorTextWithinId("tab_menu", "+ Add Formula ")); // should be back on the formulas tab

    	SeleniumUtils.click(driver, Locator.anchorTextWithinId("tab_menu", " Map Tokens "));
    	SeleniumUtils.waitForClickable(driver, Locator.spanTextWithinId("token_list", "Token"));
    	SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Back"));
    	SeleniumUtils.waitForClickable(driver, Locator.anchorTextWithinId("tab_menu", "+ Add Formula ")); // should be back on the formulas tab

    	SeleniumUtils.click(driver, Locator.anchorTextWithinId("structures", "+ Add Formula "));
    	SeleniumUtils.waitForElement(driver, Locator.divText("New Formula"));
    	SeleniumUtils.click(driver, Locator.inputWithValue("Cancel"));
    	SeleniumUtils.waitForClickable(driver, Locator.anchorTextWithinId("tab_menu", "+ Add Formula ")); // should be back on the formulas tab

    	SeleniumUtils.click(driver, Locator.anchorTextWithinId("structures", " Map Tokens "));
    	SeleniumUtils.waitForClickable(driver, Locator.spanTextWithinId("token_list", "Token"));
    	SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Back"));
    	SeleniumUtils.waitForClickable(driver, Locator.anchorTextWithinId("tab_menu", "+ Add Formula ")); // should be back on the formulas tab

    }
}
