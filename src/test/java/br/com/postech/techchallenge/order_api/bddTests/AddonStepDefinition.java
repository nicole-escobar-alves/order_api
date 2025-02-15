package br.com.postech.techchallenge.order_api.bddTests;

import br.com.postech.techchallenge.orderapi.dto.addon.AddonDto;
import br.com.postech.techchallenge.orderapi.dto.addon.CreateAddonDto;
import br.com.postech.techchallenge.orderapi.dto.addon.UpdateAddonDto;
import br.com.postech.techchallenge.orderapi.enums.ProductCategory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.math.BigDecimal;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class AddonStepDefinition {

    CreateAddonDto createAddonDto;
    UpdateAddonDto addonDto;
    private Response response;
    private final String ENDPOINT_API_ADDON = "http://localhost:8080/addon";
    ObjectMapper mapper;
    @Given("that I want to register an addon into database")
    public void thatIWantToRegisterAnAddonIntoDatabase() {}
    @When("creating a new addon")
    public void creatingANewAddon() {
        createAddonDto = new CreateAddonDto("Batata", BigDecimal.TEN, ProductCategory.LANCHE, 0.0);

        response = given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createAddonDto)
                .when()
                .post(ENDPOINT_API_ADDON);
    }
    @Then("return a validated create addon response")
    public void returnAValidatedCreateAddonResponse() {

        response.then().statusCode(HttpStatus.CREATED.value());
    }

    @Given("that I want to get an addon from database")
    public void thatIWantToGetAnAddonFromDatabase() { }

    @When("I search by id for an addon")
    public void iSearchByIdForAnAddon() {
        response = given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .log().all()
                .when().get(ENDPOINT_API_ADDON + "?categoryName={0}", ProductCategory.LANCHE.getDisplayName());
    }
    @Then("return a validated addon response")
    public void returnAValidatedAddonResponse() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .log().all()
                .extract()
                .as(AddonDto[].class);
    }

    @Given("that I want to delete an addon from database")
    public void thatIWantToDeleteAnAddonFromDatabase() { }

    @When("I delete by id an addon")
    public void iDeleteByIdAnAddon() {
        response = when().delete(ENDPOINT_API_ADDON + "/{id}", 1L);
    }

    @Then("return a validated delete response")
    public void returnAValidatedDeleteResponse() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Given("that I want to put an addon from database")
    public void thatIWantToPutAnAddonFromDatabase() {
        addonDto = new UpdateAddonDto();
        addonDto.setName("Bacon");
        addonDto.setPrice(BigDecimal.TEN);
        addonDto.setDiscountPercent(0.0);
        addonDto.setProductCategory(ProductCategory.LANCHE);
    }

    @When("I update an addon")
    public void iUpdateAnAddon() {
        response = given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(addonDto)
                .log().all()
                .when().put(ENDPOINT_API_ADDON + "/{id}", 1L);
    }

    @Then("return a validated update addon response")
    public void returnAValidatedUpdateAddonResponse() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }
}
