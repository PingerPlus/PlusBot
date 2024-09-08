package io.pinger.plusbot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents an argument for a command.
 * This annotation should be used on method parameters to specify argument details.
 * An argument can be either mandatory or optional, depending on the use case.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface Argument {

    /**
     * The name of the argument.
     *
     * @return the name of the argument
     */
    String value();

    /**
     * The description of the argument.
     *
     * @return the description of the argument
     */
    String description();

    /**
     * Indicates whether the argument is mandatory or optional.
     * Defaults to true, meaning the argument is required.
     *
     * @return true if the argument is required, false if it is optional
     */
    boolean required() default true;
}
