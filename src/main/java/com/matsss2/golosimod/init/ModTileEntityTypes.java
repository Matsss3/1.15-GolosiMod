package com.matsss2.golosimod.init;

import com.matsss2.golosimod.GolosiMod;
import com.matsss2.golosimod.tileentity.CookieJarTileEntity;
import com.matsss2.golosimod.tileentity.MortarTileEntity;
import com.matsss2.golosimod.tileentity.OvenTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(
			ForgeRegistries.TILE_ENTITIES, GolosiMod.MOD_ID);

	public static final RegistryObject<TileEntityType<OvenTileEntity>> OVEN = TILE_ENTITY_TYPES.register("oven",
			() -> TileEntityType.Builder.create(OvenTileEntity::new, BlockInit.OVEN.get()).build(null));
	public static final RegistryObject<TileEntityType<CookieJarTileEntity>> COOKIE_JAR = TILE_ENTITY_TYPES.register(
			"cookie_jar",
			() -> TileEntityType.Builder.create(CookieJarTileEntity::new, BlockInit.COOKIE_JAR.get()).build(null));
	public static final RegistryObject<TileEntityType<MortarTileEntity>> MORTAR = TILE_ENTITY_TYPES.register("mortar",
			() -> TileEntityType.Builder.create(MortarTileEntity::new, BlockInit.MORTAR.get()).build(null));
}
