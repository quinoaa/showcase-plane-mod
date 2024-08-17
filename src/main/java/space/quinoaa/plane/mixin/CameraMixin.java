package space.quinoaa.plane.mixin;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.quinoaa.plane.entity.Plane;

@Mixin(Camera.class)
public abstract class CameraMixin {


    @Shadow private boolean initialized;

    @Shadow private BlockGetter level;

    @Shadow private Entity entity;

    @Shadow private boolean detached;

    @Shadow protected abstract void setRotation(float pYRot, float pXRot);

    @Shadow private float yRot;

    @Shadow private float xRot;

    @Shadow protected abstract void move(double pDistanceOffset, double pVerticalOffset, double pHorizontalOffset);

    @Shadow protected abstract double getMaxZoom(double pStartingDistance);

    @Shadow private float eyeHeightOld;

    @Shadow private float eyeHeight;

    @Shadow protected abstract void setPosition(double pX, double pY, double pZ);

    @Inject(
            at = @At("HEAD"),
            method = "setup",
            cancellable = true
    )
    public void setup(BlockGetter pLevel, Entity pEntity, boolean pDetached, boolean pThirdPersonReverse, float pPartialTick, CallbackInfo ci){
        Minecraft mc = Minecraft.getInstance();
        var plr = mc.player;
        if(!(plr.getVehicle() instanceof Plane plane)) return;
        ci.cancel();

        this.initialized = true;
        this.level = pLevel;
        this.entity = pEntity;
        this.detached = pDetached;
        this.setRotation(pEntity.getViewYRot(pPartialTick), pEntity.getViewXRot(pPartialTick));
        this.setPosition(Mth.lerp((double)pPartialTick, pEntity.xo, pEntity.getX()), Mth.lerp((double)pPartialTick, pEntity.yo, pEntity.getY()) + (double)Mth.lerp(pPartialTick, this.eyeHeightOld, this.eyeHeight), Mth.lerp((double)pPartialTick, pEntity.zo, pEntity.getZ()));
        if (pDetached) {
            if (pThirdPersonReverse) {
                this.setRotation(this.yRot + 180.0F, -this.xRot);
            }

            this.move(-this.getMaxZoom(8.0D), 0.0D, 0.0D);
        } else if (pEntity instanceof LivingEntity && ((LivingEntity)pEntity).isSleeping()) {
            Direction direction = ((LivingEntity)pEntity).getBedOrientation();
            this.setRotation(direction != null ? direction.toYRot() - 180.0F : 0.0F, 0.0F);
            this.move(0.0D, 0.3D, 0.0D);
        }
    }
}
