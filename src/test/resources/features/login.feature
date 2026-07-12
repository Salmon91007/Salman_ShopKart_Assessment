@ui @smoke
Feature: Customer Login

  As a registered ShopKart customer
  I want to log into the application
  So that I can access the product catalog and my shopping cart

  Scenario: Alice logs into ShopKart successfully

    Given "alice" opens the ShopKart login page
    When she enters valid credentials
    And she clicks the Sign In button
    Then she should be redirected to the Home page