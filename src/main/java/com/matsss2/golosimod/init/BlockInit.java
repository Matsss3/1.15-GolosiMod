package com.matsss2.golosimod.init;

import com.matsss2.golosimod.GolosiMod;
import com.matsss2.golosimod.objects.blocks.ChocolateCheeseBlock;
import com.matsss2.golosimod.objects.blocks.CookieJar;
import com.matsss2.golosimod.objects.blocks.CornCrop;
import com.matsss2.golosimod.objects.blocks.FertileGrass;
import com.matsss2.golosimod.objects.blocks.ModBushBlock;
import com.matsss2.golosimod.objects.blocks.ModDoorBlock;
import com.matsss2.golosimod.objects.blocks.ModFlowerBlock;
import com.matsss2.golosimod.objects.blocks.ModPressurePlateBlock;
import com.matsss2.golosimod.objects.blocks.ModSaplingBlock;
import com.matsss2.golosimod.objects.blocks.ModWoodenButton;
import com.matsss2.golosimod.objects.blocks.Mortar;
import com.matsss2.golosimod.objects.blocks.Oven;
import com.matsss2.golosimod.objects.blocks.WhiteChocolateCacaoBlock;
import com.matsss2.golosimod.world.feature.ChocolateTree;
import com.matsss2.golosimod.world.feature.LemonTree;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.PressurePlateBlock.Sensitivity;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS,
			GolosiMod.MOD_ID);

	// ORES
	public static final RegistryObject<Block> BORNITE_ORE = BLOCKS.register("bornite_ore",
			() -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(16.6F, 100.0F)
					.sound(SoundType.STONE).harvestLevel(3).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Block> LIMONITE_ORE = BLOCKS.register("limonite_ore",
			() -> new Block(Block.Properties.from(Blocks.COAL_ORE)));
	// CUSTOM
	public static final RegistryObject<Block> OVEN = BLOCKS.register("oven",
			() -> new Oven(Block.Properties.create(Material.IRON).hardnessAndResistance(4.0F, 4.2F)
					.sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Block> COOKIE_JAR = BLOCKS.register("cookie_jar",
			() -> new CookieJar(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.4f, 0.4f)
					.sound(SoundType.GLASS).harvestLevel(0)));
	public static final RegistryObject<Block> MORTAR = BLOCKS.register("mortar",
			() -> new Mortar(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0f)
					.sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE)));
	// LEMON
	public static final RegistryObject<Block> LEMON_TREE_PLANKS = BLOCKS.register("lemon_tree_planks",
			() -> new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F)
					.sound(SoundType.WOOD).harvestLevel(0).harvestTool(ToolType.AXE)));
	public static final RegistryObject<Block> LEMON_TREE_LEAVES = BLOCKS.register("lemon_tree_leaves",
			() -> new LeavesBlock(Block.Properties.from(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> LEMON_TREE_LOG = BLOCKS.register("lemon_tree_log",
			() -> new LogBlock(MaterialColor.WOOD, Block.Properties.from(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> LEMON_SAPLING = BLOCKS.register("lemon_sapling",
			() -> new ModSaplingBlock(() -> new LemonTree(), Block.Properties.from(Blocks.OAK_SAPLING)));
	public static final RegistryObject<Block> LEMON_STAIRS = BLOCKS.register("lemon_stairs",
			() -> new StairsBlock(() -> BlockInit.LEMON_TREE_PLANKS.get().getDefaultState(),
					Block.Properties.from(Blocks.OAK_STAIRS)));
	public static final RegistryObject<Block> LEMON_FENCE = BLOCKS.register("lemon_fence",
			() -> new FenceBlock(Block.Properties.from(Blocks.OAK_FENCE)));
	public static final RegistryObject<Block> LEMON_BUTTON = BLOCKS.register("lemon_button",
			() -> new ModWoodenButton(Block.Properties.from(Blocks.OAK_BUTTON)));
	public static final RegistryObject<Block> LEMON_PRESSURE_PLATE = BLOCKS.register("lemon_pressure_plate",
			() -> new ModPressurePlateBlock(Sensitivity.EVERYTHING, Block.Properties.from(Blocks.OAK_PRESSURE_PLATE)));
	public static final RegistryObject<Block> LEMON_SLAB = BLOCKS.register("lemon_slab",
			() -> new SlabBlock(Block.Properties.from(BlockInit.LEMON_TREE_PLANKS.get())));
	public static final RegistryObject<Block> LEMON_DOOR = BLOCKS.register("lemon_door",
			() -> new ModDoorBlock(Block.Properties.from(Blocks.OAK_DOOR)));

	// CHOCOLATE
	public static final RegistryObject<Block> CHOCOLATE_CHEESE_BLOCK = BLOCKS.register("chocolate_cheese_block",
			() -> new ChocolateCheeseBlock(Block.Properties.from(Blocks.GRASS_BLOCK)));
	public static final RegistryObject<Block> CHOCOLATE_BLOCK = BLOCKS.register("chocolate_block",
			() -> new Block(Block.Properties.from(Blocks.DIRT)));
	public static final RegistryObject<Block> WHITE_CHOCOLATE_CACAO_BLOCK = BLOCKS.register(
			"white_chocolate_cacao_block",
			() -> new WhiteChocolateCacaoBlock(Block.Properties.from(Blocks.GRASS_BLOCK)));
	public static final RegistryObject<Block> WHITE_CHOCOLATE_BLOCK = BLOCKS.register("white_chocolate_block",
			() -> new Block(Block.Properties.from(Blocks.DIRT)));
	public static final RegistryObject<Block> CHOCOLATE_TREE_PLANKS = BLOCKS.register("chocolate_tree_planks",
			() -> new Block(Block.Properties.from(Blocks.ACACIA_PLANKS)));
	public static final RegistryObject<Block> CHOCOLATE_TREE_LEAVES = BLOCKS.register("chocolate_tree_leaves",
			() -> new LeavesBlock(Block.Properties.from(Blocks.ACACIA_LEAVES)));
	public static final RegistryObject<Block> CHOCOLATE_TREE_LOG = BLOCKS.register("chocolate_tree_log",
			() -> new LogBlock(MaterialColor.WOOD, Block.Properties.from(Blocks.ACACIA_LOG)));
	public static final RegistryObject<Block> CHOCOLATE_SAPLING = BLOCKS.register("chocolate_sapling",
			() -> new ModSaplingBlock(() -> new ChocolateTree(), Block.Properties.from(Blocks.ACACIA_SAPLING)));
	public static final RegistryObject<Block> CHOCOLATE_STAIRS = BLOCKS.register("chocolate_stairs",
			() -> new StairsBlock(() -> BlockInit.CHOCOLATE_TREE_PLANKS.get().getDefaultState(),
					Block.Properties.from(Blocks.OAK_STAIRS)));
	public static final RegistryObject<Block> CHOCOLATE_FENCE = BLOCKS.register("chocolate_fence",
			() -> new FenceBlock(Block.Properties.from(Blocks.OAK_FENCE)));
	public static final RegistryObject<Block> CHOCOLATE_BUTTON = BLOCKS.register("chocolate_button",
			() -> new ModWoodenButton(Block.Properties.from(Blocks.OAK_BUTTON)));
	public static final RegistryObject<Block> CHOCOLATE_PRESSURE_PLATE = BLOCKS.register("chocolate_pressure_plate",
			() -> new ModPressurePlateBlock(Sensitivity.EVERYTHING, Block.Properties.from(Blocks.OAK_PRESSURE_PLATE)));
	public static final RegistryObject<Block> CHOCOLATE_SLAB = BLOCKS.register("chocolate_slab",
			() -> new SlabBlock(Block.Properties.from(BlockInit.CHOCOLATE_TREE_PLANKS.get())));
	public static final RegistryObject<Block> CHOCOLATE_DOOR = BLOCKS.register("chocolate_door",
			() -> new ModDoorBlock(Block.Properties.from(Blocks.OAK_DOOR)));

	// CORN
	public static final RegistryObject<Block> FERTILE_GRASS = BLOCKS.register("fertile_grass",
			() -> new FertileGrass(Block.Properties.from(Blocks.GRASS_BLOCK)));

	// FLUIDS
	public static final RegistryObject<FlowingFluidBlock> COKE_BLOCK = BLOCKS.register("coke",
			() -> new FlowingFluidBlock(() -> FluidInit.COKE_FLUID.get(), Block.Properties.create(Material.WATER)
					.doesNotBlockMovement().hardnessAndResistance(100.0f, 100.0f).noDrops()));
	public static final RegistryObject<FlowingFluidBlock> FRYING_OIL_BLOCK = BLOCKS.register("frying_oil",
			() -> new FlowingFluidBlock(() -> FluidInit.FRYING_OIL_FLUID.get(), Block.Properties.create(Material.LAVA)
					.doesNotBlockMovement().hardnessAndResistance(100.0f, 100.0f).noDrops()));

	// OTHERS
	public static final RegistryObject<Block> LIMONITE_BLOCK = BLOCKS.register("limonite_block",
			() -> new Block(Block.Properties.from(Blocks.DIAMOND_BLOCK)));
	public static final RegistryObject<Block> SUGARED_STONE = BLOCKS.register("sugared_stone",
			() -> new Block(Block.Properties.from(Blocks.STONE)));
	public static final RegistryObject<Block> SALT_BLOCK = BLOCKS.register("salt_block",
			() -> new Block(Block.Properties.create(Material.SAND).hardnessAndResistance(0.4f, 0.1f)
					.sound(SoundType.CORAL).harvestLevel(1).harvestTool(ToolType.PICKAXE)));
	// CROPS
	public static final RegistryObject<Block> CORN_CROP = BLOCKS.register("corn_crop",
			() -> new CornCrop(Block.Properties.from(Blocks.WHEAT)));

	// PLANTS
	public static final RegistryObject<Block> FRIED_POTATOS_PLANT = BLOCKS.register("fried_potatos_plant",
			() -> new ModFlowerBlock(Effects.NIGHT_VISION, 5, Block.Properties.create(Material.PLANTS)
					.doesNotBlockMovement().hardnessAndResistance(0.0f, 0.0f).sound(SoundType.PLANT)));
	public static final RegistryObject<Block> CORN_BUSH = BLOCKS.register("corn_bush",
			() -> new ModBushBlock(Block.Properties.from(Blocks.SWEET_BERRY_BUSH)));
}
