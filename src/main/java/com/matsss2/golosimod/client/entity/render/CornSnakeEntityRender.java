package com.matsss2.golosimod.client.entity.render;

import com.matsss2.golosimod.GolosiMod;
import com.matsss2.golosimod.client.entity.model.CornSnakeModel;
import com.matsss2.golosimod.entities.CornSnake;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class CornSnakeEntityRender extends MobRenderer<CornSnake, CornSnakeModel> {

	protected static final ResourceLocation TEXTURE = new ResourceLocation(GolosiMod.MOD_ID,
			"textures/entity/corn_snake.png");
	
	public CornSnakeEntityRender(EntityRendererManager renderIn) {
		super(renderIn, new CornSnakeModel(), 0.5f);
	}
	
	@Override
	public ResourceLocation getEntityTexture(CornSnake entity) {
		return TEXTURE;
	}
}
