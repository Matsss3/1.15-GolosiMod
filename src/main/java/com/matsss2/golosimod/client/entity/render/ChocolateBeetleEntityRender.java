package com.matsss2.golosimod.client.entity.render;

import com.matsss2.golosimod.GolosiMod;
import com.matsss2.golosimod.client.entity.model.ChocolateBeetleModel;
import com.matsss2.golosimod.entities.ChocolateBeetle;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class ChocolateBeetleEntityRender extends MobRenderer<ChocolateBeetle, ChocolateBeetleModel> {

	protected static final ResourceLocation TEXTURE = new ResourceLocation(GolosiMod.MOD_ID,
			"textures/entity/chocolate_beetle.png");

	public ChocolateBeetleEntityRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ChocolateBeetleModel(), 0.5f);
	}
	
	@Override
	public ResourceLocation getEntityTexture(ChocolateBeetle entity) {
		return TEXTURE;
	}
}
