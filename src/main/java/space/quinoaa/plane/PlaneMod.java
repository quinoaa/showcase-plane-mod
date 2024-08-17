package space.quinoaa.plane;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import space.quinoaa.plane.init.PlaneEntities;
import space.quinoaa.plane.init.PlaneNetwork;

@Mod(PlaneMod.MODID)
public class PlaneMod {
    public static final String MODID = "plane";

    public static final Logger LOGGER = LogUtils.getLogger();

    public PlaneMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        PlaneEntities.init(modEventBus);
        PlaneNetwork.init();

    }

}
