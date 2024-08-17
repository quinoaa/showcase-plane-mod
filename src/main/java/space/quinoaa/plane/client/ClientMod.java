package space.quinoaa.plane.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import space.quinoaa.plane.PlaneMod;
import space.quinoaa.plane.client.render.PlaneRenderer;
import space.quinoaa.plane.init.PlaneEntities;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD, modid = PlaneMod.MODID)
public class ClientMod {


    @SubscribeEvent
    public static void init(FMLClientSetupEvent event){

    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(PlaneEntities.PLANE.get(), ctx-> new PlaneRenderer(ctx));
    }
}
