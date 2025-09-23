// 代码生成时间: 2025-09-24 00:35:32
package com.example.config;

import io.micronaut.context.annotation.ConfigurationReader;
import io.micronaut.context.annotation.Value;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.convert.format.Format;
import javax.inject.Singleton;

/**
 * A configuration manager to handle configuration files in Micronaut framework.
 */
@Singleton
public class ConfigFileManager {

    private final ConfigurationReader configurationReader;

    /**
     * Injects the configuration reader from the context.
     * @param configurationReader The configuration reader instance.
     */
    public ConfigFileManager(ConfigurationReader configurationReader) {
        this.configurationReader = configurationReader;
    }

    /**
     * Retrieves a configuration value as a String.
     * @param key The key of the configuration value to retrieve.
     * @return The configuration value, or null if not found.
     */
    @Nullable
    public String getStringConfig(String key) {
        return configurationReader.get(key, String.class).orElse(null);
    }

    /**
     * Retrieves a configuration value as an integer.
     * @param key The key of the configuration value to retrieve.
     * @return The configuration value, or null if not found.
     */
    @Nullable
    @Format
    public Integer getIntConfig(String key) {
        return configurationReader.get(key, Integer.class).orElse(null);
    }

    /**
     * Retrieves a configuration value as a boolean.
     * @param key The key of the configuration value to retrieve.
     * @return The configuration value, or null if not found.
     */
    @Nullable
    public Boolean getBooleanConfig(String key) {
        return configurationReader.get(key, Boolean.class).orElse(null);
    }

    /**
     * Retrieves a configuration value as a double.
     * @param key The key of the configuration value to retrieve.
     * @return The configuration value, or null if not found.
     */
    @Nullable
    public Double getDoubleConfig(String key) {
        return configurationReader.get(key, Double.class).orElse(null);
    }

    /**
     * Retrieves a configuration value as a List of strings.
     * @param key The key of the configuration value to retrieve.
     * @return The list of configuration values, or null if not found.
     */
    @Nullable
    public List<String> getListConfig(String key) {
        return configurationReader.get(key, List.class).orElse(null);
    }

    /**
     * Retrieves a configuration value as a Map.
     * @param key The key of the configuration value to retrieve.
     * @return The map of configuration values, or null if not found.
     */
    @Nullable
    public Map<String, Object> getMapConfig(String key) {
        return configurationReader.get(key, Map.class).orElse(null);
    }

    /**
     * Retrieves a configuration value as a byte array.
     * @param key The key of the configuration value to retrieve.
     * @return The byte array of configuration values, or null if not found.
     */
    @Nullable
    public byte[] getByteArrayConfig(String key) {
        return configurationReader.get(key, byte[].class).orElse(null);
    }

    // Add more methods as needed for different types of configuration values
}