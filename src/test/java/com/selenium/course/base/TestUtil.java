package com.selenium.course.base;

import com.selenium.course.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestUtil {
    public WebDriver driver;
    private String url;
    private int implicitWait;
    private String chromeBrowser;


    @BeforeSuite
    public void readConfigProperties() {
        try (FileInputStream configFile = new FileInputStream("src/test/resources/config.properties")) {
            Properties config = new Properties();
            config.load(configFile);
            url = config.getProperty("urlAddress");
            implicitWait = Integer.parseInt(config.getProperty("implicitWait"));
            chromeBrowser = config.getProperty("browser");

            // browser to be taken from property file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeTest
    public void setUp() {
        setupBrowserDriver();
        loadUrl();
    }

    private void loadUrl() {
        driver.get(url);
    }

    private void setupBrowserDriver() {
        driver = DriverFactory.getFirefoxDriver(implicitWait);
        // chrome implementation
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
