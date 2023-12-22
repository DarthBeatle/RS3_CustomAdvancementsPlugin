package me.darthbeatle.rs3_customadvancementsplugin.advs.tab0;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import me.darthbeatle.rs3_customadvancementsplugin.advs.AdvancementTabNamespaces;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import org.bukkit.Material;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;

public class Sponge_out_of_water extends BaseAdvancement  {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab0_NAMESPACE, "sponge_out_of_water");


  public Sponge_out_of_water(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.SPONGE, "Sponge Out of Water", AdvancementFrameType.GOAL, true, true, x, y , "Dry a sponge by placing it in the Nether or by smelting it in a furnace"), parent, 1);
  }
}