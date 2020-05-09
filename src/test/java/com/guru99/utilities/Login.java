package com.guru99.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {
    public static void login(WebDriver driver, String userID, String password) {
        WebElement userNameBox = driver.findElement(By.xpath(("(//td//input)[1]")));
        userNameBox.sendKeys(userID, Keys.TAB, password, Keys.ENTER);
    }
}
