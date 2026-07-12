package com.shopkart.data.secret;

import io.github.cdimascio.dotenv.Dotenv;

public final class Secrets {

    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();

    private Secrets() {
    }

    public static String get(String key) {

        String value = dotenv.get(key);

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Secret not found: " + key);
        }

        return value;
    }



    public static String email(String user) {

        return switch (user.toLowerCase()) {

            case "alice" -> "alice@shopkart.test";

            case "bob" -> "bob@shopkart.test";

            case "carol" -> "carol@shopkart.test";

            default ->
                    throw new IllegalArgumentException("Unknown user: " + user);
        };
    }

    public static String password(String user) {

        return switch (user.toLowerCase()) {

            case "alice" -> get("SHOPKART_ALICE_PASSWORD");

            case "bob" -> get("SHOPKART_BOB_PASSWORD");

            case "carol" -> get("SHOPKART_CAROL_PASSWORD");

            default ->
                    throw new IllegalArgumentException("Unknown user: " + user);
        };
    }


    public static String dbHost() {
        return get("DB_HOST");
    }

    public static String dbPort() {
        return get("DB_PORT");
    }

    public static String dbName() {
        return get("DB_NAME");
    }

    public static String dbUsername() {
        return get("DB_USER");
    }

    public static String dbPassword() {
        return get("DB_PASSWORD");
    }

    public static String dbUrl() {

        return String.format(
                "jdbc:mysql://%s:%s/%s",
                dbHost(),
                dbPort(),
                dbName()
        );
    }

    // =====================================================
    // Application URLs
    // =====================================================

    public static String baseUrl() {
        return get("BASE_URL");
    }

    public static String apiBaseUrl() {
        return get("API_BASE_URL");
    }

    // =====================================================
    // Server Configuration
    // =====================================================

    public static int port() {
        return Integer.parseInt(get("PORT"));
    }

    public static int connectionTimeout() {
        return Integer.parseInt(get("DB_CONNECTION_TIMEOUT_MS"));
    }

    // =====================================================
    // JWT Secret (if required)
    // =====================================================

    public static String tokenSecret() {
        return get("SHOPKART_TOKEN_SECRET");
    }

}