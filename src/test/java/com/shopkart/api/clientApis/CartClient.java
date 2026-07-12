package com.shopkart.api.clientApis;

import com.shopkart.api.baseapi.BaseAPI;
import io.restassured.response.Response;

import java.util.Map;

import static com.shopkart.api.specs.SpecFactory.created201;
import static com.shopkart.api.specs.SpecFactory.success200;

public class CartClient extends BaseAPI {

    public Response createCart(String token) {

        return request(token)
                .when()
                .post("/carts")
                .then()
                .spec(created201())
                .extract()
                .response();
    }

    public Response addItem(String token,
                            String cartId,
                            String sku,
                            int qty) {

        return request(token)
                .body(Map.of(
                        "sku", sku,
                        "qty", qty))
                .when()
                .post("/carts/" + cartId + "/items")
                .then()
                .extract()
                .response();
    }

    public Response getCart(String token,
                            String cartId) {

        return request(token)
                .when()
                .get("/carts/" + cartId)
                .then()
                .spec(success200())
                .extract()
                .response();
    }
}