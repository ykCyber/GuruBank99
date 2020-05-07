package com.guru99.tests.loginTestCase01;

import com.github.javafaker.Faker;
import com.guru99.utilities.WebDriverFactory;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class loginFunction {
    public static final String URL = "http://www.demo.guru99.com/V4/";
    public static final String USER_ID = "mngr259679";
    public static final String PASSWORD = "jymUhyr";


    WebDriver driver = WebDriverFactory.getManuelDriver("chrome");

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    @BeforeMethod
    public void setUp() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void afterMethod() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    //Verify login with valid Cridentals
    public void login() throws InterruptedException {
        driver.get(URL);
        WebElement userNameBox = driver.findElement(By.xpath(("(//td//input)[1]")));
        userNameBox.sendKeys(USER_ID, Keys.TAB, PASSWORD, Keys.ENTER);
        WebElement welcomeMarquee = driver.findElement(By.xpath("//marquee"));
        String actualId = driver.findElement(By.xpath("(//td)[3]")).getText();
        Assert.assertTrue(actualId.contains(USER_ID));
        Assert.assertTrue(welcomeMarquee.isDisplayed());
        Assert.assertFalse(isAlertPresent());
        System.out.println("driver.getWindowHandles().size() = " + driver.getWindowHandles().size());
        int size = driver.findElements(By.tagName("iframe")).size();
        driver.switchTo().frame(1);
        System.out.println("size = " + size);
//        driver.switchTo().frame(driver.findElement(By.xpath("//div[starts-with(@id, 'Player')]")));
    }

    @Test
    //Verfiy can not login with wrong cridentals
    public void loginWrongCridentals() {
        Faker jf = new Faker();
        driver.get(URL);
        WebElement userNameBox = driver.findElement(By.xpath(("(//td//input)[1]")));
        userNameBox.sendKeys(jf.name().username(), Keys.TAB, jf.internet().password(), Keys.ENTER);
        // Capturing alert message.
        Assert.assertTrue(isAlertPresent());
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());

    }

    @Test
    public void test(){
        driver.get(URL);
        WebElement userNameBox = driver.findElement(By.xpath(("(//td//input)[1]")));
        userNameBox.sendKeys(USER_ID, Keys.TAB, Keys.ENTER);

    }
}
