package com.matsss2.golosimod.init;

import com.matsss2.golosimod.GolosiMod;

import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FluidInit {

	public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<>(ForgeRegistries.FLUIDS,
			GolosiMod.MOD_ID);

	// COKE
	public static final ResourceLocation COKE_STILL_RL = new ResourceLocation(GolosiMod.MOD_ID, "blocks/coke_still");
	public static final ResourceLocation COKE_FLOWING_RL = new ResourceLocation(GolosiMod.MOD_ID,
			"blocks/coke_flowing");
	public static final ResourceLocation COKE_OVERLAY_RL = new ResourceLocation(GolosiMod.MOD_ID,
			"blocks/coke_overlay");

	// FRYING OIL
	public static final ResourceLocation FRYING_OIL_STILL_RL = new ResourceLocation(GolosiMod.MOD_ID,
			"blocks/frying_oil_still");
	public static final ResourceLocation FRYING_OIL_FLOWING_RL = new ResourceLocation(GolosiMod.MOD_ID,
			"blocks/frying_oil_flowing");
	public static final ResourceLocation FRYING_OIL_OVERLAY_RL = new ResourceLocation(GolosiMod.MOD_ID,
			"blocks/frying_oil_overlay");

	// COKE
	public static final RegistryObject<FlowingFluid> COKE_FLUID = FLUIDS.register("coke_fluid",
			() -> new ForgeFlowingFluid.Source(FluidInit.COKE_PROPERTIES));
	public static final RegistryObject<FlowingFluid> COKE_FLOWING = FLUIDS.register("coke_flowing",
			() -> new ForgeFlowingFluid.Flowing(FluidInit.COKE_PROPERTIES));

	public static final ForgeFlowingFluid.Properties COKE_PROPERTIES = new ForgeFlowingFluid.Properties(
			() -> COKE_FLUID.get(), () -> COKE_FLOWING.get(),
			FluidAttributes.builder(COKE_STILL_RL, COKE_FLOWING_RL).density(0).luminosity(0).gaseous()
					.sound(SoundEvents.ITEM_HONEY_BOTTLE_DRINK).overlay(COKE_OVERLAY_RL).color(1376256).viscosity(5))
							.block(() -> BlockInit.COKE_BLOCK.get()).bucket(() -> ItemInit.COKE_BUCKET.get());

	// FRYING OIL
	public static final RegistryObject<FlowingFluid> FRYING_OIL_FLUID = FLUIDS.register("frying_oil_fluid",
			() -> new ForgeFlowingFluid.Source(FluidInit.FRYING_OIL_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FRYING_OIL_FLOWING = FLUIDS.register("frying_oil_flowing",
			() -> new ForgeFlowingFluid.Flowing(FluidInit.FRYING_OIL_PROPERTIES));

	public static final ForgeFlowingFluid.Properties FRYING_OIL_PROPERTIES = new ForgeFlowingFluid.Properties(
			() -> FRYING_OIL_FLUID.get(), () -> FRYING_OIL_FLOWING.get(),
			FluidAttributes.builder(FRYING_OIL_STILL_RL, FRYING_OIL_STILL_RL).density(15).luminosity(2).gaseous()
					.sound(SoundEvents.ENTITY_DROWNED_HURT_WATER).overlay(FRYING_OIL_OVERLAY_RL).color(11571253)
					.viscosity(5)).block(() -> BlockInit.FRYING_OIL_BLOCK.get())
							.bucket(() -> ItemInit.FRYING_OIL_BUCKET.get());
}