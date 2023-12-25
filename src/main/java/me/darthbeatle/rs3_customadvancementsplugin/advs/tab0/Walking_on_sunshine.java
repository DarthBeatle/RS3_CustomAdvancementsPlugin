package me.darthbeatle.rs3_customadvancementsplugin.advs.tab0;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import me.darthbeatle.rs3_customadvancementsplugin.advs.AdvancementTabNamespaces;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import org.bukkit.Material;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;

public class Walking_on_sunshine extends BaseAdvancement  {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab0_NAMESPACE, "walking_on_sunshine");


  public Walking_on_sunshine(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.LEATHER_BOOTS, "Walking on Sunshine", AdvancementFrameType.TASK, true, true, x, y , "Walk 100 blocks"), parent, 1);
  }
}

// TODO - statistic for WALK_ONE_CM, RUN_ONE_CM and CROUCH_ONE_CM
// TODO - Use PlayerMoveEvent

/*
    registerEvent(PlayerMoveEvent.class, e -> {
      Player p = e.getPlayer();
      int walkingStatistic = p.getStatistic(Statistic.WALK_ONE_CM);
      p.sendMessage("You have walked: " + walkingStatistic + " blocks");
    });
 */