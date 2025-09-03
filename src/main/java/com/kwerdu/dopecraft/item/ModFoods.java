package com.kwerdu.dopecraft.item;

import com.kwerdu.dopecraft.effect.EffectLevelRaiser;
import com.kwerdu.dopecraft.effect.ModEffects;
import com.kwerdu.dopecraft.effect.custom.CoffeeEffect;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties COFFEE_CUP = new FoodProperties.Builder()
            .effect(() -> EffectLevelRaiser.raiseEffectLevel(Minecraft.getInstance().player, ModEffects.COFFEE_EFFECT.get(), 4800, 1), 100)
            .nutrition(2)
            .saturationMod(2)
            .fast()
            .build();

    public static final FoodProperties ENERGY_DRINK = new FoodProperties.Builder()
            .effect(() -> EffectLevelRaiser.raiseEffectLevel(Minecraft.getInstance().player, ModEffects.COFFEE_EFFECT.get(), 4800, 2), 100)
            .nutrition(2)
            .saturationMod(2)
            .fast()
            .build();

}
