package com.nexant.dsm.programs.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: VKamenev
 * Date: 9/13/13
 * Time: 1:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Test
public class TestBudgetAndAccounting {

    public static String savedBudgetCategoryName;
    public static String savedAccountingCode;
    public static String savedAccountingCodeCategoryName;

    private static WebDriver driver;


    @BeforeClass
    public static void setup() {
        driver = Driver.get();
        TestAddNewProgram.openSavedProgram();

        // click budget and accounting (the user must have access to this module)
        SeleniumUtils.click(driver, Locator.Programs.Open_Budget_And_Accounting);
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Budget Info", 30);
    }

    @Test
    public void testAddBudgetCategory() {

        By by = By.xpath("//input[@value='+ Manage Budget Categories']");
        //By by = By.id("btnManageBudgetCategories"); // button with value: + Manage Budget Categories
        SeleniumUtils.waitForClickable(driver, by);
        // click manage budget categories
        SeleniumUtils.click(driver, by);

        //make sure popup shows up and has the following title
        By popup = Locator.divText("Manage Budget Categories");
        SeleniumUtils.waitForVisible(driver, popup, 30);

        // click add new category
        By btn = By.id("addCategoryRowLink");
        SeleniumUtils.waitForVisible(driver, btn);
        SeleniumUtils.click(driver, btn); // just use contains here because white space is added

        savedBudgetCategoryName = "cat" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, By.xpath("//div[@id='manage_budget_category_popup']//input[@type='text']"), savedBudgetCategoryName);

        SeleniumUtils.sleepSecs(1);
        SelectUtils.clickItemOfDropDownWithTextInput(driver, By.xpath("//input[@name='status_combo']"), "Active");

        // update
        SeleniumUtils.click(driver, Locator.L_BUTTON_WITH_UPDATE_SPAN_TEXT);

