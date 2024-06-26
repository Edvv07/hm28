package ru.tinkoff.qa.apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.tinkoff.qa.hibernate.apimodels.PetRequest.PetRequest;
import ru.tinkoff.qa.hibernate.apimodels.PetResponse.PetGetResponse;
import ru.tinkoff.qa.hibernate.apimodels.PetResponse.PetGetResponseNotFound;
import ru.tinkoff.qa.hibernate.apimodels.PetResponse.PetPutResponse;
import ru.tinkoff.qa.hibernate.apimodels.PetResponse.PetResponse;

public class PetsApiTests {
    public static final String BASE_URL = "https://petstore.swagger.io/v2/pet/";
    private PetRequest petRequest;
    private PetResponse petResponse;
    private PetGetResponse getResponse;
    private PetGetResponseNotFound petGetResponseNotFound;
    private PetPutResponse petPutResponse;

    @BeforeEach
    public final void init() {
        petRequest = new PetRequest();
        petRequest.setId(144);
        petRequest.setName("CatsCat");
    }

    @Test
    public void addPet() {
        petResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petRequest)
                .post(BASE_URL)
                .as(PetResponse.class);
        Assertions.assertEquals(petRequest.getId(), petResponse.getId());
    }

    @Test
    public void getPetId() {
        petResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petRequest)
                .post(BASE_URL)
                .as(PetResponse.class);

        getResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petRequest)
                .get(BASE_URL + petRequest.getId())
                .as(PetGetResponse.class);
        Assertions.assertEquals(petRequest.getName(), getResponse.getName());
    }

    @Test
    public void updatePet() {
        petRequest.setName("newNameCats");

        petPutResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petRequest)
                .put(BASE_URL)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(PetPutResponse.class);
        Assertions.assertEquals(petRequest.getName(), petPutResponse.getName());
    }

    @Test
    public void addAndDeletePet() {
        petResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petRequest)
                .post(BASE_URL)
                .as(PetResponse.class);
        Assertions.assertEquals(petRequest.getId(), petResponse.getId());

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petRequest)
                .delete(BASE_URL + petRequest.getId())
                .then()
                .statusCode(200);

        petGetResponseNotFound = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petRequest)
                .get(BASE_URL + petRequest.getId())
                .as(PetGetResponseNotFound.class);
        Assertions.assertEquals("Pet not found", petGetResponseNotFound.getMessage());
    }
}
