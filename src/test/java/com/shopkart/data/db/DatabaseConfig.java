package com.shopkart.data.db;

import com.shopkart.data.secret.Secrets;

public final class DatabaseConfig {

    private DatabaseConfig() {
    }

    public static String getUrl() {
        return Secrets.dbUrl();
    }

    public static String getUsername() {
        return Secrets.dbUsername();
    }

    public static String getPassword() {
        return Secrets.dbPassword();
    }

}