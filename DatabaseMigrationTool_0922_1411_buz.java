// 代码生成时间: 2025-09-22 14:11:09
 * @author [Your Name]
 * @version 1.0
 */

package com.example.migrations;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.runtime.Micronaut;
import javax.inject.Singleton;
import javax.sql.DataSource;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.resource.ClassLoaderResourceAccessor;

@Requires(env = Environment.CLI)
@Singleton
public class DatabaseMigrationTool {

    private final DataSource dataSource;
    private final String changeLogPath;

    // Constructor injection of the DataSource
    public DatabaseMigrationTool(DataSource dataSource, MigrationProperties migrationProperties) {
        this.dataSource = dataSource;
        this.changeLogPath = migrationProperties.getChangeLogPath();
    }

    /**
     * Migrates the database to the latest version.
     *
     * @param context The current Micronaut context.
     * @return A message indicating the migration status.
     */
    public String migrateDatabase() {
        try {
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(dataSource);
            Liquibase liquibase = new Liquibase(changeLogPath, new ClassLoaderResourceAccessor(), database);
            liquibase.update("migration"); // Perform the migration
            return "Database migration successful.";
        } catch (Exception e) {
            // Handle potential exceptions and rethrow if necessary
            throw new RuntimeException("Database migration failed.", e);
        }
    }
}

/*
 * MigrationProperties.java
 *
 * A configuration class for database migration properties.
 */

package com.example.migrations;

import io.micronaut.context.annotation.ConfigurationBuilder;
import io.micronaut.context.annotation.ConfigurationProperties;
import javax.validation.constraints.NotBlank;

@ConfigurationProperties(prefix = "migration")
public class MigrationProperties {

    @NotBlank
    private String changeLogPath;

    // Getter and setter for the changeLogPath
    public String getChangeLogPath() {
        return changeLogPath;
    }

    public void setChangeLogPath(String changeLogPath) {
        this.changeLogPath = changeLogPath;
    }
}

/*
 * Main.java
 *
 * The main entry point for the database migration tool.
 */

package com.example.migrations;

import io.micronaut.runtime.Micronaut;

public class Main {
    public static void main(String[] args) {
        Micronaut.run(DatabaseMigrationTool.class, args);
    }
}
