package com.nexant.dsm.programs.process;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.dsm.libraries.measures.process.TestCreateCategoryTypeSubType;
import com.nexant.webdriver.Driver;

@Test(groups = {"CreateFormWorkflow"})
public class TestCreateForm {
    private static WebDriver driver;
    public static String savedFormName1 = "f1";
    public static String savedFormName2 = "f2";
    public static String savedFormTitle = "Test Form";
    //public static String savedTaskName = "Form1";
    public static String savedTaskName = "Test Task";
    public static String incentiveAttribute="Incentive - Customer:";

    @BeforeMethod
    public void setup() {
        driver = Driver.get();
        TestAddNewProgram.openSavedProgram();
    }

    @Test
    public void createBasicForm1() {
        openFormsEditor(TestAddNewProgram.savedProgramName);
        setBasicFormData(savedFormName1, savedFormTitle);
        saveForm(TestAddNewProgram.savedProgramName, savedFormName1);
    }
    
    @Test(dependsOnMethods={"createBasicForm1"})
    public void createBasicForm2() {
        openFormsEditor(TestAddNewProgram.savedProgramName);
        setBasicFormData(savedFormName2, savedFormTitle);
        saveForm(TestAddNewProgram.savedProgramName, savedFormName2);
    }

    @Test(dependsOnMethods={"createBasicForm2"})
    public void editFormDefinition2() {
        addFormData(TestAddNewProgram.savedProgramName, savedFormName2);
        configureMeasureAndButtons(TestAddNewProgram.savedProgramName, savedFormName2);
    }

	@Test(dependsOnMethods = "editFormDefinition2")
	/**
	 * To test the form preview.
	 * Open the form definition, click the preview button and check all the components added are there.
	 * Also check that save button is disabled
	 */
	public void testFormPreview() {
		openFormsEditor(TestAddNewProgram.savedProgramName);
		SelectUtils.clickItemOfDropDownWithTextInput(driver, By.id("north_region_form_combo-inputEl"), savedFormName2);
		SeleniumUtils.waitForInVisible(driver, Locator.divText("Working..."));

		SeleniumUtils.sleepSecs(2);
		SeleniumUtils.click(driver, By.id("form_desgin_button_preview"));

		SeleniumUtils.sleepSecs(2);

		//assert that we have all the sections visible in preview
		SeleniumUtils.waitForVisible(driver, Locator.divText("Begin Measure Group"));
		SeleniumUtils.waitForVisible(driver, Locator.divText("Customer"));
		SeleniumUtils.waitForVisible(driver, Locator.divText("Partner"));
		SeleniumUtils.waitForVisible(driver, Locator.divText("Payee"));
		SeleniumUtils.waitForVisible(driver, Locator.divText(TestCreateCategoryTypeSubType.savedMeasureName));

		//save button should be disabled!!
		WebElement saveButton = driver.findElement(Locator.traksmartButtonAnchorStyle("Save"));
		String cssClass = saveButton.getAttribute("class");
		Assert.assertTrue("Save Button should be disabled", cssClass.contains("x-btn-disabled"));

		//click back button
		SeleniumUtils.click(driver, Locator.inputWithValue("Back"));

	}

