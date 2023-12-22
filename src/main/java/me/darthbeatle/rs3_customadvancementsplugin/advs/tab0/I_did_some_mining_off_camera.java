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

public class I_did_some_mining_off_camera extends BaseAdvancement implements HiddenVisibility {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab0_NAMESPACE, "i_did_some_mining_off_camera");

  // Get instance of plugin
  RS3_CustomAdvancementsPlugin plugin = RS3_CustomAdvancementsPlugin.getPlugin();
  // Plugin logger
  Logger log = plugin.getLogger();
  // Online players
  // '? extends' means 'anything that would work with instanceof Player'
  Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();

  // Using boolean to detect if task is running
  private static Boolean taskIsRunning = false;
  // Task id
  private static int repeatingTaskID = -1;
  // Task name
  private static final String taskName = "trackInventoryFullOfDiamonds";
  // Task delay in seconds
  private final int taskDelayInSeconds = 5;
  // Task period in minutes (since an inventory of diamonds is unheard of for most players, there's no sense running it every few seconds
  private final int taskPeriodInMinutes = (60 * 5);

  public I_did_some_mining_off_camera(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.DIAMOND_PICKAXE, "I Did Some Mining Off Camera", AdvancementFrameType.CHALLENGE, true, true, x, y , "Have an inventory full of diamonds"), parent, 1);

    registerEvent(PlayerJoinEvent.class, (e) -> {
      // Check if task is not running
      if (!taskIsRunning) {
        // Run task if at least one player does not have the achievement
        if (!allOnlinePlayersHaveAchievement()) {
          taskIsRunning = true;

          RepeatingTask trackInventoryFullOfDiamonds = new RepeatingTask(() -> {

            loopPlayersAndIncrementProgression();

//            log.info("Task " + taskName + " repeated successfully");

            // Stop task if all players have achievement
            cancelTaskIfAllOnlinePlayersHaveAchievement();

          }, 20 * taskDelayInSeconds, 20 * taskPeriodInMinutes);

          // Storing task id for use in PlayerQuitEvent
          repeatingTaskID = trackInventoryFullOfDiamonds.getId();
          log.info("Repeating task has started running: " + taskName);
        } else {
          log.info("Skipping task: " + taskName + " | " + "All online players have the achievement I Did Some Mining Off Camera");
        }
      }
    });

    // Delay for a tick to get the proper count of online players when a player quits
    registerEvent(PlayerQuitEvent.class, (e) -> Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
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

  // Loop through players and increment progression if they have an inventory full of diamonds
  private void loopPlayersAndIncrementProgression() {
    ItemStack diamond = new ItemStack(Material.DIAMOND);

    // Loop through each player's inventory to get the amount of diamonds the player has
    for (Player p : onlinePlayers) {
      // If player already has advancement, continue to the next player
      if (isGranted(p)) continue;
      int amountInInventory = 0;
      for (ItemStack slot : p.getInventory().getContents()) {
        if (slot == null || !slot.isSimilar(diamond)) continue;
        amountInInventory += slot.getAmount();
      }
      // If the player has an inventory full of diamonds (2,304), award achievement
      if (amountInInventory >= 2304) {
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