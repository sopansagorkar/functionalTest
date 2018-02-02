package com.nexant.dsm.planning.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Tim on 10/22/2014.
 */
public class TestEditPortfolio {
    private WebDriver driver = null;
    private String portfolioName = null;

    @BeforeClass
    public void setUp(){
        driver = Driver.get();
        portfolioName = TestCreatePortfolio.portfolioName;
    }


    @Test
    public void selectCreatedPortfolio(){

        SeleniumUtils.goToTab(driver, Locator.L_TAB_PLANNING, "DSM Central: All Programs");

        TestCreatePortfolio.goToPortfolioTab();

        SeleniumUtils.waitForElement(driver, By.xpath("//div/a/span[text()[contains(.,'Portfolio Name')]]"));
        By lastUpdated = By.xpath("//div/a/span[text()[contains(.,'Last Updated')]]");

        SeleniumUtils.click(driver, lastUpdated);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));

        SeleniumUtils.click(driver, lastUpdated);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));

        SeleniumUtils.click(driver, Locator.anchorText(portfolioName));
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));

        SeleniumUtils.assertPageTitle(driver, "DSM Central: Planning Portfolio Details");
    }

    @Test
    public void editProgram(){
        SeleniumUtils.clickUsingJavascript(driver, Locator.traksmartButtonAnchorStyle("+ Edit Programs"));


        SeleniumUtils.click(driver, By.cssSelector(".x-form-trigger"));

        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.waitOnProgressBar(driver, 60);
        SeleniumUtils.sleepSecs(1);

        SeleniumUtils.moveThenClick(driver, Locator.spanText("Education and Outreach"), Locator.spanText("Education and Outreach"));

        SeleniumUtils.clickUsingJavascript(driver, Locator.traksmartButtonAnchorStyle("Search All"));

        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading, please wait..."));
        SeleniumUtils.sleepSecs(1);

        // check the first checkbox on the grid
        SeleniumUtils.click(driver, By.xpath("//div[contains(@class, 'locked_grid_with_checkbox')][1]//table[1]/tbody/tr/td[1]"));
        SeleniumUtils.sleepSecs(1);


        SeleniumUtils.clickUsingJavascript(driver, Locator.traksmartButtonAnchorStyle("Include Selected"));

        SeleniumUtils.sleepSecs(1);

        SeleniumUtils.handleConfirmMsg(driver, "Status", "Planning Programs added to portfolio successfully.", "OK");

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Planning.PL_DONE_PROGRAM_EDIT);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Planning.PL_DONE_PROGRAM_EDIT);
    }
}
