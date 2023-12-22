package me.darthbeatle.rs3_customadvancementsplugin.advs.tab0;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import me.darthbeatle.rs3_customadvancementsplugin.advs.AdvancementTabNamespaces;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.visibilities.HiddenVisibility;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import org.bukkit.Material;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;

public class Ice_dweller extends BaseAdvancement implements HiddenVisibility {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab0_NAMESPACE, "ice_dweller");


  public Ice_dweller(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.ICE, "Ice Dweller", AdvancementFrameType.CHALLENGE, true, true, x, y , "Spend 183 days in any ice biome"), parent, 1);
  }
}