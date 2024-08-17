package space.quinoaa.plane.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.vehicle.Minecart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import space.quinoaa.plane.entity.Plane;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Inject(
            at = @At("HEAD"),
            method = "startAttack",
            cancellable = true
    )
    public void cancelAttack(CallbackInfoReturnable<Boolean> cir){
        var plr = Minecraft.getInstance().player;
        if(plr != null && plr.getVehicle() instanceof Plane plane){
            cir.setReturnValue(true);
        }
    }

    @Inject(
            at = @At("HEAD"),
            method = "continueAttack",
            cancellable = true
    )
    public void cancelAttack(boolean pLeftClick, CallbackInfo ci){
        var plr = Minecraft.getInstance().player;
        if(plr != null && plr.getVehicle() instanceof Plane plane){
            ci.cancel();
        }
    }

    @Inject(
            at = @At("HEAD"),
            method = "startUseItem",
            cancellable = true
    )
    public void cancelUse(CallbackInfo ci){
        var plr = Minecraft.getInstance().player;
        if(plr != null && plr.getVehicle() instanceof Plane plane){
            ci.cancel();
        }
    }
}
