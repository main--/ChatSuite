package net.krinsoft.chat.commands;

import java.util.Arrays;
import java.util.List;
import net.krinsoft.chat.ChatCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

/**
 *
 * @author krinsdeath (Jeff Wardian)
 */
public class ChannelCommand extends ChatSuiteCommand {
    private List<String> reserved = Arrays.asList("create", "invite", "join", "leave");

    public ChannelCommand(ChatCore plugin) {
        super(plugin);
        this.plugin = (ChatCore) plugin;
        this.setName("chatsuite channel");
        this.setCommandUsage("/cs help channel");
        this.addCommandExample("/csc join ? -- show Join command usage and examples");
        this.addCommandExample("/csc leave ? -- show Leave command usage and examples");
        this.addCommandExample("/csc create ? -- show Create command usage and examples");
        this.addCommandExample("/csc invite ? -- show Invite command usage and examples");
        this.addCommandExample("/csc say ? -- show Say command usage and examples");
        this.setArgRange(1, 3);
        this.addKey("chatsuite channel");
        this.addKey("cs channel");
        this.addKey("c channel");
        this.addKey("csc");
        this.setPermission("chatsuite.channel", "Allows the user to chat in channels.", PermissionDefault.TRUE);
    }

    @Override
    public void runCommand(CommandSender cs, List<String> args) {
        if (!(cs instanceof Player)) { return; }
    }

}
