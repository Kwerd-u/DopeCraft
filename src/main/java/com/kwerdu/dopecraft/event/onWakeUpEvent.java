package com.kwerdu.dopecraft.event;

import com.kwerdu.dopecraft.effect.ModEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "dopecraft", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class onWakeUpEvent {

    @SubscribeEvent
    public static void onWakeUp(PlayerWakeUpEvent event){
        Player player = event.getEntity();
        if (player.getPersistentData().contains("dopecraft:caffeine_crash")){
            player.getPersistentData().remove("dopecraft:caffeine_crash");
            player.removeEffect(ModEffects.CAFFEINE_CRASH_EFFECT.get());
        }
    }

}
