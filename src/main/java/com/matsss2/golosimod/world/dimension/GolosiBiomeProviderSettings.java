package com.matsss2.golosimod.world.dimension;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.IBiomeProviderSettings;
import net.minecraft.world.storage.WorldInfo;

public class GolosiBiomeProviderSettings implements IBiomeProviderSettings{
	
	private final long seed;
	private final WorldType worldType;
	private GolosiGenSettings generatorSettings = new GolosiGenSettings();
	
	public GolosiBiomeProviderSettings(WorldInfo info) {
		this.seed = info.getSeed();
		this.worldType = info.getGenerator();
	}
	
	public GolosiBiomeProviderSettings setGeneratorSettings(GolosiGenSettings settings) {
		this.generatorSettings = settings;
		return this;
	}
	
	public long getSeed() {
		return this.seed;
	}
	
	public WorldType getWorldType() {
		return this.worldType;
	}
	
	public GolosiGenSettings getGeneratorSettings() {
		return this.generatorSettings;
	}
}
