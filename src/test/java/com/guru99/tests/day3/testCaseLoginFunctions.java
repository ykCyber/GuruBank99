package com.guru99.tests.day3;

import com.guru99.tests.day1.loginFunction;
import com.guru99.utilities.Login;
import com.guru99.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.guru99.utilities.Base.*;


public class testCaseLoginFunctions {
    WebDriver driver = WebDriverFactory.getDriver("chrome");

    @BeforeMethod
    public void setUp() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void afterMethod() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Test
    public void testCase01_All_Valid() {
        driver.get(URL);
        Login.login(driver,VALID_USER_ID, VALID_PASSWORD);
        String actualResult = driver.findElement(By.xpath("//td[contains(text(),'Manger Id')]")).getText();
        Assert.assertTrue(actualResult.contains(VALID_USER_ID),"Verify correct cridentals login.");

    }

    @Test
    public void testCase02_UserID_Valid(){
        driver.get(URL);
        String invalidPassword = "invalidPassword";
        Login.login(driver, VALID_USER_ID, invalidPassword);
        expectedResult = "User or Password is not valid";
        actualResult = driver.switchTo().alert().getText();
        Assert.assertEquals(actualResult,expectedResult,"Verify popUp for wrong password");
    }
    @Test
    public void testCase03_UserPassword_Valid(){
        driver.get(URL);
        String invalidUserID = "invalidID";
        Login.login(driver, invalidUserID, VALID_PASSWORD);
        expectedResult = "User or Password is not valid";
        actualResult = driver.switchTo().alert().getText();
        Assert.assertEquals(actualResult,expectedResult,"Verify popUp for wrong password");
    }
    @Test
    public void testCase04_All_Invalid(){
        driver.get(URL);
        String invalidPassword = "invalidPassword";
        String invalidID = "invalidID";
        Login.login(driver, invalidID, invalidPassword);
        expectedResult = "User or Password is not valid";
        actualResult = driver.switchTo().alert().getText();
        Assert.assertEquals(actualResult,expectedResult,"Verify popUp for wrong password");
    }
}
