package com.matsss2.golosimod.objects.blocks;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.lwjgl.glfw.GLFW;

import com.matsss2.golosimod.init.ModTileEntityTypes;
import com.matsss2.golosimod.tileentity.OvenTileEntity;
import com.matsss2.golosimod.util.GolosiItemHandler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class Oven extends Block {
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	public static final BooleanProperty LIT = BooleanProperty.create("lit");

	private static final VoxelShape SHAPE_N = Stream
			.of(Block.makeCuboidShape(3, 3, 3, 13, 13, 13), Block.makeCuboidShape(2, 2, 2, 3, 14, 14),
					Block.makeCuboidShape(13, 2, 2, 14, 14, 14), Block.makeCuboidShape(3, 13, 2, 13, 14, 14),
					Block.makeCuboidShape(3, 2, 2, 13, 3, 14), Block.makeCuboidShape(1, 3, 3, 2, 13, 13),
					Block.makeCuboidShape(14, 3, 3, 15, 13, 13), Block.makeCuboidShape(4, 14, 5, 12, 15, 11),
					Block.makeCuboidShape(5, 14, 4, 11, 15, 5), Block.makeCuboidShape(5, 14, 11, 11, 15, 12),
					Block.makeCuboidShape(4, 1, 5, 12, 2, 11), Block.makeCuboidShape(5, 1, 4, 11, 2, 5),
					Block.makeCuboidShape(5, 1, 11, 11, 2, 12), Block.makeCuboidShape(10.5, 3, 2, 11.5, 13, 3),
					Block.makeCuboidShape(4.5, 3, 2, 5.5, 13, 3), Block.makeCuboidShape(7.5, 3, 2, 8.5, 13, 3),
					Block.makeCuboidShape(10.5, 3, 13, 11.5, 13, 14), Block.makeCuboidShape(4.5, 3, 13, 5.5, 13, 14),
					Block.makeCuboidShape(7.5, 3, 13, 8.5, 13, 14),
					Block.makeCuboidShape(
							3, 0.5307337294603585, 0.6955181300451472, 4, 3.5307337294603585, 1.6955181300451472),
					Block.makeCuboidShape(3, 0, 0.5, 4, 1, 1.5),
					Block.makeCuboidShape(12, 0.5307337294603585, 0.6955181300451472, 13, 3.5307337294603585,
							1.6955181300451472),
					Block.makeCuboidShape(12, 0, 0.5, 13, 1, 1.5),
					Block.makeCuboidShape(12, 1.4207, 14, 13, 4.4207, 15),
					Block.makeCuboidShape(12, 0, 14.6, 13, 1, 15.6),
					Block.makeCuboidShape(3, 1.4306999999999999, 14, 4, 4.4307, 15),
					Block.makeCuboidShape(3, 0, 14.6, 4, 1, 15.6), Block.makeCuboidShape(6, 14, 2, 10, 16, 6))
			.reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	private static final VoxelShape SHAPE_E = Stream.of(Block.makeCuboidShape(3, 3, 3, 13, 13, 13),
			Block.makeCuboidShape(2, 2, 2, 14, 14, 3), Block.makeCuboidShape(2, 2, 13, 14, 14, 14),
			Block.makeCuboidShape(2, 13, 3, 14, 14, 13), Block.makeCuboidShape(2, 2, 3, 14, 3, 13),
			Block.makeCuboidShape(3, 3, 1, 13, 13, 2), Block.makeCuboidShape(3, 3, 14, 13, 13, 15),
			Block.makeCuboidShape(5, 14, 4, 11, 15, 12), Block.makeCuboidShape(11, 14, 5, 12, 15, 11),
			Block.makeCuboidShape(4, 14, 5, 5, 15, 11), Block.makeCuboidShape(5, 1, 4, 11, 2, 12),
			Block.makeCuboidShape(11, 1, 5, 12, 2, 11), Block.makeCuboidShape(4, 1, 5, 5, 2, 11),
			Block.makeCuboidShape(13, 3, 10.5, 14, 13, 11.5), Block.makeCuboidShape(13, 3, 4.5, 14, 13, 5.5),
			Block.makeCuboidShape(13, 3, 7.5, 14, 13, 8.5), Block.makeCuboidShape(2, 3, 10.5, 3, 13, 11.5),
			Block.makeCuboidShape(2, 3, 4.5, 3, 13, 5.5), Block.makeCuboidShape(2, 3, 7.5, 3, 13, 8.5),
			Block.makeCuboidShape(14.304481869954852, 0.5307337294603585, 3, 15.304481869954852, 3.5307337294603585, 4),
			Block.makeCuboidShape(14.5, 0, 3, 15.5, 1, 4),
			Block.makeCuboidShape(14.304481869954852, 0.5307337294603585, 12, 15.304481869954852, 3.5307337294603585,
					13),
			Block.makeCuboidShape(14.5, 0, 12, 15.5, 1, 13), Block.makeCuboidShape(1, 1.4207, 12, 2, 4.4207, 13),
			Block.makeCuboidShape(0.40000000000000036, 0, 12, 1.4000000000000004, 1, 13),
			Block.makeCuboidShape(1, 1.4306999999999999, 3, 2, 4.4307, 4),
			Block.makeCuboidShape(0.40000000000000036, 0, 3, 1.4000000000000004, 1, 4),
			Block.makeCuboidShape(10, 14, 6, 14, 16, 10)).reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	private static final VoxelShape SHAPE_S = Stream.of(Block.makeCuboidShape(3, 3, 3, 13, 13, 13),
			Block.makeCuboidShape(13, 2, 2, 14, 14, 14), Block.makeCuboidShape(2, 2, 2, 3, 14, 14),
			Block.makeCuboidShape(3, 13, 2, 13, 14, 14), Block.makeCuboidShape(3, 2, 2, 13, 3, 14),
			Block.makeCuboidShape(14, 3, 3, 15, 13, 13), Block.makeCuboidShape(1, 3, 3, 2, 13, 13),
			Block.makeCuboidShape(4, 14, 5, 12, 15, 11), Block.makeCuboidShape(5, 14, 11, 11, 15, 12),
			Block.makeCuboidShape(5, 14, 4, 11, 15, 5), Block.makeCuboidShape(4, 1, 5, 12, 2, 11),
			Block.makeCuboidShape(5, 1, 11, 11, 2, 12), Block.makeCuboidShape(5, 1, 4, 11, 2, 5),
			Block.makeCuboidShape(4.5, 3, 13, 5.5, 13, 14), Block.makeCuboidShape(10.5, 3, 13, 11.5, 13, 14),
			Block.makeCuboidShape(7.5, 3, 13, 8.5, 13, 14), Block.makeCuboidShape(4.5, 3, 2, 5.5, 13, 3),
			Block.makeCuboidShape(10.5, 3, 2, 11.5, 13, 3), Block.makeCuboidShape(7.5, 3, 2, 8.5, 13, 3),
			Block.makeCuboidShape(12, 0.5307337294603585, 14.304481869954852, 13, 3.5307337294603585,
					15.304481869954852),
			Block.makeCuboidShape(12, 0, 14.5, 13, 1, 15.5),
			Block.makeCuboidShape(3, 0.5307337294603585, 14.304481869954852, 4, 3.5307337294603585, 15.304481869954852),
			Block.makeCuboidShape(3, 0, 14.5, 4, 1, 15.5), Block.makeCuboidShape(3, 1.4207, 1, 4, 4.4207, 2),
			Block.makeCuboidShape(3, 0, 0.40000000000000036, 4, 1, 1.4000000000000004),
			Block.makeCuboidShape(12, 1.4306999999999999, 1, 13, 4.4307, 2),
			Block.makeCuboidShape(12, 0, 0.40000000000000036, 13, 1, 1.4000000000000004),
			Block.makeCuboidShape(6, 14, 10, 10, 16, 14)).reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	private static final VoxelShape SHAPE_W = Stream
			.of(Block.makeCuboidShape(3, 3, 3, 13, 13, 13), Block.makeCuboidShape(2, 2, 13, 14, 14, 14),
					Block.makeCuboidShape(2, 2, 2, 14, 14, 3), Block.makeCuboidShape(2, 13, 3, 14, 14, 13),
					Block.makeCuboidShape(2, 2, 3, 14, 3, 13), Block.makeCuboidShape(3, 3, 14, 13, 13, 15),
					Block.makeCuboidShape(3, 3, 1, 13, 13, 2), Block.makeCuboidShape(5, 14, 4, 11, 15, 12),
					Block.makeCuboidShape(4, 14, 5, 5, 15, 11), Block.makeCuboidShape(11, 14, 5, 12, 15, 11),
					Block.makeCuboidShape(5, 1, 4, 11, 2, 12), Block.makeCuboidShape(4, 1, 5, 5, 2, 11),
					Block.makeCuboidShape(11, 1, 5, 12, 2, 11), Block.makeCuboidShape(2, 3, 4.5, 3, 13, 5.5),
					Block.makeCuboidShape(2, 3, 10.5, 3, 13, 11.5), Block.makeCuboidShape(2, 3, 7.5, 3, 13, 8.5),
					Block.makeCuboidShape(13, 3, 4.5, 14, 13, 5.5), Block.makeCuboidShape(13, 3, 10.5, 14, 13, 11.5),
					Block.makeCuboidShape(13, 3, 7.5, 14, 13, 8.5),
					Block.makeCuboidShape(
							0.6955181300451478, 0.5307337294603585, 12, 1.6955181300451478, 3.5307337294603585, 13),
					Block.makeCuboidShape(0.5, 0, 12, 1.5, 1, 13),
					Block.makeCuboidShape(0.6955181300451478, 0.5307337294603585, 3, 1.6955181300451478,
							3.5307337294603585, 4),
					Block.makeCuboidShape(0.5, 0, 3, 1.5, 1, 4), Block.makeCuboidShape(14, 1.4207, 3, 15, 4.4207, 4),
					Block.makeCuboidShape(14.6, 0, 3, 15.6, 1, 4),
					Block.makeCuboidShape(14, 1.4306999999999999, 12, 15, 4.4307, 13),
					Block.makeCuboidShape(14.6, 0, 12, 15.6, 1, 13), Block.makeCuboidShape(2, 14, 6, 6, 16, 10))
			.reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	public Oven(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(LIT, false));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch (state.get(FACING)) {
		case NORTH:
			return SHAPE_N;
		case SOUTH:
			return SHAPE_S;
		case EAST:
			return SHAPE_E;
		case WEST:
			return SHAPE_W;
		default:
			return SHAPE_N;
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getLightValue(BlockState state) {
		return state.get(LIT) ? super.getLightValue(state) : 0;
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(FACING, LIT);
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return ModTileEntityTypes.OVEN.get().create();
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		if (stack.hasDisplayName()) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if (tile instanceof OvenTileEntity) {
				((OvenTileEntity) tile).setCustomName(stack.getDisplayName());
			}
		}
	}

	@Override
	public boolean hasComparatorInputOverride(BlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
		return Container.calcRedstone(worldIn.getTileEntity(pos));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (stateIn.get(LIT)) {
			double d0 = (double) pos.getX() + 0.5D;
			double d1 = (double) pos.getY();
			double d2 = (double) pos.getZ() + 0.5D;
			if (rand.nextDouble() < 0.1D) {
				worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F,
						false);
			}

			Direction direction = stateIn.get(FACING);
			Direction.Axis direction$axis = direction.getAxis();
			double d4 = rand.nextDouble() * 0.6D - 0.3D;
			double d5 = direction$axis == Direction.Axis.X ? (double) direction.getXOffset() * 0.52D : d4;
			double d6 = rand.nextDouble() * 6.0D / 16.0D;
			double d7 = direction$axis == Direction.Axis.Z ? (double) direction.getZOffset() * 0.52D : d4;
			worldIn.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
			worldIn.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (worldIn != null && !worldIn.isRemote) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if (tile instanceof OvenTileEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tile, pos);
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.SUCCESS;
	}

	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		TileEntity tile = worldIn.getTileEntity(pos);
		if (tile instanceof OvenTileEntity && state.getBlock() != newState.getBlock()) {
			OvenTileEntity furnace = (OvenTileEntity) tile;
			((GolosiItemHandler) furnace.getInventory()).toNonNullList().forEach(item -> {
				ItemEntity itemEntity = new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), item);
				worldIn.addEntity(itemEntity);
			});
		}

		if (state.hasTileEntity() && state.getBlock() != newState.getBlock()) {
			worldIn.removeTileEntity(pos);
		}
	}

	@Override
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		if (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
			tooltip.add(new StringTextComponent("This Oven is used to cook Normal Food and special Bakery things! (some ores too)"));
		} else {
			tooltip.add(new StringTextComponent("Hold " + "\u00A74" + "Shift" + "\u00A77" + " for More Information"));
		}
	}
}
