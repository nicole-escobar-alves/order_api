Feature: Addon
  Scenario: Create Addon
    Given that I want to register an addon into database
    When creating a new addon
    Then return a validated create addon response

  Scenario: Get Addon
    Given that I want to get an addon from database
    When I search by id for an addon
    Then return a validated addon response

  Scenario: Put Addon
    Given that I want to put an addon from database
    When I update an addon
    Then return a validated update addon response

  Scenario: Delete Addon
    Given that I want to delete an addon from database
    When I delete by id an addon
    Then return a validated delete response