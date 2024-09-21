package com.kornaszewska.requests.auth;

import com.kornaszewska.requests.BaseRequest;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.*;

public class CreateTokenRequest {

    public static Response createTokenRequest(JSONObject requestBody){

        return given()
                .spec(BaseRequest.requestSetup())
                .when()
                .body(requestBody.toString())
                .post()
                .then()
                .extract()
                .response();
    }

}
