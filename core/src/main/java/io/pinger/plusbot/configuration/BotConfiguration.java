package io.pinger.plusbot.configuration;

import io.pinger.plusbot.DiscordBot;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class BotConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(BotConfiguration.class);

    private String botToken;

    private String sqlHost;
    private String sqlUsername;
    private String sqlPassword;

    /**
     * This method loads the configuration from the json file in the path.
     *
     * @param configuration the configuration to load
     * @return the loaded configuration
     */

    public static BotConfiguration loadConfiguration(BotConfiguration configuration) {
        final File file = new File("config.json");
        if (!file.exists()) {
            BotConfiguration.LOGGER.error("config.json does not exist, exiting!!");

            // Write the default file here
            try (final FileWriter writer = new FileWriter(file)) {
                DiscordBot.GSON.toJson(configuration, writer);
            } catch (IOException e) {
                BotConfiguration.LOGGER.error("Failed to create a new config.json file!", e);
            }

            System.exit(-1);
        }

        try (final FileReader reader = new FileReader(file)) {
            return DiscordBot.GSON.fromJson(reader, configuration.getClass());
        } catch (IOException e) {
            BotConfiguration.LOGGER.error("Failed to read file ", e);
            System.exit(-1);
        }

        return null;
    }
}
