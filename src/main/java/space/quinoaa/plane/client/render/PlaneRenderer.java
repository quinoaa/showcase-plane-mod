package space.quinoaa.plane.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import space.quinoaa.plane.PlaneMod;
import space.quinoaa.plane.client.model.PlaneModel;
import space.quinoaa.plane.entity.Plane;

public class PlaneRenderer extends EntityRenderer<Plane> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(PlaneMod.MODID, "textures/entity/plane.png");
    public static boolean BYPASS_RENDER = false;

    private final PlaneModel<Plane> model = new PlaneModel<>(PlaneModel.createBodyLayer().bakeRoot());
    private final float scale = 1;

    private final EntityRenderDispatcher entityRender;

    public PlaneRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);

        entityRender = pContext.getEntityRenderDispatcher();
    }

    @Override
    public void render(Plane pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {


        pPoseStack.pushPose();
        pPoseStack.scale(-scale, -scale, scale);
        pPoseStack.mulPose(Axis.YP.rotationDegrees(180 + pEntity.getViewYRot(pPartialTick)));
        pPoseStack.mulPose(Axis.XP.rotationDegrees(pEntity.getViewXRot(pPartialTick)));
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(-Mth.lerp(pPartialTick, pEntity.zRot0, pEntity.zRot)));

        var passenger = pEntity.getControllingPassenger();
        if(passenger != null) {
            pPoseStack.pushPose();
            pPoseStack.mulPose(Axis.XP.rotationDegrees(180));
            pPoseStack.translate(0, -.5, 0);
            var renderer = entityRender.getRenderer(passenger);

            BYPASS_RENDER = true;
            renderer.render(passenger, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
            BYPASS_RENDER = false;
            pPoseStack.popPose();
        }

        float scale = 1.1f;
        pPoseStack.scale(scale, scale, scale);
        pPoseStack.translate(0, -1.5, .5);
        pEntity.engineAnim += pPartialTick * Mth.lerp(pPartialTick, pEntity.throttle0, pEntity.throttle);
        pEntity.engineAnim %= 360;

        model.setupAnim(pEntity, 0, 0, pEntity.tickCount + pPartialTick, 0, 0);
        var vc = pBuffer.getBuffer(model.renderType(getTextureLocation(pEntity)));
        model.renderToBuffer(pPoseStack, vc, pPackedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        pPoseStack.popPose();

        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(Plane pEntity) {
        return TEXTURE;
    }
}
