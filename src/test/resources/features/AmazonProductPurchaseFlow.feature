Feature: AmazonProductPurchaseFlow
  As a registered user
  I want to search, filter, select, and purchase a product
  So that I can successfully place an order

  Scenario: User buys a product from search to payment
    Given user is logged in with email "test.email/mobileNO." and password "test.password"

    When user searches for "test.product"
    And user filters by brand "test.brandName"
    And user sorts by "test.sortOption"
    And user selects product "test.prodC"
    Then product name should match
    And user proceeds to checkout
    And user enters delivery name "test.perName"
    And user enters delivery address "test.deliverTo"
    And user selects payment method "test.paymentMethod"
    Then order summary should be correct