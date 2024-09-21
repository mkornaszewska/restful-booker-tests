package com.mkornaszewska.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    private static ConfigReader configReader;

    public ConfigReader() {
        BufferedReader reader;
        String propertyFilePath = "src/main/resources/restfulbooker.properties";
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("restfulbooker.properties not found at " + propertyFilePath);
        }
    }

    public static ConfigReader getInstance() {
        if (configReader == null) {
            configReader = new ConfigReader();
        }
        return configReader;
    }

    public static String getBaseUrl() {
        ConfigReader.getInstance();
        String baseUrl = properties.getProperty("api.base.url");
        if (baseUrl != null) return baseUrl;
        else throw new RuntimeException("api.base.url not specified in the restfulbooker.properties file.");
    }

    public static String getAuthPath() {
        ConfigReader.getInstance();
        String authPath = properties.getProperty("api.endpoint.auth");
        if (authPath != null) return authPath;
        else throw new RuntimeException("api.endpoint.auth not specified in the restfulbooker.properties file.");
    }

    public static String getBookingPath() {
        ConfigReader.getInstance();
        String bookingPath = properties.getProperty("api.endpoint.booking");
        if (bookingPath != null) return bookingPath;
        else throw new RuntimeException("api.endpoint.booking not specified in the restfulbooker.properties file.");
    }

    public static String getHealthCheckPath() {
        ConfigReader.getInstance();
        String healthCheckPath = properties.getProperty("api.endpoint.healthcheck");
        if (healthCheckPath != null) return healthCheckPath;
        else throw new RuntimeException("api.endpoint.healthcheck not specified in the restfulbooker.properties file.");
    }

    public static String getUsername() {
        ConfigReader.getInstance();
        String username = properties.getProperty("api.auth.username");
        if (username != null) return username;
        else throw new RuntimeException("api.auth.username not specified in the restfulbooker.properties file.");
    }

    public static String getPassword() {
        ConfigReader.getInstance();
        String password = properties.getProperty("api.auth.password");
        if (password != null) return password;
        else throw new RuntimeException("api.auth.password not specified in the restfulbooker.properties file.");
    }

}