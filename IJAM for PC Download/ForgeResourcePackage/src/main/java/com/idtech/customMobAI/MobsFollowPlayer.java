package com.idtech.customMobAI;

import com.idtech.entity.Herobrine;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;

import java.util.Iterator;
import java.util.List;

public class MobsFollowPlayer extends EntityAIBase {

    private final Herobrine herobrine;
    private final EntityMob[] followers;
    private EntityPlayer leader;
    private int delayCounter;

    public MobsFollowPlayer(EntityMob[] par1Entities, Herobrine _herobrine)
    {
        this.herobrine = _herobrine;
        this.followers = par1Entities;
        this.setMutexBits(3);

    }

    @Override
    public  boolean isInterruptible() {
        return true;
    }

    @Override
    public boolean shouldExecute() {
        for (EntityMob follower: followers){
            List<EntityPlayer> lvt_1_1_ = follower.world.getEntitiesWithinAABB(EntityPlayer.class, follower.getEntityBoundingBox().grow(8.0D, 4.0D, 8.0D));
            if (lvt_1_1_.isEmpty()) {
                return false;
            } else {
                Iterator var2 = lvt_1_1_.iterator();

                while(var2.hasNext()) {
                    EntityPlayer lvt_3_1_ = (EntityPlayer) var2.next();
                    this.leader = lvt_3_1_;
                    break;
                }
                return this.leader != null;
            }
        }
        return true; //Idk if this is nevessary, java just complains
    }

    @Override
    public void startExecuting()
    {
        this.delayCounter = 0;
    }

    @Override
    public void resetTask() {
        this.leader = null;
    }

    //at least 2 mobs remain
    @Override
    public boolean shouldContinueExecuting()
    {
        int mobSize = 0;
        for (EntityMob follower: followers) {
            if (this.leader.isEntityAlive() || (follower.getDistanceSq(this.leader) < 3.0D)) {
                mobSize ++;
            }
            if(mobSize >= 2) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateTask() {
        if (--this.delayCounter <= 0) {
            this.delayCounter = 10;
            for (EntityMob follower: followers) {
                follower.getNavigator().tryMoveToEntityLiving(this.leader, 1D);
            }
            herobrine.doneAttackMobs(followers);
        }
    }

}
