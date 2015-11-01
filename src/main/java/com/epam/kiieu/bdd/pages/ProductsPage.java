package com.epam.kiieu.bdd.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.epam.kiieu.bdd.core.generalhelpers.ClickerHelper.clickOnElement;
import static com.epam.kiieu.bdd.core.generalhelpers.ClickerHelper.clickOnElementWithText;

/**
 * Created by Winner on 29.10.2015.
 */
public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private final static String MIN_PRICE_LIST = "div.group:nth-of-type(3) a";
    private final static String MAX_PRICE_LIST = "div.group:nth-of-type(4) a";
    private final static String PRODUCT_PRICE = "//div[@class='item']//div[@class='price']/strong";
    private final static String PRODUCT_NAME = "div.name>a";
    private final static String SORT_BY_PRICE = "div.order a:nth-of-type(1)";
    private final static String SEARCH_FIELD = "#edit-name-1";
    private final static String SEARCH_BUTTON = "#edit-submit-1";
    private final static String MANUFACTURERS_LIST = "(//div[@class = 'is_empty_items'])[3]/a";

    @FindBy(css = MIN_PRICE_LIST)
    private List<WebElement> minPriceList;
    @FindBy(css = MAX_PRICE_LIST)
    private List<WebElement> maxPriceList;
    @FindBy(xpath = PRODUCT_PRICE)
    private List<WebElement> productsSortedByPrice;
    @FindBy(css = PRODUCT_NAME)
    private List<WebElement> productsName;
    @FindBy(css = SORT_BY_PRICE)
    private WebElement sortByPrice;
    @FindBy(css = SEARCH_FIELD)
    private WebElement searchField;
    @FindBy(css = SEARCH_BUTTON)
    private WebElement searchButton;
    @FindBy(xpath = MANUFACTURERS_LIST)
    private List<WebElement> manufacturersList;

    public ProductsPage selectMinPrice(String price) {
        clickOnElementWithText(driver, minPriceList, price);
        return this;
    }

    public ProductsPage selectMaxPrice(String price) {
        clickOnElementWithText(driver, maxPriceList, price);
        return this;
    }

    public ProductsPage clickSortByPrice() {
        clickOnElement(driver, sortByPrice);
        return this;
    }

    public String getSomePrice(String price) {
        String onlyPrice = null;
        Pattern p = Pattern.compile("\\d+\\s\\d+");
        Matcher m = p.matcher(price);
        while (m.find()) {
            onlyPrice = m.group().replace(" ", "");
        }
        return onlyPrice;
    }

    public int getProductCountNearManufacturer(List<WebElement> manufacturers, List<String>  name) {
        String count = null;
        List<WebElement> listOfCategories = manufacturers;
        for (WebElement webElement : listOfCategories) {
            if (webElement.getText().equals(name.get(0))) {
                count = webElement.getText().trim().replaceAll("\\D", "");
            }
        }
        return Integer.parseInt(count);
    }

    public List<WebElement> getProductsNames() {
        return productsName;
    }

    public List<WebElement> getProductsManufacturers() {
        return manufacturersList;
    }

    public ProductsPage enterTextToTheSearchField(String text) {
        searchField.clear();
        searchField.sendKeys(text);
        return this;
    }

    public ProductsPage clickSearchButton() {
        clickOnElement(driver, searchButton);
        return this;
    }

    public ProductsPage selectManufacturer(List<String>  manufacturer) {
        clickOnElementWithText(driver, manufacturersList, manufacturer.get(0));
        return this;
    }

    public ProductsPage verifyFilterByMinMaxPrice(String min, String max) {
        Integer minFilter = Integer.parseInt(min);
        Integer maxFilter = Integer.parseInt(max);
        List<WebElement> filteredByMinMaxPrice = productsSortedByPrice;
        List<String> listPricesFromSite = new ArrayList<>();
        for (WebElement element : filteredByMinMaxPrice) {
            listPricesFromSite.add(getSomePrice(element.getText()));
            Integer priceInList = Integer.parseInt(getSomePrice(element.getText()));
            Assert.assertTrue("Test 'verifyFilterByMinMaxPrice' failed! Prices don't lie in the specified range!", priceInList > minFilter && priceInList < maxFilter);
        }
        return this;
    }

    public boolean verifyThatFilteredProductsBeginWithManufacturer(String manufacturer) {
        boolean state = false;
        for (WebElement webElement : productsName) {
            if (webElement.getText().startsWith(manufacturer)) {
                state = true;
                break;
            }
            else {
                return false;
            }
        }
        return state;
    }

}
