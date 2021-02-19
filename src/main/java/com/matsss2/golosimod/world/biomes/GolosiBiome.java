package com.matsss2.golosimod.world.biomes;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.matsss2.golosimod.init.BlockInit;
import com.matsss2.golosimod.world.feature.LemonTree;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.feature.TwoFeatureChoiceConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.HeightWithChanceConfig;
import net.minecraft.world.gen.placement.NoiseDependant;
import net.minecraft.world.gen.placement.Placement;

public class GolosiBiome extends Biome {
	public GolosiBiome(Builder biomeBuilder) {
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
		// CAVES
		this.addCarver(GenerationStage.Carving.AIR,
				Biome.createCarver(WorldCarver.CAVE, new ProbabilityConfig(0.14285715F)));
		addCarver(GenerationStage.Carving.AIR, Biome.createCarver(WorldCarver.CANYON, new ProbabilityConfig(0.02F)));
		// LAKES
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS,
				Feature.LAKE
						.withConfiguration(new BlockStateFeatureConfig(BlockInit.COKE_BLOCK.get().getDefaultState()))
						.withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(4))));
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS,
				Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(Blocks.LAVA.getDefaultState()))
						.withPlacement(Placement.LAVA_LAKE.configure(new ChanceConfig(80))));
		// STONE REPLACEMENT
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.ORE
						.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
								Blocks.DIRT.getDefaultState(), 33))
						.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 256))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.ORE
						.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
								Blocks.GRAVEL.getDefaultState(), 33))
						.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(8, 0, 0, 256))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.ORE
						.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
								Blocks.GRANITE.getDefaultState(), 33))
						.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 80))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.ORE
						.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
								Blocks.DIORITE.getDefaultState(), 33))
						.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 80))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.ORE
						.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
								Blocks.ANDESITE.getDefaultState(), 33))
						.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 80))));
		// NORMAL ORES (EXTRA GOLD)
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.ORE
						.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
								Blocks.COAL_ORE.getDefaultState(), 17))
						.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 0, 128))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.ORE
						.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
								Blocks.IRON_ORE.getDefaultState(), 9))
						.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 0, 64))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.ORE
						.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
								Blocks.GOLD_ORE.getDefaultState(), 9))
						.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 32, 32, 80))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.ORE
						.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
								Blocks.REDSTONE_ORE.getDefaultState(), 8))
						.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(8, 0, 0, 16))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.ORE
						.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
								Blocks.DIAMOND_ORE.getDefaultState(), 8))
						.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(1, 0, 0, 16))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.ORE
						.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
								Blocks.LAPIS_ORE.getDefaultState(), 7))
						.withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(1, 16, 16))));
		// UNDERWATER DISKS
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
		// GIANT NORMAL TREES
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(
						ImmutableList.of(
								Feature.MEGA_SPRUCE_TREE.withConfiguration(DefaultBiomeFeatures.MEGA_SPRUCE_TREE_CONFIG)
										.withChance(0.025641026F),
								Feature.MEGA_SPRUCE_TREE.withConfiguration(DefaultBiomeFeatures.MEGA_PINE_TREE_CONFIG)
										.withChance(0.30769232F),
								Feature.NORMAL_TREE.withConfiguration(DefaultBiomeFeatures.PINE_TREE_CONFIG)
										.withChance(0.33333334F)),
						Feature.NORMAL_TREE.withConfiguration(DefaultBiomeFeatures.SPRUCE_TREE_CONFIG))).withPlacement(
								Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));
		// DENSE GRASS
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.GRASS_CONFIG)
						.withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(5))));
		// TALL GRASS
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.TALL_GRASS_CONFIG)
						.withPlacement(Placement.NOISE_HEIGHTMAP_32.configure(new NoiseDependant(-0.8D, 0, 7))));
		// GIANT MUSHROOMS
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_BOOLEAN_SELECTOR
						.withConfiguration(new TwoFeatureChoiceConfig(
								Feature.HUGE_RED_MUSHROOM.withConfiguration(DefaultBiomeFeatures.BIG_RED_MUSHROOM),
								Feature.HUGE_BROWN_MUSHROOM.withConfiguration(DefaultBiomeFeatures.BIG_BROWN_MUSHROOM)))
						.withPlacement(Placement.COUNT_HEIGHTMAP.configure(new FrequencyConfig(1))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.BROWN_MUSHROOM_CONFIG).withPlacement(
						Placement.COUNT_CHANCE_HEIGHTMAP.configure(new HeightWithChanceConfig(1, 0.25F))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.RED_MUSHROOM_CONFIG).withPlacement(
						Placement.COUNT_CHANCE_HEIGHTMAP_DOUBLE.configure(new HeightWithChanceConfig(1, 0.125F))));
		// NORMAL MUSHROOMS
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.BROWN_MUSHROOM_CONFIG)
						.withPlacement(Placement.CHANCE_HEIGHTMAP_DOUBLE.configure(new ChanceConfig(4))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.RED_MUSHROOM_CONFIG)
						.withPlacement(Placement.CHANCE_HEIGHTMAP_DOUBLE.configure(new ChanceConfig(8))));
		// LEMON TREE
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.NORMAL_TREE.withConfiguration(LemonTree.LEMON_TREE_CONFIG).withPlacement(
						Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(5, 0.1f, 1))));
	}
}
