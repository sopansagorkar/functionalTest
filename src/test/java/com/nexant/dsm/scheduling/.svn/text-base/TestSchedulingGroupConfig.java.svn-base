package com.nexant.dsm.scheduling;

import com.nexant.common.ExtJsUtils;
import com.nexant.common.Locator;
import com.nexant.common.SchedulingLocator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.contextdata.SchedulingContextData;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.dsm.libraries.customers.process.TestNewCustomerAdd;
import com.nexant.webdriver.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.UUID;

/**
 * DSMC-9694 Functional Testing of the scheduling component.
 * This is available as a top level tab in traksmart (Scheduling).
 * The user will need Scheduling Read-Write Entitlement for this tab to show up.
 * @author Suresh Nagar
 * @author $LastChangedBy: snagar $
 * @version $Revision: 0 $, 1/29/2015 12:07 PM
 */
@Test(groups = {"Scheduling"})
public class TestSchedulingGroupConfig {
	private static WebDriver driver;

	@BeforeClass
	public void setup() {
		driver = Driver.get();
	}
	
	public static String savedAppointmentTypeName = null;
	public static String savedSchedulingGroupName = null;

	@Test
	public void testCreateNewAppointmentType() throws Exception {

        SeleniumUtils.goToTab(driver, Locator.L_TAB_SCHEDULING, "DSM Central: All Groups");

		SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Appointment Types"));

		SeleniumUtils.waitForElement(driver, SchedulingLocator.List.L_Create_Apt_Type);

		SeleniumUtils.click(driver, SchedulingLocator.List.L_Create_Apt_Type);

		SeleniumUtils.waitForElement(driver, SchedulingLocator.AppointmentType.L_APT_TYPE_NAME);

		//fill in details..

		String typeName = "AptType_" + UUID.randomUUID();
		SeleniumUtils.setText(driver, SchedulingLocator.AppointmentType.L_APT_TYPE_NAME, typeName);

		//save the group name created.
		SchedulingContextData contextData = SchedulingContextData.getInstance();
		contextData.setAptTypeName(typeName);

		SeleniumUtils.setText(driver, SchedulingLocator.AppointmentType.L_APT_TYPE_DESC, "Appointment type description\n New line");
        SeleniumUtils.sleepSecs(2);
		SeleniumUtils.click(driver, Locator.labelText("Yes"));

		for (int i = 0; i < 2; i++) {
            SeleniumUtils.sleepMillis(100);
			SeleniumUtils.rightClick(driver, SchedulingLocator.AppointmentType.L_FIRST_EVENT_BLOCK);
            SeleniumUtils.sleepMillis(100);
			SeleniumUtils.waitForElement(driver, SchedulingLocator.AppointmentType.L_FIRST_MENU_ITEM);
            SeleniumUtils.sleepMillis(100);
			SeleniumUtils.click(driver, SchedulingLocator.AppointmentType.L_FIRST_MENU_ITEM);
            SeleniumUtils.sleepMillis(200);
		}

//		SeleniumUtils.dragAndDropBy(driver, SchedulingLocator.AppointmentType.L_FIRST_EVENT_BLOCK, -100, 0);
//		SeleniumUtils.dragAndDropBy(driver, SchedulingLocator.AppointmentType.L_LAST_EVENT_BLOCK, 100, 0);
//
//        SeleniumUtils.sleepMillis(500);

		SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Save"));


		By popup = Locator.divText("Success");
		SeleniumUtils.assertElementExistsWithWait(driver, popup);
		By message = By.xpath("//div[text()[.='Appointment type saved successfully']]");
		SeleniumUtils.assertElementExistsWithWait(driver, message);

		By okButton = Locator.spanText("OK");
		SeleniumUtils.clickUsingJavascript(driver, okButton);

		// assert popup goes away
		SeleniumUtils.waitForInVisible(driver, popup);/* */

		savedAppointmentTypeName = typeName;
	}


