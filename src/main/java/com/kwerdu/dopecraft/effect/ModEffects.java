package com.kwerdu.dopecraft.effect;

import com.kwerdu.dopecraft.DopeCraft;
import com.kwerdu.dopecraft.effect.custom.CaffeineCrashEffect;
import com.kwerdu.dopecraft.effect.custom.CaffeineEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {

    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, DopeCraft.MODID);


    public static final RegistryObject<MobEffect> CAFFEINE_EFFECT = EFFECTS.register("caffeine_effect", CaffeineEffect::new);
    public static final RegistryObject<MobEffect> CAFFEINE_CRASH_EFFECT = EFFECTS.register("caffeine_crash_effect", CaffeineCrashEffect::new);

    public static void register(IEventBus eventBus){
        EFFECTS.register(eventBus);
    }
}
