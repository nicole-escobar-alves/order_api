package br.com.postech.techchallenge.bddTests;

import br.com.postech.techchallenge.order_api.dto.customer.CreateCustomerDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class CustomerStepDefinition {

    CreateCustomerDto customerDto;
    private Response response;
    private final String ENDPOINT_API_CUSTOMER = "http://localhost:8080/customer";
    @Given("that I want to register a new customer into database")
    public void thatIWantToRegisterANewCustomerIntoDatabase() {
        customerDto = new CreateCustomerDto("Customer", "cpf", "email");
    }
    @When("creating a new customer")
    public void creatingANewCustomer() {
        response = given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(customerDto)
                .when()
                .post(ENDPOINT_API_CUSTOMER);
    }
    @Then("return a validated create response")
    public void returnAValidatedCreateResponse() {
        response.then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Given("that I want to get a customer from database")
    public void thatIWantToGetACustomerFromDatabase() {

    }
    @When("I search by cpf for a customer")
    public void iSearchByCpfForACustomer() {
        response = given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .log().all()
                .when().get(ENDPOINT_API_CUSTOMER + "/{cpf}", "cpf" );
    }

    @Then("return a validated response")
    public void returnAValidatedResponse() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }
}
