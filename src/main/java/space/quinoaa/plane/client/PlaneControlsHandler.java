package space.quinoaa.plane.client;

import net.minecraft.client.Minecraft;

public class PlaneControlsHandler {

    public static float getThrottleTarget() {
        var opt = Minecraft.getInstance().options;

        float target = 0;
        if(opt.keyUp.isDown()) target += 1f;
        if(opt.keyDown.isDown()) target -= 1f;

        return target;
    }

    public static float turnTarget(){
        var opt = Minecraft.getInstance().options;

        float target = 0;
        if(opt.keyRight.isDown()) target += 1f;
        if(opt.keyLeft.isDown()) target -= 1f;

        return target;
    }

    public static float verticalTarget(){
        var opt = Minecraft.getInstance().options;

        float target = 0;
        if(opt.keyJump.isDown()) target += 1f;
        if(opt.keySprint.isDown()) target -= 1f;

        return target;
    }
}
