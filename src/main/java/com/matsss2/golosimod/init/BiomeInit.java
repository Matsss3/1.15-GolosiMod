package com.matsss2.golosimod.init;

import com.matsss2.golosimod.GolosiMod;
import com.matsss2.golosimod.world.biomes.CornPlainsBiome;
import com.matsss2.golosimod.world.biomes.CornPlainsSurfaceBuilder;
import com.matsss2.golosimod.world.biomes.GolosiBiome;
import com.matsss2.golosimod.world.biomes.GolosiBiomeSurfaceBuilder;
import com.matsss2.golosimod.world.biomes.SaltBiome;
import com.matsss2.golosimod.world.biomes.SaltBiomeSurfaceBuilder;
import com.matsss2.golosimod.world.biomes.WhiteChocolateBiome;

import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInit {
	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES,
			GolosiMod.MOD_ID);

	public static final RegistryObject<Biome> GOLOSI_BIOME = BIOMES
			.register("golosi_biome",
					() -> new GolosiBiome(
							new Biome.Builder().precipitation(RainType.SNOW).scale(0.3f).temperature(0.9f)
									.waterColor(1376256).waterFogColor(1379084)
									.surfaceBuilder(
											new ConfiguredSurfaceBuilder<SurfaceBuilderConfig>(
													register("golosi_surface",
															new GolosiBiomeSurfaceBuilder(
																	SurfaceBuilderConfig::deserialize)),
													new SurfaceBuilderConfig(Blocks.COARSE_DIRT.getDefaultState(),
															Blocks.DIRT.getDefaultState(),
															Blocks.DIRT.getDefaultState())))
									.category(Category.OCEAN).downfall(0.6f).depth(0.1145f).parent(null)));

	public static final RegistryObject<Biome> WHITE_CHOCOLATE_BIOME = BIOMES.register("white_chocolate_biome",
			() -> new WhiteChocolateBiome(new Biome.Builder().precipitation(RainType.RAIN).scale(0.55f)
					.temperature(0.4f).waterColor(11927537).waterFogColor(11927537)
					.surfaceBuilder(SurfaceBuilder.DEFAULT,
							new SurfaceBuilderConfig(BlockInit.WHITE_CHOCOLATE_CACAO_BLOCK.get().getDefaultState(),
									BlockInit.WHITE_CHOCOLATE_BLOCK.get().getDefaultState(),
									BlockInit.WHITE_CHOCOLATE_BLOCK.get().getDefaultState()))
					.category(Category.OCEAN).downfall(0.5f).depth(0.1105f).parent(null)));
	public static final RegistryObject<Biome> SALT_BIOME = BIOMES
			.register("salt_biome",
					() -> new SaltBiome(
							new Biome.Builder().precipitation(RainType.RAIN).scale(0.3f).temperature(1.0f)
									.waterColor(0x3F76E4).waterFogColor(11651071)
									.surfaceBuilder(
											new ConfiguredSurfaceBuilder<SurfaceBuilderConfig>(
													register("salt_biome_surface",
															new SaltBiomeSurfaceBuilder(
																	SurfaceBuilderConfig::deserialize)),
													new SurfaceBuilderConfig(Blocks.COARSE_DIRT.getDefaultState(),
															Blocks.DIRT.getDefaultState(),
															Blocks.DIRT.getDefaultState())))
									.category(Category.OCEAN).downfall(0.3f).depth(-1.0f).parent(null)));
	public static final RegistryObject<Biome> CORN_PLAINS = BIOMES
			.register("corn_plains",
					() -> new CornPlainsBiome(
							new Biome.Builder().precipitation(RainType.RAIN).scale(0.6f).temperature(1.0f)
									.waterColor(0x45ADF2).waterFogColor(0x041633)
									.surfaceBuilder(
											new ConfiguredSurfaceBuilder<SurfaceBuilderConfig>(
													register("corn_plains_surface",
															new CornPlainsSurfaceBuilder(
																	SurfaceBuilderConfig::deserialize)),
													new SurfaceBuilderConfig(Blocks.COARSE_DIRT.getDefaultState(),
															Blocks.DIRT.getDefaultState(),
															Blocks.DIRT.getDefaultState())))
									.category(Category.OCEAN).downfall(0.55f).depth(0.09050f).parent(null)));

	public static void registerBiomes() {
		registerBiome(GOLOSI_BIOME.get(), Type.PLAINS, Type.OVERWORLD);
	}

	private static void registerBiome(Biome biome, Type... types) {
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addSpawnBiome(biome);
		BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biome, 70));
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	private static <C extends ISurfaceBuilderConfig, F extends SurfaceBuilder<C>> F register(String key, F builderIn) {
		return (F) (Registry.<SurfaceBuilder<?>>register(Registry.SURFACE_BUILDER, key, builderIn));
	}
}