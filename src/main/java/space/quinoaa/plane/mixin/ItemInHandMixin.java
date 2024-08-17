package space.quinoaa.plane.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.quinoaa.plane.entity.Plane;

@Mixin(ItemInHandRenderer.class)
public class ItemInHandMixin {

    @Inject(
            at = @At("HEAD"),
            method = "renderItem",
            cancellable = true
    )
    public void render(LivingEntity pEntity, ItemStack pItemStack, ItemDisplayContext pDisplayContext, boolean pLeftHand, PoseStack pPoseStack, MultiBufferSource pBuffer, int pSeed, CallbackInfo ci){
        if(pEntity.getVehicle() instanceof Plane) ci.cancel();
    }

    @Inject(
            at = @At("HEAD"),
            method = "renderHandsWithItems",
            cancellable = true
    )
    public void render(float pPartialTicks, PoseStack pPoseStack, MultiBufferSource.BufferSource pBuffer, LocalPlayer pPlayerEntity, int pCombinedLight, CallbackInfo ci){
        if(pPlayerEntity.getVehicle() instanceof Plane) ci.cancel();
    }
}
