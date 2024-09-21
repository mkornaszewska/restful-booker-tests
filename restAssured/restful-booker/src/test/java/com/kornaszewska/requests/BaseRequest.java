package com.kornaszewska.requests;

import com.kornaszewska.requests.auth.CreateTokenRequest;
import com.mkornaszewska.config.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

public class BaseRequest {

    public static RequestSpecification requestSetup() {
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri(ConfigReader.getBaseUrl());
        requestBuilder.setBasePath(ConfigReader.getAuthPath());
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.addFilter(new RequestLoggingFilter());
        requestBuilder.addFilter(new ResponseLoggingFilter());

        return requestBuilder.build();
    }

    public static RequestSpecification requestSetupWithAuth() {
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri(ConfigReader.getBaseUrl());
        requestBuilder.setBasePath(ConfigReader.getAuthPath());
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.addHeader("Cookie", "token=" + getToken());
        requestBuilder.addFilter(new RequestLoggingFilter());
        requestBuilder.addFilter(new ResponseLoggingFilter());

        return requestBuilder.build();
    }

    public static String getToken(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", ConfigReader.getUsername());
        requestBody.put("password", ConfigReader.getPassword());

        final Response response = CreateTokenRequest.createTokenRequest(requestBody);

        JsonPath jsonData = response.jsonPath();
        String token = jsonData.getString("token");
        return token;
    }
}

