package com.idtech.customMobAI;

import com.idtech.entity.Herobrine;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;

import java.util.Iterator;
import java.util.List;

public class MobAttackPlayer extends EntityAIBase {

    private final Herobrine herobrine;
    private final EntityMob attacker;
    private EntityPlayer target;
    private int delayCounter;

    public MobAttackPlayer(EntityMob par1Entity, Herobrine _herobrine)
    {
        this.attacker = par1Entity;
        this.herobrine = _herobrine;
        this.setMutexBits(3);

    }

    @Override
    public  boolean isInterruptible() {
        return true;
    }

    @Override
    public boolean shouldExecute() {

        List<EntityPlayer> lvt_1_1_ = this.attacker.world.getEntitiesWithinAABB(EntityPlayer.class, this.attacker.getEntityBoundingBox().grow(8.0D, 4.0D, 8.0D));
        if (lvt_1_1_.isEmpty()) {
            return false;
        } else {
            Iterator var2 = lvt_1_1_.iterator();

            while(var2.hasNext()) {
                EntityPlayer lvt_3_1_ = (EntityPlayer) var2.next();
                this.target = lvt_3_1_;
                break;
            }
            return this.target != null;
        }
    }

    @Override
    public void startExecuting()
    {
        this.delayCounter = 0;
        attacker.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this.attacker, EntityPlayer.class, false));
    }

    @Override
    public void resetTask() {
        this.target = null;
    }


    @Override
    public boolean shouldContinueExecuting()
    {
        if (!this.target.isEntityAlive() || (this.attacker.getDistanceSq(this.target) > 3.0D)) {
            herobrine.doneAttack(attacker);
            return false;
        }
        return true;
    }

    @Override
    public void updateTask() {
        if (--this.delayCounter <= 0) {
            this.delayCounter = 10;
            this.attacker.getNavigator().tryMoveToEntityLiving(this.target, 1D);
        }
    }

}
