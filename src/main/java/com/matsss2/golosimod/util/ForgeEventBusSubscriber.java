package com.matsss2.golosimod.util;

import com.matsss2.golosimod.GolosiMod;
import com.matsss2.golosimod.init.DimensionInit;

import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = GolosiMod.MOD_ID, bus = Bus.FORGE)
public class ForgeEventBusSubscriber {

	@SubscribeEvent
	public static void registerDimensions(final RegisterDimensionsEvent event) {
		if (DimensionType.byName(GolosiMod.GOLOSI_DIM_TYPE) == null) {
			DimensionManager.registerDimension(GolosiMod.GOLOSI_DIM_TYPE, DimensionInit.GOLOSI_DIMENSION.get(), null,
					true);
		}
		GolosiMod.LOGGER.info("Dimension Registered!");
	}
}
