package com.matsss2.golosimod.init;

import com.matsss2.golosimod.GolosiMod;
import com.matsss2.golosimod.GolosiMod.GolosiItemGroup;
import com.matsss2.golosimod.objects.items.GolosiPortalItem;
import com.matsss2.golosimod.objects.items.ModSpawnEggItem;
import com.matsss2.golosimod.util.enums.ModArmorMaterial;
import com.matsss2.golosimod.util.enums.ModArmorMaterialLimonite;
import com.matsss2.golosimod.util.enums.ModItemTierBornite;
import com.matsss2.golosimod.util.enums.ModItemTierLimonite;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Food;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, GolosiMod.MOD_ID);

	// ITEMS
	public static final RegistryObject<Item> BORNITE = ITEMS.register("bornite",
			() -> new Item(new Item.Properties().group(GolosiItemGroup.instance)));
	public static final RegistryObject<Item> BDLECHE = ITEMS.register("bdleche",
			() -> new Item(new Item.Properties().group(GolosiItemGroup.instance)));
	public static final RegistryObject<Item> DLECHE = ITEMS.register("dleche",
			() -> new Item(new Item.Properties().group(GolosiItemGroup.instance)));
	public static final RegistryObject<Item> BORNITE_INGOT = ITEMS.register("bornite_ingot",
			() -> new Item(new Item.Properties().group(GolosiItemGroup.instance)));
	public static final RegistryObject<Item> LEMON = ITEMS.register("lemon",
			() -> new Item(new Item.Properties().group(GolosiItemGroup.instance)));
	public static final RegistryObject<Item> LIMONITE = ITEMS.register("limonite",
			() -> new Item(new Item.Properties().group(GolosiItemGroup.instance)));
	public static final RegistryObject<Item> LIMONITE_INGOT = ITEMS.register("limonite_ingot",
			() -> new Item(new Item.Properties().group(GolosiItemGroup.instance)));
	public static final RegistryObject<Item> CORN_SEED = ITEMS.register("corn_seed",
			() -> new BlockItem(BlockInit.CORN_CROP.get(), new Item.Properties().group(GolosiItemGroup.instance)));
	public static final RegistryObject<Item> SALT = ITEMS.register("salt",
			() -> new Item(new Item.Properties().group(GolosiItemGroup.instance)));
	public static final RegistryObject<Item> GOLOSI_PORTAL = ITEMS.register("golosi_portal",
			() -> new GolosiPortalItem(new Item.Properties().group(GolosiItemGroup.instance).maxStackSize(1)));

	// FOOD
	public static final RegistryObject<Item> ALFMAICENA = ITEMS.register("alfmaicena",
			() -> new Item(new Item.Properties().food(new Food.Builder().hunger(5).saturation(1.2F).build())
					.group(GolosiItemGroup.instance)));
	public static final RegistryObject<Item> LEMON_PIE = ITEMS.register("lemon_pie",
			() -> new Item(new Item.Properties().food(new Food.Builder().hunger(10).saturation(2.0F).build())
					.group(GolosiItemGroup.instance)));
	public static final RegistryObject<Item> LEMON_CANDY = ITEMS.register("lemon_candy",
			() -> new Item(new Item.Properties()
					.food(new Food.Builder().hunger(1).saturation(0.5F)
							.effect(new EffectInstance(Effects.SPEED, 100, 1), 1.0f)
							.effect(new EffectInstance(Effects.NAUSEA, 200, 1), 0.5f).build())
					.group(GolosiItemGroup.instance)));
	public static final RegistryObject<Item> DEAD_CHOCOLATE_BEETLE = ITEMS.register("dead_chocolate_beetle",
			() -> new Item(new Item.Properties()
					.food(new Food.Builder().hunger(2).saturation(0.01f)
							.effect(new EffectInstance(Effects.NAUSEA, 300, 1), 1.0f)
							.effect(new EffectInstance(Effects.HUNGER, 75, 1), 0.6f).build())
					.group(GolosiItemGroup.instance)));
	public static final RegistryObject<Item> CHOCOLATE = ITEMS.register("chocolate",
			() -> new Item(new Item.Properties().food(new Food.Builder().hunger(3).saturation(0.2f).build())
					.group(GolosiItemGroup.instance)));
	public static final RegistryObject<Item> CORN = ITEMS.register("corn", () -> new Item(new Item.Properties()
			.food(new Food.Builder().hunger(2).saturation(0.1F).build()).group(GolosiItemGroup.instance)));
	public static final RegistryObject<Item> FRIED_POTATO = ITEMS.register("fried_potato",
			() -> new Item(new Item.Properties()
					.food(new Food.Builder().hunger(3).saturation(0.01f)
							.effect(new EffectInstance(Effects.HUNGER, 400, 1), 1.0f)
							.effect(new EffectInstance(Effects.SPEED, 100, 1), 0.9f).build())
					.group(GolosiItemGroup.instance)));

	// BUCKETS
	public static final RegistryObject<BucketItem> COKE_BUCKET = ITEMS.register("coke_bucket",
			() -> new BucketItem(() -> FluidInit.COKE_FLUID.get(),
					new Item.Properties().group(GolosiItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<BucketItem> FRYING_OIL_BUCKET = ITEMS.register("frying_oil_bucket",
			() -> new BucketItem(() -> FluidInit.FRYING_OIL_FLUID.get(),
					new Item.Properties().group(GolosiItemGroup.instance).maxStackSize(1)));

	// TOOLS
	// Bornite
	public static final RegistryObject<SwordItem> BORNITE_SWORD = ITEMS.register("bornite_sword",
			() -> new SwordItem(ModItemTierBornite.BORNITE, 7, -2.4F,
					new Item.Properties().group(GolosiItemGroup.instance)));
	public static final RegistryObject<PickaxeItem> BORNITE_PICKAXE = ITEMS.register("bornite_pickaxe",
			() -> new PickaxeItem(ModItemTierBornite.BORNITE, 0, -2.8F,
					new Item.Properties().group(GolosiItemGroup.instance)));
	public static final RegistryObject<ShovelItem> BORNITE_SHOVEL = ITEMS.register("bornite_shovel",
			() -> new ShovelItem(ModItemTierBornite.BORNITE, 0, -2.8F,
					new Item.Properties().group(GolosiItemGroup.instance)));
	public static final RegistryObject<AxeItem> BORNITE_AXE = ITEMS.register("bornite_axe",
			() -> new AxeItem(ModItemTierBornite.BORNITE, 9, -3.1F,
					new Item.Properties().group(GolosiItemGroup.instance)));
	public static final RegistryObject<HoeItem> BORNITE_HOE = ITEMS.register("bornite_hoe",
			() -> new HoeItem(ModItemTierBornite.BORNITE, -1.8F,
					new Item.Properties().group(GolosiItemGroup.instance)));
	// Limonite
	public static final RegistryObject<SwordItem> LIMONITE_SWORD = ITEMS.register("limonite_sword",
			() -> new SwordItem(ModItemTierLimonite.LIMONITE, 5, -2.4F,
					new Item.Properties().group(GolosiItemGroup.instance)));
	public static final RegistryObject<PickaxeItem> LIMONITE_PICKAXE = ITEMS.register("limonite_pickaxe",
			() -> new PickaxeItem(ModItemTierLimonite.LIMONITE, 0, -2.8F,
					new Item.Properties().group(GolosiItemGroup.instance)));
	public static final RegistryObject<ShovelItem> LIMONITE_SHOVEL = ITEMS.register("limonite_shovel",
			() -> new ShovelItem(ModItemTierLimonite.LIMONITE, 0, -2.8F,
					new Item.Properties().group(GolosiItemGroup.instance)));
	public static final RegistryObject<AxeItem> LIMONITE_AXE = ITEMS.register("limonite_axe",
			() -> new AxeItem(ModItemTierLimonite.LIMONITE, 7, -3.1F,
					new Item.Properties().group(GolosiItemGroup.instance)));
	public static final RegistryObject<HoeItem> LIMONITE_HOE = ITEMS.register("limonite_hoe",
			() -> new HoeItem(ModItemTierLimonite.LIMONITE, -1.8F,
					new Item.Properties().group(GolosiItemGroup.instance)));

	// ARMOR
	// Bornite
	public static final RegistryObject<ArmorItem> BORNITE_HELMET = ITEMS.register("bornite_helmet",
			() -> new ArmorItem(ModArmorMaterial.BORNITE, EquipmentSlotType.HEAD,
					new Item.Properties().group(GolosiItemGroup.instance)));
	public static final RegistryObject<ArmorItem> BORNITE_CHESTPLATE = ITEMS.register("bornite_chestplate",
			() -> new ArmorItem(ModArmorMaterial.BORNITE, EquipmentSlotType.CHEST,
					new Item.Properties().group(GolosiItemGroup.instance)));
	public static final RegistryObject<ArmorItem> BORNITE_LEGGINGS = ITEMS.register("bornite_leggings",
			() -> new ArmorItem(ModArmorMaterial.BORNITE, EquipmentSlotType.LEGS,
					new Item.Properties().group(GolosiItemGroup.instance)));
	public static final RegistryObject<ArmorItem> BORNITE_BOOTS = ITEMS.register("bornite_boots",
			() -> new ArmorItem(ModArmorMaterial.BORNITE, EquipmentSlotType.FEET,
					new Item.Properties().group(GolosiItemGroup.instance)));
	// Limonite
	public static final RegistryObject<ArmorItem> LIMONITE_HELMET = ITEMS.register("limonite_helmet",
			() -> new ArmorItem(ModArmorMaterialLimonite.LIMONITE, EquipmentSlotType.HEAD,
					new Item.Properties().group(GolosiItemGroup.instance)));
	public static final RegistryObject<ArmorItem> LIMONITE_CHESTPLATE = ITEMS.register("limonite_chestplate",
			() -> new ArmorItem(ModArmorMaterialLimonite.LIMONITE, EquipmentSlotType.CHEST,
					new Item.Properties().group(GolosiItemGroup.instance)));
	public static final RegistryObject<ArmorItem> LIMONITE_LEGGINGS = ITEMS.register("limonite_leggings",
			() -> new ArmorItem(ModArmorMaterialLimonite.LIMONITE, EquipmentSlotType.LEGS,
					new Item.Properties().group(GolosiItemGroup.instance)));
	public static final RegistryObject<ArmorItem> LIMONITE_BOOTS = ITEMS.register("limonite_boots",
			() -> new ArmorItem(ModArmorMaterialLimonite.LIMONITE, EquipmentSlotType.FEET,
					new Item.Properties().group(GolosiItemGroup.instance)));

	// SPAWN EGGS
	public static final RegistryObject<ModSpawnEggItem> CHOCOLATE_BEETLE_SPAWN_EGG = ITEMS
			.register("chocolate_beetle_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.CHOCOLATE_BEETLE, 4264463,
					3014656, new Item.Properties().group(GolosiItemGroup.instance).maxStackSize(16)));
	public static final RegistryObject<ModSpawnEggItem> CORN_SNAKE_SPAWN_EGG = ITEMS.register("corn_snake_spawn_egg",
			() -> new ModSpawnEggItem(ModEntityTypes.CORN_SNAKE, 16768600, 16761143,
					new Item.Properties().group(GolosiItemGroup.instance).maxStackSize(16)));
}
