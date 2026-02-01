
  Feature: paymentAndConfirmation

    Scenario: Payment and final confirmation page
      Given user selects payment method "test.paymentMethod"
      Then order summary should be correct