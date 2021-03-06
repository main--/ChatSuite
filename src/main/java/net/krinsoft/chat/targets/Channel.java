package net.krinsoft.chat.targets;

import java.util.ArrayList;
import java.util.List;
import net.krinsoft.chat.ChatCore;
import net.krinsoft.chat.interfaces.Target;
import net.krinsoft.chat.util.ColoredMessage;
import org.bukkit.entity.Player;

/**
 *
 * @author krinsdeath (Jeff Wardian)
 */
public class Channel implements Target {

    private List<String> occupants = new ArrayList<String>();
    private ChatCore plugin;
    private String name;
    private String type;
    private List<String> invites = new ArrayList<String>();

    public Channel(ChatCore plugin, String name, String type) {
        this.plugin = plugin;
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String toNode() {
        return this.name.replaceAll("\\s", "_").toLowerCase();
    }

    public List<String> getOccupants() {
        return occupants;
    }

    public void addPlayer(String player) {
        occupants.add(player);
    }

    public void removePlayer(String player) {
        occupants.remove(player);
    }

    public void message(String sender, String message) {
        for (String player : occupants) {
            Player p = plugin.getServer().getPlayer(player);
            p.sendMessage(message);
        }
    }

    public boolean contains(String player) {
        return occupants.contains(player);
    }

    public boolean isPrivate() {
        return this.type.equalsIgnoreCase("private");
    }

    public void invite(String inviter, String player) {
        if (occupants.contains(inviter) && !occupants.contains(player)) {
            ColoredMessage msg = new ColoredMessage(plugin.getLocaleManager().getMessage(plugin.getPlayerManager().getPlayer(player).getLocale(), "channel_invite"));
            for (String line : msg.getContents()) {
                plugin.getServer().getPlayer(player).sendMessage(line.replaceAll("%c", this.name));
            }
            invites.add(player);
        }
    }

    public boolean hasInvite(String player) {
        return (invites.contains(player) || occupants.contains(player));
    }
}
