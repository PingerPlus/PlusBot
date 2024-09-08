package io.pinger.plusbot.command;

import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public enum CommandArgument {

    TEXT(String.class, OptionType.STRING),
    BOOLEAN(boolean.class, OptionType.BOOLEAN),
    INTEGER(int.class, OptionType.INTEGER),
    DOUBLE(double.class, OptionType.NUMBER),
    LONG(long.class, OptionType.INTEGER),
    GUILD_CHANNEL(GuildChannel.class, OptionType.CHANNEL),
    TEXT_CHANNEL(TextChannel.class, OptionType.CHANNEL),
    MESSAGE_CHANNEL(GuildMessageChannel.class, OptionType.CHANNEL),
    VOICE_CHANNEL(VoiceChannel.class, OptionType.CHANNEL),
    MENTIONABLE(IMentionable.class, OptionType.MENTIONABLE),
    MEMBER(Member.class, OptionType.USER),
    USER(User.class, OptionType.USER),
    ROLE(Role.class, OptionType.ROLE),
    ATTACHMENT(Message.Attachment.class, OptionType.ATTACHMENT);

    private final Class<?> parameter;
    private final OptionType type;

    CommandArgument(Class<?> parameter, OptionType type) {
        this.parameter = parameter;
        this.type = type;
    }

    public static OptionType getOption(Class<?> parameter) {
        for (final CommandArgument argument : CommandArgument.values()) {
            if (parameter.isAssignableFrom(argument.parameter)) {
                return argument.type;
            }
        }

        throw new IllegalStateException("Couldn't find an option type for type " + parameter.getName());
    }
}
