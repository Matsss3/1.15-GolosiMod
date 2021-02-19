package com.matsss2.golosimod.world.biomes;

import com.google.common.collect.Lists;
import com.matsss2.golosimod.init.BlockInit;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.BlockWithContextConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.CaveEdgeConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidWithNoiseConfig;

public class CornPlainsBiome extends Biome {

	public static final BlockClusterFeatureConfig CORN_BUSH_CONFIG = (new BlockClusterFeatureConfig.Builder(
			new SimpleBlockStateProvider(BlockInit.CORN_BUSH.get().getDefaultState()),
			new SimpleBlockPlacer())).tries(4).build();
	
	public CornPlainsBiome(Builder biomeBuilder) {
		super(biomeBuilder);

		// CREATURES
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.SHEEP, 12, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.PIG, 10, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.CHICKEN, 10, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.COW, 8, 4, 4));
		this.addSpawn(EntityClassification.AMBIENT, new Biome.SpawnListEntry(EntityType.BAT, 10, 8, 8));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));

		// LAKES
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS,
				Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(Blocks.WATER.getDefaultState()))
						.withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(4))));
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS,
				Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(Blocks.LAVA.getDefaultState()))
						.withPlacement(Placement.LAVA_LAKE.configure(new ChanceConfig(80))));

		// WATER DISKS
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.DISK
						.withConfiguration(new SphereReplaceConfig(Blocks.SAND.getDefaultState(), 7, 2,
								Lists.newArrayList(Blocks.DIRT.getDefaultState(),
										Blocks.GRASS_BLOCK.getDefaultState())))
						.withPlacement(Placement.COUNT_TOP_SOLID.configure(new FrequencyConfig(3))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.DISK
						.withConfiguration(new SphereReplaceConfig(Blocks.CLAY.getDefaultState(), 4, 1,
								Lists.newArrayList(Blocks.DIRT.getDefaultState(), Blocks.CLAY.getDefaultState())))
						.withPlacement(Placement.COUNT_TOP_SOLID.configure(new FrequencyConfig(1))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.DISK
						.withConfiguration(new SphereReplaceConfig(Blocks.GRAVEL.getDefaultState(), 6, 2,
								Lists.newArrayList(Blocks.DIRT.getDefaultState(),
										Blocks.GRASS_BLOCK.getDefaultState())))
						.withPlacement(Placement.COUNT_TOP_SOLID.configure(new FrequencyConfig(1))));

		// TREES
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.NORMAL_TREE.withConfiguration(DefaultBiomeFeatures.field_230129_h_).withPlacement(
						Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));

		// VEGETATION
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.GRASS_CONFIG)
						.withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(20))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.SIMPLE_BLOCK
						.withConfiguration(new BlockWithContextConfig(Blocks.SEAGRASS.getDefaultState(),
								new BlockState[] { Blocks.STONE.getDefaultState() },
								new BlockState[] { Blocks.WATER.getDefaultState() },
								new BlockState[] { Blocks.WATER.getDefaultState() }))
						.withPlacement(Placement.CARVING_MASK
								.configure(new CaveEdgeConfig(GenerationStage.Carving.LIQUID, 0.1F))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.KELP.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
						.withPlacement(Placement.TOP_SOLID_HEIGHTMAP_NOISE_BIASED.configure(
								new TopSolidWithNoiseConfig(80, 80.0D, 0.0D, Heightmap.Type.OCEAN_FLOOR_WG))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_PATCH.withConfiguration(CORN_BUSH_CONFIG)
						.withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(6))));
	}
}
