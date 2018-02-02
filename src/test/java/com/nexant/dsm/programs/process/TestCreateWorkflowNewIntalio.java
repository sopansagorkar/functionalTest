package com.nexant.dsm.programs.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aabdollahi on 2/24/2015.
 */
public class TestCreateWorkflowNewIntalio {

    private static WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = Driver.get();
    }

    @Test(groups={"TestCreateWorkflow"})
    public void createWorkflow() {

        TestAddNewProgram.openSavedProgram(TestAddNewProgram.savedProgramName);

        //Click on the 'Forms Editor' link
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorTextContains("Program Editor"));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.click(driver, Locator.anchorTextContains("Program Editor"));

        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div[contains(@class, 'traksmart_img')]"));
        Assert.assertEquals(driver.getTitle(), "DSM Central: Program Detail");

        SeleniumUtils.sleepSecs(1);
        // switching to the new tab that is displaying the Intalio
        String oldTab = driver.getWindowHandle();
        ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());

        for(int i=0; i<10; i++) {
            if (allTabs.size() < 2) {
                SeleniumUtils.sleepSecs(2);
                allTabs = new ArrayList<String>(driver.getWindowHandles());
            }
        }
        allTabs.remove(oldTab);
        driver.switchTo().window(allTabs.get(0));

        loginAndCreateWorkflowNewIntalio(oldTab);

    }

    public static void loginAndCreateWorkflowNewIntalio(String returnTab) {
        // login to intallio
        SeleniumUtils.setText(driver, By.id("username"), "system");
        SeleniumUtils.setTextAndEnter(driver, By.id("password"), "Test.123");

        SeleniumUtils.waitForElement(driver, Locator.spanTextContains("Business Process"), 30);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanTextContains("Business Process"));
        SeleniumUtils.click(driver, By.id("io_business_process-primary-image"));

        SeleniumUtils.waitForElement(driver, By.xpath("//iframe[@id = 'wapama']"));

        WebDriver frameDriver = driver.switchTo().frame(0);

        SeleniumUtils.checkForElement(driver, By.className("WAPAMA_Editor"));
        SeleniumUtils.checkForElement(driver, By.id("business_start_event"));

        SeleniumUtils.dragAndDrop(driver, By.id("business_start_event"), By.className("WAPAMA_Editor"), 8, 8);

        SeleniumUtils.sleepSecs(1);

        //find the user task square and click on it.
        By taskBy = By.xpath("//div[@id='canvas']//img[@title='User Task - Click or drag']");
        SeleniumUtils.waitForClickable(driver, taskBy);
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.click(driver, taskBy);
        SeleniumUtils.sleepSecs(1);

        driver.switchTo().parentFrame(); // to set the task properties

        By taskNameBy = By.xpath("//div[@id='tabs_overview']//input[@id = 'io_name']");
        SeleniumUtils.waitForElement(driver, taskNameBy); //set name property
        SeleniumUtils.setText(driver, taskNameBy, TestCreateForm.savedTaskName);

        SeleniumUtils.click(driver, By.id("nex_traksmart_web_form_id")); // click for dropdown to appear and pick the right form
        SeleniumUtils.sleepSecs(1);

        String formName = TestAddNewProgram.savedProgramName + "_v1." + TestCreateForm.savedFormName2;
        SeleniumUtils.click(driver, By.xpath("(//div/ul/li/a[text()[contains(.,'" + formName + "')]])"));
        SeleniumUtils.sleepSecs(1);

        driver.switchTo().frame(0); // back to canvas

        //find the end event circle and click on it.
        By by = By.xpath("//div[@id='canvas']//img[@title='End Event - Click or drag']");
        SeleniumUtils.waitForClickable(driver, by);

        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.click(driver, by);
        SeleniumUtils.sleepSecs(1);

        //save
        SeleniumUtils.click(driver, By.xpath("//a[@id = 'save']"));

        driver.switchTo().parentFrame();//messages show up outside the iframe.
        SeleniumUtils.waitForElement(driver, By.xpath("//p[text()[.='Process saved.']]"), 30);
        driver.switchTo().frame(0); // back to canvas

        SeleniumUtils.sleepSecs(1);

        SeleniumUtils.click(driver, By.id("io-export-options"));
        SeleniumUtils.sleepSecs(1);

        SeleniumUtils.click(driver, By.xpath("(//div/ul/li/a[text()[contains(.,'Deploy')]])"));

        driver.switchTo().parentFrame();
        //SeleniumUtils.waitForInVisible(driver, Locator.divContainsClass("notice-item notice"));
        SeleniumUtils.waitForElement(driver, By.xpath("//p[text()[.='Process deployed.']]"), 30);
        driver.switchTo().frame(0); // back to canvas

        SeleniumUtils.sleepSecs(1);

        driver.close();
        driver.switchTo().window(returnTab);

        SeleniumUtils.sleepSecs(2);
    }
}
