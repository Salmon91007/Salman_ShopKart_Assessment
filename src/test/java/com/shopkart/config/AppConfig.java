package com.shopkart.config;

import com.shopkart.data.secret.Secrets;

public final class AppConfig {

    private AppConfig() {}

    public static final String BASE_URL = Secrets.baseUrl();

    public static final String API_BASE_URL = Secrets.apiBaseUrl();

}