package com.shopkart.data.repository;

import com.shopkart.data.sql.SqlQueries;
import com.shopkart.data.db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderRepository {

    public int countPlacedOrders(long customerId) {

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SqlQueries.COUNT_PLACED_ORDERS)) {

            statement.setLong(1, customerId);

            ResultSet rs = statement.executeQuery();

            rs.next();

            return rs.getInt(1);

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

    public String getOrderStatus(long orderId) {

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SqlQueries.GET_ORDER_STATUS)) {

            statement.setLong(1, orderId);

            ResultSet rs = statement.executeQuery();

            rs.next();

            return rs.getString("status");

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

    public int getOrderTotal(long orderId) {

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SqlQueries.GET_ORDER_TOTAL)) {

            statement.setLong(1, orderId);

            ResultSet rs = statement.executeQuery();

            rs.next();

            return rs.getInt("total_paise");

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

    public long customerIdOfOrder(long orderId) {

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(
                             "SELECT customer_id FROM orders WHERE id = ?")) {

            statement.setLong(1, orderId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getLong("customer_id");
            }

            throw new RuntimeException("Order not found with id: " + orderId);

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch customer id for order: " + orderId, e);
        }
    }
}