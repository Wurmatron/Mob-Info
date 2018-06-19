package com.wurmcraft.wurmatron;

import com.animania.common.entities.AnimaniaAnimal;
import com.animania.common.entities.chickens.EntityAnimaniaChicken;
import com.animania.common.entities.cows.EntityAnimaniaCow;
import com.animania.common.entities.goats.EntityAnimaniaGoat;
import com.animania.common.entities.horses.EntityAnimaniaHorse;
import com.animania.common.entities.peacocks.EntityAnimaniaPeacock;
import com.animania.common.entities.pigs.EntityAnimaniaPig;
import com.animania.common.entities.rodents.EntityFerretBase;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehogBase;
import com.animania.common.entities.sheep.EntityAnimaniaSheep;
import com.animania.common.handler.ItemHandler;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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

  @EventHandler
  public void init(FMLInitializationEvent event) {
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

  private ItemStack getDisplayFood(EntityLivingBase entity) {
    if (entity instanceof AnimaniaAnimal) {
      if (entity instanceof EntityAnimaniaCow) {
        EntityAnimaniaCow cow = (EntityAnimaniaCow) entity;
        if (!cow.getFed()) {
          return new ItemStack(Items.WHEAT, 1, 0);
        }
      } else if (entity instanceof EntityAnimaniaChicken) {
        EntityAnimaniaChicken chicken = (EntityAnimaniaChicken) entity;
        if (!chicken.getFed()) {
          return new ItemStack(Items.WHEAT_SEEDS, 1, 0);
        }
      } else if (entity instanceof EntityAnimaniaGoat) {
        EntityAnimaniaGoat goat = (EntityAnimaniaGoat) entity;
        if (!goat.getFed()) {
          return new ItemStack(Items.WHEAT, 1, 0);
        }
      } else if (entity instanceof EntityAnimaniaHorse) {
        EntityAnimaniaHorse horse = (EntityAnimaniaHorse) entity;
        if (!horse.getFed()) {
          return new ItemStack(Items.APPLE, 1, 0);
        }
      } else if (entity instanceof EntityAnimaniaPeacock) {
        EntityAnimaniaPeacock peacock = (EntityAnimaniaPeacock) entity;
        if (!peacock.getFed()) {
          return new ItemStack(Items.WHEAT_SEEDS, 1, 0);
        }
      } else if (entity instanceof EntityAnimaniaPig) {
        EntityAnimaniaPig pig = (EntityAnimaniaPig) entity;
        if (!pig.getFed()) {
          return new ItemStack(Items.CARROT, 1, 0);
        }
      } else if (entity instanceof EntityFerretBase) {
        EntityFerretBase ferret = (EntityFerretBase) entity;
        if (!ferret.getFed()) {
          return new ItemStack(Items.EGG, 1, 0);
        }
      } else if (entity instanceof EntityHamster) {
        EntityHamster hamster = (EntityHamster) entity;
        if (!hamster.getFed()) {
          return new ItemStack(ItemHandler.hamsterFood, 1, 0);
        }
      } else if (entity instanceof EntityHedgehogBase) {
        EntityHedgehogBase hamster = (EntityHedgehogBase) entity;
        if (!hamster.getFed()) {
          return new ItemStack(Items.CARROT, 1, 0);
        }
      } else if (entity instanceof EntityAnimaniaSheep) {
        EntityAnimaniaSheep hamster = (EntityAnimaniaSheep) entity;
        if (!hamster.getFed()) {
          return new ItemStack(Items.WHEAT, 1, 0);
        }
      }
    }
    return ItemStack.EMPTY;
  }

  private ItemStack getDisplayWater(EntityLivingBase entity) {
    if (entity instanceof AnimaniaAnimal) {
      ItemStack waterBucket = new ItemStack(Items.WATER_BUCKET, 1, 0);
      if (entity instanceof EntityAnimaniaCow) {
        EntityAnimaniaCow cow = (EntityAnimaniaCow) entity;
        if (!cow.getWatered()) {
          return waterBucket;
        }
      } else if (entity instanceof EntityAnimaniaChicken) {
        EntityAnimaniaChicken chicken = (EntityAnimaniaChicken) entity;
        if (!chicken.getWatered()) {
          return waterBucket;
        }
      } else if (entity instanceof EntityAnimaniaGoat) {
        EntityAnimaniaGoat goat = (EntityAnimaniaGoat) entity;
        if (!goat.getWatered()) {
          return waterBucket;
        }
      } else if (entity instanceof EntityAnimaniaHorse) {
        EntityAnimaniaHorse horse = (EntityAnimaniaHorse) entity;
        if (!horse.getWatered()) {
          return waterBucket;
        }
      } else if (entity instanceof EntityAnimaniaPeacock) {
        EntityAnimaniaPeacock peacock = (EntityAnimaniaPeacock) entity;
        if (!peacock.getWatered()) {
          return waterBucket;
        }
      } else if (entity instanceof EntityAnimaniaPig) {
        EntityAnimaniaPig pig = (EntityAnimaniaPig) entity;
        if (!pig.getWatered()) {
          return waterBucket;
        }
      } else if (entity instanceof EntityFerretBase) {
        EntityFerretBase ferret = (EntityFerretBase) entity;
        if (!ferret.getWatered()) {
          return waterBucket;
        }
      } else if (entity instanceof EntityHamster) {
        EntityHamster hamster = (EntityHamster) entity;
        if (!hamster.getWatered()) {
          return waterBucket;
        }
      } else if (entity instanceof EntityHedgehogBase) {
        EntityHedgehogBase hamster = (EntityHedgehogBase) entity;
        if (!hamster.getWatered()) {
          return waterBucket;
        }
      } else if (entity instanceof EntityAnimaniaSheep) {
        EntityAnimaniaSheep hamster = (EntityAnimaniaSheep) entity;
        if (!hamster.getWatered()) {
          return waterBucket;
        }
      }
    }
    return ItemStack.EMPTY;
  }
}
