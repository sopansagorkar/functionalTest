package com.nexant.common.pageObjects.dropdownlist;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;

import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.List;
import java.util.Collection;

/**
 * This is Page Object for manipulating Lists. What I'm doing here:
 * 1. Pressing on list to expand
 * 2. Selecting object in list. Sometimes just first in list sometimes specified by Parameter.
 * See description in front of each method.
 */
public class SelectUtils {
    private static final Log log = LogFactory.getLog(SelectUtils.class);

    /**
     * Pressing on text field and press on object in expanded list.
     * @param itemName name of the item in list. Name could be "CA" if you're selecting from states.
     *                 take a look how I'm locating object in list and you'll see that I'm actually inserting passed item name into xpath locator.
     */
    public static void clickItemName(WebDriver driver, final By locator, String itemName) {
    	clickItemOfDropDownWithTextInput(driver, locator, itemName);
    }

    /**
     * Click an item of a drop down
     * @param driver The driver
     * @param locator The drop down locator (input)
     * @param itemName The value of the drop down to select. This can be a function or a text value
     */
    public static void clickItemName(WebDriver driver, final By locator, Locator.XpathTextValue itemName) {
    	clickItemOfDropDownWithTextInput(driver, locator, itemName);
    }

    /**
     *
     * @param driver
     * @param locator
     * @return
     */
    public static WebElement clickFirstItemOfBoundList(WebDriver driver, final By locator) {
        return clickFirstItemOfDropDownWithTextInput(driver, locator);
    }

    /**
     * This is to support ExtJs drop down combo boxes which use a text input, with a drop down button next to it, and a list (somewhere else on the page) that has the drop down elements.
     * ie. this is not an html combo box.
     * The locator provided should be the locator of the text input element.
     * It then attempts to find the button, click it, find the drop down list, and select the first item
     * 
     * Note in ExtJs 5, there are a couple of styles for the drop down elements (which are in a hidden div usually at the bottom of the page under the body element)
     * (the list should be in a div with a class 'x-boundlist-list-ct', and items in the list should be in a li or div with a class 'x-boundlist-item')
     *  - list elements are in a ul/li format
     *  - list elements can be in a ul/div format 
     * @param driver the driver
     * @param locator The locator of the text input field used for the combo box
     * @return WebElement The item that is clicked
     */
    public static WebElement clickFirstItemOfDropDownWithTextInput(WebDriver driver, final By locator) {

        WebElement found = findBoundList(driver, locator);

        // click the first one
        WebElement firstItem = null;
        try {
        	firstItem = found.findElement(By.xpath("ul/*[contains(@class, 'x-boundlist-item')]")); // ul/li or ul/div can be used for list items
        } catch(NoSuchElementException ex) {
            log.error("did not find first element in bound list " + SeleniumUtils.debugElement(found) + " on page '" + driver.getCurrentUrl() + "' title '" + driver.getTitle() + "'");
            throw ex;
        }
        log.info("clicking  " + firstItem + " from " + locator + " in bound list on page '" + driver.getCurrentUrl() + "' title '" + driver.getTitle() + "'");
        firstItem.click();

        return firstItem;
    }

    public static WebElement clickItemOfDropDownWithTextInput(WebDriver driver, final By locator, String itemName) {
    	return clickItemOfDropDownWithTextInput(driver, locator, new Locator.XpathText(itemName));
    }
    
    /**
     * Click a given item of a combo box, where the combo box is an ExtJs style combo box (not standard html).
     * The input element is actually a text input, but there is a drop down button next to it to bring up the items that can be selected.
     * This method clicks on the drop down button, and selects the item with the given name.
     * @param driver
     * @param locator The locator of the combo box (an input element)
     * @param itemName The text value of the item to select. This is an interface which provides the text, so raw text and also functions like concat can be used
     * @return WebElement The item actually clicked
     */
    public static WebElement clickItemOfDropDownWithTextInput(WebDriver driver, final By locator, Locator.XpathTextValue itemName) {

        WebElement found = findBoundList(driver, locator);

        try {
            String id = found.getAttribute("id");
            By itemBy = By.xpath("//*[@id='" + id + "']/ul/*[contains(@class, 'x-boundlist-item') and text()[.=" + itemName.getString() + "]]");

            WebElement theItem = SeleniumUtils.waitForClickable(driver, itemBy, 30);
            log.info("clicking  " + theItem + " from " + locator + " in bound list  on page '" + driver.getCurrentUrl() + "' title '" + driver.getTitle() + "'");
            theItem.click();

            return theItem;
        } catch(NoSuchElementException ex){
            throw new NoSuchElementException("did not find item [" + itemName.getString() + "] in the dropdown at [" + locator + "] in the list at " + SeleniumUtils.debugElement(found) + " on page " + driver.getCurrentUrl(), ex);
        }
    }

