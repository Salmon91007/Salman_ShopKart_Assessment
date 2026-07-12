@smoke @ui @api
Feature: Product Search

  Background:
    Given the ShopKart application is available
    And "alice" logs into ShopKart successfully

  Scenario: Customer searches for a product successfully

    When "alice" searches for "Insulated Bottle"
    Then the product "Insulated Bottle" should appear in the catalog
    And the Product Search API should return "Insulated Bottle"
    And the Product API should return SKU "SKU-BTL"