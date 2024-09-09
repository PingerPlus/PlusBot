package io.pinger.plusbot.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.pinger.plusbot.configuration.BotConfiguration;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Database {
    private static final Logger LOGGER = LogManager.getLogger(Database.class);

    private static HikariDataSource hikariDataSource;

    /**
     * This method opens a managed connection to the
     * provided sql database.
     *
     * @param configuration the credentials for the database
     */

    public static void open(BotConfiguration configuration) {
        // If the database has already been set up?
        if (Database.hikariDataSource != null) {
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            Database.LOGGER.error("Couldn't find the mysql driver!", e);
            System.exit(-1);
        }

        final HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + configuration.getSqlHost());
        config.setUsername(configuration.getSqlUsername());
        config.setPassword(configuration.getSqlPassword());
        config.setConnectionTimeout(60 * 1000);
        config.setMaxLifetime(5 * 60 * 1000);
        config.setIdleTimeout(30 * 1000);
        config.setMinimumIdle(1);
        config.setMaximumPoolSize(20);

        try {
            Database.hikariDataSource = new HikariDataSource(config);
        } catch (Exception e) {
            Database.LOGGER.error("FAILED TO ESTABLISH A CONNECTION TO THE DATABASE!", e);
            System.exit(-1);
        }

        Database.LOGGER.info("Successfully initiated a connection to the database!");

        // Add a hook to automatically close the source
        Runtime.getRuntime().addShutdownHook(new Thread(Database::shutdown));
    }

    public static Connection getConnection() throws SQLException {
        return Database.hikariDataSource.getConnection();
    }

    public static void shutdown() {
        Database.hikariDataSource.close();
    }

}
