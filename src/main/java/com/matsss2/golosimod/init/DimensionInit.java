package com.matsss2.golosimod.init;

import com.matsss2.golosimod.GolosiMod;
import com.matsss2.golosimod.world.dimension.GolosiModDimension;

import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DimensionInit {
	
	public static final DeferredRegister<ModDimension> MOD_DIMENSIONS = new DeferredRegister<>(
			ForgeRegistries.MOD_DIMENSIONS, GolosiMod.MOD_ID);

	public static final RegistryObject<ModDimension> GOLOSI_DIMENSION = MOD_DIMENSIONS.register("golosi_dimension",
			() -> new GolosiModDimension());
}
