package com.kornaszewska.requests.booking;

import com.kornaszewska.requests.BaseRequest;
import com.mkornaszewska.config.ConfigReader;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteBookingRequest {

    public static Response deleteBookingRequest(String bookingId) {

        return given()
                .spec(BaseRequest.requestSetupWithAuth())
                .when()
                .delete(ConfigReader.getBaseUrl() + ConfigReader.getBookingPath() + "/" + bookingId)
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
