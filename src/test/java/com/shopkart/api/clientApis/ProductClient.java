package com.shopkart.api.clientApis;

import com.shopkart.api.baseapi.BaseAPI;
import io.restassured.response.Response;

import static com.shopkart.api.specs.SpecFactory.success200;

public class ProductClient extends BaseAPI {

    public Response search(String productName) {

        return request()
                .queryParam("q", productName)
                .when()
                .get("/products")
                .then()
                .spec(success200())
                .extract()
                .response();
    }

    public Response allProducts() {

        return request()
                .when()
                .get("/products")
                .then()
                .spec(success200())
                .extract()
                .response();
    }
}