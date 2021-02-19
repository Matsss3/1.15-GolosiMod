package com.matsss2.golosimod.world.dimension;

import com.matsss2.golosimod.init.BlockInit;

import net.minecraft.world.gen.GenerationSettings;

public class GolosiGenSettings extends GenerationSettings {

	public int getBiomeSize() {
		return 4;
	}

	public int getRiverSize() {
		return 4;
	}

	public int getBiomeId() {
		return -1;
	}

	@Override
	public int getBedrockFloorHeight() {
		return 0;
	}

	public GolosiGenSettings() {
		this.defaultBlock = BlockInit.SUGARED_STONE.get().getDefaultState();
	}
}
