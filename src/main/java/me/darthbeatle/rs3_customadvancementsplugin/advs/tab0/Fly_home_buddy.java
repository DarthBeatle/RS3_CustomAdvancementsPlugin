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

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

public class Fly_home_buddy extends BaseAdvancement implements HiddenVisibility {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab0_NAMESPACE, "fly_home_buddy");

  // Central Point location will not change
  final Location CENTRAL_POINT = new Location(Bukkit.getServer().getWorld("world_the_end"), 0, 63, 0);
  Logger log = Bukkit.getLogger();

  public Fly_home_buddy(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.ELYTRA, "Fly Home Buddy", AdvancementFrameType.CHALLENGE, true, true, x, y , "Travel from an outer end island to the main end island using an elytra"), parent, 1);

    // End Home Portal Coords: 0, 63, 0 - central point
    // Detect when a player is within a certain range and flying
    // Distance is about ~1000 blocks
    // Detect using distance() - make sure it detects only x and z coords, not y - compare x and z values

    HashMap<UUID, Boolean> trackingFlyingPlayers = new HashMap<>();

    registerEvent(PlayerMoveEvent.class, (e)-> {

      Player p = e.getPlayer();
      UUID pUUID = p.getUniqueId();
      Location pLocation = p.getLocation();

      // stop if player has advancement, is not in the end, or is not gliding
      if (isGranted(pUUID) || !p.getWorld().getName().equalsIgnoreCase("world_the_end") || !p.isGliding()) {
        if (trackingFlyingPlayers.containsKey(pUUID)) {
          trackingFlyingPlayers.remove(pUUID);
          log.info("player not gliding, removed from hashmap");
          log.info("Hashmap contents: " + trackingFlyingPlayers);
          return;
        }
        return;
      }
//      if (!p.isGliding()) {
//        trackingFlyingPlayers.remove(pUUID);
//        log.info("player not gliding, removed from hashmap");
//        log.info("Hashmap contents: " + trackingFlyingPlayers);
//        return;
//      }

      // if player is far enough away and is not in hashmap, put in hashmap
      if (playerIsWithinOuterRadius(p) && !trackingFlyingPlayers.containsKey(pUUID)) {

        trackingFlyingPlayers.put(pUUID, true);
        p.sendMessage("You're gliding in the end! Gliding Location: " + pLocation);

        log.info("Player gliding at location: " + pLocation);
        log.info("Hashmap contents: " + trackingFlyingPlayers);

        // Remove the player from hashmap if they are too far away. No sense keeping them in there
      } else if (playerIsBeyondOuterRadius(p) && trackingFlyingPlayers.containsKey(pUUID)) {

        trackingFlyingPlayers.remove(pUUID);

        log.info("player removed from hashmap");
        log.info("Hashmap contents: " + trackingFlyingPlayers);

      }
      // Award player advancement if they are in the hashmap, and they are near the main end island
      if (playerIsWithinInnerRadius(p) && trackingFlyingPlayers.containsKey(pUUID)) {

        incrementProgression(p);
        trackingFlyingPlayers.remove(pUUID);
        log.info("Hashmap contents: " + trackingFlyingPlayers);

        log.info("Player: " + p.getName() + " awarded advancement and removed from hashmap");

      }
    });

    // Clear hashmap when no one is online!
    registerEvent(PlayerQuitEvent.class, (e) -> {
      if (Bukkit.getServer().getOnlinePlayers().isEmpty()) {
        trackingFlyingPlayers.clear();
      }
    });
  }

  // Used to detect when the player is near the outer end islands
  private boolean playerIsWithinOuterRadius(Player p) {
    Location pLocation = p.getLocation();
    int minimumRange = 1000;
    int maximumRange = 2000;
    int negativeMinimumRange = -1000;
    int negativeMaximumRange = -2000;
    // If player distance to Central Point is in between (1000 and 1500) or in between (-1000 and -1500) and (x or z) value is in between (1000 and 1500) or in between (-1000 and -1500)
    // The check for x and z prevents a false positive when a player flies straight up and increases or decreases the y value
    return (((pLocation.getX() - CENTRAL_POINT.getX() > minimumRange && pLocation.getX() - CENTRAL_POINT.getX() < maximumRange) || (pLocation.getZ() - CENTRAL_POINT.getZ() > minimumRange && pLocation.getZ() - CENTRAL_POINT.getZ() < maximumRange)) || ((pLocation.getX() - CENTRAL_POINT.getX() < negativeMinimumRange && pLocation.getX() - CENTRAL_POINT.getX() > negativeMaximumRange) || (pLocation.getZ() - CENTRAL_POINT.getZ() < negativeMinimumRange && pLocation.getZ() - CENTRAL_POINT.getZ() > negativeMaximumRange)));
  }

  private boolean playerIsBeyondOuterRadius(Player p) {
    Location pLocation = p.getLocation();
    return (!playerIsWithinOuterRadius(p) && (pLocation.distance(CENTRAL_POINT) > 2000 || pLocation.distance(CENTRAL_POINT) < -2000));
  }

  // Used to detect when the player is near the main end island.
  private boolean playerIsWithinInnerRadius(Player p) {
    Location pLocation = p.getLocation();
    return (pLocation.distance(CENTRAL_POINT) < 250);
  }
}

// TODO: clear hashmap when all players online have advancement!
// TODO: clear hashmap when no players are in end!
// TODO: make sure hashmap is not saving player more than once!