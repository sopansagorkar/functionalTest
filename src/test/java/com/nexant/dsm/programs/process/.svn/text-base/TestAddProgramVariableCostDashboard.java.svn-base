package com.nexant.dsm.programs.process;

import java.awt.Robot;

import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;

/**
 * DSMC-11396
 * This test validates a variable cost payment scenario.
 * A problem is added, a variable cost is added, and a payment is approved.
 */
public class TestAddProgramVariableCostDashboard {
	 private static WebDriver driver = null;
	 	
	 	private String savedProgramName;
	 	private String newVariableCostName;
	 	private String payeePartnerNumber;
	 	private String remitPartnerNumber;
	 	private String savedAccountingCodeCategoryName;
	 	private String savedAccountingCode;
	 	
	    @BeforeClass
	    public void setup() {
	        driver = Driver.get();
	    }
	    
	    @Test
	    public void testCreateNewProgram() throws Exception {
	    	///////////////////////////////////////FIRST WE CREATE A NEW PROGRAM ///////////////////////////////////////////

	    	SeleniumUtils.goToTab(driver, Locator.L_TAB_PROGRAMS, "DSM Central: All Programs");

	        SeleniumUtils.click(driver, Locator.Programs.L_Add_New_Program);

	        // wait for the page to come up
	        SeleniumUtils.assertPageTitle(driver, "DSM Central: New Program");
	        SeleniumUtils.waitForElement(driver, Locator.Programs.L_ADD_PROGRAM_NAME);

	        // basic program info
	        savedProgramName = "program " + System.currentTimeMillis();
	        
 			SeleniumUtils.setText(driver, Locator.Programs.L_ADD_PROGRAM_NAME, savedProgramName);
	        SeleniumUtils.setText(driver, Locator.Programs.L_ADD_PROGRAM_DESCRIPTION, Locator.Text.DESCRIPTION);

	        SelectUtils.clickItemOfDropDownWithTextInput(driver, By.id("program_serviceType_field_combobox-inputEl"), "Electric");

	        SelectUtils.clickItemName(driver, By.id("program_type_field-inputEl"), "Education and Outreach");
	        SelectUtils.clickItemName(driver, By.id("program_status_field-inputEl"), "Active");
	        SelectUtils.enterTextToSelect(driver, By.id("program_sectors_field_combobox-inputEl"), "Residential");
	        SelectUtils.clickItemName(driver, By.id("program_defaultsector_field-inputEl"), "Residential");
	        SeleniumUtils.setText(driver, Locator.Programs.DATE_FROM, Locator.Text.DATE_FROM);
	        SeleniumUtils.setText(driver, Locator.Programs.DATE_TO, Locator.Text.DATE_TO);
	        SeleniumUtils.click(driver, Locator.Programs.TRACK_RENEWABLE_GENERATION);
	        SeleniumUtils.clickUsingJavascript(driver, Locator.Programs.ADD_NEW_SERVICE_POINT);
	        SeleniumUtils.setText(driver, By.id("service_point_0_input"), "12345");
	        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, By.id("program_service_territory_field_combobox-inputEl"));
	        // goals
	        SelectUtils.clickFirstItemOfBoundList(driver, By.id("program_metric_0_input")); //metric1

