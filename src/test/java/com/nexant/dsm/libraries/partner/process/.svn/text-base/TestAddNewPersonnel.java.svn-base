package com.nexant.dsm.libraries.partner.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = {"Partner Personnel"})
public class TestAddNewPersonnel {
    public static String personnel_first_name;
    public static String personnel_last_name;
    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        driver = Driver.get();
    }

    @Test
    public void testAddPersonnel() {

        String office = TestAddNewCompanyOffices.savedOfficeName;
        Assert.assertNotNull(office, "office from previous test is not saved");

        By addPersonnelButton = By.cssSelector("[value= '+Add Personnel']");
        SeleniumUtils.click(driver, addPersonnelButton);

        By popup = Locator.divText("Add New Personnel");
        SeleniumUtils.assertElementExistsWithWait(driver, popup);
        // assert popup

        personnel_first_name = "pfn" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Partner.firstName, personnel_first_name);


        personnel_last_name = "pln" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Partner.lastName, personnel_last_name);

        SeleniumUtils.setText(driver, Locator.Partner.title1, Locator.Text.STRING);

        SelectUtils.clickItemName(driver, Locator.Partner.partnerOfficeId, office);

        SeleniumUtils.setText(driver, Locator.Partner.emailAddress, Locator.Text.EMAIL);
        SeleniumUtils.setText(driver, Locator.Partner.phCntryCode, Locator.Text.PRIMARY_COUNTRY_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.phAreaCode, Locator.Text.PRIMARY_AREA_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.phPrefix, Locator.Text.PRIMARY_PREFIX_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.phSuffix, Locator.Text.PRIMARY_SUFFIX_CODE);
        SeleniumUtils.setText(driver, Locator.Partner.phExt, Locator.Text.PRIMARY_EXT_CODE);

        SeleniumUtils.click(driver, Locator.Partner.primaryContact);
        SeleniumUtils.setText(driver, Locator.Partner.comments, Locator.Text.DESCRIPTION);

        SeleniumUtils.click(driver, Locator.Partner.L_SAVE_NEW_PERSONNEL);

        // check the popup goes away
        SeleniumUtils.waitForInVisible(driver, popup);

        // check the personnel is added
        SeleniumUtils.assertElementExistsWithWait(driver, By.linkText(personnel_first_name));


        // Sort Personnel List
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/span[text()[.='Last Name']]"));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='Last Name']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/span[text()[.='First Name']]"));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='First Name']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/span[text()[.='Email']]"));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='Email']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/span[text()[.='Primary Contact']]"));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='Primary Contact']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/span[text()[.='Last Update']]"));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='Last Update']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/span[text()[.='By']]"));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='By']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//div/span[text()[.='TrakSmart User']]"));
        SeleniumUtils.click(driver, By.xpath("//div/span[text()[.='TrakSmart User']]"));

    }
}
