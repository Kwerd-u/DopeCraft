package com.kwerdu.dopecraft.effect.custom;

import com.kwerdu.dopecraft.effect.EffectLevelRaiser;
import com.kwerdu.dopecraft.effect.ModEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;

import java.util.UUID;

public class CaffeineEffect extends MobEffect {
    private static final UUID COFFEE_SPEED_MODIFIER_UUID = UUID.fromString("e18550de-2ea3-4c21-a841-8d1984f4e05d");
    private static final UUID COFFEE_ATTACK_SPEED_UUID = UUID.fromString("83cdbe62-6aeb-4e64-a943-a49dcad4e8f2");

    public CaffeineEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x00FF00);
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        double speedBoost = 0.1D * (amplifier + 1); // Учитываем, что amplifier начинается с 0
        double attackSpeedBoost = 0.1D * (amplifier + 1);

        AttributeModifier speedModifier = new AttributeModifier(
                COFFEE_SPEED_MODIFIER_UUID,
                "coffee speed modifier",
                speedBoost,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );

        AttributeModifier attackSpeedModifier = new AttributeModifier(
                COFFEE_ATTACK_SPEED_UUID,
                "coffee attack speed modifier",
                attackSpeedBoost,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );

        attributeMap.getInstance(Attributes.MOVEMENT_SPEED).addPermanentModifier(speedModifier);
        attributeMap.getInstance(Attributes.ATTACK_SPEED).addPermanentModifier(attackSpeedModifier);

        super.addAttributeModifiers(entity, attributeMap, amplifier);
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        attributeMap.getInstance(Attributes.MOVEMENT_SPEED).removeModifier(COFFEE_SPEED_MODIFIER_UUID);
        attributeMap.getInstance(Attributes.ATTACK_SPEED).removeModifier(COFFEE_ATTACK_SPEED_UUID);
        super.removeAttributeModifiers(entity, attributeMap, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }

    public static void raiseEffect(LivingEntityUseItemEvent.Finish event, int basicAmplifier){
        Player player = (Player) event.getEntity();

        EffectLevelRaiser.raiseEffectLevel(player, ModEffects.CAFFEINE_EFFECT.get(), 100, basicAmplifier, true);

        CompoundTag compoundTag = player.getPersistentData();
        if (compoundTag.contains("dopecraft:caffeine_crash")){
            int currentAmplifier = compoundTag.getInt("dopecraft:caffeine_crash");
            int effectAmplifier = player.getEffect(ModEffects.CAFFEINE_EFFECT.get()).getAmplifier();
            int newAmplifier = Math.max(currentAmplifier, effectAmplifier);

            compoundTag.putInt("dopecraft:caffeine_crash", newAmplifier);
        }
        else {
            int effectAmplifier = player.getEffect(ModEffects.CAFFEINE_EFFECT.get()).getAmplifier();
            compoundTag.putInt("dopecraft:caffeine_crash", effectAmplifier);
        }
    }

    public static void applyDebuff(MobEffectEvent.Expired event){
        Player player = (Player) event.getEntity();
        int amplifier = player.getPersistentData().getInt("dopecraft:caffeine_crash");
        player.addEffect(new MobEffectInstance(ModEffects.CAFFEINE_CRASH_EFFECT.get(), -1, amplifier, true, true, true));
    }

}