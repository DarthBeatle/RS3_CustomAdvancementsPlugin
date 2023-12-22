package me.darthbeatle.rs3_customadvancementsplugin.util;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class RepeatingTask implements Listener {

    private static Plugin plugin = null;

    private int id = -1;

    public RepeatingTask(Plugin instance) {
        plugin = instance;
    }

    public RepeatingTask(Runnable runnable) {
        this(runnable, 0, 0);
    }

    public RepeatingTask(Runnable runnable, long delay, long period) {
        if (plugin.isEnabled()) {
            id = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, runnable, delay, period);
        } else {
            runnable.run();
        }
    }

    public int getId() {
        return id;
    }

}
