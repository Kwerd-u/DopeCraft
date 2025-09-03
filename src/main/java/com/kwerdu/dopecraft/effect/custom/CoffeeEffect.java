package com.kwerdu.dopecraft.effect.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class CoffeeEffect extends MobEffect {
    private static final UUID COFFEE_SPEED_MODIFIER_UUID = UUID.fromString("e18550de-2ea3-4c21-a841-8d1984f4e05d");
    private static final UUID COFEE_ATACK_SPEED = UUID.fromString("83cdbe62-6aeb-4e64-a943-a49dcad4e8f2");

    public CoffeeEffect(){
        super(MobEffectCategory.BENEFICIAL, 0x00FF00);
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier){
        double speedBoost = 0.1D * (amplifier);
        double atackSpeedBoost = 0.1D * (amplifier);
        amplifier -= 1;
        AttributeModifier speedModifier = new AttributeModifier(
                COFFEE_SPEED_MODIFIER_UUID,
                "coffee speed modifier",
                speedBoost,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );

        AttributeModifier atackSpeedModifier = new AttributeModifier(
                COFEE_ATACK_SPEED,
                "coffee atack speed modifier",
                atackSpeedBoost,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );

        attributeMap.getInstance(Attributes.MOVEMENT_SPEED).addPermanentModifier(speedModifier);
        attributeMap.getInstance(Attributes.ATTACK_SPEED).addPermanentModifier(atackSpeedModifier);

        super.addAttributeModifiers(entity, attributeMap, amplifier);
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        attributeMap.getInstance(Attributes.MOVEMENT_SPEED).removeModifier(COFFEE_SPEED_MODIFIER_UUID);
        attributeMap.getInstance(Attributes.ATTACK_SPEED).removeModifier(COFEE_ATACK_SPEED);
        super.removeAttributeModifiers(entity, attributeMap, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }

}
