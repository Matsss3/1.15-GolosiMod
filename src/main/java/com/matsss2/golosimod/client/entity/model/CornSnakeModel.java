package com.matsss2.golosimod.client.entity.model;

import com.matsss2.golosimod.entities.CornSnake;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

public class CornSnakeModel extends AnimatedEntityModel<CornSnake> {
	private final AnimatedModelRenderer Body;
	private final AnimatedModelRenderer Head;
	private final AnimatedModelRenderer BodyPart1;
	private final AnimatedModelRenderer BodyPart2;
	private final AnimatedModelRenderer BodyPart3;
	private final AnimatedModelRenderer BodyPart4;
	private final AnimatedModelRenderer BodyPart5;
	private final AnimatedModelRenderer Tail;

	public CornSnakeModel() {
		textureWidth = 32;
		textureHeight = 32;

		Body = new AnimatedModelRenderer(this);
		Body.setRotationPoint(0.0F, 24.0F, -2.0F);
		Body.setModelRendererName("Body");
		this.registerModelRenderer(Body);

		Head = new AnimatedModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(Head);
		Head.setTextureOffset(0, 0).addBox(-2.0F, -3.0F, -12.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
		Head.setTextureOffset(14, 24).addBox(-2.0F, -2.0F, -14.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
		Head.setModelRendererName("Head");
		this.registerModelRenderer(Head);

		BodyPart1 = new AnimatedModelRenderer(this);
		BodyPart1.setRotationPoint(1.0F, 0.0F, 0.0F);
		Body.addChild(BodyPart1);
		BodyPart1.setTextureOffset(0, 20).addBox(-3.0F, -2.0F, -8.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		BodyPart1.setModelRendererName("BodyPart1");
		this.registerModelRenderer(BodyPart1);

		BodyPart2 = new AnimatedModelRenderer(this);
		BodyPart2.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(BodyPart2);
		BodyPart2.setTextureOffset(12, 18).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		BodyPart2.setModelRendererName("BodyPart2");
		this.registerModelRenderer(BodyPart2);

		BodyPart3 = new AnimatedModelRenderer(this);
		BodyPart3.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(BodyPart3);
		BodyPart3.setTextureOffset(0, 14).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		BodyPart3.setModelRendererName("BodyPart3");
		this.registerModelRenderer(BodyPart3);

		BodyPart4 = new AnimatedModelRenderer(this);
		BodyPart4.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(BodyPart4);
		BodyPart4.setTextureOffset(12, 3).addBox(-2.0F, -2.0F, 4.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		BodyPart4.setModelRendererName("BodyPart4");
		this.registerModelRenderer(BodyPart4);

		BodyPart5 = new AnimatedModelRenderer(this);
		BodyPart5.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(BodyPart5);
		BodyPart5.setTextureOffset(12, 12).addBox(-2.0F, -2.0F, 8.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		BodyPart5.setModelRendererName("BodyPart5");
		this.registerModelRenderer(BodyPart5);

		Tail = new AnimatedModelRenderer(this);
		Tail.setRotationPoint(0.0F, 0.0F, 4.0F);
		Body.addChild(Tail);
		Tail.setTextureOffset(0, 7).addBox(-2.0F, -2.0F, 8.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		Tail.setTextureOffset(12, 0).addBox(-2.0F, -1.0F, 12.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);
		Tail.setTextureOffset(24, 0).addBox(-1.0F, -1.0F, 14.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		Tail.setTextureOffset(16, 9).addBox(-1.0F, -2.0F, 12.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		Tail.setModelRendererName("Tail");
		this.registerModelRenderer(Tail);
		
		this.rootBones.add(Body);
	}

	@Override
	public ResourceLocation getAnimationFileLocation() {
		return new ResourceLocation("golosimod", "animations/corn_snake.json");
	}
}
