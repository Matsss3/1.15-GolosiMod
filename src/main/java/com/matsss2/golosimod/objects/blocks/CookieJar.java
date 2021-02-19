package com.matsss2.golosimod.objects.blocks;

import java.util.List;
import java.util.stream.Stream;

import org.lwjgl.glfw.GLFW;

import com.matsss2.golosimod.init.ModTileEntityTypes;
import com.matsss2.golosimod.tileentity.CookieJarTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
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

public class CookieJar extends Block {
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

	private static final VoxelShape SHAPE_N = Stream.of(Block.makeCuboidShape(4, 1, 5, 5, 11, 11),
			Block.makeCuboidShape(5, 1, 4, 11, 11, 5), Block.makeCuboidShape(5, 1, 11, 11, 11, 12),
			Block.makeCuboidShape(11, 1, 5, 12, 11, 11), Block.makeCuboidShape(5, 0, 5, 11, 1, 11),
			Block.makeCuboidShape(5, 11, 5, 11, 12, 11), Block.makeCuboidShape(4, 12, 5, 12, 13, 11),
			Block.makeCuboidShape(5, 13, 5, 11, 14, 11), Block.makeCuboidShape(5, 12, 4, 11, 13, 5),
			Block.makeCuboidShape(5, 12, 11, 11, 13, 12), Block.makeCuboidShape(6, 1, 6, 10, 2, 10),
			Block.makeCuboidShape(5, 2, 5, 9, 3, 9), Block.makeCuboidShape(5, 3, 6, 9, 4, 10),
			Block.makeCuboidShape(6, 4, 6, 10, 5, 10), Block.makeCuboidShape(7, 5, 6, 11, 6, 10)).reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	private final VoxelShape SHAPE_W = Stream.of(Block.makeCuboidShape(5, 1, 4, 11, 11, 5),
			Block.makeCuboidShape(11, 1, 5, 12, 11, 11), Block.makeCuboidShape(4, 1, 5, 5, 11, 11),
			Block.makeCuboidShape(5, 1, 11, 11, 11, 12), Block.makeCuboidShape(5, 0, 5, 11, 1, 11),
			Block.makeCuboidShape(5, 11, 5, 11, 12, 11), Block.makeCuboidShape(5, 12, 4, 11, 13, 12),
			Block.makeCuboidShape(5, 13, 5, 11, 14, 11), Block.makeCuboidShape(11, 12, 5, 12, 13, 11),
			Block.makeCuboidShape(4, 12, 5, 5, 13, 11), Block.makeCuboidShape(6, 1, 6, 10, 2, 10),
			Block.makeCuboidShape(7, 2, 5, 11, 3, 9), Block.makeCuboidShape(6, 3, 5, 10, 4, 9),
			Block.makeCuboidShape(6, 4, 6, 10, 5, 10), Block.makeCuboidShape(6, 5, 7, 10, 6, 11)).reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	private final VoxelShape SHAPE_S = Stream.of(Block.makeCuboidShape(11, 1, 5, 12, 11, 11),
			Block.makeCuboidShape(5, 1, 4, 11, 11, 5), Block.makeCuboidShape(5, 1, 11, 11, 11, 12),
			Block.makeCuboidShape(4, 1, 5, 5, 11, 11), Block.makeCuboidShape(5, 0, 5, 11, 1, 11),
			Block.makeCuboidShape(5, 11, 5, 11, 12, 11), Block.makeCuboidShape(4, 12, 5, 12, 13, 11),
			Block.makeCuboidShape(5, 13, 5, 11, 14, 11), Block.makeCuboidShape(5, 12, 4, 11, 13, 5),
			Block.makeCuboidShape(5, 12, 11, 11, 13, 12), Block.makeCuboidShape(6, 1, 6, 10, 2, 10),
			Block.makeCuboidShape(7, 2, 5, 11, 3, 9), Block.makeCuboidShape(7, 3, 6, 11, 4, 10),
			Block.makeCuboidShape(6, 4, 6, 10, 5, 10), Block.makeCuboidShape(5, 5, 6, 9, 6, 10)).reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	private final VoxelShape SHAPE_E = Stream.of(Block.makeCuboidShape(5, 1, 11, 11, 11, 12),
			Block.makeCuboidShape(4, 1, 5, 5, 11, 11), Block.makeCuboidShape(11, 1, 5, 12, 11, 11),
			Block.makeCuboidShape(5, 1, 4, 11, 11, 5), Block.makeCuboidShape(5, 0, 5, 11, 1, 11),
			Block.makeCuboidShape(5, 11, 5, 11, 12, 11), Block.makeCuboidShape(5, 12, 4, 11, 13, 12),
			Block.makeCuboidShape(5, 13, 5, 11, 14, 11), Block.makeCuboidShape(4, 12, 5, 5, 13, 11),
			Block.makeCuboidShape(11, 12, 5, 12, 13, 11), Block.makeCuboidShape(6, 1, 6, 10, 2, 10),
			Block.makeCuboidShape(5, 2, 7, 9, 3, 11), Block.makeCuboidShape(6, 3, 7, 10, 4, 11),
			Block.makeCuboidShape(6, 4, 6, 10, 5, 10), Block.makeCuboidShape(6, 5, 5, 10, 6, 9)).reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	public CookieJar(Properties properties) {
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
		builder.add(FACING);
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return ModTileEntityTypes.COOKIE_JAR.get().create();
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult result) {
		if (!worldIn.isRemote) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if (tile instanceof CookieJarTileEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (CookieJarTileEntity) tile, pos);
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.SUCCESS;
	}
	
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof CookieJarTileEntity) {
				InventoryHelper.dropItems(worldIn, pos, ((CookieJarTileEntity) te).getItems());
			}
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		if (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
			tooltip.add(new StringTextComponent("This Cookie Jar is used to store a short amoun of things"));
		} else {
			tooltip.add(new StringTextComponent("Hold " + "\u00A74" + "Shift" + "\u00A77" + " for More Information"));
		}
	}
}