    public void openFormsEditor(String programName) {
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorText(programName));
        //Click on the 'Forms Editor' link
        SeleniumUtils.clickUsingJavascript(driver, Locator.anchorTextContains("Forms Editor"));

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorText(programName));
        //Click on the link to open Form Assignment Configuration Window
        SeleniumUtils.clickUsingJavascript(driver, Locator.anchorTextContains("+ Add New Form"));

        // Wait while page loading. This pops up while loading
        SeleniumUtils.sleepMillis(100);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Working..."));
        SeleniumUtils.sleepSecs(1);
    }

    public void saveForm(String programName, String formName) {
        //Click on Save
        SeleniumUtils.click(driver, By.id("saveButton"));
        SeleniumUtils.sleepMillis(500); // wait a bit here so the working box has time to come up
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Working..."), 90);

        //Wait for the confirmation message
        SeleniumUtils.waitForElement(driver, Locator.divText("Form Saved Successfully"));

        
        // click back to the program (not sure why it would be done here...?)
        SeleniumUtils.click(driver, Locator.anchorTextContains(programName));

        // verify we are back on the program page
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorWithSpanText("Forms"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorWithSpanText("Measures"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorWithSpanText("Formulas"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorWithSpanText("Emails/Notifications"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorWithSpanText("Templates"));
        
        // verify the form shows up in the bottom list
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.containsTextWithinId("structures", formName));
    }

    public void setBasicFormData(String formName, String formTitle) {

        //Click on "page title" to set form Name and Title
        SeleniumUtils.click(driver, Locator.divText("Page Title"));

        SeleniumUtils.setFormPropertyText(driver, "FormName", formName);

        // click on the + sign to expand the title section
        SeleniumUtils.click(driver, By.xpath("//div[text()[.='Page Title']]/../../div[2]"));
        SeleniumUtils.sleepSecs(1);
        // click on the page title in the middle box
        SeleniumUtils.click(driver, By.xpath("//div[text()[.='Page Title'] and contains(@class, 'formAssembly_title')]"));

        SeleniumUtils.setFormPropertyText(driver, "Title", formTitle);
    }
    

    public void addFormData(String programName, String formName) {

        openFormsEditor(programName);

        SelectUtils.clickItemOfDropDownWithTextInput(driver, By.id("north_region_form_combo-inputEl"), formName);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Working..."));

        SeleniumUtils.sleepSecs(2);

        SeleniumUtils.click(driver, Locator.divText("Begin Measure Group"));
        SeleniumUtils.click(driver, Locator.divText("End Measure Group"));

        SeleniumUtils.click(driver, Locator.divText("Customer"));
        SeleniumUtils.click(driver, Locator.divText("Partner"));
        SeleniumUtils.click(driver, Locator.divText("Payee"));

        SeleniumUtils.sleepMillis(200);
        SeleniumUtils.rightClick(driver, Locator.divText("Payee"));
        SeleniumUtils.sleepMillis(200);
        SeleniumUtils.click(driver, Locator.spanText("Add to top"));

        SeleniumUtils.waitForInVisible(driver, Locator.divText("Working..."));

        SeleniumUtils.sleepMillis(200);
        //click on the already dragged begin measure
        SeleniumUtils.click(driver, By.xpath("//div[@id='webFormDesigner_center_regionId-body']//div[text()[.='Begin Measure Group']]"));
        SeleniumUtils.sleepMillis(200);
        // select a measure to be added between begin measure and end measure
        By measureLocation = By.xpath("//div[@id='programElementMeasuresListBoxId-body']//table/tbody/tr");
        SeleniumUtils.click(driver, measureLocation);

        SeleniumUtils.sleepMillis(200);
        SeleniumUtils.rightClick(driver, measureLocation);
        SeleniumUtils.sleepMillis(200);
        SeleniumUtils.click(driver, Locator.spanText("Add after selection"));
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Working..."));

        SeleniumUtils.sleepSecs(1);

        saveForm(programName, formName);

    }
    
    public void configureMeasureAndButtons(String programName, String formName) {
    	if(TestCreateCategoryTypeSubType.savedMeasureName == null) throw new RuntimeException("depend on a saved measure from TestCreateCategoryTypeSubType");
    	
        openFormsEditor(programName);

        SelectUtils.clickItemOfDropDownWithTextInput(driver, By.id("north_region_form_combo-inputEl"), formName);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Working..."));
        SeleniumUtils.sleepSecs(2);

        //Configure Measure
        SeleniumUtils.click(driver, By.xpath("//div[@id='webFormDesigner_center_regionId-body']//div[text()[.='"+TestCreateCategoryTypeSubType.savedMeasureName+"']]"));
        
        //click on the + sign to expand the title section
        SeleniumUtils.click(driver, By.xpath("//div[@id='webFormDesigner_center_regionId-body']//div[text()[.='"+TestCreateCategoryTypeSubType.savedMeasureName+"']]/../../div[8]"));
        SeleniumUtils.sleepSecs(1);
        //click on the incentive in the middle box
        SeleniumUtils.click(driver, By.xpath("//label[text()[.='"+incentiveAttribute+"'] and contains(@class, 'formAssembly_label')]"));

        //Set Pay
        SeleniumUtils.sleepMillis(300);   
        SeleniumUtils.setFormPropertyText(driver, "Pay", "TRUE");
        
       
        //Set Payee 
        SeleniumUtils.sleepMillis(300);        
        SeleniumUtils.setFormPropertyText(driver, "Payee", savedFormName2+":Payee");
        
        
        
        //Set Payment Type
        SeleniumUtils.sleepMillis(300);        
        SeleniumUtils.setFormPropertyText(driver, "PaymentType", "Check");
        
        //Set Accounting Code
        SeleniumUtils.click(driver, By.xpath("//div[text()[.='AccountingCode']]/../../td[2]/div"));
        WebElement el = driver.switchTo().activeElement();
        el.sendKeys(Keys.RETURN);
        
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.click(driver, Locator.divText(Locator.Text.DESCRIPTION));
        SeleniumUtils.sleepSecs(1);
        //Click Apply
        SeleniumUtils.clickUsingJavascript(driver, Locator.inputWithValue("Apply"));
        
        //click on the - sign to collapse the title section
        SeleniumUtils.click(driver, By.xpath("//div[@id='webFormDesigner_center_regionId-body']//div[text()[.='"+TestCreateCategoryTypeSubType.savedMeasureName+"']]/../../div[8]"));
        SeleniumUtils.sleepSecs(1);
        
        //Configure Buttons
        SeleniumUtils.click(driver, By.xpath("//div[@id='webFormDesigner_center_regionId-body']//div[text()[.='Buttons']]"));
        SeleniumUtils.sleepSecs(1);
        
        //click on the + sign to expand the Buttons section
        //SeleniumUtils.click(driver, By.xpath("//div[text()[.='Buttons']]/../../div[2]"));
        SeleniumUtils.click(driver, By.xpath("//div[@id='webFormDesigner_center_regionId-body']//div[text()[.='Buttons']]/../../div[2]"));
        SeleniumUtils.sleepSecs(1);
        
        //click on the div in the middle box
        SeleniumUtils.clickUsingJavascript(driver, By.xpath("//button[text()[.='Save'] and contains(@class, 'formAssembly_button_save')]/../../.."));

        //Enable Request Payment 
        SeleniumUtils.sleepMillis(300);        
        SeleniumUtils.setFormPropertyText(driver, "DisplayRequestPaymentButton", "TRUE");
        
        saveForm(programName, formName);
    }
}
