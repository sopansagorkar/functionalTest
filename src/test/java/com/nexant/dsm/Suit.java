package com.nexant.dsm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.common.pageObjects.login.Login;
import com.nexant.webdriver.Driver;

@Test(groups = {"Main"})
public class Suit {
    private static WebDriver driver;

    public static String siteAddress;
    public static String login;
    public static String password;


    @Parameters({"SiteAddress", "Login", "Password"})
    @Test(groups = {"Setup"})
    public void login(String siteAddressParam, String loginParam, String passwordParam) {
    	System.out.println("Suit 1");
        driver = Driver.get();
        siteAddress = siteAddressParam;
        login = loginParam;
        password = passwordParam;

        Login.login(driver, siteAddress, login, password);
    }

    
    /**
     * We need a non-incentive attribute for the planning module
     */
    @Test(dependsOnMethods={"login"}, groups = {"Setup"})
    public void setupNonIncentiveAttribute(){
    	 //eleniumUtils.waitForElement(driver, Locator.L_TAB_ADMIN, 30);
    	 SeleniumUtils.goToSubTab(driver, Locator.L_TAB_ADMIN, Locator.L_ADMIN_SYSTEM, "DSM Central: Administration" );
       
    	 SeleniumUtils.click(driver, By.xpath("//span[text()[.='Attributes']]/../img[2]"));
        
        SeleniumUtils.click(driver, Locator.spanText("Measure Attributes"));
        
        SeleniumUtils.waitForClickable(driver, By.id("admin_attributes_search_combobox-inputEl"));
        
        SeleniumUtils.waitForAllInVisibleWithSleeps(driver, Locator.divText("Loading..."), 1);
        
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Add"));
        
        SeleniumUtils.waitForVisible(driver, Locator.divText("Add/Edit"));
        SeleniumUtils.setText(driver, Locator.inputWithName("attributeName"), "NonIncentiveAtt");
        SeleniumUtils.setText(driver, Locator.textareaWithName("description"), "Non Incentive Att");
        SelectUtils.clickItemOfDropDownWithTextInput(driver, Locator.inputWithName("dataType"), "BIGDECIMAL");
        // note that UI Element should be auto populated to "numeric field" when BIGDECIMAL is selected
        SelectUtils.clickItemOfDropDownWithTextInput(driver, Locator.inputWithName("configElement"), "CostProperties");
        SeleniumUtils.sleepMillis(250);
        SelectUtils.clickItemOfDropDownWithTextInput(driver, Locator.inputWithName("costCategory"), "Non-Incentive");
        SeleniumUtils.click(driver, By.xpath("//label[text()[.='Planning Attribute:']]/../input"));
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Create"));
        
        // just click ok on error or success
        //SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Record Saved Successfully"));
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("OK"));
    }
    


    @AfterSuite
    public static void CloseDriver() {
        driver.close();
    }
}
