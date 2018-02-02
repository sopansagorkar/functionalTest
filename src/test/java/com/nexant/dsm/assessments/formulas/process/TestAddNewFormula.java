package com.nexant.dsm.assessments.formulas.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by tshakirov on 2/5/2015.
 */
public class TestAddNewFormula {

    private WebDriver driver;
    private String formulaName = null;
    private String tokenName = null;

    @BeforeClass
    public void setup() {
        driver = Driver.get();
    }

    @Test
    public void navigate(){
        SeleniumUtils.moveThenClick(driver, Locator.L_TAB_ASSESSMENTS, Locator.AS_ASSESSMENTS_FORMULAS);
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Formula List");
    }

    @Test(dependsOnMethods={"navigate"})
    public void addNewFormula(){
        formulaName = "FR" + System.currentTimeMillis();
        SeleniumUtils.click(driver, Locator.Assessments.ADD_NEW_FORMULA);
     //   SeleniumUtils.assertPageTitle(driver, "DSM Central: Program Structure");
        SeleniumUtils.setText(driver, Locator.Assessments.AS_FORMULA_NAME, formulaName);
        SelectUtils.clickItemName(driver, Locator.Assessments.AS_FORM_ACTIVE_INDICATOR, "Inactive");
        SeleniumUtils.setText(driver, Locator.Assessments.AS_FORMULA_FIELD, "2+2");
        SeleniumUtils.click(driver, Locator.Assessments.AS_CALCULATE_BTN);
        String result = SeleniumUtils.getText(driver, Locator.Assessments.AS_FORMULA_RESULT);
    }

    @Test(dependsOnMethods={"addToken"})
    public void addVariable(){
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_ADD_NEW_VAR);

        SeleniumUtils.clickUsingJavascript(driver, By.xpath("//td/div[text()[.='Numeric']]"));
        SelectUtils.clickItemName(driver, Locator.Assessments.AS_DATA_TYPE, "Non-Numeric");

        SeleniumUtils.clickUsingJavascript(driver, By.xpath("//td/div[text()[.='val']]"));
        SeleniumUtils.clearThenSetText(driver, Locator.Assessments.AS_VARIABLE_NAME, "Automation");

        SeleniumUtils.clickUsingJavascript(driver, By.xpath("//td/div[text()[.='0']]"));
        SeleniumUtils.clearThenSetText(driver, Locator.Assessments.AS_TEST_VALUE, "20");

        String attr = SeleniumUtils.getAttributeValue(driver, Locator.spanText("Default Value"), "id");
        SeleniumUtils.clickUsingJavascript(driver, By.cssSelector(".x-grid-cell-"+attr.substring(0, attr.length()-7)));
        SeleniumUtils.setText(driver, Locator.Assessments.AS_DEFAULT_TEXT, "23");

        attr = SeleniumUtils.getAttributeValue(driver, Locator.spanText("Token"), "id");
        SeleniumUtils.clickUsingJavascript(driver, By.cssSelector(".x-grid-cell-"+attr.substring(0, attr.length()-7)));
        SelectUtils.clickItemName(driver, Locator.Assessments.AS_TOKEN, "[ T ]" + tokenName);

        SeleniumUtils.clickUsingJavascript(driver, By.cssSelector(".x-grid-row-checker"));

        SeleniumUtils.sleepSecs(5);

        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_SAVE_FORMULA);

        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_SAVE_FORMULA);

        SeleniumUtils.handleConfirmMsg(driver, "Saved", "Formula changes are saved", "OK");

        SeleniumUtils.sleepSecs(5);
    }

    @Test(dependsOnMethods={"addNewFormula"})
    public void addToken(){
        tokenName = "TK" + System.currentTimeMillis();
        SeleniumUtils.click(driver, Locator.Assessments.AS_ADD_TOKEN);
        SeleniumUtils.waitForElement(driver, Locator.divText("Add Token"));
        SeleniumUtils.setText(driver, Locator.Assessments.AS_TOKEN_NAME, tokenName);
        SelectUtils.clickItemName(driver, Locator.Assessments.AS_TOKEN_TYPE, "Assessment Program");
        SelectUtils.clickItemOfDropDownWithTextInput(driver, Locator.Assessments.AS_ATTRIBUTE_NAME, "ADT_Ass_Start_Time");
        SeleniumUtils.setText(driver, Locator.Assessments.AS_EXPRESSION_NAME, "Expression");
        SeleniumUtils.sleepSecs(5);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Assessments.AS_SAVE_TOKEN);
        SeleniumUtils.sleepSecs(5);
   //     SeleniumUtils.waitForElement(driver, Locator.divText("Token saved."));
        SeleniumUtils.handleConfirmMsg(driver, "Success", "Token saved.", "OK");
   //     SeleniumUtils.assertElementNotExists(driver, Locator.divText("Token saved."));
    }

    @Test(dependsOnMethods={"addVariable"})
    public void verifyThatProgramWasCreated(){
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Formula List");
        SeleniumUtils.assertElementExistsWithWait(driver, By.linkText(formulaName));
    }

}
