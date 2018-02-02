package com.nexant.dsm.libraries.partner.process;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.common.pageObjects.dropdownlist.SelectUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = {"Partner Note"})
public class TestAddNewNote {
    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        driver = Driver.get();
    }

    @Test(description = "Open Add New Note")
    public void testAddPartnerNote() {

        By addNoteButton = By.cssSelector("[value= '+ Add Note']");
        SeleniumUtils.waitForClickable(driver, addNoteButton);
        SeleniumUtils.click(driver, addNoteButton, 30L);

        // check we have the add note popup
        By popup = Locator.divText("Add Note");
        SeleniumUtils.assertElementExistsWithWait(driver, popup);

        // fill in the name
        SeleniumUtils.setText(driver, Locator.Partner.contactedBy, Locator.Text.NAME);

        // full in the subject
        SeleniumUtils.setText(driver, Locator.Partner.subject, Locator.Text.STRING);

        // fill in the description
        String desc = "description " + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Partner.notes, desc);

        // fill in the contact type
        SelectUtils.clickItemName(driver, Locator.Partner.notetype, "Note");

        // save
        SeleniumUtils.click(driver, Locator.Partner.saveNote, 30L);

        // check the popup goes away
        SeleniumUtils.waitForInVisible(driver, popup);

        // check the note is there
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("(//div[@id='library-note-gridContent']//div[@class='notes-detail'])[text()[contains(.,'" + desc + "')]]"));
    }


    @Test(description = "Open Add New Note")
    public void testAddPartnerNoteWithFollowUp() {
        By addNoteButton = By.cssSelector("[value= '+ Add Note']");
        SeleniumUtils.waitForClickable(driver, addNoteButton);
        SeleniumUtils.click(driver, addNoteButton);

        // check we have the add note popup
        By popup = Locator.divText("Add Note");
        SeleniumUtils.assertElementExistsWithWait(driver, popup);

        SeleniumUtils.setText(driver, Locator.Partner.contactedBy, Locator.Text.NAME);

        SeleniumUtils.setText(driver, Locator.Partner.subject, Locator.Text.STRING);

        String desc = "description with follow up " + System.currentTimeMillis();
        SeleniumUtils.setText(driver, Locator.Partner.notes, desc);

        SelectUtils.clickItemName(driver, Locator.Partner.notetype, "Note");

        SeleniumUtils.click(driver, Locator.Partner.followUpRequired);

        SeleniumUtils.setText(driver, Locator.Partner.followUpEmailAddress, Locator.Text.EMAIL);

        SeleniumUtils.setText(driver, Locator.Partner.followUpEmailText, Locator.Text.DESCRIPTION);

        //    Select_Calendar.regular(driver, By.xpath("//div/table[11]/tbody/tr/td[2]/table/tbody/tr/td[2]/div"), By.xpath("(//div/table/tbody/tr/td/a/em/span)[last()]"));
        SelectUtils.selectDateInTheFuture(driver, By.xpath("//div[@id='followUpDateString-trigger-picker']"), 4, 5);
        //save
        SeleniumUtils.click(driver, Locator.Partner.saveNote);

        // check the popup goes away
        SeleniumUtils.waitForInVisible(driver, popup);

        // check the note is there   [contains(.,'Add new Measure Category')]
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("(//div[@id='library-note-gridContent']//div[@class='notes-detail'])[text()[contains(.,'" + desc + "')]]"));

        // Sort Notes
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//a/span[text()[.='Note Type']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Note Type']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//a/span[text()[.='Contact Date']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Contact Date']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//a/span[text()[.='Contact By']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Contact By']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//a/span[text()[.='Program']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Program']]"));
        SeleniumUtils.assertElementExistsWithWait(driver, By.xpath("//a/span[text()[.='Project']]"));
        SeleniumUtils.click(driver, By.xpath("//a/span[text()[.='Project']]"));
    }

}
