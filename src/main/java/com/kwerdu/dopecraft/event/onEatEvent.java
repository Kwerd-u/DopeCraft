package com.kwerdu.dopecraft.event;

import com.kwerdu.dopecraft.effect.ModEffects;
import com.kwerdu.dopecraft.effect.custom.CaffeineEffect;
import com.kwerdu.dopecraft.item.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "dopecraft", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class onEatEvent {
    @SubscribeEvent
    public static void onUseItem(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player player && !player.level().isClientSide()) {
            if (event.getItem().getItem() == ModItems.COFFEE_CUP.get()) {
                CaffeineEffect.raiseEffect(event, 1);
            }
            else if (event.getItem().getItem() == ModItems.ENERGY_DRINK.get()) {
                CaffeineEffect.raiseEffect(event, 2);
            }
            else if (event.getItem().getItem() == Items.MILK_BUCKET) {
                if (player.getPersistentData().contains("dopecraft:caffeine_crash")){
                    int amplifier = player.getPersistentData().getInt("dopecraft:caffeine_crash");
                    player.addEffect(new MobEffectInstance(ModEffects.CAFFEINE_CRASH_EFFECT.get(), -1, amplifier, true, true, true));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEffectExpire(MobEffectEvent.Expired event) {
        if (event.getEntity() instanceof Player player) {
            if (!player.level().isClientSide()) {
                if (event.getEffectInstance().getEffect() == ModEffects.CAFFEINE_EFFECT.get()) {
                    CaffeineEffect.applyDebuff(event);
                }
            }
        }
    }
}