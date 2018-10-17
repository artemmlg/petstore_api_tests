package io.swagger.petstore.tests;

import io.swagger.petstore.actions.PetModelActions;
import io.swagger.petstore.models.PetModel;
import io.swagger.petstore.utils.PetStoreUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class PetTests {

    private SoftAssertions softAssert = new SoftAssertions();

    @Test
    public void addNewPetTest() {
        PetModel pet = new PetModel(PetStoreUtils.nameGenerator.name().firstName(), PetStoreUtils.idGenerator(), PetModel.Status.AVAILABLE.name());
        PetModel petResponse = new PetModelActions(pet).addNewPet();
        softAssert.assertThat(petResponse.getId()).isEqualTo(pet.getId());
        softAssert.assertAll();
    }

}
