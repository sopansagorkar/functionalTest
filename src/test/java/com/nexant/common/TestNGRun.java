package com.nexant.common;

import com.nexant.dsm.libraries.contactmanagement.process.TestAddNewContactManagementContact;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

/**
 * Created by VKamenev on 12/4/13.
 */
public class TestNGRun {

    public void testTestNGProgramatically() {
        TestListenerAdapter tla = new TestListenerAdapter();
        TestNG testng = new TestNG();
        testng.setTestClasses(new Class[]{com.nexant.dsm.Suit.class, TestAddNewContactManagementContact.class});
        testng.addListener(tla);
        testng.run();
    }
}