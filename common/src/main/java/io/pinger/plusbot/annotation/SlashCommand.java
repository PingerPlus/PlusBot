package io.pinger.plusbot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a slash command that can be executed by the user.
 * This annotation should be used on types to mark them as a slash command.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SlashCommand {

    /**
     * The base name of the slash command.
     *
     * @return the name of the slash command
     */
    String name();

    /**
     * A short description of the slash command.
     *
     * @return the description of the slash command
     */
    String description();

    /**
     * Indicates whether the command is global or guild-specific.
     * Defaults to GUILD.
     *
     * @return the target of the slash command
     */
    Target target() default Target.GUILD;

    /**
     * Enum to represent the target of the slash command.
     */
    enum Target {
        GUILD,
        GLOBAL
    }
}
