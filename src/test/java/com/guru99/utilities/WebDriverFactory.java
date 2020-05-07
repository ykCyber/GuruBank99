package com.guru99.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class WebDriverFactory {
    public static WebDriver getManuelDriver(String browserType) {
        String projectPath = System.getProperty("user.dir");

        WebDriver driver = null;
        switch (browserType.toLowerCase()) {
            case "chrome":
                String relativePath = "drivers\\chromedriver.exe";
                String filePath = (projectPath + "\\" + relativePath);
                System.setProperty("webdriver.chrome.driver", filePath);

                driver = new ChromeDriver();
                break;
            case "firefox":

                relativePath = "drivers\\geckhodriver.exe";
                filePath = (projectPath + "\\" + relativePath);
                System.setProperty("webdriver.chrome.driver", filePath);

                driver = new FirefoxDriver();
            case "opera":

                relativePath = "drivers\\operadriver.exe";
                filePath = (projectPath + "\\" + relativePath);
                System.setProperty("webdriver.opera.driver", filePath);

                driver = new OperaDriver();
                break;
        }
        return driver;
    }

    public static WebDriver getDriver(String browserType) {
        WebDriver driver = null;
        switch (browserType.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "opera":
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
                break;
        }

        return driver;
    }
}