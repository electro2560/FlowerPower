package com.electro2560.dev.flowerpower.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;
import org.mcstats.MetricsLite;

import com.electro2560.dev.flowerpower.FlowerPower;
import com.electro2560.dev.flowerpower.bstats.Metrics;

@SuppressWarnings("deprecation")
public class Utils {
	
	public static ItemStack poppy = new ItemStack(Material.POPPY);
	public static ItemStack redTulip = new ItemStack(Material.RED_TULIP);
	public static ItemStack blueOrchid = new ItemStack(Material.BLUE_ORCHID);
	public static ItemStack dandelion = new ItemStack(Material.DANDELION);
	public static ItemStack pinkTulip = new ItemStack(Material.PINK_TULIP);
	public static ItemStack oxeyeDaisy = new ItemStack(Material.OXEYE_DAISY);
	public static ItemStack allium = new ItemStack(Material.ALLIUM);
	public static ItemStack orangeTulip = new ItemStack(Material.ORANGE_TULIP);
	public static ItemStack azureBluet = new ItemStack(Material.AZURE_BLUET);
	public static ItemStack whiteTulip = new ItemStack(Material.WHITE_TULIP);
	
	public static Location getTargetBlock(Player player){
		Block targetBlock = player.getTargetBlock(null, 100); 
		
		return targetBlock.getLocation();
	}
	
	public static ArrayList<Player> getNearbyPlayers(Location l, int radius, Player skip){
		int chunkRadius = radius < 16 ? 1 : (radius - (radius % 16)) / 16;
		HashSet<Entity> radiusEntites = new HashSet<Entity>();
		for (int chX = 0 - chunkRadius; chX <= chunkRadius; chX++) {
			for(int chZ = 0 - chunkRadius; chZ <= chunkRadius; chZ++){
				int x = (int) l.getX(), y = (int) l.getY(), z = (int) l.getZ();
				for(Entity e : new Location(l.getWorld(), x + (chX * 16), y, z + (chZ * 16)).getChunk().getEntities()){
					if(e.getLocation().distance(l) <= radius && e.getLocation().getBlock() != l.getBlock()){
						radiusEntites.add(e);
					}
						
				}
			}
		}
		
		Entity[] list = radiusEntites.toArray(new Entity[radiusEntites.size()]);
		
		ArrayList<Player> players = new ArrayList<Player>();
		
		for(Entity entity : list){
			if(entity instanceof Player){
				Player player = (Player) entity;
				if(player != skip) players.add(player);
			}
		}
		
		return players;
	}
	
	public static void knockbackPlayer(Player player, double strength){
		double pitch = ((player.getLocation().getPitch() + 90) * Math.PI) / 180;
		double yaw = ((player.getLocation().getYaw() + 90) * Math.PI) / 180;
		
		double x = Math.sin(pitch) * Math.cos(yaw);
		double y = Math.sin(pitch) * Math.sin(yaw);
		double z = Math.cos(pitch);
		
		Vector vector = new Vector(x * -1, z * -1, y * -1);
		
		player.setVelocity(vector.multiply(strength));
	}
	
	public static void pullPlayer(Player player, double strength){
		double pitch = ((player.getLocation().getPitch() + 90) * Math.PI) / 180;
		double yaw = ((player.getLocation().getYaw() + 90) * Math.PI) / 180;
		
		double x = Math.sin(pitch) * Math.cos(yaw);
		double y = Math.sin(pitch) * Math.sin(yaw);
		double z = Math.cos(pitch);
		
		Vector vector = new Vector(x, z, y);
		
		player.setVelocity(vector.multiply(strength));
	}
	
	public static void shootBlock(Material material, Player player, Byte data, Double forceMultiplyer){
		FallingBlock block = player.getWorld().spawnFallingBlock(player.getLocation(), material, (byte) data);
		
		block.setDropItem(false);
		block.setVelocity(player.getLocation().getDirection().multiply(forceMultiplyer));
	}

	public static ItemStack decrementStack(ItemStack is, int decrement){
		is.setAmount(is.getAmount() - decrement);
		
		return is;
	}
	
