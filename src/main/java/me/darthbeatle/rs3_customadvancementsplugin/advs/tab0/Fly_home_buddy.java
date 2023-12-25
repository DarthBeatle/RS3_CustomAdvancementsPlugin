package me.darthbeatle.rs3_customadvancementsplugin.advs.tab0;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import me.darthbeatle.rs3_customadvancementsplugin.advs.AdvancementTabNamespaces;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.visibilities.HiddenVisibility;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Fly_home_buddy extends BaseAdvancement implements HiddenVisibility {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab0_NAMESPACE, "fly_home_buddy");

  // Center of main end island portal coordinates: 0, 63, 0
  final Location MAIN_END_ISLAND_PORTAL = new Location(Bukkit.getServer().getWorld("world_the_end"), 0, 63, 0);

  public Fly_home_buddy(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.ELYTRA, "Fly Home Buddy", AdvancementFrameType.CHALLENGE, true, true, x, y , "Travel from an outer end island to the main end island using an elytra"), parent, 1);

    List<UUID> flyingPlayers = new ArrayList<>();

    registerEvent(PlayerMoveEvent.class, (e)-> {

      Player p = e.getPlayer();
      UUID pUUID = p.getUniqueId();

      // Stop if the player has the advancement, is not in the end, or is not gliding
      if (isGranted(pUUID) || !p.getWorld().getName().equalsIgnoreCase("world_the_end") || !p.isGliding()) {

          if (!flyingPlayers.contains(pUUID)) return;

          flyingPlayers.remove(pUUID);
          return;

      }

      // If the player is far enough away and is not in the list, put in list
      if (playerIsAtOuterEndIslands(p) && !flyingPlayers.contains(pUUID)) flyingPlayers.add(pUUID);

      // Award the player the advancement if they are in the list, and they are near the main end island
      if (playerIsNearMainEndIsland(p) && flyingPlayers.contains(pUUID)) {

        incrementProgression(p);
        flyingPlayers.remove(pUUID);

      }

    });

    // Clear the list when no one is online
    registerEvent(PlayerQuitEvent.class, (e) -> {

      if (Bukkit.getServer().getOnlinePlayers().isEmpty()) flyingPlayers.clear();

    });

  }

  // Used to detect when the player is at the outer end islands
  private boolean playerIsAtOuterEndIslands(Player p) {

    Location pLocation = p.getLocation();

    // Sets the player y coordinate to a fixed y coordinate in order to get the distance only using the x and z coordinates
    // If y was counted, the player could fly straight up over the main end island and then back down to get the advancement

    Location pLocationWithFixedY = new Location(p.getWorld(), pLocation.getX(), MAIN_END_ISLAND_PORTAL.getY(), pLocation.getZ());

    // The outer end islands start around 1000 blocks away from the main end island
    int distanceToEndIslands = 1000;

    // Return true if the player's distance to the given location is greater than the given range
    return pLocationWithFixedY.distance(MAIN_END_ISLAND_PORTAL) > distanceToEndIslands;

  }

  // Used to detect when the player is a short distance away and within sight of the main end island
  private boolean playerIsNearMainEndIsland(Player p) {

    Location pLocation = p.getLocation();
    int distanceToMainEndIsland = 250;
    return (pLocation.distance(MAIN_END_ISLAND_PORTAL) < distanceToMainEndIsland);

  }
}