    /**
     * In Ext, the drop down combo boxes are not html, they have bound list elements elsewhere on the page.
     * This tries to find the bound list of a drop down
     * @param driver
     * @param dropDownLocator The locator of the input element for the drop down
     * @return The WebElement of the bound list (the list containing the combo box choices)
     */
    public static WebElement findBoundList(WebDriver driver, By dropDownLocator) {

        Set<WebElement> boundListsBefore = getAllDisplayedAndEnabledBoundLists(driver);

        SeleniumUtils.waitForElement(driver, dropDownLocator);
        WebElement txtBox = SeleniumUtils.waitForClickable(driver, dropDownLocator);
        String txtBoxId = txtBox.getAttribute("id");
        if(txtBoxId == null) throw new WebDriverException("no id on element by " + dropDownLocator + ": " + SeleniumUtils.debugElement(txtBox));

        By dropDownButtonSelector = By.xpath("//*[@id='" + txtBoxId + "']/../../div[2]");
        SeleniumUtils.moveMouseWithWait(driver, dropDownButtonSelector);
        SeleniumUtils.click(driver, dropDownButtonSelector);
        SeleniumUtils.sleepMillis(500);

        Set<WebElement> boundListsAfter = getAllDisplayedAndEnabledBoundLists(driver);

        boundListsAfter.removeAll(boundListsBefore);
        if (boundListsAfter.size() != 1) {
            throw new RuntimeException("bound lists is not 1 (found " + boundListsAfter.size() + ") by " + dropDownLocator + ", txtBoxId=" + txtBoxId + ", dropDownButtonSelector=" + dropDownButtonSelector + ", ids before=" + getWebElementIds(boundListsBefore) + ", ids after=" + getWebElementIds(boundListsAfter));
        }

        WebElement foundDiv = boundListsAfter.iterator().next();

        log.info("found bound list: " + foundDiv.getAttribute("id"));

        return foundDiv;
    }

    private static Set<String> getWebElementIds(Collection<WebElement> elements) {
        Set<String> ids = new TreeSet<>();
        for(WebElement e: elements) ids.add(e.getAttribute("id"));
        return ids;
    }


    private static Set<WebElement> getAllDisplayedAndEnabledBoundLists(WebDriver driver) {

        Set<WebElement> set = new HashSet<WebElement>();
        By listBy = By.xpath("//div[contains(@class, 'x-boundlist-list-ct')]");

        List<WebElement> allBoundsLists = driver.findElements(listBy);

        for(WebElement boundlist: allBoundsLists) {
            log.info("found bound list by " + listBy + ": " + boundlist.getAttribute("id") + ", isEnabled=" + boundlist.isEnabled() + ", isDisplayed=" + boundlist.isDisplayed());
            if(!boundlist.isEnabled()) continue;
            if(!boundlist.isDisplayed()) continue;
            log.info("Enabled and displayed bound list by " + listBy + ": " + boundlist.getAttribute("id"));
            set.add(boundlist);
        }
        return set;
    }

    /**
     * Some drop downs take text, and then offer selections
     * This types the test, waits for the drop down, then hits enter to select it
     * @param driver
     * @param locator
     * @param itemName
     */
    public static void enterTextToSelect(WebDriver driver, By locator, String itemName) {
        SeleniumUtils.waitForElement(driver, locator);
        WebElement el = SeleniumUtils.click(driver, locator);
        el.clear();
        el.sendKeys(itemName);

        // wait for drop down
        //    By list = By.xpath("//*[@class='x-boundlist-list-ct']");
        //    SeleniumUtils.waitForAtLeastOneElementToBeDisplayedAndEnabled(driver, list, 10);

        el.sendKeys(Keys.RETURN);
    }

    /**
     *
     * @param driver
     * @param locator
     */
    public static void selectFirstItemByArrowDownEnter(WebDriver driver, By locator) {
        SeleniumUtils.waitForElement(driver, locator);
        WebElement el = SeleniumUtils.click(driver, locator);
        el.sendKeys(Keys.ARROW_DOWN);
        el.sendKeys(Keys.RETURN);
    }


    public static void selectDateInTheFuture(WebDriver driver, By locator, int row, int col){

        SeleniumUtils.click(driver, locator);

        By date = By.xpath("//div/table/tbody/tr[" + row + "]/td[" + col + "]/div"); // for 184 you need to change div to a/em/span
        SeleniumUtils.waitForClickable(driver, date);

        // click next month
        SeleniumUtils.click(driver, By.xpath("//div[@data-ref='nextEl']"));

        SeleniumUtils.click(driver, date);
    }

    /**
     * @param  locator locator of calendar button
     * @param date           xpath to date
     */
    public static void regular(WebDriver driver, By locator, final By date) {
        SeleniumUtils.click(driver,  locator);
        SeleniumUtils.click(driver, date);
    }
}
