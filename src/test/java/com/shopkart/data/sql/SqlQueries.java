package com.shopkart.data.sql;

public final class SqlQueries {

    private SqlQueries() {}


    public static final String GET_ORDER_BY_ID = """
            SELECT *
            FROM orders
            WHERE id = ?
            """;

    public static final String GET_ORDERS_BY_CUSTOMER = """
            SELECT *
            FROM orders
            WHERE customer_id = ?
            """;

    public static final String COUNT_PLACED_ORDERS = """
            SELECT COUNT(*)
            FROM orders
            WHERE customer_id = ?
            AND status='PLACED'
            """;

    public static final String GET_ORDER_STATUS = """
            SELECT status
            FROM orders
            WHERE id = ?
            """;

    public static final String GET_ORDER_TOTAL = """
            SELECT total_paise
            FROM orders
            WHERE id = ?
            """;


    public static final String GET_CART = """
            SELECT *
            FROM carts
            WHERE id = ?
            """;

    public static final String GET_CART_TOTAL = """
            SELECT SUM(ci.qty * p.price_paise)
            FROM cart_items ci
            JOIN products p
              ON ci.sku = p.sku
            WHERE ci.cart_id = ?
            """;

    public static final String GET_CART_ITEMS = """
            SELECT *
            FROM cart_items
            WHERE cart_id = ?
            """;

}