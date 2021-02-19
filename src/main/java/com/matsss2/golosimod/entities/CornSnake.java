package com.matsss2.golosimod.entities;

import java.util.Random;

import com.matsss2.golosimod.init.BlockInit;
import com.matsss2.golosimod.init.ModEntityTypes;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.animation.builder.AnimationBuilder;
import software.bernie.geckolib.animation.controller.AnimationController;
import software.bernie.geckolib.animation.controller.EntityAnimationController;
import software.bernie.geckolib.entity.IAnimatedEntity;
import software.bernie.geckolib.event.AnimationTestEvent;
import software.bernie.geckolib.manager.EntityAnimationManager;

public class CornSnake extends AnimalEntity implements IAnimatedEntity {

	private EntityAnimationManager manager = new EntityAnimationManager();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private AnimationController controller = new EntityAnimationController(this, "move_controller", 20,
			this::animationPredicate);
	private EatGrassGoal eatGrassGoal;
	private int Timer;

	public CornSnake(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
		registerAnimationController();
	}

	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		CornSnake entity = new CornSnake(ModEntityTypes.CORN_SNAKE.get(), this.world);
		entity.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(entity)),
				SpawnReason.BREEDING, (ILivingEntityData) null, (CompoundNBT) null);
		return entity;
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.eatGrassGoal = new EatGrassGoal(this);
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.1d, Ingredient.fromItems(Items.WHEAT_SEEDS), false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1d));
		this.goalSelector.addGoal(5, this.eatGrassGoal);
		this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0d));
		this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
	}

	@Override
	protected void updateAITasks() {
		this.Timer = this.eatGrassGoal.getEatingGrassTimer();
		super.updateAITasks();
	}

	@Override
	public void livingTick() {
		if (this.world.isRemote) {
			this.Timer = Math.max(0, this.Timer - 1);
		}
		super.livingTick();
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0d);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double) 0.15d);
	}

	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 10) {
			this.Timer = 40;
		} else {
			super.handleStatusUpdate(id);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public float getHeadRotationPointY(float p_70894_1_) {
		if (this.Timer <= 0) {
			return 0.0F;
		} else if (this.Timer >= 4 && this.Timer <= 36) {
			return 1.0F;
		} else {
			return this.Timer < 4 ? ((float) this.Timer - p_70894_1_) / 4.0F
					: -((float) (this.Timer - 40) - p_70894_1_) / 4.0F;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public float getHeadRotationAngleX(float p_70890_1_) {
		if (this.Timer > 4 && this.Timer <= 36) {
			float f = ((float) (this.Timer - 4) - p_70890_1_) / 32.0F;
			return ((float) Math.PI / 5F) + 0.21991149F * MathHelper.sin(f * 28.7F);
		} else {
			return this.Timer > 0 ? ((float) Math.PI / 5F) : this.rotationPitch * ((float) Math.PI / 180F);
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return this.isChild() ? SoundEvents.ENTITY_CREEPER_HURT : SoundEvents.ENTITY_CREEPER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return this.isChild() ? SoundEvents.ENTITY_CREEPER_DEATH : SoundEvents.ENTITY_CREEPER_DEATH;
	}
	
	@Override
	public boolean canBreed() {
		return true;
	}

	protected float determineNextStepDistance() {
		return this.distanceWalkedOnStepModified + 0.15F;
	}
	
	public boolean isBreedingItem(ItemStack stack) {
		return stack.getItem() == Items.WHEAT_SEEDS.asItem();
	}
	
	@Override
	public EntityAnimationManager getAnimationManager() {
		return manager;
	}

	private <E extends CornSnake> boolean animationPredicate(AnimationTestEvent<E> event) {
		if (event.isWalking()) {
			controller.setAnimation(new AnimationBuilder().addAnimation("animation.golosimod.walk", true)
					.addAnimation("animation.golosimod.idle"));
			return true;
		} else {
			controller.setAnimation(new AnimationBuilder().addRepeatingAnimation("animation.golosimod.idle", 5));
			return true;
		}
	}

	private void registerAnimationController() {
		manager.addAnimationController(controller);
	}
	
	public static boolean canAnimalSpawn(EntityType<? extends AnimalEntity> animal, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
	      return worldIn.getBlockState(pos.down()).getBlock() == BlockInit.FERTILE_GRASS.get() && worldIn.getLightSubtracted(pos, 0) >= 0;
	}
}
