package com.matsss2.golosimod.world.biomes;

import com.google.common.collect.Lists;
import com.matsss2.golosimod.init.BlockInit;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.BlockWithContextConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.placement.CaveEdgeConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidWithNoiseConfig;

public class SaltBiome extends Biome {

	public static final BlockClusterFeatureConfig FRIED_POTATOS_PLANT_CONFIG = (new BlockClusterFeatureConfig.Builder(
			new SimpleBlockStateProvider(BlockInit.FRIED_POTATOS_PLANT.get().getDefaultState()),
			new SimpleBlockPlacer())).tries(4).build();

	public SaltBiome(Builder biomeBuilder) {
		super(biomeBuilder);
		// CREATURES
		this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.SQUID, 1, 1, 4));
		this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.COD, 10, 3, 6));
		this.addSpawn(EntityClassification.AMBIENT, new Biome.SpawnListEntry(EntityType.BAT, 10, 8, 8));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.DROWNED, 5, 1, 1));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));

		// CAVES
		this.addCarver(GenerationStage.Carving.AIR,
				Biome.createCarver(WorldCarver.CAVE, new ProbabilityConfig(0.06666667F)));
		this.addCarver(GenerationStage.Carving.AIR,
				Biome.createCarver(WorldCarver.CANYON, new ProbabilityConfig(0.02F)));
		this.addCarver(GenerationStage.Carving.LIQUID,
				Biome.createCarver(WorldCarver.UNDERWATER_CANYON, new ProbabilityConfig(0.02F)));
		this.addCarver(GenerationStage.Carving.LIQUID,
				Biome.createCarver(WorldCarver.UNDERWATER_CAVE, new ProbabilityConfig(0.06666667F)));

		// WATER DISKS
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.DISK
						.withConfiguration(new SphereReplaceConfig(Blocks.CLAY.getDefaultState(), 4, 1,
								Lists.newArrayList(Blocks.DIRT.getDefaultState(), Blocks.CLAY.getDefaultState())))
						.withPlacement(Placement.COUNT_TOP_SOLID.configure(new FrequencyConfig(1))));

		// UNDERWATER VEGETATION
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.SIMPLE_BLOCK
						.withConfiguration(new BlockWithContextConfig(Blocks.SEAGRASS.getDefaultState(),
								new BlockState[] { BlockInit.SUGARED_STONE.get().getDefaultState() },
								new BlockState[] { Blocks.WATER.getDefaultState() },
								new BlockState[] { Blocks.WATER.getDefaultState() }))
						.withPlacement(Placement.CARVING_MASK
								.configure(new CaveEdgeConfig(GenerationStage.Carving.LIQUID, 0.1F))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.KELP.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
						.withPlacement(Placement.TOP_SOLID_HEIGHTMAP_NOISE_BIASED.configure(
								new TopSolidWithNoiseConfig(80, 80.0D, 0.0D, Heightmap.Type.OCEAN_FLOOR_WG))));

		// PLANT
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_PATCH.withConfiguration(FRIED_POTATOS_PLANT_CONFIG)
						.withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1))));
		
		// FRIED LAKES
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS,
				Feature.LAKE
						.withConfiguration(new BlockStateFeatureConfig(BlockInit.FRYING_OIL_BLOCK.get().getDefaultState()))
						.withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(8))));
	}
}
