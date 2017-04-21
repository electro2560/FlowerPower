package com.electro2560.dev.FlowerPower.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.electro2560.dev.FlowerPower.FlowerPower;

public class PowerUtils {
	
	public static void shootFireball(Player player){
		Fireball f = player.launchProjectile(Fireball.class);
		f.setVelocity(f.getVelocity().multiply(2D));
	}
	
	public static void shootSnowball(Player player){
		Snowball s = player.launchProjectile(Snowball.class);
		
		//Snoot twice as fast
		s.setVelocity(s.getVelocity().multiply(2D));
	}
	
	public static void shootSmallFireball(Player player){

		SmallFireball f = player.launchProjectile(SmallFireball.class);
		f.setVelocity(f.getVelocity().multiply(2D));
	}

	@SuppressWarnings("deprecation")
	public static void turnToPotato(final Player player){
		final ItemStack potato = new ItemStack(Material.getMaterial(392), 1);
		final Item item = Bukkit.getServer().getWorld(player.getWorld().getName()).dropItem(player.getLocation(), potato);
		
		item.setPickupDelay(320);
		
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			p.hidePlayer(player);
		}
		
		player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 320, 1));
		
		player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD +  "You have been turned into a potato!");
		
		new BukkitRunnable(){
			@Override
			public void run(){
				
				item.remove();
				
				for (Player p : Bukkit.getServer().getOnlinePlayers()) p.showPlayer(player);
			}
			
		}.runTaskLater(FlowerPower.get(), 300); //1200 == 60 seconds
		
	}
}
