package com.nexant.common.pageObjects.login;

import org.openqa.selenium.WebDriver;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;

/**
 *
 */
public class Login {

    /**
     * This Method is made to process with login.
     * Can pass the following config for the URL 
     * 1. http://host:port/traksmart4/unprotected/login.do  (legacy)
     * 2. http://host:port/traksmart4/
     * 3. http://host:port
     * 4. host:port
     * @param path URL to login page
     * @param login Login string
     * @param password Password string
     */
    public static void login(WebDriver driver, String path, String login, String password) {
    	System.out.println("Login 1");
    	String url = getInternalLoginPath(path);
    	
        /**
         * Get WebDriver with specified path
         */
        driver.get(url);
        /**
         * Maximize browser window
         */
        driver.manage().window().maximize();
        
        SeleniumUtils.setText(driver, Locator.Login.L_LOGIN, login);
        SeleniumUtils.clearThenSetText(driver, Locator.Login.L_PASSWORD, password);
        SeleniumUtils.click(driver, Locator.Login.L_LOGIN_BTN);
        System.out.println("Login 2");
    }
    
    /**
    * Can pass the following config for the URL 
    * 1. http://host:port/traksmart4/unprotected/login.do  (legacy)
    * 2. http://host:port/traksmart4/public/registration.do  (legacy)
    * 3. http://host:port/traksmart4/
    * 4. http://host:port/traksmart4
    * 5. http://host:port/
    * 6. http://host:port
    * 7. host:port
	*/
    public static String getInternalLoginPath(String path) {

    	if(path == null) path = "http://localhost:8080";
    	
    	if(!path.startsWith("http")) path = "http://" + path;
    	
    	if(path.endsWith("/traksmart4/unprotected/login.do")) {
    		return path;
    	}

    	if(path.endsWith("/traksmart4/public/registration.do")) {
    		return path.replace("/public/registration.do", "/unprotected/login.do");
    	}

    	if(path.endsWith("/traksmart4/")) {
    		return path + "unprotected/login.do";
    	}

    	if(path.endsWith("/traksmart4")) {
    		return path + "/unprotected/login.do";
    	}

    	if(path.endsWith("/")) {
    		return path + "traksmart4/unprotected/login.do";
    	}

    	return path + "/traksmart4/unprotected/login.do";
    }
}
