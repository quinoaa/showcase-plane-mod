package space.quinoaa.plane.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import space.quinoaa.plane.PlaneMod;
import space.quinoaa.plane.network.PlaneMovePacket;

public class PlaneNetwork {
    public static final String PROTOCOL = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(PlaneMod.MODID, "main"),
            ()->PROTOCOL,
            PROTOCOL::equals,
            PROTOCOL::equals
    );

    public static void init(){
        CHANNEL.registerMessage(0, PlaneMovePacket.class, PlaneMovePacket::encode, PlaneMovePacket::decode, PlaneMovePacket::handle);
    }
}
