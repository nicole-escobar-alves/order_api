Feature: Order

    #Scenario com erro na integração com o pagamento
    #Scenario: Create Order
    #    Given I want to place an order
    #    When creating a new order
    #   Then return a validated create order response

    Scenario: Get Order By Customer
        Given I want find an order by customer
        When entering a customer CPF
        Then return a detailed order

    Scenario: Get Order By Status
        Given I want find an order by status
        When entering a order status
        Then return all orders with the required status

    Scenario: Get All Orders
        Given I want to find all orders
        When all the orders are required
        Then return all detailed orders
