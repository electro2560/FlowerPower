package com.electro2560.dev.FlowerPower.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.bukkit.Bukkit;
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

import com.electro2560.dev.FlowerPower.FlowerPower;
import com.electro2560.dev.FlowerPower.bstats.Metrics;

@SuppressWarnings("deprecation")
public class Utils {
	
	
	public static ItemStack poppy = new ItemStack(Material.getMaterial(38), 1, (byte) 0);
	public static ItemMeta poppyMeta = poppy.getItemMeta();
	
	public static ItemStack redTulip = new ItemStack(Material.getMaterial(38), 1, (byte) 4);
	public static ItemMeta redTulipMeta = redTulip.getItemMeta();
	
	public static ItemStack blueOrchid = new ItemStack(Material.getMaterial(38), 1, (byte) 1);
	public static ItemMeta blueOrchidMeta = blueOrchid.getItemMeta();
	
	public static ItemStack dandelion = new ItemStack(Material.getMaterial(37), 1, (byte) 0);
	public static ItemMeta dandelionMeta = dandelion.getItemMeta();
	
	public static ItemStack pinkTulip = new ItemStack(Material.getMaterial(38), 1, (byte) 7);
	public static ItemMeta pinkTulipMeta = pinkTulip.getItemMeta();
	
	public static ItemStack oxeyeDaisy = new ItemStack(Material.getMaterial(38), 1, (byte) 8);
	public static ItemMeta oxeyeDaisyMeta = oxeyeDaisy.getItemMeta();
	
	public static ItemStack allium = new ItemStack(Material.getMaterial(38), 1, (byte) 2);
	public static ItemMeta alliumMeta = allium.getItemMeta();
	
	public static ItemStack orangeTulip = new ItemStack(Material.getMaterial(38), 1, (byte) 5);
	public static ItemMeta orangeTulipMeta = orangeTulip.getItemMeta();
	
	public static ItemStack azureBluet = new ItemStack(Material.getMaterial(38), 1, (byte) 3);
	public static ItemMeta azureBluetMeta = azureBluet.getItemMeta();
	
	public static ItemStack whiteTulip = new ItemStack(Material.getMaterial(38), 1, (byte) 6);
	public static ItemMeta whiteTulipMeta = whiteTulip.getItemMeta();
	