        // save
        SeleniumUtils.click(driver, By.xpath("//input[@value='Save']"));

        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));

        // popup goes away
        SeleniumUtils.waitForInVisible(driver, popup);
    }


    @Test
    public void testAddAccountingCodeCategory() {

        // click add budget category
        SeleniumUtils.click(driver, By.id("btnManageAcctCodeCategory"));

        // check popup comes up
        By popup = Locator.divText("Manage Accounting Code Categories");
        SeleniumUtils.waitForElement(driver, popup);
        SeleniumUtils.waitForElement(driver, By.id("deleteRowLink")); //delete category button exist

        SeleniumUtils.sleepSecs(2);

        // click +Add New Category
        SeleniumUtils.clickUsingJavascript(driver, By.id("addRowLink"));
        //SeleniumUtils.clickUsingJavascript(driver, Locator.anchorTextContains("+Add New Category")); // just use contains here because white space is added

        // enter the name
        savedAccountingCodeCategoryName = "accat" + System.currentTimeMillis();
        SeleniumUtils.sleepMillis(200);
        SeleniumUtils.setTextAndEnter(driver, By.xpath("//input[@name='accountCategory']"), savedAccountingCodeCategoryName);
        SeleniumUtils.sleepMillis(200);

        // save
        SeleniumUtils.clickUsingJavascript(driver, Locator.traksmartButtonAnchorStyle("Save")); // id=accounting_code_popup_form_save
        SeleniumUtils.sleepMillis(200);

        // check popup goes away
        SeleniumUtils.waitForInVisible(driver, popup);
    }


    @Test(dependsOnMethods={"testAddAccountingCodeCategory"})
    public void testAddAccountingCode() {

        // click add accounting code
        SeleniumUtils.click(driver, Locator.Programs.Add_code);

        By popup = Locator.divText("Add New Accounting Code");
        SeleniumUtils.assertElementExistsWithWait(driver, popup);

        SeleniumUtils.sleepSecs(1);
        // fill in form
        savedAccountingCode = "code" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Programs.Add_Account_Code, savedAccountingCode);
        SeleniumUtils.setText(driver, Locator.Programs.Description_Budget, Locator.Text.DESCRIPTION);
        SelectUtils.clickItemOfDropDownWithTextInput(driver, Locator.Programs.Select_Category, savedAccountingCodeCategoryName);
        SelectUtils.clickItemOfDropDownWithTextInput(driver, Locator.Programs.Select_Indicator, "Active");
        SelectUtils.clickFirstItemOfBoundList(driver, Locator.Programs.Select_Sector);
        SelectUtils.clickItemName(driver, Locator.Programs.Select_State, "TX");

        // save
        SeleniumUtils.click(driver, Locator.Programs.create_accounting_code_popup_form_save);

        SeleniumUtils.sleepMillis(200);
        // check popup goes away
        SeleniumUtils.waitForInVisible(driver, popup);

        // check the item is saved (there should be a link)
        SeleniumUtils.waitForClickable(driver, By.linkText(savedAccountingCode));
    }


    public static String newVariableCostName;
    public static String newFixedCostName;
    public static String payeePartnerNumber;
    public static String remitPartnerNumber;

    @Test(dependsOnMethods={"testAddAccountingCode"})
    public void testAddVariableCost() {

        SeleniumUtils.clickUsingJavascript(driver, Locator.Programs.Add_Variable_Cost);

        // check we have the popup
        By popup = Locator.divText("Add Variable Cost");
        SeleniumUtils.waitForVisible(driver, popup);

        // fill in the form
        newVariableCostName = "cn" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, By.xpath("//div[@id='add-variable-cost-form-innerCt']//input[@name='costName']"), newVariableCostName);

        SeleniumUtils.setText(driver, Locator.Programs.Description_VC, Locator.Text.DESCRIPTION);

        SeleniumUtils.setText(driver, Locator.Programs.Transaction_Date, Locator.Text.DATE_TO);

        SeleniumUtils.setText(driver, Locator.Programs.Select_Amount_VC, "14");

        String newExternalRefId = "ref" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Programs.Add_External_Reference_Id_VC, newExternalRefId);

        String savedAccountingCode = TestBudgetAndAccounting.savedAccountingCode;
        //String savedAccountingCode = "code" + System.nanoTime();
        Assert.assertNotNull(savedAccountingCode, "should have a saved accounting code from dependent test");

        SeleniumUtils.waitForElement(driver, Locator.Programs.Select_Accounting_Code_VC);

        // WebElement el = SeleniumUtils.click(driver, Locator.Programs.Select_Accounting_Code_VC);
        // el.sendKeys(Keys.ARROW_DOWN);
        //el.sendKeys(Keys.RETURN);
        SelectUtils.clickItemOfDropDownWithTextInput(driver, Locator.Programs.Select_Accounting_Code_VC, savedAccountingCode);

        SeleniumUtils.setText(driver, Locator.Programs.Payee_First_Name, Locator.Text.FIRST_NAME);
        SeleniumUtils.setText(driver, Locator.Programs.Payee_Last_Name, Locator.Text.LAST_NAME);
        SeleniumUtils.setText(driver, Locator.Programs.Payee_Name, Locator.Text.NAME);

        payeePartnerNumber = "ppn" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Programs.Payee_Partner_Number, payeePartnerNumber);

        SeleniumUtils.setText(driver, Locator.Programs.Payee_Street_address_1, Locator.Text.ADDRESS1);
        SeleniumUtils.setText(driver, Locator.Programs.Payee_Street_address_2, Locator.Text.ADDRESS2);
        SeleniumUtils.setText(driver, Locator.Programs.Payee_City, Locator.Text.CITY);
        SelectUtils.clickItemName(driver, Locator.Programs.Payee_State, Locator.Programs.Select_State_text);
        SeleniumUtils.setText(driver, Locator.Programs.Payee_ZIP, Locator.Text.ZIP_CODE);
        SeleniumUtils.setText(driver, Locator.Programs.Payee_County, Locator.Text.STRING);
        SeleniumUtils.setText(driver, Locator.Programs.Payee_Country, Locator.Text.COUNTRY);

        SeleniumUtils.click(driver, Locator.Programs.Press_Remit_Information);

        SeleniumUtils.setText(driver, Locator.Programs.Remit_First_Name, Locator.Text.FIRST_NAME);
        SeleniumUtils.setText(driver, Locator.Programs.Remit_Last_Name, Locator.Text.LAST_NAME);
        SeleniumUtils.setText(driver, Locator.Programs.Remit_Name, Locator.Text.NAME);
        remitPartnerNumber = "remit" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Programs.Remit_Partner_Number, remitPartnerNumber);
        SeleniumUtils.setText(driver, Locator.Programs.Remit_Street_address_1, Locator.Text.ADDRESS1);
        SeleniumUtils.setText(driver, Locator.Programs.Remit_Street_address_2, Locator.Text.ADDRESS2);
        SeleniumUtils.setText(driver, Locator.Programs.Remit_City, Locator.Text.CITY);
        SelectUtils.clickItemName(driver, Locator.Programs.Remit_State, Locator.Programs.Select_State_text);
        SeleniumUtils.setText(driver, Locator.Programs.Remit_ZIP, Locator.Text.ZIP_CODE);
        SeleniumUtils.setText(driver, Locator.Programs.Remit_County, Locator.Text.STRING);
        SeleniumUtils.setText(driver, Locator.Programs.Remit_Country, Locator.Text.COUNTRY);

        // click save
        //SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Save"));

        By by = By.xpath("//a[contains(@class, 'variableCostSaveBtn')]");
        SeleniumUtils.scrollToElement(driver, by);
        SeleniumUtils.click(driver, by);


        // check the popup goes away
        SeleniumUtils.waitForInVisible(driver, popup);

        // check the variable cost is added
        SeleniumUtils.assertElementExistsWithWait(driver, By.linkText(newVariableCostName));
    }

    @Test(dependsOnMethods={"testAddAccountingCode"})
    public void testAddFixedCost() {

        SeleniumUtils.clickUsingJavascript(driver, Locator.Programs.Add_Fixed_Cost);

        // check we have the popup
        By popup = Locator.divText("Add Fixed Cost");
        SeleniumUtils.waitForVisible(driver, popup);

        // fill in the form
        newFixedCostName = "fixedcost" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, By.xpath("//div[@id='add-cost-form-innerCt']//input[@name='costName']"), newFixedCostName);

        SeleniumUtils.setText(driver, By.xpath("//div[@id='add-cost-form-innerCt']//input[@name='description']"), "UnitTest Fixed Cost Description");

        SeleniumUtils.setText(driver, Locator.inputWithName("startDate"), "08/22/2020");

        SeleniumUtils.setText(driver, Locator.inputWithName("endDate"), "08/22/2030");

        SeleniumUtils.setText(driver, By.xpath("//div[@id='add-cost-form-innerCt']//input[@name='amount']"), "23");

        SelectUtils.clickItemOfDropDownWithTextInput(driver, Locator.inputWithName("costPeriodTypeId"), "MONTHLY");

        String newExternalRefId = "ref" + System.currentTimeMillis();
        SeleniumUtils.setText(driver, By.xpath("//div[@id='add-cost-form-innerCt']//input[@name='externalRef']"), newExternalRefId);

        String savedAccountingCode = TestBudgetAndAccounting.savedAccountingCode;
        Assert.assertNotNull(savedAccountingCode, "should have a saved accounting code from dependent test");
        SelectUtils.clickItemOfDropDownWithTextInput(driver, By.xpath("//div[@id='add-cost-form-innerCt']//input[@name='accountCodeId']"), savedAccountingCode);


        By by = Locator.inputWithId("file_upload_button"); // save button
       // SeleniumUtils.scrollToElement(driver, by);
        SeleniumUtils.click(driver, by);


        // check the popup goes away
        SeleniumUtils.waitForInVisible(driver, popup);

        // check the variable cost is added
        SeleniumUtils.assertElementExistsWithWait(driver, By.linkText(newFixedCostName));
    }
}
