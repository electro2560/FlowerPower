package com.electro2560.dev.flowerpower.listeners;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.electro2560.dev.flowerpower.FlowerPower;
import com.electro2560.dev.flowerpower.utils.Perms;
import com.electro2560.dev.flowerpower.utils.PowerUtils;
import com.electro2560.dev.flowerpower.utils.Utils;

public class FlowerListener implements Listener{
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		
		@SuppressWarnings("deprecation")
		ItemStack stack = player.getItemInHand();
	
		Inventory inv = player.getInventory();
		
		if(event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) return;
			
		if (stack.isSimilar(Utils.dandelion)) {
			// Dandelion
			// Knockback player

			// Ammo: Trip wire hook

			event.setCancelled(true);

			if ((FlowerPower.usePermissions && player.hasPermission(Perms.dandelion)) || !FlowerPower.usePermissions) {
				if (!inv.contains(Material.TRIPWIRE_HOOK, 1)) {
					player.sendMessage(ChatColor.RED + "You need ammo!");
					return;
				}

				inv.remove(new ItemStack(Material.TRIPWIRE_HOOK, 1));

				ArrayList<Player> nearby = Utils.getNearbyPlayers(Utils.getTargetBlock(player), 5, player);
					
				if(nearby == null || nearby.size() == 0){
					player.sendMessage(ChatColor.RED + "No players found!");
					return;
				}
					
				for(Player target : nearby) Utils.knockbackPlayer(target, 2);
			}else player.sendMessage(ChatColor.RED + "No permission!");
		} else if (stack.isSimilar(Utils.poppy)) {
			// Poppy
			// Shoot Fire balls

			// Ammo: Firecharge + One Tick of Flint and Steel

			event.setCancelled(true);

			if ((FlowerPower.usePermissions && player.hasPermission(Perms.poppy)) || !FlowerPower.usePermissions) {
				if (!inv.contains(Material.TRIPWIRE_HOOK, 1) || !inv.contains(Material.FLINT_AND_STEEL, 1)) {
					player.sendMessage(ChatColor.RED + "You need ammo!");
					return;
				}

				inv.remove(new ItemStack(Material.TRIPWIRE_HOOK, 1));

				for (int i = 0; i < inv.getSize(); i++) {
					if (inv.getItem(i).getType() == Material.FLINT_AND_STEEL) {
						ItemStack s = inv.getItem(i);
						Damageable meta = (Damageable) s.getItemMeta();
						
						meta.setDamage(meta.getDamage() + 1);
						
						if(meta.getDamage() > Material.FLINT_AND_STEEL.getMaxDurability()) inv.setItem(i, null);
						
						break;
					}
				}
				
				PowerUtils.shootFireball(player);
			}else player.sendMessage(ChatColor.RED + "No permission!");
		} else if (stack.isSimilar(Utils.redTulip)) {
			// Red Tulip
			// Shoot Fire

			// Ammo: One tick of flint and steel

			event.setCancelled(true);

			if ((FlowerPower.usePermissions && player.hasPermission(Perms.redtulip)) || !FlowerPower.usePermissions) {
				if (!inv.contains(Material.FLINT_AND_STEEL, 1)) {
					player.sendMessage(ChatColor.RED + "You need ammo!");
					return;
				}

				for (int i = 0; i < inv.getSize(); i++) {
					if (inv.getItem(i).getType() == Material.FLINT_AND_STEEL) {
						ItemStack s = inv.getItem(i);
						Damageable meta = (Damageable) s.getItemMeta();
						
						meta.setDamage(meta.getDamage() + 1);
						
						if(meta.getDamage() > Material.FLINT_AND_STEEL.getMaxDurability()) inv.setItem(i, null);
						
						break;
					}
				}
				
				PowerUtils.shootSmallFireball(player);
			}else player.sendMessage(ChatColor.RED + "No permission!");
		} else if (stack.isSimilar(Utils.blueOrchid)) {
			// Blue Orchid
			// Shoot Ice

			// Ammo: Block of Ice

			event.setCancelled(true);
			
			if ((FlowerPower.usePermissions && player.hasPermission(Perms.blueorchid)) || !FlowerPower.usePermissions) {
				if (inv.contains(Material.ICE, 1)) {
					inv.remove(new ItemStack(Material.ICE, 1));
					
					Utils.shootBlock(Material.ICE, player, (byte) 0, 2D);
				}else if(inv.contains(Material.PACKED_ICE)){
					inv.remove(new ItemStack(Material.PACKED_ICE, 1));
					
					Utils.shootBlock(Material.PACKED_ICE, player, (byte) 0, 2D);
				}else{
					player.sendMessage(ChatColor.RED + "You need ammo!");
					return;
				}
			}else player.sendMessage(ChatColor.RED + "No permission!");
		} else if (stack.isSimilar(Utils.pinkTulip)) {
			// Pink tulip
			// Pull target player towards player

			// Ammo: TripWire Hook

			event.setCancelled(true);
			
			if ((FlowerPower.usePermissions && player.hasPermission(Perms.pinktulip)) || !FlowerPower.usePermissions) {
				if (!inv.contains(Material.TRIPWIRE_HOOK, 1)) {
					player.sendMessage(ChatColor.RED + "You need ammo!");
					return;
				}

				inv.remove(new ItemStack(Material.TRIPWIRE_HOOK, 1));

				ArrayList<Player> nearby = Utils.getNearbyPlayers(Utils.getTargetBlock(player), 5, player);
					
				if(nearby == null || nearby.size() == 0){
					player.sendMessage(ChatColor.RED + "No players found!");
					return;
				}
					
				for(Player target : nearby) Utils.pullPlayer(target, 2);
			}else player.sendMessage(ChatColor.RED + "No permission!");
		} else if (stack.isSimilar(Utils.oxeyeDaisy)) {
			// Oxeye Daisy
			// Give target player blindness/nausea

			// Ammo: Spider Eye

			event.setCancelled(true);

			if ((FlowerPower.usePermissions && player.hasPermission(Perms.oxeyedaisy)) || !FlowerPower.usePermissions) {
				if (!inv.contains(Material.SPIDER_EYE, 1)) {
					player.sendMessage(ChatColor.RED + "You need ammo!");
					return;
				}

				inv.remove(new ItemStack(Material.SPIDER_EYE, 1));

				ArrayList<Player> nearby = Utils.getNearbyPlayers(Utils.getTargetBlock(player), 5, player);
					
				if(nearby == null || nearby.size() == 0){
					player.sendMessage(ChatColor.RED + "No players found!");
					return;
				}
					
				for(Player target : nearby){
					// Delay in ticks. 15 seconds == 300
					
					target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 300, 1));
					target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 300, 1));
				}
			}else player.sendMessage(ChatColor.RED + "No permission!");
		} else if (stack.isSimilar(Utils.allium)) {
			// Alluim
			// Regen II

			// Ammo: 4 gold ingots

			event.setCancelled(true);

			if ((FlowerPower.usePermissions && player.hasPermission(Perms.allium)) || !FlowerPower.usePermissions) {
				if (!inv.contains(Material.GOLD_INGOT, 4)) {
					player.sendMessage(ChatColor.RED + "You need ammo!");
					return;
				}

				inv.remove(new ItemStack(Material.GOLD_INGOT, 4));

				ArrayList<Player> nearby = Utils.getNearbyPlayers(Utils.getTargetBlock(player), 5, player);
					
				if(nearby == null || nearby.size() == 0){
					player.sendMessage(ChatColor.RED + "No players found!");
					return;
				}
					
				for(Player target : nearby){
					// Delay in ticks. 15 seconds == 300
					
					target.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 2));
				}
			}else player.sendMessage(ChatColor.RED + "No permission!");
		} else if (stack.isSimilar(Utils.orangeTulip)) {
			// Orange Tulip
			// Turn to Potato

			// Ammo: Poisoned Potato

			event.setCancelled(true);

			if ((FlowerPower.usePermissions && player.hasPermission(Perms.orangetulip)) || !FlowerPower.usePermissions) {
				if (!inv.contains(Material.POISONOUS_POTATO, 1)) {
					player.sendMessage(ChatColor.RED + "You need ammo!");
					return;
				}

				inv.remove(new ItemStack(Material.POISONOUS_POTATO, 1));

				ArrayList<Player> nearby = Utils.getNearbyPlayers(Utils.getTargetBlock(player), 5, player);
					
				if(nearby == null || nearby.size() == 0){
					player.sendMessage(ChatColor.RED + "No players found!");
					return;
				}
					
				for(Player target : nearby){
					PowerUtils.turnToPotato(target);
				}
			}else player.sendMessage(ChatColor.RED + "No permission!");
		} else if (stack.isSimilar(Utils.azureBluet)) {
			// Azure Bluet
			// Shoot snow balls, but faster than normal

			event.setCancelled(true);
			
			if ((FlowerPower.usePermissions && player.hasPermission(Perms.azurebluet)) || !FlowerPower.usePermissions) {
				if (!inv.contains(Material.SNOWBALL, 1)) {
					player.sendMessage(ChatColor.RED + "You need ammo!");
					return;
				}

				inv.remove(new ItemStack(Material.SNOWBALL, 1));

				PowerUtils.shootSnowball(player);
			}else player.sendMessage(ChatColor.RED + "No permission!");
		} else if (stack.isSimilar(Utils.whiteTulip)) {
			// White Tulip
			// Shoot webs

			// Ammo: string

			event.setCancelled(true);

			if ((FlowerPower.usePermissions && player.hasPermission(Perms.whitetulip)) || !FlowerPower.usePermissions) {
				if (!inv.contains(Material.STRING, 1)) {
					player.sendMessage(ChatColor.RED + "You need ammo!");
					return;
				}

				inv.remove(new ItemStack(Material.STRING, 1));

				Utils.shootBlock(Material.COBWEB, player, (byte) 0, 2D);
			}else player.sendMessage(ChatColor.RED + "No permission!");
		}

		player.updateInventory();
	}
	
}
