package com.idtech.customMobAI;

import com.idtech.entity.Herobrine;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;

import java.util.Iterator;
import java.util.List;

public class MobsAttackPlayer extends EntityAIBase {

    private final Herobrine herobrine;
    private final EntityMob[] attackers;
    private EntityPlayer target;
    private int delayCounter;

    public MobsAttackPlayer(EntityMob[] par1Entities, Herobrine _herobrine)
    {
        this.attackers = par1Entities;
        this.herobrine = _herobrine;
        this.setMutexBits(3);

    }

    @Override
    public  boolean isInterruptible() {
        return true;
    }

    @Override
    public boolean shouldExecute() {
        for (EntityMob attacker: attackers) {
            List<EntityPlayer> lvt_1_1_ = attacker.world.getEntitiesWithinAABB(EntityPlayer.class, attacker.getEntityBoundingBox().grow(8.0D, 4.0D, 8.0D));
            if (lvt_1_1_.isEmpty()) {
                return false;
            } else {
                Iterator var2 = lvt_1_1_.iterator();

                while (var2.hasNext()) {
                    EntityPlayer lvt_3_1_ = (EntityPlayer) var2.next();
                    this.target = lvt_3_1_;
                    break;
                }
                return this.target != null;
            }
        }
        herobrine.doneAttackMobs(attackers);
        return true;
    }

    @Override
    public void startExecuting()
    {
        this.delayCounter = 0;
        for (EntityMob attacker: attackers) {
            attacker.targetTasks.addTask(1, new EntityAINearestAttackableTarget(attacker, EntityPlayer.class, false));
        }
    }

    @Override
    public void resetTask() {
        this.target = null;
    }

    //at least 2 mobs remain
    @Override
    public boolean shouldContinueExecuting()
    {
        int mobSize = 0;
        for (EntityMob attacker: attackers) {
            if (this.target.isEntityAlive() || (attacker.getDistanceSq(this.target) < 3.0D)) {
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
            for (EntityMob attacker: attackers) {
                attacker.getNavigator().tryMoveToEntityLiving(this.target, 1D);
            }
            herobrine.doneAttackMobs(attackers);
        }
    }

}
