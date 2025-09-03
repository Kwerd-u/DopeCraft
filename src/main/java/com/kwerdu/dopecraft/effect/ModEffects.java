package com.kwerdu.dopecraft.effect;

import com.kwerdu.dopecraft.DopeCraft;
import com.kwerdu.dopecraft.effect.custom.CoffeeEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {

    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, DopeCraft.MODID);


    public static final RegistryObject<MobEffect> COFFEE_EFFECT = EFFECTS.register("coffee_effect", CoffeeEffect::new);


    public static void register(IEventBus eventBus){
        EFFECTS.register(eventBus);
    }
}
