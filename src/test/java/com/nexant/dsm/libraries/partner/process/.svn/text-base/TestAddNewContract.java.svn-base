package com.nexant.dsm.libraries.partner.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = {"Partner Contract"})
public class TestAddNewContract {


    public static String parnerIdActive;
    public static String parnerIdInactive;

    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        driver = Driver.get();
    }

    @Test
    public void Test_Add_Active_Contract() {

        // click add contract
        By addContractButton = By.cssSelector("[value='+Add Contract']");

        SeleniumUtils.click(driver, addContractButton);

        // make sure the popup appears
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Partner.L_ADD_CONTRACT_POPUP);

        // fill in the number
        parnerIdActive = "pa" + System.currentTimeMillis();

        SeleniumUtils.setText(driver, Locator.Partner.contractName, parnerIdActive);

        // select active
        SelectUtils.clickItemName(driver, Locator.Partner.activeindicator, "Active");

        // save
        SeleniumUtils.click(driver, Locator.Partner.saveContractButton);

        // wait for it to show up saved
        By savedLink = By.xpath("//div[@id='partner_contract_content']//a[.='" + parnerIdActive + "']");
        SeleniumUtils.waitForClickable(driver, savedLink);
    }

    @Test
    public void Test_Add_Inactive_Contract() {
        By addContractButton = By.cssSelector("[value='+Add Contract']");

        SeleniumUtils.assertElementExistsWithWait(driver, addContractButton);

        SeleniumUtils.click(driver, addContractButton);

        // make sure the popup appears
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Partner.L_ADD_CONTRACT_POPUP);

        // set id
        parnerIdInactive = "pi" + System.currentTimeMillis();

        SeleniumUtils.setText(driver, Locator.Partner.contractName, parnerIdInactive);

        // select inactive
        SelectUtils.clickItemName(driver, Locator.Partner.activeindicator, "Inactive");


        // save
        SeleniumUtils.click(driver, Locator.Partner.saveContractButton);

        // wait for it to show up saved
        By savedLink = By.xpath("//div[@id='partner_contract_content']//a[.='" + parnerIdInactive + "']");
        SeleniumUtils.waitForClickable(driver, savedLink);

        // Sort Contract

        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/span[text()[.='Contract']]"));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='Contract']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/span[text()[.='Active Indicator']]"));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='Active Indicator']]"));

    }
}

