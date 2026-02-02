package com.extentsparkreports;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentSpark {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    // PageFactory Login Page
    public static class LoginPage {
        WebDriver driver;

        @FindBy(id = "username")
        WebElement usernameField;

        @FindBy(id = "password")
        WebElement passwordField;

        @FindBy(id = "loginBtn")
        WebElement loginButton;

        public LoginPage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        public void login(String user, String pass) {
            usernameField.sendKeys(user);
            passwordField.sendKeys(pass);
            loginButton.click();
        }
    }

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("SparkReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeMethod
    public void setupDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://example.com/login"); // replace with your login URL
    }

    @Test
    public void validLoginTest() {
        test = extent.createTest("Valid Login Test");

        LoginPage loginPage = new LoginPage(driver);
        test.info("Login Page initialized");

        loginPage.login("testuser", "password123");
        test.info("Entered credentials and clicked login");

        String expectedTitle = "Dashboard"; // adjust as per your app
        String actualTitle = driver.getTitle();

        try {
            Assert.assertEquals(actualTitle, expectedTitle);
            test.pass("Login successful, Dashboard opened");
        } catch (AssertionError e) {
            test.fail("Login failed. Expected: " + expectedTitle + ", Found: " + actualTitle);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}
