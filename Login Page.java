package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[contains(.,'Login')]")
    private WebElement loginBtn;

    @FindBy(css = ".error") // fallback
    private WebElement genericError;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open(String baseUrl) {
        driver.get(baseUrl + "/login");
    }

    public void login(String email, String password) {
        emailInput.clear();
        emailInput.sendKeys(email);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginBtn.click();
    }

    public String getGenericErrorText() {
        try { return genericError.getText(); } catch (Exception e) { return ""; }
    }

    public boolean isPasswordMasked() {
        try {
            String type = passwordInput.getAttribute("type");
            return type != null && (type.equals("password") || type.equals("masked"));
        } catch (Exception e) { return false; }
    }
}
