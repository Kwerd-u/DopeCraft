package com.kwerdu.dopecraft;

import com.kwerdu.dopecraft.effect.ModEffects;
import com.kwerdu.dopecraft.effect.custom.CoffeeEffect;
import com.kwerdu.dopecraft.item.ModCreativeModTabs;
import com.kwerdu.dopecraft.item.ModFoods;
import com.kwerdu.dopecraft.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import static com.kwerdu.dopecraft.effect.ModEffects.COFFEE_EFFECT;

@Mod(DopeCraft.MODID)
public class DopeCraft
{
    public static final String MODID = "dopecraft";
    private static final Logger LOGGER = LogUtils.getLogger();

    public DopeCraft(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        ModEffects.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeModTabs.register(modEventBus);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        }
    }
    @SubscribeEvent
    public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        // Проверяем, есть ли у игрока наш эффект
        MobEffectInstance effect = event.getEntity().getEffect(COFFEE_EFFECT.get());
        if (effect != null) {
            int amplifier = effect.getAmplifier(); // Уровень эффекта
            float multiplier = 1.0F + (amplifier + 1) * 0.3F; // +30% на уровень 0, +60% на 1 и т.д. (как Haste)
            event.setNewSpeed(event.getNewSpeed() * multiplier); // Применяем множитель к скорости копания
        }
    }
}
