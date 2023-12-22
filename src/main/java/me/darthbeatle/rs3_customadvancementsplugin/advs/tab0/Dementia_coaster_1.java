package me.darthbeatle.rs3_customadvancementsplugin.advs.tab0;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import me.darthbeatle.rs3_customadvancementsplugin.advs.AdvancementTabNamespaces;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import org.bukkit.Material;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;

public class Dementia_coaster_1 extends BaseAdvancement  {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab0_NAMESPACE, "dementia_coaster_1");


  public Dementia_coaster_1(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.MINECART, "Dementia Coaster I", AdvancementFrameType.TASK, true, true, x, y , "Travel 1,000 blocks by minecart"), parent, 1);
  }
}