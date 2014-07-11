package com.homespawnwarp.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.homespawnwarp.plugin.HomeSpawnWarp;
import com.homespawnwarp.tool.PermissionAgent;
import com.homespawnwarp.tool.Tools;

public abstract class AbstractCommand implements CommandExecutor {

	protected HomeSpawnWarp plugin;

	protected String commandPermission;
	protected boolean isDefaultPermitted;

	abstract public String getName();

	abstract boolean doCommand(Player player, CommandSender sender,
			Command cmd, String commandLabel, String[] args);

	public AbstractCommand(final HomeSpawnWarp plugin,
			final String commandPermission, boolean isDefaultPermitted) {
		this.plugin = plugin;
		this.commandPermission = commandPermission;
		this.isDefaultPermitted = isDefaultPermitted;
		
		plugin.getCommand(getName()).setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player player = null;
		if (this instanceof ConsoleSendable) {
			doCommand(player, sender, cmd, commandLabel, args);
		} else {
			if (sender instanceof Player) {

				player = (Player) sender;

				if (PermissionAgent.checkPerm(sender, commandPermission,
						isDefaultPermitted, true)) {
					// if (MoneyMachine.checkMoney(player, price)) {
					if (doCommand(player, sender, cmd, commandLabel, args)) {
						// @returns wether the command is done or not
					}
					/*
					 * } else { //Not using this no more
					 * player.sendMessage(Tools.getMessage("not-enough-money"));
					 * }
					 */

				}
			} else {
				sender.sendMessage(Tools.getMessage("cannot-perform"));
			}
		}
		return false;
	}

	protected boolean containsIllegalChars(final String s,
			final CommandSender sender) {
		if (s.indexOf(".") != -1) {
			sender.sendMessage(Tools.getMessage("cannot-use-dot"));
			return true;
		}
		return false;
	}
}
