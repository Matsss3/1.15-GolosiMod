package com.matsss2.golosimod.init;

import com.matsss2.golosimod.GolosiMod;
import com.matsss2.golosimod.effects.HypeEffect;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionInit {
	public static final DeferredRegister<Effect> EFFECTS = new DeferredRegister<>(ForgeRegistries.POTIONS,
			GolosiMod.MOD_ID);
	public static final DeferredRegister<Potion> POTIONS = new DeferredRegister<>(ForgeRegistries.POTION_TYPES,
			GolosiMod.MOD_ID);

	// EFFECTS
	public static final RegistryObject<Effect> HYPE_EFFECT = EFFECTS.register("hype_effect",
			() -> new HypeEffect(EffectType.BENEFICIAL, 1376256));

	// POTIONS
	public static final RegistryObject<Potion> HYPE_POTION = POTIONS.register("hype",
			() -> new Potion(new EffectInstance(HYPE_EFFECT.get(), 6000, 0)));

	public static final RegistryObject<Potion> LONG_HYPE_POTION = POTIONS.register("long_hype",
			() -> new Potion(new EffectInstance(HYPE_EFFECT.get(), 9600, 0)));

	public static final RegistryObject<Potion> COKE_BOTTLE = POTIONS.register("coke_bottle", () -> new Potion());

	// RECIPES
	public static void addPotionRecipes() {
		BrewingRecipeRegistry
				.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD, ItemInit.COKE_BUCKET.get(), COKE_BOTTLE.get()));
		BrewingRecipeRegistry
				.addRecipe(new BetterBrewingRecipe(COKE_BOTTLE.get(), ItemInit.SALT.get(), HYPE_POTION.get()));
		BrewingRecipeRegistry
				.addRecipe(new BetterBrewingRecipe(HYPE_POTION.get(), ItemInit.SALT.get(), HYPE_POTION.get()));
	}

	private static class BetterBrewingRecipe implements IBrewingRecipe {
		private final Potion bottleInput;
		private final Item itemInput;
		private final ItemStack itemOutput;

		public BetterBrewingRecipe(Potion bottleInputIn, Item itemInputIn, Potion itemOutputIn) {
			this.bottleInput = bottleInputIn;
			this.itemInput = itemInputIn;
			this.itemOutput = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), itemOutputIn);
		}

		@Override
		public boolean isInput(ItemStack input) {
			return PotionUtils.getPotionFromItem(input).equals(this.bottleInput);
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem().equals(this.itemInput);
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				return this.itemOutput.copy();
			} else {
				return ItemStack.EMPTY;
			}
		}
	}
}
