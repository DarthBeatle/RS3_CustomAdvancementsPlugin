package me.darthbeatle.rs3_customadvancementsplugin.advs.tab0;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import me.darthbeatle.rs3_customadvancementsplugin.advs.AdvancementTabNamespaces;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.visibilities.ParentGrantedVisibility;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import org.bukkit.Material;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;

public class The_horse_is_here extends BaseAdvancement implements ParentGrantedVisibility {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab0_NAMESPACE, "the_horse_is_here");


  public The_horse_is_here(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.SKELETON_HORSE_SPAWN_EGG, "The Horse is Here", AdvancementFrameType.GOAL, true, true, x, y , "Kill your own horse"), parent, 1);
  }
}