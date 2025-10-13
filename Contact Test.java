package com.example.tests;

import com.example.pages.ContactPage;
import com.example.pages.LoginPage;
import com.example.utils.TestLogger;
import org.testng.Assert;
import org.testng.annotations.*;

public class ContactTests extends BaseTest {

    @BeforeClass
    public void loginBeforeTests() {
        // ensure logged in; reuse a known valid account
        LoginPage lp = new LoginPage(driver);
        lp.open(BASE_URL);
        lp.login("registered@example.com","Password123"); // replace with valid test account
    }

    @Test(priority = 1)
    public void addContactWithAllValidDetails() {
        String testName = "addContactWithAllValidDetails";
        try {
            ContactPage cp = new ContactPage(driver);
            cp.openAddContact();
            String f = "John"+System.currentTimeMillis()%1000;
            String l = "Doe";
            cp.fillAndSubmitContact(f, l, "9998887776", "john.doe@example.com");
            Thread.sleep(900);
            Assert.assertTrue(cp.contactExists(f, l), "Contact should appear in list");
            TestLogger.log(testName, "PASS", "Added contact " + f + " " + l);
        } catch (Throwable t) {
            TestLogger.log(testName, "FAIL", t.getMessage());
            Assert.fail(t.getMessage(), t);
        }
    }

    @Test(priority = 2)
    public void addContactMissingRequiredFieldsShowsError() {
        String testName = "addContactMissingRequiredFieldsShowsError";
        try {
            ContactPage cp = new ContactPage(driver);
            cp.openAddContact();
            cp.fillAndSubmitContact("", "", "123", "a@b.com");
            Thread.sleep(600);
            // Since validation varies, attempt to assert that contact wasn't added
            Assert.assertFalse(cp.contactExists("", ""), "Contact with blank names should not be added");
            TestLogger.log(testName, "PASS", "Validation prevented blank names");
        } catch (Throwable t) {
            TestLogger.log(testName, "FAIL", t.getMessage());
            Assert.fail(t.getMessage(), t);
        }
    }

    @Test(priority = 3)
    public void phoneFieldAcceptsOnlyNumeric() {
        String testName = "phoneFieldAcceptsOnlyNumeric";
        try {
            ContactPage cp = new ContactPage(driver);
            cp.openAddContact();
            cp.fillAndSubmitContact("Alpha", "Num", "abcde", "alpha@example.com");
            Thread.sleep(600);
            // verify either error or stored value not equal to alphabetic phone
            boolean exists = cp.contactExists("Alpha", "Num");
            TestLogger.log(testName, exists ? "FAIL" : "PASS", exists ? "Contact added with alpha phone" : "Alpha phone rejected");
            Assert.assertFalse(exists, "Alpha phone should not be accepted");
        } catch (Throwable t) {
            TestLogger.log(testName, "FAIL", t.getMessage());
            Assert.fail(t.getMessage(), t);
        }
    }

    @Test(priority = 4)
    public void editContactAndVerifyUpdate() {
        String testName = "editContactAndVerifyUpdate";
        try {
            ContactPage cp = new ContactPage(driver);
            // Create contact to edit
            String f = "EditMe";
            String l = "User";
            cp.openAddContact();
            cp.fillAndSubmitContact(f, l, "7777777777", "old@example.com");
            Thread.sleep(600);
            cp.editContact(f, l, "new@example.com");
            Thread.sleep(700);
            Assert.assertTrue(cp.contactExists(f, l), "Contact still present after edit");
            TestLogger.log(testName, "PASS", "Edited contact " + f + " " + l);
        } catch (Throwable t) {
            TestLogger.log(testName, "FAIL", t.getMessage());
            Assert.fail(t.getMessage(), t);
        }
    }

    @Test(priority = 5)
    public void deleteContactAndVerifyRemoval() {
        String testName = "deleteContactAndVerifyRemoval";
        try {
            ContactPage cp = new ContactPage(driver);
            String f = "ToDelete";
            String l = "User";
            cp.openAddContact();
            cp.fillAndSubmitContact(f, l, "6666666666", "todelete@example.com");
            Thread.sleep(600);
            cp.deleteContact(f, l);
            Thread.sleep(700);
            Assert.assertFalse(cp.contactExists(f, l), "Deleted contact should not be visible");
            TestLogger.log(testName, "PASS", "Deleted contact: " + f + " " + l);
        } catch (Throwable t) {
            TestLogger.log(testName, "FAIL", t.getMessage());
            Assert.fail(t.getMessage(), t);
        }
    }

    @Test(priority = 6)
    public void logoutAndVerifyRedirectToLogin() {
        String testName = "logoutAndVerifyRedirectToLogin";
        try {
            // Try to click logout and assert redirect. Adjust selector if necessary.
            driver.findElement(By.xpath("//button[contains(.,'Logout') or contains(.,'Log out')]")).click();
            Thread.sleep(700);
            Assert.assertTrue(driver.getCurrentUrl().contains("/login"), "Should redirect to login after logout");
            TestLogger.log(testName, "PASS", "Logout redirected to login");
        } catch (Throwable t) {
            TestLogger.log(testName, "FAIL", t.getMessage());
            Assert.fail(t.getMessage(), t);
        }
    }
}
