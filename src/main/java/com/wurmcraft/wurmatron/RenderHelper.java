package com.wurmcraft.wurmatron;

import com.animania.common.entities.chickens.EntityAnimaniaChicken;
import com.animania.common.entities.cows.EntityAnimaniaCow;
import com.animania.common.entities.goats.EntityAnimaniaGoat;
import com.animania.common.entities.horses.EntityAnimaniaHorse;
import com.animania.common.entities.interfaces.IAnimaniaAnimalBase;
import com.animania.common.entities.peacocks.EntityAnimaniaPeacock;
import com.animania.common.entities.pigs.EntityAnimaniaPig;
import com.animania.common.entities.rodents.EntityFerretBase;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehogBase;
import com.animania.common.entities.sheep.EntityAnimaniaSheep;
import com.animania.common.handler.ItemHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RenderHelper {

  public static ItemStack getDisplayFood(EntityLivingBase entity) {
    if (entity instanceof IAnimaniaAnimalBase) {
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

  public static ItemStack getDisplayWater(EntityLivingBase entity) {
    if (entity instanceof IAnimaniaAnimalBase) {
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
