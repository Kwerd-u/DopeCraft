package com.kwerdu.dopecraft.event;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.kwerdu.dopecraft.effect.ModEffects.CAFFEINE_CRASH_EFFECT;
import static com.kwerdu.dopecraft.effect.ModEffects.CAFFEINE_EFFECT;

@Mod.EventBusSubscriber(modid = "dopecraft", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class onBreakSpeedEvent {

    @SubscribeEvent
    public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        MobEffectInstance effect = player.getEffect(CAFFEINE_EFFECT.get());
        if (effect != null) {
            int amplifier = effect.getAmplifier();
            float multiplier = 1.0F + (amplifier + 1) * 0.3F;
            event.setNewSpeed(event.getNewSpeed() * multiplier);
        }

        effect = player.getEffect(CAFFEINE_CRASH_EFFECT.get());
        if (effect != null) {
            int amplifier = effect.getAmplifier();
            float multiplier = 1.0F - (amplifier + 1) * 0.3F;
            event.setNewSpeed(event.getNewSpeed() * multiplier);
        }
    }
}
