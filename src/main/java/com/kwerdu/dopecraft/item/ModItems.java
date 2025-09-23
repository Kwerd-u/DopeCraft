package com.kwerdu.dopecraft.item;

import com.kwerdu.dopecraft.DopeCraft;
import com.kwerdu.dopecraft.block.ModBlocks;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DopeCraft.MODID);

    public static final RegistryObject<Item> CUP = ITEMS.register("cup",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COFFEE_BEANS = ITEMS.register("coffee_beans",
            () -> new ItemNameBlockItem(
                    ModBlocks.COFFEE_BUSH.get(),
                    new Item.Properties())
    );

    public static final RegistryObject<Item> ROASTED_COFFEE_BEANS = ITEMS.register("roasted_coffee_beans",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAN = ITEMS.register("can",
            () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> COFFEE_CUP = ITEMS.register("coffee_cup",
            () -> new Item(new Item.Properties().food(ModFoods.COFFEE_CUP)));

    public static final RegistryObject<Item> ENERGY_DRINK = ITEMS.register("energy_drink",
            () -> new Item(new Item.Properties().food(ModFoods.ENERGY_DRINK)));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
