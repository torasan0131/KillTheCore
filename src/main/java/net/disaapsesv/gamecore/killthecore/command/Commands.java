package net.disaapsesv.gamecore.killthecore.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.disaapsesv.gamecore.killthecore.Config;
import net.disaapsesv.gamecore.killthecore.mysql.MySQL;

public class Commands
  implements CommandExecutor
{
  private static final String perm = ChatColor.RED + "You are not allowed to do this.";

  private void send(CommandSender sender)
  {
    sender.sendMessage(ChatColor.RED + "Usage: /mysql <reload/rl>");
    sender.sendMessage(ChatColor.RED + "Usage: /mysql <connect/disconnect/reconnect>");
  }

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (cmd.getName().equalsIgnoreCase("mysql"))
    {
      if (((sender instanceof Player)) && (!sender.hasPermission("mysql.admin")))
      {
        sender.sendMessage(perm);
        return true;
      }
      if (args.length == 0)
      {
        send(sender);
      }
      else if (args.length == 1)
      {
        String s = args[0];
        if ((s.equalsIgnoreCase("Reload")) || (s.equalsIgnoreCase("Rl")))
        {
          Config.reload();
          sender.sendMessage(ChatColor.AQUA + "MySQL config reloaded.");
        }
        else if (s.equalsIgnoreCase("Connect"))
        {
          sender.sendMessage(ChatColor.GREEN + "MySQL is trying to connect. Check the console for more info.");
          MySQL.connect();
        }
        else if (s.equalsIgnoreCase("Disconnect"))
        {
          sender.sendMessage(ChatColor.GREEN + "MySQL is trying to disconnect. Check the console for more info.");
          MySQL.disconnect();
        }
        else if (s.equalsIgnoreCase("Reconnect"))
        {
          sender.sendMessage(ChatColor.GREEN + "MySQL is trying to reconnect. Check the console for more info.");
          MySQL.reconnect();
        }
        else
        {
          send(sender);
        }
      }
      else
      {
        send(sender);
      }
    }
    return false;
  }
}
