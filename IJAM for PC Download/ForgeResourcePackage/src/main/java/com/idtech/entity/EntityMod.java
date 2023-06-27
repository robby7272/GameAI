package com.idtech.entity;

import com.idtech.BaseMod;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import scala.xml.dtd.impl.Base;

public class EntityMod {
    public static int currentEntityId = 0;

    public static void preInit(){
        createEntityWithEgg(Herobrine.class, "Herobrine", 0x000000, 0x000000);
        BaseMod.proxy.registerEntityRenderers();
    }

    public static void init(){

    }


    public static void createEntityWithEgg(Class entityClass, String entityName, int solidColor, int spotColor){
        int entityId = currentEntityId++;
        //EntityRegistry makes Herobrine known to the game, it has nothing to do with active mobs
        EntityRegistry.registerModEntity(new ResourceLocation(BaseMod.MODID, entityName), entityClass, entityName, entityId, BaseMod.instance, 250, 1 , true, solidColor, spotColor);
    }
}
