package com.electro2560.dev.flowerpower.updater;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.electro2560.dev.flowerpower.FlowerPower;
import com.electro2560.dev.flowerpower.utils.Perms;
import com.electro2560.dev.flowerpower.utils.Utils;

public class UpdateListener implements Listener {
	private final FlowerPower plugin;

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
