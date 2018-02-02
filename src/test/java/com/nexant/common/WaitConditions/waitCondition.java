package com.nexant.common.WaitConditions;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.nexant.common.Locator;

/**
 * Created with IntelliJ IDEA.
 * User: akumar
 * Date: 2/5/14
 * Time: 5:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class waitCondition {
    
	private static final Log log = LogFactory.getLog(waitCondition.class);
	
	WebDriver driver;
    
    public waitCondition(WebDriver driver) {
        this.driver = driver;
    }
    
    public WebElement waitForElement(final By by) {
    	return waitForElementInSeconds(by, Locator.Text.WAIT_TIME.intValue());
    }
    
    public WebElement waitForElementInSeconds(final By by, int seconds) {
        Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver)
                .withTimeout(seconds, TimeUnit.SECONDS)
                .pollingEvery(Locator.Text.POLLING_TIME, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        
        try {
        	return wait1.until(new Function<WebDriver, WebElement>() {
	            public WebElement apply(WebDriver driver) {
	                return driver.findElement(by);
	            }
	        });
        } catch(NoSuchElementException ex){
        	throw new NoSuchElementException("cannot find by=" + by + ", url=" + driver.getCurrentUrl() + ", title=" + driver.getTitle(), ex);
        }
    }

    private WebElement waitForClickable(final By by){
        WebDriverWait wait = new WebDriverWait(driver, Locator.Text.WAIT_TIME);
        try {
        	return wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch(TimeoutException ex){
        	throw new TimeoutException("error waiting for element to be clickable for id=" + by + " in url " + driver.getCurrentUrl(), ex);
        }

    }

    public WebElement waitelementToBeClickable(By by) {
        WebElement el = waitForElement(by);
        
        el =  waitForClickable(by);

        if(el == null) {
        	el = driver.findElement(by);
        }
        if(el == null) throw new NoSuchElementException("did not find element for cssSelector=" + by + " in url " + driver.getCurrentUrl());
        return el;
    }

    public WebElement waitelementToBeClickable_Css(final String locator) {
    	return waitelementToBeClickable(By.cssSelector(locator));
    }
    
    public WebElement waitelementToBeClickable_Xpath(final String locator) {
    	return waitelementToBeClickable(By.xpath(locator));
    }

    public WebElement waitelementToBeClickable_Link(final String locator) {
    	return waitelementToBeClickable(By.linkText(locator));
    }
    
    public WebElement waitelementToBeClickable_ID(final String locator) {
    	return waitelementToBeClickable(By.id(locator));
    }
}
