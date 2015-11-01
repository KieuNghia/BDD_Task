package com.epam.kiieu.bdd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.epam.kiieu.bdd.core.generalhelpers.ClickerHelper.clickOnElement;

/**
 * Created by Winner on 29.10.2015.
 */
public class SubcategoryPage extends BasePage {

    public SubcategoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private final static String SUBCATEGORY_LIST = "//div[contains(@class,'content category')]//a";
    private final static String NOTEBOOK = "//a[@href='http://pn.com.ua/ct/1003/']";

    @FindBy(xpath = SUBCATEGORY_LIST)
    private List<WebElement> subcategoryList;

    @FindBy(xpath = NOTEBOOK)
    private WebElement notebook;

    public ProductsPage clickOnSubcategoryByName(String categoryName) {
        List<WebElement> listOfCategories = subcategoryList;
        for (WebElement webElement : listOfCategories) {
            if (webElement.getText().equals(categoryName)) {
                clickOnElement(driver, webElement);
                break;
            }
        }
        return new ProductsPage(driver);
    }

    public ProductsPage clickOnNotebook(){
        clickOnElement(driver, notebook);
        return new ProductsPage(driver);
    }

}
