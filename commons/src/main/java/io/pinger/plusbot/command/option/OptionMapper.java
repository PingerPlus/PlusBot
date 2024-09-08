package io.pinger.plusbot.command.option;

import java.util.HashMap;
import java.util.Map;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class OptionMapper {
    private final Map<Class<?>, Option<?>> bindings = new HashMap<>();

    public OptionMapper() {
        // Primitives
        this.addBinding(String.class, StringOption.INSTANCE);
        this.addBinding(int.class, IntegerOption.INSTANCE);
        this.addBinding(Integer.class, IntegerOption.INSTANCE);
        this.addBinding(long.class, LongOption.INSTANCE);
        this.addBinding(Long.class, LongOption.INSTANCE);
        this.addBinding(double.class, DoubleOption.INSTANCE);
        this.addBinding(Double.class, DoubleOption.INSTANCE);
        this.addBinding(boolean.class, BooleanOption.INSTANCE);
        this.addBinding(Boolean.class, BooleanOption.INSTANCE);

        // JDA stuff
        this.addBinding(GuildChannel.class, GuildChannelOption.INSTANCE);
        this.addBinding(TextChannel.class, TextChannelOption.INSTANCE);
        this.addBinding(GuildMessageChannel.class, GuildMessageChannelOption.INSTANCE);
        this.addBinding(VoiceChannel.class, VoiceChannelOption.INSTANCE);
        this.addBinding(IMentionable.class, MentionableOption.INSTANCE);
        this.addBinding(Member.class, MemberOption.INSTANCE);
        this.addBinding(User.class, UserOption.INSTANCE);
        this.addBinding(Role.class, RoleOption.INSTANCE);
        this.addBinding(Message.Attachment.class, AttachmentOption.INSTANCE);
    }

    /**
     * This method adds a new binding to a specific option.
     *
     * @param classifier the classifier
     * @param option the option returned from the binding
     */

    public void addBinding(Class<?> classifier, Option<?> option) {
        this.bindings.put(classifier, option);
    }

    /**
     * This method returns the option from the specified
     * classifier.
     * <p>
     * If a bind currently doesn't exist for the specified classifier,
     * this method will return null.
     *
     * @param classifier the classifier
     * @return the bind option if it exists, otherwise null
     */

    public Option<?> getOption(Class<?> classifier) {
        return this.bindings.get(classifier);
    }

    public static class StringOption extends Option<String> {
        private static final StringOption INSTANCE = new StringOption();

        @Override
        public String map(Class<?> classifier, OptionMapping mapping) {
            return mapping.getAsString();
        }
    }

    public static class IntegerOption extends Option<Integer> {
        private static final IntegerOption INSTANCE = new IntegerOption();

        @Override
        public Integer getDefaultValue() {
            return 0;
        }

        @Override
        public Integer map(Class<?> classifier, OptionMapping mapping) {
            return mapping.getAsInt();
        }
    }

    public static class LongOption extends Option<Long> {
        private static final LongOption INSTANCE = new LongOption();

        @Override
        public Long getDefaultValue() {
            return 0L;
        }

        @Override
        public Long map(Class<?> classifier, OptionMapping mapping) {
            return mapping.getAsLong();
        }
    }

    public static class BooleanOption extends Option<Boolean> {
        private static final BooleanOption INSTANCE = new BooleanOption();

        @Override
        public Boolean getDefaultValue() {
            return false;
        }

        @Override
        public Boolean map(Class<?> classifier, OptionMapping mapping) {
            return mapping.getAsBoolean();
        }
    }

    public static class DoubleOption extends Option<Double> {
        private static final DoubleOption INSTANCE = new DoubleOption();

        @Override
        public Double getDefaultValue() {
            return 0D;
        }

        @Override
        public Double map(Class<?> classifier, OptionMapping mapping) {
            return mapping.getAsDouble();
        }
    }

    public static class GuildChannelOption extends Option<GuildChannel> {
        private static final GuildChannelOption INSTANCE = new GuildChannelOption();

        @Override
        public GuildChannel map(Class<?> classifier, OptionMapping mapping) {
            return mapping.getAsChannel().asGuildMessageChannel();
        }
    }

    public static class GuildMessageChannelOption extends Option<GuildMessageChannel> {
        private static final GuildChannelOption INSTANCE = new GuildChannelOption();

        @Override
        public GuildMessageChannel map(Class<?> classifier, OptionMapping mapping) {
            return mapping.getAsChannel().asGuildMessageChannel();
        }
    }

    public static class TextChannelOption extends Option<TextChannel> {
        private static final TextChannelOption INSTANCE = new TextChannelOption();

        @Override
        public TextChannel map(Class<?> classifier, OptionMapping mapping) {
            return mapping.getAsChannel().asTextChannel();
        }
    }

    public static class VoiceChannelOption extends Option<VoiceChannel> {
        private static final VoiceChannelOption INSTANCE = new VoiceChannelOption();

        @Override
        public VoiceChannel map(Class<?> classifier, OptionMapping mapping) {
            return mapping.getAsChannel().asVoiceChannel();
        }
    }

    public static class MentionableOption extends Option<IMentionable> {
        private static final MentionableOption INSTANCE = new MentionableOption();

        @Override
        public IMentionable map(Class<?> classifier, OptionMapping mapping) {
            return mapping.getAsMentionable();
        }
    }

    public static class MemberOption extends Option<Member> {
        private static final MemberOption INSTANCE = new MemberOption();

        @Override
        public Member map(Class<?> classifier, OptionMapping mapping) {
            return mapping.getAsMember();
        }
    }

    public static class UserOption extends Option<User> {
        private static final UserOption INSTANCE = new UserOption();

        @Override
        public User map(Class<?> classifier, OptionMapping mapping) {
            return mapping.getAsUser();
        }
    }

    public static class RoleOption extends Option<Role> {
        private static final RoleOption INSTANCE = new RoleOption();

        @Override
        public Role map(Class<?> classifier, OptionMapping mapping) {
            return mapping.getAsRole();
        }
    }

    public static class AttachmentOption extends Option<Message.Attachment> {
        private static final AttachmentOption INSTANCE = new AttachmentOption();

        @Override
        public Message.Attachment map(Class<?> classifier, OptionMapping mapping) {
            return mapping.getAsAttachment();
        }
    }

}
