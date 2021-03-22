package com.selenium.course.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;


public class SimpleTest {

    private WebDriver driver;

    @BeforeTest
    public void setUp() {

        //  System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        //  driver = new ChromeDriver();
        //   System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        //   driver = new FirefoxDriver();

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void executeSimpleTest() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");

        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.cssSelector("[value=LOGIN]"));
        loginButton.click();

        WebElement selectElement = driver.findElement(By.className("product_sort_container"));
        Select list = new Select(selectElement);
        list.selectByValue("lohi");
        list.selectByVisibleText("Name (Z to A)");

        WebElement addToCart = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']//ancestor::div[@class='inventory_item']//button"));
        addToCart.click();

        WebElement badge = driver.findElement(By.cssSelector(".shopping_cart_badge"));

        assertEquals(badge.getText(), "1");

      //  Thread.sleep(3000);
    }


    @AfterTest
    public void tearDown() {
        //   driver.close(); // затваря браузъра
        driver.quit();
    }

}
