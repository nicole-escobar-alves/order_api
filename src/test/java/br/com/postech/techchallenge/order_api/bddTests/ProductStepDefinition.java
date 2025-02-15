package br.com.postech.techchallenge.order_api.bddTests;

import br.com.postech.techchallenge.order_api.dto.product.CreateProductDto;
import br.com.postech.techchallenge.order_api.dto.product.UpdateProductDto;
import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class ProductStepDefinition {
    CreateProductDto createDto;
    UpdateProductDto productDto;
    private Response response;
    private final String ENDPOINT_API_PRODUCT = "http://localhost:8080/product";
    @Given("that I want to register an product into database")
    public void thatIWantToRegisterAnProductIntoDatabase() {
        createDto = new CreateProductDto("Hamburguer", "Descrição", BigDecimal.TEN, 0.0, ProductCategory.LANCHE, 60L);
    }

    @When("creating a new product")
    public void creatingANewProduct() {
        response = given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDto)
                .when()
                .post(ENDPOINT_API_PRODUCT);
    }

    @Then("return a validated create product response")
    public void returnAValidatedCreateProductResponse() {

        response.then().statusCode(HttpStatus.CREATED.value());
    }

    @Given("that I want to get an product from database")
    public void thatIWantToGetAnProductFromDatabase() {
        
    }

    @When("I search by id for an product")
    public void iSearchByIdForAnProduct() {
        response = given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("categoryName", "lanche")
                .log().all()
                .when().get(ENDPOINT_API_PRODUCT);
        
    }

    @Then("return a validated product response")
    public void returnAValidatedProductResponse() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .log().all();
    }

    @Given("that I want to put an product from database")
    public void thatIWantToPutAnProductFromDatabase() {
        productDto = new UpdateProductDto();
        productDto.setName("XSalada");
        productDto.setDescription("Lanche com salada");
        productDto.setPrice(BigDecimal.TEN);
        productDto.setDiscountPercent(0.0);
    }

    @When("I update an product")
    public void iUpdateAnProduct() {
        response = given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(productDto)
                .log().all()
                .when().put(ENDPOINT_API_PRODUCT + "/{id}", 1L);

    }

    @Then("return a validated update product response")
    public void returnAValidatedUpdateProductResponse() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Given("that I want to delete an product from database")
    public void thatIWantToDeleteAnProductFromDatabase() { }

    @When("I delete by id an product")
    public void iDeleteByIdAnProduct() {
        response = when().delete(ENDPOINT_API_PRODUCT + "/{id}", 1L);
    }

    @Then("return a validated product deleted response")
    public void returnAValidatedProductDeletedResponse() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }
}