	@Test
	public void testCreateNewSchedulingGroup() throws Exception {

        SeleniumUtils.goToTab(driver, Locator.L_TAB_SCHEDULING, "DSM Central: All Groups");

		//click create group button
		WebElement createGroupButton = ExtJsUtils.getExtComponentWithWait(driver, ExtJsUtils.ExtCmpType.button, ExtJsUtils.ExtLocatorType.text, "+ Create Scheduling Group");
		SeleniumUtils.clickUsingJavascript(driver, createGroupButton);


		ExtJsUtils.getExtComponentWithWait(driver, ExtJsUtils.ExtCmpType.textfield, ExtJsUtils.ExtLocatorType.name, "name");

		String groupName = "Group_" + UUID.randomUUID();
		ExtJsUtils.setExtComponentValue(driver, ExtJsUtils.ExtCmpType.textfield, ExtJsUtils.ExtLocatorType.name, "name", groupName);

		//save the group name created.
		SchedulingContextData contextData = SchedulingContextData.getInstance();
		contextData.setGroupName(groupName);

		ExtJsUtils.setExtComponentValue(driver, ExtJsUtils.ExtCmpType.textfield, ExtJsUtils.ExtLocatorType.name, "location", "Test Location");

		ExtJsUtils.setExtComponentValue(driver, ExtJsUtils.ExtCmpType.sliderfield, ExtJsUtils.ExtLocatorType.name, "people",
				String.valueOf(new Random().nextInt(20)));

		ExtJsUtils.setExtComponentValue(driver, ExtJsUtils.ExtCmpType.textfield, ExtJsUtils.ExtLocatorType.name, "address1", "1810 Gateway Drive");
		ExtJsUtils.setExtComponentValue(driver, ExtJsUtils.ExtCmpType.textfield, ExtJsUtils.ExtLocatorType.name, "address2", "Suite 360");
		ExtJsUtils.setExtComponentValue(driver, ExtJsUtils.ExtCmpType.textfield, ExtJsUtils.ExtLocatorType.name, "city", "San Mateo");
		ExtJsUtils.setExtComponentValue(driver, ExtJsUtils.ExtCmpType.textfield, ExtJsUtils.ExtLocatorType.name, "postalCode", "95505");
		ExtJsUtils.setExtComponentValue(driver, ExtJsUtils.ExtCmpType.textfield, ExtJsUtils.ExtLocatorType.name, "country", "USA");
		ExtJsUtils.setExtComponentValue(driver, ExtJsUtils.ExtCmpType.combo, ExtJsUtils.ExtLocatorType.name, "state", "CA");


//		ExtJsUtils.clickExtButtonHavingLabel(driver, "Save & Continue");
		WebElement button = ExtJsUtils.getExtComponent(driver, ExtJsUtils.ExtCmpType.button, ExtJsUtils.ExtLocatorType.text, "Save & Continue");
		SeleniumUtils.clickUsingJavascript(driver, button);

		//page is switched to search members page.
		ExtJsUtils.getExtComponentWithWait(driver, ExtJsUtils.ExtCmpType.textfield, ExtJsUtils.ExtLocatorType.itemId, "memberSearchQuery");

        SeleniumUtils.goToTab(driver, Locator.L_TAB_SCHEDULING, "DSM Central: All Groups");

		ExtJsUtils.setExtComponentValue(driver, ExtJsUtils.ExtCmpType.combo, ExtJsUtils.ExtLocatorType.fieldLabel, "", "Name");
		//wait for a second so that parameter is selected
		SeleniumUtils.sleepSecs(1);
		ExtJsUtils.setExtComponentValue(driver, ExtJsUtils.ExtCmpType.textfield, ExtJsUtils.ExtLocatorType.cls, "searchItemValue", contextData.getGroupName());

        SeleniumUtils.sleepSecs(1);
		WebElement searchButton = ExtJsUtils.getExtComponent(driver, ExtJsUtils.ExtCmpType.button, ExtJsUtils.ExtLocatorType.text, "Search");
        SeleniumUtils.sleepSecs(1);
		SeleniumUtils.clickUsingJavascript(driver, searchButton);

        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.sleepSecs(1);
		SeleniumUtils.waitForElement(driver, Locator.anchorText(contextData.getGroupName()));

		savedSchedulingGroupName = groupName;
	}


