package com.nexant.dsm.libraries.equipment.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.UUID;

@Test(groups = {"Equipment"})
public class TestAddNewEquipment {

	private static String equipmentType = "AC";
	private static String attributeCategory = "Directly-related";
	private static String attributeName = "AHRI #";
	private static String attributeValue = String.valueOf(new Random().nextInt(1000));


	private static WebDriver driver;

	@BeforeClass
	public static void setup() {
		driver = Driver.get();
	}


	private static By equipmentTypeLocator(String equipmentTypeName) {
		return By.xpath("(//*[@id='eqtype_filter_container']//ul/li)[text()[contains(.,'" + equipmentTypeName + "')]]");
	}

	@Test
	public void testAddNewEquipment() {

        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_LIBRARIES, Locator.L_LIBRARIES_EQUIPMENT, "DSM Central: Equipment Library");
        SeleniumUtils.waitForClickable(driver, By.xpath("//span[@class='link']//a[text()[.='Equipment']]"));// make sure page is loaded. equipment in the bread crumbs
		SeleniumUtils.click(driver, Locator.Equipment.L_ADD_NEW_EQUIPMENT);

		testAddNewEquipment("DEFAULT");

        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_LIBRARIES, Locator.L_LIBRARIES_EQUIPMENT, "DSM Central: Equipment Library");
        SeleniumUtils.waitForClickable(driver, By.xpath("//span[@class='link']//a[text()[.='Equipment']]"));// make sure page is loaded. equipment in the bread crumbs
		SeleniumUtils.click(driver, Locator.Equipment.L_ADD_NEW_EQUIPMENT);

		testAddNewEquipment("ONSITE_EQUIPMENT");
	}


	private void testAddNewEquipment(String source) {

        SeleniumUtils.assertPageTitle(driver, "DSM Central: Equipment Library");
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divText("Equipment Detail"));
		//select source and type.
        SeleniumUtils.sleepSecs(1);
		SelectUtils.clickItemName(driver, Locator.Equipment.L_SOURCE_COMBO, source);
		SelectUtils.clickItemName(driver, Locator.Equipment.L_CATEGORY_COMBO, equipmentType);

		// set name and description
		SeleniumUtils.setText(driver, Locator.Equipment.L_EQUIPMENT_NAME, "Equipment_" + UUID.randomUUID());
		SeleniumUtils.setText(driver, Locator.Equipment.L_EQUIPMENT_DESCR, "Equipment Description..");


		//add attributes
		SeleniumUtils.clickUsingJavascript(driver, Locator.spanText("Add"));
		SelectUtils.clickItemName(driver, Locator.Equipment.L_SELECT_CATEGORY, attributeCategory);

        // the attribute drop down is loaded based on the category selected above...need to wait for the attributes to be loaded...
        SeleniumUtils.sleepSecs(1);
		SelectUtils.clickItemOfDropDownWithTextInput(driver, Locator.Equipment.L_SELECT_ATTRIBUTE, attributeName);

		SeleniumUtils.setText(driver, Locator.Equipment.L_ENTER_VALUE, attributeValue);
		SeleniumUtils.clickUsingJavascript(driver, Locator.Equipment.L_SAVE_ATTRIBUTE);


		//save the equipment.
		SeleniumUtils.clickUsingJavascript(driver, Locator.Equipment.L_SAVE_EQUIPMENT);


		// a popup confirmation shows
		By popup = Locator.divText("Save Equipment Successful");
		SeleniumUtils.assertElementExistsWithWait(driver, popup);

		By message = By.xpath("//div[text()[.='Equipment save succeeded.']]");
		SeleniumUtils.assertElementExistsWithWait(driver, message);

		By okButton = Locator.spanText("OK");
		SeleniumUtils.clickUsingJavascript(driver, okButton);

		// assert popup goes away
		SeleniumUtils.waitForInVisible(driver, popup);/* */
	}

//	@Test(dependsOnMethods = {"testAddNewEquipment"})
	public void testSearchForAddedEquipment() {
        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_LIBRARIES, Locator.L_LIBRARIES_EQUIPMENT, "DSM Central: Equipment Library");
		testSearchForAddedEquipment("DEFAULT");
        SeleniumUtils.goToSubTab(driver, Locator.L_TAB_LIBRARIES, Locator.L_LIBRARIES_EQUIPMENT, "DSM Central: Equipment Library");
		testSearchForAddedEquipment("ONSITE_EQUIPMENT");

	}


	private void testSearchForAddedEquipment(String source) {


		By sourceLi = Locator.liTextContains(source);
		SeleniumUtils.clickUsingJavascript(driver, sourceLi);


		SeleniumUtils.click(driver, equipmentTypeLocator(equipmentType));

		// select category
		SelectUtils.clickItemName(driver, By.cssSelector("#cat_1_field-inputEl"), attributeCategory);

		// select attribute
		SelectUtils.clickItemName(driver, By.cssSelector("#attr_1_field-inputEl"), attributeName);

		// select condition
		SelectUtils.clickItemName(driver, By.cssSelector("#cond_1_field-inputEl"), "Equals");

		SeleniumUtils.setText(driver, By.cssSelector("#valcenter_1_field-inputEl"), attributeValue);

		SeleniumUtils.click(driver, By.cssSelector("#searchEquipment"));

		// loading box should go away
		SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."), 30);

		By by = By.className("library_user_title");
		SeleniumUtils.assertElementInnerHtmlWithWait(driver, by, equipmentType); // need to wait a while for the search!
	}

}
