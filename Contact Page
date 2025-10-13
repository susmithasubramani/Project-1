package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import org.openqa.selenium.By;

public class ContactPage extends BasePage {
    @FindBy(xpath = "//a[contains(.,'Add Contact') or contains(.,'Add')]")
    private WebElement addContactBtn;

    // Modal/form fields (IDs/classes may vary; adjust selectors as necessary)
    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "phone")
    private WebElement phone;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(xpath = "//button[contains(.,'Submit') or contains(.,'Add')]")
    private WebElement submitBtn;

    @FindBy(css = ".contact-row")
    private List<WebElement> contactRows;

    public ContactPage(WebDriver driver) { super(driver); }

    public void openAddContact() {
        addContactBtn.click();
    }

    public void fillAndSubmitContact(String fName, String lName, String phoneNum, String emailAddr) {
        firstName.clear(); firstName.sendKeys(fName);
        lastName.clear(); lastName.sendKeys(lName);
        phone.clear(); phone.sendKeys(phoneNum);
        email.clear(); email.sendKeys(emailAddr);
        submitBtn.click();
    }

    public boolean contactExists(String fName, String lName) {
        try {
            for (WebElement row : contactRows) {
                if (row.getText().contains(fName) && row.getText().contains(lName)) return true;
            }
        } catch (Exception ignored) {}
        return false;
    }

    public void deleteContact(String fName, String lName) {
        for (WebElement row : contactRows) {
            if (row.getText().contains(fName) && row.getText().contains(lName)) {
                // Try to find delete button inside row
                try {
                    WebElement del = row.findElement(By.xpath(".//button[contains(.,'Delete') or contains(.,'delete') or contains(@class,'delete')]"));
                    del.click();
                    return;
                } catch (Exception e) { /* fallback */ }
            }
        }
    }

    public void editContact(String fName, String lName, String newEmail) {
        for (WebElement row : contactRows) {
            if (row.getText().contains(fName) && row.getText().contains(lName)) {
                try {
                    WebElement edit = row.findElement(By.xpath(".//button[contains(.,'Edit') or contains(@class,'edit')]"));
                    edit.click();
                    WebElement emailField = driver.findElement(By.id("email")); // assuming modal reuse
                    emailField.clear();
                    emailField.sendKeys(newEmail);
                    driver.findElement(By.xpath("//button[contains(.,'Save') or contains(.,'Update')]")).click();
                    return;
                } catch (Exception e) {}
            }
        }
    }
}
