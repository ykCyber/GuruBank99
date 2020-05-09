package com.guru99.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class WebDriverFactory {
    public static WebDriver getManuelDriver(String browserType) {
        String projectPath = System.getProperty("user.dir");

        WebDriver driver = null;
        switch (browserType.toLowerCase()) {
            case "chrome":
                String relativePath = "drivers\\chromedriver.exe";
                String filePath = (projectPath + "\\" + relativePath);
                System.setProperty("webdriver.chrome.driver", filePath);

                ChromeOptions options = new ChromeOptions();
                options.addExtensions(new File("I:\\extentions\\chropath_6_1_4_0.crx"));

                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                driver = new ChromeDriver(capabilities);
                System.out.println("Opening extension");
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
                ChromeOptions options = new ChromeOptions();
                //download related crx file of Extention
                // show path to addExtentions
                //import related classes
                //delete the last line driver = new ChromeDriver(); otherwise browser will open as default without extensions
                options.addExtensions(new File("I:\\extentions\\chropath_6_1_4_0.crx"));
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                driver = new ChromeDriver(capabilities);


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