package com.matsss2.golosimod.client.entity.model;

import com.matsss2.golosimod.entities.ChocolateBeetle;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

public class ChocolateBeetleModel extends AnimatedEntityModel<ChocolateBeetle> {

    private final AnimatedModelRenderer Body;
	private final AnimatedModelRenderer Head;
	private final AnimatedModelRenderer Legs;
	private final AnimatedModelRenderer Back;
	private final AnimatedModelRenderer RightBack;
	private final AnimatedModelRenderer LeftBack;
	private final AnimatedModelRenderer Mid;
	private final AnimatedModelRenderer RightMid;
	private final AnimatedModelRenderer LeftMid;
	private final AnimatedModelRenderer Front;
	private final AnimatedModelRenderer RightFront;
	private final AnimatedModelRenderer LeftFront;

    public ChocolateBeetleModel()
    {
        textureWidth = 64;
    textureHeight = 64;
    Body = new AnimatedModelRenderer(this);
		Body.setRotationPoint(0.0F, 21.0F, 2.0F);
		Body.setTextureOffset(0, 0).addBox(-6.0F, -4.0F, -7.0F, 12.0F, 7.0F, 13.0F, 0.0F, false);
		Body.setTextureOffset(24, 24).addBox(-5.0F, -3.0F, 6.0F, 10.0F, 5.0F, 1.0F, 0.0F, false);
		Body.setTextureOffset(0, 20).addBox(-5.0F, -3.0F, -9.0F, 10.0F, 6.0F, 2.0F, 0.0F, false);
		Body.setModelRendererName("Body");
		this.registerModelRenderer(Body);

		Head = new AnimatedModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, -9.0F);
		Body.addChild(Head);
		Head.setTextureOffset(0, 28).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 4.0F, 3.0F, 0.0F, false);
		Head.setTextureOffset(26, 30).addBox(-3.0F, 1.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Head.setTextureOffset(22, 30).addBox(2.0F, 1.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Head.setModelRendererName("Head");
		this.registerModelRenderer(Head);

		Legs = new AnimatedModelRenderer(this);
		Legs.setRotationPoint(0.0F, 3.0F, -2.0F);
		Body.addChild(Legs);
		
		Legs.setModelRendererName("Legs");
		this.registerModelRenderer(Legs);

		Back = new AnimatedModelRenderer(this);
		Back.setRotationPoint(0.0F, 0.0F, 0.0F);
		Legs.addChild(Back);
		
		Back.setModelRendererName("Back");
		this.registerModelRenderer(Back);

		RightBack = new AnimatedModelRenderer(this);
		RightBack.setRotationPoint(-6.0F, -3.0F, 6.0F);
		Back.addChild(RightBack);
		RightBack.setTextureOffset(5, 3).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		RightBack.setTextureOffset(24, 20).addBox(-3.0F, 1.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		RightBack.setTextureOffset(18, 30).addBox(-4.0F, 2.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		RightBack.setModelRendererName("RightBack");
		this.registerModelRenderer(RightBack);

		LeftBack = new AnimatedModelRenderer(this);
		LeftBack.setRotationPoint(6.0F, -3.0F, 6.0F);
		Back.addChild(LeftBack);
		LeftBack.setTextureOffset(5, 1).addBox(0.0F, 0.0F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		LeftBack.setTextureOffset(4, 10).addBox(2.0F, 1.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		LeftBack.setTextureOffset(28, 20).addBox(3.0F, 2.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		LeftBack.setModelRendererName("LeftBack");
		this.registerModelRenderer(LeftBack);

		Mid = new AnimatedModelRenderer(this);
		Mid.setRotationPoint(0.0F, 0.0F, 0.0F);
		Legs.addChild(Mid);
		
		Mid.setModelRendererName("Mid");
		this.registerModelRenderer(Mid);

		RightMid = new AnimatedModelRenderer(this);
		RightMid.setRotationPoint(-6.0F, -3.0F, 2.0F);
		Mid.addChild(RightMid);
		RightMid.setTextureOffset(5, 5).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		RightMid.setTextureOffset(0, 9).addBox(-3.0F, 1.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		RightMid.setTextureOffset(19, 28).addBox(-4.0F, 2.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		RightMid.setModelRendererName("RightMid");
		this.registerModelRenderer(RightMid);

		LeftMid = new AnimatedModelRenderer(this);
		LeftMid.setRotationPoint(6.0F, -3.0F, 2.0F);
		Mid.addChild(LeftMid);
		LeftMid.setTextureOffset(0, 4).addBox(0.0F, 0.0F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		LeftMid.setTextureOffset(8, 8).addBox(2.0F, 1.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		LeftMid.setTextureOffset(15, 28).addBox(3.0F, 2.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		LeftMid.setModelRendererName("LeftMid");
		this.registerModelRenderer(LeftMid);

		Front = new AnimatedModelRenderer(this);
		Front.setRotationPoint(0.0F, 0.0F, 0.0F);
		Legs.addChild(Front);
		
		Front.setModelRendererName("Front");
		this.registerModelRenderer(Front);

		RightFront = new AnimatedModelRenderer(this);
		RightFront.setRotationPoint(-6.0F, -3.0F, -2.0F);
		Front.addChild(RightFront);
		RightFront.setTextureOffset(0, 2).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		RightFront.setTextureOffset(4, 7).addBox(-3.0F, 1.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		RightFront.setTextureOffset(27, 22).addBox(-4.0F, 2.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		RightFront.setModelRendererName("RightFront");
		this.registerModelRenderer(RightFront);

		LeftFront = new AnimatedModelRenderer(this);
		LeftFront.setRotationPoint(6.0F, -3.0F, -2.0F);
		Front.addChild(LeftFront);
		LeftFront.setTextureOffset(0, 0).addBox(0.0F, 0.0F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		LeftFront.setTextureOffset(0, 6).addBox(2.0F, 1.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		LeftFront.setTextureOffset(8, 11).addBox(3.0F, 2.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		LeftFront.setModelRendererName("LeftFront");
		this.registerModelRenderer(LeftFront);

    this.rootBones.add(Body);
  }


    @Override
    public ResourceLocation getAnimationFileLocation()
    {
        return new ResourceLocation("golosimod", "animations/chocolate_beetle.json");
    }
}