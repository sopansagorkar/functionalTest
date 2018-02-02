package com.nexant.dsm.programs.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Suresh Nagar
 * @author $LastChangedBy: snagar $
 * @version $Revision: 0 $, 12/16/2015 1:58 PM
 */
public class TestPaymentTemplate {

	private static WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = Driver.get();
		TestAddNewProgram.openSavedProgram();

	}


	@Test
	/**
	 * for the program when template add screen is added, we confirm that delete is not there..
	 */
	public void deleteTemplateTest() {
		SeleniumUtils.clickUsingJavascript(driver, Locator.anchorTextContains("Payments"));

		SeleniumUtils.waitForClickable(driver, By.linkText("Manage Payments Templates"));

		//click manage templates
		SeleniumUtils.click(driver, By.linkText("Manage Payments Templates"));

		SeleniumUtils.waitForClickable(driver, By.linkText("+Add New Payment File Type"));

		SeleniumUtils.click(driver, By.linkText("+Add New Payment File Type"));

		SeleniumUtils.waitForClickable(driver, Locator.inputWithValue("Save"));

		SeleniumUtils.sleepSecs(3);

		SeleniumUtils.assertElementInVisibleWithWait(driver, Locator.inputWithValue("Delete"));

	}
}
