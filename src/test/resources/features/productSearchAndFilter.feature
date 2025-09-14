
Feature: productSearchAndFilter

  Scenario: Search the specific product and filter out the specific brand name
    Given user searches for "test.product"
    And user filters by brand "test.brandName"
    And user sorts by "test.sortOption"
    And user selects product "test.prodC"
    Then product name should match