package com.matsss2.golosimod.objects.items;

import java.util.List;
import java.util.function.Function;

import com.matsss2.golosimod.GolosiMod;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;

public class GolosiPortalItem extends Item {

	public GolosiPortalItem(Properties properties) {
		super(properties);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity entityIn, Hand handIn) {
		BlockPos pos = new BlockPos(entityIn);

		if (entityIn.dimension != DimensionType.byName(GolosiMod.GOLOSI_DIM_TYPE)) {
			teleportToDimension(entityIn, DimensionType.byName(GolosiMod.GOLOSI_DIM_TYPE), pos);
		} else {
			teleportToDimension(entityIn, DimensionType.byName(new ResourceLocation("minecraft", "overworld")), pos);
		}

		return super.onItemRightClick(worldIn, entityIn, handIn);
	}

	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent("\u00A7r"
				+ "This Item provides you a Portable Portal wich leads you to de Golosi Dimension! It'll teleport you to the exact coordinates between diemnsions (Press"
				+ "\u00A74" + "Right Click Button" + "\u00A7r" + "to Teleport you)"));
	}

	private void teleportToDimension(Entity entityIn, DimensionType dimension, BlockPos pos) {
		entityIn.changeDimension(dimension, new ITeleporter() {
			@Override
			public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw,
					Function<Boolean, Entity> repositionEntity) {
				entity = repositionEntity.apply(false);
				entity.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
				return entity;
			}
		});
	}
}
