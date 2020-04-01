package com.electro2560.dev.flowerpower;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.electro2560.dev.flowerpower.listeners.FlowerListener;
import com.electro2560.dev.flowerpower.updater.UpdateListener;
import com.electro2560.dev.flowerpower.utils.Utils;

public class FlowerPower extends JavaPlugin{

	private PluginManager pm = Bukkit.getServer().getPluginManager();
	
	public static boolean usePermissions = true;
	
	private static FlowerPower instance;
	
	public void onEnable(){
		instance = this;
		
		saveDefaultConfig();
		
		Utils.loadRecepies();
		
		usePermissions = getConfig().getBoolean("usePermissions", true);
		
		pm.registerEvents(new FlowerListener(), instance);
		pm.registerEvents(new UpdateListener(instance), instance);
		
		if(getConfig().getBoolean("useMetrics", true)) Utils.startMetrics();
	}
	
	public void onDisable(){
		instance = null;
	}
	
	public static FlowerPower get(){
		return instance;
	}
	
}
