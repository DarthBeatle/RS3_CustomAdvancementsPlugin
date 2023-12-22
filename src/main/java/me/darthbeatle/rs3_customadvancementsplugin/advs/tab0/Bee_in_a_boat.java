package me.darthbeatle.rs3_customadvancementsplugin.advs.tab0;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import me.darthbeatle.rs3_customadvancementsplugin.RS3_CustomAdvancementsPlugin;
import me.darthbeatle.rs3_customadvancementsplugin.advs.AdvancementTabNamespaces;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import org.bukkit.Material;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityPlaceEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.Objects;

public class Bee_in_a_boat extends BaseAdvancement {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab0_NAMESPACE, "bee_in_a_boat");


  public Bee_in_a_boat(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.OAK_BOAT, "Bee in a Boat", AdvancementFrameType.TASK, true, true, x, y , "Put a bee in a boat"), parent, 1);

    registerEvent(EntityPlaceEvent.class, (e)-> {
      // get boat placed
      if (e.getEntityType().name().contains("BOAT")) {

        Player p = e.getPlayer();
        // Use Persistent Data Storage to add a key value for the player who placed the boat
        PersistentDataContainer data = e.getEntity().getPersistentDataContainer();
        // Set player as the owner of the boat
        data.set(new NamespacedKey(RS3_CustomAdvancementsPlugin.getPlugin(), "owner"), PersistentDataType.STRING, Objects.requireNonNull(p, "Player does not exist!").toString());

      }
    });

    registerEvent(VehicleEnterEvent.class, (e)->{
      Entity ent = e.getEntered();
      PersistentDataContainer boatData = e.getVehicle().getPersistentDataContainer();

      if (ent.getType() == EntityType.BEE && e.getVehicle().getType().name().contains("BOAT") && boatData.has(new NamespacedKey(RS3_CustomAdvancementsPlugin.getPlugin(), "owner"), PersistentDataType.STRING)) {

        // Search for players in a 5 block radius
        List<Entity> NearbyPlayers = ent.getNearbyEntities(5, 5, 5);
        for (Entity entity : NearbyPlayers) {
          // If the player matches the owner in the boat metadata, continue
          if (entity instanceof Player && entity.toString().equals(boatData.get(new NamespacedKey(RS3_CustomAdvancementsPlugin.getPlugin(), "owner"), PersistentDataType.STRING))) {
            Player p = (Player) entity;
            incrementProgression(p);
            break;
          }
        }
      }
    });

  }
}