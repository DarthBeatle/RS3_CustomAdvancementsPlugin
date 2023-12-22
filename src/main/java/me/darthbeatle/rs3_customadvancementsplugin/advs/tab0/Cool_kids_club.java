package me.darthbeatle.rs3_customadvancementsplugin.advs.tab0;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import me.darthbeatle.rs3_customadvancementsplugin.RS3_CustomAdvancementsPlugin;
import me.darthbeatle.rs3_customadvancementsplugin.advs.AdvancementTabNamespaces;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.visibilities.HiddenVisibility;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import me.darthbeatle.rs3_customadvancementsplugin.util.RepeatingTask;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class Cool_kids_club extends BaseAdvancement implements HiddenVisibility {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab0_NAMESPACE, "cool_kids_club");

  // Get instance of plugin
  RS3_CustomAdvancementsPlugin plugin = RS3_CustomAdvancementsPlugin.getPlugin();
  // Plugin logger
  Logger log = plugin.getLogger();
  // Online players
  // '? extends' means 'anything that would work with instanceof Player'
  Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();
  // Using boolean to check if the task is running
  private static Boolean taskIsRunning = false;
  // Set task id
  private static int repeatingTaskID = -1;
  // Task name
  private final String taskName = "track64DiamondBlocksInInventory";
  // Task delay in seconds
  private final int taskDelayInSeconds = 5;
  // Task period in minutes
  private final int taskPeriodInSeconds = 60;

  public Cool_kids_club(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.DIAMOND_BLOCK, "Cool Kids Club", AdvancementFrameType.CHALLENGE, true, true, x, y , "Have a stack of diamond blocks"), parent, 1);

    registerEvent(PlayerJoinEvent.class, (e)-> {
      // Check if task is not running
      if (!taskIsRunning) {
        // Run task if at least one player does not have the achievement
        if (!allOnlinePlayersHaveAchievement()) {
          taskIsRunning = true;

          RepeatingTask track64DiamondBlocksInInventory = new RepeatingTask(() -> {

            loopPlayersAndIncrementProgression();

            log.info("Task " + taskName + " repeated successfully");

            // Stop task if all players have achievement
            cancelTaskIfAllOnlinePlayersHaveAchievement();

          }, 20 * taskDelayInSeconds, 20 * taskPeriodInSeconds);

          // Storing task id for use in PlayerQuitEvent
          repeatingTaskID = track64DiamondBlocksInInventory.getId();
          log.info("Repeating task has started running: " + taskName);
        } else {
          log.info("Skipping task: " + taskName + " | " + "All online players have the achievement Cool Kids Club");
        }
      }
    });

    // Delay for a tick to get the proper count of online players when a player quits
    registerEvent(PlayerQuitEvent.class, (e)-> Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
      if (taskIsRunning && onlinePlayers.isEmpty()) {
        Bukkit.getScheduler().cancelTask(repeatingTaskID);
        taskIsRunning = false;
        log.info("Stopped repeating task: " + taskName + " | No players online");
      }
    }, 1));

  }

  private void cancelTaskIfAllOnlinePlayersHaveAchievement() {
    if (allOnlinePlayersHaveAchievement()) {
      Bukkit.getScheduler().cancelTask(repeatingTaskID);
      taskIsRunning = false;
      log.info("Stopped task: " + taskName + " | All players online have achievement");
    }
  }

  // Loop through players and increment progression if they have the required amount of diamond blocks
  private void loopPlayersAndIncrementProgression() {
    ItemStack diamondBlock = new ItemStack(Material.DIAMOND_BLOCK);

    // Loop through each player's inventory to get the amount of diamond blocks the player has
    for (Player p : onlinePlayers) {
      // If player already has advancement, continue to the next player
      if (isGranted(p)) continue;
      int amountInInventory = 0;
      for (ItemStack slot : p.getInventory().getContents()) {
        if (slot == null || !slot.isSimilar(diamondBlock)) continue;
        amountInInventory += slot.getAmount();
      }
      // If the player has 64 or more diamond blocks in their inventory, award achievement
      if (amountInInventory >= 64) {
        incrementProgression(p);
      }
    }
  }

  private boolean allOnlinePlayersHaveAchievement() {
    // Loop through players and add to array if they do not have the achievement
    ArrayList<Player> playersWithoutAchievement = new ArrayList<>();
    for (Player player : onlinePlayers) {
      if (!isGranted(player)) {
        playersWithoutAchievement.add(player);
      }
    }

    // If no players are in the array, don't run task
    if (playersWithoutAchievement.isEmpty()) {
      return true;
      // If array is larger than online player collection, log a warning and don't run the task
    } else if (playersWithoutAchievement.size() > onlinePlayers.size()) {
      log.warning("playersWithOutAchievement array is larger than online players array!");
      log.warning("Skipping task: " + taskName + " | Players online: " + onlinePlayers.size() + " | " + "Players in array: " + playersWithoutAchievement.size());
      // Returning true will stop task from starting
      return true;
    } else {
      // Players are in the array, so task will run
      return false;
    }

  }

}