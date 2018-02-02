package com.nexant.common;

import org.openqa.selenium.By;

public class Locator {
	
	/**
	 * Provider of a string xpath value
	 * This has a little interface to support string values and string
	 * values created by functions like concat.
	 */
	public static interface XpathTextValue {
		String getString();
	}

	/**
	 * A raw string value, rendered as 'value'
	 */
	public static class XpathText implements XpathTextValue {
		private String v;
		
		public XpathText(String v){
			this.v = v;
		}
		
		@Override public String getString() {
			return "'" + (v==null?"":v) + "'";
		}
	}

	/**
	 * A string value that is actualy a function, like concat('v1', 'v2')
	 * These will be rendered as is.
	 * @author sevans
	 *
	 */
	public static class XpathTextViaFunction implements XpathTextValue {
		private String function;
		
		public XpathTextViaFunction(String function){
			this.function = function;
		}
		
		@Override public String getString() {
			return function;
		}
	}
	
	/**
	 * This can be used to escape single quotes using concat	
	 */
	public static XpathTextValue escapeTextValueOfXpath(String text) {
		if(text == null) return new XpathText(null);
		
		if(!text.contains("'")) return new XpathText(text);
		
		// use concat
		StringBuilder sb = new StringBuilder("concat(");
		int i = 0;
		int previ = 0;
		for(i = text.indexOf('\'', i); i != -1; i = text.indexOf('\'', i)) {
			if(previ != 0) sb.append(",");
			if(previ != i) {
				sb.append("'").append(text.substring(previ, i)).append("'");
				sb.append(",");
			}
			sb.append("\"'\"");
			i++;
			previ = i;
		}
		if(previ != 0 && previ < text.length()) {
			sb.append(",");
			sb.append("'").append(text.substring(previ)).append("'");
		}
		sb.append(")");
		return new XpathTextViaFunction(sb.toString());
	}

    /**
     * Select a span with the given text
     * @param text
     * @return
     */
    public static By spanText(String text){
        return By.xpath("//span[text()[.='" + text + "']]");
    }

    /**
     * Select a span with the given text inside a div that has the given id
     * //div[@id='divid']//span[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By spanTextWithinDivId(String divId, String text){
        return By.xpath("//div[@id='" + divId + "']//span[text()[.='" + text + "']]");
    }

    /**
     * Select a span with the given text inside an element that has the given id
     * //*[@id='id']//span[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By spanTextWithinId(String elementId, String text){
        return By.xpath("//*[@id='" + elementId + "']//span[text()[.='" + text + "']]");
    }
	
    	/**
        * Select a div with the given text inside an element that has the given id
        * //*[@id='id']//div[text()[.='mytext']]
        * @param text
        * @return
        */
       public static By divTextWithinId(String elementId, String text){
           return By.xpath("//*[@id='" + elementId + "']//div[text()[.='" + text + "']]");
       }

    public static By h1Text(String text){
        return By.xpath("//h1[text()[.='" + text + "']]");
    }

    public static By h1TextContains(String text){
        return By.xpath("//h1[text()[contains(.,'" + text + "')]]");
    }

    /**
     * Select a span with the given id
     * @param text
     * @return
     */
    public static By spanId(String id){
        return By.cssSelector("[id='"+id+"']");
    }
    /**
     * Select a span with the given text
     * @param text
     * @return
     */
    public static By h2Text(String text){
        return By.xpath("//h2[text()[.='" + text + "']]");
    }

    public static By h2TextContains(String text){
        return By.xpath("//h2[text()[contains(.,'" + text + "')]]");
    }

    /**
     * Select a span with the given text
     * @param text
     * @return
     */
    public static By spanTextContains(String text){
        return By.xpath("//span[text()[contains(.,'" + text + "')]]");
    }

    /**
     * Select a span containing a class with the given name
     * @param className
     * @return
     */
    public static By spanContainsClass(String className){
        return By.xpath("//span[contains(@class, '" + className + "')]");
    }

    /**
     * Select a div with the given text
     * @param text
     * @return
     */
    public static By divText(String text){
        return By.xpath("//div[text()[.='" + text + "']]");
    }

    /**
     * Select any element with the given text
     * @param text
     * @return
     */
    public static By anyText(String text){
        return By.xpath("//*[text()[.='" + text + "']]");
    }

    /**
     * Select the element with the given text value within the element with the given id
     * @param id The element id to look within
     * @param text The text to look for
     * @return By The locator
     */
    public static By anyTextWithinId(String id, String text){
        return By.xpath("//*[@id='" + id + "']//*[text()[.='" + text + "']]");
    }

    /**
     * A selector for a single element with the given id and the given title
     * @param id
     * @param title
     * @return
     */
    public static By byIdAndTitle(String id, String title){
        return By.xpath("//*[@id='" + id + "' and @title='" + title + "']");
    }


    /**
     * Select any element that contains the given text
     * @param text
     * @return
     */
    public static By anyTextContains(String text){
        return By.xpath("//*[contains(text(),'" + text + "')]");
    }

    /**
     * Select a div containing a class with the given name
     * @param className
     * @return
     */
    public static By divContainsClass(String className){
        return By.xpath("//div[contains(@class, '" + className + "')]");
    }

    /**
     * Select a div with the given text
     * @param text
     * @return
     */
    public static By idText(String id, String text){
        return By.xpath("//*[@id='" + id + "']/text()[.='" + text + "']]");
    }

    /**
     * Select any element within another element id that contains the given text
     * @param id The element id to look within
     * @param text The text to look for
     * @return By The locator
     */
    public static By containsTextWithinId(String id, String text){
        return By.xpath("//*[@id='" + id + "']//*[contains(text(),'" + text + "')]");
    }

    /**
     * Select any element within another element id that contains the given text
     * @param id The element id to look within
     * @param text The text to look for
     * @return By The locator
     */
    public static By textWithinId(String id, String text){
        return By.xpath("//*[@id='" + id + "']//*[text()[.='" + text + "']]");
      
    }

    /**
     * Select any element that contains the given text
     * @param id The element id to look within
     * @param text The text to look for
     * @return By The locator
     */
    public static By anyContainsText(String text){
        return By.xpath("//*[contains(text(),'" + text + "')]");
    }

    /**
     * Select a div with the given text
     * @param text
     * @return
     */
    public static By divTextContains(String text){
        return By.xpath("//div[text()[contains(.,'" + text + "')]]");
    }

    /**
     * Select a div with the given text
     * @param text
     * @return
     */
    public static By liTextContains(String text){
        return By.xpath("//li[text()[contains(.,'" + text + "')]]");
    }

    /**
     * Select a label with the given text
     * @param text
     * @return
     */
    public static By labelText(String text){
        return By.xpath("//label[text()[.='" + text + "']]");
    }

    /**
     * Select a label with the given text
     * //label[text()[contains(.,'mytext')]]
     * @param text
     * @return
     */
    public static By labelTextContains(String text){
        return By.xpath("//label[text()[contains(.,'" + text + "')]]");
    }

    /**
     * Select an anchor with the given text
     * //a[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By anchorText(String text){
        return By.xpath("//a[text()[.='" + text + "']]");
    }

    /**
     * Select an anchor with the given text inside a div that has the given class
     * //div[contains(@class, 'myclass')]//a[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By anchorTextWithinDivWithClass(String divClass, String text){
        return By.xpath("//div[contains(@class, '" + divClass + "')]//a[text()[.='" + text + "']]");
    }

    /**
     * Select an anchor with the given text inside any element that has the given class
     * //*[contains(@class, 'myclass')]//a[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By anchorTextWithinAnyWithClass(String c, String text){
        return By.xpath("//*[contains(@class, '" + c + "')]//a[text()[.='" + text + "']]");
    }

    /**
     * Select an anchor with the given text inside any element that has the given class
     * //*[contains(@class, 'myclass')]//a[contains(text(),'mytext')]
     * @param text
     * @return
     */
    public static By anchorTextContainsWithinAnyWithClass(String c, String text){
        return By.xpath("//*[contains(@class, '" + c + "')]//a[contains(text(),'" + text + "')]");
    }

    /**
     * Select an anchor with the given text inside any element that has the given id
     * //*[@id='myid']//a[contains(text(),'mytext')]
     * @param text
     * @return
     */
    public static By anchorTextContainsWithinId(String elementId, String text){
        return By.xpath("//*[@id='" + elementId + "']//a[contains(text(),'" + text + "')]");
    }

    /**
     * Select an anchor with the given text inside a div that has the given id
     * //div[@id='myid']//a[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By anchorTextWithinDivId(String divId, String text){
        return By.xpath("//div[@id='" + divId + "']//a[text()[.='" + text + "']]");
    }

    /**
     * Select an anchor with the given text inside a div that has the given id
     * //*[@id='myid']//a[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By anchorTextWithinId(String elementId, String text){
        return By.xpath("//*[@id='" + elementId + "']//a[text()[.='" + text + "']]");
    }

    /**
     * FInd the element with the given text residing within an element that has the given css class
     * //*[contains(@class, 'myclass')]//*[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By textWithinElementWithClass(String classname, String text){
        return By.xpath("//*[contains(@class, '" + classname + "')]//*[text()[.='" + text + "']]");
    }

    /**
     * Select an div with the given text inside a div with a given class
     * //div[contains(@class, 'myclass')]//div[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By divTextWithinDivWithClass(String divClass, String text){
        return By.xpath("//div[contains(@class, '" + divClass + "')]//div[text()[.='" + text + "']]");
    }

    
    /**
     * Select an anchor that contains the given text
     * @param text
     * @return
     */
    public static By anchorTextContains(String text){
        return By.xpath("//a[text()[contains(.,'" + text + "')]]");
    }

    /**
     * Select an anchor that as a child span with the given text (EXT style link)
     *  //a/span[text()[.='mytext']]/..
     * @param text
     * @return
     */
    public static By anchorWithSpanText(String text){
        return By.xpath("//a/span[text()[.='" + text + "']]/..");
    }

    /**
     * Select an anchor that as a child span with the given text (EXT style link)
     * //div[contains(@class, 'myclass')]//a/span[text()[.='mytext']]/..
     * @param text
     * @return
     */
    public static By anchorWithSpanTextWithinDivWithClass(String divClass, String text){
        return By.xpath("//div[contains(@class, '" + divClass + "')]//a/span[text()[.='" + text + "']]/..");
    }

    /**
     * Select an anchor that as a child span with the given text (EXT style link)
     * //*[@id='myid')]//a/span[text()[.='mytext']]/..
     * @param text
     * @return
     */
    public static By anchorWithSpanTextWithinId(String elementId, String text){
        return By.xpath("//*[@id='" + elementId + "']//a/span[text()[.='" + text + "']]/..");
    }

    /**
     * Locate a main tab
     * @param name
     * @return
     */
    public static By tab(String name) {
       // return By.linkText(name);
        return By.xpath("//div/ul/li/a[text()[starts-with(.,'" + name + "')]]");
    }

    /**
     * traksmart style buttons that use <button ...><span ...>Save</span><span ..></span></button>
     * @param text
     * @return
     */
    public static By buttonWithSpanText(String text) {
        //return By.xpath("//button/span[text()[.='" + text + "']]/..");
        return By.xpath("(//button/span[text()[.='" + text + "']])[last()]");
    }
	
	
    /**
        * html button element within an id
        * //*[@id='myid']//button[text()[.='mytext']]
        * @param id The element id
        * @param text The text of the button element
        * @return
        */
       public static By buttonWithTextWithinId(String id, String text) {
           return By.xpath("//*[@id='" + id + "']//button[text()[.='" + text + "']]");
       }

    /**
     * traksmart style buttons that use the html pattern: <a ...><span ...><span ...><span ...>Save
     * This is specific probably to the ext version, but is in many places.
     * If methods use this method, then once place can be used to update all the buttons using this style.
     * @param text
     * @return
     */
    public static By traksmartButtonAnchorStyle(String text) {
        //return By.xpath("//button/span[text()[.='" + text + "']]/..");
        return By.xpath("(//a/span/span/span[text()[.='" + text + "']])/../../..");
    }

    /**
     * traksmart style buttons on a popup window that use the html pattern: <a ...><span ...><span ...><span ...>Save
     * @param text
     * @return
     */
    public static By traksmartButtonAnchorStyleOnPopup(String text) {
        //return By.xpath("//button/span[text()[.='" + text + "']]/..");
        return By.xpath("(//div[contains(@class, 'x-window')]//a/span/span/span[text()[.='" + text + "']])/../../..");
    }

    public static By traksmartButtonAnchorStyleOnPopupTitle(String popupTitle, String text) {
        return By.xpath("(//div[text()[.='" + popupTitle + "']]/../../../../..//a/span/span/span[text()[.='" + text + "']])/../../..");
    }

