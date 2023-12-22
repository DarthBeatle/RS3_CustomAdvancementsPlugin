package me.darthbeatle.rs3_customadvancementsplugin.advs.tab0;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import me.darthbeatle.rs3_customadvancementsplugin.advs.AdvancementTabNamespaces;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import org.bukkit.Material;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;

public class Astronaut_in_the_ocean extends BaseAdvancement  {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab0_NAMESPACE, "astronaut_in_the_ocean");


  public Astronaut_in_the_ocean(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.WATER_BUCKET, "Astronaut in the Ocean", AdvancementFrameType.GOAL, true, true, x, y , "Swim in water in the End Dimension"), parent, 1);
  }
}