package space.quinoaa.plane.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import space.quinoaa.plane.PlaneMod;
import space.quinoaa.plane.client.PlaneCameraHandler;
import space.quinoaa.plane.client.PlaneControlsHandler;
import space.quinoaa.plane.init.PlaneNetwork;
import space.quinoaa.plane.network.PlaneMovePacket;

public class Plane extends Entity {
    public float throttle0 = 0;
    public float throttle = 0;

    public float zRot = 0, zRot0 = 0;

    public float engineAnim = 0;

    public Plane(EntityType<? extends Plane> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData() {

    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        if(getPassengers().isEmpty()) return null;
        return getPassengers().get(0) instanceof LivingEntity l ? l : null;
    }


    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public boolean isPickable() {
        return true;
    }





    @Override
    protected boolean canAddPassenger(Entity pPassenger) {
        return getPassengers().isEmpty();
    }

    @Override
    public float getViewXRot(float pPartialTicks) {
        return super.getViewXRot(pPartialTicks);
    }

    @Override
    public void tick() {
        super.tick();
        if(isControlledByLocalInstance() && level().isClientSide){
            var throttleTarget = PlaneControlsHandler.getThrottleTarget();

            throttle0 = throttle;
            if(throttleTarget == 0 && Mth.abs(throttle) < 0.5f){
                throttle = 3 * throttle / 4;
            }

            float amount = throttleTarget * .05f;
            if(throttleTarget > 0) amount *= (2 - (throttle)) * .05;
            else amount *= 0.5;
            throttle += amount;
            if(throttle < 0) throttle = 0;
            if(throttle > 2) throttle = 2;

            var turnTarget = PlaneControlsHandler.turnTarget();

            zRot0 = zRot;
            zRot = (zRot * 7 + turnTarget * 40) / 8;
            setYRot(getYRot() + turnTarget * 6);

            addDeltaMovement(getLookAngle().scale(throttle * 0.1f));

            var mvnt = getDeltaMovement();
            var keepRatio = Math.exp(-mvnt.length() * .1) * .6;
            var keep = mvnt.scale(keepRatio);
            var directed = getLookAngle().scale(mvnt.length() * (1 - keepRatio));
            setDeltaMovement(keep.add(directed));

            move(MoverType.SELF, getDeltaMovement());

            float vertical = PlaneControlsHandler.verticalTarget() * 4;
            setXRot(getXRot() - vertical);

            addDeltaMovement(new Vec3(0, -.06, 0));
            if(onGround()) setDeltaMovement(getDeltaMovement().scale(.8f));
            else setDeltaMovement(getDeltaMovement().scale(Math.max(0, 1 - mvnt.length() * 0.01)));
        }else{
            if(!getPassengers().isEmpty()) return;
            throttle = 0;
            var mvnt = getDeltaMovement();

            move(MoverType.SELF, getDeltaMovement());

            addDeltaMovement(new Vec3(0, -.06, 0));
            if(onGround()) setDeltaMovement(getDeltaMovement().scale(.8f));
            else setDeltaMovement(getDeltaMovement().scale(Math.max(0, 1 - mvnt.length() * 0.01)));
        }

        var passenger = getControllingPassenger();
        if(passenger != null){
            passenger.setYRot(0);
        }
    }

    @Override
    public double getMyRidingOffset() {
        return -2;
    }

    @Override
    public InteractionResult interact(Player pPlayer, InteractionHand pHand) {
        if(level().isClientSide) return InteractionResult.SUCCESS;
        if(!getPassengers().isEmpty()) return InteractionResult.CONSUME;

        pPlayer.startRiding(this);

        return InteractionResult.CONSUME;
    }

    @Override
    public void lerpTo(double pX, double pY, double pZ, float pYRot, float pXRot, int pLerpSteps, boolean pTeleport) {
        //if(isControlledByLocalInstance()) return;

        setYRot(pYRot);
        setXRot(pXRot);
        setPos(pX, pY, pZ);

        super.lerpTo(pX, pY, pZ, pYRot, pXRot, pLerpSteps, pTeleport);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {

    }
}
