package com.epam.kiieu.bdd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.epam.kiieu.bdd.core.generalhelpers.ClickerHelper.clickOnElement;

/**
 * User: Viktoriia_Akhadova Date: 28.10.2015 Time: 19:58
 */
public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private final static String CATEGORY_LIST = "//div[@class = 'home-page-cloud']/h1/a";
    private final static String COMPUTER_CATEGORY = "//a[contains(@href,'computer') and (@class='main_page_link_catalog') ]";

    @FindBy(xpath = CATEGORY_LIST)
    private List<WebElement> categoryList;

    @FindBy(xpath = COMPUTER_CATEGORY)
    private WebElement computer;

    public SubcategoryPage clickOnCategoryByName(String categoryName) {
        List<WebElement> listOfCategories = categoryList;
        for (WebElement webElement : listOfCategories) {
            if (webElement.getText().equals(categoryName)) {
                clickOnElement(driver, webElement);
                break;
            }
        }
        return new SubcategoryPage(driver);
    }

    public SubcategoryPage clickOnComputer(){
        clickOnElement(driver, computer);
        return new SubcategoryPage(driver);
    }

}
