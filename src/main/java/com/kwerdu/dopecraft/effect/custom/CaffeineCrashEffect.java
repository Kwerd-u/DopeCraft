package com.kwerdu.dopecraft.effect.custom;

import com.kwerdu.dopecraft.DopeCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class CaffeineCrashEffect extends MobEffect {
    private static final UUID COFFEE_CRASH_SPEED = UUID.fromString("bcd97ea1-9314-4837-aace-2f56519dbea2");
    private static final UUID COFFEE_CRASH_ATACK_SPEED = UUID.fromString("4cdbab44-ebf4-420f-a06e-546748127a61");
    private Minecraft mc = Minecraft.getInstance();

    public CaffeineCrashEffect() {
        super(MobEffectCategory.HARMFUL, 0x00FF00); // Цвет эффекта (зеленый)
    }

    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier){
        double speedDebuff = -0.1D * (amplifier);
        double atackSpeedDebuff = -0.1D * (amplifier);
        amplifier -= 1;
        AttributeModifier speedModifier = new AttributeModifier(
                COFFEE_CRASH_SPEED,
                "coffee speed debuff",
                speedDebuff,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );

        AttributeModifier atackSpeedModifier = new AttributeModifier(
                COFFEE_CRASH_ATACK_SPEED,
                "coffee atack speed debuff",
                atackSpeedDebuff,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );

        attributeMap.getInstance(Attributes.MOVEMENT_SPEED).addPermanentModifier(speedModifier);
        attributeMap.getInstance(Attributes.ATTACK_SPEED).addPermanentModifier(atackSpeedModifier);

        super.addAttributeModifiers(entity, attributeMap, amplifier);



        mc.gameRenderer.loadEffect(new ResourceLocation(DopeCraft.MODID, "pulse_effect")); // ищет assets/<modid>/shaders/post/my_effect.json
        PostChain si = mc.gameRenderer.currentEffect();
    }


//    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
//
//
//
//    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        attributeMap.getInstance(Attributes.MOVEMENT_SPEED).removeModifier(COFFEE_CRASH_SPEED);
        attributeMap.getInstance(Attributes.ATTACK_SPEED).removeModifier(COFFEE_CRASH_ATACK_SPEED);
        super.removeAttributeModifiers(entity, attributeMap, amplifier);
        mc.gameRenderer.shutdownEffect();
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }
}