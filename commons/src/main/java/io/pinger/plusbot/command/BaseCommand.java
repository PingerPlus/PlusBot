package io.pinger.plusbot.command;

import io.pinger.plusbot.annotation.Require;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Builder
@Data
public class BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger(BaseCommand.class);

    private final Object parent; // The parent object of this command
    private final String name;
    private final String description;
    private final List<OptionData> optionData = new ArrayList<>();
    private final List<SubcommandData> subcommands = new ArrayList<>();

    private DefaultMemberPermissions defaultPermissions;

    public BaseCommand defaultPermissions(Require require) {
        this.defaultPermissions = DefaultMemberPermissions.enabledFor(require != null ? require.permissions() : Permission.EMPTY_PERMISSIONS);
        return this;
    }

    public BaseCommand addSubcommands(SubcommandData... subcommands) {
        this.subcommands.addAll(Arrays.asList(subcommands));
        return this;
    }

    public BaseCommand addOption(OptionType type, String value, String description, boolean required) {
        this.optionData.add(new OptionData(type, value, description, required, false));
        return this;
    }

    public void createCommand(Guild guild) {
        if (this.name == null || this.description == null) {
            BaseCommand.LOGGER.error("Command name and description must be different than null!");
            return;
        }

        guild.upsertCommand(this.name, this.description)
            .addSubcommands(this.subcommands)
            .addOptions(this.optionData)
            .setDefaultPermissions(this.defaultPermissions)
            .queue();
    }

    public void createCommand(JDA jda) {
        if (this.name == null || this.description == null) {
            BaseCommand.LOGGER.error("Command name and description must be different than null!");
            return;
        }

        jda.upsertCommand(this.name, this.description)
            .addSubcommands(this.subcommands)
            .addOptions(this.optionData)
            .setDefaultPermissions(this.defaultPermissions)
            .queue();
    }

    public void editCommand(Command target) {
        if (this.name == null || this.description == null) {
            BaseCommand.LOGGER.error("Command name and description must be different than null!");
            return;
        }

        target.editCommand().setDescription(this.description)
            .setDefaultPermissions(this.defaultPermissions)
            .addSubcommands(this.subcommands)
            .addOptions(this.optionData)
            .queue();
    }
}
