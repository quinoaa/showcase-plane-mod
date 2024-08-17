package space.quinoaa.plane.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Quaternionf;
import space.quinoaa.plane.PlaneMod;
import space.quinoaa.plane.entity.Plane;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD, modid = PlaneMod.MODID)
public class PlaneGuiRenderer implements IGuiOverlay {
    public static final ResourceLocation GUI = new ResourceLocation(PlaneMod.MODID, "textures/gui/plane_gui.png");

    private int scale = 3;

    @Override
    public void render(ForgeGui gui, GuiGraphics g, float partialTick, int screenWidth, int screenHeight) {
        var mc = gui.getMinecraft();
        var plr = mc.player;
        if(plr == null) return;
        if(!(plr.getVehicle() instanceof Plane plane)) return;

        scale = Math.max(screenWidth / 200, 1);
        g.pose().pushPose();
        g.pose().scale(scale, scale, scale);
        renderThrottle(gui, g, partialTick, screenWidth / scale, screenHeight / scale, Mth.lerp(partialTick, plane.throttle0, plane.throttle));
        renderGyro(gui, g, partialTick, screenWidth / scale, screenHeight / scale);

        g.pose().popPose();
    }

    private void renderGyro(ForgeGui gui, GuiGraphics g, float partialTick, int width, int height) {
        int gyroX = 18, gyroY = height - 17;

        g.blit(GUI, gyroX + 2, gyroY + 2, 50, 0, 14, 14, 256, 256);

        var pose = g.pose();
        pose.pushPose();
        pose.translate(gyroX + 9, gyroY + 9, 20);

        var plr = Minecraft.getInstance().player;
        if(plr != null && plr.getVehicle() instanceof Plane plane){

            pose.mulPose(new Quaternionf().rotateZ(Mth.lerp(partialTick, plane.zRot0, plane.zRot) * Mth.DEG_TO_RAD)
                    .rotateX(plane.getViewXRot(partialTick) * Mth.DEG_TO_RAD)
                    .rotateY(plane.getViewYRot(partialTick) * Mth.DEG_TO_RAD));

            pose.pushPose();
            pose.translate(0, 0, 5);
            g.blit(GUI, -5, -5, 84, 0, 10, 10, 256, 256);
            pose.popPose();

            pose.mulPose(Axis.YP.rotationDegrees(90));
            pose.pushPose();
            pose.translate(0, 0, 5);
            g.blit(GUI, -5, -5, 84, 0, 10, 10, 256, 256);
            pose.popPose();

            pose.mulPose(Axis.YP.rotationDegrees(90));
            pose.pushPose();
            pose.translate(0, 0, 5);
            g.blit(GUI, -5, -5, 84, 0, 10, 10, 256, 256);
            pose.popPose();

            pose.mulPose(Axis.YP.rotationDegrees(90));
            pose.pushPose();
            pose.translate(0, 0, 5);
            g.blit(GUI, -5, -5, 84, 0, 10, 10, 256, 256);
            pose.popPose();

            pose.mulPose(Axis.XP.rotationDegrees(-90));
            pose.pushPose();
            pose.translate(0, 0, 5);
            g.blit(GUI, -5, -5, 64, 0, 10, 10, 256, 256);
            pose.popPose();

            pose.mulPose(Axis.XP.rotationDegrees(180));
            pose.pushPose();
            pose.translate(0, 0, 5);
            g.blit(GUI, -5, -5, 74, 0, 10, 10, 256, 256);
            pose.popPose();
        }

        pose.popPose();


        g.flush();
        g.blit(GUI, gyroX, gyroY, 32, 0, 18, 18, 256, 256);
    }

    private void renderThrottle(ForgeGui gui, GuiGraphics g, float partialTick, int screenWidth, int screenHeight, float throttleAmount){
        int throttleX = 0, throttleY = screenHeight - 33;
        g.blit(GUI, throttleX + 2, throttleY + 2, 18, 0, 14, 30, 256, 256);

        float throttleOff = 20 * (1 - throttleAmount * .5f);
        g.pose().pushPose();
        g.pose().translate(0, throttleOff, 0);
        g.blit(GUI, throttleX + 2, throttleY + 3, 18, 30, 14, 8, 256, 256);
        g.pose().popPose();

        g.blit(GUI, throttleX, throttleY, 0, 0, 18, 34, 256, 256);
    }



    @SubscribeEvent
    public static void register(RegisterGuiOverlaysEvent event){
        event.registerBelowAll("plane_overlay", new PlaneGuiRenderer());
    }

}
