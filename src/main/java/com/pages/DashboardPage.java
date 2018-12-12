package com.pages;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

    @FindBy(xpath = "//*[@title='Log out']")
    private WebElement logoutIcon;

    @FindBy(className = "cms-login-status__profile-link")
    private WebElement adminProfileIcon;

    public void clickLogout(ExtentTest logger) {
        logoutIcon.click();
        logger.info("Logout Icon clicked");
    }

    public boolean logoutIconStatus(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(logoutIcon));
        return logoutIcon.isDisplayed();
    }

    public void WaitForLogoutIconToDisplay(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(logoutIcon));
    }
}
