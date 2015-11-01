package com.epam.kiieu.bdd.steps;

import com.epam.kiieu.bdd.core.webdriver.WebDriverFactory;
import com.epam.kiieu.bdd.pages.BasePage;
import com.epam.kiieu.bdd.pages.HomePage;
import com.epam.kiieu.bdd.pages.ProductsPage;
import com.epam.kiieu.bdd.pages.SubcategoryPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FilterTestSteps {

    private BasePage basePage;
    private HomePage homePage;
    private SubcategoryPage subcategoryPage;
    private ProductsPage productsPage;
    //    private SearchResultPageHelper searchResultPageHelper;
    private WebDriver driver;

    private String cheapProduct;

    @Before
    public void setUp() throws InterruptedException {
        driver = WebDriverFactory.createInstance();
        basePage = new BasePage(driver);
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Given("^I am on start page \"([^\"]*)\"$")
    public void I_am_on_start_page(String startPage) {
        homePage = basePage.openPage(startPage);
    }

    @And("^I select category \"([^\"]*)\"$")
    public void I_select_category(String category) {
        //subcategoryPage = homePage.clickOnCategoryByName(category);//todo git destroy cyrillic symbols
       subcategoryPage = homePage.clickOnComputer();
    }

    @And("^I select subcategory \"([^\"]*)\"$")
    public void I_select_subcategory(String subcategory) {
        //productsPage = subcategoryPage.clickOnSubcategoryByName(subcategory); //todo git destroy cyrillic symbols
        productsPage = subcategoryPage.clickOnNotebook();
    }

    @And("^I set minimum \"([^\"]*)\" and maximum \"([^\"]*)\" prices$")
    public void I_set_mini_and_max_prices(String minPrice, String maxPrice) {
        productsPage
                .selectMinPrice(minPrice)
                .selectMaxPrice(maxPrice);
    }

    @Then("^I see results matching search parameters \"([^\"]*)\" \"([^\"]*)\"$")
    public void I_see_results_matching_search_parameters(String min, String max) {
        productsPage.verifyFilterByMinMaxPrice(min, max);
    }

    @And("^I sort product by price$")
    public void I_sort_product_by_price() {
        productsPage.clickSortByPrice();
    }

    @And("^I get the name of the cheapest product$")
    public void I_get_the_name_of_the_cheapest_product() {
        cheapProduct = productsPage.getProductsNames().get(0).getText();
    }

    @And("^enter product name into the search field and submit$")
    public void enter_product_name_into_the_search_field() {
        productsPage.enterTextToTheSearchField(cheapProduct)
                .clickSearchButton();
    }

    @Then("^Search Results Page has (\\d+) product$")
    public void Search_Results_Page_has_product(int quantity) {
        assertEquals(quantity, productsPage.getProductsNames().size());
    }

    @And("^product name is equal to specified name$")
    public void product_name_is_equal_to_specified_name() {
        assertEquals(productsPage.getProductsNames().get(0).getText(), cheapProduct);
    }

}
