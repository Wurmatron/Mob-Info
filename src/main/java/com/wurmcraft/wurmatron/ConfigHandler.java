package com.wurmcraft.wurmatron;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = MobInfo.MODID)
@Config(modid = MobInfo.MODID)
public class ConfigHandler {

  @Config.Comment("Only display's info when you are holding shift")
  public static boolean invertDisplay = false;

  @SubscribeEvent
  public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
    if (event.getModID().equals(MobInfo.MODID)) {
      ConfigManager.load(MobInfo.MODID, Config.Type.INSTANCE);
    }
  }
}
