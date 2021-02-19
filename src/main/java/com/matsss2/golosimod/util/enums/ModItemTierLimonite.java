package com.matsss2.golosimod.util.enums;

import java.util.function.Supplier;

import com.matsss2.golosimod.init.ItemInit;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public enum ModItemTierLimonite implements IItemTier
{
	//harvestLevel, maxUses, efficiency, attackDamage, enchantability, repairMaterial
	LIMONITE(2, 521, 7.0F, 1.5F, 16, () -> 
	{
		return Ingredient.fromItems(ItemInit.LIMONITE.get());
	});
	
	private final int harvestLevel;
	private final int maxUses;
	private final float efficiency;
	private final float attackDamage;
	private final int enchantability;
	private final LazyValue<Ingredient> repairMaterial;
	
	private ModItemTierLimonite (int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) 
	{
		this.harvestLevel=harvestLevel;
		this.maxUses=maxUses;
		this.efficiency=efficiency;
		this.attackDamage=attackDamage;
		this.enchantability=enchantability;
		this.repairMaterial=new LazyValue<>(repairMaterial);
	}

	@Override
	public int getMaxUses() {
		return this.maxUses;
	}

	@Override
	public float getEfficiency() {
		return this.efficiency;
	}

	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}

	@Override
	public int getHarvestLevel() {
		return this.harvestLevel;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return this.repairMaterial.getValue();
	}
}
