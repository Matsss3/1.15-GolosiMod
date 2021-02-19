package com.matsss2.golosimod.world.biomes;

import com.google.common.collect.Lists;
import com.matsss2.golosimod.world.feature.ChocolateTree;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.feature.structure.ShipwreckConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.HeightWithChanceConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

public class WhiteChocolateBiome extends Biome {

	public WhiteChocolateBiome(Builder biomeBuilder) {
		super(biomeBuilder);

		// ENTITIES
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
		// STRUCTURES
		this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES,
				Feature.SHIPWRECK.withConfiguration(new ShipwreckConfig(false))
						.withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
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
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.DISK
						.withConfiguration(new SphereReplaceConfig(Blocks.CLAY.getDefaultState(), 4, 1,
								Lists.newArrayList(Blocks.DIRT.getDefaultState(), Blocks.CLAY.getDefaultState())))
						.withPlacement(Placement.COUNT_TOP_SOLID.configure(new FrequencyConfig(1))));
		// BERRY BUSHES
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.SWEET_BERRY_BUSH_CONFIG)
						.withPlacement(Placement.CHANCE_HEIGHTMAP_DOUBLE.configure(new ChanceConfig(12))));
		// VANILLA DENSE GRASS
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.GRASS_CONFIG)
						.withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(20))));
		// VANILLA VEGETATION
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.NORMAL_TREE.withConfiguration(DefaultBiomeFeatures.SWAMP_TREE_CONFIG).withPlacement(
						Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(2, 0.1F, 1))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.FLOWER.withConfiguration(DefaultBiomeFeatures.BLUE_ORCHID_CONFIG)
						.withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(1))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.GRASS_CONFIG)
						.withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(5))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.DEAD_BUSH_CONFIG)
						.withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.LILY_PAD_CONFIG)
						.withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(4))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.BROWN_MUSHROOM_CONFIG).withPlacement(
						Placement.COUNT_CHANCE_HEIGHTMAP.configure(new HeightWithChanceConfig(8, 0.25F))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.RED_MUSHROOM_CONFIG).withPlacement(
						Placement.COUNT_CHANCE_HEIGHTMAP_DOUBLE.configure(new HeightWithChanceConfig(8, 0.125F))));
		// TREES
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.NORMAL_TREE.withConfiguration(ChocolateTree.CHOCOLATE_TREE_CONFIG).withPlacement(
						Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(5, 0.1f, 1))));
	}
}