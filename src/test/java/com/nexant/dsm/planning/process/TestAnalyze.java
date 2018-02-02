package com.nexant.dsm.planning.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.webdriver.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Tim on 9/16/2014.
 */
public class TestAnalyze {
    private WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = Driver.get();
    }

    @Test
    public void selectAnalyze(){

        Assert.assertNotNull(TestCreateNewProgram.savedProgramName, "must have a saved program name, this test depends on TestCreateNewProgram");

    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanText("Analyze"));
        SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("Analyze"));

        SeleniumUtils.sleepMillis(200);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Please Wait..."));

        // click 'Run Tests' button
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Planning.PL_RUN_TEST_BTN);
        SeleniumUtils.click(driver, Locator.Planning.PL_RUN_TEST_BTN);

        SeleniumUtils.handleConfirmMsg(driver, "Confirm", "Analyzing a scenario may take few minutes, want to continue?", "Yes");

        SeleniumUtils.waitOnProgressBar(driver, 120);
        SeleniumUtils.sleepSecs(1);

        // click 'Save As'
        SeleniumUtils.clickUsingJavascript(driver, Locator.traksmartButtonAnchorStyle("Save As"));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.setText(driver, By.xpath("//input[@id='saveASName-inputEl']"), TestCreateNewProgram.savedProgramName); // set 'Name'
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("Submit"));

        SeleniumUtils.handleConfirmMsg(driver, "Success", "Scenario Saved Sucessfully", "OK");

    }

}
