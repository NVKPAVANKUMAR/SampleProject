package com.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;


public class ScreenshotUtility {

    public static void takeScreenshot(WebDriver driver, String scrName) throws IOException {
        String destFilePath = "target/screenshot-" + scrName + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(destFilePath));
    }
}
