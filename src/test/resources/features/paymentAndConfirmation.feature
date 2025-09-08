@paymentAndConfirmation
  Feature: paymentAndConfirmation

    Scenario: Payment and final confirmation page
      Given user selects payment method "test.paymentMethod"
      When
      Then order summary should be correct