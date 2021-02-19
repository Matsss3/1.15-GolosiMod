package com.matsss2.golosimod;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import com.matsss2.golosimod.init.BiomeInit;
import com.matsss2.golosimod.init.BlockInit;
import com.matsss2.golosimod.init.DimensionInit;
import com.matsss2.golosimod.init.FluidInit;
import com.matsss2.golosimod.init.ItemInit;
import com.matsss2.golosimod.init.ModContainerTypes;
import com.matsss2.golosimod.init.ModEntityTypes;
import com.matsss2.golosimod.init.ModTileEntityTypes;
import com.matsss2.golosimod.init.PotionInit;
import com.matsss2.golosimod.init.RecipeSerializerInit;
import com.matsss2.golosimod.objects.blocks.CornCrop;
import com.matsss2.golosimod.objects.blocks.ModBushBlock;
import com.matsss2.golosimod.objects.items.ModSpawnEggItem;
import com.matsss2.golosimod.world.biomes.CornPlainsBiome;
import com.matsss2.golosimod.world.biomes.WhiteChocolateBiome;
import com.matsss2.golosimod.world.gen.ModOreGen;

@SuppressWarnings("deprecation")
@Mod("golosimod")
@Mod.EventBusSubscriber(modid = GolosiMod.MOD_ID, bus = Bus.MOD)
public class GolosiMod {
	public static final String MOD_ID = "golosimod";
	public static final Logger LOGGER = LogManager.getLogger();
	public static GolosiMod instance;
	public static final ResourceLocation GOLOSI_DIM_TYPE = new ResourceLocation(MOD_ID, "golosi_dimension");
	public static final Marker DIMMGR = MarkerManager.getMarker("DIMS");

	public GolosiMod() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setup);

		ItemInit.ITEMS.register(modEventBus);
		RecipeSerializerInit.RECIPE_SERIALIZERS.register(modEventBus);
		FluidInit.FLUIDS.register(modEventBus);
		BlockInit.BLOCKS.register(modEventBus);
		PotionInit.EFFECTS.register(modEventBus);
		PotionInit.POTIONS.register(modEventBus);
		ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
		ModContainerTypes.CONTAINER_TYPES.register(modEventBus);
		ModEntityTypes.ENTITY_TYPES.register(modEventBus);
		BiomeInit.BIOMES.register(modEventBus);
		DimensionInit.MOD_DIMENSIONS.register(modEventBus);

		instance = this;
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();

		BlockInit.BLOCKS
				.getEntries().stream().filter(block -> !(block.get() instanceof FlowingFluidBlock)
						&& !(block.get() instanceof CornCrop) && !(block.get() instanceof ModBushBlock))
				.map(RegistryObject::get).forEach(block -> {
					final Item.Properties properties = new Item.Properties().group(GolosiItemGroup.instance);
					final BlockItem blockItem = new BlockItem(block, properties);
					blockItem.setRegistryName(block.getRegistryName());
					registry.register(blockItem);
				});
		LOGGER.debug("Registered BlockItems!");
	}

	@SubscribeEvent
	public static void registerBiomes(final RegistryEvent.Register<Biome> event) {
		BiomeInit.registerBiomes();
	}

	private void setup(final FMLCommonSetupEvent event) {
		ModEntityTypes.registerChocolateBeetlePlacementTypes();
		ModEntityTypes.registerCornSnakePlacementTypes();
		DeferredWorkQueue.runLater(ModOreGen::generatedOre);
		PotionInit.addPotionRecipes();
		DeferredWorkQueue.runLater(() -> {
			for (Biome biome : ForgeRegistries.BIOMES) {
				if (biome instanceof WhiteChocolateBiome) {
					biome.getSpawns(EntityClassification.CREATURE)
							.add(new Biome.SpawnListEntry(ModEntityTypes.CHOCOLATE_BEETLE.get(), 4000, 2, 4));
				}
			}
		});
		DeferredWorkQueue.runLater(() -> {
			for (Biome biome : ForgeRegistries.BIOMES) {
				if (biome instanceof CornPlainsBiome) {
					biome.getSpawns(EntityClassification.CREATURE)
							.add(new Biome.SpawnListEntry(ModEntityTypes.CORN_SNAKE.get(), 4000, 1, 4));
				}
			}
		});
	}

	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {

	}

	@SubscribeEvent
	public static void loadCompleteEvent(FMLLoadCompleteEvent event) {
		ModOreGen.generatedOre();
	}

	@SubscribeEvent
	public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
		ModSpawnEggItem.initSpawnEggs();
	}

	public static class GolosiItemGroup extends ItemGroup {
		public static final GolosiItemGroup instance = new GolosiItemGroup(ItemGroup.GROUPS.length, "golositab");

		private GolosiItemGroup(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemInit.ALFMAICENA.get());
		}
	}
}
