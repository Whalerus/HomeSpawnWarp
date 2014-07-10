package com.homespawnwarp.tool;

import org.bukkit.command.CommandSender;

public class PermissionAgent {

	public static boolean hasPerm(final CommandSender sender, final String permission,
			final boolean isDefaultPermitted) {
		return hasPerm(sender, permission, isDefaultPermitted, true);
	}

	public static boolean hasPerm(final CommandSender sender, final String permission,
			final boolean isDefaultPermitted, final boolean sendMessage) {
		if (isDefaultPermitted) {
			if (sender.hasPermission(permission)
					|| !sender.isPermissionSet(permission)) {
				return true;
			} else {
				if (sendMessage) {
					sender.sendMessage(Tools.getMessage("no-permission"));
				}
				return false;
			}
		} else {
			if (sender.hasPermission(permission)
					|| (!sender.isPermissionSet(permission) && sender.isOp())) {
				return true;
			} else {
				if (sendMessage) {
					sender.sendMessage(Tools.getMessage("no-permission"));
				}
				return false;
			} // WORKS!!
		}
	}
}
