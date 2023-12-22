package me.darthbeatle.rs3_customadvancementsplugin.advs.tab0;

import com.fren_gor.ultimateAdvancementAPI.events.PlayerLoadingCompletedEvent;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementUtils;
import me.darthbeatle.rs3_customadvancementsplugin.advs.AdvancementTabNamespaces;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import org.bukkit.Material;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import org.bukkit.event.EventPriority;

public class Yankee_with_no_trim extends BaseAdvancement  {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab0_NAMESPACE, "yankee_with_no_trim");


  public Yankee_with_no_trim(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.NETHERITE_HELMET, "YANKEE WITH NO TRIM", AdvancementFrameType.GOAL, true, true, x, y , "Use a trim on every armor piece except your helmet"), parent, 1);
  }
}