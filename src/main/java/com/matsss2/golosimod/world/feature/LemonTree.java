package com.matsss2.golosimod.world.feature;

import java.util.Random;

import com.matsss2.golosimod.init.BlockInit;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraftforge.common.IPlantable;

public class LemonTree extends Tree {
	public static final TreeFeatureConfig LEMON_TREE_CONFIG = (new TreeFeatureConfig.Builder(
			new SimpleBlockStateProvider(BlockInit.LEMON_TREE_LOG.get().getDefaultState()),
			new SimpleBlockStateProvider(BlockInit.LEMON_TREE_LEAVES.get().getDefaultState()),
			new BlobFoliagePlacer(2, 0))).baseHeight(5).heightRandA(1).foliageHeight(3).ignoreVines()
					.setSapling((IPlantable) BlockInit.LEMON_SAPLING.get()).build();

	@Override
	protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean b) {
		return Feature.NORMAL_TREE.withConfiguration(LEMON_TREE_CONFIG);
	}
}
