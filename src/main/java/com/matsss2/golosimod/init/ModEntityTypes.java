package com.matsss2.golosimod.init;

import com.matsss2.golosimod.GolosiMod;
import com.matsss2.golosimod.entities.ChocolateBeetle;
import com.matsss2.golosimod.entities.CornSnake;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES,
			GolosiMod.MOD_ID);

	public static final RegistryObject<EntityType<ChocolateBeetle>> CHOCOLATE_BEETLE = ENTITY_TYPES.register(
			"chocolate_beetle",
			() -> EntityType.Builder.<ChocolateBeetle>create(ChocolateBeetle::new, EntityClassification.CREATURE)
					.size(0.9f, 0.5f).build(new ResourceLocation(GolosiMod.MOD_ID, "chocolate_beetle").toString()));

	public static final RegistryObject<EntityType<CornSnake>> CORN_SNAKE = ENTITY_TYPES.register("corn_snake",
			() -> EntityType.Builder.<CornSnake>create(CornSnake::new, EntityClassification.CREATURE).size(0.7f, 0.2f)
					.build(new ResourceLocation(GolosiMod.MOD_ID, "corn_snake").toString()));

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void registerChocolateBeetlePlacementType(EntityType type,
			EntitySpawnPlacementRegistry.PlacementType spawnType) {
		EntitySpawnPlacementRegistry.register(type, spawnType, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				ChocolateBeetle::canAnimalSpawn);
	}

	public static void registerChocolateBeetlePlacementTypes() {
		registerChocolateBeetlePlacementType(ModEntityTypes.CHOCOLATE_BEETLE.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void registerCornSnakePlacementType(EntityType type,
			EntitySpawnPlacementRegistry.PlacementType spawnType) {
		EntitySpawnPlacementRegistry.register(type, spawnType, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				CornSnake::canAnimalSpawn);
	}

	public static void registerCornSnakePlacementTypes() {
		registerCornSnakePlacementType(ModEntityTypes.CORN_SNAKE.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND);
	}
}
