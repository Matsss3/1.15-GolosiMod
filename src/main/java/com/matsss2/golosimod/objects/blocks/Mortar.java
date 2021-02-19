package com.matsss2.golosimod.objects.blocks;

import java.util.List;
import java.util.stream.Stream;

import org.lwjgl.glfw.GLFW;

import com.matsss2.golosimod.init.ModTileEntityTypes;
import com.matsss2.golosimod.tileentity.MortarTileEntity;
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
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
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
import net.minecraftforge.fml.network.NetworkHooks;

public class Mortar extends Block {
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

	private static final VoxelShape SHAPE_N = Stream.of(Block.makeCuboidShape(4, 0, 4, 12, 1, 12),
			Block.makeCuboidShape(4, 1, 3, 12, 2, 13), Block.makeCuboidShape(3, 2, 2, 13, 3, 14),
			Block.makeCuboidShape(12, 1, 4, 13, 2, 12), Block.makeCuboidShape(13, 2, 3, 14, 4, 13),
			Block.makeCuboidShape(2, 2, 3, 3, 4, 13), Block.makeCuboidShape(3, 4, 1, 13, 6, 2),
			Block.makeCuboidShape(3, 4, 14, 13, 6, 15), Block.makeCuboidShape(14, 4, 3, 15, 6, 13),
			Block.makeCuboidShape(1, 4, 3, 2, 6, 13), Block.makeCuboidShape(3, 1, 4, 4, 2, 12),
			Block.makeCuboidShape(2, 4, 2, 3, 6, 3), Block.makeCuboidShape(2, 4, 13, 3, 6, 14),
			Block.makeCuboidShape(13, 4, 13, 14, 6, 14), Block.makeCuboidShape(13, 4, 2, 14, 6, 3),
			Block.makeCuboidShape(3, 3, 2, 13, 4, 3), Block.makeCuboidShape(3, 3, 13, 13, 4, 14)).reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	private static final VoxelShape SHAPE_S = Stream.of(Block.makeCuboidShape(4, 0, 4, 12, 1, 12),
			Block.makeCuboidShape(4, 1, 3, 12, 2, 13), Block.makeCuboidShape(3, 2, 2, 13, 3, 14),
			Block.makeCuboidShape(12, 1, 4, 13, 2, 12), Block.makeCuboidShape(13, 2, 3, 14, 4, 13),
			Block.makeCuboidShape(2, 2, 3, 3, 4, 13), Block.makeCuboidShape(3, 4, 14, 13, 6, 15),
			Block.makeCuboidShape(3, 4, 1, 13, 6, 2), Block.makeCuboidShape(14, 4, 3, 15, 6, 13),
			Block.makeCuboidShape(1, 4, 3, 2, 6, 13), Block.makeCuboidShape(3, 1, 4, 4, 2, 12),
			Block.makeCuboidShape(2, 4, 13, 3, 6, 14), Block.makeCuboidShape(2, 4, 2, 3, 6, 3),
			Block.makeCuboidShape(13, 4, 2, 14, 6, 3), Block.makeCuboidShape(13, 4, 13, 14, 6, 14),
			Block.makeCuboidShape(3, 3, 13, 13, 4, 14), Block.makeCuboidShape(3, 3, 2, 13, 4, 3)).reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	private static final VoxelShape SHAPE_E = Stream.of(Block.makeCuboidShape(4, 0, 4, 12, 1, 12),
			Block.makeCuboidShape(4, 1, 3, 12, 2, 13), Block.makeCuboidShape(3, 2, 2, 13, 3, 14),
			Block.makeCuboidShape(3, 1, 4, 4, 2, 12), Block.makeCuboidShape(2, 2, 3, 3, 4, 13),
			Block.makeCuboidShape(13, 2, 3, 14, 4, 13), Block.makeCuboidShape(3, 4, 1, 13, 6, 2),
			Block.makeCuboidShape(3, 4, 14, 13, 6, 15), Block.makeCuboidShape(1, 4, 3, 2, 6, 13),
			Block.makeCuboidShape(14, 4, 3, 15, 6, 13), Block.makeCuboidShape(12, 1, 4, 13, 2, 12),
			Block.makeCuboidShape(13, 4, 2, 14, 6, 3), Block.makeCuboidShape(13, 4, 13, 14, 6, 14),
			Block.makeCuboidShape(2, 4, 13, 3, 6, 14), Block.makeCuboidShape(2, 4, 2, 3, 6, 3),
			Block.makeCuboidShape(3, 3, 2, 13, 4, 3), Block.makeCuboidShape(3, 3, 13, 13, 4, 14)).reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	private static final VoxelShape SHAPE_W = Stream.of(Block.makeCuboidShape(4, 0, 4, 12, 1, 12),
			Block.makeCuboidShape(4, 1, 3, 12, 2, 13), Block.makeCuboidShape(3, 2, 2, 13, 3, 14),
			Block.makeCuboidShape(3, 1, 4, 4, 2, 12), Block.makeCuboidShape(2, 2, 3, 3, 4, 13),
			Block.makeCuboidShape(13, 2, 3, 14, 4, 13), Block.makeCuboidShape(3, 4, 1, 13, 6, 2),
			Block.makeCuboidShape(3, 4, 14, 13, 6, 15), Block.makeCuboidShape(1, 4, 3, 2, 6, 13),
			Block.makeCuboidShape(14, 4, 3, 15, 6, 13), Block.makeCuboidShape(12, 1, 4, 13, 2, 12),
			Block.makeCuboidShape(13, 4, 2, 14, 6, 3), Block.makeCuboidShape(13, 4, 13, 14, 6, 14),
			Block.makeCuboidShape(2, 4, 13, 3, 6, 14), Block.makeCuboidShape(2, 4, 2, 3, 6, 3),
			Block.makeCuboidShape(3, 3, 2, 13, 4, 3), Block.makeCuboidShape(3, 3, 13, 13, 4, 14)).reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	public Mortar(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch (state.get(FACING)) {
		case NORTH:
			return SHAPE_N;
		case EAST:
			return SHAPE_E;
		case SOUTH:
			return SHAPE_S;
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

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(FACING);
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return ModTileEntityTypes.MORTAR.get().create();
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		if (stack.hasDisplayName()) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if (tile instanceof MortarTileEntity) {
				((MortarTileEntity) tile).setCustomName(stack.getDisplayName());
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
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (worldIn != null && !worldIn.isRemote) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if (tile instanceof MortarTileEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tile, pos);
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.SUCCESS;
	}

	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		TileEntity tile = worldIn.getTileEntity(pos);
		if (tile instanceof MortarTileEntity && state.getBlock() != newState.getBlock()) {
			MortarTileEntity mortar = (MortarTileEntity) tile;
			((GolosiItemHandler) mortar.getInventory()).toNonNullList().forEach(item -> {
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
			tooltip.add(new StringTextComponent("This Mortar is used to get some things into Powder"));
		} else {
			tooltip.add(new StringTextComponent("Hold " + "\u00A74" + "Shift" + "\u00A77" + " for More Information"));
		}
	}
}
