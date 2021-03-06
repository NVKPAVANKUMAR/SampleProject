package com.tests;

import com.driver.DriverInstance;
import com.pages.DashboardPage;
import com.pages.HomePage;
import com.utilities.ConfigParser;
import com.utilities.ExcelDataParser;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.utilities.ReportGenerator.*;
import static com.utilities.ScreenshotUtility.takeScreenshot;

public class TestRunner extends DriverInstance {
    private HomePage homePage;
    private DashboardPage dashboardPage;
    private String successStatus = "PASS", failureMessage = "FAIL";

    @DataProvider(name = "loginCredentials")
    public static Object[][] provideLoginCredentials() {
        return new Object[][]{
                {"ADMIN", "124556"},
                {"wefrgefdr", "pass"},
                {"admin", "password"},
                {"12345", "12345"}
        };
    }

    @BeforeSuite
    public void setUp() throws IOException {
        startReport();
        initiateDriver(ConfigParser.fetchProperity("browser").toString());
        openUrl(ConfigParser.fetchProperity("testurl").toString());
        homePage = PageFactory.initElements(driver, HomePage.class);
        dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
    }

    @Test(priority = 2, dataProvider = "Excel", dataProviderClass = ExcelDataParser.class)
    public void LoginPageTest(String usn, String pass) throws IOException {
        startTest(new Object() {
        }.getClass().getEnclosingMethod().getName());
        homePage.enterUsername(usn, logger);
        homePage.enterPassword(pass, logger);
        homePage.clickLoginButton(logger);
        try {
            if (dashboardPage.logoutIconStatus(driver)) {
                takeScreenshot(driver, "LoginSuccess");
                logger.pass("Login Success");
                //  writeToExcel(1,3,"dataSheets/TestSheet.xlsx","credentials",successStatus);
            }
        } catch (Exception e) {
            takeScreenshot(driver, "LoginFailure");
            logger.fail("Login Failure");
            //  writeToExcel(1,3,"dataSheets/TestSheet.xlsx","credentials",failureMessage);

        }
    }

    //@Test(dependsOnMethods = "LoginPageTest")
    public void DashboardPageTest() {
        startTest(new Object() {
        }.getClass().getEnclosingMethod().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (dashboardPage.logoutIconStatus(driver)) {
            dashboardPage.clickLogout(logger);
        }
    }

    @Test(priority = 1, dataProvider = "loginCredentials")
    public void logInNlogOutTest(String usn, String pass) throws IOException, InterruptedException {
        startTest(new Object() {
        }.getClass().getEnclosingMethod().getName());
        homePage.enterUsername(usn, logger);
        homePage.enterPassword(pass, logger);
        homePage.clickLoginButton(logger);
        try {
            if (dashboardPage.logoutIconStatus(driver)) {
                takeScreenshot(driver, "LoginSuccess");
                logger.pass("Login Success");
                Thread.sleep(2000);
                if (dashboardPage.logoutIconStatus(driver)) {
                    dashboardPage.clickLogout(logger);
                }
            }
        } catch (Exception e) {
            takeScreenshot(driver, "LoginFailure");
            logger.fail("Login Failure");
        }
    }

    @AfterClass
    public void tearDown() {
        endReport();
        driver.quit();
    }
}
