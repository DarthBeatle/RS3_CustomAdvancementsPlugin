package me.darthbeatle.rs3_customadvancementsplugin.advs.tab0;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import me.darthbeatle.rs3_customadvancementsplugin.advs.AdvancementTabNamespaces;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import org.bukkit.Material;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;

public class Oops_all_goat_horns extends BaseAdvancement  {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab0_NAMESPACE, "oops_all_goat_horns");


  public Oops_all_goat_horns(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.GOAT_HORN, "Oops, All Goat Horns", AdvancementFrameType.TASK, true, true, x, y , "Get all goat horns"), parent, 1);
  }
}