package com.idtech.entity.HerobrineGOAP.actions;

import static com.idtech.BaseMod.printInfo;

public class CreeperSpawn {
    public boolean spawned = false;

    public boolean isSpawned() {
        return this.spawned;
    }
    public boolean spawn() {
        //EntityCreeper creeper = new EntityCreeper(this.entity.worldObj);
        //EntityRegistry.addSpawn()
        printInfo("Spawn");
        this.spawned = true;
        return true;
    }
}