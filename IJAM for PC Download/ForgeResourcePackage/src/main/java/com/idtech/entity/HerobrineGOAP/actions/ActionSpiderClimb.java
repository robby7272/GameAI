package com.idtech.entity.HerobrineGOAP.actions;


import com.idtech.entity.Herobrine;
import com.javagoap.src.javaGOAP.GoapAction;
import com.javagoap.src.javaGOAP.IGoapUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.math.BlockPos;

public class ActionSpiderClimb extends GoapAction {
    //I think target would represent the herobrine object here
    public ActionSpiderClimb(Herobrine target) {
        super(target);
        this.addPrecondition(-1, "can_reach_player", false);
        this.addPrecondition(-1, "blocks_in_way", false);
        this.addPrecondition(-1, "player_above", true);
        this.addPrecondition(-1, "mob_in_control", "spider");
        this.addEffect(-1, "can_reach_player", true);
    }

    protected boolean isDone(IGoapUnit goapUnit) {
        return true; //Return true if the creeper has exploded
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
