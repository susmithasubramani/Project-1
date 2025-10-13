package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage {
    @FindBy(id = "name")
    private WebElement nameInput; // if exists

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//button[contains(.,'Sign up') or contains(.,'Signup') or contains(.,'Register')]")
    private WebElement signUpBtn;

    @FindBy(css = ".error")
    private WebElement genericError;

    public SignUpPage(WebDriver driver) { super(driver); }

    public void open(String baseUrl) { driver.get(baseUrl + "/register"); }

    public void signUp(String email, String password, String confirmPassword) {
        try { if (emailInput != null) { emailInput.clear(); emailInput.sendKeys(email); } } catch (Exception ignored) {}
        try { if (passwordInput != null) { passwordInput.clear(); passwordInput.sendKeys(password); } } catch (Exception ignored) {}
        try { if (confirmPasswordInput != null) { confirmPasswordInput.clear(); confirmPasswordInput.sendKeys(confirmPassword); } } catch (Exception ignored) {}
        signUpBtn.click();
    }

    public String getGenericErrorText() {
        try { return genericError.getText(); } catch (Exception e) { return ""; }
    }
}
