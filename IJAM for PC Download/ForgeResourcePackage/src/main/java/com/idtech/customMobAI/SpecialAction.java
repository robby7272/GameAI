package com.idtech.customMobAI;

import com.idtech.entity.Herobrine;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.*;

public class SpecialAction extends EntityAIBase {

    //Each mob has an action to get towards the player in some way
    private final Herobrine herobrine;
    private final EntityMob mob;
    private final String type;
    private final Entity player;

    public SpecialAction(EntityMob par1Entity, String type, Entity player, Herobrine _herobrine)
    {
        this.mob = par1Entity;
        this.type = type;
        this.player = player;
        this.herobrine = _herobrine;
        this.setMutexBits(3);

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
        switch (this.type) {
            case "skeleton":

                break;
            case "creeper":

                break;
            case "zombie":

                break;
            case "witch":

                break;
            case "enderman":

                break;
            case "spider":

                break;
        }
    }

    @Override
    public void resetTask() {

    }

    @Override
    public boolean shouldContinueExecuting()
    {
        //add break statements later, this is to check
        switch (this.type) {
            case "skeleton":
                herobrine.doneAction(mob);
                break;
            case "creeper":
                herobrine.doneAction(mob);
                break;
            case "zombie":
                herobrine.doneAction(mob);
                break;
            case "witch":
                herobrine.doneAction(mob);
                break;
            case "enderman":
                herobrine.doneAction(mob);
                break;
            case "spider":
                herobrine.doneAction(mob);
                break;
        }
        return true;
    }

    @Override
    public void updateTask() {
        switch (this.type) {
            case "skeleton":

                break;
            case "creeper":

                break;
            case "zombie":

                break;
            case "witch":

                break;
            case "enderman":

                break;
            case "spider":

                break;
        }
    }
}
