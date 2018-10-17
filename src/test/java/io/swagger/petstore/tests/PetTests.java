package io.swagger.petstore.tests;

import io.swagger.petstore.actions.PetModelActions;
import io.swagger.petstore.models.PetModel;
import io.swagger.petstore.models.PetNotFoundModel;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class PetTests {

    private SoftAssertions softAssert = new SoftAssertions();

    @Test
    public void addNewPetTest() {
        PetModel pet = new PetModel();
        PetModel petResponse = new PetModelActions(pet).addNewPet();
        softAssert.assertThat(petResponse.getId()).isEqualTo(pet.getId());
        softAssert.assertThat(petResponse.getName()).isEqualTo(pet.getName());
        softAssert.assertThat(petResponse.getStatus()).isEqualTo(pet.getStatus());
        softAssert.assertAll();
    }

    @Test
    public void updatePetTest() {
        PetModel pet = new PetModel();
        PetModelActions petActions = new PetModelActions(pet);
        petActions.addNewPet();
        pet.setStatus(PetModel.Status.UNAVAILABLE.name());
        PetModel petResponse = petActions.updatePet();
        softAssert.assertThat(petResponse.getStatus()).isEqualTo(pet.getStatus());
        softAssert.assertAll();
    }

    @Test
    public void deletePetTest() {
        PetModel pet = new PetModel();
        PetModelActions petActions = new PetModelActions(pet);
        petActions.addNewPet();
        petActions.deletePet();
        PetNotFoundModel petNotFound = petActions.getDeletedPet();
        softAssert.assertThat(petNotFound.getCode()).isEqualTo(1); //1 here means 404 (not found)
        softAssert.assertThat(petNotFound.getMessage()).isEqualTo("Pet not found");
        softAssert.assertAll();
    }

}
