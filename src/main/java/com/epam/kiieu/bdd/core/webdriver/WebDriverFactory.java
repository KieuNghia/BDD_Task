package com.epam.kiieu.bdd.core.webdriver;

import com.epam.kiieu.bdd.core.generalhelpers.PropertyFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * User: Viktoriia_Akhadova Date: 28.10.2015 Time: 19:50
 */
public class WebDriverFactory {

    private static WebDriver driver;

    private static PropertyFactory property = new PropertyFactory();
    private static final String BROWSER = property.getProperty("browser.type");

    public static WebDriver createInstance() {
        switch (BROWSER) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "chrome":
                driver = new ChromeDriver();
                break;
            default:
                driver = new FirefoxDriver();
                break;
        }
        return setUpDriver(driver);
    }

    private static WebDriver setUpDriver(WebDriver driver) {
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver;
    }

}
