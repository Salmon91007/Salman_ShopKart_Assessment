package com.shopkart.api.clientApis;

import com.shopkart.api.baseapi.BaseAPI;
import io.restassured.response.Response;

import java.util.Map;

import static com.shopkart.api.specs.SpecFactory.created201;
import static com.shopkart.api.specs.SpecFactory.success200;

public class OrderClient extends BaseAPI {

    public Response placeOrder(String token,
                               String cartId) {

        return request(token)
                .body(Map.of("cartId", cartId))
                .when()
                .post("/orders")
                .then()
                .spec(created201())
                .extract()
                .response();
    }

    public Response getOrder(String token,
                             String orderId) {

        return request(token)
                .when()
                .get("/orders/" + orderId)
                .then()
                .extract()
                .response();
    }

    public Response cancelOrder(String token,
                                String orderId) {

        return request(token)
                .when()
                .post("/orders/" + orderId + "/cancel")
                .then()
                .extract()
                .response();
    }
}