package net.syobonoon.plugin.miclosknock;

import org.bukkit.plugin.java.JavaPlugin;

public class MicLoSKnock extends JavaPlugin {

    @Override
    public void onEnable() {
    	getLogger().info("onEnable");
    	new KnockEventsListener(this);
    }

}
