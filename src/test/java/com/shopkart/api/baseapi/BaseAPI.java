package com.shopkart.api.baseapi;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static com.shopkart.api.specs.SpecFactory.authenticatedRequest;
import static com.shopkart.api.specs.SpecFactory.unauthenticatedRequest;

public abstract class BaseAPI {

    protected RequestSpecification request() {
        return RestAssured
                .given()
                .spec(unauthenticatedRequest());
    }

    protected RequestSpecification request(String token) {
        return RestAssured
                .given()
                .spec(authenticatedRequest(token));
    }
}