package io.pinger.plusbot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a subcommand for a command system.
 * This annotation should be used on methods to mark them as subcommands
 * of a base command.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Subcommand {

    /**
     * The name of the subcommand.
     * Defaults to an empty string if not specified.
     *
     * @return the name of the subcommand
     */
    String name() default "";

    /**
     * A short description of the subcommand.
     *
     * @return the description of the subcommand
     */
    String description();

    /**
     * The group this subcommand belongs to.
     * Defaults to an empty string if not specified.
     *
     * @return the group of the subcommand
     */
    String group() default "";
}
