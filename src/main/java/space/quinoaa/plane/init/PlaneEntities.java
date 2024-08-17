package space.quinoaa.plane.init;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import space.quinoaa.plane.PlaneMod;
import space.quinoaa.plane.entity.Plane;

public class PlaneEntities {
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PlaneMod.MODID);

    public static final RegistryObject<EntityType<Plane>> PLANE = REGISTER.register("plane", ()->EntityType.Builder.of(Plane::new, MobCategory.MISC).updateInterval(2).sized(2, 1).build("plane"));

    public static void init(IEventBus bus){
        REGISTER.register(bus);

        bus.addListener(PlaneEntities::registerAttributes);
    }

    private static void registerAttributes(EntityAttributeCreationEvent event){

    }
}
