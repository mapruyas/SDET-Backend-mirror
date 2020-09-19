package com.example.nasa;

import io.github.cdimascio.dotenv.Dotenv;

public class Configuration {

    private final Dotenv dotenv = Dotenv
            .configure()
            .ignoreIfMissing()
            .load();

    public Configuration() {}

    public String getNASAApiBaseUrl() {
        return dotenv.get("NASA_API_BASE_URL", "https://api.nasa.gov");
    }

    public String getNASAApiKey() {
        return dotenv.get("NASA_API_KEY", "DEMO_KEY");
    }
}
