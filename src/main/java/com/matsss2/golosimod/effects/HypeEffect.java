package com.matsss2.golosimod.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class HypeEffect extends Effect {

	public HypeEffect(EffectType typeIn, int liquidColorIn) {
		super(EffectType.BENEFICIAL, 1376256);
	}

	@Override
	public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
		if (entityLivingBaseIn.getHealth() < entityLivingBaseIn.getMaxHealth()) {
			entityLivingBaseIn.heal(1.0F);
		}

		addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635",
				(double) 1.0F, AttributeModifier.Operation.MULTIPLY_TOTAL);
	}

	@Override
	public boolean isReady(int duration, int amplifier) {
		return duration == 6000;
	}
}