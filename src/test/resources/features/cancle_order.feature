@api
@negative
Feature: Cancel Order

  Scenario: Cancel a placed order once

    Given Alice has placed an order
    When Alice cancels the order
    Then the order status should become "CANCELLED"
    When Alice cancels the same order again
    Then the response status should be 409