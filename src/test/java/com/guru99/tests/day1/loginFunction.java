package com.guru99.tests.day1;

import com.github.javafaker.Faker;
import com.guru99.utilities.*;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.guru99.utilities.Base.*;


public class loginFunction {



    WebDriver driver = WebDriverFactory.getManuelDriver("chrome");

    public static boolean isAlertPresent(WebDriver driver) {
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
        userNameBox.sendKeys(VALID_USER_ID, Keys.TAB, VALID_PASSWORD, Keys.ENTER);
        WebElement welcomeMarquee = driver.findElement(By.xpath("//marquee"));
        String actualId = driver.findElement(By.xpath("(//td)[3]")).getText();
        Assert.assertTrue(actualId.contains(VALID_USER_ID));
        Assert.assertTrue(welcomeMarquee.isDisplayed());
        Assert.assertFalse(isAlertPresent(driver));
        System.out.println("driver.getWindowHandles().size() = " + driver.getWindowHandles().size());
        int size = driver.findElements(By.tagName("iframe")).size();
        WebDriver element = driver.switchTo().frame("flow_close_btn_iframe");
        System.out.println(element.getPageSource());
        System.out.println("size = " + size);
        driver.findElement(By.id("closeBtn")).click();
        size = driver.findElements(By.tagName("iframe")).size();
        System.out.println("size = " + size);
//        driver.findElement(By.xpath("//div[starts-with(@id, 'Player')]")).click();
        Thread.sleep(5000);
    }

    @Test
    //Verfiy can not login with wrong cridentals
    public void loginWrongCridentals() {
        Faker jf = new Faker();
        driver.get(URL);
        WebElement userNameBox = driver.findElement(By.xpath(("(//td//input)[1]")));
        userNameBox.sendKeys(jf.name().username(), Keys.TAB, jf.internet().password(), Keys.ENTER);
        // Capturing alert message.
        Assert.assertTrue(isAlertPresent(driver));
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());

    }

    @Test
    public void test(){
        driver.get(URL);
        WebElement userNameBox = driver.findElement(By.xpath(("(//td//input)[1]")));
        userNameBox.sendKeys(VALID_USER_ID, Keys.TAB, Keys.ENTER);

    }
}
