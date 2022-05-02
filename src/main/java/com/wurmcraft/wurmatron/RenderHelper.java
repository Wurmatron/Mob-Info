package com.wurmcraft.wurmatron;

import com.animania.api.interfaces.IAnimaniaAnimalBase;
import com.animania.api.interfaces.IFoodEating;
import java.util.HashMap;
import java.util.UUID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RenderHelper {

  private static HashMap<UUID, Integer[]> currentItemSelection = new HashMap<>();

  public static ItemStack getDisplayFood(EntityLivingBase entity) {
    if (entity instanceof IAnimaniaAnimalBase) {
      IFoodEating eating = (IFoodEating) entity;
      if (!eating.getFed()) {
        ItemStack[] items = eating.getFoodItems().toArray(new ItemStack[0]);
        if (currentItemSelection.containsKey(entity.getUniqueID())) {
          Integer[] data = currentItemSelection.get(entity.getUniqueID());
          if (data[1] > System.currentTimeMillis() / 1000) {
            if (items.length > data[0]) {
              return items[data[0]];
            }
          } else {
            currentItemSelection.remove(entity.getUniqueID()); // Updates on next frame
          }
        } else {
          int selectionID = entity.world.rand.nextInt(items.length);
          ItemStack nextSelection = items[selectionID];
          currentItemSelection.put(
              entity.getUniqueID(),
              new Integer[] {
                selectionID,
                Math.toIntExact((System.currentTimeMillis() / 1000))
                    + ConfigHandler.itemChangeInterval
              });
          return nextSelection;
        }
      }
    }
    return ItemStack.EMPTY;
  }

  public static ItemStack getDisplayWater(EntityLivingBase entity) {
    if (entity instanceof IAnimaniaAnimalBase) {
      IFoodEating eating = (IFoodEating) entity;
      ItemStack waterBucket = new ItemStack(Items.WATER_BUCKET, 1, 0);
      if (!eating.getWatered()) {
        return waterBucket;
      }
    }
    return ItemStack.EMPTY;
  }
}
