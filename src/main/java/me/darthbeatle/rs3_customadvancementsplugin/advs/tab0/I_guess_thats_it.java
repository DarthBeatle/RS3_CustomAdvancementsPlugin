package me.darthbeatle.rs3_customadvancementsplugin.advs.tab0;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import me.darthbeatle.rs3_customadvancementsplugin.advs.AdvancementTabNamespaces;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import org.bukkit.Material;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class I_guess_thats_it extends BaseAdvancement  {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab0_NAMESPACE, "i_guess_thats_it");


  public I_guess_thats_it(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.BOW, "I Guess That's It", AdvancementFrameType.TASK, true, true, x, y , "Kill yourself with a bow"), parent, 1);

    registerEvent(PlayerDeathEvent.class, e -> {
      Player p = e.getEntity();
      if (!isGranted(p) && p.getKiller() == p && Objects.requireNonNull(p.getLastDamageCause(), "Damage cause is null!").getCause().toString().equals("PROJECTILE")) {
        incrementProgression(p);
      }
    });

  }
}