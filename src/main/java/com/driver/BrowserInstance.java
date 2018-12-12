package com.driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BrowserInstance {

    private static final String USERNAME = "nvkpavankumar2";
    private static final String AUTOMATE_KEY = "AET4cyqprWngmzYvyK8m";
    private static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";
    protected static WebDriver driver;
    private static ChromeDriverService service;
    private static ChromeOptions option;

    protected static void initiateDriver(String browserName) throws IOException {
        if (browserName.equalsIgnoreCase("chrome")) {
            service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File("DriverJars/chromedriver.exe"))
                    .usingAnyFreePort()
                    .build();
            service.start();
            option = new ChromeOptions();
            option.addArguments("--incognito");
            driver = new RemoteWebDriver(service.getUrl(), option);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "DriverJars/geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("remote")) {
            DesiredCapabilities capability = new DesiredCapabilities();
            capability.setPlatform(Platform.WINDOWS);
            capability.setBrowserName("chrome");
            capability.setVersion("66");
            capability.setCapability("browserstack.debug", "true");
            URL browserStackUrl = new URL(URL);
            driver = new RemoteWebDriver(browserStackUrl, capability);

        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    protected void openUrl(String url) {
        driver.get(url);
    }
}
