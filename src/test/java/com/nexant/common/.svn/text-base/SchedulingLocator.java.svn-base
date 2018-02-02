package com.nexant.common;

import org.openqa.selenium.By;

/**
 * @author Suresh Nagar
 * @author $LastChangedBy: snagar $
 * @version $Revision: 0 $, 1/16/2015 4:28 PM
 */
public class SchedulingLocator extends Locator {

	//list screen
	public interface List {
		public static final By L_Create_Scheduling_Group = traksmartButtonAnchorStyle("+ Create Scheduling Group");
		//they have same class but appear on different pages.
		public static final By L_Create_Apt_Type = traksmartButtonAnchorStyle("+ Create Appointment Type");

	}

	//group details screen.
	public interface GroupDetails {
		//fields.. -
		public static final By L_Group_Name = By.cssSelector("input[name='name']");
		public static final By L_Group_Location = By.cssSelector("input[name='location']");
		public static final By L_Group_Address1 = By.cssSelector("input[name='address1']");
		public static final By L_Group_Address2 = By.cssSelector("input[name='address2']");
		public static final By L_Group_City = By.cssSelector("input[name='city']");
		public static final By L_Group_PostalCode = By.cssSelector("input[name='postalCode']");
		public static final By L_Group_State = By.cssSelector("input[name='state']");
		public static final By L_Group_Country = By.cssSelector("input[name='country']");
		//actions
		public static final By L_SaveAndContinueButton = traksmartButtonAnchorStyle("Save & Continue");



		public static final By L_Search_Trigger = By.cssSelector(".x-form-search-trigger");
		public static final By L_Search_Member_Result = By.cssSelector(".x-grid-item");
		public static final By L_Search_Member_Result_First_Result = By.cssSelector(".x-grid-item a");
		//member details
		public static final By L_FIRST_EVENT_BLOCK = By.cssSelector(".sch-event:first-child");
		public static final By L_FIRST_MENU_ITEM = By.cssSelector(".x-menu-item");

		public static final By L_Message_Box = By.cssSelector(".x-message-box");

	}

	public interface AppointmentType {
		public static final By L_APT_TYPE_NAME = By.cssSelector("input[name='name']");
		public static final By L_APT_TYPE_DESC = By.cssSelector("textarea[name='description']");

		public static final By L_FIRST_EVENT_BLOCK = By.cssSelector(".sch-event:first-child");
		public static final By L_FIRST_MENU_ITEM = By.cssSelector(".x-menu-item");

		public static final By L_LAST_EVENT_BLOCK = By.cssSelector(".sch-event:last-child");

	}

}
