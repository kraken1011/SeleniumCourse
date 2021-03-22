package com.selenium.course.tests;

import com.opencsv.exceptions.CsvException;
import com.selenium.course.base.TestUtil;
import com.selenium.course.utils.CsvReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends TestUtil {

    // private WebDriver driver;

/*    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }*/

    @DataProvider(name = "login-data")
    public static Object[][] dataProviderHardcodedData() {
        return new Object[][]{
                {"user1", "pass1"},
                {"user2", "pass2"}
        };
    }

    @DataProvider(name = "login-data-file")
    public static Object[][] dataProviderFromCsvFile() throws IOException, CsvException {
        return CsvReader.readCsvFile("src/test/resources/login-data.csv");
    }

    @Test(dataProvider = "login-data-file")
    public void executeSimpleTest(String userName, String password) throws InterruptedException {
        //  driver.get("https://www.saucedemo.com/");

        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.clear();
        userNameInput.sendKeys(userName);

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.clear();
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.cssSelector("[value=LOGIN]"));
        loginButton.click();
    }


  /*  @AfterTest
    public void tearDown() {
        driver.quit();
    }*/
}
