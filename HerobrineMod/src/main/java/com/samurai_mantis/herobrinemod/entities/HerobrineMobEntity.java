package com.samurai_mantis.herobrinemod.entities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.WorldData;

import java.util.ArrayList;

public class HerobrineMobEntity extends Monster {

    ArrayList<Entity> mobs;

    protected HerobrineMobEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }

    void get_mobs(){
        mobs = World.getEntities();
    }
}
