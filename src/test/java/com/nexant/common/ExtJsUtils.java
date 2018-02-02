package com.nexant.common;

import com.google.common.base.Function;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

/**
 * Utility class like Selenium Utils, but uses functions exposed by DSMC which uses Ext - ComponentQuery to find the components
 * and set the values to those components.
 * This removes dependency on the DOM Structure of element to find and set the value to the component and can work with Ext version change also.
 *
 *
 * @author Suresh Nagar
 * @author $LastChangedBy: snagar $
 * @version $Revision: 0 $, 1/26/2015 3:02 PM
 */


public class ExtJsUtils {

	private static final Log log = LogFactory.getLog(ExtJsUtils.class);

	//ext component types - map 1 to 1 with xtypes.
	public static enum ExtCmpType {
		button,
		textfield,
		datefield,
		combo,
		sliderfield,
		panel
	}

	//component attributes
	public static enum ExtLocatorType {
		id,
		itemId,
		fieldLabel, //for textfields, date fields etc
		name,
		text, //buttons label are texts
		cls
	}


	static long DEFAULT_TIMEOUT_SECONDS = 15L;
	static long DEFAULT_POLLING_MILLISECONDS = 1000L;

	/**
	 * Find and click the button with label using ExtJS
	 * @param driver
	 * @param label
	 * @return
	 */
	public static WebElement clickExtButtonHavingLabel(WebDriver driver, String label) {
		getExtComponentWithWait(driver, ExtCmpType.button, ExtLocatorType.text, label);

		String script = "clickExtButton(\"" + label + "\")";
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement button = (WebElement) executor.executeScript(script);
		return button;
	}

	/**
	 * Find and return dom of the element (into WebElement).
	 * Finds element using ExtJs component query.
	 *
	 * @param driver
	 * @param type - xtype of extjs component
	 * @param locatorType - attribute of component
	 * @param locatorValue - attribute value.
	 * @return
	 */
	public static WebElement getExtComponent(WebDriver driver, ExtCmpType type, ExtLocatorType locatorType, String locatorValue) {

		String script = "return getExtComponentDom('" + type.name() + "','" + locatorType.name() + "','" + locatorValue + "')";
		log.info("Executing Script ->" + script);

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement component = (WebElement) executor.executeScript(script);

		log.info("Got Result ->" + component);

		return component;
	}

	/**
	 * calls getExtComponent(..) with wait..
	 * @param driver
	 * @param type
	 * @param locatorType
	 * @param locatorValue
	 * @return
	 */
	public static WebElement getExtComponentWithWait(WebDriver driver, final ExtCmpType type, final ExtLocatorType locatorType, final String locatorValue) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
				.pollingEvery(DEFAULT_POLLING_MILLISECONDS, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		try {
			return wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					WebElement elem = getExtComponent(driver, type, locatorType, locatorValue);
					log.info("Element polled and received " + elem);
					if (elem != null) {
						return elem;
					}
					return null;
				}
			});
		} catch (TimeoutException ex) {
			throw new TimeoutException("timed out waiting for " + type + "[" + locatorType + "=" + locatorValue + "]", ex);
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("cannot find element " + type + "[" + locatorType + "=" + locatorValue + "]", ex);
		}
	}


	/**
	 * sets the value to the extjs component
	 *
	 * @param driver
	 * @param type - - xtype of extjs component
	 * @param locatorType - - attribute of component
	 * @param locatorValue - - attribute value.
	 * @param value - value to be set to the component.
	 *              for combobox - this can be value or display value - if combo is not loaded, it triggers the combo load and then sets the value when its
	 *              loaded.
	 */
	public static void setExtComponentValue(WebDriver driver, final ExtCmpType type, final ExtLocatorType locatorType, final String locatorValue,
											String value) {
		getExtComponentWithWait(driver, type, locatorType, locatorValue);

		String script = "setExtComponentValue('" + type.name() + "','" + locatorType.name() + "','" + locatorValue + "','" + value + "')";

		log.info("Executing script "+script);

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript(script);
	}



}