	public static void loadRecepies(){
		ItemMeta meta;
		
		meta = poppy.getItemMeta();
		meta.setDisplayName(color("&c&lFireball Flower"));
		meta.setLore(color(Arrays.asList("&5Power: Shoot fireballs", "&bAmmo: Firecharge + Flint & Steel")));
		poppy.setItemMeta(meta);
		ShapedRecipe poppyRec = new ShapedRecipe(poppy);
		poppyRec.shape("ldl","cfc","dcd");
		poppyRec.setIngredient('l', Material.LAVA_BUCKET);
		poppyRec.setIngredient('d', Material.DIAMOND);
		poppyRec.setIngredient('c', Material.FIRE_CHARGE);
		poppyRec.setIngredient('f', new MaterialData(Material.POPPY));
		Bukkit.getServer().addRecipe(poppyRec);
		
		
		meta = redTulip.getItemMeta();
		meta.setDisplayName(color("&c&lFire Flower"));
		meta.setLore(color(Arrays.asList("&5Power: Shoot fire", "&bAmmo: Flint & Steel")));
		redTulip.setItemMeta(meta);
		ShapedRecipe redTulipRec = new ShapedRecipe(redTulip);
		redTulipRec.shape("gbg","bfb","gbg");
		redTulipRec.setIngredient('g', Material.GOLD_INGOT);
		redTulipRec.setIngredient('b', Material.BLAZE_ROD);
		redTulipRec.setIngredient('f', Material.RED_TULIP);
		Bukkit.getServer().addRecipe(redTulipRec);
		
		
		meta = blueOrchid.getItemMeta();
		meta.setDisplayName(color("&b&lIce Flower"));
		meta.setLore(color(Arrays.asList("&5Power: Shoot blocks of ice", "&bAmmo: Ice")));
		blueOrchid.setItemMeta(meta);
		ShapedRecipe blueOrchidRec = new ShapedRecipe(blueOrchid);
		blueOrchidRec.shape("wlw","dfd","ili");
		blueOrchidRec.setIngredient('w', Material.WATER_BUCKET);
		blueOrchidRec.setIngredient('l', Material.LAPIS_BLOCK);
		blueOrchidRec.setIngredient('d', Material.DIAMOND);
		blueOrchidRec.setIngredient('i', Material.ICE);
		blueOrchidRec.setIngredient('f', Material.BLUE_ORCHID);
		Bukkit.getServer().addRecipe(blueOrchidRec);
		
		
		meta = dandelion.getItemMeta();
		meta.setDisplayName(color("&6&lKnockback Flower"));
		meta.setLore(color(Arrays.asList("&5Power: Knockback players", "&bAmmo: Tripwire hook")));
		dandelion.setItemMeta(meta);
		ShapedRecipe dandelionRec = new ShapedRecipe(dandelion);
		dandelionRec.shape("gpg","pfp","ibi");
		dandelionRec.setIngredient('g', Material.GOLD_INGOT);
		dandelionRec.setIngredient('p', Material.FISHING_ROD);
		dandelionRec.setIngredient('i', Material.IRON_BLOCK);
		dandelionRec.setIngredient('b', Material.BOW);
		dandelionRec.setIngredient('f', Material.DANDELION);
		Bukkit.getServer().addRecipe(dandelionRec);
		
		
		meta = pinkTulip.getItemMeta();
		meta.setDisplayName(color("&b&lPull Flower"));
		meta.setLore(color(Arrays.asList("&5Power: Pull players", "&bAmmo: Tripwire hook")));
		pinkTulip.setItemMeta(meta);
		ShapedRecipe pinkTulipRec = new ShapedRecipe(pinkTulip);
		pinkTulipRec.shape("dpd","pfp","ibi");
		pinkTulipRec.setIngredient('d', Material.DIAMOND);
		pinkTulipRec.setIngredient('p', Material.FISHING_ROD);
		pinkTulipRec.setIngredient('i', Material.IRON_BLOCK);
		pinkTulipRec.setIngredient('b', Material.BOW);
		pinkTulipRec.setIngredient('f', Material.PINK_TULIP);
		Bukkit.getServer().addRecipe(pinkTulipRec);
		
		
		meta = oxeyeDaisy.getItemMeta();
		meta.setDisplayName(color("&5&lConfusion Flower"));
		meta.setLore(color(Arrays.asList("&5Power: Confuse players", "&bAmmo: Spider eye")));
		oxeyeDaisy.setItemMeta(meta);
		ShapedRecipe oxeyeDaisyRec = new ShapedRecipe(oxeyeDaisy);
		oxeyeDaisyRec.shape("did","pfp","eie");
		oxeyeDaisyRec.setIngredient('d', Material.DIAMOND);
		oxeyeDaisyRec.setIngredient('i', Material.INK_SAC);
		oxeyeDaisyRec.setIngredient('p', Material.PUFFERFISH);
		oxeyeDaisyRec.setIngredient('e', Material.SPIDER_EYE);
		oxeyeDaisyRec.setIngredient('f', Material.OXEYE_DAISY);
		Bukkit.getServer().addRecipe(oxeyeDaisyRec);
		
		
		meta = allium.getItemMeta();
		meta.setDisplayName(color("&d&lRegen Flower"));
		meta.setLore(color(Arrays.asList("&5Power: Regen players", "&bAmmo: 4 gold ingots")));
		allium.setItemMeta(meta);
		ShapedRecipe alliumRec = new ShapedRecipe(allium);
		alliumRec.shape("rdr","gfg","rdr");
		alliumRec.setIngredient('r', Material.REDSTONE_BLOCK);
		alliumRec.setIngredient('d', Material.DIAMOND);
		alliumRec.setIngredient('g', Material.GOLDEN_APPLE);
		alliumRec.setIngredient('f', Material.ALLIUM);
		Bukkit.getServer().addRecipe(alliumRec);
		
		
		meta = orangeTulip.getItemMeta();
		meta.setDisplayName(color("&6&lPotato Flower"));
		meta.setLore(color(Arrays.asList("&5Power: Turn players into potatoes", "&bAmmo: poisoned potato")));
		orangeTulip.setItemMeta(meta);
		ShapedRecipe orangeTulipRec = new ShapedRecipe(orangeTulip);
		orangeTulipRec.shape("pdp","dfd","pdp");
		orangeTulipRec.setIngredient('d', Material.DIAMOND);
		orangeTulipRec.setIngredient('p', Material.POISONOUS_POTATO);
		orangeTulipRec.setIngredient('f', Material.ORANGE_TULIP);
		Bukkit.getServer().addRecipe(orangeTulipRec);
		
		
		meta = azureBluet.getItemMeta();
		meta.setDisplayName(color("&b&lSnowball Flower"));
		meta.setLore(color(Arrays.asList("&5Power: Shoot snowballs", "&bAmmo: snowball")));
		azureBluet.setItemMeta(meta);
		ShapedRecipe azureBluetRec = new ShapedRecipe(azureBluet);
		azureBluetRec.shape("dsd","sfs","wsw");
		azureBluetRec.setIngredient('d', Material.DIAMOND);
		azureBluetRec.setIngredient('s', Material.SNOW_BLOCK);
		azureBluetRec.setIngredient('w', Material.WATER_BUCKET);
		azureBluetRec.setIngredient('f', Material.AZURE_BLUET);
		Bukkit.getServer().addRecipe(azureBluetRec);
		
		
		meta = whiteTulip.getItemMeta();
		meta.setDisplayName(color("&f&lWeb Flower"));
		meta.setLore(color(Arrays.asList("&5Power: Shoot webs", "&bAmmo: string")));
		whiteTulip.setItemMeta(meta);
		ShapedRecipe whiteTulipRec = new ShapedRecipe(whiteTulip);
		whiteTulipRec.shape("dsd","sfs","dsd");
		whiteTulipRec.setIngredient('d', Material.DIAMOND);
		whiteTulipRec.setIngredient('s', Material.STRING);
		whiteTulipRec.setIngredient('f', Material.WHITE_TULIP);
		Bukkit.getServer().addRecipe(whiteTulipRec);
	}
	
	public static boolean isCheckForUpdates() {
		return FlowerPower.get().getConfig().getBoolean("checkForUpdates", true);
	}

	public static String getVersion() {
		return FlowerPower.get().getDescription().getVersion();
	}
	
	public static void startMetrics(){
		try {
	        MetricsLite metrics = new MetricsLite(FlowerPower.get());
	        metrics.start();
	    } catch (IOException e) {}
		
		@SuppressWarnings("unused")
		Metrics metrics = new Metrics(FlowerPower.get());
	}
	
	public static String color(String str) {
		return ChatColor.translateAlternateColorCodes('&', str);
	}
	
	public static List<String> color(List<String> strs){
		List<String> colored = new ArrayList<>();
		for(String s : strs) colored.add(color(s));
		return colored;
	}
	
}
