package com.matsss2.golosimod.objects.blocks;

import java.util.Random;

import com.matsss2.golosimod.init.BlockInit;
import com.matsss2.golosimod.init.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ModBushBlock extends BushBlock implements IGrowable {

	public static final IntegerProperty AGE = BlockStateProperties.AGE_0_3;
	private static final VoxelShape AGE_BABY = Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
	private static final VoxelShape AGE_NOT_BABY = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

	public ModBushBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(AGE, Integer.valueOf(0)));
	}

	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(ItemInit.CORN_SEED.get());
	}

	@SuppressWarnings("deprecation")
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if (state.get(AGE) == 0) {
			return AGE_BABY;
		} else {
			return state.get(AGE) < 3 ? AGE_NOT_BABY : super.getShape(state, worldIn, pos, context);
		}
	}

	@SuppressWarnings("deprecation")
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		super.tick(state, worldIn, pos, rand);
		int i = state.get(AGE);
		if (i < 3 && worldIn.getLightSubtracted(pos.up(), 0) >= 1
				&& net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(5) == 0)) {
			worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i + 1)), 2);
			net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}

	}

	@SuppressWarnings("deprecation")
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		int i = state.get(AGE);
		boolean flag = i == 3;
		if (!flag && player.getHeldItem(handIn).getItem() == Items.BONE_MEAL) {
			return ActionResultType.PASS;
		} else if (i > 1) {
			int j = 1 + worldIn.rand.nextInt(2);
			spawnAsEntity(worldIn, pos, new ItemStack(ItemInit.CORN_SEED.get(), j + (flag ? 1 : 0)));
			worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH,
					SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
			worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(1)), 2);
			return ActionResultType.SUCCESS;
		} else {
			return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
		}
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	@Override
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return state.get(AGE) < 3;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		int i = Math.min(3, state.get(AGE) + 1);
		worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i)), 2);
	}
	
	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		Block block = state.getBlock();
		return block == BlockInit.FERTILE_GRASS.get();
	}
}