	@Test(dependsOnMethods = {"testCreateNewAppointmentType","testCreateNewSchedulingGroup"})
	/**
	 * whats being done:
	 * 1. finding group already created
	 * 2. Searching "test" to find members
	 * 3. if result found - pick the first member and add
	 * 	  3.1 add manual member
	 * 4. if not result found add manual member
	 * 5. save group to generate blocks
	 * 6. move to week view
	 * 7. confirm all blocks
	 * 8. save the group.
	 */
	public void addMembersAndGenerateBlocks() throws InterruptedException {
		SchedulingContextData contextData = SchedulingContextData.getInstance();
//		contextData.setGroupName("Group_028886f7-f2c4-408c-86e0-879ed933951a");
//		contextData.setAptTypeName("AptType_b674721c-385f-4d59-b9e0-6d6ed1209994");

		//got to scheduling
        SeleniumUtils.goToTab(driver, Locator.L_TAB_SCHEDULING, "DSM Central: All Groups");

		//search for the group
		ExtJsUtils.setExtComponentValue(driver, ExtJsUtils.ExtCmpType.combo, ExtJsUtils.ExtLocatorType.fieldLabel, "", "Name");

		//wait for a second so that parameter is selected, otherwise the combo waits for store to load and then sets the value which will clear the search
		// item value.
        SeleniumUtils.sleepSecs(1);
		ExtJsUtils.setExtComponentValue(driver, ExtJsUtils.ExtCmpType.textfield, ExtJsUtils.ExtLocatorType.cls, "searchItemValue", contextData.getGroupName());

        SeleniumUtils.sleepSecs(1);
		WebElement searchButton = ExtJsUtils.getExtComponent(driver, ExtJsUtils.ExtCmpType.button, ExtJsUtils.ExtLocatorType.text, "Search");
        SeleniumUtils.sleepSecs(1);
		SeleniumUtils.clickUsingJavascript(driver, searchButton);

        //wait for search results
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.sleepSecs(1);
		SeleniumUtils.waitForElement(driver, Locator.anchorText(contextData.getGroupName()));

		//click settings link
		SeleniumUtils.clickUsingJavascript(driver, Locator.anchorText("Group Settings"));


		//wait for details to load
        SeleniumUtils.waitOnProgressBar(driver, 15);
		ExtJsUtils.getExtComponentWithWait(driver, ExtJsUtils.ExtCmpType.textfield, ExtJsUtils.ExtLocatorType.name, "name");

		//move to members
		WebElement button = ExtJsUtils.getExtComponent(driver, ExtJsUtils.ExtCmpType.button, ExtJsUtils.ExtLocatorType.text, "Members");
		SeleniumUtils.clickUsingJavascript(driver, button);

		ExtJsUtils.setExtComponentValue(driver, ExtJsUtils.ExtCmpType.textfield, ExtJsUtils.ExtLocatorType.itemId, "memberSearchQuery", "Test");

		SeleniumUtils.clickUsingJavascript(driver, SchedulingLocator.GroupDetails.L_Search_Trigger);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.sleepSecs(1);

		WebElement searchResult = SeleniumUtils.waitForElementWithoutException(driver, SchedulingLocator.GroupDetails.L_Search_Member_Result);

		if (searchResult != null) {
			//found search result - click the first member
			SeleniumUtils.clickUsingJavascript(driver, SchedulingLocator.GroupDetails.L_Search_Member_Result_First_Result);

			SeleniumUtils.waitForVisible(driver, SchedulingLocator.GroupDetails.L_FIRST_EVENT_BLOCK);

			for (int i = 0; i < 2; i++) {
                SeleniumUtils.sleepMillis(100);
				SeleniumUtils.rightClick(driver, SchedulingLocator.GroupDetails.L_FIRST_EVENT_BLOCK);
                SeleniumUtils.sleepMillis(100);
				SeleniumUtils.waitForVisible(driver, SchedulingLocator.GroupDetails.L_FIRST_MENU_ITEM);
                SeleniumUtils.sleepMillis(100);
				SeleniumUtils.click(driver, SchedulingLocator.GroupDetails.L_FIRST_MENU_ITEM);
                SeleniumUtils.sleepMillis(100);
				SeleniumUtils.waitForInVisible(driver, SchedulingLocator.GroupDetails.L_FIRST_MENU_ITEM);
                SeleniumUtils.sleepMillis(100);
			}

            SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
            SeleniumUtils.sleepSecs(1);

            By by = Locator.divText(contextData.getAptTypeName());
            SeleniumUtils.scrollToElement(driver, by);
            SeleniumUtils.sleepSecs(1);
            SeleniumUtils.clickUsingJavascript(driver, by);

            SeleniumUtils.sleepSecs(1);

			WebElement addAndSearchAnother = ExtJsUtils.getExtComponent(driver, ExtJsUtils.ExtCmpType.button, ExtJsUtils.ExtLocatorType.text,
					"Add and Search Another Member");
			SeleniumUtils.clickUsingJavascript(driver, addAndSearchAnother);

            SeleniumUtils.sleepSecs(1);

			ExtJsUtils.setExtComponentValue(driver, ExtJsUtils.ExtCmpType.textfield, ExtJsUtils.ExtLocatorType.itemId, "memberSearchQuery",
					UUID.randomUUID().toString());

			SeleniumUtils.clickUsingJavascript(driver, SchedulingLocator.GroupDetails.L_Search_Trigger); // click the search magnifying glass.
            SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
            SeleniumUtils.sleepSecs(1);

			addManualMember();

		} else {
			addManualMember();
		}

		WebElement addAndSearchAnother = ExtJsUtils.getExtComponent(driver, ExtJsUtils.ExtCmpType.button, ExtJsUtils.ExtLocatorType.text,
				"Save & Continue");
		SeleniumUtils.clickUsingJavascript(driver, addAndSearchAnother);

        SeleniumUtils.waitOnProgressBar(driver, 20);


		WebElement weekView = ExtJsUtils.getExtComponentWithWait(driver, ExtJsUtils.ExtCmpType.button, ExtJsUtils.ExtLocatorType.text,
				"Week");
		SeleniumUtils.clickUsingJavascript(driver, weekView);

		WebElement confirmAll = ExtJsUtils.getExtComponentWithWait(driver, ExtJsUtils.ExtCmpType.button, ExtJsUtils.ExtLocatorType.text,
				"Confirm All Appointment Blocks");
		SeleniumUtils.clickUsingJavascript(driver, confirmAll);

        SeleniumUtils.sleepSecs(1);

		WebElement saveGroup = ExtJsUtils.getExtComponentWithWait(driver, ExtJsUtils.ExtCmpType.button, ExtJsUtils.ExtLocatorType.text,
				"Save");
		SeleniumUtils.clickUsingJavascript(driver, saveGroup);

		SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Success"));
		SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Group and appointment blocks saved successfully"));

		SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("OK"));

