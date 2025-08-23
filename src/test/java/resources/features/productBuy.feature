Feature: Amazon Product Purchase Flow
  As a registered user
  I want to search, filter, select, and purchase a product
  So that I can successfully place an order

  Scenario: User buys a product from search to payment
    Given user is logged in with email "testuser@gmail.com" and password "Password123"
    When user searches for "Phones"
    And user filters by brand "Samsung"
    And user sorts by "Newest Arrivals"
    And user selects product "Samsung Galaxy Z Fold7 5G Smartphone with Galaxy AI (Silver Shadow, 12GB RAM, 512GB Storage), Ultra Sleek Design with 200MP Camera, Powerful Snapdragon 8 Elite, Google Gemini"
    Then product name should match
    And user proceeds to checkout
    And user enters delivery name "Apurba Acharya"
    And user enters delivery address "123 Main Street, Bangalore"
    And user selects payment method "Credit Card"
    Then order summary should be correct
