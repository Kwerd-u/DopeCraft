package com.kwerdu.dopecraft.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties COFFEE_CUP = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(2)
            .fast()
            .build();

    public static final FoodProperties ENERGY_DRINK = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(2)
            .fast()
            .build();
}