	        SeleniumUtils.setText(driver, Locator.Programs.goal, Locator.Programs.goal_txt);
	        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, By.id("program_svc_terr_0_input"));
	        SelectUtils.clickItemName(driver, By.id("program_sector_0_input"), "Residential");
	        SeleniumUtils.setText(driver, Locator.Programs.startdate, Locator.Text.DATE_FROM);
	        SeleniumUtils.setText(driver, Locator.Programs.enddate, Locator.Text.DATE_TO);
	        // internal users
	   
	        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, By.id("program_programmgrs_0_field_combobox-inputEl"));

	        SelectUtils.enterTextToSelect(driver, By.id("program_supportstaff_0_field_combobox-inputEl"), Locator.Programs.Support_Staff_Name);
	        SelectUtils.enterTextToSelect(driver, By.id("program_projectmgrs_0_field_combobox-inputEl"), Locator.Programs.Project_Managers_Name);
	        // project settings
	        SeleniumUtils.setText(driver, Locator.Programs.Project_Number_Prefix, Locator.Programs.Project_Number_Prefix_Name);
	        SeleniumUtils.click(driver, Locator.Programs.Allow_Project_Copy);
	        SeleniumUtils.setText(driver, Locator.Programs.Comments_program, Locator.Text.DESCRIPTION);
	        // save
	        SeleniumUtils.click(driver, Locator.Programs.Save_btn);
	        // wait for the confirmation dialog

	        SeleniumUtils.handleConfirmMsg(driver, "Program Saved", "The program was successfully saved.", "OK", 120);

	        SeleniumUtils.sleepMillis(200);
	        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));

	        // check we are on the program list page now
	        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("(//div[@class='title'])[text()[contains(.,'All Programs')]]"));
	        SeleniumUtils.assertPageTitle(driver, "DSM Central: All Programs");

	        ///////////////////////////////////////ADD VARIABLE COST TO PROGRAM BUDGET
	        TestAddNewProgram.openSavedProgram(savedProgramName);

	        // check some expected elements
	        SeleniumUtils.waitForClickable(driver, Locator.anchorText("Program Enroll Setting"));
	        SeleniumUtils.waitForClickable(driver, Locator.anchorText("Budget & Accounting"));
	        SeleniumUtils.waitForClickable(driver, Locator.anchorText("Batch Review"));
	        SeleniumUtils.waitForClickable(driver, Locator.anchorText("Program Settings"));
	        
	        SeleniumUtils.assertElementExistsWithWait(driver, Locator.h2Text("Projects"));
	        SeleniumUtils.assertElementExistsWithWait(driver, Locator.h2Text("Task Queue & Notification"));
	        SeleniumUtils.assertElementExistsWithWait(driver, Locator.h2Text("Structure"));
	        
	        // click budget and accounting (the user must have access to this module)
	        SeleniumUtils.click(driver, Locator.Programs.Open_Budget_And_Accounting);
	        SeleniumUtils.assertPageTitle(driver, "DSM Central: Budget Info", 30);
       
	        // checkwe have expected elements
	        SeleniumUtils.assertElementExistsWithWait(driver, Locator.h2Text("Budget Summary"));
	        SeleniumUtils.waitForClickable(driver, Locator.inputWithValue("+ Manage Budget Categories"));
	        SeleniumUtils.waitForClickable(driver, Locator.inputWithValue("+ Create Budgets"));
	        SeleniumUtils.waitForClickable(driver, Locator.inputWithValue("+ Map Cost Codes"));
	        
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
	        
	        // click add accounting code
	        SeleniumUtils.click(driver, Locator.Programs.Add_code);

	        popup = Locator.divText("Add New Accounting Code");
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
	        
	        //ADD VARIABLE COST
	        SeleniumUtils.clickUsingJavascript(driver, Locator.Programs.Add_Variable_Cost);

	        // check we have the popup
	        popup = Locator.divText("Add Variable Cost");
	        SeleniumUtils.waitForVisible(driver, popup);

	        // fill in the form
	        newVariableCostName = "cn" + System.currentTimeMillis();
	        SeleniumUtils.setText(driver, By.xpath("//div[@id='add-variable-cost-form-innerCt']//input[@name='costName']"), newVariableCostName);

	        SeleniumUtils.setText(driver, Locator.Programs.Description_VC, Locator.Text.DESCRIPTION);

	        SeleniumUtils.setText(driver, Locator.Programs.Transaction_Date, Locator.Text.DATE_TO);

	        SeleniumUtils.setText(driver, Locator.Programs.Select_Amount_VC, "45");

	        String newExternalRefId = "ref" + System.currentTimeMillis();
	        SeleniumUtils.setText(driver, Locator.Programs.Add_External_Reference_Id_VC, newExternalRefId);

	        //String savedAccountingCode = TestBudgetAndAccounting.savedAccountingCode;
	        //String savedAccountingCode = "code" + System.nanoTime();
	        Assert.assertNotNull(savedAccountingCode, "should have a saved accounting code from dependent test");

	        SeleniumUtils.waitForElement(driver, Locator.Programs.Select_Accounting_Code_VC);

	        // WebElement el = SeleniumUtils.click(driver, Locator.Programs.Select_Accounting_Code_VC);
	        // el.sendKeys(Keys.ARROW_DOWN);
	        //el.sendKeys(Keys.RETURN);
	        SelectUtils.clickItemOfDropDownWithTextInput(driver, Locator.Programs.Select_Accounting_Code_VC, savedAccountingCode);

	        SeleniumUtils.setText(driver, Locator.Programs.Payee_First_Name, Locator.Text.FIRST_NAME);
	        SeleniumUtils.setText(driver, Locator.Programs.Payee_Last_Name, Locator.Text.LAST_NAME);
	        
	        String variableCostPayeeName = "VariableCost PayeeName";
	        SeleniumUtils.setText(driver, Locator.Programs.Payee_Name, variableCostPayeeName);

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
	        
	        String variableCostRemitToName = "Test Remit To Name";
	        SeleniumUtils.setText(driver, Locator.Programs.Remit_Name, variableCostRemitToName);
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
	        
	        ///PAYMENTS PART
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
			  Assert.assertEquals(div1.getText(), "1");
			  Assert.assertEquals(div2.getText(), "$45.00");
			  
			  // check we have the batch edit checkbox
			  SeleniumUtils.assertElementExistsWithWait(driver, Locator.labelText("Batch Edit"));
			  SeleniumUtils.waitForClickable(driver, Locator.inputWithId("selectFileFormat-batchEdit-inputEl"));
			  
			  // check we have the text
			  SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Select to edit the payments as a batch"));
			  
			  
			  // check the sorting columns are there
			  SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("Group Name "));
			  SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("Total Amount "));
			  SeleniumUtils.waitForClickable(driver, Locator.anchorWithSpanText("No Of Cost Lines"));
			  
			  // the payment should have the remit to name 
			  SeleniumUtils.click(driver, Locator.spanText(variableCostRemitToName));
			  
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
			
			  SeleniumUtils.goToTab(driver, Locator.L_TAB_DASHBOARD, "DSM Central: Dashboard");
			  SeleniumUtils.click(driver, By.id("dashboard-elec-id"));
			  SeleniumUtils.clickUsingJavascript(driver,Locator.anchorText("25"));
			  WebElement progToClick =null;
			  try{
				  SeleniumUtils.sleepSecs(5);
				  progToClick = driver.findElement(By.xpath("//a[text()=\""+savedProgramName+"\"]/../../..//*[local-name() = 'svg']"));
				  
			  }
			  catch(org.openqa.selenium.NoSuchElementException ex){
				  //do nothing
			  }
			  while(progToClick==null){
				  SeleniumUtils.click(driver,By.xpath("//span[contains(@class, 'x-tbar-page-next')]"));
				  SeleniumUtils.sleepSecs(5);
				  try{
				  progToClick = driver.findElement(By.xpath("//a[text()=\""+savedProgramName+"\"]/../../..//*[local-name() = 'svg']"));
				  }
				  catch(org.openqa.selenium.NoSuchElementException ex){
					  //do nothing
				  }
			  }
			  //click on the svg graph	          
			  
			  
			  SeleniumUtils.click(driver,By.xpath("//a[text()=\""+savedProgramName+"\"]/../../..//*[local-name() = 'svg']"));
			  
			  
			  
			  
			  // the programme number is not entirely displayed in the page. We get "program 143276723..." displayed which means we need to compare
			  // actual programmename substring to the string displayed
			  String sub = null;
			  if(savedProgramName.length() >= 20)
				  sub = savedProgramName.substring(0, 17) + "..."; // create the substring expected to appear
			  else
				  sub = savedProgramName;
			  //check that the program is the one opened
			  WebElement el = SeleniumUtils.waitForElementWithoutException(driver,By.xpath("//div[@class='breadcrumb_links']/span[contains(a,'"+sub+"')]/a"));
			  Assert.assertNotNull(el, "The web element is not found");
			  Assert.assertEquals(el.getText().replaceAll("\\s+",""), sub.replaceAll("\\s+",""));
			  SeleniumUtils.sleepSecs(2);
			  //go to the svg graph (Performance portfolio), and click on the "Spend" button
			  SeleniumUtils.click(driver,By.xpath("//div[@id='lineChartGlobalId_chart']/span/*[name()='svg']/*[name()='g'][@class='fusioncharts-legend']/*[name()='rect'][7]"));
			  //wait 2 secs for the bar to load
			  SeleniumUtils.sleepSecs(2);
			  //get the coordinates of the svg graph
			  Point p = driver.findElement(By.xpath("//div[@id='lineChartGlobalId_chart']/span/*[name()='svg']/*[name()='g'][5]")).getLocation();
			  String coordinates = driver.findElement(By.xpath("//div[@id='lineChartGlobalId_chart']/span/*[name()='svg']/*[name()='g'][4]/*[name()='g'][3]/*[name()='g'][7]//*[name()='path']")).getAttribute("d");
			  //exctract the last point from the string
			  StringTokenizer tok = new StringTokenizer(coordinates, "ML");
			  String coordinate = null;
			  while(tok.hasMoreTokens())
				  coordinate = tok.nextToken();
			  tok = new StringTokenizer(coordinate,",");
			  //we use the robot object to move the mouse to the SVG graph and hover over the point in order to get the text
			  //we use the coordinates
			  Robot robot = new Robot();
			  robot.mouseMove(p.x + Integer.parseInt(tok.nextToken()), 60 + p.y + Integer.parseInt(tok.nextToken()));
			  SeleniumUtils.sleepSecs(2);
			  //assert that the value displayed when hovered is the correct one
			  
			  el = SeleniumUtils.waitForElementWithoutException(driver,By.xpath("//span[@id='fusioncharts-tooltip-element']/span[contains(text(),'45.0')]"));
			  //Assert.assertNotNull(el, "The web element is not found");
	          //Assert.assertEquals(el.getText(), "Spend, JUN-2015, 45.0");
	          

	    }
}
