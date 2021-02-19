package com.matsss2.golosimod.world.gen;

import com.matsss2.golosimod.init.BiomeInit;
import com.matsss2.golosimod.init.BlockInit;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class ModOreGen {
	public static void generatedOre() {
		for (Biome biome : ForgeRegistries.BIOMES) {
			if (biome == BiomeInit.GOLOSI_BIOME.get()) {
				ConfiguredPlacement<CountRangeConfig> customConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(2, 3, 15, 80));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
						Feature.ORE
								.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
										BlockInit.BORNITE_ORE.get().getDefaultState(), 5))
								.withPlacement(customConfig));
			}
			if (biome == BiomeInit.GOLOSI_BIOME.get()) {
				ConfiguredPlacement<CountRangeConfig> customConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(15, 0, 40, 60));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
						Feature.ORE
								.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
										BlockInit.LIMONITE_ORE.get().getDefaultState(), 8))
								.withPlacement(customConfig));
			}
		}
	}
}
