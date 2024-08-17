package space.quinoaa.plane.client;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import space.quinoaa.plane.PlaneMod;
import space.quinoaa.plane.entity.Plane;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE, modid = PlaneMod.MODID)
public class PlaneCameraHandler {

    @SubscribeEvent
    public static void handleCamera(TickEvent.ClientTickEvent event){
        Minecraft mc = Minecraft.getInstance();
        var plr = mc.player;
        if(plr == null) return;

        if(plr.getVehicle() instanceof Plane plane){
            mc.setCameraEntity(plane);
        }else{
            if(mc.getCameraEntity() instanceof Plane) mc.setCameraEntity(plr);
        }
    }

    @SubscribeEvent
    public static void handleAngles(ViewportEvent.ComputeCameraAngles event){
        Minecraft mc = Minecraft.getInstance();
        if(mc.getCameraEntity() instanceof Plane plane){
            event.setRoll((float) Mth.lerp(event.getPartialTick(), plane.zRot0, plane.zRot));
        }
    }
}
