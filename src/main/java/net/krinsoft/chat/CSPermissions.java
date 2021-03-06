package net.krinsoft.chat;

import com.pneumaticraft.commandhandler.PermissionsInterface;
import java.util.List;
import net.krinsoft.chat.targets.Channel;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

/**
 *
 * @author krinsdeath
 */
public class CSPermissions implements PermissionsInterface {
    private ChatCore plugin;

    CSPermissions(ChatCore aThis) {
        plugin = aThis;
    }

    public boolean canChatHere(CommandSender cs, Channel chan) {
        String node = "chatsuite.access." + chan.toNode();
        if (cs instanceof ConsoleCommandSender) return true;
        if (cs.hasPermission(node)) {
            return true;
        } else if (cs.isPermissionSet(node) && !cs.hasPermission(node)) {
            return false;
        } else if (cs.hasPermission("chatsuite.access.*") && !cs.isPermissionSet(node)) {
            return true;
        }
        return false;
    }

    public boolean canCreateChannel(CommandSender cs) {
        return cs.hasPermission("chatsuite.create");
    }

    @Override
    public boolean hasPermission(CommandSender cs, String node, boolean bln) {
        if (cs instanceof ConsoleCommandSender) return true;
        if (node == null) return false;
        if (cs.hasPermission(node)) {
            return true;
        } else if (cs.isPermissionSet(node) && !cs.hasPermission(node)) {
            return false;
        } else if (cs.hasPermission("chatsuite.*") && !cs.isPermissionSet(node)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasAnyPermission(CommandSender cs, List<String> list, boolean bln) {
        for (String node : list) {
            if (cs.hasPermission(node)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasAllPermission(CommandSender cs, List<String> list, boolean bln) {
        for (String node : list) {
            if (!cs.hasPermission(node)) {
                return false;
            }
        }
        return true;
    }

}
