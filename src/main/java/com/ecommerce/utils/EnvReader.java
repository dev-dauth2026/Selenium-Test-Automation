package com.ecommerce.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvReader {
    private static final Dotenv dotenv = Dotenv.load(); // Load .env file

    public static String getEnv(String key) {
        return dotenv.get(key); // Get value by key
    }
}
