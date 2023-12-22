package me.darthbeatle.rs3_customadvancementsplugin;

import com.fren_gor.ultimateAdvancementAPI.AdvancementTab;
import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI;
import com.fren_gor.ultimateAdvancementAPI.advancement.RootAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.events.PlayerLoadingCompletedEvent;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementUtils;
import com.fren_gor.ultimateAdvancementAPI.util.CoordAdapter;
import me.darthbeatle.rs3_customadvancementsplugin.advs.AdvancementTabNamespaces;
import me.darthbeatle.rs3_customadvancementsplugin.advs.tab0.*;
import me.darthbeatle.rs3_customadvancementsplugin.util.DelayedTask;
import me.darthbeatle.rs3_customadvancementsplugin.util.RepeatingTask;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class RS3_CustomAdvancementsPlugin extends JavaPlugin implements Listener {

    private static RS3_CustomAdvancementsPlugin plugin;
    Logger log = Bukkit.getLogger();

    @Override
    public void onEnable() {
        plugin = this;

        Bukkit.getPluginManager().registerEvents(this, this);
        new DelayedTask(this);
        new RepeatingTask(this);

        initializeTabs();
    }

    public static UltimateAdvancementAPI api;
    public AdvancementTab tab0;

    public void initializeTabs() {
        api = UltimateAdvancementAPI.getInstance(this);
        tab0 = api.createAdvancementTab(AdvancementTabNamespaces.tab0_NAMESPACE);
        AdvancementKey rs3_root_0Key = new AdvancementKey(tab0.getNamespace(), "rs3_root_0");
        CoordAdapter adaptertab0 = CoordAdapter.builder().add(rs3_root_0Key, 0f, 0f).add(I_guess_thats_it.KEY, 1f, 1f).add(_250_pitcher_pods.KEY, 1f, -10f).add(Bee_in_a_boat.KEY, 1f, 0f).add(Hi_squidward.KEY, 1f, 2f).add(Sponge_out_of_water.KEY, 1f, -1f).add(Fly_home_buddy.KEY, 1f, -9f).add(Astronaut_in_the_ocean.KEY, 1f, -2f).add(Ice_dweller.KEY, 1f, -11f).add(Yankee_with_no_trim.KEY, 1f, 3f).add(Simon_didnt_say_stand_up.KEY, 1f, 4f).add(Peter.KEY, 1f, -3f).add(The_horse_is_here.KEY, 2f, -3f).add(Schizophrenia.KEY, 1f, -12f).add(Music_man.KEY, 1f, -4f).add(Big_shot.KEY, 1f, 5f).add(Dementia_coaster_1.KEY, 1f, 6f).add(Dementia_coaster_2.KEY, 2f, 6f).add(Dementia_coaster_3.KEY, 3f, 6f).add(Dementia_coaster_4.KEY, 4f, 6f).add(Oops_all_goat_horns.KEY, 1f, -5f).add(I_did_some_mining_off_camera.KEY, 1f, -13f).add(Cool_kids_club.KEY, 1f, -14f).add(The_flexiest_of_flexes.KEY, 1f, -15f).add(Dimensional_analysus.KEY, 1f, -8f).add(Walking_on_sunshine.KEY, 1f, 7f).add(Walkin_on_the_sun.KEY, 2f, 7f).add(Walk_like_an_egyptian.KEY, 3f, 7f).add(Walkin_back_to_the_nether_hub.KEY, 4f, 7f).add(Im_gonna_be_500000_miles.KEY, 5f, 7f).add(These_boots_are_made_for_walking.KEY, 6f, 7f).build();
        RootAdvancement rs3_root_0 = new RootAdvancement(tab0, rs3_root_0Key.getKey(), new AdvancementDisplay(Material.NETHER_STAR, "Realms Season 3 Advancements", AdvancementFrameType.CHALLENGE, true, true, adaptertab0.getX(rs3_root_0Key), adaptertab0.getY(rs3_root_0Key), "§6Gotta collect them all!"),"textures/block/magenta_terracotta.png",1);
        I_guess_thats_it i_guess_thats_it = new I_guess_thats_it(rs3_root_0,adaptertab0.getX(I_guess_thats_it.KEY), adaptertab0.getY(I_guess_thats_it.KEY));
        _250_pitcher_pods _250_pitcher_pods = new _250_pitcher_pods(rs3_root_0,adaptertab0.getX(me.darthbeatle.rs3_customadvancementsplugin.advs.tab0._250_pitcher_pods.KEY), adaptertab0.getY(me.darthbeatle.rs3_customadvancementsplugin.advs.tab0._250_pitcher_pods.KEY));
        Bee_in_a_boat bee_in_a_boat = new Bee_in_a_boat(rs3_root_0,adaptertab0.getX(Bee_in_a_boat.KEY), adaptertab0.getY(Bee_in_a_boat.KEY));
        Hi_squidward hi_squidward = new Hi_squidward(rs3_root_0,adaptertab0.getX(Hi_squidward.KEY), adaptertab0.getY(Hi_squidward.KEY));
        Sponge_out_of_water sponge_out_of_water = new Sponge_out_of_water(rs3_root_0,adaptertab0.getX(Sponge_out_of_water.KEY), adaptertab0.getY(Sponge_out_of_water.KEY));
        Fly_home_buddy fly_home_buddy = new Fly_home_buddy(rs3_root_0,adaptertab0.getX(Fly_home_buddy.KEY), adaptertab0.getY(Fly_home_buddy.KEY));
        Astronaut_in_the_ocean astronaut_in_the_ocean = new Astronaut_in_the_ocean(rs3_root_0,adaptertab0.getX(Astronaut_in_the_ocean.KEY), adaptertab0.getY(Astronaut_in_the_ocean.KEY));
        Ice_dweller ice_dweller = new Ice_dweller(rs3_root_0,adaptertab0.getX(Ice_dweller.KEY), adaptertab0.getY(Ice_dweller.KEY));
        Yankee_with_no_trim yankee_with_no_trim = new Yankee_with_no_trim(rs3_root_0,adaptertab0.getX(Yankee_with_no_trim.KEY), adaptertab0.getY(Yankee_with_no_trim.KEY));
        Simon_didnt_say_stand_up simon_didnt_say_stand_up = new Simon_didnt_say_stand_up(rs3_root_0,adaptertab0.getX(Simon_didnt_say_stand_up.KEY), adaptertab0.getY(Simon_didnt_say_stand_up.KEY));
        Peter peter = new Peter(rs3_root_0,adaptertab0.getX(Peter.KEY), adaptertab0.getY(Peter.KEY));
        The_horse_is_here the_horse_is_here = new The_horse_is_here(peter,adaptertab0.getX(The_horse_is_here.KEY), adaptertab0.getY(The_horse_is_here.KEY));
        Schizophrenia schizophrenia = new Schizophrenia(rs3_root_0,adaptertab0.getX(Schizophrenia.KEY), adaptertab0.getY(Schizophrenia.KEY));
        Music_man music_man = new Music_man(rs3_root_0,adaptertab0.getX(Music_man.KEY), adaptertab0.getY(Music_man.KEY));
        Big_shot big_shot = new Big_shot(rs3_root_0,adaptertab0.getX(Big_shot.KEY), adaptertab0.getY(Big_shot.KEY));
        Dementia_coaster_1 dementia_coaster_1 = new Dementia_coaster_1(rs3_root_0,adaptertab0.getX(Dementia_coaster_1.KEY), adaptertab0.getY(Dementia_coaster_1.KEY));
        Dementia_coaster_2 dementia_coaster_2 = new Dementia_coaster_2(dementia_coaster_1,adaptertab0.getX(Dementia_coaster_2.KEY), adaptertab0.getY(Dementia_coaster_2.KEY));
        Dementia_coaster_3 dementia_coaster_3 = new Dementia_coaster_3(dementia_coaster_2,adaptertab0.getX(Dementia_coaster_3.KEY), adaptertab0.getY(Dementia_coaster_3.KEY));
        Dementia_coaster_4 dementia_coaster_4 = new Dementia_coaster_4(dementia_coaster_3,adaptertab0.getX(Dementia_coaster_4.KEY), adaptertab0.getY(Dementia_coaster_4.KEY));
        Oops_all_goat_horns oops_all_goat_horns = new Oops_all_goat_horns(rs3_root_0,adaptertab0.getX(Oops_all_goat_horns.KEY), adaptertab0.getY(Oops_all_goat_horns.KEY));
        I_did_some_mining_off_camera i_did_some_mining_off_camera = new I_did_some_mining_off_camera(rs3_root_0,adaptertab0.getX(I_did_some_mining_off_camera.KEY), adaptertab0.getY(I_did_some_mining_off_camera.KEY));
        Cool_kids_club cool_kids_club = new Cool_kids_club(rs3_root_0,adaptertab0.getX(Cool_kids_club.KEY), adaptertab0.getY(Cool_kids_club.KEY));
        The_flexiest_of_flexes the_flexiest_of_flexes = new The_flexiest_of_flexes(rs3_root_0,adaptertab0.getX(The_flexiest_of_flexes.KEY), adaptertab0.getY(The_flexiest_of_flexes.KEY));
        Dimensional_analysus dimensional_analysus = new Dimensional_analysus(rs3_root_0,adaptertab0.getX(Dimensional_analysus.KEY), adaptertab0.getY(Dimensional_analysus.KEY));
        Walking_on_sunshine walking_on_sunshine = new Walking_on_sunshine(rs3_root_0,adaptertab0.getX(Walking_on_sunshine.KEY), adaptertab0.getY(Walking_on_sunshine.KEY));
        Walkin_on_the_sun walkin_on_the_sun = new Walkin_on_the_sun(walking_on_sunshine,adaptertab0.getX(Walkin_on_the_sun.KEY), adaptertab0.getY(Walkin_on_the_sun.KEY));
        Walk_like_an_egyptian walk_like_an_egyptian = new Walk_like_an_egyptian(walkin_on_the_sun,adaptertab0.getX(Walk_like_an_egyptian.KEY), adaptertab0.getY(Walk_like_an_egyptian.KEY));
        Walkin_back_to_the_nether_hub walkin_back_to_the_nether_hub = new Walkin_back_to_the_nether_hub(walk_like_an_egyptian,adaptertab0.getX(Walkin_back_to_the_nether_hub.KEY), adaptertab0.getY(Walkin_back_to_the_nether_hub.KEY));
        Im_gonna_be_500000_miles im_gonna_be_500000_miles = new Im_gonna_be_500000_miles(walkin_back_to_the_nether_hub,adaptertab0.getX(Im_gonna_be_500000_miles.KEY), adaptertab0.getY(Im_gonna_be_500000_miles.KEY));
        These_boots_are_made_for_walking these_boots_are_made_for_walking = new These_boots_are_made_for_walking(im_gonna_be_500000_miles,adaptertab0.getX(These_boots_are_made_for_walking.KEY), adaptertab0.getY(These_boots_are_made_for_walking.KEY));
        tab0.registerAdvancements(rs3_root_0 ,i_guess_thats_it ,_250_pitcher_pods ,bee_in_a_boat ,hi_squidward ,sponge_out_of_water ,fly_home_buddy ,astronaut_in_the_ocean ,ice_dweller ,yankee_with_no_trim ,simon_didnt_say_stand_up ,peter ,the_horse_is_here ,schizophrenia ,music_man ,big_shot ,dementia_coaster_1 ,dementia_coaster_2 ,dementia_coaster_3 ,dementia_coaster_4 ,oops_all_goat_horns ,i_did_some_mining_off_camera ,cool_kids_club ,the_flexiest_of_flexes ,dimensional_analysus ,walking_on_sunshine ,walkin_on_the_sun ,walk_like_an_egyptian ,walkin_back_to_the_nether_hub ,im_gonna_be_500000_miles ,these_boots_are_made_for_walking );
    }

    @EventHandler
    public void onJoin(PlayerLoadingCompletedEvent e) {
        // Called after a player has successfully been loaded by the API
        Player p = e.getPlayer();
        // Here you can show tabs to players
        tab0.showTab(p);
        // Delay the root advancement to give time for player data to load
//        AdvancementUtils.runSync(plugin, 5, () -> tab0.grantRootAdvancement(p));
//        log.info("Granted root advancement to: " + p);
    }

    // Get instance of plugin
    public static RS3_CustomAdvancementsPlugin getPlugin() {
        return plugin;
    }

}
