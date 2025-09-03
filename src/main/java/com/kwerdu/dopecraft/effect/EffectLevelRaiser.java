package com.kwerdu.dopecraft.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class EffectLevelRaiser {
    public static MobEffectInstance raiseEffectLevel(Player player, MobEffect targetEffect, int duration, int basic_amplifier) {
        MobEffectInstance currentEffect = player.getEffect(targetEffect);

        int newAmplifier = (currentEffect != null) ? currentEffect.getAmplifier() + basic_amplifier - 1 : basic_amplifier - 1;

        MobEffectInstance newEffect = new MobEffectInstance(
                targetEffect,
                duration,
                newAmplifier,
                false,  // Частицы (true/false)
                true,   // Видно в инвентаре
                true    // Иконка в HUD
        );

        return newEffect;
    }
}
