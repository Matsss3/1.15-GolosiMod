package com.matsss2.golosimod.world.dimension;

import java.util.function.BiFunction;

import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

public class GolosiModDimension extends ModDimension{

	@Override
	public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
		return GolosiDimension::new;
	}
}
