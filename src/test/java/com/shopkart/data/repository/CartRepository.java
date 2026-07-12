package com.shopkart.data.repository;

import com.shopkart.data.db.DatabaseConnection;
import com.shopkart.data.sql.SqlQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CartRepository {

    public int getCartTotal(long cartId) {

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SqlQueries.GET_CART_TOTAL)) {

            statement.setLong(1, cartId);

            ResultSet rs = statement.executeQuery();

            rs.next();

            return rs.getInt(1);

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

    public int getItemCount(long cartId) {

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SqlQueries.GET_CART_ITEMS)) {

            statement.setLong(1, cartId);

            ResultSet rs = statement.executeQuery();

            int count = 0;

            while (rs.next()) {

                count++;

            }

            return count;

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

}