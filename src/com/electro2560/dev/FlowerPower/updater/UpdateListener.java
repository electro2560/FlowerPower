package com.electro2560.dev.FlowerPower.updater;

import java.beans.ConstructorProperties;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.electro2560.dev.FlowerPower.FlowerPower;
import com.electro2560.dev.FlowerPower.utils.Perms;
import com.electro2560.dev.FlowerPower.utils.Utils;

public class UpdateListener implements Listener {
	private final FlowerPower plugin;

	@ConstructorProperties({ "plugin" })
	public UpdateListener(FlowerPower plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (e.getPlayer().hasPermission(Perms.canCheckForUpdates) && Utils.isCheckForUpdates()) {
			UpdateUtil.sendUpdateMessage(e.getPlayer(), plugin);
		}
	}
}
