package com.kwerdu.dopecraft.item;

import com.kwerdu.dopecraft.DopeCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, com.kwerdu.dopecraft.DopeCraft.MODID);

    public static final RegistryObject<CreativeModeTab> DopeCraft = CREATIVE_MODE_TABS.register("dopecraft_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.COFFEE_CUP.get()))
                    .title(Component.literal("DopeCraft"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.COFFEE_CUP.get());
                        output.accept(ModItems.CUP.get());
                        output.accept(ModItems.COFFEE_BEANS.get());
                        output.accept(ModItems.ROASTED_COFFEE_BEANS.get());
                        output.accept(ModItems.ENERGY_DRINK.get());
                        output.accept(ModItems.CAN.get());
                    })

                    .build());



    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
