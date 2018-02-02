package com.nexant.dsm.programs.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
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
public class TestCreateWorkflow {

    private static WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = Driver.get();
        TestAddNewProgram.openSavedProgram();
    }

    @Test(groups={"TestCreateWorkflow"})
    public void createWorkflow() {

        //Click on the 'Forms Editor' link
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorTextContains("Program Editor"));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.click(driver, Locator.anchorTextContains("Program Editor"));

        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div[contains(@class, 'traksmart_img')]"));
        Assert.assertEquals(driver.getTitle(), "DSM Central: Program Detail");

        SeleniumUtils.sleepSecs(1);
        // switching to the new tab that is displaying the Intallio
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

        SeleniumUtils.waitForElement(driver, By.id("username"));//make sure page is loaded

        WebElement el = SeleniumUtils.waitForElementWithoutException(driver, By.xpath("//div/input[@name='submit']"), 5);

        if (el == null) {
            //new intalio does not have submit button. Have to find some differentiating feature here.
            TestCreateWorkflowNewIntalio.loginAndCreateWorkflowNewIntalio(oldTab);
        } else {
            loginAndCreateWorkflow(oldTab);
        }

    }

    public static void loginAndCreateWorkflow(String returnTab) {
        // login to intallio
        SeleniumUtils.setText(driver, By.id("username"), "admin");
        SeleniumUtils.setText(driver, By.id("password"), "123");
        SeleniumUtils.click(driver, By.xpath("//div/input[@name='submit']"));

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanTextContains("Edit Business Process"));
        SeleniumUtils.click(driver, Locator.spanText("Design"));
        SeleniumUtils.click(driver, Locator.divContainsClass("x-tool-maximize"));

        WebDriver frameDriver = driver.switchTo().frame(0);

        SeleniumUtils.click(driver, By.className("x-tool-expand-west"));

        SeleniumUtils.click(driver, By.className("x-tool-expand-east"));

        SeleniumUtils.checkForElement(driver, By.className("WAPAMA_Editor"));
        SeleniumUtils.checkForElement(driver, By.linkText("Start Event"));
        SeleniumUtils.checkForElement(driver, By.linkText("Traksmart User Task"));
        SeleniumUtils.checkForElement(driver, By.linkText("End Event"));

        SeleniumUtils.dragAndDrop(driver, By.linkText("Start Event"), By.className("WAPAMA_Editor"));

        SeleniumUtils.sleepSecs(2);

        //select what we just dropped in
        // SeleniumUtils.click(driver, By.xpath("//*[local-name() = 'svg']"));
        //  SeleniumUtils.sleepSecs(2);


        // click on the button to create the next flow component
        WebElement el = SeleniumUtils.waitForElement(driver, By.cssSelector(".Wapama_Right"));

        //different buttons on the start element we dragged in
        List<WebElement> buttons = el.findElements(By.className("Wapama_button"));
        buttons.get(0).click(); // first button is task component

        SeleniumUtils.sleepSecs(2);

        //div with all configurable properties for the task component, on the right side of the screen
        WebElement topDiv = SeleniumUtils.waitForElement(driver, By.className("x-grid-group-body"));
        // each div is a property.
        List<WebElement> propDivs  = topDiv.findElements(By.className("x-grid3-row"));
        // Team Participant property is the third option
        List<WebElement> tds = propDivs.get(2).findElements(By.tagName("td"));
        tds.get(1).click();

        SeleniumUtils.sleepSecs(1);

        WebElement dropdown = driver.switchTo().activeElement();
        dropdown.sendKeys("Everybody");
        dropdown.sendKeys(Keys.RETURN);
        dropdown.sendKeys(Keys.ENTER);

        SeleniumUtils.sleepSecs(1);

        topDiv = SeleniumUtils.waitForElement(driver, By.className("x-grid-group-body"));
        propDivs  = topDiv.findElements(By.className("x-grid3-row"));
        tds = propDivs.get(1).findElements(By.tagName("td"));
        tds.get(1).click();

        SeleniumUtils.sleepSecs(1);

        WebElement taskName = driver.switchTo().activeElement();
        taskName.sendKeys(TestCreateForm.savedTaskName);
        taskName.sendKeys(Keys.RETURN);

        SeleniumUtils.sleepSecs(1);

        // Set Traksmart Web Form ID
        topDiv = SeleniumUtils.waitForElement(driver, By.className("x-grid-group-body"));
        propDivs  = topDiv.findElements(By.className("x-grid3-row"));
        tds = propDivs.get(3).findElements(By.tagName("td"));
        tds.get(1).click();

        SeleniumUtils.sleepSecs(1);

        dropdown = driver.switchTo().activeElement();
        dropdown.sendKeys(TestAddNewProgram.savedProgramName + "_v1." + TestCreateForm.savedFormName2);
        dropdown.sendKeys(Keys.RETURN);
        dropdown.sendKeys(Keys.ENTER);

        SeleniumUtils.sleepSecs(1);

        // click on the button to create the next flow component
        el = SeleniumUtils.waitForElement(driver, By.cssSelector(".Wapama_Right"));
        buttons = el.findElements(By.className("Wapama_button"));
        buttons.get(1).click();

        SeleniumUtils.sleepSecs(2);

        driver.switchTo().parentFrame();

        SeleniumUtils.sleepSecs(1);
        //save
        SeleniumUtils.click(driver, By.xpath("//button[contains(@style, 'save.png')]"));

        SeleniumUtils.sleepSecs(4);

        //deploy
        SeleniumUtils.click(driver, By.xpath("//button[text()[.='Deploy']]"));

        SeleniumUtils.sleepSecs(2);

        List<WebElement> okButtons = SeleniumUtils.waitForElements(driver, By.xpath("//button[text()[.='OK']]"));

        for (WebElement okButton: okButtons) {
            if (okButton.isDisplayed()) {
                okButton.click();
                break;
            }
            //String s = okButton.getAttribute("id");
        }

        SeleniumUtils.sleepSecs(2);


        driver.close();
        driver.switchTo().window(returnTab);

        SeleniumUtils.sleepSecs(2);

    }
}
