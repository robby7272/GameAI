package com.idtech.entity.HerobrineGOAP;

import com.idtech.entity.Herobrine;
import com.idtech.entity.HerobrineGOAP.actions.*;
import com.javagoap.src.javaGOAP.GoapAction;
import com.javagoap.src.javaGOAP.GoapState;
import com.javagoap.src.javaGOAP.GoapUnit;


import java.util.Queue;

import static com.idtech.BaseMod.printInfo;

public class HerobrineUnit extends GoapUnit{
    Herobrine herobrineObj;

    public HerobrineUnit(Herobrine target) {
        herobrineObj = target;
        // Importance set at -1 for initial world state
        //Plans are created if herobrine cannot reach the player normally
        //Currently our only goal is that there are no blocks between self and player

        //this.addWorldState(new GoapState(-1, "conditionOne", false));
        //this.addWorldState(new GoapState(-1, "conditionTwo", true));
        //this.addWorldState(new GoapState(-1, "conditionThree", true));
        // Importance matters now.
        //this.addGoalState(new GoapState(1, "conditionOne", true));
        //this.addGoalState(new GoapState(10, "conditionTwo", false));

        //this.addAvailableAction(new CustomAction(target));
        //this.addAvailableAction(new CustomAction2(target));


        this.addWorldState(new GoapState(-1, "can_reach_player", false));
        this.addWorldState(new GoapState(-1, "blocks_in_way", target.firstBlockBetweenPlayer() != null));
        this.addWorldState(new GoapState(-1, "player_above", false));
        this.addWorldState(new GoapState(-1, "mob_in_control", "none"));
        this.addWorldState(new GoapState(-1, "has_50_energy", target.getEnergy() >= 50));
        this.addWorldState(new GoapState(-1, "has_100_energy", target.getEnergy() >= 100));

        this.addGoalState(new GoapState(10, "can_reach_player", true));

        /*
        this.addWorldState(new GoapState(-1, "creeperSpawned", false));
        this.addWorldState(new GoapState(-1, "skeletonSpawned", false));
        this.addWorldState(new GoapState(-1, "zombieSpawned", false));
        this.addWorldState(new GoapState(-1, "endermanSpawned", false));
        this.addWorldState(new GoapState(-1, "spiderSpawned", false));
        this.addWorldState(new GoapState(-1, "witchSpawned", false));

        this.addAvailableAction(new SpawnAction(herobrineObj, "creeper"));
        this.addAvailableAction(new SpawnAction(herobrineObj, "skeleton"));
        this.addAvailableAction(new SpawnAction(herobrineObj, "zombie"));
        this.addAvailableAction(new SpawnAction(herobrineObj, "enderman"));
        this.addAvailableAction(new SpawnAction(herobrineObj, "spider"));
        this.addAvailableAction(new SpawnAction(herobrineObj, "witch"));

        this.addAvailableAction(new MoveAction(herobrineObj, "creeper"));
        this.addAvailableAction(new MoveAction(herobrineObj, "skeleton"));
        this.addAvailableAction(new MoveAction(herobrineObj, "zombie"));
        this.addAvailableAction(new MoveAction(herobrineObj, "enderman"));
        this.addAvailableAction(new MoveAction(herobrineObj, "spider"));
        this.addAvailableAction(new MoveAction(herobrineObj, "witch"));*/
        this.addAvailableAction(new ActionSpawnCreeper(target, "creeper", this));
        //this.addAvailableAction(new ActionSpawnCreeper(target, "spider", this));
        //this.addAvailableAction(new ActionSpawnCreeper(target, "enderman", this));
        this.addAvailableAction(new ActionCreeperExplode(target));
        this.addAvailableAction(new ActionSpiderClimb(target));
    }
    @Override
    public void goapPlanFound(Queue<GoapAction> actions) {
        // Gets called when a new plan was generated.
        //printInfo("Got a new plan: " + actions);
        //herobrineObj.currentPlan = actions;
    }

    @Override
    public void goapPlanFailed(Queue<GoapAction> actions) {
        // Gets called when the current plan fails and therefore prematurely ends.
        // (i.e. performing an action returns false or an Exception is thrown)
        printInfo("Plan failed!");
    }

    @Override
    public void goapPlanFinished() {
        // Gets called when a plan finished executing.
    }

    @Override
    public void update() {
        printInfo("HerobrineUnit update");
    }

    @Override
    public boolean moveTo(Object target) {
        // Is used to provide a way for the Unit to move to a certain target if a GoapAction requires the Unit to be in a certain range to one.
        return false;
    }
}
