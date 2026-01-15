
  Feature: checkoutAndDeliveredTo

    Scenario: Checkout and deliver to details
      Given user proceeds to checkout "test.prodC"
      When user enters delivery name "test.perName"
      Then user enters delivery address "test.deliverTo"