package me.darthbeatle.rs3_customadvancementsplugin.advs.tab0;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import me.darthbeatle.rs3_customadvancementsplugin.advs.AdvancementTabNamespaces;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.visibilities.ParentGrantedVisibility;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import org.bukkit.Material;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;

public class Im_gonna_be_500000_miles extends BaseAdvancement implements ParentGrantedVisibility {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab0_NAMESPACE, "im_gonna_be_500000_miles");


  public Im_gonna_be_500000_miles(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.DIAMOND_BOOTS, "I'm Gonna Be (500000 Miles)", AdvancementFrameType.TASK, true, true, x, y , "Walk 500,000 blocks"), parent, 1);
  }
}