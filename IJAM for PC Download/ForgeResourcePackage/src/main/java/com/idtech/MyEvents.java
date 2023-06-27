package com.idtech;

import com.idtech.entity.Herobrine;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MyEvents {
    private static Herobrine herobrine = null;


    @SubscribeEvent
    public void onEntitySpawn(EntityJoinWorldEvent event)
    {
        if (event.getEntity() instanceof EntityLiving && !(event.getEntity() instanceof EntityPlayer)){
            doTaskStuff((EntityLiving) event.getEntity(), event);
        }
    }

    // calls Herobrine when creeper explodes
    @SubscribeEvent
    public void onEntityDespawn(ExplosionEvent.Start event){
        if (event.getExplosion().getExplosivePlacedBy() instanceof EntityCreeper && herobrine != null) {
            herobrine.creeperExploded((EntityCreeper) event.getExplosion().getExplosivePlacedBy());
        }
    }

    public static void doTaskStuff(EntityLiving e, Event ev)
    {
        //for (Object a : e.targetTasks.taskEntries.toArray())
        //{
        //    EntityAIBase ai = ((EntityAITasks.EntityAITaskEntry) a).action;

        //    if ((e instanceof EntityCreeper || e instanceof EntitySkeleton || e instanceof EntityZombie) && ai instanceof EntityAIHurtByTarget)
        //        e.targetTasks.removeTask(ai);

        //}

        if (e instanceof Herobrine) {
            herobrine = (Herobrine) e;
        }
    }
}
