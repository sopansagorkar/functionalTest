package com.nexant.dsm.planning.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Tim on 9/22/2014.
 */
public class TestCreatePortfolio {
    private static WebDriver driver = null;
    public static String portfolioName = null;

    @BeforeClass
    public void setUp(){
        driver = Driver.get();
        portfolioName = "PRF" + System.currentTimeMillis();
    }


    public static void goToPortfolioTab() {
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("Portfolio"));
        SeleniumUtils.sleepMillis(200);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
    }

    @Test
    public void createPortfolio(){

        SeleniumUtils.goToTab(driver, Locator.L_TAB_PLANNING, "DSM Central: All Programs");


        goToPortfolioTab();


        // click 'create portfolio' button
        SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("+ Create Portfolio"));
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Planning Portfolio Details");

        SeleniumUtils.setText(driver, By.xpath("//*[@name='portfolioName']"), portfolioName);
        SeleniumUtils.setText(driver, By.xpath("//*[@name='description']"), Locator.Text.DESCRIPTION);


        // click 'Save' btn
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Save"));

        // verify that a title of a page


        SeleniumUtils.sleepSecs(5);

    }
}
