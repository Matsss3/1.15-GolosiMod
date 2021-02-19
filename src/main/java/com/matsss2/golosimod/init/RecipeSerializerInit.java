package com.matsss2.golosimod.init;

import com.matsss2.golosimod.GolosiMod;
import com.matsss2.golosimod.recipes.IMortarRecipe;
import com.matsss2.golosimod.recipes.IOvenRecipe;
import com.matsss2.golosimod.recipes.MortarRecipe;
import com.matsss2.golosimod.recipes.MortarRecipeSerializer;
import com.matsss2.golosimod.recipes.OvenRecipe;
import com.matsss2.golosimod.recipes.OvenRecipeSerializer;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeSerializerInit {

	// TYPES AND RECIPE SERIALIZERS FOR: OVEN
	public static final IRecipeSerializer<OvenRecipe> OVEN_RECIPE_SERIALIZER = new OvenRecipeSerializer();
	public static final IRecipeType<IOvenRecipe> OVEN_TYPE = registerTypeOven(IOvenRecipe.OVEN_RECIPE_TYPE_ID);

	// TYPES AND RECIPE SERIALIZERS FOR: MORTAR
	public static final IRecipeSerializer<MortarRecipe> MORTAR_RECIPE_SERIALIZER = new MortarRecipeSerializer();
	public static final IRecipeType<IMortarRecipe> MORTAR_TYPE = registerTypeMortar(IMortarRecipe.MORTAR_RECIPE_TYPE_ID);

	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = new DeferredRegister<>(
			ForgeRegistries.RECIPE_SERIALIZERS, GolosiMod.MOD_ID);

	// SERIALIZERS
	public static final RegistryObject<IRecipeSerializer<?>> OVEN_SERIALIZER = RECIPE_SERIALIZERS.register("oven",
			() -> OVEN_RECIPE_SERIALIZER);

	public static final RegistryObject<IRecipeSerializer<?>> MORTAR_SERIALIZER = RECIPE_SERIALIZERS.register("mortar",
			() -> MORTAR_RECIPE_SERIALIZER);

	private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> {
		@Override
		public String toString() {
			return Registry.RECIPE_TYPE.getKey(this).toString();
		}
	}

	// RECIPE TYPES
	private static IRecipeType<IOvenRecipe> registerTypeOven(ResourceLocation recipeTypeId) {
		return Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RecipeType<>());
	}
	
	private static IRecipeType<IMortarRecipe> registerTypeMortar(ResourceLocation recipeTypeId){
		return Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RecipeType<>());
	}
}
