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

  // End Home Portal Coordinates: 0, 63, 0 - central point
  final Location CENTRAL_POINT = new Location(Bukkit.getServer().getWorld("world_the_end"), 0, 63, 0);

  public Fly_home_buddy(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.ELYTRA, "Fly Home Buddy", AdvancementFrameType.CHALLENGE, true, true, x, y , "Travel from an outer end island to the main end island using an elytra"), parent, 1);

    List<UUID> flyingPlayers = new ArrayList<>();

    registerEvent(PlayerMoveEvent.class, (e)-> {

      Player p = e.getPlayer();
      UUID pUUID = p.getUniqueId();

      // stop if player has advancement, is not in the end, or is not gliding
      if (isGranted(pUUID) || !p.getWorld().getName().equalsIgnoreCase("world_the_end") || !p.isGliding()) {

        if (flyingPlayers.contains(pUUID)) {

          flyingPlayers.remove(pUUID);
          return;

        }

        return;

      }

      // if player is far enough away and is not in hashmap, put in hashmap
      if (playerIsWithinOuterRadius(p) && !flyingPlayers.contains(pUUID)) {

        flyingPlayers.add(pUUID);

      }

      // Remove the player from hashmap if they are too far away. No sense keeping them in there
      if (playerIsBeyondOuterRadius(p)) {

        flyingPlayers.remove(pUUID);

      }

      // Award player advancement if they are in the hashmap, and they are near the main end island
      if (playerIsWithinInnerRadius(p) && flyingPlayers.contains(pUUID)) {

        incrementProgression(p);
        flyingPlayers.remove(pUUID);

      }

    });

    // Clear hashmap when no one is online
    registerEvent(PlayerQuitEvent.class, (e) -> {

      if (Bukkit.getServer().getOnlinePlayers().isEmpty()) flyingPlayers.clear();

    });

  }

  // Used to detect when the player is near the outer end islands
  private boolean playerIsWithinOuterRadius(Player p) {

    Location pLocation = p.getLocation();

    // Sets player y coordinate to CENTRAL_POINT y coordinate in order to get the distance only using the x and z coordinates
    // If y was counted, the player could fly straight up over the main end island and then back down to get the advancement

    Location pLocationWithFixedY = new Location(p.getWorld(), pLocation.getX(), CENTRAL_POINT.getY(), pLocation.getZ());

    int minimumRange = 1000;
    int maximumRange = 2000;

    // return true if player distance to Central Point is in between (1000 and 1500 or -1000 and -1500) on the (x or z coordinates)
    return pLocationWithFixedY.distance(CENTRAL_POINT) > minimumRange && pLocationWithFixedY.distance(CENTRAL_POINT) < maximumRange;

  }

  // Used to detect when the player is further out in the outer end islands
  private boolean playerIsBeyondOuterRadius(Player p) {

    Location pLocation = p.getLocation();

    // Setting player y level to a constant value for same reason as in #playerIsWithingOuterRadius
    Location pLocationWithFixedY = new Location(p.getWorld(), pLocation.getX(), CENTRAL_POINT.getY(), pLocation.getZ());

    int distance = 2000;

    return (!playerIsWithinOuterRadius(p) && pLocationWithFixedY.distance(CENTRAL_POINT) > distance || pLocationWithFixedY.distance(CENTRAL_POINT) < -distance);
  }

  // Used to detect when the player is near the main end island.
  private boolean playerIsWithinInnerRadius(Player p) {

    Location pLocation = p.getLocation();
    return (pLocation.distance(CENTRAL_POINT) < 250);

  }
}
