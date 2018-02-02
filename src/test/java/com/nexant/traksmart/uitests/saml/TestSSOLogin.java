package com.nexant.traksmart.uitests.saml;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.webdriver.Driver;

/**
 * 
 */
public class TestSSOLogin {

    private static WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = Driver.get();
    }

    @Test
    public void testSSOLogin(){
    	driver.get("http://localhost:8080/traksmart4/dashboard/dashboard_exec.do");
    	
    	SeleniumUtils.assertPageTitle(driver, "OpenAM - Login", 30);
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.h1Text("Sign in to OpenAM"));
    	SeleniumUtils.clearThenSetText(driver, Locator.inputWithName("callback_0"), "sje1");
    	SeleniumUtils.clearThenSetText(driver, Locator.inputWithName("callback_1"), "Test.123");
    	SeleniumUtils.click(driver, Locator.inputWithValue("Log in"));
    	

    	SeleniumUtils.assertPageTitle(driver, "DSM Central: Dashboard", 30);
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanText("sje1"));
    	SeleniumUtils.click(driver, Locator.anchorText("Logout"));
    	
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.divTextContains("You are logged out"));

    	// logout of open am
    	driver.get("http://localhost:8090/openam");
    	SeleniumUtils.assertPageTitle(driver, "OpenAM - Profile", 30);
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanText("sje1"));
    	SeleniumUtils.click(driver, Locator.anchorText("Log out"));
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.h1Text("You have been logged out."));
    }
    
    @Test
    public void testSSOLoginSerialLoad(){
    	for(int i = 0; i < 100; i++) {
    		testSSOLogin();
    	}
    }

    @Test
    public void testPublicInterfaceSSOLogin(){
    	driver.get("http://localhost:8080/traksmart4/public/saml/login");
    	
    	SeleniumUtils.assertPageTitle(driver, "OpenAM - Login", 30);
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.h1Text("Sign in to OpenAM"));
    	SeleniumUtils.clearThenSetText(driver, Locator.inputWithName("callback_0"), "sje1");
    	SeleniumUtils.clearThenSetText(driver, Locator.inputWithName("callback_1"), "Test.123");
    	SeleniumUtils.click(driver, Locator.inputWithValue("Log in"));
    	

    	SeleniumUtils.assertPageTitle(driver, "DSM Central: TrakSmart", 30);
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.liTextContains("Welcome, sje1@nexant.com"));
    	SeleniumUtils.click(driver, Locator.anchorText("Logout"));
    	
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.h2Text("You are logged out"));

    	// logout of open am
    	driver.get("http://localhost:8090/openam");
    	SeleniumUtils.assertPageTitle(driver, "OpenAM - Profile", 30);
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.spanText("sje1"));
    	SeleniumUtils.click(driver, Locator.anchorText("Log out"));
    	SeleniumUtils.assertElementExistsWithWait(driver, Locator.h1Text("You have been logged out."));
    }
    
    @Test
    public void testPublicInterfaceSSOLoginSerialLoad(){
    	for(int i = 0; i < 100; i++) {
    		testPublicInterfaceSSOLogin();
    	}
    }
}
