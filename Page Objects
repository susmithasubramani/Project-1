package com.example.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        PageFactory.initElements(driver, this);
    }

    protected void waitForUrlContains(String text) {
        wait.until((ExpectedCondition<Boolean>) d -> d.getCurrentUrl().contains(text));
    }
}
