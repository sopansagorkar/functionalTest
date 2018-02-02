package com.nexant.dsm.libraries.contactmanagement.process;

/**
 * Created with IntelliJ IDEA.
 * User: vkamenev
 * Date: 8/27/13
 * Time: 1:46 PM
 * To change this template use File | Settings | File Templates.
 */

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;

@Test
public class TestAddContactManagementNote {

    private static final Log log = LogFactory.getLog(TestAddContactManagementNote.class);

    private WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = Driver.get();
    }

    @Test public void testAddNote() {

        SeleniumUtils.waitForElement(driver, Locator.ContactManagement.L_ADD_NOTE_BTN);
        // should be on the contact detail page
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Contact Detail");

        SeleniumUtils.click(driver, Locator.ContactManagement.L_ADD_NOTE_BTN);

        // check the popup comes up
        By popup = Locator.divText("Add Note");
        SeleniumUtils.assertElementExistsWithWait(driver, popup);

        // fill in form
        String subject = "subject " + System.currentTimeMillis();
        String note = "note " + System.currentTimeMillis();

        SeleniumUtils.setText(driver, Locator.Note.L_ADD_NOTE_CONTACTED_BY, Locator.Text.FIRST_NAME);
        SeleniumUtils.setText(driver, Locator.Note.L_ADD_NOTE_SUBJECT, subject);
        SeleniumUtils.setText(driver, Locator.Note.L_ADD_NOTE, note);

        // just test invalid date
        SeleniumUtils.clear(driver, Locator.Note.L_ADD_DATE);
        SeleniumUtils.click(driver, Locator.Note.L_ADD_NOTE_SAVE_BTN);

        // error
   /* 	Error_Label.Check_it(driver, Locator.Text.ERROR_1);
    	Red_Border.Check_it(driver, "contactDateString", "aria-invalid", "true");
    	Red_Border.Check_it(driver, "noteTypeId", "aria-invalid", "true"); */

        // fix error and continue
        SeleniumUtils.sleepMillis(100);
        SelectUtils.clickItemName(driver, Locator.Note.L_ADD_NOTE_TYPE_OF_CONTACT, "Note");
        SeleniumUtils.sleepMillis(100);
        SeleniumUtils.setText(driver, Locator.Note.L_ADD_DATE, Locator.Text.DATE_FROM);


        // save
        SeleniumUtils.sleepMillis(300);
        SeleniumUtils.click(driver, Locator.Note.L_ADD_NOTE_SAVE_BTN);

        // popup should go away
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.waitForInVisible(driver, popup);

        // check the saved note is there
        WebElement e = SeleniumUtils.waitForElement(driver, By.xpath("//div[@class='notes-detail']"));
        log.info("found " + e.getTagName() + ": " + e.getText());

       // By savedNote = By.xpath("(//div[@class='notes-detail'])[text()[.='" + note + "']]");
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divTextContains(note));
    }

    @Test public void testAddNoteWithFollowUp() {

        SeleniumUtils.waitForElement(driver, Locator.ContactManagement.L_ADD_NOTE_BTN);
        SeleniumUtils.assertPageTitle(driver, "DSM Central: Contact Detail");

        SeleniumUtils.click(driver, Locator.ContactManagement.L_ADD_NOTE_BTN);

        // check the popup comes up
        By popup = Locator.divText("Add Note");
        SeleniumUtils.assertElementExistsWithWait(driver, popup);

        String subject = "subject follow up " + System.currentTimeMillis();
        String note = "note follow up " + System.currentTimeMillis();

        // fill in form
        SeleniumUtils.setText(driver, Locator.Note.L_ADD_NOTE_CONTACTED_BY, Locator.Text.FIRST_NAME);
        SeleniumUtils.setText(driver, Locator.Note.L_ADD_NOTE_SUBJECT, subject);
        SeleniumUtils.setText(driver, Locator.Note.L_ADD_NOTE, note);

        SeleniumUtils.click(driver, Locator.Note.L_ADD_NOTE_FOLLOW_UP);
        SeleniumUtils.setText(driver, Locator.Note.L_ADD_NOTE_EMAIL_TEXT, Locator.Text.DESCRIPTION);

        // check error checking
        SeleniumUtils.clear(driver, Locator.Note.L_ADD_DATE);
        SeleniumUtils.click(driver, Locator.Note.L_ADD_NOTE_SAVE_BTN);

   /* 	Error_Label.Check_it(driver, "Please fix the validation errors!");
        Red_Border.Check_it(driver, "contactDateString", "aria-invalid", "true");    
        Red_Border.Check_it(driver, "noteTypeId", "aria-invalid", "true");    
        Red_Border.Check_it(driver, "followUpEmailAddress", "aria-invalid", "true");    
        Red_Border.Check_it(driver, "followUpDateString", "aria-invalid", "true"); */

        // finish filling it out
        SeleniumUtils.setText(driver, Locator.Note.L_ADD_DATE, Locator.Text.DATE_FROM);

        SeleniumUtils.sleepMillis(100);
        SelectUtils.clickItemName(driver, Locator.Note.L_ADD_NOTE_TYPE_OF_CONTACT, "Note");
        SeleniumUtils.sleepMillis(100);
        SelectUtils.selectDateInTheFuture(driver, By.xpath("//div[@id='followUpDateString-trigger-picker']"), 4, 5);
        SeleniumUtils.sleepMillis(100);

        SeleniumUtils.setText(driver, Locator.Note.L_ADD_NOTE_EMAIL, Locator.Text.EMAIL);

        SeleniumUtils.sleepMillis(300);
        // save
        SeleniumUtils.click(driver, Locator.Note.L_ADD_NOTE_SAVE_BTN);

        // popup should go away
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.waitForInVisible(driver, popup);

        // check the saved note is there
        WebElement e = SeleniumUtils.waitForElement(driver, By.xpath("//div[@class='notes-detail']"));
        log.info("found " + e.getTagName() + ": " + e.getText());

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divTextContains(note));

        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Subject']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Note Type']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Contact By']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Contact Date']]"));

    }

}
