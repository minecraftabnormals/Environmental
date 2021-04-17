package com.minecraftabnormals.environmental.common.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.Map.Entry;

public class PanicEffect extends Effect {
	public PanicEffect() {
		super(EffectType.BENEFICIAL, 15494786);
	}

	@Override
	public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier) {
		return 0.05 * (double) (amplifier + 1);
	}

	@Override
	public void applyAttributesModifiersToEntity(LivingEntity entity, AttributeModifierManager attributeMapIn, int amplifier) {
		float amount = entity.getMaxHealth() - entity.getHealth();
		for (Entry<Attribute, AttributeModifier> entry : this.getAttributeModifierMap().entrySet()) {
			ModifiableAttributeInstance iattributeinstance = attributeMapIn.createInstanceIfAbsent(entry.getKey());
			if (iattributeinstance != null) {
				AttributeModifier attributemodifier = entry.getValue();
				iattributeinstance.removeModifier(attributemodifier);
				iattributeinstance.applyPersistentModifier(new AttributeModifier(attributemodifier.getID(), this.getName() + " " + amplifier, amount * this.getAttributeModifierAmount(amplifier, attributemodifier), attributemodifier.getOperation()));
			}
		}
	}

	@Override
	public void performEffect(LivingEntity entity, int amplifier) {
		if (entity instanceof PlayerEntity) {
			this.applyAttributesModifiersToEntity(entity, entity.getAttributeManager(), amplifier);
		}
	}

	@Override
	public boolean isReady(int duration, int amplifier) {
		return true;
	}
}