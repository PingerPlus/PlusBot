package io.pinger.plusbot.command.option;

import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public abstract class Option<T> {

    /**
     * This method returns the default value of this option
     * if the mapping couldn't be finished for some reason.
     *
     * @return the default value
     */

    public T getDefaultValue() {
        return null;
    }

    /**
     * This method maps the provided {@link OptionMapping} mapping
     * to the type of the specified classifier.
     *
     * @param classifier the classifier
     * @param mapping the mapping
     * @return the mapped value
     */

    public abstract T map(Class<?> classifier, OptionMapping mapping);

}
