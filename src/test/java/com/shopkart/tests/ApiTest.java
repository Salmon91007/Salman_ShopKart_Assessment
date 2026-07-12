//package com.shopkart.tests;
//
//import com.shopkart.api.clientApis.AuthClient;
//import com.shopkart.api.clientApis.CartClient;
//import com.shopkart.api.clientApis.OrderClient;
//import com.shopkart.api.clientApis.ProductClient;
//import io.restassured.response.Response;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ApiTest {
//
//    AuthClient authClient = new AuthClient();
//    ProductClient productClient = new ProductClient();
//    CartClient cartClient = new CartClient();
//    OrderClient orderClient = new OrderClient();
//
//    // Replace with your actual test password
//    private static final String EMAIL = "alice@shopkart.test";
//    private static final String PASSWORD = "AlicePassword@123";
//
//    @Test
//    void shouldLoginSuccessfully() {
//
//        Response response = authClient.login(EMAIL, PASSWORD);
//
//        assertEquals(200, response.statusCode());
//
//        String token = response.jsonPath().getString("token");
//
//        System.out.println(token);
//        assertNotNull(token);
//        assertFalse(token.isBlank());
//
//        response.prettyPrint();
//    }
//
//
//    @Test
//    void shouldSearchProduct() {
//
//        Response response = productClient.search("BAG");
//
//        assertEquals(200, response.statusCode());
//
//        assertFalse(response.jsonPath().getList("$").isEmpty());
//
//        response.prettyPrint();
//    }
//
//    @Test
//    void shouldCreateCart() {
//
//        String token = authClient.token(EMAIL, PASSWORD);
//
//        Response response = cartClient.createCart(token);
//
//        assertEquals(201, response.statusCode());
//
//        assertNotNull(response.jsonPath().getString("cartId"));
//
//        response.prettyPrint();
//    }
//
//    @Test
//    void assertCustomerID(){
//        int customerID = authClient.customerId(EMAIL,PASSWORD);
//
//    }
//
//    @Test
//    void shouldAddItemToCart() {
//
//        String token = authClient.token(EMAIL, PASSWORD);
//
//        String cartId = cartClient.createCart(token)
//                .jsonPath()
//                .getString("cartId");
//
//        Response response = cartClient.addItem(
//                token,
//                cartId,
//                "SKU-BAG",
//                2
//        );
//
//        assertEquals(200, response.statusCode());
//
//        assertEquals(99800, response.jsonPath().getInt("totalPaise"));
//
//        response.prettyPrint();
//    }
//
//    @Test
//    void shouldPlaceOrder() {
//
//        String token = authClient.token(EMAIL, PASSWORD);
//
//        String cartId = cartClient.createCart(token)
//                .jsonPath()
//                .getString("cartId");
//
//        cartClient.addItem(
//                token,
//                cartId,
//                "SKU-BAG",
//                2
//        );
//
//        Response response = orderClient.placeOrder(token, cartId);
//
//        assertEquals(201, response.statusCode());
//
//        String orderId = response.jsonPath().getString("orderId");
//
//        assertNotNull(orderId);
//
//        response.prettyPrint();
//    }
//
//    @Test
//    void shouldGetOrder() {
//
//        String token = authClient.token(EMAIL, PASSWORD);
//
//        String cartId = cartClient.createCart(token)
//                .jsonPath()
//                .getString("cartId");
//
//        cartClient.addItem(token,
//                cartId,
//                "SKU-BAG",
//                2);
//
//        Response res = orderClient.placeOrder(token, cartId);
//
//        String orderId = res.jsonPath().getString("orderId");
//
//        Response response = orderClient.getOrder(token, orderId);
//
//        assertEquals(200, response.statusCode());
//
//        assertEquals("PLACED", response.jsonPath().getString("status"));
//
//        response.prettyPrint();
//    }
//
//    @Test
//    void shouldCancelOrder() {
//
//        String token = authClient.token(EMAIL, PASSWORD);
//
//        String cartId = cartClient.createCart(token)
//                .jsonPath()
//                .getString("cartId");
//
//        cartClient.addItem(
//                token,
//                cartId,
//                "SKU-BAG",
//                2
//        );
//
//        String orderId = orderClient.placeOrder(token, cartId)
//                        .jsonPath()
//                        .getString("orderId");
//
//        Response response =
//                orderClient.cancelOrder(token, orderId);
//
//        assertEquals(200, response.statusCode());
//
//        assertEquals("CANCELLED", response.jsonPath().getString("status"));
//
//        response.prettyPrint();
//    }
//}