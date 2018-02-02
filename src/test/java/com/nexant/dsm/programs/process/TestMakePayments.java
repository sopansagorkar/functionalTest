package com.nexant.dsm.programs.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestMakePayments {
    private static WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = Driver.get();
        TestAddNewProgram.openSavedProgram();
    }

  
  
    @Test
    public void makePayments() {

        SeleniumUtils.clickUsingJavascript(driver, Locator.anchorTextContains("Payments"));

        SeleniumUtils.waitForClickable(driver, By.linkText("Projects Ready For Payment"));
        SeleniumUtils.waitForClickable(driver, By.linkText("Processed Individual Payments"));
        SeleniumUtils.waitForClickable(driver, By.linkText("Processed Batch Payments"));

        SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyle("Group"));
        SeleniumUtils.waitForClickable(driver, Locator.traksmartButtonAnchorStyle("Proceed"));

        SeleniumUtils.sleepMillis(500);

        SeleniumUtils.waitForAllInVisible(driver, Locator.divText("Loading..."));

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanText("Payments :"));
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanText("Total to pay:"));

		//as per requirement of DSMC-12030 we need to check if the select all checkbox in the grid header is there and present.
		SeleniumUtils.assertElementExistsWithWait(driver, By.cssSelector(".x-column-header-checkbox div"));
		SeleniumUtils.waitForVisible(driver, By.cssSelector(".x-column-header-checkbox div"));

        SeleniumUtils.sleepMillis(500);
        WebElement div1=null;
        WebElement div2=null;

        for (int i = 0; i < 10; i++) {
       	  div1=SeleniumUtils.waitForElementWithoutException(driver, By.xpath("//span[text()[.='Payments :']]/../../div/div"));

      	  div2=SeleniumUtils.waitForElementWithoutException(driver, By.xpath("//span[text()[.='Total to pay:']]/../../div/div"));
            if (div1.getText() ==null || div1.getText().isEmpty() || div2.getText()==null || div2.getText().isEmpty()) {
                SeleniumUtils.sleepSecs(1);
            }
            else{
          	  break;
            }

        }
	      Assert.assertEquals(div1.getText(), "2");
    	  Assert.assertEquals(div2.getText(), "$24.00");



        //SeleniumUtils.assertElementInnerHtmlWithWait(driver, By.xpath("//span[text()[.='Payments :']]/../../div/div"), "1");
        //SeleniumUtils.assertElementInnerHtmlWithWait(driver, By.xpath("//span[text()[.='Total to pay:']]/../../div/div"), "$10.00");

  	  	  SeleniumUtils.click(driver, Locator.spanText("ABC Test Company"));
          SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("Proceed"));
          SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("Approve"));
          SeleniumUtils.clickUsingJavascript(driver, Locator.inputWithId("selectFileFormat-Batch-inputEl"));
          SelectUtils.clickFirstItemOfBoundList(driver, Locator.inputWithId("fileFormat-inputEl"));
          SelectUtils.clickFirstItemOfBoundList(driver, Locator.inputWithId("paymentStatus-inputEl"));
          SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("Save"));
          SeleniumUtils.sleepSecs(5);
          
          
          SeleniumUtils.clickUsingJavascript(driver, By.partialLinkText("Processed Batch Payments"));
          SeleniumUtils.sleepSecs(5);

          SeleniumUtils.moveMouseWithWait(driver, By.xpath("(//div[@class='x-grid-item-container']/table/tbody/tr/td)"));
          SeleniumUtils.clickUsingJavascript(driver, By.xpath("(//div[@class='payment_prog_wrapper']/a[3])"));          
          SeleniumUtils.sleepSecs(5);
          

    }

}
