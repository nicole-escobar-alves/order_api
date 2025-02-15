Feature: Customer
  Scenario: Create Customer
    Given that I want to register a new customer into database
    When creating a new customer
    Then return a validated create response

  Scenario: Get Customer
    Given that I want to get a customer from database
    When I search by cpf for a customer
    Then return a validated response