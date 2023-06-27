package com.idtech.customMobAI;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;

import java.util.Iterator;
import java.util.List;

public class SkeletonFollowZombie extends EntityAIBase {

    private final EntitySkeleton follower;
    private EntityZombie leader;
    private int delayCounter;

    public SkeletonFollowZombie(EntitySkeleton par1Entity)
    {
        this.follower = par1Entity;
        this.setMutexBits(3);

    }

    @Override
    public  boolean isInterruptible() {
        return true;
    }

    @Override
    public boolean shouldExecute() {

        List<EntityZombie> lvt_1_1_ = this.follower.world.getEntitiesWithinAABB(EntityZombie.class, this.follower.getEntityBoundingBox().grow(8.0D, 4.0D, 8.0D));
        if (lvt_1_1_.isEmpty()) {
            return false;
        } else {
            Iterator var2 = lvt_1_1_.iterator();

            while(var2.hasNext()) {
                EntityZombie lvt_3_1_ = (EntityZombie) var2.next();
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
