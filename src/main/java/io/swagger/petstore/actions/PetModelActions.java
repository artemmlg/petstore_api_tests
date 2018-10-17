package io.swagger.petstore.actions;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.swagger.petstore.models.PetModel;
import io.swagger.petstore.models.PetNotFoundModel;

import static io.restassured.RestAssured.given;

public class PetModelActions {

    private RequestSpecification requestSpecification;
    private PetModel pet;

    public PetModelActions(PetModel petModel) {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://petstore.swagger.io")
                .setBasePath("/v2/pet")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL).build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();
        RestAssured.defaultParser = Parser.JSON;
        this.pet = petModel;
    }

    public PetModel addNewPet() {
        return given(requestSpecification)
                .body(pet)
                .post().as(PetModel.class);
    }

    public PetModel updatePet() {
        return given(requestSpecification)
                .body(pet)
                .put().as(PetModel.class);
    }

    public void deletePet() {
        given(requestSpecification)
                .delete(String.valueOf(pet.getId()));
    }

    public Object getPet() {
        Response response = given(requestSpecification).get(String.valueOf(pet.getId()));
        if (response.statusCode() == 200) {
            return response.as(PetModel.class);
        } else {
            return response.as(PetNotFoundModel.class);
        }
    }

    public PetNotFoundModel getDeletedPet() {
        return given(requestSpecification)
                .get(String.valueOf(pet.getId()))
                .then()
                .statusCode(404)
                .and()
                .extract().response().as(PetNotFoundModel.class);

    }
}
