Feature: Product

  @smoke @high
  Scenario: Create Product
    Given that I want to register an product into database
    When creating a new product
    Then return a validated create product response

  @smoke
  Scenario: Get Product
    Given that I want to get an product from database
    When I search by id for an product
    Then return a validated product response


  Scenario: Put Product
    Given that I want to put an product from database
    When I update an product
    Then return a validated update product response

  Scenario: Delete Product
    Given that I want to delete an product from database
    When I delete by id an product
    Then return a validated product deleted response