package com.idtech.entity.HerobrineGOAP.actions;

import com.idtech.entity.Herobrine;
import com.javagoap.src.javaGOAP.GoapAction;
import com.javagoap.src.javaGOAP.GoapState;
import com.javagoap.src.javaGOAP.IGoapUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.math.BlockPos;

public class ActionSpawnCreeper extends GoapAction {

    //Precondition: Herobrine has enough energy
    //Postcondition: The selected mob gets spawned. Energy depletes
    public ActionSpawnCreeper(Herobrine target, String mob_type, IGoapUnit goapUnit) {
        super(target);
        Object has_100_energy = false;
        for(GoapState i : goapUnit.getWorldState()){
            if(i.effect == "has_100_energy"){
                has_100_energy = i.value;
            }
        }
        //this.addPrecondition(-1, "has_50_energy", true);
        this.addEffect(-1, "mob_in_control", "creeper");
        //this.addEffect(-1, "has_50_energy", (boolean) has_100_energy);
        //this.addEffect(-1, "has_100_energy", false);

        //Simple style
            //Initial state: can_reach_player = false
            //Initial state: blocks_in_way = if(blocks_in_way > 0) <- Will pretty much always return true if player is not in HB's line of sight
            //Initial state: player_above = if(player is more than 1 block above
            //Goal state: can_reach_player = true
        //Actions (Most of them are repeating actions)
            //CreeperExplosions
                //Precondition: can_reach_player = false
                //Precondition: blocks_in_way = true
                //Precondition: player_above = false
                //Precondition: mob_in_control = creeper
                //Effect: blocks_in_way = false
                //Effect: can_reach_player = true
            //SpiderCrawl
                //Precondition: can_reach_player = false
                //Precondition: blocks_in_way = false
                //Precondition: player_above = true
                //Precondition: mob_in_control = spider
                //Effect: can_reach_player = true
            //EndermanTP
                //Precondition: can_reach_player = false
                //Precondition: blocks_in_way = true
                //Precondition: player_above = true
                //Precondition: mob_in_control = enderman
                //Effect: can_reach_player = true
            //ActionSpawn
                //Can't really involve energy because that would require an effect that isn't a boolean
                //Without spawning, this would just be an if statement really, not much of a planner

        //This entire system was clearly meant to only use booleans or simple non-addable values
        //Our options are...
            //1. Continue banging my head against the wall with this, in hopes that the planner will work.
            //   Need to figure out how to add an int to an object
            //2. Switch to a more simple style of planning, which just has task 'mine with creepers' to use
            //   an AI task that spawns creepers and blows them up until HB has no energy left. Will need more
            //   mobs to truly be interesting to watch
            //3. Abandon this goap entirely and implement a version of pyhop instead. Will need to create methods
            //   This is very likely to work properly but requires a lot of code to be written.
            //   Can define every value as an int, using 0/1 for booleans
            //   Requires pairs, which we can define as arraylists
            //   Also requires function that can take in varying numbers of arguments (Can Java do this? Could be a list again...)
    }

    protected boolean isDone(IGoapUnit goapUnit) {
        return false;
    }

    protected boolean performAction(IGoapUnit goapUnit) {
        return false;
    }

    protected float generateBaseCost(IGoapUnit goapUnit) {
        return 0;
    }

    protected float generateCostRelativeToTarget(IGoapUnit goapUnit) {
        return 0;
    }

    protected boolean checkProceduralPrecondition(IGoapUnit goapUnit) {
        return true;
    }

    protected boolean requiresInRange(IGoapUnit goapUnit) {
        return false;
    }

    protected boolean isInRange(IGoapUnit goapUnit) {
        return false;
    }

    protected void reset() {}
}
