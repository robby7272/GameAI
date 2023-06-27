package com.idtech.customMobAI;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;

import java.util.Iterator;
import java.util.List;

public class SkeletonFollowCreeper extends EntityAIBase {

    private final EntitySkeleton follower;
    private EntityCreeper leader;
    private EntityPlayer player;
    private int delayCounter;

    public SkeletonFollowCreeper(EntitySkeleton par1Entity)
    {
        this.follower = par1Entity;
        this.setMutexBits(3);
        List<EntityPlayer> lvt_1_1_ = this.follower.world.getEntitiesWithinAABB(EntityPlayer.class, this.follower.getEntityBoundingBox().grow(8.0D, 4.0D, 8.0D));
        if (lvt_1_1_.isEmpty()) {
        } else {
            Iterator var2 = lvt_1_1_.iterator();

            while(var2.hasNext()) {
                EntityPlayer lvt_3_1_ = (EntityPlayer) var2.next();
                this.player = lvt_3_1_;
            }
        }
    }

    @Override
    public  boolean isInterruptible() {
        return true;
    }

    @Override
    public boolean shouldExecute() {

        List<EntityCreeper> lvt_1_1_ = this.follower.world.getEntitiesWithinAABB(EntityCreeper.class, this.follower.getEntityBoundingBox().grow(8.0D, 4.0D, 8.0D));
        if (lvt_1_1_.isEmpty()) {
            return false;
        } else {
            Iterator var2 = lvt_1_1_.iterator();

            while(var2.hasNext()) {
                EntityCreeper lvt_3_1_ = (EntityCreeper) var2.next();
                this.leader = lvt_3_1_;
                break;
            }
            return this.leader != null;
        }
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

    @Override
    public boolean shouldContinueExecuting()
    {
        if (!this.leader.isEntityAlive() || (this.follower.getDistanceSq(this.leader) > 3.0D)) {
            return false;
        }
        if (this.player != null) {
            if (this.follower.getDistanceSq(this.leader) < 3.0D) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void updateTask() {
        if (--this.delayCounter <= 0) {
            this.delayCounter = 10;
            this.follower.getNavigator().tryMoveToEntityLiving(this.leader, 1D);
        }
    }

}
