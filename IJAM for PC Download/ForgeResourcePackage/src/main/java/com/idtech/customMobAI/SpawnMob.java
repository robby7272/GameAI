package com.idtech.customMobAI;

import com.idtech.MyEvents;
import com.idtech.entity.Herobrine;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.*;
import net.minecraft.util.math.BlockPos;


public class SpawnMob extends EntityAIBase {

    private final Herobrine herobrine;
    private BlockPos position;
    private int delayCounter;
    private String type;

    //used by agent to spawn any kind of mob
    //skeleton, creeper, zombie, witch, enderman, spider
    public SpawnMob(Herobrine _herobrine, String type)
    {
        //get herobrine somehow
        this.herobrine = _herobrine;
        //get position of herobrine
        this.position = this.herobrine.getPosition();
        //spawn respective mob
        this.type = type;
        this.setMutexBits(3);
    }

    @Override
    public  boolean isInterruptible() {
        return true;
    }

    //energy check at least
    //check if in queue
    @Override
    public boolean shouldExecute() {
        return herobrine.getEnergy() > 5;
    }

    @Override
    public void startExecuting()
    {
        this.delayCounter = 0;
    }

    @Override
    public void resetTask() {

    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return herobrine.getEnergy() > 50;
        // when finished (ie returns fals) then call Spawn Mob listener
        // should be done right when it starts
        // *maybe* add delay? idk
        //MyEvents.doneSpawn(herobrine);
    }

    @Override
    public void updateTask() {
            EntityMob mobSpawned = null;
            switch (this.type) {
                case "skeleton":
                    EntitySkeleton skeleton = new EntitySkeleton(this.herobrine.world);
                    skeleton.setLocationAndAngles(this.herobrine.posX, this.herobrine.posY, this.herobrine.posZ, 0, 0);
                    this.herobrine.world.spawnEntity(skeleton);
                    this.herobrine.removeEnergy(10);
                    mobSpawned = skeleton;
                    break;
                case "creeper":
                    EntityCreeper creeper = new EntityCreeper(this.herobrine.world);
                    creeper.setLocationAndAngles(this.herobrine.posX, this.herobrine.posY, this.herobrine.posZ, 0, 0);
                    this.herobrine.world.spawnEntity(creeper);
                    this.herobrine.removeEnergy(10);
                    mobSpawned = creeper;
                    break;
                case "zombie":
                    EntityZombie zombie = new EntityZombie(this.herobrine.world);
                    zombie.setLocationAndAngles(this.herobrine.posX, this.herobrine.posY, this.herobrine.posZ, 0, 0);
                    this.herobrine.world.spawnEntity(zombie);
                    this.herobrine.removeEnergy(10);
                    mobSpawned = zombie;
                    break;
                case "witch":
                    EntityWitch witch = new EntityWitch(this.herobrine.world);
                    witch.setLocationAndAngles(this.herobrine.posX, this.herobrine.posY, this.herobrine.posZ, 0, 0);
                    this.herobrine.world.spawnEntity(witch);
                    this.herobrine.removeEnergy(10);
                    mobSpawned = witch;
                    break;
                case "enderman":
                    EntityEnderman enderman = new EntityEnderman(this.herobrine.world);
                    enderman.setLocationAndAngles(this.herobrine.posX, this.herobrine.posY, this.herobrine.posZ, 0, 0);
                    this.herobrine.world.spawnEntity(enderman);
                    this.herobrine.removeEnergy(10);
                    mobSpawned = enderman;
                    break;
                case "spider":
                    EntitySpider spider = new EntitySpider(this.herobrine.world);
                    spider.setLocationAndAngles(this.herobrine.posX, this.herobrine.posY, this.herobrine.posZ, 0, 0);
                    this.herobrine.world.spawnEntity(spider);
                    this.herobrine.removeEnergy(10);
                    mobSpawned = spider;
                    break;
        }
        herobrine.doneSpawn(mobSpawned);
    }

}
