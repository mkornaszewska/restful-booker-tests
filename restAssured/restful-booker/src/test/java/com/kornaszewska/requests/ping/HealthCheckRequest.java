package com.kornaszewska.requests.ping;

import com.kornaszewska.requests.BaseRequest;
import com.mkornaszewska.config.ConfigReader;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class HealthCheckRequest {

    public static Response getHealthCheckRequest(){

        return given()
                .spec(BaseRequest.requestSetup())
                .when()
                .get(ConfigReader.getBaseUrl() + ConfigReader.getHealthCheckPath())
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
