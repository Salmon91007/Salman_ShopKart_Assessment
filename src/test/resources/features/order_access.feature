@api @negative @security
Feature: Orders are visible only to their owner

  Scenario: Another customer cannot read someone else's order

    Given "alice" owns a PLACED order
    When "bob" requests Alice's order
    Then the Order API should respond with 403
    And the response body should not contain order details