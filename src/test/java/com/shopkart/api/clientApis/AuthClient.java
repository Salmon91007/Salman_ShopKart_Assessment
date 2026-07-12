package com.shopkart.api.clientApis;

import com.shopkart.api.baseapi.BaseAPI;
import io.restassured.response.Response;

import java.util.Map;

import static com.shopkart.api.specs.SpecFactory.success200;

public class AuthClient extends BaseAPI {


    public Response login(String email, String password) {

        return request()
                .body(Map.of(
                        "email", email,
                        "password", password))
                .when()
                .post("/auth/login")
                .then()
                .spec(success200())
                .extract()
                .response();
    }


    public String token(String email, String password) {

        return login(email, password)
                .jsonPath()
                .getString("token");
    }

    public Integer customerId(String email, String password) {

        return login(email, password)
                .jsonPath()
                .getInt("customerId");
    }
}