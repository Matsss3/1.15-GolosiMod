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

public class ChocolateTree extends Tree {
	public static final TreeFeatureConfig CHOCOLATE_TREE_CONFIG = (new TreeFeatureConfig.Builder(
			new SimpleBlockStateProvider(BlockInit.CHOCOLATE_TREE_LOG.get().getDefaultState()),
			new SimpleBlockStateProvider(BlockInit.CHOCOLATE_TREE_LEAVES.get().getDefaultState()),
			new BlobFoliagePlacer(2, 0))).baseHeight(5).heightRandA(1).foliageHeight(3).ignoreVines()
					.setSapling((IPlantable) BlockInit.CHOCOLATE_SAPLING.get()).build();

	@Override
	protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
		return Feature.NORMAL_TREE.withConfiguration(CHOCOLATE_TREE_CONFIG);
	}
}
