package br.com.postech.techchallenge.bddTests;

import br.com.postech.techchallenge.orderapi.dto.combo.CreateComboDto;
import br.com.postech.techchallenge.orderapi.dto.order.CreateOrderDto;
import br.com.postech.techchallenge.orderapi.dto.order.DetailsOrderDto;
import br.com.postech.techchallenge.orderapi.enums.OrderStatus;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
public class OrderStepDefinition {
    private final String ENDPOINT_API_ORDER = "http://localhost:8080/order";
    private Response response;
    CreateOrderDto createOrderDto;
    @When("creating a new order")
    public void creating_a_new_order()
    {
        response = given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .log().all()
                .body(createOrderDto)
                .when()
                .post(ENDPOINT_API_ORDER);
    }
    @Given("I want to place an order")
    public void iWantToPlaceAnOrder()
    {
        List<CreateComboDto> comboDtoList = new ArrayList<>();
        CreateComboDto comboDto = new CreateComboDto();
        List<Long> ArrayList = new ArrayList<>();
        ArrayList.add(1L);
        comboDto.setAddonsId(ArrayList);
        comboDto.setProductId(1L);
        comboDtoList.add(comboDto);

        createOrderDto = new CreateOrderDto();
        createOrderDto.setCustomerId(1L);
        createOrderDto.setCombos(comboDtoList);
    }

    @Then("return a validated create order response")
    public void returnAValidatedCreateOrderResponse() {
        response.then().statusCode(HttpStatus.CREATED.value());
    }

    @Given("I want find an order by customer")
    public void iWantFindAnOrderByCustomer() { }

    @When("entering a customer CPF")
    public void enteringACustomerCPF() {
        response = given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .log().all()
                .when().get(ENDPOINT_API_ORDER + "?cpf={cpf}", "cpf");
    }

    @Then("return a detailed order")
    public void returnADetailedOrder() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .log().all();
    }
    @Given("I want find an order by status")
    public void iWantFindAnOrderByStatus() { }

    @When("entering a order status")
    public void enteringAOrderStatus() {
        response = given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .log().all()
                .when().get(ENDPOINT_API_ORDER + "/status/{orderStatus}", OrderStatus.CREATED);
    }
    @Then("return all orders with the required status")
    public void returnAllOrdersWithTheRequiredStatus() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .log().all();
    }

    @Given("I want to find all orders")
    public void iWantToFindAllOrders() {}

    @When("all the orders are required")
    public void allTheOrdersAreRequired() {
        response = given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .log().all()
                .when().get(ENDPOINT_API_ORDER + "/allStatus");
    }

    @Then("return all detailed orders")
    public void returnAllDetailedOrders() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .log().all()
                .extract()
                .as(DetailsOrderDto[].class);
    }
}
