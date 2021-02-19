package com.matsss2.golosimod.util.enums;

import java.util.function.Supplier;

import com.matsss2.golosimod.GolosiMod;
import com.matsss2.golosimod.init.ItemInit;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

public enum ModArmorMaterial implements IArmorMaterial
{
	BORNITE(GolosiMod.MOD_ID+":bornite", 50, new int[]{5,7,9,3}, 16, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 3.0F, () -> {
		return Ingredient.fromItems(ItemInit.BORNITE.get());
	});
	
	private static final int[] MAX_DAMAGE_ARRAY=new int[] {11,16,15,13};
	private final String name;
	private final int maxDamageFactor;
	private final int[] damageReductionAmountArray;
	private final int enchantability;
	private final SoundEvent soundEvent;
	private final float toughness;
	private final LazyValue<Ingredient> repairMaterial;
	
	private ModArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEvent soundEventIn, float toughnessIn, Supplier<Ingredient> repairMaterialIn) 
	{
		this.name=nameIn;
		this.maxDamageFactor=maxDamageFactorIn;
		this.damageReductionAmountArray=damageReductionAmountArrayIn;
		this.enchantability=enchantabilityIn;
		this.soundEvent=soundEventIn;
		this.toughness=toughnessIn;
		this.repairMaterial=new LazyValue<>(repairMaterialIn);
	}

	@Override
	public int getDurability(EquipmentSlotType slotIn) {
		return MAX_DAMAGE_ARRAY[slotIn.getIndex()]*this.maxDamageFactor;
	}

	@Override
	public int getDamageReductionAmount(EquipmentSlotType slotIn) {
		return this.damageReductionAmountArray[slotIn.getIndex()];
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}
	
	@Override
	public SoundEvent getSoundEvent() {
		return this.soundEvent;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public Ingredient getRepairMaterial() {
		return this.repairMaterial.getValue();
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public float getToughness() {
		return this.toughness;
	} 
}
