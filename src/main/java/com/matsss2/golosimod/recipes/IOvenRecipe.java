package com.matsss2.golosimod.recipes;

import javax.annotation.Nonnull;

import com.matsss2.golosimod.GolosiMod;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public interface IOvenRecipe extends IRecipe<RecipeWrapper>
{
	ResourceLocation OVEN_RECIPE_TYPE_ID=new ResourceLocation(GolosiMod.MOD_ID, "oven");
	
	@Nonnull
	@Override
	default IRecipeType<?> getType() {
		return Registry.RECIPE_TYPE.getValue(OVEN_RECIPE_TYPE_ID).get();
	}
	
	@Override
	default boolean canFit(int width, int height) {
		return false;
	}
	
	Ingredient getInput();
}
