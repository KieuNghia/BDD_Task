@Suite
Feature: Filter search results
  As user
  I want to filter the search results
  So that I can search a necessary product quickly

  Background:
    Given I am on start page "http://pn.com.ua"
    When I select category "Компьютеры"
    And I select subcategory "Ноутбуки, планшеты"


  Scenario Outline: Check filter by price
    And I set minimum "<min>" and maximum "<max>" prices
    Then I see results matching search parameters "<min>" "<max>"
    Examples:
      | min  | max  |
      | 2000 | 4000 |


  Scenario: Check search by product name
    And I sort product by price
    And I get the name of the cheapest product
    And enter product name into the search field and submit
    Then Search Results Page has 1 product
    And product name is equal to specified name

  @ignore
  Scenario: This scenario will never run
    And because of @ignore