package com.wurmcraft.wurmatron;

import static com.wurmcraft.wurmatron.RenderHelper.getDisplayFood;
import static com.wurmcraft.wurmatron.RenderHelper.getDisplayWater;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import org.lwjgl.input.Keyboard;

@Mod(
  modid = MobInfo.MODID,
  name = MobInfo.NAME,
  version = MobInfo.VERSION,
  clientSideOnly = true,
  dependencies = "required-after:animania;"
)
public class MobInfo {

  public static final String MODID = "mobinfo";
  public static final String NAME = "Mob Info";
  public static final String VERSION = "@VERSION@";

  public static final KeyBinding KEY_TOGGLE =
      new KeyBinding("key.mobInfo_toggle.name", Keyboard.KEY_LSHIFT, "category.mobInfo.name");
  private boolean holdingKey = false;

  @EventHandler
  public void preInit(FMLPreInitializationEvent e) {
    ClientRegistry.registerKeyBinding(KEY_TOGGLE);
  }

  @EventHandler
  public void init(FMLInitializationEvent e) {
    MinecraftForge.EVENT_BUS.register(this);
  }

  private static List<Entity> mobData = new ArrayList<>();

  @SubscribeEvent
  public void onRenderEntity(RenderLivingEvent.Pre e) {
    if (mobData.size() <= 0 || !mobData.contains(e.getEntity())) {
      e.getRenderer()
          .addLayer(
              new LayerRenderer() {
                @Override
                public void doRenderLayer(
                    EntityLivingBase entityLiving,
                    float limbSwing,
                    float limbSwingAmount,
                    float partialTicks,
                    float ageInTicks,
                    float netHeadYaw,
                    float headPitch,
                    float scale) {
                  ItemStack displayItemFood = getDisplayFood(entityLiving);
                  ItemStack displayItem =
                      displayItemFood != ItemStack.EMPTY
                          ? displayItemFood
                          : getDisplayWater(entityLiving);
                  if (!holdingKey && ConfigHandler.invertDisplay
                      || holdingKey && !ConfigHandler.invertDisplay) {
                    displayItem = ItemStack.EMPTY;
                  }
                  if (displayItem != ItemStack.EMPTY) {
                    GlStateManager.pushMatrix();
                    GlStateManager.rotate(180, 0, 0, 1);
                    GlStateManager.scale(0.6, 0.6, 0.6);
                    GlStateManager.rotate(
                        (ageInTicks) / 20.0F * (180F / (float) Math.PI), 0.0F, 1.0F, 0.0F);
                    GlStateManager.translate(
                        0, (e.getEntity().height - 0.15) + (Math.sin(ageInTicks / 20)) / 3, 0);
                    Minecraft.getMinecraft()
                        .getRenderItem()
                        .renderItem(displayItem, ItemCameraTransforms.TransformType.FIXED);
                    GlStateManager.popMatrix();
                  }
                }

                @Override
                public boolean shouldCombineTextures() {
                  return true;
                }
              });
      mobData.add(e.getEntity());
    }
  }

  @SubscribeEvent
  public void onKeyInput(KeyInputEvent e) {
    if (KEY_TOGGLE.isPressed()) {
      holdingKey = !holdingKey;
    }
  }
}
