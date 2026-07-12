@api @db
Feature: Cart totals

  Scenario: Cart total equals quantity multiplied by price

    Given "alice" authenticates through the API
    And she creates a new shopping cart
    When she adds 2 quantities of "SKU-BAG"
    And she adds 1 quantity of "SKU-BTL"
    Then the Cart API should return the correct total of 99800
    And the database should contain the same cart total