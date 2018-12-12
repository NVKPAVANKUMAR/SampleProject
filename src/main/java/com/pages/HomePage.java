package com.pages;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    @FindBy(how = How.ID, using = "MemberLoginForm_LoginForm_Email")
    private WebElement userNamefield;

    @FindBy(xpath = "//*[@name='Password']")
    private WebElement passwordfield;

    @FindBy(id = "MemberLoginForm_LoginForm_action_doLogin")
    private WebElement loginbutton;

    @FindBy(id = "MemberLoginForm_LoginForm_error")
    private WebElement loginErrorMsg;

    public void enterUsername(String username, ExtentTest logger) {
        userNamefield.clear();
        userNamefield.sendKeys(username);
        logger.info("username entered");
    }

    public void enterPassword(String password, ExtentTest logger) {
        passwordfield.clear();
        passwordfield.sendKeys(password);
        logger.info("password entered");
    }

    public void clickLoginButton(ExtentTest logger) {
        loginbutton.click();
        logger.info("Login Button Clicked");
    }

    public boolean errorMsgPresent(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(loginErrorMsg));
        return loginErrorMsg.isDisplayed();
    }


}
