package com.idtech.customMobAI;

import com.idtech.entity.Herobrine;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;

import java.util.List;

public class SendCreepersToExplode extends EntityAIBase {

    //Each mob has an action to get towards the player in some way
    private final Herobrine herobrine;
    private final EntityMob mob;
    private final String type;
    private final Entity player;
    private final List<EntityMob> mobAllies;
    EntityCreeper currentCreeper;

    public SendCreepersToExplode(EntityMob par1Entity, String type, Entity player, Herobrine _herobrine)
    {
        this.mob = par1Entity;
        this.type = type;
        this.player = player;
        this.herobrine = _herobrine;
        this.setMutexBits(3);
        this.mobAllies = herobrine.mobAllies;
    }

    @Override
    public  boolean isInterruptible() {
        return true;
    }

    @Override
    public boolean shouldExecute() {
        return false;
    }

    @Override
    public void startExecuting(){
        for(EntityMob mob: mobAllies){
            if(mob instanceof EntityCreeper){
                currentCreeper = (EntityCreeper) mob;
                break;
            }
        }
        //currentCreeper.tasks.addTask(0, );
    }

    @Override
    public void resetTask() {

    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return false;
    }

    @Override
    public void updateTask() {

    }
}
