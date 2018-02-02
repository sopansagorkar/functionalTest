package com.nexant.dsm.programs.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.dsm.libraries.measures.process.TestCreateCategoryTypeSubType;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TestCreateProject {
    private static WebDriver driver;

    @BeforeMethod(dependsOnGroups={"TestCreateWorkflow"})
    public void setup() {
        driver = Driver.get();
        TestAddNewProgram.openSavedProgram();
    }

    private static void refreshProjectTasks() {

        // wait for specified seconds to see if the project task is present or click refresh.
        for (int i = 0; i < 15; i++) {
            if (SeleniumUtils.waitForElementWithoutException(driver, Locator.anchorText(TestCreateForm.savedTaskName), 3) == null) {
                SeleniumUtils.click(driver, By.xpath("//input[@value='Refresh']"));
            }
        }
    }

    @Test
    public void createNewProject() {

        SeleniumUtils.click(driver, By.xpath("//input[@value = '+ Create A New Project']"));
        SeleniumUtils.sleepMillis(200);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Project Detail", 30);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.h2Text("Project Tasks"));
    }

    
    @Test
    public void fillFormData() {
    	if(TestCreateForm.savedTaskName == null) throw new RuntimeException("this test depends on a saved form from TestCreateForm");
    	if(TestCreateCategoryTypeSubType.savedMeasureName == null) throw new RuntimeException("this test depends on a saved measure from TestCreateCategoryTypeSubType");
    		
    	By by = By.xpath("//div[@id='projects']//a[text()[contains(.,'" + Locator.Programs.Project_Number_Prefix_Name + "')]]");
        List<WebElement> projects = SeleniumUtils.waitForElements(driver, by);

        for (int i = 0; i < 10; i++) {
            projects = SeleniumUtils.waitForElements(driver, by);
            if (projects.size() == 0) {
                SeleniumUtils.sleepSecs(1);
                SeleniumUtils.click(driver, Locator.spanTextContains("IN PROGRESS("));
            }
        }

        Assert.assertNotEquals(projects.size(), 0, "There should be at least one project to fill the data and complete");

        WebElement projectLink = projects.get(0);

        SeleniumUtils.waitForClickable(driver, projectLink);
       // projectLink.click();

        SeleniumUtils.clickUsingJavascript(driver, projectLink);

        SeleniumUtils.assertPageTitle(driver, "DSM Central: Project Detail", 30);

        refreshProjectTasks();

        SeleniumUtils.waitForClickable(driver, Locator.anchorText(TestCreateForm.savedTaskName));

        SeleniumUtils.sleepSecs(1);

        SeleniumUtils.click(driver, Locator.anchorText(TestCreateForm.savedTaskName));

        SeleniumUtils.assertPageTitle(driver, "DSM Central: Edit Task", 90);
        SeleniumUtils.waitOnProgressBar(driver, 30);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.h2TextContains(TestCreateForm.savedTaskName));

        //Check if the button with the label Save and Create New Project exists on the page
        SeleniumUtils.assertElementIsClickable(driver, Locator.traksmartButtonAnchorStyle("Save and Create New Project"));
        
        //Check if 2 Save and Create New Project buttons exists on the page
        List saveButtons = driver.findElements(Locator.traksmartButtonAnchorStyle("Save and Create New Project"));
        int size = saveButtons.size();
        Assert.assertEquals(size, 2, "There should be two buttons on this page, one on the top and one on the bottom");
        
        
        //////////// test the files button //////////////
        //Check if the button with the label Files exists on the page

		//first open the more toolbar to get the Files button
		SeleniumUtils.assertElementIsClickable(driver, By.id("runtime_more_button"));
		SeleniumUtils.click(driver, By.id("runtime_more_button"));
		SeleniumUtils.sleepSecs(1);

        SeleniumUtils.assertElementIsClickable(driver, Locator.traksmartButtonAnchorStyle("Files"));
        
        //Click the Files button
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Files"));

        // check elements
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.textWithinId("projectfilespopup", "Files"), "missing popup title which should be 'Files'");
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanTextWithinId("projectfilespopup", "File Name"), "missing the table header called 'File Name'");
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanTextWithinId("projectfilespopup", "Description"), "missing the table header called 'Description'");
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanTextWithinId("projectfilespopup", "Categories"), "missing the table header called 'Categories'");
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanTextWithinId("projectfilespopup", "Last Updated"), "missing the table header called 'Last Updated'");

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divTextWithinId("projectfilespopup", "Add File ?"), "missing the add file label");
        
        //Click Add File checkbox in the pop up
        SeleniumUtils.click(driver, By.xpath("//div[@id='projectfilespopup']//div[text()[.='Add File ?']]/..//input"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.inputWithNameAndTypeWithinElementId("projectfilespopup", "fileName", "file"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.textareaWithNameWithinElementId("projectfilespopup", "documentDescription"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.inputWithNameWithinElementId("projectfilespopup", "pf_categoryPanel_combobox-inputEl"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.inputWithNameAndTypeWithinElementId("projectfilespopup", "pf_newCategory-inputEl", "text"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.buttonWithTextWithinId("projectfilespopup", "Add New Category"));
        SeleniumUtils.assertElementIsClickable(driver, Locator.traksmartButtonAnchorStyleOnPopupId("projectfilespopup", "Upload"));
        SeleniumUtils.assertElementIsClickable(driver, Locator.traksmartButtonAnchorStyleOnPopupId("projectfilespopup", "Cancel"));
        
        //Close the popup
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopupId("projectfilespopup", "Cancel"));
        
        
        
       //Select the measure 
        SeleniumUtils.click(driver, Locator.divText(TestCreateCategoryTypeSubType.savedMeasureName));
        SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("Add"));
        
        
        //Add value of Incentive Customer
        SeleniumUtils.setText(driver, By.xpath("//label/span[text()[contains(.,'Incentive - Customer:')]]/../..//input[@type='text']"), "10");

        
        //Fill Company Name in Payee Section        
        SeleniumUtils.setText(driver, By.xpath("//div[text()[.='Payee']]/../../../../..//span[text()[contains(.,'Company Name:')]]/../..//input[@type='text']"), "ABC Test Company");

        //Save the details filled in the form
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Save"));
        
        

        // wait a bit for the saving box, and wait for it to go away, saving a form may take some time
        SeleniumUtils.sleepSecs(2);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Saving application, please wait..."), 120);
        
        // wait a bit more
        SeleniumUtils.sleepSecs(1);

        //Do Request Payment
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Request Payment"));
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Processing action, please wait..."), 120);

        // partial payment popup - verify all the elements of the popup
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Payment Selection"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divTextWithinPopupTitle("Payment Selection", "Group By : "));
        SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("Payment Selection", "Measure Name"));
        SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("Payment Selection", "Payee"));
        SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("Payment Selection", "Submit"));
        SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("Payment Selection", "Cancel"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.textWithinPopupTitle("Payment Selection", "Attribute Name"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.textWithinPopupTitle("Payment Selection", "Amount"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.textWithinPopupTitle("Payment Selection", "Payee"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.textWithinPopupTitle("Payment Selection", TestCreateCategoryTypeSubType.savedMeasureName));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.textWithinPopupTitle("Payment Selection", "Incentive - Customer"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.textWithinPopupTitle("Payment Selection", "10"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.textWithinPopupTitle("Payment Selection", "ABC Test Company: "));
        
        //Click on submit
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopupTitle("Payment Selection", "Submit"));
        
        // click OK
        SeleniumUtils.handleConfirmMsg(driver, "Message", "Payment requested successfully", "OK"); 
        
        // also need to wait for the page refresh
        SeleniumUtils.sleepSecs(5);
        
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."), 120);
    }

    @Test
    public void completeProject() {
    	if(TestCreateForm.savedTaskName == null) throw new RuntimeException("this test depends on a saved form from TestCreateForm");

    	By by = By.xpath("//div[@id='projects']//a[text()[contains(.,'" + Locator.Programs.Project_Number_Prefix_Name + "')]]");
        List<WebElement> projects = SeleniumUtils.waitForElements(driver, by);

        for (int i = 0; i < 10; i++) {
            projects = SeleniumUtils.waitForElements(driver, by);
            if (projects.size() == 0) {
                SeleniumUtils.sleepSecs(1);
                SeleniumUtils.click(driver, Locator.spanTextContains("IN PROGRESS("));
            }
        }


        WebElement projectLink = projects.get(0);

        SeleniumUtils.waitForClickable(driver, projectLink);
       // projectLink.click();

        SeleniumUtils.clickUsingJavascript(driver, projectLink);

        SeleniumUtils.assertPageTitle(driver, "DSM Central: Project Detail", 30);

        refreshProjectTasks();

        SeleniumUtils.waitForClickable(driver, Locator.anchorText(TestCreateForm.savedTaskName));

        SeleniumUtils.click(driver, Locator.anchorText(TestCreateForm.savedTaskName));

        SeleniumUtils.assertPageTitle(driver, "DSM Central: Edit Task");
        SeleniumUtils.waitOnProgressBar(driver, 15);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.h2TextContains(TestCreateForm.savedTaskName));

        
        //Select value of the Status as Completed
    	  SelectUtils.clickItemOfDropDownWithTextInput(driver, By.xpath("//label/span[text()[contains(.,'Status:')]]/../..//input[@type='text']"), "Completed");
          SeleniumUtils.sleepSecs(1);
          
          SeleniumUtils.clickUsingJavascript(driver, Locator.traksmartButtonAnchorStyle("Save"));

          SeleniumUtils.sleepSecs(2);

          // pre 6.3, wait a bit for the saving box, and wait for it to go away, saving a form may take some time
          // SeleniumUtils.waitForInVisible(driver, Locator.divText("Saving application, please wait..."), 90);
          //SeleniumUtils.waitOnProgressBar(driver, 30);
          
          SeleniumUtils.assertPageTitle(driver, "DSM Central: Project Detail", 120);
          SeleniumUtils.sleepSecs(1);

          refreshProjectTasks();

          SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//*[@id='summary']//div/b[text()[.='Completed']]"), "expected the Completed status in a bold tag");
          SeleniumUtils.sleepSecs(1);

    }
    	
}
