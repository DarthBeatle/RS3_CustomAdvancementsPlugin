package me.darthbeatle.rs3_customadvancementsplugin.advs.tab0;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import me.darthbeatle.rs3_customadvancementsplugin.advs.AdvancementTabNamespaces;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import org.bukkit.Material;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.entity.Squid;
import org.bukkit.event.entity.EntityDeathEvent;

public class Hi_squidward extends BaseAdvancement  {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab0_NAMESPACE, "hi_squidward");


  public Hi_squidward(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.IRON_SWORD, "Hi Squidward", AdvancementFrameType.TASK, true, true, x, y , "Kill a squid"), parent, 1);

    registerEvent(EntityDeathEvent.class, e -> {
      if (e.getEntity() instanceof Squid && e.getEntity().getKiller() != null && !isGranted(e.getEntity().getKiller())) {
        Player p = e.getEntity().getKiller();
        incrementProgression(p);
      }
    });

  }
}