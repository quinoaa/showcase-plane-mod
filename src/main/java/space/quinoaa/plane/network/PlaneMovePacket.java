package space.quinoaa.plane.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;
import space.quinoaa.plane.entity.Plane;

import java.util.function.Supplier;

public class PlaneMovePacket {
    public final float xRot, yRot, zRot;

    public PlaneMovePacket(float xRot, float yRot, float zRot) {
        this.xRot = xRot;
        this.yRot = yRot;
        this.zRot = zRot;
    }

    public void encode(FriendlyByteBuf b) {
        b.writeFloat(xRot);
        b.writeFloat(yRot);
        b.writeFloat(zRot);
    }

    public static PlaneMovePacket decode(FriendlyByteBuf b) {
        return new PlaneMovePacket(
                b.readFloat(), b.readFloat(), b.readFloat()
        );
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        var ctx = supplier.get();
        if(!ctx.getDirection().getReceptionSide().isServer()) return;
        ctx.setPacketHandled(true);

        ctx.enqueueWork(()->{
            var plr = ctx.getSender();

            if(!(plr.getVehicle() instanceof Plane plane)) return;

            plane.setXRot(xRot);
            plane.setYRot(yRot);
            plane.zRot = zRot;
        });
    }
}
