package io.pinger.plusbot.command;

import io.pinger.plusbot.annotation.Argument;
import io.pinger.plusbot.annotation.Subcommand;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandExtractor {
    private static final Logger LOGGER = LogManager.getLogger(CommandExtractor.class);

    /**
     * This method extracts all subcommands that can be found within a
     * single object handler.
     *
     * @param handler the handler
     * @param parent the parent command
     */
    public void extractCommands(Object handler, BaseCommand parent) {
        for (final Method method : handler.getClass().getMethods()) {
            if (!method.isAnnotationPresent(Subcommand.class)) {
                continue;
            }

            final Subcommand command = method.getAnnotation(Subcommand.class);
            SubcommandData commandData = null;

            if (!command.name().isEmpty()) {
                commandData = new SubcommandData(command.name(), command.description());
            }

            // Listen to the parameters
            // The method needs to at least listen to the SlashCommandInteractionEvent
            final Parameter[] parameters = method.getParameters();
            if (parameters.length == 0 || !parameters[0].getType().isAssignableFrom(SlashCommandInteractionEvent.class)) {
                CommandExtractor.LOGGER.error("SlashCommandInteractEvent argument missing for method {}!", method.getName());
                continue;
            }

            // Handle the parameters
            for (int i = 1; i < parameters.length; i++) {
                final Parameter parameter = method.getParameters()[i];
                if (!parameter.isAnnotationPresent(Argument.class)) {
                    CommandExtractor.LOGGER.error("Parameter with index {} doesn't have an @Argument annotation!", i);
                    break;
                }

                final Argument argument = parameter.getAnnotation(Argument.class);
                if (command.name().isEmpty()) {
                    parent.addOption(CommandArgument.getOption(parameter.getType()), argument.value(), argument.description(), argument.required());
                } else {
                    if (commandData != null) {
                        commandData.addOption(CommandArgument.getOption(parameter.getType()), argument.value(), argument.description(), argument.required(), false);
                    }
                }
            }

            // Add the subcommand to the parent
            if (commandData != null) {
                parent.addSubcommands(commandData);
            }
        }
    }
}
