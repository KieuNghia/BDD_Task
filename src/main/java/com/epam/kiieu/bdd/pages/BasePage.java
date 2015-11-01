package com.epam.kiieu.bdd.pages;

import com.epam.kiieu.bdd.core.generalhelpers.WaiterHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static com.epam.kiieu.bdd.core.generalhelpers.Constants.SMALL_DELAY;

/**
 * User: Viktoriia_Akhadova Date: 28.10.2015 Time: 19:59
 */
public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public HomePage openPage(String page) {
        driver.get(page);
        WaiterHelper.delay(SMALL_DELAY);
        return new HomePage(driver);
    }

}
