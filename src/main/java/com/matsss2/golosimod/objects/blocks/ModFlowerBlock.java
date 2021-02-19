package com.matsss2.golosimod.objects.blocks;

import com.matsss2.golosimod.init.BlockInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.potion.Effect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.IPlantable;

public class ModFlowerBlock extends BushBlock implements IPlantable {
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);
	private final Effect stewEffect;
	private final int stewEffectDuration;

	public ModFlowerBlock(Effect effectIn, int effectDuration, Block.Properties properties) {
		super(properties);
		this.stewEffect = effectIn;
		if (effectIn.isInstant()) {
			this.stewEffectDuration = effectDuration;
		} else {
			this.stewEffectDuration = effectDuration * 20;
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Vec3d vec3d = state.getOffset(worldIn, pos);
		return SHAPE.withOffset(vec3d.x, vec3d.y, vec3d.z);
	}

	@Override
	public OffsetType getOffsetType() {
		return Block.OffsetType.XZ;
	}

	public Effect getStewEffect() {
		return this.stewEffect;
	}

	public int getStewEffectDuration() {
		return this.stewEffectDuration;
	}

	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		Block block = state.getBlock();
		return block == BlockInit.SALT_BLOCK.get() || block == BlockInit.CHOCOLATE_CHEESE_BLOCK.get()
				|| block == BlockInit.WHITE_CHOCOLATE_CACAO_BLOCK.get() || block == Blocks.GRASS_BLOCK
				|| block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL
				|| block == Blocks.FARMLAND || block == Blocks.STONE || block == BlockInit.FERTILE_GRASS.get();
	}
}
