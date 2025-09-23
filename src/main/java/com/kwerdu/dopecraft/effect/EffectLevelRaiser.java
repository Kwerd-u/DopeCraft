package com.kwerdu.dopecraft.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class EffectLevelRaiser {
    public static void raiseEffectLevel(Player player, MobEffect targetEffect, int duration, int basicAmplifier, boolean isVisible) {
        if (player == null || player.level().isClientSide()) return;

        MobEffectInstance currentEffect = player.getEffect(targetEffect);
        int newAmplifier = (currentEffect != null) ? currentEffect.getAmplifier() + basicAmplifier : basicAmplifier - 1;

        MobEffectInstance newEffect = new MobEffectInstance(
                targetEffect,
                duration,
                newAmplifier,
                isVisible,
                isVisible,
                isVisible
        );

        if (currentEffect != null) {
            player.removeEffect(targetEffect);
        }
        player.addEffect(newEffect);
    }
}