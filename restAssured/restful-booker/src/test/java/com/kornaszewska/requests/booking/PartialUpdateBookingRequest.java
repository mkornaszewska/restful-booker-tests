package com.kornaszewska.requests.booking;

import com.kornaszewska.requests.BaseRequest;
import com.mkornaszewska.config.ConfigReader;
import com.mkornaszewska.dto.request.partialUpdateBooking.PartialUpdateBookingRequestDto;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PartialUpdateBookingRequest {

    public static Response partialUpdateBookingRequest(PartialUpdateBookingRequestDto bookingDto, String bookingId) {

        return given()
                .spec(BaseRequest.requestSetupWithAuth())
                .body(bookingDto)
                .when()
                .patch(ConfigReader.getBaseUrl() + ConfigReader.getBookingPath() + "/" + bookingId)
                .then()
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/kornaszewska/schemas/ReadBookingSchema.json")))
                .log().ifError()
                .extract()
                .response();
    }
}
