package com.kornaszewska.tests.auth;

import com.kornaszewska.requests.auth.CreateTokenRequest;
import com.mkornaszewska.config.ConfigReader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;


class CreateTokenTest {

    @Test
    void validCredentialsTest() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", ConfigReader.getUsername());
        requestBody.put("password", ConfigReader.getPassword());

        final Response response = CreateTokenRequest.createTokenRequest(requestBody);

        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("token")).isNotEmpty();
    }


}