	public static Location getTargetBlock(Player player){
		Block targetBlock = player.getTargetBlock((HashSet<Byte>) null, 100); 
		
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
		poppyMeta.setDisplayName("§c§lFireball Flower");
		poppyMeta.setLore(Arrays.asList("§5Power: Shoot fireballs", "§bAmmo: Firecharge + Flint & Steel"));
		poppy.setItemMeta(poppyMeta);
		ShapedRecipe poppyRec = new ShapedRecipe(poppy);
		poppyRec.shape("ldl","cfc","dcd");
		poppyRec.setIngredient('l', Material.LAVA_BUCKET);
		poppyRec.setIngredient('d', Material.DIAMOND);
		poppyRec.setIngredient('c', Material.FIREBALL);
		poppyRec.setIngredient('f', new MaterialData(Material.getMaterial(38), (byte) 0));
		Bukkit.getServer().addRecipe(poppyRec);
		
		
		redTulipMeta.setDisplayName("§c§lFire Flower");
		redTulipMeta.setLore(Arrays.asList("§5Power: Shoot fire", "§bAmmo: Flint & Steel"));
		redTulip.setItemMeta(redTulipMeta);
		ShapedRecipe redTulipRec = new ShapedRecipe(redTulip);
		redTulipRec.shape("gbg","bfb","gbg");
		redTulipRec.setIngredient('g', Material.GOLD_INGOT);
		redTulipRec.setIngredient('b', Material.BLAZE_ROD);
		redTulipRec.setIngredient('f', new MaterialData(Material.getMaterial(38), (byte) 4));
		Bukkit.getServer().addRecipe(redTulipRec);
		
		
		blueOrchidMeta.setDisplayName("§b§lIce Flower");
		blueOrchidMeta.setLore(Arrays.asList("§5Power: Shoot blocks of ice", "§bAmmo: Ice"));
		blueOrchid.setItemMeta(blueOrchidMeta);
		ShapedRecipe blueOrchidRec = new ShapedRecipe(blueOrchid);
		blueOrchidRec.shape("wlw","dfd","ili");
		blueOrchidRec.setIngredient('w', Material.WATER_BUCKET);
		blueOrchidRec.setIngredient('l', Material.LAPIS_BLOCK);
		blueOrchidRec.setIngredient('d', Material.DIAMOND);
		blueOrchidRec.setIngredient('i', Material.ICE);
		blueOrchidRec.setIngredient('f', new MaterialData(Material.getMaterial(38), (byte) 1));
		Bukkit.getServer().addRecipe(blueOrchidRec);
		
		
		dandelionMeta.setDisplayName("§6§lKnockback Flower");
		dandelionMeta.setLore(Arrays.asList("§5Power: Knockback players", "§bAmmo: Tripwire hook"));
		dandelion.setItemMeta(dandelionMeta);
		ShapedRecipe dandelionRec = new ShapedRecipe(dandelion);
		dandelionRec.shape("gpg","pfp","ibi");
		dandelionRec.setIngredient('g', Material.GOLD_INGOT);
		dandelionRec.setIngredient('p', Material.FISHING_ROD);
		dandelionRec.setIngredient('i', Material.IRON_BLOCK);
		dandelionRec.setIngredient('b', Material.BOW);
		dandelionRec.setIngredient('f', new MaterialData(Material.getMaterial(37), (byte) 0));
		Bukkit.getServer().addRecipe(dandelionRec);
		
		
		pinkTulipMeta.setDisplayName("§b§lPull Flower");
		pinkTulipMeta.setLore(Arrays.asList("§5Power: Pull players", "§bAmmo: Tripwire hook"));
		pinkTulip.setItemMeta(pinkTulipMeta);
		ShapedRecipe pinkTulipRec = new ShapedRecipe(pinkTulip);
		pinkTulipRec.shape("dpd","pfp","ibi");
		pinkTulipRec.setIngredient('d', Material.DIAMOND);
		pinkTulipRec.setIngredient('p', Material.FISHING_ROD);
		pinkTulipRec.setIngredient('i', Material.IRON_BLOCK);
		pinkTulipRec.setIngredient('b', Material.BOW);
		pinkTulipRec.setIngredient('f', new MaterialData(Material.getMaterial(38), (byte) 7));
		Bukkit.getServer().addRecipe(pinkTulipRec);
		
		
		oxeyeDaisyMeta.setDisplayName("§5§lConfusion Flower");
		oxeyeDaisyMeta.setLore(Arrays.asList("§5Power: Confuse players", "§bAmmo: Spider eye"));
		oxeyeDaisy.setItemMeta(oxeyeDaisyMeta);
		ShapedRecipe oxeyeDaisyRec = new ShapedRecipe(oxeyeDaisy);
		oxeyeDaisyRec.shape("did","pfp","eie");
		oxeyeDaisyRec.setIngredient('d', Material.DIAMOND);
		oxeyeDaisyRec.setIngredient('i', Material.INK_SACK);
		oxeyeDaisyRec.setIngredient('p', new MaterialData(Material.getMaterial(349), (byte) 3));
		oxeyeDaisyRec.setIngredient('e', Material.SPIDER_EYE);
		oxeyeDaisyRec.setIngredient('f', new MaterialData(Material.getMaterial(38), (byte) 8));
		Bukkit.getServer().addRecipe(oxeyeDaisyRec);
		
		
		alliumMeta.setDisplayName("§d§lRegen Flower");
		alliumMeta.setLore(Arrays.asList("§5Power: Regen players", "§bAmmo: 4 gold ingots"));
		allium.setItemMeta(alliumMeta);
		ShapedRecipe alliumRec = new ShapedRecipe(allium);
		alliumRec.shape("rdr","gfg","rdr");
		alliumRec.setIngredient('r', Material.REDSTONE_BLOCK);
		alliumRec.setIngredient('d', Material.DIAMOND);
		alliumRec.setIngredient('g', Material.GOLDEN_APPLE);
		alliumRec.setIngredient('f', new MaterialData(Material.getMaterial(38), (byte) 2));
		Bukkit.getServer().addRecipe(alliumRec);
		
		
		orangeTulipMeta.setDisplayName("§6§lPotato Flower");
		orangeTulipMeta.setLore(Arrays.asList("§5Power: Turn players into potatoes", "§bAmmo: poisoned potato"));
		orangeTulip.setItemMeta(orangeTulipMeta);
		ShapedRecipe orangeTulipRec = new ShapedRecipe(orangeTulip);
		orangeTulipRec.shape("pdp","dfd","pdp");
		orangeTulipRec.setIngredient('d', Material.DIAMOND);
		orangeTulipRec.setIngredient('p', Material.POISONOUS_POTATO);
		orangeTulipRec.setIngredient('f', new MaterialData(Material.getMaterial(38), (byte) 5));
		Bukkit.getServer().addRecipe(orangeTulipRec);
		
		
		azureBluetMeta.setDisplayName("§b§lSnowball Flower");
		azureBluetMeta.setLore(Arrays.asList("§5Power: Shoot snowballs", "§bAmmo: snowball"));
		azureBluet.setItemMeta(azureBluetMeta);
		ShapedRecipe azureBluetRec = new ShapedRecipe(azureBluet);
		azureBluetRec.shape("dsd","sfs","wsw");
		azureBluetRec.setIngredient('d', Material.DIAMOND);
		azureBluetRec.setIngredient('s', Material.SNOW_BLOCK);
		azureBluetRec.setIngredient('w', Material.WATER_BUCKET);
		azureBluetRec.setIngredient('f', new MaterialData(Material.getMaterial(38), (byte) 3));
		Bukkit.getServer().addRecipe(azureBluetRec);
		
		
		whiteTulipMeta.setDisplayName("§f§lWeb Flower");
		whiteTulipMeta.setLore(Arrays.asList("§5Power: Shoot webs", "§bAmmo: string"));
		whiteTulip.setItemMeta(whiteTulipMeta);
		ShapedRecipe whiteTulipRec = new ShapedRecipe(whiteTulip);
		whiteTulipRec.shape("dsd","sfs","dsd");
		whiteTulipRec.setIngredient('d', Material.DIAMOND);
		whiteTulipRec.setIngredient('s', Material.STRING);
		whiteTulipRec.setIngredient('f', new MaterialData(Material.getMaterial(38), (byte) 6));
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
	
}
