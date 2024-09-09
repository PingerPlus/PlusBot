package io.pinger.plusbot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.pinger.plusbot.configuration.BotConfiguration;
import io.pinger.plusbot.database.Database;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DiscordBot {
    private static final Logger LOGGER = LogManager.getLogger(DiscordBot.class);

    public static final Gson GSON = new GsonBuilder()
        .enableComplexMapKeySerialization()
        .setPrettyPrinting()
        .create();

    private final BotConfiguration configuration;

    private JDA jda;

    public DiscordBot() {
        this.configuration = BotConfiguration.loadConfiguration(new BotConfiguration());

        Database.open(this.configuration);

        try {
            DiscordBot.LOGGER.info("Starting JDA");

            this.jda = JDABuilder.createDefault(this.configuration.getBotToken())
                .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT)
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setActivity(Activity.playing(""))
                .build();

            DiscordBot.LOGGER.info("JDA started!");
        } catch (Throwable e) {
            DiscordBot.LOGGER.error("An error occurred while building the bot ", e);
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        //final int cores = Runtime.getRuntime().availableProcessors();
        //if (cores <= 1) {
        //    System.out.println("Available Cores \"" + cores + "\", setting Parallelism Flag");
        //    System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "1");
        //}

        // Create a new application
        new DiscordBot();
    }
}
