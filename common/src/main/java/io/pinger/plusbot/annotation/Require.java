package io.pinger.plusbot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.dv8tion.jda.api.Permission;

/**
 * Specifies the required permissions for executing a command.
 * This annotation should be used on types to enforce permission checks.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Require {

    /**
     * List of permissions required to execute the command.
     * Defaults to {@link Permission#EMPTY_PERMISSIONS} if no permissions are specified.
     *
     * @return the required permissions to execute the command
     */
    Permission[] permissions() default {};
}
