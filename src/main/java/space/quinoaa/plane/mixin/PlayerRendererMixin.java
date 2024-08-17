package space.quinoaa.plane.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.quinoaa.plane.client.render.PlaneRenderer;
import space.quinoaa.plane.entity.Plane;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {


    @Inject(
            at = @At("HEAD"),
            method = "render(Lnet/minecraft/client/player/AbstractClientPlayer;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            cancellable = true
    )
    public void renderStart(AbstractClientPlayer pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, CallbackInfo ci){
        if(!(pEntity.getVehicle() instanceof Plane plane)) return;
        if(!PlaneRenderer.BYPASS_RENDER) ci.cancel();
    }

    @Inject(
            at = @At("HEAD"),
            method = "renderNameTag(Lnet/minecraft/client/player/AbstractClientPlayer;Lnet/minecraft/network/chat/Component;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            cancellable = true
    )
    public void renderTag(AbstractClientPlayer pEntity, Component pDisplayName, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, CallbackInfo ci){
        if(!(pEntity.getVehicle() instanceof Plane plane)) return;
        ci.cancel();
    }

}