    /**
     * Match on an html input element that has the name attribute set
     * We specify a popup title so we only look within a popup (since there may be other elements on the page)
     * ie. <input name='aaa'>...</input>
     * @param popupTitle The popup title
     * @param inputValue The value attribute of the input element
     * @return By The locator 
     */
    public static By inputWithNameOnPopupTitle(String popupTitle, String inputName) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//input[@name='" + inputName + "']");
    }

    public static By inputWithNameOnPopupTitleParent(String popupTitle, String inputName) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//input[@name='" + inputName + "']/..");
    }

    /**
     * Match on an html input element that has the value attribute set
     * We specify a popup title so we only look within a popup (since there may be other elements on the page)
     * ie. <input value='aaa'>...</input>
     * @param popupTitle The popup title
     * @param inputValue The value attribute of the input element
     * @return By The locator 
     */
    public static By inputWithValueOnPopupTitle(String popupTitle, String inputValue) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//input[@value='" + inputValue + "']");
    }

    public static By divTextWithinPopupTitle(String popupTitle, String divText) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//div[text()[.='" + divText + "']]");
    }

    /**
     * A helper to find any text string within a popup title
     * The popup title is a way to locate the popup without needing the internal ids
     * This is EXT specific helper
     * @param popupTitle The popup title
     * @param text The text to find
     * @return By The Locator
     */
    public static By anyTextWithinPopupTitle(String popupTitle, String text) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//*[text()[.='" + text + "']]");
    }

    public static By spanTextWithinPopupTitle(String popupTitle, String spanText) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//span[text()[.='" + spanText + "']]");
    }

    public static By textWithinPopupTitle(String popupTitle, String text) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//*[text()[.='" + text + "']]");
    }

    /**
     * Match on an html textarea element that has the name attribute set within a popup.
     * We specify a popup title so we only look within a popup (since there may be other elements on the page)
     * ie. <textarea name='aaa'>...</input>
     * @param popupTitle The popup title
     * @param inputValue The name attribute of the input element
     * @return By The locator 
     */
    public static By textareaWithNameOnPopupTitle(String popupTitle, String textAreaName) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//textarea[@name='" + textAreaName + "']");
    }

    public static By inputWithinLabel(String label, String text) {
        return By.xpath("//span[text()[.='" + label + "']]/../..//input[@name='" + text + "']");
    }


    public static By traksmartButtonAnchorStyleOnPopupId(String popupId, String text) {
        return By.xpath("(//*[@id='" + popupId + "']//a/span/span/span[text()[.='" + text + "']])/../../..");
    }

    /**
     * Get an input given the name attribute (<input name='myname' ...)
     * @param name The value of the name attribute of the input element
     * @return By The locator
     */
    public static By inputWithName(String name) {
    	return By.xpath("//input[@name='" + name + "']");
    }


    /**
     * Get an input given the name attribute (<input value='Apply' ...)
     * @param value The value of the input element
     * @return By The locator
     */
    public static By inputWithValue(String value) {
    	return By.xpath("//input[@value='" + value + "']");
    }

    /**
     * Locator an input given a name, but only with an element with the given element id
     * eg to find elements within a section of the page (like a popup box)
     * @param id The element id to look within
     * @param name The input element name attribute value
     * @return The Locator
     */
    public static By inputWithNameWithinElementId(String id, String name) {
    	return By.xpath("//*[@id='" + id + "']//input[@name='" + name + "']");
    }
		
    /**
        * Locator an input given a name, but only with an element with the given element id
        * eg to find elements within a section of the page (like a popup box)
        * @param id The element id to look within
        * @param name The input element name attribute value
        * @return The Locator
        */
       public static By inputWithNameAndTypeWithinElementId(String id, String name, String type) {
       	return By.xpath("//*[@id='" + id + "']//input[@name='" + name + "' and @type='" + type + "']");
       }	

    public static By inputWithType(String type) {
        return By.xpath("//input[@type='" + type + "']");
    }

    /**
     * Get the parent element of input element given the name attribute (parent of <input name='myname' ...)
     * @param name The value of the name attribute of the input element
     * @return By The locator
     */
    public static By inputWithNameParent(String name) {
    	return By.xpath("//input[@name='" + name + "']/..");
    }

    /**
     * Get an input given the name attribute (<input name='myname' ...)
     * @param id The value of the name attribute of the input element
     * @return By The locator
     */
    public static By inputWithId(String id) {
    	return By.xpath("//input[@id='" + id + "']");
    }

    public static By inputWithRole(String role) {
        return By.xpath("//input[@role='" + role + "']");
    }

    /**
     * Get the parent element of input element given the name attribute (parent of <input name='myname' ...)
     * @param id The value of the name attribute of the input element
     * @return By The locator
     */
    public static By inputWithIdParent(String id) {
    	return By.xpath("//input[@id='" + id + "']/..");
    }

    /**
     * Find a textarea anywhere with the given name
     * Careful with a page that may contain multiple textareas with the same name attribute, the locator could match multiple web elements
     * @param name The name attribute value of the textarea element
     * @return By The Locator
     */
    public static By textareaWithName(String name) {
    	return By.xpath("//textarea[@name='" + name + "']");
    }

    /**
     * Get a textarea element given the name attribute, only looking within the given element id
     * eg find a textarea with the given name within a popup
     * @param id The id of an element to look within
     * @param name The name attribute value of a textarea element
     * @return By The Locator
     */
    public static By textareaWithNameWithinElementId(String id, String name) {
    	return By.xpath("//*[@id='" + id + "']//textarea[@name='" + name + "']");
    }

    public static By L_BUTTON_WITH_SAVE_SPAN_TEXT = buttonWithSpanText("Save");
    public static By L_BUTTON_WITH_OK_SPAN_TEXT = buttonWithSpanText("OK");
   public static By L_BUTTON_WITH_UPDATE_SPAN_TEXT = traksmartButtonAnchorStyle("Update");

    public static By L_BUTTON_WITH_SUBMIT_SPAN_TEXT = buttonWithSpanText("Submit");

    public static By L_TAB_DASHBOARD = tab("DASHBOARD");
    public static By L_TAB_PROGRAMS = tab("PROGRAMS");
    public static By L_TAB_PROJECTS = tab("PROJECTS");
    public static By L_TAB_MARKETING = tab("MARKETING");
    public static By L_TAB_PLANNING = tab("PLANNING");
    public static By L_TAB_ASSESSMENTS = tab("Assessments");
    public static By L_TAB_LIBRARIES = tab("LIBRARIES");
    public static By L_TAB_USERS = tab("USERS");
    public static By L_TAB_ADMIN = tab("ADMIN");
	public static By L_TAB_SCHEDULING = tab("SCHEDULING");
	public static By L_TAB_CUSTOMERS = tab("CUSTOMERS");
    /**
     * Locate a library link under the LIBRARIES Tab
     * @param name
     * @return
     */
    public static By library(String name) {
        return By.xpath("//li[@id='tabLibraries']//a[text()[starts-with(.,'" + name + "')]]");
    }

    /**
     * Locate a library link under the LIBRARIES Tab
     * @param name
     * @return
     */
    public static By admin(String name) {
        return By.xpath("//li[@id='tabAdmin']//a[text()[starts-with(.,'" + name + "')]]");
    }

    /**
     * Locate a assessment link under the ASSESSMENTS Tab
     * @param name
     * @return
     */
    public static By assessment(String name) {
        return By.xpath("//li[@id='tabAssessment']//a[text()[starts-with(.,'" + name + "')]]");
    }

    public static By L_ADMIN_SYSTEM = admin("System");
    public static By L_ADMIN_USER = admin("Users");
    public static By L_LIBRARIES_EQUIPMENT = library("Equipment");
    public static By L_LIBRARIES_MEASURES = library("Measures");
    public static By L_LIBRARIES_PARTNERS = library("Partners");
    public static By L_LIBRARIES_SERVICE_PROVIDERS = library("Service Providers");
    public static By L_LIBRARIES_SURVEY = library("Survey");
    public static By L_LIBRARIES_CONTACT_MANAGEMENT = library("Contact Management");
    public static By L_LIBRARIES_DSM_COST_EFFECTIVENESS = library("DSM Cost Effectiveness");

    public static By AS_ASSESSMENTS_ONSITE_PROGRAMS = assessment("Onsite Programs");
    public static By AS_ASSESSMENTS_ASSESSMENTS = assessment("Assessments");
    public static By AS_ASSESSMENTS_FORMULAS = assessment("Formulas");

    public interface Text {
        public static final Long WAIT_TIME = (long) 10;
        public static final Long WAIT_TIME_1 = (long) 3;
        public static final Long POLLING_TIME = (long) 1;
        public static final String ERROR_PLEASE_FIX_THE_VALIDATION_ERRORS = "Please fix the validation errors!";

        public static final String FIRST_NAME = "Automation";
        public static final String LAST_NAME = "Test";
        public static final String ADDRESS1 = "1810 Gateway Dr";
        public static final String ADDRESS2 = "Suite 300";
        public static final String CITY = "San Mateo";
        public static final String COUNTRY = "United States of America";
        public static final String STATE = "CA";
        public static final String ZIP_CODE = "94404";
        public static final String DESCRIPTION = "Nexant iEnergy provides utilities with comprehensive business process management driving the effective rollout and management of an entire portfolio of energy efficiency, renewable energy and demand side management (DSM) programs";
        public static final String EMAIL = "anonymous@nexant.com";
        public static final String PHONE = "123-456-7890";
        public static final String WEBSITE = "http://www.nexant.com";
        public static final String L_SERVPROV_URL = "http://www.nexant.com";
        public static final String LATITUDE = "37.235";
        public static final String LONGITUDE = " -115.811111";
        public static final String PRIMARY_COUNTRY_CODE = "1";
        public static final String PRIMARY_AREA_CODE = "650";
        public static final String PRIMARY_PREFIX_CODE = "258";
        public static final String PRIMARY_SUFFIX_CODE = "2544";
        public static final String PRIMARY_EXT_CODE = "587";
        public static final String ALT_COUNTRY_CODE = "1";
        public static final String ALT_AREA_CODE = "147";
        public static final String ALT_PREFIX_CODE = "258";
        public static final String ALT_SUFFIX_CODE = "4456";
        public static final String ALT_EXT_CODE = "7895";
        public static final String COMPANY_NAME = "Nexant Inc";
        public static final String NAME = "Just automation name";
        public static final String STRING = "Just string from automation";
        public static final String DATE_FROM = "08/22/2020";
        public static final String DATE_TO = "08/22/2030";
        public static final String DATE = "08/14/2016";
        public static final String SECTOR = "Mining";
        public static final String CONTACTEDBY = "QA";
        public static final String NOTE_PROGRAM_NAME = "!-meter tracking";
        public static final String NOTE_PROJECT_NAME = "!-meter tracking";
        public static final String NOTE_TYPE_OF_CONTACT = "Campaign";
        public static final String L_CONTACT_DATE = "08/14/2014";
        public static final String L_CONTACT_BY = "nmtest";
        public static final String L_CONTACT_NUMBER = "123456789";
        public static final String L_SERVPROV_BRANDKEY = "12345AUTO6789";
        public static final String L_SERVPROV_DISTRNUM = "234567";
        public static final String L_TYPE_CONTACT = "Campaign";
        public static final String L_SUBJECT = "Subject Test add";
        public static final String L_NOTE = "Add note test";
        public static final String L_PROGRAM_NOTE = "AZ Smarter Greener Better Commercial Rebates Program";
        public static final String L_Project = "";
        public static final String L_EMAIL_NOTE = "anonymous@nexant.com";
        public static final String L_EMAIL_TEXT = "test";
        public static final String L_FOLLOW_UP = "03/20/2015";


    }

    public interface Login {
        public static final String LOGIN_PATH = ":8080/traksmart4/unprotected/login.do";
        public static final String LOGIN_TRADE_ALLY = ":8080/tradeally/unprotected/login.do";
        public static final String LOGIN_PATH_150_PROJECT = ":8080/traksmart4/projects/project_list.do";
        public static final By L_LOGIN = By.cssSelector("#j_username");
        public static final String LOGIN = "progmgr";
        public static final By L_PASSWORD = By.cssSelector("#j_password");
        public static final String PASSWORD = "Test.123";
        public static final By L_LOGIN_BTN = By.cssSelector("#frmLogin div.form_login input");
        public static final String LOGIN_PATH_150_USER = ":8080/traksmart4/users/user_list.do";
    }

    public interface ServiceProvider {
        public static final By LSP_SORT_BY_NAME = By.xpath("//div/a/span[text()[contains(., 'Name')]]");
        public static final By LSP_SORT_BY_CITY = By.xpath("//div/a/span[text()[contains(., 'City')]]");
        public static final By LSP_SORT_BY_STATE = By.xpath("//div/a/span[text()[contains(., 'State')]]");
        public static final By LSP_SORT_BY_ACTIVE = By.xpath("//div/a/span[text()[contains(., 'Active')]]");
        public static final By LSP_SORT_BY_LAST_UPDATED = By.xpath("//div/a/span[text()[contains(., 'Last Updated')]]");
        public static final By L_ADD_SERVICE_PROVIDER = By.cssSelector("[value='+ Add Service Providers']");
        public static final By L_SERVICE_PROVIDER_NUMBER = By.cssSelector("#serviceProviderNum-inputEl");
        public static final By L_SERVICE_PROVIDER_NAME = By.cssSelector("#serviceProviderName-inputEl");
        public static final By L_ADDRESS_1 = By.cssSelector("#addressLine1_serviceProvider-inputEl");
        public static final By L_ADDRESS_2 = By.cssSelector("#addressLine2_serviceProvider-inputEl");
        public static final By L_CITY = By.cssSelector("#city_serviceProvider-inputEl");
        public static final By L_STATE = By.cssSelector("#state_serviceProvider-trigger-picker");
        public static final By L_STATE1 = By.cssSelector(".x-boundlist-item:nth-child(7)");
        public static final By L_COUNTRY = By.cssSelector("#country_serviceProvider-inputEl");
        public static final By L_ZIP = By.cssSelector("#postalCode_serviceProvider-inputEl");
        public static final By L_BRANDKEY = By.cssSelector("#brandKey-inputEl");
        public static final By L_DISTRICT_NUMBER = By.cssSelector("#districtNum-inputEl");
        public static final By L_URL = By.cssSelector("#url-inputEl");
        public static final By L_PHONE_COUNTRY = By.cssSelector("#textfield-1013-inputEl");
        public static final By L_PHONE_AREA = By.cssSelector("#textfield-1015-inputEl");
        public static final By L_PHONE_PREFIX = By.cssSelector("#textfield-1017-inputEl");
        public static final By L_PHONE_SUFFIX = By.cssSelector("#textfield-1019-inputEl");
        public static final By L_PHONE_EXT = By.cssSelector("#textfield-1021-inputEl");
        public static final By L_FAX_COUNTRY = By.cssSelector("#textfield-1023-inputEl");
        public static final By L_FAX_AREA = By.cssSelector("#textfield-1025-inputEl");
        public static final By L_FAX_PREFIX = By.cssSelector("#textfield-1027-inputEl");
        public static final By L_FAX_SUFFIX = By.cssSelector("#textfield-1029-inputEl");
        public static final By L_FAX_EXT = By.cssSelector("#faxExtension-inputEl");
        public static final By L_ACTIVE = By.cssSelector("#activeFlag-trigger-picker");
        public static final By L_ACTIVE1 = By.cssSelector(".x-boundlist-item:nth-child(1)");
        public static final By L_ACTIVE2 = By.cssSelector(".x-boundlist-item:nth-child(2)");
        public static final By L_DESCRIPTION = By.cssSelector("#description-inputEl");
        public static final By L_SAVE_BTN = traksmartButtonAnchorStyle("Save");
        public static final By L_SERVPROV_SAVEOK = By.cssSelector("#button-1005-btnIconEl");
        public static final By L_SERVPROV_ADCN = By.cssSelector("#service_add_new_content");
        public static final By L_SERVPROV_ADCN_CONTACT_NUMB = By.cssSelector("#contactNumber-inputEl");
        public static final By L_SERVPROV_ADCN_FIRSTNM = By.cssSelector("#contactFirstName-inputEl");
        public static final By L_SERVPROV_ADCN_LASTNM = By.cssSelector("#contactLastName-inputEl");
        public static final By L_SERVPROV_ADCN_EMAIL = By.cssSelector("#contactEmailAddress-inputEl");
        public static final By L_SERVPROV_ADCN_ADDR1 = By.cssSelector("#addressLine1_contact-inputEl");
        public static final By L_SERVPROV_ADCN_ADDR2 = By.cssSelector("#addressLine2_contact-inputEl");
        public static final By L_SERVPROV_ADCN_CITY = By.cssSelector("#city_contact-inputEl");
        public static final By L_SERVPROV_ADCN_STATE = By.cssSelector("#state_contact-inputEl");
        public static final By L_SERVPROV_ADCN_STATE1 = By.xpath("//*[@id=\"state_contact-trigger-picker\"]");
        public static final By L_SERVPROV_ADCN_STATE2 = By.cssSelector(".x-boundlist-item:nth-child(7)");
        public static final By L_SERVPROV_ADCN_COUNTRY = By.cssSelector("#country_contact-inputEl");
        public static final By L_SERVPROV_ADCN_ZIPCODE = By.cssSelector("#postalCode_contact-inputEl");
        public static final By L_SERVPROV_ADCN_PRIMCONT = By.cssSelector("#primaryContact-trigger-picker");
        public static final By L_SERVPROV_ADCN_PRIMCONT2 = By.xpath("//div/ul/li[text()[contains(., 'True')]]");
        public static final By L_SERVPROV_ADCN_SAVE = By.cssSelector("#addNewContact-save-btnIconEl");
        public static final By L_SERVPROV_ADCN_INNER_TXT = By.xpath("//*[@id=\"messagebox-1001-displayfield-inputEl\"]");
        public static By L_BUTTON_WITH_OK_TEXT = By.xpath("//*[@id=\"button-1005-btnIconEl\"]");
        public static By L_HEADER_SUCCESS = By.xpath("//div[7]/div[1]/div/div/div[1]/div");
    }

    public interface Customer {
        public static final By L_ADD_NEW_CUSTOMER = By.cssSelector("[value='+ Add New Customer']");
        public static final By L_CUSTOMER_NUMBER = inputWithName("customerNumber");
        public static final By L_ADDRESS_1 = inputWithName("addressLine1");
        public static final By L_ADDRESS_2 = inputWithName("addressLine2");
        public static final By L_FIRST_NAME = inputWithName("firstName");
        public static final By L_LAST_NAME = inputWithName("lastName");
        public static final By L_CITY = inputWithName("city");
        public static final By L_EMAIL_ADDRESS = inputWithName("emailAddress");
        public static final By L_COUNTRY = inputWithName("country");
        public static final By L_DESCRIPTION = textareaWithName("description");
        public static final By L_ZIP = inputWithName("postalCode");
        public static final By L_LATITUDE = inputWithName("latitude");
        public static final By L_LONGITUDE = inputWithName("longitude");
        public static final By L_PRIMARY_COUNTRY_CODE = inputWithName("phoneCountryCode");
        public static final By L_PRIMARY_AREA_CODE = inputWithName("phoneAreaCode");
        public static final By L_PRIMARY_PREFIX_CODE = inputWithName("phonePrefix");
        public static final By L_PRIMARY_SUFFIX_CODE = inputWithName("phoneSuffix");
        public static final By L_PRIMARY_EXT_CODE = inputWithName("phoneExtension");
        public static final By L_ALT_COUNTRY_CODE = inputWithName("altPhoneCountryCode");
        public static final By L_ALT_AREA_CODE = inputWithName("altPhoneAreaCode");
        public static final By L_ALT_PREFIX_CODE = inputWithName("altPhonePrefix");
        public static final By L_ALT_SUFFIX_CODE = inputWithName("altPhoneSuffix");
        public static final By L_ALT_EXT_CODE = inputWithName("altPhoneExtension");
        public static final By L_SUBMIT_BTN = Locator.traksmartButtonAnchorStyle("Save");
        public static final By L_STATE_DRPBX = By.cssSelector("#state_customer-inputEl");
        public static final By L_SECTOR = By.cssSelector("[name='sectorId']");
        public static final By L_SECTOR_ITEMS = By.cssSelector("[role='option']");
        public static final By L_COMPANY_NAME = inputWithName("customerName");

        public static final By L_CONTACT_DATE = By.xpath("//*[@id=\"contactDateString-inputEl\"]");
        public static final By L_CONTACT_BY = By.xpath("//*[@id=\"contactedBy-inputEl\"]");
        public static final By L_TYPE_CONTACT = By.xpath("//*[@id=\"noteTypeId-inputEl\"]");
        public static final By L_SUBJECT = By.xpath("//*[@id=\"subject-inputEl\"]");
        public static final By L_NOTE = By.xpath("//*[@id=\"notes-inputEl\"]");
        public static final By L_PROGRAM_NOTE = By.xpath("//*[@id=\"programId-inputEl\"]");
        public static final By L_Project = By.xpath("//*[@id=\"projectId-inputEl\"]");
        public static final By followUpRequired = By.xpath("//*[@id=\"followUpRequired-inputEl\"]");
        public static final By L_EMAIL_NOTE = By.xpath("//*[@id=\"followUpEmailAddress-inputEl\"]");
        public static final By L_EMAIL_TEXT = By.xpath("//*[@id=\"followUpEmailText-inputEl\"]");
        public static final By L_FOLLOW_UP = By.xpath("//*[@id=\"followUpDateString-inputEl\"]");
        public static final By L_SAVE_BUTTON = By.xpath("//*[@id=\"file_upload_button\"]");
        public static final By L_CANCELBUTTON = By.xpath("//*[\" value=\"Cancel\"]");
        public static final By L_SERVICE_PROVIDER = By.cssSelector("[name='serviceProviderId']");

        public static final By CREATE_PROJECT = By.linkText("Create Project");
        public static final By SELECT_PROGRAM = inputWithName("programId");

        // Appointments
        public static final By SORT_BY_NAME = By.xpath("//span[@class='x-column-header-text' and text()[.='Name']");


    }

    public interface Common {
        public static final By L_SEARCH_BY_FIELD = By.cssSelector("#keyword_type_filter-inputEl");
        public static final By L_SEARCH_BY_FIELD_MEASURE = By.cssSelector("#keyword_search_textfield-inputEl");
        public static final By L_SEARCH_BY_FIELD_PARTNER = By.cssSelector("#keyword_search_textfield-inputEl");
        public static final By L_SEARCH_BY_FIELD_DSM = By.cssSelector("#keyword_search_textfield-inputEl");
        public static final By L_SEARCH_BY_FIELD_PROGRAM = By.cssSelector("#keyword_search_textfield-inputEl");
        public static final By L_SEARCH_BY_FIELD_USER = By.cssSelector("#keyword_search_textfield-inputEl");
        public static final By L_SEARCH_FIELD = By.cssSelector("#keyword_search_textfield-inputEl");
    }

    public interface Bill_Account {
        public static final By L_ADD_BILL_ACCOUNT_BTN = By.cssSelector("[value='+ Bill Account']");
        public static final By L_ADD_BILL_ACCOUNT_NUMBER = By.cssSelector("#accountNumber-inputEl");
        public static final By L_ADD_BILL_ACCOUNT_NAME = By.cssSelector("#accountName-inputEl");
        public static final By L_ADD_BILL_ACCOUNT_DESCRIPTION = By.cssSelector("#billAcct_description-inputEl");
        public static final By L_ADD_BILL_ACCOUNT_ADDRESS1 = By.cssSelector("#addressLine1_billAccount-inputEl");
        public static final By L_ADD_BILL_ACCOUNT_ADDRESS2 = By.cssSelector("#addressLine2_billAccount-inputEl");
        public static final By L_ADD_BILL_ACCOUNT_CITY = By.cssSelector("#city_billAccount-inputEl");
        public static final By L_ADD_BILL_ACCOUNT_COUNTRY = By.cssSelector("#country_billAccount-inputEl");
        public static final By L_ADD_BILL_ACCOUNT_ZIP = By.cssSelector("#postalCode_billAccount-inputEl");
        public static final By L_ADD_BILL_ACCOUNT_LATITUDE = By.cssSelector("#latitude_billAccount-inputEl");
        public static final By L_ADD_BILL_ACCOUNT_LONGITUDE = By.cssSelector("#longitude_billAccount-inputEl");
        public static final By L_ADD_BILL_ACCOUNT_STATE = By.cssSelector("#state_billAccount-inputEl");
        public static final By L_ADD_BILL_ACCOUNT_SAVE_BTN = By.cssSelector("#bill_account_save_button");
        public static final By L_ADD_BILL_ACCOUNT_CANCEL_BTN = By.xpath("(//div/input[@value='Cancel'])[2]");
        public static final By L_ADD_BILL_ACCOUNT_ACTIVE_INDICATOR_FIELD = By.cssSelector("[name='activeFlag']");
        public static final By L_ADD_BILL_ACCOUNT_REVENUE_CLASS = By.xpath("//input[@name='revenueId']");
        public static final By L_ADD_BILL_ACCOUNT_ACTIVATION_DATE = By.cssSelector("#accountStartDateString-inputEl");
        public static final By L_ADD_BILL_ACCOUNT_TERMINATION_DATE = By.cssSelector("#accountEndDateString-inputEl");
    }

    public interface Note {
    	public static final By L_ADD_NOTE_LINK = Locator.spanId("Notes-btnIconEl");
    	public static final By L_ADD_CUTOMER_NOTE_HEADER = By.id("notesHeader");
        public static final By L_ADD_NOTE_BTN = By.xpath("//input[@value='+ Add Customer Note']"); // //input[@name='']
        public static final By L_ADD_DATE = By.xpath("//input[@componentid='contactDateString']");
        public static final By L_ADD_NOTE_CONTACTED_BY = By.xpath("//input[@componentid='contactedBy']");
        public static final By L_ADD_NOTE_SUBJECT = By.xpath("//input[@componentid=\"subject\"]");
        public static final By L_ADD_NOTE = By.xpath("//textarea[@componentid='notes']");
        public static final By L_ADD_NOTE_TYPE_OF_CONTACT = By.xpath("//input[@id='noteTypeId-inputEl']");
        public static final By L_ADD_NOTE_PROGRAM = By.xpath("//input[@componentid='programId']");
        public static final By L_ADD_NOTE_PROJECT = By.xpath("//input[@componentid='projectId']");
        public static final By L_ADD_NOTE_FOLLOW_UP = By.xpath("//input[@componentid='followUpRequired']");
        public static final By L_ADD_NOTE_EMAIL = By.xpath("//input[@componentid='followUpEmailAddress']");
        public static final By L_ADD_NOTE_EMAIL_TEXT = By.xpath("//textarea[@componentid='followUpEmailText']");
        public static final By L_ADD_NOTE_FOLLOW_UP_DATE = By.xpath("//input[@componentid='followUpDateString']");
        public static final By L_ADD_NOTE_SAVE_BTN = By.xpath("//input[@id='file_upload_button']");
        public static final By L_ADD_NOTE_CANCEL_BTN = By.xpath("(//div/input[@value='Cancel'])[last()]");
        public static final By L_ADD_DATE_OF_CONTACT_CSS = By.xpath("#contactDateString-triggerWrap>tbody>tr>td:nth-child(2)>div");
        public static final By L_ADD_DATE_FROM_CALENDER_CSS = By.xpath(".x-datepicker-inner>tbody>tr:nth-child(4)>td:nth-child(7)>a>em>span");
        //public static final By L_ADD_TYPE_OF_CONTACT_VALUE_XPATH = By.xpath("//div[17]/div/ul/li[1]");

        public static final By L_ADD_SITE_NOTE_BTN = By.xpath("//input[@value='+ Add Site Note']"); 
    }

	public interface CustomerUsage {
		public static final By L_CUSTOMER_USAGE_LINK = Locator.traksmartButtonAnchorStyle("Usage");
	}

    public interface Contact {
        public static final By L_ADD_CONTACT_BTN = By.xpath("//input[@value='+ Add Contact']");
        public static final By L_SET_CONTACT_AS_PRIMARY = By.xpath("//input[@id='primaryContact-inputEl']");
        public static final By L_ADD_CONTACT_NUMBER = By.xpath("//*[@name='contactNumber']");
        public static final By L_ADD_CONTACT_FIRST_NAME = By.xpath("//*[@componentid='contactFirstName']");
        public static final By L_ADD_CONTACT_LAST_NAME = By.xpath("//*[@componentid='contactLastName']");
        public static final By L_ADD_CONTACT_TITLE = By.xpath("//*[@componentid='title_contact']");
        public static final By L_ADD_CONTACT_EMAIL = By.xpath("//*[@componentid='contactEmailAddress']");
        public static final By L_ADD_CONTACT_PHONE_COUNTRY_CODE = By.xpath("//*[@name='phCntryCode']");
        public static final By L_ADD_CONTACT_PHONE_AREA_CODE = By.xpath("//*[@name='phAreaCode']");
        public static final By L_ADD_CONTACT_PHONE_PREFIX_CODE = By.xpath("//*[@name='phPrefix']");
        public static final By L_ADD_CONTACT_PHONE_SUFFIX_CODE = By.xpath("//*[@name='phSuffix']");
        public static final By L_ADD_CONTACT_PHONE_EXT_CODE = By.xpath("//*[@name='phExt']");
        public static final By L_ADD_CONTACT_ADDRESS1 = By.xpath("//*[@componentid='addressLine1_contact']");
        public static final By L_ADD_CONTACT_ADDRESS2 = By.xpath("//*[@componentid='addressLine2_contact']");
        public static final By L_ADD_CONTACT_CITY = By.xpath("//*[@componentid='city_contact']");
        public static final By L_ADD_CONTACT_COUNTRY = By.xpath("//*[@componentid='country_contact']");
        public static final By L_ADD_CONTACT_POSTAL_CODE = By.xpath("//*[@componentid='postalCode_contact']");
        public static final By L_ADD_CONTACT_COMMENTS = By.xpath("//*[@componentid='comments']");
        public static final By L_ADD_CONTACT_STATE = By.xpath("//*[@componentid='state_contact']");
        // /html/body/div[11]/div[2]/div[2]/div/div/div/div[10]/div/div/a[1]/span/span/span[1]
        public static final By L_ADD_CONTACT_SAVE_BTN = By.xpath("//div[10]/div/div/a/span/span/span");
        public static final By L_ADD_CONTACT_CANCEL_BTN = By.xpath("//a/span/span/span[text()[contains(.,'Cancel')]]");
        // title of pop up
        public static final By L_CONTACT_POPUP_HEADER = By.id("contact_popup_header_hd-textEl");
    }

    public interface ContactManagement {
        public static final By L_CONTACT_SEARCH = By.cssSelector("#keyword_1_field-inputEl");
        public static final By L_ADD_CONTACT_BTN = By.cssSelector("[value= '+ Add Contact']");
        public static final By L_SET_CONTACT_COMPANY_NAME = By.xpath("//input[@name='companyName']");
        public static final By L_ADD_CONTACT_FIRST_NAME = By.xpath("//input[@name='firstName']");
        public static final By L_ADD_CONTACT_LAST_NAME = By.xpath("//input[@name='lastName']");
        public static final By L_ADD_CONTACT_TITLE = By.xpath("//input[@name='role']");
        public static final By L_ADD_CONTACT_EMAIL = By.xpath("//input[@name='email']");
        public static final By L_ADD_CONTACT_SECONDARY_EMAIL = By.xpath("//input[@name='secondaryEmail']");
        public static final By L_ADD_CONTACT_PHONE_COUNTRY_CODE = By.xpath("//input[@name='phCntryCode']");
        public static final By L_ADD_CONTACT_PHONE_AREA_CODE = By.xpath("//input[@name='phAreaCode']");
        public static final By L_ADD_CONTACT_PHONE_PREFIX_CODE = By.xpath("//input[@name='phPrefix']");
        public static final By L_ADD_CONTACT_PHONE_SUFFIX_CODE = By.xpath("//input[@name='phSuffix']");
        public static final By L_ADD_CONTACT_PHONE_EXT_CODE = By.xpath("//input[@name='phExt']");
        public static final By L_ADD_CONTACT_ADDRESS1 = By.xpath("//input[@name='address1']");
        public static final By L_ADD_CONTACT_ADDRESS2 = By.xpath("//input[@name='address2']");
        public static final By L_ADD_CONTACT_CITY = By.xpath("//input[@name='city']");
        public static final By L_ADD_CONTACT_COUNTRY = By.xpath("//input[@name='country']");
        public static final By L_ADD_CONTACT_POSTAL_CODE = By.xpath("//input[@name='postalCode']");
        public static final By L_ADD_CONTACT_STATE = By.xpath("//input[@name='state']");
        public static final By L_ADD_NOTE_BTN = By.cssSelector("[value='+ Add Note']");
        public static final By L_ADD_CONTACT_SAVE_BTN = By.cssSelector("[value=Save]");

        // the id of the popup for add note on the contact management page. This is the popup div that will hide and show.
        public static final By L_ADD_NOTE_POPUP = By.id("add_note_popup_header-body");


        // Reminder
        public static final By L_CONTACT_M_REMINDER = By.xpath("//a[text()[.='Reminder']]");
        public static final By REMINDER_SORT_BY_REMINDER_DATE = By.xpath("//div/div/span[text()[.='Reminder Date']]");
        public static final By REMINDER_SORT_BY_NAME = By.xpath("//div/div/span[text()[.='Name']]");
        public static final By REMINDER_SORT_BY_ENTERED_BY = By.xpath("//div/div/span[text()[.='Entered By']]");
        public static final By REMINDER_SORT_BY_DATE_ENTERED = By.xpath("//div/div/span[text()[.='Date Entered']]");
        public static final By REMINDER_SORT_BY_EMAIL = By.xpath("//div/div/span[text()[.='Email Recipient']]");
        public static final By REMINDER_SORT_BY_STATUS = By.xpath("//div/div/span[text()[.='Status']]");
        public static final By REMINDER_SORT_BY_DELETE = By.xpath("//div/div/span[text()[.='Delete']]");

    }

    public interface User {
        public static final By L_CLICK_ADD_NEW_USER = By.cssSelector("#measure_library_top_button_left");
        public static final By L_ADD_USER_NAME = By.cssSelector("#userName-inputEl");
        public static final By L_ADD_FIRST_NAME = By.cssSelector("#firstName-inputEl");
        public static final By L_ADD_LAST_NAME = By.cssSelector("#lastName-inputEl");
        public static final By L_PASSWORD = By.cssSelector("#encryptedPassword-inputEl");
        public static final By L_CONFIRM_PASSWORD = By.cssSelector("#password_confirm-inputEl");
        public static final By L_SECRET_QUESTION = By.cssSelector("#securityQuestion-inputEl");
        public static final By L_SECRET_QUESTION_VALUE = By.cssSelector(".x-boundlist-item:nth-child(2)");
        public static final By L_SECRET_ANSWER = By.cssSelector("#securityAnswer-inputEl");
        public static final By L_EMAIL = By.cssSelector("#emailAddress-inputEl");
        public static final By L_SAVE = By.cssSelector("#saveUser");

    }

    public interface DSM_CE {
        public static final By L_NAME = By.cssSelector("[name='loadShapeName']");
        public static final By L_IS_SYSTEM = By.xpath("//input[@role='checkbox']");
        public static final By L_DESCRIPTION = By.cssSelector("[name='description']");
        public static final By L_TYPE = By.cssSelector("[name='measureType']");
        public static final By L_SECTOR = By.cssSelector("[name='sector']");
        public static final By L_SUBTYPE = By.cssSelector("[name='measureSubType']");
        public static final By L_CLIMATE_ZONE = By.cssSelector("[name='climateZone']");
        public static final By L_ADD_SHAPE = By.cssSelector("[value='+ Add New Shape']");
        public static final By L_ADD_SHAPE_ID = By.cssSelector("#measure_library_top_button_right");
        public static final By LCE_SORT_BY_NAME = By.xpath("//div/a/span[text()[contains(., 'Name')]]");
        public static final By LCE_SORT_BY_LAST_UPDATED = By.xpath("//div/a/span[text()[contains(., 'Last Updated')]]");
        public static final By LCE_SORT_BY_START_YEAR = By.xpath("//div/a/span[text()[contains(., 'Start Year')]]");
        public static final By LCE_SHAPES = By.xpath("//div/ul/li/a[text()[contains(., 'Shapes')]]");
        public static final By LCE_COSTS = By.xpath("//div/ul/li/a[text()[contains(., 'Costs')]]");
        public static final By LCE_RATES = By.xpath("//div/ul/li/a[text()[contains(., 'Rates')]]");
        public static final By LCE_OTHER = By.xpath("//div/ul/li/a[text()[contains(., 'Other')]]");
        public static final By LCE_CUSTOMERS = By.xpath("//div/ul/li/a[text()[contains(., 'Customers')]]");
    }

    public interface Measure {

        public static final By L_CATEGORYCOUNTER = By.cssSelector("#categoryListComp_countLabel");
        public static final By L_TYPECOUNTER = By.cssSelector("#typeListComp_countLabel");
        public static final By L_SUBTYPECOUNTER = By.cssSelector("#subTypeListComp_countLabel");

        public static final By ADD_NEW_MESAURE_CATEGORY_POPUP_LOCATOR = Locator.divText("Add new Measure Category");
        public static final By ADD_NEW_MESAURE_TYPE_POPUP_LOCATOR = Locator.divText("Add new Measure Type");
        public static final By ADD_NEW_MESAURE_SUBTYPE_POPUP_LOCATOR = Locator.divText("Add new Measure SubType");
        public static final By ADD_NEW_TRACKING_FIELD = Locator.spanTextContains("+ Add");
        public static final By TRACKING_FIELD = Locator.spanTextContains("TRACKING FIELDS");



    }

    public interface Survey {
        public static final By L_ZIP_CODE = By.cssSelector("#saveSurveySector-zipCode-inputEl");
        public static final By L_SEARCH = By.cssSelector("#survey-search-button");
        public static final By L_SAVE = By.cssSelector("#survey-save-button");
        public static final By L_SAVE_AGAIN = By.cssSelector("#createSurveyPopup-save-btnEl");
        public static final By L_NAME = By.cssSelector("#createSurveyPopup-tokenName-inputEl");
        public static final By L_DESCRIPTION = By.cssSelector("#createSurveyPopup-description-inputEl");
        public static final By L_CUSTOMER_TYPE = By.cssSelector("#customer_type_filter-body");
        public static final By L_SURVEY_LIST = By.xpath("//div/ul/li/a[text()[contains(.,'Survey List')]]");
    }

    public interface Equipment {
		//main page
		public static final By L_ADD_NEW_EQUIPMENT = By.cssSelector("[value= '+ Add New Equipment']");
		//public static final By L_EQUIPMENT_TYPE = By.xpath("(//*[@id='eqtype_filter_container']//ul/li)[text()[contains(.,'Boiler')]]");
		public static final By L_SOURCE_COMBO = By.cssSelector("input[name='source']");
		public static final By L_CATEGORY_COMBO = By.cssSelector("input[name='type']");
		public static final By L_EQUIPMENT_NAME = By.cssSelector("input[name='equipmentName']");
		public static final By L_EQUIPMENT_DESCR = By.cssSelector("input[name='description']");


		//attributes popup
		public static final By L_SELECT_CATEGORY = By.cssSelector("input[name='categoryId']");
		public static final By L_SELECT_ATTRIBUTE = By.cssSelector("input[name='id']");
		public static final By L_ENTER_VALUE = By.cssSelector("input[name='value']");
		public static final By L_SAVE_ATTRIBUTE = By.cssSelector("a.save_attribute.actionButton");

		public static final By L_SAVE_EQUIPMENT = By.cssSelector("a.equipment_save.actionButton");




		public static final String L_CATEGORY_ITEM = "Size/Capacity";
        public static final By L_SELECT_ATTRIBUTE2 = By.xpath("//div[@id=library-equipment-gridContent]//*[text()[contains(.,'Select Attribute')]]"); // By.xpath("//div/table/tbody/tr[2]/td[3]/div");
        public static final By L_ATTRIBUTE_COMBO = By.cssSelector("#attrib_combo-inputEl");

        public static final By L_SELECT_ATTRIBUTE1 = By.xpath("//div/ul/li[1][text()[contains(.,'AHRI #')]]");
        public static final String L_ATTRIBUTE_ITEM = "GrossThermsPerUnit";
        public static final By L_VALUE = By.cssSelector("[name='value']");



    }

    public interface Partner {
        public static final By L_ADD_PARTNER_BTN = By.cssSelector("[value= '+ Add Partners']");
        public static final By L_MANAGE_PARTNER_ELEMENTS_BTN = By.cssSelector("[value='+ Manage Partner Elements']");

        /**
         * no great way to find these elements since there is no label and no field name (execpt auto generated ids that we cannot rely on)
         */
        public static final By L_PARTNER_ID = By.xpath("//div[@id='companyName_div']//input[@type='text']");
        public static final By L_ADDRESS_1 = By.xpath("//div[@id='addressLine1_div']//input[@type='text']");
        public static final By L_ADDRESS_2 = By.xpath("//div[@id='addressLine2_div']//input[@type='text']");
        public static final By L_WEBSITE = By.xpath("//div[@id='companyUrl_div']//input[@type='text']");
        public static final By L_CITY = By.xpath("//div[@id='city_div']//input[@type='text']");
        public static final By L_ZIP = By.xpath("//div[@id='postalCode_div']//input[@type='text']");
        public static final By L_STATE = By.xpath("//div[@id='state_div']//input[@type='text' and @role='combobox']");
        public static final By L_COUNTRY = By.xpath("//div[@id='country_div']//input[@type='text']");
        public static final By L_DESCRIPTION = By.xpath("//div[@id='description_div']//input[@type='text']");
        public static final By L_phoneCountryCode = By.xpath("//input[@type='text' and @name='phoneCountryCode']");
        public static final By L_Add_Phone_Area = By.xpath("//input[@type='text' and @name='phoneAreaCode']");
        public static final By L_Add_Phone_Prefix = By.xpath("//input[@type='text' and @name='phonePrefix']");
        public static final By L_Add_Phone_Suffix = By.xpath("//input[@type='text' and @name='phoneSuffix']");
        public static final By L_Add_Phone_Ext = By.xpath("//input[@type='text' and @name='phoneExtension']");
        public static final By L_faxCountryCode = By.xpath("//input[@type='text' and @name='faxCountryCode']");
        public static final By L_Add_fax_Area = By.xpath("//input[@type='text' and @name='faxAreaCode']");
        public static final By L_Add_fax_Prefix = By.xpath("//input[@type='text' and @name='faxPrefix']");
        public static final By L_Add_fax_Suffix = By.xpath("//input[@type='text' and @name='faxSuffix']");
        public static final By L_Add_fax_Ext = By.xpath("//input[@type='text' and @name='faxExtension']");
        public static final By remittanceNumber = By.xpath("//div[@id='remittanceNumber_div']//input[@type='text']");
        public static final By partnerNumber = By.xpath("//label/span[text()[contains(.,'Partner Number')]]/../..//input[@type='text']");
        public static final By STATUS = By.xpath("//div[@id='active_div']//input[@type='text' and @role='combobox']");
        public static final By savePartner = By.cssSelector("#savePartner");
        // Add new Company Offices
        public static final By CO_STATE = By.xpath("//input[@componentid='aostate']");
        // Add Note
        public static final By contactedBy = By.cssSelector("#contactedBy-inputEl");
        public static final By subject = By.cssSelector("#subject-inputEl");
        public static final By notes = By.cssSelector("#notes-inputEl");
        public static final By notetype = By.cssSelector("#noteTypeId-inputEl");
        public static final By saveNote = By.cssSelector("#file_upload_button");
        public static final By L_SAVE_NEW_PERSONNEL = By.xpath("//input[@type='button' and @value='Save']");
        public static final By contractName = By.xpath("//input[@name='contractName']");
        public static final By activeindicator = By.xpath("//input[@name='activeFlag' and @role='combobox']");
        public static final By saveContractButton = By.xpath("//input[@value='Save']");
        public static final By saveLicenseButton = By.cssSelector("#file_upload_button");
        public static final By licenseName = By.cssSelector("#licenseName-inputEl");
        public static final By followUpRequired = By.cssSelector("#followUpRequired-inputEl");
        public static final By followUpEmailAddress = By.cssSelector("#followUpEmailAddress-inputEl");
        public static final By followUpEmailText = By.cssSelector("#followUpEmailText-inputEl");
        public static final By serviceTypes = By.xpath("//input[@name='serviceTypes']");
        public static final By serviceTypeSpecialties = By.xpath("//input[@name='serviceTypeSpecialties']");
        public static final By sector = By.xpath("//input[@id='sector-inputEl']");
        public static final By state_comb = By.xpath("//input[@id='state-inputEl']");
        public static final By followUpDateString = By.cssSelector("#followUpDateString-inputEl");
        public static final By officeName = By.cssSelector("[name='officeName']");
        public static final By addressLine1 = By.cssSelector("[name='addressLine1']");
        public static final By addressLine2 = By.cssSelector("[name='addressLine2']");
        public static final By city = By.cssSelector("[name='city']");
        public static final By postalCode = By.cssSelector("[name='postalCode']");
        public static final By country = By.cssSelector("[name='country']");
        public static final By description = By.cssSelector("[name='description']");
        public static final By phoneCountryCode = By.cssSelector("[name='phoneCountryCode']");
        public static final By phoneAreaCode = By.cssSelector("[name='phoneAreaCode']");
        public static final By phonePrefix = By.cssSelector("[name='phonePrefix']");
        public static final By phoneSuffix = By.cssSelector("[name='phoneSuffix']");
        public static final By phoneExtension = By.cssSelector("[name='phoneExtension']");
        public static final By faxCountryCode = By.cssSelector("[name='faxCountryCode']");
        public static final By faxAreaCode = By.cssSelector("[name='faxAreaCode']");
        public static final By faxPrefix = By.cssSelector("[name='faxPrefix']");
        public static final By faxSuffix = By.cssSelector("[name='faxSuffix']");
        public static final By faxExtension = By.cssSelector("[name='faxExtension']");
        public static final By firstName = By.cssSelector("[name='firstName']");
        public static final By lastName = By.cssSelector("[name='lastName']");
        public static final By title1 = By.cssSelector("[name='title']");
        public static final By emailAddress = By.cssSelector("[name='emailAddress']");
        public static final By phCntryCode = By.cssSelector("[name='phCntryCode']");
        public static final By phAreaCode = By.cssSelector("[name='phAreaCode']");
        public static final By phPrefix = By.cssSelector("[name='phPrefix']");
        public static final By phSuffix = By.cssSelector("[name='phSuffix']");
        public static final By phExt = By.cssSelector("[name='phExt']");
        public static final By primaryContact = By.id("apprimaryContact-inputEl");
        public static final By comments = By.cssSelector("[name='comments']");
        public static final By partnerOfficeId = By.cssSelector("[name='partnerOfficeId']");

        // the add new contract popup box
        public static final By L_ADD_CONTRACT_POPUP = Locator.divText("Add New Contract");

        // the add new contract popup box title span
        public static final By L_ADD_CONTRACT_POPUP_TITLE_SPAN = By.id("addContractPopup_header_hd-textEl");

        public static final By L_SAVE_COMPANY_OFFICE = By.xpath("//input[@type='button' and @value='Save']");

    }

    public interface Measure_PSEG {
        public static final By L_MANAGE_TAXONOMY = Locator.traksmartButtonAnchorStyle("+ Manage Taxonomy");
        // add category test
       // public static final By L_MEASURE_CATEGORY_NAME = By.xpath("//label/span[text()[contains(.,'Name:')]]//../..//input[@name='name' and @type='text']");
        public static final By L_MEASURE_CATEGORY_NAME = By.xpath("//*[contains(@class, 'manageTaxonomy')]//input[@name='name' and @type='text']");
        public static final By L_STATUS = By.xpath("//*[contains(@class, 'manageTaxonomy')]//input[@name='statusId']");
        public static final By L_DESCRIPTION = By.xpath("//*[contains(@class, 'manageTaxonomy')]//textarea[@name='description']");
        public static final By L_CATEGORIES = By.cssSelector("#categoryListComp_listcontainer");
        public static final By L_SAVE_CATEGORY = By.xpath("//*[contains(@class, 'manageTaxonomy')]//span[text()[.='Save']]");
        // add type test // addTaxonomyElement-1122
        public static final By L_ADD_TYPE_NAME = By.xpath("//*[contains(@class, 'manageTaxonomy')]//input[@name='name' and @type='text']");
        public static final By L_ADD_TYPE_DESCRIPTION = By.xpath("//*[contains(@class, 'manageTaxonomy')]//textarea[@name='description']");
        public static final By L_ADD_TYPE_STATUS = By.xpath("//*[contains(@class, 'manageTaxonomy')]//input[@name='statusId']");
        public static final By L_SAVE_TYPE = By.xpath("//*[contains(@class, 'manageTaxonomy')]//span[text()[.='Save']]");
        // add  // addTaxonomyElement-1135 // x-form-checkbox-default
        public static final By L_ADD_SUB_TYPE_NAME = By.xpath("//*[contains(@class, 'manageTaxonomy')]//input[@name='name' and @type='text']");
        public static final By L_ADD_SUB_TYPE_DESCRIPTION = By.xpath("//*[contains(@class, 'manageTaxonomy')]//textarea[@name='description']");
        public static final By L_ADD_SUB_TYPE_CHECK = By.xpath("//*[contains(@class, 'manageTaxonomy')]//span[text()[.='Track Renewable Generation:']]");
        public static final By L_ADD_SUB_TYPE_STATUS = By.xpath("//*[contains(@class, 'manageTaxonomy')]//input[@name='statusId']");
        public static final By L_SAVE_SUB_TYPE = By.xpath("//*[contains(@class, 'manageTaxonomy')]//span[text()[.='Save']]");
        //
        public static final By L_ADD_TRACKING = By.cssSelector("#trackFieldButton-btnEl");
        public static final By L_CHECK_BOX = By.xpath("//div/table/tbody/tr/td/div/div[@class='x-grid-row-checker']");
        public static final By L_ATTRIBUTES = By.cssSelector("#tab-1095-btnEl");
        public static final By L_ADD_ATTRIBUTES = By.xpath("//div/em/button/span[text()[contains(.,'ATTRIBUTES')]]");
        public static final By L_ADD = By.xpath("(//div/em/button/span[text()[contains(.,'+ Add')]])[last()]");
        public static final By L_TRACK = By.xpath("(//div/table/tbody/tr/td/input)[Last()]");
        public static final By L_TYPE = By.cssSelector("#typeListComp_listcontainer");
        public static final By L_SUB_TYPE = By.cssSelector("#subTypeListComp_listcontainer");

    }


    public interface Programs {
        public static final By L_PROGRAMS = By.xpath("//div/ul/li/a[text()[contains(.,'PROGRAMS')]]");
        public static final By L_Add_New_Program = By.xpath("//div/input[@value='+ Add New Program']");
        public static final By L_SEARCH = By.cssSelector("[name='keyword_search']");
        public static final By L_Enroll = By.linkText("Program Enroll Setting"); // By.xpath("//div/ul/li/a[text()[contains(.,'Program Enroll Setting')]]");
        public static final By L_NAME = By.cssSelector("#publicName-inputEl");
        public static final By L_DESCRIPTION = By.cssSelector("#publicDescription-inputEl");
        public static final By L_publicSummary = By.cssSelector("#publicSummary-inputEl");
        // Add program
        public static final By L_ADD_PROGRAM_NAME = By.xpath("//input[@componentid='program_name_field']");
        public static final By L_ADD_PROGRAM_DESCRIPTION = By.xpath("//textarea[@componentid='program_description_field']");
        public static final By SERVICE_TYPE = By.xpath("(//input[@componentid='program_serviceType_field_combobox']");
        public static final By TYPE = By.xpath("//input[@componentid='program_type_field']");
        public static final By STATUS = By.xpath("//input[@componentid='program_status_field']");
        public static final By SECTORS = By.xpath("//input[@componentid='program_sectors_field_combobox']");
        public static final By DEFAULT_SECTOR = By.xpath("//input[@componentid='program_defaultsector_field']");
        public static final By DATE_FROM = By.xpath("//input[@componentid='program_fromdate_field']");
        public static final By DATE_TO = By.xpath("//input[@componentid='program_todate_field']");
        public static final By TRACK_RENEWABLE_GENERATION = By.xpath("//input[@componentid='program_renewable_field']");
        public static final By ADD_NEW_SERVICE_POINT = By.partialLinkText("+ Add New Service Point");
        public static final By SERVICE_TERRITORY = By.xpath("//input[@componentid='program_service_territory_field_combobox']");
        public static final By Metric1 = By.xpath("//input[@componentid='program_metric_0_field']");
        public static final String Metric1_txt = "Coincidence Demand Reduction kW Per Unit";
        public static final By ADD_NEW_GOAL = By.linkText("+ Add New Goal");
        public static final By goal = By.xpath("//input[@componentid='program_goal_0_field']");
        public static final String goal_txt = "20";
        public static final By terr = By.xpath("#program_svc_terr_0_input");
        public static final String terr_txt = "CA";
        public static final By sector = By.xpath("#program_sector_0_input");
        public static final String sector_txt = "Mining";
        public static final By startdate = By.xpath("//input[@componentid='program_goal_startdate_0_field']");
        public static final By enddate = By.xpath("//input[@componentid='program_goal_enddate_0_field']");
        public static final By Program_Manager = By.xpath("(//input[@componentid='program_programmgrs_0_field_combobox']");
        public static final String Program_Manager_Name = "Tester Automation";
        public static final By Support_Staff = By.xpath("(//input[@componentid='program_supportstaff_0_field_combobox']");
        public static final String Support_Staff_Name = "Tester Automation";
        public static final By Project_Managers = By.xpath("(//input[@componentid='program_projectmgrs_0_field_combobox']");
        public static final String Project_Managers_Name = "Tester Automation";
        public static final By Project_Number_Prefix = By.xpath("//input[@componentid='program_projectnumprefix_field']");
        public static final String Project_Number_Prefix_Name = "PNP";
        public static final By Allow_Project_Copy = By.xpath("//input[@componentid='program_projectCopy_field']");
        public static final By Comments_program = By.xpath("//textarea[@componentid='program_comments_field']");
        public static final By Save_btn = By.xpath("//input[@type='button' and @value='Save']");
        public static final By program_category = By.cssSelector("#program_category_field_combobox-inputEl");
        public static final String program_category_text = "Air Conditioners";
        public static final By category = By.cssSelector("#newCategory-inputEl");
        public static final By Add_Category = By.xpath("//div/em/button/span[text()[contains(.,'Add Category')]]");
        public static final By create_accounting_code_popup_form_save = By.cssSelector("#create_accounting_code_popup_form_save-btnEl");
        public static final By Select_State = By.cssSelector("[name='stateId']");
        public static final By Add_Eligibility_Setting = By.linkText("+Add");
        public static final String Select_State_text = "CA";
        public static final By Select_Sector = By.xpath("//div/input[@name='sectorId']");
        public static final String Select_Sector_text = "Mining";
        public static final By Select_Indicator = By.cssSelector("[name='activeFlag']");
        public static final String Select_Indicator_text = "Active";
        public static final By Select_Category = By.cssSelector("[name='accountCategoryId']");
        public static final String Select_Category_text = "EABVMRK";
        public static final By Description_Budget = By.cssSelector("[name='description']");
        public static final By Add_Account_Code = By.cssSelector("[name='accountCode']");
        public static final By Add_code = By.cssSelector("#btnAddNewAccountingItem");
        public static final By Open_Budget_And_Accounting = By.linkText("Budget & Accounting");
        //public static final By Save_Cost = By.xpath("(//*[@id='variable-program-cost-window'])//input[@type='button' and @value='Save']"); // might be other save buttons on the screen

        public static final By Save_Cost = By.xpath("(//div[@id='variable-program-cost-window'])//span[text()[.='Save']]"); // might be other save buttons on the screen

        public static final By Select_Accounting_Code = By.xpath("(//div/table/tbody/tr/td/table/tbody/tr/td/div)[21]");
        public static final By Add_External_Reference_Id_VC = By.cssSelector("[name='externalRef']");
        public static final By Payee_Last_Name = By.cssSelector("[name='payeeLastName']");
        public static final By Payee_First_Name = By.cssSelector("[name='payeeFirstName']");
        public static final By Select_Interval = By.xpath("(//div/table/tbody/tr/td/table/tbody/tr/td/div)[19]");
        public static final String Select_Interval_Text = "MONTHLY";
        public static final By Select_Amount = By.xpath("(//div/table/tbody/tr/td/table/tbody/tr/td/div)[16]");
        public static final By End_Date = By.cssSelector("[name='endDate']");
        public static final By Start_Date = By.cssSelector("[name='startDate']");
        public static final By Description_FC = By.cssSelector("[name='description']");
        // Add variable cost
        public static final By ADD_COST_NAME = By.cssSelector("[name='costName']");
        public static final By Remit_Country = By.cssSelector("[name='remitToCountry']");
        public static final By Remit_County = By.cssSelector("[name='remitToCounty']");
        public static final By Remit_ZIP = By.cssSelector("[name='remitToPostalCode']");
        public static final By Remit_State = By.cssSelector("[name='remitToState']");
        public static final By Remit_City = By.cssSelector("[name='remitToCity']");
        public static final By Remit_Street_address_2 = By.cssSelector("[name='remitToAddressLine2']");
        public static final By Remit_Street_address_1 = By.cssSelector("[name='remitToAddressLine1']");
        public static final By Remit_Partner_Number = By.cssSelector("[name='remitToPartnerNumber']");
        public static final By Remit_Name = By.cssSelector("[name='remitToName']");
        public static final By Remit_Last_Name = By.cssSelector("[name='remitToLastName']");
        public static final By Remit_First_Name = By.cssSelector("[name='remitToFirstName']");
        public static final By Press_Remit_Information = By.xpath("//div/fieldset/legend/div[text()[contains(.,\"Remit Information\")]]");
        public static final By Payee_Country = By.cssSelector("[name='payeeCountry']");
        public static final By Payee_County = By.cssSelector("[name='payeeCounty']");
        public static final By Payee_ZIP = By.cssSelector("[name='payeePostalCode']");
        public static final By Payee_State = By.cssSelector("[name='payeeState']");
        public static final By Payee_City = By.cssSelector("[name='payeeCity']");
        public static final By Payee_Street_address_2 = By.cssSelector("[name='payeeAddressLine2']");
        public static final By Payee_Street_address_1 = By.cssSelector("[name='payeeAddressLine1']");
        public static final By Payee_Partner_Number = By.cssSelector("[name='payeePartnerNumber']");
        public static final By Payee_Name = By.cssSelector("[name='payeeName']");
        public static final By Select_Accounting_Code_VC = By.cssSelector("[name='accountCodeId']");
        ////div/table/tbody/tr/td/table/tbody/tr/td/div[@class='x-trigger-index-0 x-form-trigger x-form-arrow-trigger x-form-trigger-last x-unselectable']
        public static final By Add_External_Reference_Id = By.cssSelector("[name='externalRef']");
        public static final By Select_Amount_VC = By.cssSelector("[name='amount']");
        public static final By Transaction_Date = By.cssSelector("[name='transactionDate']");
        public static final By Description_VC = By.xpath("//textarea[@name='description']");
        public static final By Add_Cost_Name = By.cssSelector("[name='costName']");
        public static final By Add_Variable_Cost = By.cssSelector("[value='+ Add Variable Cost']");
        public static final By Add_Partner_Eligibility_Setting = By.cssSelector("[id='varDecAddRowLink_Partner']");
        public static final By Add_Fixed_Cost = By.cssSelector("[value='+ Add Fixed Cost']");

    }

    public interface Formulas {
        public static final String Program_name_1 = "Standard Business Solutions";
        public static final String Formula_name_1 = "CO Sensor Inc Cost";
        public static final String Formula_name_2 = "CO Sensor kWh";
        public static final String Formula_name_3 = "CO Sensor Rebate";
        public static final String Formula_name_4 = "CO2 Sensor kW";
        public static final String Formula_name_5 = "AC Constant";
        public static final String Formula_name_6 = "Air Conditioner Measure Name";
        public static final String Formula_name_7 = "Chiller kW";
        public static final String Formula_name_8 = "Chiller kWh";
        public static final String Formula_name_9 = "Chiller Minimum IPLV";
        public static final String Formula_name_10 = "Chiller Rebate";
        public static final String Formula_name_11 = "Chillers - Proposed Chiller IPLV (kW/ton)";
        public static final String Formula_name_12 = "Chillers Measure Name";
        public static final String Formula_name_13 = "CO2 Sensor kW";
        public static final String Formula_name_14 = "CO2 Sensor Measure Name";
        public static final String Formula_name_15 = "CO2 Sensor Rebate";
        public static final String Formula_name_16 = "CO2 Sensors Base HSPF or COP";
        public static final String Formula_name_17 = "CO2 Sensors SEERbase";
        public static final String Formula_name_18 = "Compressed Air Inc Cost";
        public static final String Formula_name_19 = "Compressed Air kW";
        public static final String Formula_name_20 = "Compressed Air kWh";
        public static final String Formula_name_21 = "Compressed Air Measure Name";
        public static final String Formula_name_22 = "Compressed Air Qualifies";
        public static final String Formula_name_23 = "Compressed Air Rebate";
        public static final String Formula_name_24 = "Computer and Data Center Inc Cost";
        public static final String Formula_name_25 = "Computer and Data Center kW";
        public static final String Formula_name_26 = "Computer and data Center Measure Name";
        public static final String Formula_name_27 = "Computer and Data Center Rebate";
        public static final String Formula_name_28 = "Cust Info Customer Company Name";
        public static final String Formula_name_29 = "RA Customer Company Name";
        public static final String Formula_name_30 = "RA Lighting Controls Incremental Cost";
        public static final String Formula_name_31 = "RA Lighting Fixture Incremental Cost";
        public static final String Formula_name_32 = "RA Total Customer Incentive";
        public static final String Formula_name_33 = "RA Total Project Incremental Cost";
        public static final String Formula_name_34 = "RA Total Project kW Reduction";
        public static final String Formula_name_35 = "RA Total Project kWh Savings";
        public static final String Formula_name_36 = "RA Total Project Measure Cost";
        public static final String Formula_name_37 = "RA Total Project Rebate";
        public static final String Formula_name_38 = "Retro Lighting Total Project Incremental Cost";
        public static final String Formula_name_39 = "Retro Lighting Total Project kW Reduction";
        public static final String Formula_name_40 = "Retro Lighting Total Project Measure Cost";
        public static final String Formula_name_41 = "Retro Lighting Total Project Rebate";
        public static final String Formula_name_42 = "Retrofit Lighting Total Project kWh Savings";
        public static final String Formula_name_43 = "RRR Incentive Total";
        public static final String Formula_name_44 = "RRR Total Project Incremental Cost";
        public static final String Formula_name_45 = "RRR Total Project kW Reduction";
        public static final String Formula_name_46 = "RRR Total Project kWh Savings";
        public static final String Formula_name_47 = "RRR Total Project Measure Cost";
        public static final String Formula_name_48 = "RRR Total Project Rebate";
        public static final String Formula_name_49 = "RRR Total Quantity";
        public static final String Formula_name_50 = "Self Direct?";
        public static final String Formula_name_51 = "Chiller Measure Life";
        public static final String Formula_name_52 = "CO2 Inc Cost";
        public static final String Formula_name_53 = "Computer and Data Center kW";
        public static final String Formula_name_54 = "Computer and Data Center kWh";
        public static final String Formula_name_55 = "Hotel Occ Controls Inc Cost";
        public static final String Formula_name_56 = "ECM Inc Rebate";
        public static final String Formula_name_57 = "ECM kW";
        public static final String Formula_name_58 = "ECM kWh";
        public static final String Formula_name_59 = "ECM Inc Cost";
        public static final String Formula_name_60 = "Envelope Inc Cost";
        public static final String Formula_name_61 = "Envelope kW";
        public static final String Formula_name_62 = "Envelope kWh";
        public static final String Formula_name_63 = "Refrigeration Inc Cost";
        public static final String Formula_name_64 = "Refrigeration kW";
        public static final String Formula_name_65 = "Refrigeration kWh";
        public static final String Formula_name_66 = "Refrigeration kW_copy_02-28-2014T08:39:26.218";
        public static final String Formula_name_67 = "VFD Inc Cost";
        public static final String Formula_name_68 = "VFD kW";
        public static final String Formula_name_69 = "VFD kWh";
        public static final String Formula_name_70 = "Year 1 Inc Cost";
        public static final String Formula_name_71 = "Year 1 kW Reduction";
        public static final String Formula_name_72 = "Year 1 kWh";
        public static final String Formula_name_73 = "Year 1 Measure Cost";
        public static final String Formula_name_74 = "Year 1 Rebate";
        public static final String Formula_name_75 = "Year 2 Expiration Date";
        public static final String Formula_name_76 = "Year 2 Inc Cost";
        public static final String Formula_name_77 = "Year 2 kW Reduction";
        public static final String Formula_name_78 = "Year 2 kWh";
        public static final String Formula_name_79 = "Year 2 Measure Cost";
        public static final String Formula_name_80 = "Year 3 Expiration Date";
        public static final String Formula_name_81 = "Year 3 Inc Cost";
        public static final String Formula_name_82 = "Year 3 kW Reduction";
        public static final String Formula_name_83 = "Year 3 kWh";
        public static final String Formula_name_84 = "Year 3 Measure Cost";
        public static final String Formula_name_85 = "Year 3 Rebate";
        public static final String Formula_name_86 = "Heat Pump Measure Name";
        public static final String Formula_name_87 = "Hotel Occ Control Base HSPF or COP";
        public static final String Formula_name_88 = "Hotel Occ Controls Inc Cost";
        public static final String Formula_name_89 = "Hotel Occ Controls kW";
        public static final String Formula_name_90 = "Hotel Occ Controls kWh";
        public static final String Formula_name_91 = "Hotel Occ Controls Measure Name";
        public static final String Formula_name_92 = "Hotel Occ Controls Rebate";
        public static final String Formula_name_93 = "Hotel Occupancy Sensors Qualifies";
        public static final String Formula_name_94 = "HP Constant";
        public static final String Formula_name_95 = "HVAC AC Tier";
        public static final String Formula_name_96 = "HVAC HP Tier";
        public static final String Formula_name_97 = "HVAC-AC Inc Cost";
        public static final String Formula_name_98 = "HVAC-AC kW";
        public static final String Formula_name_99 = "HVAC-AC kWh/yr";
        public static final String Formula_name_100 = "HVAC-AC Rebate";
        public static final String Formula_name_101 = "HVAC-HP Inc Cost";
        public static final String Formula_name_102 = "HVAC-HP kW";
        public static final String Formula_name_103 = "HVAC-HP kWh/yr";
        public static final String Formula_name_104 = "HVAC-HP Rebate";
        public static final String Formula_name_105 = "Installed Expired";
        public static final String Formula_name_106 = "Installed PY";
        public static final String Formula_name_107 = "Lighting Controls Measure Name";
        public static final String Formula_name_108 = "LR Lighting Fixture kW Reduction";
        public static final String Formula_name_109 = "Lighting Fixtures Measure Name";
        public static final String Formula_name_110 = "LR Lighting Controls kW Reduction";
        public static final String Formula_name_111 = "";
        public static final String Formula_name_112 = "";
        public static final String Formula_name_113 = "";
        public static final String Formula_name_114 = "";
        public static final String Formula_name_115 = "";
        public static final String Formula_name_116 = "";
        public static final String Formula_name_117 = "";
        public static final String Formula_name_118 = "";
        public static final String Formula_name_119 = "";
        public static final String Formula_name_120 = "";
        public static final String Formula_name_121 = "";
        public static final String Formula_name_122 = "";
        public static final String Formula_name_123 = "";
        public static final String Formula_name_124 = "";
        public static final String Formula_name_125 = "";
        public static final String Formula_name_126 = "";
        public static final String Formula_name_127 = "";
        public static final String Formula_name_128 = "";
        public static final String Formula_name_129 = "";


        public static final By L_FORMULA_NAME = By.cssSelector("[name='formulaName']");
        public static final By L_FORMULA = By.cssSelector("[name='formulaField-inputEl']");
        public static final String FORMULA_1 = "Q*C";
        public static final String FORMULA_2 = "Q*S*K";
        public static final String FORMULA_3 = "Q*C";
        public static final String FORMULA_4 = "Q*(S*400*.15)* (PEAKred/1000/SEERbase)";
        public static final String FORMULA_5 = "if(right(val,7)==\"Unitary\", \"Unitary AC\", \"PTAC\")";
        public static final String FORMULA_6 = "\"HVAC - \"+  if(right(type,3)==\"ary\", \"Air Conditioner\", if(left(type,3)==\"Pac\",\"PTAC\",\" \")) + \n" +
                "\" - \"+if(right(type,3)==\"ary\", if(right(str(round(btuh/6000,0)/2),2)==\".5\", \n" +
                "str(round(btuh/6000,0)/2),\n" +
                "left(str(round(btuh/6000,0)/2),\n" +
                "len(str(round(btuh/6000,0)/2))-2)) \n" +
                "+ \" ton\" ,str(btuh)+\" Btu/hr\")";
        public static final String FORMULA_7 = "max(0,(3.517 * (1 / baseCOP - 1 / COP) - if(left(Type,3)==\"Wat\"&&left(Typebase,3)==\"Air\",0.109,0) )* Tons * Quantity)";
        public static final String FORMULA_8 = "(3.517*(1/baseIPLV - 1/IPLV) - if(left(Type,3)==\"Wat\"&&left(Typebase,3)==\"Air\",0.109,0))\n" +
                "*Tons*EFLH*Quantity";
        public static final String FORMULA_9 = "floor( (12 / (N * 3.412)) * 100 + .5) / 100";
        public static final String FORMULA_10 = "if(IPLV <= M,  N * Tons * ( EqR+ (M - IPLV) * EfR), 0)";
        public static final String FORMULA_11 = "floor ( (12 / (J * 3.412)) * 100 + .5) / 100";
        public static final String FORMULA_12 = "\"Chiller - \" + val + \" ton\"";
        public static final String FORMULA_13 = "Q*(S*400*.15)* (PEAKred/1000/SEERbase)";
        public static final String FORMULA_14 = "\"CO2 Sensor - \" + val + \" ton\"";
        public static final String FORMULA_15 = "if(S>=10,Q*C,0)";
        public static final String FORMULA_16 = "if(Heat==\"Electric Resistance\", 3.412,\n" +
                "if(Type==\"Unitary Heat Pump\", \n" +
                "if(ID==1,COP,COP*3.413/1.718),COP*3.413/1.718))";
        public static final String FORMULA_17 = "val";
        public static final String FORMULA_18 = "if(Qual==1,if(NC==\"Yes\", CUN, CUR) * if (U==\"None (Equip)\", Q, S*Q),0)";
        public static final String FORMULA_19 = "if(Qual==1,K * ( if(U==\"None (Equip)\", Q, S*Q) ),0)";
        public static final String FORMULA_20 = "if(Qual==1,K * (if(U==\"None (Equip)\", Q*min(A,8760), S*Q*min(A,8760))),0)";
        public static final String FORMULA_21 = "\"Compressed Air - \" + val";
        public static final String FORMULA_22 = "if(type==\"VFD Compressors\", if(val>0 && val<=75,1,0), if(type==\"Refrigerated Cycling Dryers\"||type==\"Low Pressure-drop Filters\",\n" +
                "if(val<=1000 ,1,0),1))";
        public static final String FORMULA_23 = "if(Qual==1,R * ( if(U==\"None (Equip)\", Q, S*Q) ),0)";
        public static final String FORMULA_24 = "Q*K";
        public static final String FORMULA_25 = "Q*K";
        public static final String FORMULA_26 = "val";
        public static final String FORMULA_27 = "if(NC==\"Yes\"&&val!=\"Network Desktop Computer Power Mgt Software\",0,Q*K)";
        public static final String FORMULA_28 = "val";
        public static final String FORMULA_29 = "val";
        public static final String FORMULA_30 = "val";
        public static final String FORMULA_31 = "val";
        public static final String FORMULA_32 = "val";
        public static final String FORMULA_33 = "val";
        public static final String FORMULA_34 = "val";
        public static final String FORMULA_35 = "val";
        public static final String FORMULA_36 = "val";
        public static final String FORMULA_37 = "val";
        public static final String FORMULA_38 = "val";
        public static final String FORMULA_39 = "val";
        public static final String FORMULA_40 = "val";
        public static final String FORMULA_41 = "val";
        public static final String FORMULA_42 = "val";
        public static final String FORMULA_43 = "val";
        public static final String FORMULA_44 = "val";
        public static final String FORMULA_45 = "val";
        public static final String FORMULA_46 = "val";
        public static final String FORMULA_47 = "val";
        public static final String FORMULA_48 = "val";
        public static final String FORMULA_49 = "val";
        public static final String FORMULA_50 = "val";
        public static final String FORMULA_51 = "val";
        public static final String FORMULA_52 = "Q*C";
        public static final String FORMULA_53 = "Q*K";
        public static final String FORMULA_54 = "Q*K";
        public static final String FORMULA_55 = "Q*C";
        public static final String FORMULA_56 = "val*K * S*Q";
        public static final String FORMULA_57 = "val*K * S*Q";
        public static final String FORMULA_58 = "val*K*S*Q";
        public static final String FORMULA_59 = "val*K * S*Q";
        public static final String FORMULA_60 = "if(QUAL==1,Q*K,0)";
        public static final String FORMULA_61 = "if(QUAL==1,Q*K,0)";
        public static final String FORMULA_62 = "if(QUAL==1,Q*K,0)";
        public static final String FORMULA_63 = "K * (if(U==1, Q, S*Q))";
        public static final String FORMULA_64 = "K * (if(U==1, Q, S*Q))";
        public static final String FORMULA_65 = "K * (if(U==1, Q, S*Q))";
        public static final String FORMULA_66 = "K * (if(U==1, Q, S*Q))";
        public static final String FORMULA_67 = "K * (if(U==1, Q, S*Q))";
        public static final String FORMULA_68 = "K * (if(U==1, Q, S*Q))";
        public static final String FORMULA_69 = "K * (if(U==1, Q, S*Q))";
        public static final String FORMULA_70 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_71 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_72 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_73 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_74 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_75 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_76 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_77 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_78 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_79 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_80 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_81 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_82 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_83 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_84 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_85 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_86 = "\"HVAC - \"+  if(right(type,3)==\"ary\", \"Heat Pump\", if(left(type,3)==\"Pac\",\"PTHP\",\" \")) + \n" +
                "\" - \"+if(right(type,3)==\"ary\", if(right(str(round(btuh/6000,0)/2),2)==\".5\", \n" +
                "str(round(btuh/6000,0)/2),\n" +
                "left(str(round(btuh/6000,0)/2),\n" +
                "len(str(round(btuh/6000,0)/2))-2)) \n" +
                "+ \" ton\" ,str(btuh)+\" Btu/hr\")";
        public static final String FORMULA_87 = "if((Type==\"PTAC\"||Type==\"Fan Coil Unit\") &&Heat==\"Electric Resistance\", 3.412,\n" +
                "COP)";
        public static final String FORMULA_88 = "Q*C";
        public static final String FORMULA_89 = "0";
        public static final String FORMULA_90 = "0.624*Q*(S*((12/SEERbase)*(EFLHcBase-EFLHc) +\n" +
                "if(HSPFbase>0,(12/HSPFbase)*(EFLHhBase-EFLHh),0)))";
        public static final String FORMULA_91 = "\"Hotel Occ Controls - \" + val";
        public static final String FORMULA_92 = "qual*Q*C";
        public static final String FORMULA_93 = "if(OCCHEAT>=UNOCCHEAT+6&&UNOCCCOOL>=OCCCOOL+6,1,0)";
        public static final String FORMULA_94 = "if(right(val,7)==\"Unitary\", \"Unitary HP\", \"PTHP\")";
        public static final String FORMULA_95 = "if(Type!=\"Water-Cooled Unitary\" && Type!=\"Evap-Cooled Unitary\"&& Unit!=\"PTAC\", if(SEER >= T2SEER &&\n" +
                "EER >= T2EER, 2, \n" +
                "if(SEER >= T1SEER && \n" +
                "EER >= T1EER, 1, 0)),\n" +
                "if(EER >= T2EER,2,if(EER >= T1EER,1,0)))";
        public static final String FORMULA_96 = "if(Type!=\"Water-Cooled Unitary\" && Type!=\"Evap-Cooled Unitary\"&& Unit!=\"PTHP\", if(SEER >= T2SEER &&\n" +
                "EER >= T2EER && HSPF >= T2HSPF &&\n" +
                "COP2>=T2COP2, 2, \n" +
                "if(SEER >= T1SEER && \n" +
                "EER >= T1EER && HSPF >= T1HSPF && COP2>=T1COP2, 1, 0)),\n" +
                "if(EER >= T2EER && HSPF >= T2HSPF &&\n" +
                "COP2>=T2COP2,2,if(EER >= T1EER && HSPF >= T1HSPF && COP2>=T1COP2,1,0)))";
        public static final String FORMULA_97 = "S /12000 * Q * if(Tier==1, T1, if(Tier==2, T2, 0))";
        public static final String FORMULA_98 = "if(Tier==0,0,Q * (S/1000 * (1/baseEER - 1/EER)))";
        public static final String FORMULA_99 = "if(Tier==0,0,Q * (S/1000 * (if(SEER==0,(1/baseEER - 1/EER),(1/baseSEER - 1/SEER)) * EC)))";
        public static final String FORMULA_100 = "S /12000 * Q * if(Tier==1, T1, if(Tier==2, T2, 0))";
        public static final String FORMULA_101 = "S /12000 * Q * if(Tier==1, T1, if(Tier==2, T2, 0))";
        public static final String FORMULA_102 = "if(Tier==0,0,Q * (S/1000 * (1/baseEER - 1/EER)))";
        public static final String FORMULA_103 = "if(Tier==0,0,Q * (S/1000 *(if(SEER==0,(1/baseEER - 1/EER),(1/baseSEER - 1/SEER)) * EC + \n" +
                "if(baseHSPF>0&&HSPF>0,(1/baseHSPF - 1/HSPF)*EH* \n" +
                "if(ID==1,1,1.718/3.413),0))))";
        public static final String FORMULA_104 = "S /12000 * Q * if(Tier==1, T1, if(Tier==2, T2, 0))";
        public static final String FORMULA_105 = "if(isNull(EXP)||EXP==\"\",\"\",\n" +
                "(isBeforeDate(EXP, Now(\"MM/dd/yyyy\")))&&(\n" +
                "(isNull(Review)||Review==\"\")),\"\")";
        public static final String FORMULA_106 = "if((isNull(Review)||Review==\"\"),\"\",\n" +
                "if(month(Review)>=5,mid(str(year(Review)+1),2,2), right(Review,2)))";
        public static final String FORMULA_107 = "\"Lighting Controls\" + if(val==\"Yes\", \" New Construction\", \" Retrofit\")";
        public static final String FORMULA_108 = "CF*IF*revkW";
        public static final String FORMULA_109 = "\"Lighting Fixtures\" + if(val==\"Yes\", \" New Construction\", \" Retrofit\")";
        public static final String FORMULA_110 = "CF*IF*revkW";
        public static final String FORMULA_111 = "";
        public static final String FORMULA_112 = "";
        public static final String FORMULA_113 = "";
        public static final String FORMULA_114 = "";
        public static final String FORMULA_115 = "";
        public static final String FORMULA_116 = "";
        public static final String FORMULA_117 = "";
        public static final String FORMULA_118 = "";
        public static final String FORMULA_119 = "";
        public static final String FORMULA_120 = "";


        public static final String FORMULA_1_VALUE_1 = "5";
        public static final String FORMULA_1_VALUE_2 = "6";
        public static final String FORMULA_2_VALUE_1 = "3341.74";
        public static final String FORMULA_2_VALUE_2 = "1";
        public static final String FORMULA_2_VALUE_3 = "3";
        public static final String FORMULA_3_VALUE_1 = "250";
        public static final String FORMULA_3_VALUE_2 = "1";
        public static final String FORMULA_4_VALUE_1 = "10";
        public static final String FORMULA_4_VALUE_2 = "20";
        public static final String FORMULA_4_VALUE_3 = "30";
        public static final String FORMULA_4_VALUE_4 = "40";
        public static final String FORMULA_4_VALUE_5 = "50";
        public static final String FORMULA_5_VALUE_1 = "PTAC";
        public static final String FORMULA_6_VALUE_1 = "120000";
        public static final String FORMULA_6_VALUE_2 = "ary";
        public static final String FORMULA_7_VALUE_1 = "3.9";
        public static final String FORMULA_7_VALUE_2 = "Air";
        public static final String FORMULA_7_VALUE_3 = "1";
        public static final String FORMULA_7_VALUE_4 = "3.2";
        public static final String FORMULA_7_VALUE_5 = "Water";
        public static final String FORMULA_7_VALUE_6 = "120";
        public static final String FORMULA_8_VALUE_1 = "1";
        public static final String FORMULA_8_VALUE_2 = "3.66";
        public static final String FORMULA_8_VALUE_3 = "3505";
        public static final String FORMULA_8_VALUE_4 = "Air";
        public static final String FORMULA_8_VALUE_5 = "60";
        public static final String FORMULA_8_VALUE_6 = "Wat";
        public static final String FORMULA_8_VALUE_7 = "5.832";
        public static final String FORMULA_9_VALUE_1 = "5";
        public static final String FORMULA_10_VALUE_1 = "10";
        public static final String FORMULA_10_VALUE_2 = "20";
        public static final String FORMULA_10_VALUE_3 = "30";
        public static final String FORMULA_10_VALUE_4 = "40";
        public static final String FORMULA_10_VALUE_5 = "50";
        public static final String FORMULA_10_VALUE_6 = "60";
        public static final String FORMULA_11_VALUE_1 = "5.832";
        public static final String FORMULA_12_VALUE_1 = "80";
        public static final String FORMULA_13_VALUE_1 = "10";
        public static final String FORMULA_13_VALUE_2 = "20";
        public static final String FORMULA_13_VALUE_3 = "30";
        public static final String FORMULA_13_VALUE_4 = "40";
        public static final String FORMULA_13_VALUE_5 = "50";
        public static final String FORMULA_14_VALUE_1 = "80";
        public static final String FORMULA_15_VALUE_1 = "10";
        public static final String FORMULA_15_VALUE_2 = "1";
        public static final String FORMULA_15_VALUE_3 = "120";
        public static final String FORMULA_16_VALUE_1 = "5";
        public static final String FORMULA_16_VALUE_2 = "Unitary HP";
        public static final String FORMULA_16_VALUE_3 = "8.0";
        public static final String FORMULA_16_VALUE_4 = "Electric Resistance";
        public static final String FORMULA_17_VALUE_1 = "10";
        public static final String FORMULA_18_VALUE_1 = "10";
        public static final String FORMULA_18_VALUE_2 = "1";
        public static final String FORMULA_18_VALUE_3 = "k";
        public static final String FORMULA_18_VALUE_4 = "50";
        public static final String FORMULA_18_VALUE_5 = "10";
        public static final String FORMULA_18_VALUE_6 = "Yes";
        public static final String FORMULA_18_VALUE_7 = "10";
        public static final String FORMULA_19_VALUE_1 = "10";
        public static final String FORMULA_19_VALUE_2 = "20";
        public static final String FORMULA_19_VALUE_3 = "1";
        public static final String FORMULA_19_VALUE_4 = "None";
        public static final String FORMULA_19_VALUE_5 = "30";
        public static final String FORMULA_20_VALUE_1 = "1";
        public static final String FORMULA_20_VALUE_2 = "9000";
        public static final String FORMULA_20_VALUE_3 = "1";
        public static final String FORMULA_20_VALUE_4 = "1";
        public static final String FORMULA_20_VALUE_5 = "1";
        public static final String FORMULA_20_VALUE_6 = "k";
        public static final String FORMULA_21_VALUE_1 = "80";
        public static final String FORMULA_22_VALUE_1 = "Low Pressure-drop Filters";
        public static final String FORMULA_22_VALUE_2 = "1000";
        public static final String FORMULA_23_VALUE_1 = "1";
        public static final String FORMULA_23_VALUE_2 = "10";
        public static final String FORMULA_23_VALUE_3 = "10";
        public static final String FORMULA_23_VALUE_4 = "10";
        public static final String FORMULA_23_VALUE_5 = "10";
        public static final String FORMULA_23_VALUE_6 = "k";
        public static final String FORMULA_24_VALUE_1 = "10";
        public static final String FORMULA_24_VALUE_2 = "200";
        public static final String FORMULA_25_VALUE_1 = "10";
        public static final String FORMULA_25_VALUE_2 = "200";
        public static final String FORMULA_26_VALUE_1 = "Name";
        public static final String FORMULA_27_VALUE_1 = "Network Desktop Computer Power Mgt Software";
        public static final String FORMULA_27_VALUE_2 = "2";
        public static final String FORMULA_27_VALUE_3 = "Yes";
        public static final String FORMULA_27_VALUE_4 = "4";
        public static final String FORMULA_28_VALUE_1 = "Name";
        public static final String FORMULA_29_VALUE_1 = "Name";
        public static final String FORMULA_30_VALUE_1 = "10";
        public static final String FORMULA_31_VALUE_1 = "10";
        public static final String FORMULA_32_VALUE_1 = "10";
        public static final String FORMULA_33_VALUE_1 = "10";
        public static final String FORMULA_34_VALUE_1 = "10";
        public static final String FORMULA_35_VALUE_1 = "10";
        public static final String FORMULA_36_VALUE_1 = "10";
        public static final String FORMULA_37_VALUE_1 = "10";
        public static final String FORMULA_38_VALUE_1 = "10";
        public static final String FORMULA_39_VALUE_1 = "10";
        public static final String FORMULA_40_VALUE_1 = "10";
        public static final String FORMULA_41_VALUE_1 = "10";
        public static final String FORMULA_42_VALUE_1 = "10";
        public static final String FORMULA_43_VALUE_1 = "10";
        public static final String FORMULA_44_VALUE_1 = "10";
        public static final String FORMULA_45_VALUE_1 = "10";
        public static final String FORMULA_46_VALUE_1 = "10";
        public static final String FORMULA_47_VALUE_1 = "10";
        public static final String FORMULA_48_VALUE_1 = "10";
        public static final String FORMULA_49_VALUE_1 = "10";
        public static final String FORMULA_50_VALUE_1 = "10";
        public static final String FORMULA_51_VALUE_1 = "10";
        public static final String FORMULA_52_VALUE_1 = "650";
        public static final String FORMULA_52_VALUE_2 = "2";
        public static final String FORMULA_53_VALUE_1 = "10";
        public static final String FORMULA_53_VALUE_2 = "20";
        public static final String FORMULA_54_VALUE_1 = "20";
        public static final String FORMULA_54_VALUE_2 = "10";
        public static final String FORMULA_55_VALUE_1 = "20";
        public static final String FORMULA_55_VALUE_2 = "10";
        public static final String FORMULA_56_VALUE_1 = "10";
        public static final String FORMULA_56_VALUE_2 = "20";
        public static final String FORMULA_56_VALUE_3 = "30";
        public static final String FORMULA_56_VALUE_4 = "40";
        public static final String FORMULA_57_VALUE_1 = "10";
        public static final String FORMULA_57_VALUE_2 = "20";
        public static final String FORMULA_57_VALUE_3 = "30";
        public static final String FORMULA_57_VALUE_4 = "40";
        public static final String FORMULA_58_VALUE_1 = "10";
        public static final String FORMULA_58_VALUE_2 = "20";
        public static final String FORMULA_58_VALUE_3 = "30";
        public static final String FORMULA_58_VALUE_4 = "40";
        public static final String FORMULA_59_VALUE_1 = "10";
        public static final String FORMULA_59_VALUE_2 = "20";
        public static final String FORMULA_59_VALUE_3 = "30";
        public static final String FORMULA_59_VALUE_4 = "40";
        public static final String FORMULA_60_VALUE_1 = "10";
        public static final String FORMULA_60_VALUE_2 = "20";
        public static final String FORMULA_60_VALUE_3 = "30";
        public static final String FORMULA_61_VALUE_1 = "10";
        public static final String FORMULA_61_VALUE_2 = "20";
        public static final String FORMULA_61_VALUE_3 = "30";
        public static final String FORMULA_62_VALUE_1 = "10";
        public static final String FORMULA_62_VALUE_2 = "20";
        public static final String FORMULA_62_VALUE_3 = "30";
        public static final String FORMULA_63_VALUE_1 = "k";
        public static final String FORMULA_63_VALUE_2 = "20";
        public static final String FORMULA_63_VALUE_3 = "30";
        public static final String FORMULA_63_VALUE_4 = "30";
        public static final String FORMULA_64_VALUE_1 = "k";
        public static final String FORMULA_64_VALUE_2 = "20";
        public static final String FORMULA_64_VALUE_3 = "30";
        public static final String FORMULA_64_VALUE_4 = "30";
        public static final String FORMULA_65_VALUE_1 = "k";
        public static final String FORMULA_65_VALUE_2 = "20";
        public static final String FORMULA_65_VALUE_3 = "30";
        public static final String FORMULA_65_VALUE_4 = "30";
        public static final String FORMULA_66_VALUE_1 = "k";
        public static final String FORMULA_66_VALUE_2 = "20";
        public static final String FORMULA_66_VALUE_3 = "30";
        public static final String FORMULA_66_VALUE_4 = "30";
        public static final String FORMULA_67_VALUE_1 = "k";
        public static final String FORMULA_67_VALUE_2 = "20";
        public static final String FORMULA_67_VALUE_3 = "30";
        public static final String FORMULA_67_VALUE_4 = "30";
        public static final String FORMULA_68_VALUE_1 = "k";
        public static final String FORMULA_68_VALUE_2 = "20";
        public static final String FORMULA_68_VALUE_3 = "30";
        public static final String FORMULA_68_VALUE_4 = "30";
        public static final String FORMULA_69_VALUE_1 = "k";
        public static final String FORMULA_69_VALUE_2 = "20";
        public static final String FORMULA_69_VALUE_3 = "30";
        public static final String FORMULA_69_VALUE_4 = "30";
        public static final String FORMULA_70_VALUE_1 = "k";
        public static final String FORMULA_70_VALUE_2 = "20";
        public static final String FORMULA_70_VALUE_3 = "30";
        public static final String FORMULA_70_VALUE_4 = "30";
        public static final String FORMULA_71_VALUE_1 = "k";
        public static final String FORMULA_71_VALUE_2 = "20";
        public static final String FORMULA_71_VALUE_3 = "30";
        public static final String FORMULA_71_VALUE_4 = "30";
        public static final String FORMULA_72_VALUE_1 = "k";
        public static final String FORMULA_72_VALUE_2 = "20";
        public static final String FORMULA_72_VALUE_3 = "30";
        public static final String FORMULA_72_VALUE_4 = "30";
        public static final String FORMULA_73_VALUE_1 = "k";
        public static final String FORMULA_73_VALUE_2 = "20";
        public static final String FORMULA_73_VALUE_3 = "30";
        public static final String FORMULA_73_VALUE_4 = "30";
        public static final String FORMULA_74_VALUE_1 = "k";
        public static final String FORMULA_74_VALUE_2 = "20";
        public static final String FORMULA_74_VALUE_3 = "30";
        public static final String FORMULA_74_VALUE_4 = "30";
        public static final String FORMULA_75_VALUE_1 = "k";
        public static final String FORMULA_75_VALUE_2 = "20";
        public static final String FORMULA_75_VALUE_3 = "30";
        public static final String FORMULA_75_VALUE_4 = "30";
        public static final String FORMULA_76_VALUE_1 = "k";
        public static final String FORMULA_76_VALUE_2 = "20";
        public static final String FORMULA_76_VALUE_3 = "30";
        public static final String FORMULA_76_VALUE_4 = "30";
        public static final String FORMULA_77_VALUE_1 = "k";
        public static final String FORMULA_77_VALUE_2 = "20";
        public static final String FORMULA_77_VALUE_3 = "30";
        public static final String FORMULA_77_VALUE_4 = "30";
        public static final String FORMULA_78_VALUE_1 = "k";
        public static final String FORMULA_78_VALUE_2 = "20";
        public static final String FORMULA_78_VALUE_3 = "30";
        public static final String FORMULA_78_VALUE_4 = "30";
        public static final String FORMULA_79_VALUE_1 = "k";
        public static final String FORMULA_79_VALUE_2 = "20";
        public static final String FORMULA_79_VALUE_3 = "30";
        public static final String FORMULA_79_VALUE_4 = "30";
        public static final String FORMULA_80_VALUE_1 = "k";
        public static final String FORMULA_80_VALUE_2 = "20";
        public static final String FORMULA_80_VALUE_3 = "30";
        public static final String FORMULA_80_VALUE_4 = "30";
        public static final String FORMULA_81_VALUE_1 = "k";
        public static final String FORMULA_81_VALUE_2 = "20";
        public static final String FORMULA_81_VALUE_3 = "30";
        public static final String FORMULA_81_VALUE_4 = "30";
        public static final String FORMULA_82_VALUE_1 = "k";
        public static final String FORMULA_82_VALUE_2 = "20";
        public static final String FORMULA_82_VALUE_3 = "30";
        public static final String FORMULA_82_VALUE_4 = "30";
        public static final String FORMULA_83_VALUE_1 = "k";
        public static final String FORMULA_83_VALUE_2 = "20";
        public static final String FORMULA_83_VALUE_3 = "30";
        public static final String FORMULA_83_VALUE_4 = "30";
        public static final String FORMULA_84_VALUE_1 = "k";
        public static final String FORMULA_84_VALUE_2 = "20";
        public static final String FORMULA_84_VALUE_3 = "30";
        public static final String FORMULA_84_VALUE_4 = "30";
        public static final String FORMULA_85_VALUE_1 = "k";
        public static final String FORMULA_85_VALUE_2 = "20";
        public static final String FORMULA_85_VALUE_3 = "30";
        public static final String FORMULA_85_VALUE_4 = "30";
        public static final String FORMULA_86_VALUE_1 = "ary";
        public static final String FORMULA_86_VALUE_2 = "2000";
        public static final String FORMULA_87_VALUE_1 = "FCU";
        public static final String FORMULA_87_VALUE_2 = "8.0";
        public static final String FORMULA_87_VALUE_3 = "Electric d";
        public static final String FORMULA_88_VALUE_1 = "10";
        public static final String FORMULA_88_VALUE_2 = "260";
        public static final String FORMULA_89_VALUE_1 = "10";
        public static final String FORMULA_90_VALUE_1 = "10";
        public static final String FORMULA_90_VALUE_2 = "10";
        public static final String FORMULA_90_VALUE_3 = "10";
        public static final String FORMULA_90_VALUE_4 = "10";
        public static final String FORMULA_90_VALUE_5 = "10";
        public static final String FORMULA_90_VALUE_6 = "10";
        public static final String FORMULA_90_VALUE_7 = "10";
        public static final String FORMULA_90_VALUE_8 = "10";
        public static final String FORMULA_91_VALUE_1 = "80";
        public static final String FORMULA_92_VALUE_1 = "50";
        public static final String FORMULA_92_VALUE_2 = "1";
        public static final String FORMULA_92_VALUE_3 = "2";
        public static final String FORMULA_93_VALUE_1 = "0";
        public static final String FORMULA_93_VALUE_2 = "6";
        public static final String FORMULA_93_VALUE_3 = "6";
        public static final String FORMULA_93_VALUE_4 = "0";
        public static final String FORMULA_94_VALUE_1 = "Unitary";
        public static final String FORMULA_95_VALUE_1 = "16";
        public static final String FORMULA_95_VALUE_2 = "0";
        public static final String FORMULA_95_VALUE_3 = "12";
        public static final String FORMULA_95_VALUE_4 = "16";
        public static final String FORMULA_95_VALUE_5 = "16";
        public static final String FORMULA_95_VALUE_6 = "16";
        public static final String FORMULA_95_VALUE_7 = "0";
        public static final String FORMULA_95_VALUE_8 = "15";
        public static final String FORMULA_96_VALUE_1 = "14";
        public static final String FORMULA_96_VALUE_2 = "12";
        public static final String FORMULA_96_VALUE_3 = "14";
        public static final String FORMULA_96_VALUE_4 = "0";
        public static final String FORMULA_96_VALUE_5 = "14";
        public static final String FORMULA_96_VALUE_6 = "8";
        public static final String FORMULA_96_VALUE_7 = "7";
        public static final String FORMULA_96_VALUE_8 = "Water-Cooled Unitary";
        public static final String FORMULA_96_VALUE_9 = "12";
        public static final String FORMULA_96_VALUE_10 = "12";
        public static final String FORMULA_96_VALUE_11 = "Unitary HP";
        public static final String FORMULA_96_VALUE_12 = "12";
        public static final String FORMULA_96_VALUE_13 = "12";
        public static final String FORMULA_96_VALUE_14 = "12";
        public static final String FORMULA_97_VALUE_1 = "14";
        public static final String FORMULA_97_VALUE_2 = "12";
        public static final String FORMULA_97_VALUE_3 = "14";
        public static final String FORMULA_97_VALUE_4 = "0";
        public static final String FORMULA_97_VALUE_5 = "14";
        public static final String FORMULA_98_VALUE_1 = "14";
        public static final String FORMULA_98_VALUE_2 = "12";
        public static final String FORMULA_98_VALUE_3 = "14";
        public static final String FORMULA_98_VALUE_4 = "0";
        public static final String FORMULA_98_VALUE_5 = "14";
        public static final String FORMULA_99_VALUE_1 = "14";
        public static final String FORMULA_99_VALUE_2 = "12";
        public static final String FORMULA_99_VALUE_3 = "14";
        public static final String FORMULA_99_VALUE_4 = "0";
        public static final String FORMULA_99_VALUE_5 = "14";
        public static final String FORMULA_99_VALUE_6 = "14";
        public static final String FORMULA_99_VALUE_7 = "12";
        public static final String FORMULA_99_VALUE_8 = "14";
        public static final String FORMULA_100_VALUE_1 = "14";
        public static final String FORMULA_100_VALUE_2 = "12";
        public static final String FORMULA_100_VALUE_3 = "14";
        public static final String FORMULA_100_VALUE_4 = "0";
        public static final String FORMULA_100_VALUE_5 = "14";
        public static final String FORMULA_101_VALUE_1 = "14";
        public static final String FORMULA_101_VALUE_2 = "12";
        public static final String FORMULA_101_VALUE_3 = "14";
        public static final String FORMULA_101_VALUE_4 = "0";
        public static final String FORMULA_101_VALUE_5 = "14";
        public static final String FORMULA_102_VALUE_1 = "14";
        public static final String FORMULA_102_VALUE_2 = "12";
        public static final String FORMULA_102_VALUE_3 = "14";
        public static final String FORMULA_102_VALUE_4 = "0";
        public static final String FORMULA_102_VALUE_5 = "14";
        public static final String FORMULA_103_VALUE_1 = "14";
        public static final String FORMULA_103_VALUE_2 = "12";
        public static final String FORMULA_103_VALUE_3 = "14";
        public static final String FORMULA_103_VALUE_4 = "0";
        public static final String FORMULA_103_VALUE_5 = "14";
        public static final String FORMULA_103_VALUE_6 = "8";
        public static final String FORMULA_103_VALUE_7 = "7";
        public static final String FORMULA_103_VALUE_8 = "12";
        public static final String FORMULA_103_VALUE_9 = "12";
        public static final String FORMULA_103_VALUE_10 = "12";
        public static final String FORMULA_103_VALUE_11 = "12";
        public static final String FORMULA_103_VALUE_12 = "12";
        public static final String FORMULA_104_VALUE_1 = "14";
        public static final String FORMULA_104_VALUE_2 = "12";
        public static final String FORMULA_104_VALUE_3 = "14";
        public static final String FORMULA_104_VALUE_4 = "0";
        public static final String FORMULA_104_VALUE_5 = "14";
        public static final String FORMULA_105_VALUE_1 = "2/15/2013";
        public static final String FORMULA_105_VALUE_2 = "0";
        public static final String FORMULA_106_VALUE_1 = "5/15/2014";
        public static final String FORMULA_107_VALUE_1 = "Yes";
        public static final String FORMULA_108_VALUE_1 = "2";
        public static final String FORMULA_108_VALUE_2 = "3";
        public static final String FORMULA_108_VALUE_3 = "4";
        public static final String FORMULA_109_VALUE_1 = "Yes";
        public static final String FORMULA_110_VALUE_1 = "2";
        public static final String FORMULA_110_VALUE_2 = "3";
        public static final String FORMULA_110_VALUE_3 = "4";

        public static final String FORMULA_1_RESULT = "30";
        public static final String FORMULA_2_RESULT = "10025.22";
        public static final String FORMULA_3_RESULT = "250";
        public static final String FORMULA_4_RESULT = "16";
        public static final String FORMULA_5_RESULT = "PTAC";
        public static final String FORMULA_6_RESULT = "HVAC - Air Conditioner - 10 ton";
        public static final String FORMULA_7_RESULT = "10.59211538461537";
        public static final String FORMULA_8_RESULT = "52338.79851581999";
        public static final String FORMULA_9_RESULT = "0.7";
        public static final String FORMULA_10_RESULT = "246000";
        public static final String FORMULA_11_RESULT = "0.6";
        public static final String FORMULA_12_RESULT = "Chiller - 80 ton";
        public static final String FORMULA_13_RESULT = "14.4";
        public static final String FORMULA_14_RESULT = "CO2 Sensor - 80 ton";
        public static final String FORMULA_15_RESULT = "120";
        public static final String FORMULA_16_RESULT = "3.412";
        public static final String FORMULA_17_RESULT = "10";
        public static final String FORMULA_18_RESULT = "1000";
        public static final String FORMULA_19_RESULT = "6000";
        public static final String FORMULA_20_RESULT = "8760";
        public static final String FORMULA_21_RESULT = "Compressed Air - 80";
        public static final String FORMULA_22_RESULT = "1";
        public static final String FORMULA_23_RESULT = "1000";
        public static final String FORMULA_24_RESULT = "2000";
        public static final String FORMULA_25_RESULT = "2000";
        public static final String FORMULA_26_RESULT = "Name";
        public static final String FORMULA_27_RESULT = "8";
        public static final String FORMULA_28_RESULT = "Name";
        public static final String FORMULA_29_RESULT = "Name";
        public static final String FORMULA_30_RESULT = "10";
        public static final String FORMULA_31_RESULT = "10";
        public static final String FORMULA_32_RESULT = "10";
        public static final String FORMULA_33_RESULT = "10";
        public static final String FORMULA_34_RESULT = "10";
        public static final String FORMULA_35_RESULT = "10";
        public static final String FORMULA_36_RESULT = "10";
        public static final String FORMULA_37_RESULT = "10";
        public static final String FORMULA_38_RESULT = "10";
        public static final String FORMULA_39_RESULT = "10";
        public static final String FORMULA_40_RESULT = "10";
        public static final String FORMULA_41_RESULT = "10";
        public static final String FORMULA_42_RESULT = "10";
        public static final String FORMULA_43_RESULT = "10";
        public static final String FORMULA_44_RESULT = "10";
        public static final String FORMULA_45_RESULT = "10";
        public static final String FORMULA_46_RESULT = "10";
        public static final String FORMULA_47_RESULT = "10";
        public static final String FORMULA_48_RESULT = "10";
        public static final String FORMULA_49_RESULT = "10";
        public static final String FORMULA_50_RESULT = "10";
        public static final String FORMULA_51_RESULT = "10";
        public static final String FORMULA_52_RESULT = "1300";
        public static final String FORMULA_53_RESULT = "200";
        public static final String FORMULA_54_RESULT = "200";
        public static final String FORMULA_55_RESULT = "200";
        public static final String FORMULA_56_RESULT = "240000";
        public static final String FORMULA_57_RESULT = "240000";
        public static final String FORMULA_58_RESULT = "240000";
        public static final String FORMULA_59_RESULT = "240000";
        public static final String FORMULA_60_RESULT = "200";
        public static final String FORMULA_61_RESULT = "200";
        public static final String FORMULA_62_RESULT = "200";
        public static final String FORMULA_63_RESULT = "24000";
        public static final String FORMULA_64_RESULT = "24000";
        public static final String FORMULA_65_RESULT = "24000";
        public static final String FORMULA_66_RESULT = "24000";
        public static final String FORMULA_67_RESULT = "600";
        public static final String FORMULA_68_RESULT = "600";
        public static final String FORMULA_69_RESULT = "600";
        public static final String FORMULA_70_RESULT = "600";
        public static final String FORMULA_71_RESULT = "600";
        public static final String FORMULA_72_RESULT = "600";
        public static final String FORMULA_73_RESULT = "600";
        public static final String FORMULA_74_RESULT = "600";
        public static final String FORMULA_75_RESULT = "600";
        public static final String FORMULA_76_RESULT = "600";
        public static final String FORMULA_77_RESULT = "600";
        public static final String FORMULA_78_RESULT = "600";
        public static final String FORMULA_79_RESULT = "600";
        public static final String FORMULA_80_RESULT = "600";
        public static final String FORMULA_81_RESULT = "600";
        public static final String FORMULA_82_RESULT = "600";
        public static final String FORMULA_83_RESULT = "600";
        public static final String FORMULA_84_RESULT = "600";
        public static final String FORMULA_85_RESULT = "600";
        public static final String FORMULA_86_RESULT = "600";
        public static final String FORMULA_87_RESULT = "8";
        public static final String FORMULA_88_RESULT = "2600";
        public static final String FORMULA_89_RESULT = "0";
        public static final String FORMULA_90_RESULT = "748.8000000000001";
        public static final String FORMULA_91_RESULT = "Hotel Occ Controls - 80";
        public static final String FORMULA_92_RESULT = "1";
        public static final String FORMULA_93_RESULT = "1";
        public static final String FORMULA_94_RESULT = "Unitary HP";
        public static final String FORMULA_95_RESULT = "2";
        public static final String FORMULA_96_RESULT = "0";
        public static final String FORMULA_97_RESULT = "600";
        public static final String FORMULA_98_RESULT = "600";
        public static final String FORMULA_99_RESULT = "600";
        public static final String FORMULA_100_RESULT = "600";
        public static final String FORMULA_101_RESULT = "600";
        public static final String FORMULA_102_RESULT = "600";
        public static final String FORMULA_103_RESULT = "600";
        public static final String FORMULA_104_RESULT = "600";
        public static final String FORMULA_105_RESULT = "false";
        public static final String FORMULA_106_RESULT = "15";
        public static final String FORMULA_107_RESULT = "Lighting Controls New Construction";
        public static final String FORMULA_108_RESULT = "24";
        public static final String FORMULA_109_RESULT = "Lighting Fixtures New Construction";
        public static final String FORMULA_110_RESULT = "24";


        public static final By L_TEST_VALUE_1 = By.xpath("//div/table/tbody/tr[2]/td[3]/div");
        public static final By L_TEST_VALUE_1_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_2 = By.xpath("//div/table/tbody/tr[3]/td[3]/div");
        public static final By L_TEST_VALUE_2_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_3 = By.xpath("//div/table/tbody/tr[4]/td[3]/div");
        public static final By L_TEST_VALUE_3_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_4 = By.xpath("//div/table/tbody/tr[5]/td[3]/div");
        public static final By L_TEST_VALUE_4_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_5 = By.xpath("//div/table/tbody/tr[6]/td[3]/div");
        public static final By L_TEST_VALUE_5_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_6 = By.xpath("//div/table/tbody/tr[7]/td[3]/div");
        public static final By L_TEST_VALUE_6_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_7 = By.xpath("//div/table/tbody/tr[8]/td[3]/div");
        public static final By L_TEST_VALUE_7_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_8 = By.xpath("//div/table/tbody/tr[9]/td[3]/div");
        public static final By L_TEST_VALUE_8_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_9 = By.xpath("//div/table/tbody/tr[10]/td[3]/div");
        public static final By L_TEST_VALUE_9_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_10 = By.xpath("//div/table/tbody/tr[11]/td[3]/div");
        public static final By L_TEST_VALUE_10_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_11 = By.xpath("//div/table/tbody/tr[12]/td[3]/div");
        public static final By L_TEST_VALUE_11_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_12 = By.xpath("//div/table/tbody/tr[13]/td[3]/div");
        public static final By L_TEST_VALUE_12_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_13 = By.xpath("//div/table/tbody/tr[14]/td[3]/div");
        public static final By L_TEST_VALUE_13_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_14 =By.xpath( "//div/table/tbody/tr[15]/td[3]/div");
        public static final By L_TEST_VALUE_14_Field = By.cssSelector("[name='testValue']");
        public static final By L_CALCULATION_LINK = By.cssSelector("[id='calculationLink']");
        public static final By L_CALCULATION_RESULT = By.cssSelector("[name='formulaResultField-inputEl']");

    }


    public interface Program_Bulk_Upload {
        public static final String Link = "AK-PBU";
        public static final By ADMIN = By.xpath("//div/ul/li/a[text()[contains(.,'ADMIN')]]");
        public static final By L_PROGRAMS = By.cssSelector(".main_bg:nth-child(2)>a");
        public static final String Link_PBU_Void_Prog = "AK-PBU-Void";

    }

    public interface Planning {
        public static final By PL_CREATE_PROG_BTN = By.xpath("//span[text()[contains(.,'+ Create Program')]]/../../.."); // go up click on a
        // Form 'Create New Program'
        public static final By PL_CHOOSE_EXISTING = By.xpath("//div/input[@placeholder='Program Details.....']");
        public static final By PL_NAME_FIELD = By.xpath("//*[@name='name']");
        public static final By PL_DESCRIPTION_FIELD = By.xpath("//*[@name='description']");
        public static final By PL_TYPE_DD_FIELD = By.xpath("//*[@name='programTypeId']");
        public static final By PL_SECTORS_DD_FIELD =  By.xpath("//label/span[text()[contains(.,'Sectors')]]/../..//div/input[@placeholder='Select to add']");
		public static final By PL_SERVICE_TYPE_DD_FIELD =  By.xpath("//label/span[text()[contains(.,'Service Type')]]/../..//div/input[@placeholder='Select " +
				"to add']");
        public static final By PL_DEFAULT_SECTOR_FIELD = By.xpath("//*[@name='defaultSectorId']");
        public static final By PL_START_YEAR = By.xpath("//*[@name='planningStartYear']");
        public static final By PL_END_YEAR = By.xpath("//*[@name='planningEndYear']");
        public static final By PL_PROG_START_YEAR = By.xpath("//*[@name='programStartYear']");
        public static final By PL_PROG_END_YEAR = By.xpath("//*[@name='programEndYear']");
        public static final By PL_SAVE_PROG_BTN = By.xpath("//span[text()[contains(.,'Save')]]/../../..");

        public static final By PL_SHOW_20 = By.xpath("//div/a[text()[contains(.,'20')]]");
        public static final By PL_SHOW_50 = By.xpath("//div/a[text()[contains(.,'50')]]");
        public static final By PL_SHOW_100 = By.xpath("//div/a[text()[contains(.,'100')]]");
        public static final By PL_SORT_LAST_UPDATED = By.xpath("//div/a/span[text()[contains(.,'Last Updated')]]");
        public static final By PL_TYPE_DD = By.xpath("//*[@name='type_1_field-inputEl']");
        public static final By PL_KEYWORD_TXT = By.xpath("//input[@name='keyword_1_field-inputEl']");
        public static final By PL_SEARCH_BTN = By.xpath("//input[@id='searchContact' and @value='Search']");

        public static final By PL_OVERVIEW_LINK = spanText("Overview");
        public static final By PL_INPUT_LINK = spanText("Input");
        public static final By PL_BUDGETING_LINK = spanText("Budgeting");
        public static final By PL_ANALYZE_LINK = spanText("Analyze");
        // After the BTN save new program was clicked 'Overview'
        public static final By PL_EDIT_PARAMETERS_BTN = By.xpath("//div[4]/em/button/span[text()[contains(.,'Edit')]]");
        public static final By PL_SAVE_PARAM_BTN = By.xpath("//div/em/button/span[text()[contains(.,'Save')]]");
        public static final By PL_PROGRAME_NAME = By.cssSelector("#planningTitle");
        // Parameters Fields
        public static final By PL_MARGINAL_COST = By.xpath("//*[@name='marginalCostId']");
        public static final By PL_SYSTEMS_INPUT = By.xpath("//*[@name='systemInputId']");
        public static final By PL_MARGINAL_RATE = By.xpath("//*[@name='marginalRateId']");
        public static final By PL_CUSTOMERS_FORECAST = By.xpath("//*[@name='planningCustForecastLibId']");

        public static final By PL_GAS_MARGINAL_COST = By.xpath("//*[@name='gasMarginalCostId']");
        public static final By PL_GAS_SYSTEMS_INPUT = By.xpath("//*[@name='gasSystemInputId']");
        public static final By PL_GAS_MARGINAL_RATE = By.xpath("//*[@name='gasMarginalRateId']");
        public static final By PL_GAS_CUSTOMERS_FORECAST = By.xpath("//*[@name='gasCustForecastLibId']");
        // Measures
        public static final By PL_EDIT_MEASURES_BTN = By.xpath("//span[1][text()[contains(.,'+ Edit Measures')]]");
        // 'Edit Measures'
        public static final By PL_SELECT_TAXONOMY = By.xpath("//div/input[@value='Categories']");
        public static final By PL_SEARCH_ALL = spanText("Search All");
        public static final By PL_DRAG_AND_DROP_TARGET = By.cssSelector("#dropPromptText");
        public static final By PL_TYPE_COLUMN = By.xpath("//div/span[text()[contains(.,'Type')]]");
        public static final By PL_INCLUDE_SELECTED_BTN = spanText("Include Selected");
        public static final By PL_DONE_MEASURING_BTN = spanText("Done Measure Editing");
        // Input screen
        public static final By PL_ELIGIBILITY_BTH = spanText("ELIGIBILITY");
        public static final By PL_PARTICIPATION_BTH = spanText("PARTICIPATION");
        public static final By PL_COST_BTH = spanText("COST");
        // Input - Eligibility
        // //input[starts-with(@name, 'combo-') and @type='text']
        // //label/span[text()[contains(.,'Support Staff')]]/../..//input[@type='text']
        public static final By PL_ELIGIBILITY_ROW = By.xpath("//div[starts-with(@componentid, 'inputMeasuresList')]/div/table/tbody/tr[1]/td[1]");
        public static final By PL_PARTICIPATION_ROW = By.xpath("//div[starts-with(@id, 'inputMeasuresList')]/../..//table[contains(@class, 'x-grid-item')]/..//tr[contains(@class, 'x-grid-row')]");
        public static final By PL_COST_ROW = By.xpath("//div[starts-with(@id, 'inputMeasuresList')]/../..//table[contains(@class, 'x-grid-item')]/..//tr[contains(@class, 'x-grid-row')]");
        public static final By PL_SECTOR_DD = By.xpath("//input[@name='eSectorId']");
        public static final By PL_FACILITY_TYPE_DD = By.xpath("//input[@name='eFacilityTypeId']");
        public static final By PL_CONSTRUCTION_TYPE_DD = By.xpath("//input[@name='eConstructionTypeId']");
        public static final By PL_EQUIPMENT_DD = By.xpath("//input[@name='eheatingEquipTypeId']");
        // Input - Participation
        public static final By PL_START_TXT = By.xpath("//*[@name='pStart']");
        public static final By PL_MAX_TXT =  By.xpath("//*[@name='pMax']");
        public static final By PL_INCREMENT_TXT =  By.xpath("//*[@name='pIncrement']");
        public static final By PL_UNIT_DD =  By.xpath("//*[@name='pUnit']");
        public static final By PL_NO_YEARS_TXT =  By.xpath("//*[@name='pNumYears']");
        public static final By PL_QTY_CUSTOMERS_TXT =  By.xpath("//*[@name='pQtyPerCustomer']");
        // Input - Cost
        public static final By PL_CAP_TYPE = By.xpath("//input[@name='cCapType']");
        public static final By PL_CAP_VALUE = By.xpath("//input[@name='cCapValue']");
        public static final By PL_INSTALL_COST = By.xpath("//input[@name='attribute164']");
        public static final By PL_INCENTIVE_CUSTOMER = By.xpath("//*[@name='attribute154']");
        public static final By PL_CREDIT_AMOUNT = By.xpath("//*[@name='attribute148']");
        public static final By PL_PARTNER_AMOUNT = By.xpath("//*[@name='attribute138']");
        public static final By PL_CUSTOMER_AMMOUNT = By.xpath("//*[@name='attribute130']");
        public static final By PL_INCREMENTAL = By.xpath("//*[@name='attribute129']");
        // Budgeting Cost - Budget Cost Reference Parameters
        public static final By PL_EDIT_BUDG_BTN = spanTextContains("Edit");
        public static final By PL_REFERENCE_SET_DD = By.xpath("//*[@name='planningBudgetCostReferenceId']");
        public static final By PL_SAVE_BTN = spanText("Save");
        // Budgeting Cost Categories
        public static final By PL_ADD_BTN_CC = spanText("Add");
        public static final By PL_BUD_CAT_DD = By.xpath("//*[@name='budgetCategoryId']");
        public static final By PL_METH_DD = By.xpath("//*[@name='methodology']");
        public static final By PL_VALUE1_TXT = By.xpath("//*[@name='value1']");
        // / Analyze
        public static final By PL_RUN_TEST_BTN = By.linkText("Run Tests");
        public static final By PL_SAVE_BTN_ANALYZE = spanText("Save");
        public static final By PL_SAVE_AS_BTN_ANALYZE = By.linkText("Save As");
        public static final By PL_NAME_TXT = By.xpath("//input[@id='saveASName-inputEl']");
        // Portfolio
        public static final By PL_PORTFOLIO_LINK = spanText("Portfolio");
        public static final By PL_CREATE_PORTF_BTN = spanText("+ Create Portfolio");
        public static final By PL_NAME_PF_TXT = By.xpath("//*[@name='portfolioName']");
        public static final By PL_DESCRIPTION_TXT = By.xpath("//*[@name='description']");
        public static final By PL_SAVE_PRF_BTN = spanText("Save");
        // Edit Portfolio
        public static final By PL_EDIT_PROGRAMS = spanText("+ Edit Programs");
        public static final By PL_SELECT_TYPE = By.cssSelector(".x-form-trigger");
        public static final By PL_SEARCH_ALL_BTN = spanText("Search All");
        public static final By PL_CHECK_BOX = By.cssSelector(".x-grid-row-checker");
        public static final By PL_DONE_PROGRAM_EDIT = spanText("Done Program Editing");
        public static final By PL_PORTFOLIO_ANALYZER = By.xpath("//div/em/button/span[text()[contains(.,'Portfolio Analyzer')]]");
        public static final By PL_ANALYZE_BTN = By.xpath("//div/em/button/span[text()[contains(.,'Analyze')]]");
    }

    public interface Marketing{
        // Tabs
        public static final By MR_CAMPAIGNS_TAB = spanText("Campaigns");
        public static final By MR_DASHBOARD_TAB = spanText("Dashboard");
        public static final By MR_LEADS_TAB = spanText("Leads");
        public static final By MR_EMAIl_TEMPLATES = spanText("Email Templates");
        // Create Campaign
        public static final By MR_CREATE_CAMPAIGN_BTN = spanText("Create Campaigns");
        public static final By MR_POPUP_HEADER = divText("Create Campaign");
        public static final By MR_NAME_TXT = By.xpath("//input[@name='campaignName']");
        public static final By MR_STATUS_DD = By.xpath("//input[@name='statusId']");
        public static final By MR_START_DATE  = By.xpath("//input[@name='startDate']");
        public static final By MR_END_DATE = By.xpath("//input[@name='endDate']");
        public static final By MR_SUPPORT_STAFF = By.xpath("//label/span[text()[contains(.,'Support Staff')]]/../..//input[@type='text']");
        public static final By MR_CAMPAIGN_MANAGER  = By.xpath("//label/span[text()[contains(.,'Campaign Manager')]]/../..//input[@type='text']");
        public static final By MR_OBJECTIVE = By.xpath("//textarea[@name='objective']");
        public static final By MR_DESCRIPTION = By.xpath("//textarea[@name='description']");
        public static final By MR_TYPE = By.xpath("//input[@name='campaignTypeId']");
        public static final By MR_RELATED_PROGS = By.xpath("//label/span[text()[contains(.,'Related Programs')]]/../..//input[@type='text']");
        // Show All Filter
        public static final By MR_SHOW_ADDED_BY_YOU = By.partialLinkText("Added by You");
        public static final By MR_SHOW_COMPLETED = By.partialLinkText("Completed");
        public static final By MR_SHOW_IN_PROGRESS = By.partialLinkText("In Progress");
        public static final By MR_SHOW_ALL = By.partialLinkText("All");
        // Sort By
        public static final By LUD = By.xpath("//div/a/span[text()[contains(., 'Last Updated Date')]]");
        public static final By ED = By.xpath("//div/a/span[text()[contains(., 'End Date')]]");
        public static final By SD = By.xpath("//div/a/span[text()[contains(., 'Start Date')]]");
        public static final By Type = By.xpath("//div/a/span[text()[contains(., 'Type')]]");
        public static final By Name = By.xpath("//div/a/span[text()[contains(., 'Name')]]");
        // pop up
        public static final By MR_POPUP_OK_BTN = spanText("OK");
        // campaign page
        public static final By MR_ADD_BTN = spanText("+ Add");
        // add 'Activity' // //*[@id='']   //*[@name='']
        public static final By MR_ACTIVITY_HEADER = spanText("Activities");
        public static final By MR_TITLE = By.xpath("//*[@name='activityTitle']");
        public static final By MR_STATUS = By.xpath("//*[@name='statusId']");
        public static final By MR_DESCRIPTION_2 = By.xpath("//*[@name='description']");
        public static final By MR_PRIORITY = By.xpath("//*[@name='priorityId']");
        public static final By MR_NEED_BY_DATE = By.xpath("//*[@name='needByDate']");
        public static final By MR_PR_COMPLETE  = By.xpath("//*[@name='percentComplete']");
        public static final By MR_ASSIGNED_TO = By.xpath("//*[@name='assignedToId']");
        public static final By MR_NOTES = By.xpath("//*[@name='notes']");
        // add 'Emails'
        public static final By MR_EMAIL_HEADER = spanText("Emails");
        public static final By MR_EMAIL_NAME_TXT = By.xpath("//*[@name='templateName']");
        public static final By MR_SUBJECT_TXT = By.xpath("//*[@name='subject']");
        public static final By MR_BODY_AREA = By.xpath("//*[@name='body']");
        public static final By MR_NAME_TOKEN = divText("${CAMPAIGN_NAME}");
        public static final By MR_NUMBER_TOKEN = divText("${TM_NUMBER}");
        public static final By MR_TEMPLATE_ARR_DD = By.xpath("//label/span[text()[contains(.,'Template Attachment')]]/../..//input[@type='text']");
        public static final By MR_SAVE_EMAIL = spanText("Save");
        public static final By MR_CLOSE_EMAIL = spanText("Close");
        // Participants
        public static final By MR_PARTICIPANTS = spanText("Participants");
        public static final By MR_OPTIONS = spanText("Options");
        public static final By MR_SELECT_EXISTING = By.xpath("//input[starts-with(@name, 'combo-') and @type='text']");
        // mark as lead
        public static final By MR_MARK_AS_LEAD = spanText("Mark as Lead");
        public static final By MR_LEAD_QUALIFIER = By.xpath("//input[@name='leadQualifierId']");
        public static final By MR_LEAD_STATUS = By.xpath("//input[@name='leadStatusId']");
        public static final By MR_OWNER = By.xpath("//*[@name='leadOwnerId']");
        // //div[@id='variable-program-cost-window'])//span[text()[.='Save']]
        public static final By MR_SAVE_LEAD = By.xpath("//div[starts-with(@id, 'markaslead-') and @data-ref='body']//span[text()[.='Save']]");
        //   public static final By MR_SAVE_LEAD = spanText("Save"); // markaslead-1264
        // Leads
        public static final By MR_LEADS = spanText("Leads");
        // Select a Lead by anchor text
        // Add Opportunity
        public static final By MR_ADD_OPPORTNUITY = spanText("Add Opportunity");
        public static final By MR_PROGRAM_DD = By.xpath("//*[@name='programId']");
        public static final By MR_STATUS_DD_OPP = By.xpath("//*[@name='statusId']");
        public static final By MR_ASSIGNED_TO_OPP = By.xpath("//*[@name='assignedToId']");
        public static final By MR_COMMENTS = By.xpath("//*[@name='notes']");
        public static final By MR_MR_OPPORTNUITY_SAVE = spanText("Save");
        // Add Notes
        public static final By MR_ADD_NOTES_FOR_LEAD = spanText("Add Notes for Leads");
        public static final By MR_DATE_OF_CONTACT_NOTES = By.xpath("//*[@name='contactDateStr']");
        public static final By MR_CONTACTED_BY_NOTES = By.xpath("//*[@name='contactedBy']");
        public static final By MR_SUBJECT_NOTES = By.xpath("//*[@name='subject']");
        public static final By MR_NOTES_NOTES = By.xpath("//*[@name='notes']");
        public static final By MR_F_UP_CHECK = By.xpath("//input[contains(@class, 'x-form-checkbox') and @type='button']");
        public static final By MR_EMAIL_NOTES = By.xpath("//*[@name='followUpEmailAddress']");
        public static final By MR_TEXT_NOTES = By.xpath("//*[@name='followUpEmailText']");
        public static final By MR_F_UP_DATE = By.xpath("//*[@name='followUpDateStr']");
        // Activities
        public static final By MR_ACT_TITLE = By.xpath("//*[@name='activityTitle']");
        public static final By MR_ACT_STATUS = By.xpath("//*[@name='statusId']");
        public static final By MR_ACT_DESCRIPTION = By.xpath("//*[@name='description']");
        public static final By MR_ACT_PRIORITY = By.xpath("//*[@name='description']");
        public static final By MR_ACT_NEED_BY_DATE = By.xpath("//*[@name='needByDate']");
        public static final By MR_ACT_PERCENT_COMPLETE = By.xpath("//*[@name='percentComplete']");
        public static final By MR_ACT_ASSIGNED_TO = By.xpath("//*[@name='assignedToId']");
        public static final By MR_ACT_NOTES = By.xpath("//*[@name='notes']");
    }

    public interface Assessments{
        // Onsite Programs
        public static final By AS_ADD_NEW_PROGRAM = By.xpath("//input[@value='+ Add New Program']");
        public static final By AS_PROG_NAME = By.xpath("//input[@name='auditProgramName']");
        public static final By AS_PROG_DESCRIPTION = By.xpath("//textarea[@name='description']");
        public static final By AS_ASSESSMENT_TYPE = By.xpath("//input[@name='assessmentType']");
        public static final By AS_SERVICE_TYPE = By.xpath("//label/span[text()[contains(.,'Service Type')]]/../..//input[@type='text']");
        public static final By AS_PROG_STATUS = By.xpath("//input[@name='programStatus']");
        public static final By AS_NUM_PREFIX = By.xpath("//input[@name='auditProgramNumber']");
        // Assessment Settings
        public static final By AS_PROG_MANAGER = By.xpath("//input[@name='program_programmgrs_0_field_combobox-inputEl']");
        public static final By AS_PROJ_MANAGER = By.xpath("//input[@name='program_projectmgrs_0_field_combobox-inputEl']");
        public static final By AS_APP_USER = By.xpath("//input[@name='program_supportstaff_0_field_combobox-inputEl']");
        // Configuration Parameters
        public static final By AS_ADD_BTN = spanText("Add");
        public static final By AS_LABEL = By.xpath("//input[@name='label']");
        public static final By AS_VALUE = By.xpath("//input[@name='value']");
        public static final By AS_UPDATE = By.linkText("Update");
        //public static final By AS_UPDATE = By.xpath("//div[contains(@class,'manageScreensGrid ')]/div[3]/div[2]/div/div/a[1]/span/span");
        public static final By AS_SAVE = By.linkText("Save");

        // Attribute Groups
        public static final By AS_ATTR_GROUP = spanText("Attribute Groups");


        //Add Name
        public static final By AS_ATTR_NAME = By.xpath("//div[starts-with(@id,'managescreen')]//input[@name='auditProgramGroupName']");

        //Add Description
        public static final By AS_ATTR_DESCR = By.xpath("//div[starts-with(@id,'managescreen')]//input[@name='description']");

        public static final By AS_ATTR_ORDER = By.xpath("//div[starts-with(@id,'managescreen')]//input[@name='order']");

        public static final By AS_ATTR_SAVE_BTN = By.xpath("//div[starts-with(@id,'managescreen')]//span[text()[.='Save']]");

        //Click YES on Confirm pop up Manage screen
        public  static final By AS_ATTR_CON_BTN = By.xpath("/html/body/div[4]/div[3]/div/div/a[2]/span/span/span[2]");


        // Association Types
        public static final By AS_ASS_TYPE = spanText("Association Types");
        public static final By AS_ASS_ADD = By.xpath("//div[@id='associationTypesPopup']//span[text()[.='Add']]");
        public static final By AS_ASS_KEY = By.xpath("//div[@id='associationTypesPopup']//input[@name='key']");
        public static final By AS_ASS_NAME = By.xpath("//div[@id='associationTypesPopup']//input[@name='name']");
        public static final By AS_ASS_DESCR = By.xpath("//div[@id='associationTypesPopup']//input[@name='description']");
        public static final By AS_ASS_SAVE = By.xpath("//div[@id='associationTypesPopup']//span[text()[.='Save']]");
        // Attributes
        public static final By AS_ATTR = By.linkText("Attributes");
        public static final By AS_ATTR_HINT_TEXT = By.xpath("//*[@name='hintText']");
        //public static final By AS_ATTR_DROP_TARGET = By.xpath("//div[starts-with(@id, 'grid')]//div[@class='x-grid-item-container']");
        public static final By AS_ATTR_DROP_TARGET = By.xpath("//div[@id='summary']/div/div//div[@data-ref='body']/div");
        public static final By AS_ATTR_SAVE = Locator.spanText("Save");
        // Equipment
        public static final By EQUIPMENT = By.linkText("Equipment");
        public static final By AS_ADD_ASSOCIATION = spanText("+ Add Associations");
        public static final By AS_ASS_EQU_TYPE = By.xpath("//label/span[text()[contains(.,'Equipment Type')]]/../..//input[@type='text']");
        public static final By AS_ASS_BASELINE = By.xpath("//label/span[text()[contains(.,'Baseline')]]/../..//input[@type='text']");
        public static final By AS_ASS_EQU_ASS_TYPE = By.xpath("//label/span[text()[contains(.,'Association Type')]]/../..//input[@type='text']");
        public static final By AS_ASS_ITEM = divText("qa1");
        public static final By AS_ASSOCIATION_ADD = spanText("Add");
        // Measures here
        public static final By MEASURES = By.linkText("Measures");
        public static final By AS_M_CATEGORY = By.xpath("//label/span[text()[contains(.,'Category')]]/../..//input[@type='text']");
        public static final By AS_M_TYPE = By.xpath("//label/span[text()[contains(.,'Type')]]/../..//input[@type='text']");
        public static final By AS_M_SUB_TYPE = By.xpath("//label/span[text()[contains(.,'Sub-Type')]]/../..//input[@type='text']");
        public static final By AS_M_SAVE = spanText("Save");
        // Measure Configuration
        public static final By MEASURE_CONF = By.linkText("Measure Configuration");
        public static final By AS_MC_FORMULA = By.xpath("//label/span[text()[contains(.,'Formula')]]/../..//input[@type='text']");
        public static final By AS_MC_SAVE = spanText("Save");
        // Measure Identification
        public static final By MEASURE_IDENT = By.linkText("Measure Identification");
        public static final By ASS_MI_ADD_IDENTIFICATION = spanText("+ Add Identification");
        public static final By ASS_MI_SELECT_BASELINE = By.xpath("//label/span[text()[contains(.,'Select Baseline')]]/../..//input[@type='text']");
        public static final By ASS_MI_SELECT_MEASURE = By.xpath("//label/span[text()[contains(.,'Select Measure')]]/../..//input[@type='text']");
        public static final By ASS_MI_SAVE = By.xpath("//div[@id='measueIdentificationPopup']//span[text()[.='Save']]");
        // Formulas
        public static final By ADD_NEW_FORMULA = By.xpath("//input[@value='Add New Formula']");
        public static final By AS_FORM_ACTIVE_INDICATOR = By.xpath("//input[@name='formulaActiveFlag-inputEl']");
        public static final By AS_FORMULA_NAME = By.xpath("//input[@name='formulaName']");
        public static final By AS_FORMULA_FIELD = By.xpath("//textarea[@name='formulaField-inputEl']");
        public static final By AS_FORMULA_RESULT = By.xpath("//textarea[@name='formulaResultField-inputEl']");
        public static final By AS_CALCULATE_BTN = By.linkText("Calculate");
        // Add New Token
        public static final By AS_ADD_TOKEN = By.linkText("+ Add Token");
        public static final By AS_TOKEN_NAME = By.xpath("//input[@name='name']");
        public static final By AS_TOKEN_TYPE = By.xpath("//input[@name='tokenType']");
        public static final By AS_ATTRIBUTE_NAME = By.xpath("//input[@name='attributeName']");
        public static final By AS_EXPRESSION_NAME = By.xpath("//input[@name='expression']");
        public static final By AS_SAVE_TOKEN = By.xpath("//div[starts-with(@id, 'toolbar')]//span[text()[.='Save']]");
        // Add Variable
        public static final By AS_ADD_NEW_VAR = By.linkText("+Add");
        public static final By AS_VARIABLE_NAME = By.xpath("//*[@name='name']");
        public static final By AS_TEST_VALUE = By.xpath("//*[@name='testValue']");
        public static final By AS_DEFAULT_TEXT = By.xpath("//*[@name='defaultValue']");
        public static final By AS_TOKEN = By.xpath("//*[@name='formulas_token_combo']");
        public static final By AS_DATA_TYPE = By.xpath("//*[@name='tokenDataType']");
        public static final By AS_SAVE_FORMULA = By.xpath("//input[@value='Save']");
    }

    public interface FormAssignment{
        public static final By TYPE = By.xpath("//input[@name='type']");
        public static final By AUTO = By.xpath("//input[@name='autoAssignmentFormula']");
        public static final By MANUAL = By.xpath("//input[@name='manualAssignmentListFormula']");
        public static final By APPLY_BTN = By.xpath("//*[@id='form_assignment_config_form_save']");
    }

    public interface Dashboard {
        // Electric
        public static final By DB_ELECTRIC = By.xpath("//*[@value='Electric']");
        public static final By DB_GAS = By.xpath("//*[@value='Gas']");
        public static final By DB_RENEWABLE = By.xpath("//*[@value='Renewable']");
        public static final By DB_WATER = By.xpath("//*[@value='Water']");
        public static final By DB_SPEND_BY_CHART = By.xpath("//div/label[text()[contains(.,'Spend By')]]");
        public static final By DB_SPENDBY_CAT = By.partialLinkText("Category");
        public static final By DB_SPENDBY_SEC = By.partialLinkText("Sector");
        public static final By DB_CLOSE_POP_UP = By.xpath("//div/img[@class='x-tool-img x-tool-close']");
        public static final By DB_YTD_BUDGET = By.xpath("//div/label[text()[contains(.,'YTD Budget')]]");
        public static final By DB_ELECTRIC_KWH = By.xpath("//div/label[text()[contains(.,'Cost/kWh Installed')]]");
        public static final By DB_ENERGY_GRAPH = By.partialLinkText("Energy");
        public static final By DB_DEMAND_GRAPH = By.partialLinkText("Demand");

        // Gas
        public static final By DB_GAS_THERM = By.xpath("//div/label[text()[contains(.,'Cost/Therm Installed')]]");

        // Renewable
        public static final By DB_RENEWABLE_THERM = By.xpath("//div/label[text()[contains(.,'Cost/kW Installed')]]");

        // Water
        public static final By DB_WATER_THERM = By.xpath("//div/label[text()[contains(.,'Cost/Gal Installed')]]");

        // General
        public static final By DB_SORT_MAX = By.partialLinkText("Max");
        public static final By DB_SORT_1Y = By.partialLinkText("1Y");
        public static final By DB_SORT_2Y = By.partialLinkText("2Y");
        public static final By DB_SORT_3Y = By.partialLinkText("3Y");
        public static final By DB_SORT_YTD = By.partialLinkText("YTD");
        public static final By DB_SHOW_5 = By.xpath("//a[text()[contains(.,'5')] and @class='count']");
        public static final By DB_SHOW_10 = By.xpath("//a[text()[contains(.,'10')] and @class='count']");
        public static final By DB_SHOW_25 = By.xpath("//a[text()[contains(.,'25')] and @class='count']");

        public static final By DB_TRANSPORTATION_TAB = By.partialLinkText("Transportation");
        public static final By DB_ST_PUBLIC_AUTHORITY_TAB = By.partialLinkText("Street Light Public Authority");
        public static final By DB_RESIDENTIAL_TAB = By.linkText("Residential");
        public static final By DB_PUBLIC_AUTHORITY_TAB = By.partialLinkText("Public Authority");
        public static final By DB_NON_RESIDENTIAL_TAB = By.linkText("Non-Residential");
        public static final By DB_MINING_TAB = By.partialLinkText("Mining");
        public static final By DB_Irrigation_TAB = By.partialLinkText("Irrigation");
        public static final By DB_Industrial_TAB = By.partialLinkText("Industrial");
        public static final By DB_Commercial_TAB = By.partialLinkText("Commercial");
        public static final By DB_Agriculture_TAB = By.partialLinkText("Agriculture");
        public static final By DB_ALL_TAB = By.partialLinkText("ALL");


    }

    public interface CustomerAppointment {
        public static final By CUSTOMER_APPOINTMENT_LINK = Locator.spanId("Appointments-btnIconEl");

        // Sort Appointments
        public static final By APPOINTMENT_SORT_BY_NAME = By.xpath("//div/div/span[text()[.='Name']]");
        public static final By APPOINTMENT_SORT_BY_GROUP = By.xpath("//div/div/span[text()[.='Group']]");
        public static final By APPOINTMENT_SORT_BY_TYPE = By.xpath("//div/div/span[text()[.='Type']]");
        public static final By APPOINTMENT_SORT_BY_PHONE = By.xpath("//div/div/span[text()[.='Phone']]");
        public static final By APPOINTMENT_SORT_BY_EMAIL = By.xpath("//div/div/span[text()[.='Email']]");
        public static final By APPOINTMENT_SORT_BY_ADDRESS = By.xpath("//div/div/span[text()[.='Address']]");
        public static final By APPOINTMENT_SORT_BY_A_TIME = By.xpath("//div/div/span[text()[.='Assigned Time']]");
        public static final By APPOINTMENT_SORT_BY_P_TIME = By.xpath("//div/div/span[text()[.='Preferred Time']]");
        public static final By APPOINTMENT_SORT_BY_ALT_TIME = By.xpath("//div/div/span[text()[.='Alternative Time 1']]");
        public static final By APPOINTMENT_SORT_BY_ALT_TIME_2 = By.xpath("//div/div/span[text()[.='Alternative Time 2']]");
        public static final By APPOINTMENT_SORT_BY_STATUS = By.xpath("//div/div/span[text()[.='Status']]");
        public static final By APPOINTMENT_SORT_BY_NOTES = By.xpath("//div/div/span[text()[.='Notes']]");


    }
    
    public interface PICustomerRegistrationAndLogin {
    	public static final By L_ACCOUNT_NUMBER = inputWithName("accountNumber");
    	public static final By L_SERVICE_PROVIDER = By.cssSelector("[name='businessUnitId']");
    	public static final By L_LAST_NAME = inputWithName("lastName");
    	public static final By L_ZIP_CODE = inputWithName("zipCode");
    	public static final By L_EMAIL = inputWithName("emailAddress");
    	public static final By L_PASSWORD = inputWithName("encryptedPassword");
        public static final By L_CONFIRM_PASSWORD = inputWithName("password_confirm");
        public static final By L_SIGN_UP_BTN = Locator.traksmartButtonAnchorStyle("Sign Up");
        public static final By L_LOGIN_USERNAME_EMAIL = inputWithName("j_username");
        public static final By L_LOGIN_USER_PWD = inputWithName("j_password");
    }

    public interface ProjectDSMInput{
        public static final By PDI_NAME = inputWithName("dsmInputName");
        public static final By PDI_DESCRIPTION = textareaWithName("description");
        public static final By PDI_SERVICE_DRPBX = inputWithName("serviceTypeId");
        public static final String PDI_ELECTRIC = "Electric";
        public static final By PDI_YEAR_DRPBX = inputWithName("year");
        public static final String PDI_YEAR = "2014";
        public static final By PDI_OTHER_DRPBX = inputWithName("dsmSystemInputId");
    }

    public interface Users {
        public static final By US_ADD_NEW = inputWithValue("+ Add New User");
        public static final By US_USER_ID = anchorTextWithinDivId("userName_div", "User ID");
    }

}
