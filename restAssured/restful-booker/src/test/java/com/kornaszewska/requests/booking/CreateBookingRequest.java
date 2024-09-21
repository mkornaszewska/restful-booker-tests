package com.kornaszewska.requests.booking;

import com.kornaszewska.requests.BaseRequest;
import com.mkornaszewska.config.ConfigReader;
import com.mkornaszewska.dto.request.createBooking.CreateBookingRequestDto;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.*;

public class CreateBookingRequest {

    public static Response createBookingRequest(CreateBookingRequestDto bookingDto) {

        return given()
                .spec(BaseRequest.requestSetup())
                .body(bookingDto)
                .when()
                .post(ConfigReader.getBaseUrl() + ConfigReader.getBookingPath())
                .then()
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/kornaszewska/schemas/CreateBookingSchema.json")))
                .log().ifError()
                .extract()
                .response();
    }
}
