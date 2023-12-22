package me.darthbeatle.rs3_customadvancementsplugin.advs.tab0;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import me.darthbeatle.rs3_customadvancementsplugin.advs.AdvancementTabNamespaces;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import me.darthbeatle.rs3_customadvancementsplugin.util.DelayedTask;
import org.bukkit.Material;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class Simon_didnt_say_stand_up extends BaseAdvancement  {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab0_NAMESPACE, "simon_didnt_say_stand_up");


  public Simon_didnt_say_stand_up(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.END_CRYSTAL, "Simon Didn't Say Stand Up", AdvancementFrameType.TASK, true, true, x, y , "Kill someone with an end crystal"), parent, 1);

    HashMap<EnderCrystal, UUID> targetCrystal = new HashMap<>();
    HashMap<EnderCrystal, UUID> targetPlayer = new HashMap<>();
    HashMap<Projectile, UUID> projectileSource = new HashMap<>();

    registerEvent(EntityDamageByEntityEvent.class, e -> {
      // Player interacts with end crystal, store in hashmap and then clear after a set time period
      if (e.getEntity() instanceof EnderCrystal && e.getDamager() instanceof Player) {

        Player p = (Player) e.getDamager();
        if (!isGranted(p.getUniqueId())) {

          EnderCrystal crystal = (EnderCrystal) e.getEntity();
          targetCrystal.put(crystal, p.getUniqueId());

          DelayedTask clearTargetCrystalHashMap = new DelayedTask(targetCrystal::clear, 5);

        }

        // Projectile interacts with end crystal, store in hashmap and then clear after a set time period
      } else if (e.getEntity() instanceof EnderCrystal && e.getDamager() instanceof Projectile){

        Projectile proj = (Projectile) e.getDamager();

        if (proj.getShooter() != null && proj.getShooter() instanceof Player && !isGranted(((Player) proj.getShooter()).getUniqueId())) {

          projectileSource.put(proj, ((Player) proj.getShooter()).getUniqueId());

          DelayedTask clearProjectileSourceHashMap = new DelayedTask(projectileSource::clear, 5);

        }

      }
      // Check for when player is damaged by end crystal. Check if player is killed by same crystal. Check and then clear hashmap
      if (e.getEntity() instanceof Player && e.getDamager() instanceof EnderCrystal) {

        Player p = (Player) e.getEntity();
        EnderCrystal damager = (EnderCrystal) e.getDamager();
        targetPlayer.put(damager, p.getUniqueId());

        DelayedTask clearTargetPlayerHashMap = new DelayedTask(targetPlayer::clear, 5);

      }

    });

    registerEvent(PlayerDeathEvent.class, e -> {

      Player p = e.getEntity();
      Player killer = p.getKiller();

      if (Objects.requireNonNull(p.getLastDamageCause(), "getLastDamageCause should not be null!").getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {

        // If UUIDs in hashmaps match player and killer and if player and killer are different players
        if (targetPlayer.containsValue(p.getUniqueId()) && (targetCrystal.containsValue(Objects.requireNonNull(killer, "Killer is null!").getUniqueId()) || projectileSource.containsValue(killer.getUniqueId())) && !(p.getUniqueId() == killer.getUniqueId())) {
          incrementProgression(killer);
        }
      }
    });

  }
}