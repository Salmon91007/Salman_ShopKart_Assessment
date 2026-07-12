@e2e @smoke
Feature: Checkout places an order and the backend agrees

  Background:
    Given the ShopKart application is available

  Scenario: Alice logs in and successfully purchases an Insulated Bottle

    Given "alice" logs into ShopKart successfully
    Then the Home page should be displayed
    When "alice" searches for the product "Insulated Bottle"
    Then the product "Insulated Bottle" should be displayed in the search results
    When "alice" adds the product "Insulated Bottle" to the cart
    And "alice" navigates to the Cart page
    Then the Cart page should be displayed
    And the product "Insulated Bottle" should be displayed in the cart
    And the cart total should be "₹349.00"
    When "alice" proceeds to Checkout
    Then the Checkout page should be displayed
    When "alice" enters the delivery address "UST Global, Trivandrum"
    And "alice" places the order
    Then the Order Confirmation page should be displayed
    And the order status should be "PLACED"
    And the order total should be "₹349.00"
    And the delivery address should be "UST Global, Trivandrum"
    And the Order API should return status "PLACED"
    And the Order API should return total amount 34900 paise
    And the database should contain "PLACED" order for "alice"
    And the order should belong to "alice"