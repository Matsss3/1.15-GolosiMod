package com.matsss2.golosimod.world.biomes;

import java.util.Random;
import java.util.function.Function;

import com.matsss2.golosimod.init.BlockInit;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class SaltBiomeSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {

	public SaltBiomeSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> configFactory) {
		super(configFactory);
	}

	@Override
	public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise,
			BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
		Random rd = new Random();
		int i = rd.nextInt(5);
		if (i == 0) {
			SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock,
					defaultFluid, seaLevel, seed, new SurfaceBuilderConfig(BlockInit.SALT_BLOCK.get().getDefaultState(),
							BlockInit.SALT_BLOCK.get().getDefaultState(), Blocks.CLAY.getDefaultState()));
		} else {
			SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock,
					defaultFluid, seaLevel, seed,
					new SurfaceBuilderConfig(
							i == 1 ? Blocks.STONE.getDefaultState() : BlockInit.SALT_BLOCK.get().getDefaultState(),
							BlockInit.SALT_BLOCK.get().getDefaultState(),
							BlockInit.SALT_BLOCK.get().getDefaultState()));
		}
	}
}
