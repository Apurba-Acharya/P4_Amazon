Feature: Amazon Product buy
  Scenario: Amazon Product buy from search to payment
    Given Search and select the particular product
    When I choose payment method Cash on Delivery
    Then I should see delivery name "Delivering to Arpita Acharya"
    And I should see delivery address "5/28 Sri Vishnu Appartment ph 2, Brahmapur Shiv Mandir Road, KOLKATA, WEST BENGAL, 700096, India"
    And I should see payment type "Pay on delivery (Cash/Card)"