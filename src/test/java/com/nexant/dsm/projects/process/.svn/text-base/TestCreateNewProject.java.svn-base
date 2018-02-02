package com.nexant.dsm.projects.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.dsm.programs.process.TestAddNewProgram;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

/**
 * Created by tshakirov on 11/8/15.
 */
public class TestCreateNewProject {

    private static WebDriver driver;
    private static String savedProgramName = null;
    private static String path = null;
    private static File dir = null;
    private static String projectName = null;

    @BeforeClass
    public void setup() {
        driver = Driver.get();
//        if(TestAddNewProgram.savedProgramName != null){
//            savedProgramName = TestAddNewProgram.savedProgramName;
//        }else {
        savedProgramName = "Commercial Electric Vehicle Charging Station";
//        }
        dir = new File("data");
        path = dir.getAbsolutePath();
    }

    @Test
    public void navigateToProjects() {
        SeleniumUtils.goToTab(driver, Locator.L_TAB_PROJECTS, "DSM Central: All Projects");
        SeleniumUtils.click(driver, By.xpath("//input[@value = '+ Create A New Project']"));
        SeleniumUtils.sleepMillis(200);
        SelectUtils.enterTextToSelect(driver, By.xpath("//input[@name='programId']"), savedProgramName);
        SeleniumUtils.sleepMillis(200);
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyleOnPopup("OK"));
        SeleniumUtils.sleepMillis(200);
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Edit Task", 30);
    }

    @Test(dependsOnMethods = { "navigateToProjects" })
    public void fillFormData() {
            SeleniumUtils.assertPageTitle(driver, "DSM Central: Edit Task", 90);
            SeleniumUtils.waitOnProgressBar(driver, 30);

            //Check if the button with the label Save and Create New Project exists on the page
            SeleniumUtils.assertElementIsClickable(driver, Locator.traksmartButtonAnchorStyle("Save and Create New Project"));

            //Check if 2 Save and Create New Project buttons exists on the page
            List saveButtons = driver.findElements(Locator.traksmartButtonAnchorStyle("Save and Create New Project"));
            int size = saveButtons.size();
            Assert.assertEquals(size, 2, "There should be two buttons on this page, one on the top and one on the bottom");

            // Customer
            long time = System.currentTimeMillis();
            String savedAccountNum = "acc" + time;
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Customer')]]/../../../../..//label/span[text()[contains(.,'OUC Account')]]/../..//input[@type='text']"), savedAccountNum);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Customer')]]/../../../../..//label/span[text()[.='Name:']]/../..//input[@type='text']"), "name" + time);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Customer')]]/../../../../..//label/span[text()[contains(.,'Phone Number')]]/../..//input[@type='text']"), Locator.Text.PHONE);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Customer')]]/../../../../..//label/span[text()[contains(.,'Email')]]/../..//input[@type='text']"), Locator.Text.EMAIL);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Customer')]]/../../../../..//label/span[text()[contains(.,'Alternative Phone Number')]]/../..//input[@type='text']"), Locator.Text.PHONE);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Customer')]]/../../../../..//label/span[text()[contains(.,'Premise Address 1')]]/../..//input[@type='text']"), Locator.Text.ADDRESS1);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Customer')]]/../../../../..//label/span[text()[contains(.,'Premise Address 2')]]/../..//input[@type='text']"), Locator.Text.ADDRESS2);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Customer')]]/../../../../..//label/span[text()[contains(.,'Premise City')]]/../..//input[@type='text']"), Locator.Text.CITY);
            SelectUtils.clickItemOfDropDownWithTextInput(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Customer')]]/../../../../..//label/span[text()[contains(.,'Premise State')]]/../..//input[@type='text']"), Locator.Text.STATE);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Customer')]]/../../../../..//label/span[text()[contains(.,'Premise Zip')]]/../..//input[@type='text']"), Locator.Text.ZIP_CODE);

            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Customer')]]/../../../../..//label/span[text()[contains(.,'Contact First Name')]]/../..//input[@type='text']"), Locator.Text.FIRST_NAME);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Customer')]]/../../../../..//label/span[text()[contains(.,'Contact Last Name')]]/../..//input[@type='text']"), Locator.Text.LAST_NAME);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Customer')]]/../../../../..//label/span[text()[contains(.,'Contact Email')]]/../..//input[@type='text']"), Locator.Text.EMAIL);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Customer')]]/../../../../..//label/span[text()[contains(.,'Contact Phone Number')]]/../..//input[@type='text']"), Locator.Text.PHONE);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Customer')]]/../../../../..//label/span[text()[contains(.,'Mailing Address 1')]]/../..//input[@type='text']"), Locator.Text.ADDRESS1);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Customer')]]/../../../../..//label/span[text()[contains(.,'Mailing Address 2')]]/../..//input[@type='text']"), Locator.Text.ADDRESS2);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Customer')]]/../../../../..//label/span[text()[contains(.,'Mailing City')]]/../..//input[@type='text']"), Locator.Text.CITY);
            SelectUtils.clickItemOfDropDownWithTextInput(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Customer')]]/../../../../..//label/span[text()[contains(.,'Mailing State')]]/../..//input[@type='text']"), Locator.Text.STATE);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Customer')]]/../../../../..//label/span[text()[contains(.,'Mailing Zip')]]/../..//input[@type='text']"), Locator.Text.ZIP_CODE);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Customer')]]/../../../../..//label/span[text()[contains(.,'Customer Number')]]/../..//input[@type='text']"), "custNum" + time);

            // Application Information
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Application Information')]]/../../../../..//label/span[text()[.='Date:']]/../..//input[@type='text']"), Locator.Text.DATE);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Application Information')]]/../../../../..//label/span[text()[.='Install Date:']]/../..//input[@type='text']"), Locator.Text.DATE_FROM);
            SelectUtils.clickItemOfDropDownWithTextInput(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Application Information')]]/../../../../..//label/span[text()[.='Type of Parking:']]/../..//input[@type='text']"), "Other");
            SelectUtils.clickItemOfDropDownWithTextInput(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Application Information')]]/../../../../..//label/span[text()[.='How Did You Hear About Us:']]/../..//input[@type='text']"), "News");
            SelectUtils.clickItemOfDropDownWithTextInput(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Application Information')]]/../../../../..//label/span[text()[.='Do you plan to charge users a session fee?:']]/../..//input[@type='text']"), "Yes");

            String ratePath = path + File.separatorChar + "Rates.xlsx";
            SeleniumUtils.waitForElement(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Application Information')]]/../../../../..//label/span[text()[.='Installation Invoices:']]/../..//input[@type='file']")).sendKeys(ratePath); //file Browse Button
            SeleniumUtils.sleepSecs(3);
            SeleniumUtils.clickElement(driver, By.partialLinkText("Upload"), 1);
            SeleniumUtils.waitForInVisible(driver, Locator.divTextContains("Uploading your file..."), 60);
            SeleniumUtils.waitForElement(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Application Information')]]/../../../../..//label/span[text()[.='Proof of Purchase:']]/../..//input[@type='file']")).sendKeys(ratePath); //file Browse Button
            SeleniumUtils.sleepSecs(3);
            SeleniumUtils.clickElement(driver, By.partialLinkText("Upload"), 2);
            SeleniumUtils.waitForInVisible(driver, Locator.divTextContains("Uploading your file..."), 60);
            // Solar Installer
            SelectUtils.clickItemOfDropDownWithTextInput(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Solar Installer')]]/../../../../..//label/span[text()[contains(.,'Installer State')]]/../..//input[@type='text']"), Locator.Text.STATE);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Solar Installer')]]/../../../../..//label/span[text()[.='Installer Company Name:']]/../..//input[@type='text' and @maxlength='100' ]"), Locator.Text.FIRST_NAME + " " + Locator.Text.LAST_NAME);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Solar Installer')]]/../../../../..//label/span[text()[.='Installer Address 1:']]/../..//input[@type='text']"), Locator.Text.ADDRESS1);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Solar Installer')]]/../../../../..//label/span[text()[.='Installer Address 2:']]/../..//input[@type='text']"), Locator.Text.ADDRESS2);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Solar Installer')]]/../../../../..//label/span[text()[.='Installer City:']]/../..//input[@type='text']"), Locator.Text.CITY);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Solar Installer')]]/../../../../..//label/span[text()[.='Installer Zip Code:']]/../..//input[@type='text']"), Locator.Text.ZIP_CODE);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Solar Installer')]]/../../../../..//label/span[text()[.='License #:']]/../..//input[@type='text']"), "#"+time);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Solar Installer')]]/../../../../..//label/span[text()[.='Contact First Name:']]/../..//input[@type='text']"), Locator.Text.FIRST_NAME);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Solar Installer')]]/../../../../..//label/span[text()[.='Contact Last Name:']]/../..//input[@type='text']"), Locator.Text.LAST_NAME);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Solar Installer')]]/../../../../..//label/span[text()[.='Contact Phone:']]/../..//input[@type='text']"), Locator.Text.PHONE);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Solar Installer')]]/../../../../..//label/span[text()[.='Contact Email:']]/../..//input[@type='text']"), Locator.Text.EMAIL);
            SeleniumUtils.setText(driver, By.xpath("//div[contains(@class, 'x-panel')]/div[text()[contains(., 'Solar Installer')]]/../../../../..//label/span[text()[.='Company Website:']]/../..//input[@type='text']"), Locator.Text.WEBSITE);

            SeleniumUtils.waitForClickable(driver, By.linkText("Save"), 10);
            SeleniumUtils.click(driver, By.linkText("Save"));

            SeleniumUtils.sleepSecs(10);
    }

    @Test(dependsOnMethods = {"fillFormData"})
    public void visitAllMenuItems(){
        SeleniumUtils.waitForClickable(driver, By.linkText("Appointments"));
        SeleniumUtils.click(driver, By.linkText("Appointments"));
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Project Partner");

        SeleniumUtils.waitForClickable(driver, By.linkText("Task Order"));
        SeleniumUtils.click(driver, By.linkText("Task Order"));
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Task Order");

        SeleniumUtils.waitForClickable(driver, By.linkText("Performance"));
        SeleniumUtils.click(driver, By.linkText("Performance"));
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Performance");

        SeleniumUtils.waitForClickable(driver, By.linkText("Files"));
        SeleniumUtils.click(driver, By.linkText("Files"));
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Project Files");

        SeleniumUtils.waitForClickable(driver, By.linkText("Project Partners"));
        SeleniumUtils.click(driver, By.linkText("Project Partners"));
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Project Partner");

        SeleniumUtils.waitForClickable(driver, By.linkText("Project Structure"));
        SeleniumUtils.click(driver, By.linkText("Project Structure"));
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Project Structure");

        SeleniumUtils.waitForClickable(driver, By.linkText("Customer Info"));
        SeleniumUtils.click(driver, By.linkText("Customer Info"));
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Project Customer");

        SeleniumUtils.waitForClickable(driver, By.linkText("Summary"));
        SeleniumUtils.click(driver, By.linkText("Summary"));
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Project Detail");
    }

    @Test(dependsOnMethods = {"visitAllMenuItems"})
    public void sortProjects(){
        SeleniumUtils.goToTab(driver, Locator.L_TAB_PROJECTS, "DSM Central: All Projects");

        // Sort By
        SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("Created"));
        SeleniumUtils.click(driver, Locator.anchorWithSpanText("Created"));

        SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("Last Updated"));
        SeleniumUtils.click(driver, Locator.anchorWithSpanText("Last Updated"));

        SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("Application Priority"));
        SeleniumUtils.click(driver, Locator.anchorWithSpanText("Application Priority"));

        SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("Project Name"));
        SeleniumUtils.click(driver, Locator.anchorWithSpanText("Project Name"));

        SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("Project Name"));
        SeleniumUtils.click(driver, Locator.anchorWithSpanText("Project Name"));

        // Show
        SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("Completed"));
        SeleniumUtils.click(driver, Locator.anchorWithSpanText("Completed"));

        SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("In Progress"));
        SeleniumUtils.click(driver, Locator.anchorWithSpanText("In Progress"));

        SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("Assigned to me"));
        SeleniumUtils.click(driver, Locator.anchorWithSpanText("Assigned to me"));

        SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("Added by you"));
        SeleniumUtils.click(driver, Locator.anchorWithSpanText("Added by you"));

        SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("All"));
        SeleniumUtils.click(driver, Locator.anchorWithSpanText("All"));

        // Per Page
        SeleniumUtils.waitForClickable(driver, By.linkText("100"));
        SeleniumUtils.click(driver, By.linkText("100"));

        SeleniumUtils.waitForClickable(driver, By.linkText("50"));
        SeleniumUtils.click(driver, By.linkText("50"));

        SeleniumUtils.waitForClickable(driver, By.linkText("20"));
        SeleniumUtils.click(driver, By.linkText("20"));
    }
}
