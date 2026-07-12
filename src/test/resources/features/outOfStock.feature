@api @negative
Feature: Out of stock validation

  Scenario: Customer cannot add quantity greater than stock

    Given "alice" authenticates through the API
    And she has an empty shopping cart
    When she adds 999 quantities of "SKU-BTL"
    Then the Cart API should respond with 409
