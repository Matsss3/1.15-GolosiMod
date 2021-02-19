package com.matsss2.golosimod.init;

import com.matsss2.golosimod.GolosiMod;
import com.matsss2.golosimod.container.CookieJarContainer;
import com.matsss2.golosimod.container.MortarContainer;
import com.matsss2.golosimod.container.OvenContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.common.extensions.IForgeContainerType;

public class ModContainerTypes {
	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(
			ForgeRegistries.CONTAINERS, GolosiMod.MOD_ID);

	public static final RegistryObject<ContainerType<OvenContainer>> OVEN = CONTAINER_TYPES.register("oven",
			() -> IForgeContainerType.create(OvenContainer::new));

	public static final RegistryObject<ContainerType<CookieJarContainer>> COOKIE_JAR = CONTAINER_TYPES
			.register("cookie_jar", () -> IForgeContainerType.create(CookieJarContainer::new));

	public static final RegistryObject<ContainerType<MortarContainer>> MORTAR = CONTAINER_TYPES.register("mortar",
			() -> IForgeContainerType.create(MortarContainer::new));
}
