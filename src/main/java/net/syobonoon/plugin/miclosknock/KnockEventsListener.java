package net.syobonoon.plugin.miclosknock;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BlockIterator;

public class KnockEventsListener implements Listener {
	Random random = new Random();

	public KnockEventsListener(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

    @EventHandler(ignoreCancelled = true)
    public void Knockplayer(PlayerInteractAtEntityEvent event) {
    	if (!(event.getRightClicked() instanceof  Player)) return;

    	Player player = event.getPlayer();
    	if (!player.isSneaking()) return;

    	Player target = (Player) event.getRightClicked();
    	int ran_sound = random.nextInt(100);
    	if(ran_sound == 1) {
    		target.getWorld().playSound(target.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1F, 1F);
    	}else {
    		target.getWorld().playSound(target.getLocation(), Sound.BLOCK_ANVIL_PLACE, 0.5F, 0.5F);
    	}
    }

	@EventHandler
    public void onPlayerInteractBlockExplosion(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if (player.getInventory().getItemInMainHand().getType() == Material.FISHING_ROD) {
            Block target = getTargetBlock(player);
            if (target != null) target.getWorld().createExplosion(target.getLocation(), 4F, true, false);
        }
    }

    private Block getTargetBlock(Player player) {

        BlockIterator it = new BlockIterator(player, 100);

        while(it.hasNext()) {
            Block block = it.next();
            if (block.getType() != Material.AIR) return block;
        }

        return null;
    }
}
