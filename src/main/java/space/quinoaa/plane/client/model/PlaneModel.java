package space.quinoaa.plane.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import space.quinoaa.plane.PlaneMod;
import space.quinoaa.plane.entity.Plane;

public class PlaneModel<T extends Plane> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PlaneMod.MODID, "plane"), "main");
	private final ModelPart root;
	private final ModelPart fontWings;
	private final ModelPart backRightWing;
	private final ModelPart backLeftWing;
	private final ModelPart backWing;
	private final ModelPart rightWing;
	private final ModelPart rightWinglet;
	private final ModelPart leftWing;
	private final ModelPart leftWinglet;
	private final ModelPart bb_main;

	public PlaneModel(ModelPart root) {
		this.root = root;
		this.fontWings = root.getChild("fontWings");
		this.backRightWing = root.getChild("backRightWing");
		this.backLeftWing = root.getChild("backLeftWing");
		this.backWing = root.getChild("backWing");
		this.rightWing = root.getChild("rightWing");
		this.rightWinglet = rightWing.getChild("rightWinglet");
		this.leftWing = root.getChild("leftWing");
		this.leftWinglet = leftWing.getChild("leftWinglet");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition fontWings = partdefinition.addOrReplaceChild("fontWings", CubeListBuilder.create().texOffs(90, 29).addBox(-8.0F, -8.0F, 2.0F, 16.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.5F, -32.0F));

		PartDefinition backRightWing = partdefinition.addOrReplaceChild("backRightWing", CubeListBuilder.create(), PartPose.offset(-3.0F, 18.0F, 31.0F));

		PartDefinition cube_r1 = backRightWing.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(14, 29).addBox(-0.5F, -9.0F, -1.0F, 0.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -2.0F, -0.2618F, 0.0F, -1.5708F));

		PartDefinition cube_r2 = backRightWing.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(10, 20).addBox(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(44, 8).addBox(-1.0F, -8.0F, 1.0F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition backLeftWing = partdefinition.addOrReplaceChild("backLeftWing", CubeListBuilder.create(), PartPose.offset(3.0F, 18.0F, 31.0F));

		PartDefinition cube_r3 = backLeftWing.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(38, 5).addBox(0.5F, -9.0F, -1.0F, 0.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -2.0F, -0.2618F, 0.0F, 1.5708F));

		PartDefinition cube_r4 = backLeftWing.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(16, 20).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(49, 32).addBox(0.0F, -8.0F, 1.0F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition backWing = partdefinition.addOrReplaceChild("backWing", CubeListBuilder.create().texOffs(0, 54).addBox(-0.5F, -8.0F, 1.0F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 32).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, 31.0F));

		PartDefinition cube_r5 = backWing.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(14, 38).addBox(0.5F, -9.0F, -1.0F, 0.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition rightWing = partdefinition.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(39, 54).addBox(-19.0F, -0.5F, -2.0F, 19.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(52, 78).addBox(-39.0F, -0.5F, 2.0F, 20.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 20.0F, -12.0F));

		PartDefinition cube_r6 = rightWing.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(75, 8).addBox(-19.0F, -1.0F, 0.0F, 19.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.6F, -5.0F, 0.0F, 0.1309F, 0.0F));

		PartDefinition cube_r7 = rightWing.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(64, 23).addBox(-21.0F, 0.0F, 0.0F, 21.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.9F, -0.45F, -2.5F, 0.0F, 0.1745F, 0.0F));

		PartDefinition rightWinglet = rightWing.addOrReplaceChild("rightWinglet", CubeListBuilder.create().texOffs(38, 0).addBox(-39.0F, -0.5F, 6.0F, 39.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftWing = partdefinition.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(50, 63).addBox(0.0F, -0.5F, -2.0F, 19.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(52, 83).addBox(19.0F, -0.5F, 2.0F, 20.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 20.0F, -12.0F));

		PartDefinition cube_r8 = leftWing.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(75, 14).addBox(0.0F, -1.0F, 0.0F, 19.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.6F, -5.0F, 0.0F, -0.1309F, 0.0F));

		PartDefinition cube_r9 = leftWing.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(45, 72).addBox(0.0F, 0.0F, 0.0F, 21.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.9F, -0.45F, -2.5F, 0.0F, -0.1745F, 0.0F));

		PartDefinition leftWinglet = leftWing.addOrReplaceChild("leftWinglet", CubeListBuilder.create().texOffs(38, 4).addBox(0.0F, -0.5F, 6.0F, 39.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 54).addBox(-7.0F, -9.0F, -28.0F, 14.0F, 9.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(0, 20).addBox(-1.5F, -6.0F, -31.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(39, 54).addBox(-1.5F, -8.025F, -29.0F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(49, 32).addBox(-7.0F, -9.0F, -5.0F, 14.0F, 8.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.0F, -9.0F, 8.0F, 6.0F, 6.0F, 26.0F, new CubeDeformation(0.0F))
		.texOffs(18, 74).addBox(-5.0F, -13.0F, -5.0F, 10.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r10 = bb_main.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(87, 75).addBox(-2.0F, -2.0F, -13.0F, 4.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 21.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r11 = bb_main.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(92, 45).addBox(-2.0F, -5.0F, -11.0F, 2.0F, 6.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -3.9F, 18.0F, 0.0F, -0.1309F, 0.0F));

		PartDefinition cube_r12 = bb_main.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(94, 90).addBox(0.0F, -5.0F, -11.0F, 2.0F, 6.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -3.9F, 18.0F, 0.0F, 0.1309F, 0.0F));

		PartDefinition cube_r13 = bb_main.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 32).addBox(5.0F, -2.0F, -21.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 40).addBox(-8.0F, -2.0F, -21.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(38, 8).addBox(-7.0F, -2.0F, -21.0F, 12.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 16.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r14 = bb_main.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -2.0F, 0.0F, 5.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -6.0F, -29.0F, 0.0F, 0.2182F, 0.0F));

		PartDefinition cube_r15 = bb_main.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(14, 0).addBox(0.0F, -2.0F, 0.0F, 5.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -6.0F, -29.0F, 0.0F, -0.2182F, 0.0F));

		PartDefinition cube_r16 = bb_main.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 32).addBox(-7.0F, -1.0F, 0.0F, 14.0F, 1.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -26.0F, -0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r17 = bb_main.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 0).addBox(-0.3926F, 0.0F, -0.0086F, 1.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.7F, -8.0F, -11.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition cube_r18 = bb_main.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(68, 88).addBox(-0.6074F, 0.0F, -0.0086F, 1.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.7F, -8.0F, -11.0F, 0.0F, -0.0436F, 0.0F));

		PartDefinition cube_r19 = bb_main.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 74).addBox(-1.0F, 0.0F, -7.0F, 1.0F, 8.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.7F, -7.9F, -20.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition cube_r20 = bb_main.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(34, 78).addBox(0.0F, 0.0F, -7.0F, 1.0F, 8.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.7F, -7.9F, -20.0F, 0.0F, -0.0436F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		fontWings.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		backRightWing.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		backLeftWing.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		backWing.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightWing.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftWing.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		fontWings.zRot = pEntity.engineAnim * .3f;
	}
}