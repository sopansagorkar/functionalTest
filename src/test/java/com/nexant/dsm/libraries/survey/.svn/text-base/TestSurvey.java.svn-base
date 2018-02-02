package com.nexant.dsm.libraries.survey;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by VKamenev on 5/30/2014.
 */
@Test
public class TestSurvey {

    private WebDriver driver = null;

    @BeforeClass
    public void setup() {
        driver = Driver.get();
    }

    @Test
    public void testSurveysSelector() {

        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_LIBRARIES, Locator.L_LIBRARIES_SURVEY, "DSM Central: Survey Selector Page");

        // Sort Surveys
        SeleniumUtils.waitForClickable(driver, By.xpath("//div/a[text()[.='100']]"));
        SeleniumUtils.click(driver, By.xpath("//div/a[text()[.='100']]"));
        SeleniumUtils.waitForClickable(driver, By.xpath("//div/a[text()[.='50']]"));
        SeleniumUtils.click(driver, By.xpath("//div/a[text()[.='50']]"));
        SeleniumUtils.waitForClickable(driver, By.xpath("//div/a[text()[.='20']]"));
        SeleniumUtils.click(driver, By.xpath("//div/a[text()[.='20']]"));
        SeleniumUtils.waitForClickable(driver, By.xpath("//a/span[text()[.='Last Updated']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Last Updated']]"));
        SeleniumUtils.waitForClickable(driver, By.xpath("//a/span[text()[.='Zip Code']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Zip Code']]"));
        SeleniumUtils.waitForClickable(driver, By.xpath("//a/span[text()[.='Name']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Name']]"));
        SeleniumUtils.waitForClickable(driver, By.xpath("//a/span[text()[.='Customer Number']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Customer Number']]"));


    }

    @Test
    public void testSurveyList() {

        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_LIBRARIES, Locator.L_LIBRARIES_SURVEY, "DSM Central: Survey Selector Page");

        SeleniumUtils.waitForClickable(driver, By.xpath("//div/ul/li[text()[.='Survey List']]"));
        SeleniumUtils.click(driver, By.xpath("//div/ul/li[text()[.='Survey List']]"));

        // Sort Surveys
        SeleniumUtils.waitForClickable(driver, By.xpath("//ul/li[text()[.='Created On']]"));
        SeleniumUtils.click(driver, By.xpath("//ul/li[text()[.='Created On']]"));
        SeleniumUtils.waitForClickable(driver, By.xpath("//div/a[text()[.='Name']]"));
        SeleniumUtils.click(driver, By.xpath("//div/a[text()[.='Name']]"));

    }

}
