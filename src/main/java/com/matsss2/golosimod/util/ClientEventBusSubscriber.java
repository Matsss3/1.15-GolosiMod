package com.matsss2.golosimod.util;

import com.matsss2.golosimod.GolosiMod;
import com.matsss2.golosimod.client.entity.render.ChocolateBeetleEntityRender;
import com.matsss2.golosimod.client.entity.render.CornSnakeEntityRender;
import com.matsss2.golosimod.client.gui.CookieJarScreen;
import com.matsss2.golosimod.client.gui.MortarScreen;
import com.matsss2.golosimod.client.gui.OvenScreen;
import com.matsss2.golosimod.init.BlockInit;
import com.matsss2.golosimod.init.ModContainerTypes;
import com.matsss2.golosimod.init.ModEntityTypes;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid=GolosiMod.MOD_ID, bus=Bus.MOD, value=Dist.CLIENT)
public class ClientEventBusSubscriber 
{
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) 
	{
		RenderTypeLookup.setRenderLayer(BlockInit.LEMON_SAPLING.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.COOKIE_JAR.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.CHOCOLATE_SAPLING.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.COKE_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(BlockInit.CORN_CROP.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.FRIED_POTATOS_PLANT.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.CHOCOLATE_DOOR.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.LEMON_DOOR.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.CORN_BUSH.get(), RenderType.getCutout());
		
		//GUI
		ScreenManager.registerFactory(ModContainerTypes.OVEN.get(), OvenScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.COOKIE_JAR.get(), CookieJarScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.MORTAR.get(), MortarScreen::new);
		
		//ENTITIES
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CHOCOLATE_BEETLE.get(), ChocolateBeetleEntityRender::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CORN_SNAKE.get(), CornSnakeEntityRender::new);
	}
}