		SeleniumUtils.assertElementInVisibleWithWait(driver, SchedulingLocator.GroupDetails.L_Message_Box);


	}

	private void addManualMember() throws InterruptedException {

		SchedulingContextData contextData = SchedulingContextData.getInstance();

		WebElement manualEnterButton = ExtJsUtils.getExtComponent(driver, ExtJsUtils.ExtCmpType.button, ExtJsUtils.ExtLocatorType.text,
				"Manually Enter Info for this Employee");
		SeleniumUtils.clickUsingJavascript(driver, manualEnterButton);

		ExtJsUtils.setExtComponentValue(driver, ExtJsUtils.ExtCmpType.textfield, ExtJsUtils.ExtLocatorType.name, "name", "Jon Doe");
		ExtJsUtils.setExtComponentValue(driver, ExtJsUtils.ExtCmpType.textfield, ExtJsUtils.ExtLocatorType.name, "email", "jdoe@nexant.com");
		ExtJsUtils.setExtComponentValue(driver, ExtJsUtils.ExtCmpType.textfield, ExtJsUtils.ExtLocatorType.name, "phone", "408-448-8845");

		WebElement saveMember = ExtJsUtils.getExtComponent(driver, ExtJsUtils.ExtCmpType.button, ExtJsUtils.ExtLocatorType.text,
				"Save");
		SeleniumUtils.clickUsingJavascript(driver, saveMember);

		SeleniumUtils.waitForVisible(driver, SchedulingLocator.GroupDetails.L_FIRST_EVENT_BLOCK);

		for (int i = 0; i < 2; i++) {
            SeleniumUtils.sleepMillis(100);
			SeleniumUtils.rightClick(driver, SchedulingLocator.GroupDetails.L_FIRST_EVENT_BLOCK);
            SeleniumUtils.sleepMillis(100);
			SeleniumUtils.waitForVisible(driver, SchedulingLocator.GroupDetails.L_FIRST_MENU_ITEM);
            SeleniumUtils.sleepMillis(100);
			SeleniumUtils.click(driver, SchedulingLocator.GroupDetails.L_FIRST_MENU_ITEM);
            SeleniumUtils.sleepMillis(100);
			SeleniumUtils.waitForInVisible(driver, SchedulingLocator.GroupDetails.L_FIRST_MENU_ITEM);
            SeleniumUtils.sleepMillis(100);
		}

        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.sleepSecs(1);
        By by = Locator.divText(contextData.getAptTypeName());
        SeleniumUtils.scrollToElement(driver, by);
        SeleniumUtils.sleepSecs(1);
        SeleniumUtils.clickUsingJavascript(driver, by);

        SeleniumUtils.sleepSecs(1);

		WebElement reviewMembers = ExtJsUtils.getExtComponent(driver, ExtJsUtils.ExtCmpType.button, ExtJsUtils.ExtLocatorType.text,
				"Add Member & Review");
		SeleniumUtils.clickUsingJavascript(driver, reviewMembers);

        SeleniumUtils.sleepSecs(1);
	}


	/**
	 * Tests an appointment request can be made from the customer library
	 * see DSMC-9694, and subtask DSMC-10163
	 * @throws Exception
	 */
	@Test(dependsOnMethods = {"addMembersAndGenerateBlocks"})
	public void addScheduleAppointmentFromCustomerLibrary() throws Exception {
		
		// need a saved customer
		Assert.assertNotNull(TestNewCustomerAdd.savedCustomerNumber, "need to have first saved a customer, see TestNewCustomerAdd");
		Assert.assertNotNull(TestNewCustomerAdd.savedCustomerFirstName, "need to have first saved a customer, see TestNewCustomerAdd");
		Assert.assertNotNull(TestNewCustomerAdd.savedCustomerLastName, "need to have first saved a customer, see TestNewCustomerAdd");
		
        // click libraries -> customers
		 SeleniumUtils.goToTab(driver, Locator.L_TAB_CUSTOMERS,  "DSM Central: Customer Library");

        for(int i=0; i< 5; i++) {
            //SelectUtils.clickItemName(driver, Locator.divContainsClass("x-form-arrow-trigger-default"), "Customer Number");
            SelectUtils.clickItemOfDropDownWithTextInput(driver, By.xpath("//div[@id='parameter_search_container']//input[@role='combobox']"), "Customer Number");
            SeleniumUtils.clearThenSetText(driver, By.xpath("//div[@id='parameter_search_container']//input[@role='textbox']"), TestNewCustomerAdd.savedCustomerNumber);
            SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Search"));
            SeleniumUtils.waitForInVisible(driver, Locator.spanText("Loading..."));
            WebElement el = SeleniumUtils.waitForClickableWithoutException(driver, Locator.anchorText("ID# " + TestNewCustomerAdd.savedCustomerNumber));
            if (el != null) {
                break;
            } else {
                SeleniumUtils.sleepSecs(3);
            }
        }

        SeleniumUtils.click(driver, Locator.anchorText("ID# " + TestNewCustomerAdd.savedCustomerNumber));

        SeleniumUtils.assertPageTitle(driver, "DSM Central: Customer Detail", 30); // make sure we are on the right page.

        // click add appointment
        SeleniumUtils.click(driver, Locator.CustomerAppointment.CUSTOMER_APPOINTMENT_LINK);
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("+ Add Appointment"));

        By popupTitle = Locator.divText("Create Appointment Request");
        SeleniumUtils.assertElementExistsWithWait(driver, popupTitle);
        
        // the page has other input elements with the same name, so we find elements within the 'Create Appointment Request' popop only
        By popupWindowLocator = By.xpath("//div[text()[.='Create Appointment Request']]/../../../../..");
        WebElement popupWindowElement = SeleniumUtils.waitForElement(driver, popupWindowLocator);
        SeleniumUtils.assertElementHasClass(popupWindowElement, "x-window");
        SeleniumUtils.assertElementHasTagName(popupWindowElement, "div");
        String popupWindowId = popupWindowElement.getAttribute("id");
        WebElement windowParent = popupWindowElement.findElement(By.xpath(".."));
        SeleniumUtils.assertElementHasTagName(windowParent, "body"); // parent of popup window should be the body of the page
        
        By customerNumberLocator = Locator.inputWithNameWithinElementId(popupWindowId, "customerNumber");
        WebElement customerNumberInput = SeleniumUtils.waitForElement(driver, customerNumberLocator);
        //Assert.assertEquals(customerNumberInput.getAttribute("placeholder"), "Customer Number", "There should be a place holder in the customer number input, called 'Customer Number', by=" + customerNumberLocator + ", tag=" + customerNumberInput.getTagName() + ", id=" + customerNumberInput.getAttribute("id") + ", name=" + customerNumberInput.getAttribute("name") + ", type=" + customerNumberInput.getAttribute("type") + ", role=" + customerNumberInput.getAttribute("role") + ", class=" + customerNumberInput.getAttribute("class") + ", value=" + customerNumberInput.getAttribute("value") + ", text=" + customerNumberInput.getText());
        Assert.assertEquals(customerNumberInput.getAttribute("value"), TestNewCustomerAdd.savedCustomerNumber, "Customber Number should be pre-filled into the form");
        
        // enter a phone number
        By phoneNumberLocator = Locator.inputWithNameWithinElementId(popupWindowId, "phone");
        SeleniumUtils.clearThenSetText(driver, phoneNumberLocator, "3102454444");

        // enter an email
        By emailLocator = Locator.inputWithNameWithinElementId(popupWindowId, "email");
        WebElement e = SeleniumUtils.clearThenSetText(driver, emailLocator, "unittest@nexant.com");
        Assert.assertEquals(e.getAttribute("value"), "unittest@nexant.com", "should have set email");

        // select the group, then the type
        By groupLocator = Locator.inputWithNameWithinElementId(popupWindowId, "groupId");
        SelectUtils.clickItemOfDropDownWithTextInput(driver, groupLocator, savedSchedulingGroupName);

        SeleniumUtils.sleepSecs(1); // maybe sleep a bit since the next combo is loaded with the associated types for that group

        By typeLocator = Locator.inputWithNameWithinElementId(popupWindowId, "typeId");
        SelectUtils.clickItemOfDropDownWithTextInput(driver, typeLocator, savedAppointmentTypeName);

        // select the preferred appointment time
        By preferredDateLocator = Locator.inputWithNameWithinElementId(popupWindowId, "requestedDate1Millis");
        SeleniumUtils.sleepSecs(1);
        SelectUtils.clickFirstItemOfDropDownWithTextInput(driver, preferredDateLocator);
        SeleniumUtils.sleepMillis(200);
        
        // set notes
        SeleniumUtils.setText(driver, Locator.textareaWithNameWithinElementId(popupWindowId, "notes"), "my notes...");
        
        // submit 
        SeleniumUtils.click(driver, Locator.traksmartButtonAnchorStyle("Create"));
        
        
        // check it is created (the popup should go away, and the appointment listed on the page)
        SeleniumUtils.waitForInVisible(driver, popupTitle);
        String customerDisplayname = TestNewCustomerAdd.savedCustomerFirstName + " " + TestNewCustomerAdd.savedCustomerLastName;
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.anchorText(customerDisplayname));
	}

}

