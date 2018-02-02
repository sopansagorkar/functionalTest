package com.nexant.dsm.planning.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.dsm.libraries.measures.process.TestCreateCategoryTypeSubType;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Tim on 9/11/2014.
 */
public class TestEditMeasures {

    private static WebDriver driver = null;
    
    @BeforeClass
    public void setUp(){
        driver = Driver.get();
        
        if(TestCreateNewProgram.savedProgramName == null) throw new RuntimeException("a program must be created using TestCreateNewProgram first");
        if(TestCreateCategoryTypeSubType.savedCategoryName == null) throw new RuntimeException("a measure category must be created first using TestCreateCategoryTypeSubType");
        if(TestCreateCategoryTypeSubType.savedCategoryTypeName == null) throw new RuntimeException("a measure category type must be created first using TestCreateCategoryTypeSubType");
        if(TestCreateCategoryTypeSubType.savedSubTypeName == null) throw new RuntimeException("a measure category subtype must be created first using TestCreateCategoryTypeSubType");
    }

    @Test
    public void editMeasures(){
        TestCreateNewProgram.openSavedProgram();

        SeleniumUtils.waitForClickable(driver, By.xpath("//div[text()[.='Measures']]/../a[1]")); // click on edit measures button
        SeleniumUtils.click(driver, By.xpath("//div[text()[.='Measures']]/../a[1]"));

        SeleniumUtils.assertPageTitle(driver, "DSM Central: Planning Program Details");

        SeleniumUtils.waitForElement(driver, Locator.anchorText("Select Measures"));

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divContainsClass("x-form-arrow-trigger-default"));
        SeleniumUtils.click(driver, Locator.divContainsClass("x-form-arrow-trigger-default"));

        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.waitOnProgressBar(driver, 120);
        SeleniumUtils.sleepSecs(2);

        SeleniumUtils.moveMouseWithWait(driver, Locator.spanTextContains(TestCreateCategoryTypeSubType.savedCategoryName));
        SeleniumUtils.moveMouseWithWait(driver, Locator.spanTextContains(TestCreateCategoryTypeSubType.savedCategoryName));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.moveThenClick(driver, Locator.spanTextContains(TestCreateCategoryTypeSubType.savedCategoryTypeName), Locator.spanTextContains(TestCreateCategoryTypeSubType.savedSubTypeName));

        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.scrollToElement(driver, Locator.spanTextContains("Search All"));

        // click search All
        SeleniumUtils.clickUsingJavascript(driver, Locator.spanTextContains("Search All"));
        SeleniumUtils.sleepSecs(2);

        // check a box on line 1
        SeleniumUtils.click(driver, By.xpath("//div[contains(@class, 'locked_grid_with_checkbox')][1]//table[1]/tbody/tr/td[1]"));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.dragAndDrop(driver, By.xpath("//div/span[text()[contains(.,'Type')]]"), By.cssSelector("#dropPromptText"));
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("Include Selected"));


        // pop up should appear
        SeleniumUtils.handleConfirmMsg(driver, "Status", "Measures added to program successfully.", "OK");

        SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("Done Measure Editing"));

        // make sure we are back on the planning tab
        SeleniumUtils.waitForElement(driver, By.cssSelector("#planningTitle")); // label with program name content
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorText(TestCreateNewProgram.savedProgramName));
    }

}
