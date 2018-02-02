package com.nexant.dsm.programs.process;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;

public class TestAddDsmTestProjectInput {
	 private static WebDriver driver;

	    @BeforeMethod(dependsOnGroups={"TestCreateWorkflow"})
	    public void setup() {
	        driver = Driver.get();
	        TestAddNewProgram.openSavedProgram();
	    }
	    
	    @Test
	    public void createProjectTestInput() {
	    	
	    	By by = By.xpath("//div[@id='projects']//a[text()[contains(.,'" + Locator.Programs.Project_Number_Prefix_Name + "')]]");
	        List<WebElement> projects = SeleniumUtils.waitForElements(driver, by);
	        for (int i = 0; i < 10; i++) {
	            projects = SeleniumUtils.waitForElements(driver, by);
	            if (projects.size() == 0) {
	                SeleniumUtils.sleepSecs(1);
	                SeleniumUtils.click(driver, Locator.spanTextContains("COMPLETED("));
	            }
	        }

	        Assert.assertNotEquals(projects.size(), 0, "There should be at least one project to fill the data and complete");

	        WebElement projectLink = projects.get(0);
	        
	        
		    SeleniumUtils.clickUsingJavascript(driver, projectLink);

		    SeleniumUtils.assertPageTitle(driver, "DSM Central: Project Detail", 30);
		    
		    //Click on Performace
		    SeleniumUtils.click(driver, By.xpath("//div[@id='subnav']//a[text()[contains(.,'Performance')]]"));

		    //Click on + Add Project Input
		    SeleniumUtils.clickUsingJavascript(driver, By.xpath("//input[@value = '+ Add Project Input']"));

	        //Create a new DSM Input
	        String name = "DSM Input Name" + System.currentTimeMillis();
	        SeleniumUtils.setText(driver, Locator.ProjectDSMInput.PDI_NAME, name);
	        
	        String description = "DSM Input Description" + System.currentTimeMillis();
	        SeleniumUtils.setText(driver, Locator.ProjectDSMInput.PDI_DESCRIPTION, description);	        
	        
	        SelectUtils.clickItemName(driver, Locator.ProjectDSMInput.PDI_SERVICE_DRPBX, Locator.ProjectDSMInput.PDI_ELECTRIC);

	        SelectUtils.clickItemName(driver, Locator.ProjectDSMInput.PDI_YEAR_DRPBX, Locator.ProjectDSMInput.PDI_YEAR);

	        SelectUtils.clickFirstItemOfBoundList(driver, Locator.ProjectDSMInput.PDI_OTHER_DRPBX);

	        	        
	        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Save"));

	        SeleniumUtils.assertPageTitle(driver, "DSM Central: Performance", 30);
	        
	    	SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//ul[@id='library_user_info_text']//a[text()[contains(.,'"+name+"')]]"));
	        
	        
	    }

}
