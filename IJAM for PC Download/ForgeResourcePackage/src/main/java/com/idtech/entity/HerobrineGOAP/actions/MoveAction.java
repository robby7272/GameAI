package com.idtech.entity.HerobrineGOAP.actions;

import com.idtech.entity.Herobrine;
import com.javagoap.src.javaGOAP.GoapAction;
import com.javagoap.src.javaGOAP.GoapState;
import com.javagoap.src.javaGOAP.IGoapUnit;
import net.minecraft.entity.monster.EntityMob;

public class MoveAction extends GoapAction {

    private final Herobrine herobrine;
    private String type;
    private final EntityMob follower;
    private boolean isDone;

    //Precondition: Herobrine has enough energy. There is at least one of the selected mob
    //Postcondition: The selected mob plans to move towards the player. Energy depletes
    public MoveAction(Object target, String _type) {
        super(target);
        this.herobrine = (Herobrine) target;
        this.type = _type;
        this.follower = null;
        //spawn based on entity passed in
        switch (type){
            case "skeleton":
                this.addEffect(new GoapState(-1, "skeletonMoving", true));
                this.addPrecondition(new GoapState(-1, "skeletonSpawned", true));
                break;
            case "creeper":
                this.addEffect(new GoapState(-1, "creeperMoving", true));
                this.addPrecondition(new GoapState(-1, "creeperSpawned", true));
                break;
            case "zombie":
                this.addEffect(new GoapState(-1, "zombieMoving", true));
                this.addPrecondition(new GoapState(-1, "zombieSpawned", true));
                break;
            case "witch":
                this.addEffect(new GoapState(-1, "witchMoving", true));
                this.addPrecondition(new GoapState(-1, "witchSpawned", true));
                break;
            case "enderman":
                this.addEffect(new GoapState(-1, "endermanMoving", true));
                this.addPrecondition(new GoapState(-1, "endermanSpawned", true));
                break;
            case "spider":
                this.addEffect(new GoapState(-1, "spiderMoving", true));
                this.addPrecondition(new GoapState(-1, "spiderSpawned", true));
                break;
        }
        //this.addEffect(new javaGOAP.GoapState(-1, "conditionOne", true));
        this.addEffect(new GoapState(-1, "can_reach_player", true));
        //this.addPrecondition(new GoapState(-1, "conditionTwo", true));
    }

    protected boolean isDone(IGoapUnit goapUnit) {
        return this.isDone;
    }

    protected boolean performAction(IGoapUnit goapUnit) {
        this.follower.getNavigator().tryMoveToEntityLiving(this.herobrine.getAttackTarget(), 1D);
        this.isDone = true;
        return true;
    }

    protected float generateBaseCost(IGoapUnit goapUnit) {
        return 0;
    }

    protected float generateCostRelativeToTarget(IGoapUnit goapUnit) {
        return 0;
    }

    protected boolean checkProceduralPrecondition(IGoapUnit goapUnit) {
        return false;
    }

    protected boolean requiresInRange(IGoapUnit goapUnit) {
        return false;
    }

    protected boolean isInRange(IGoapUnit goapUnit) {
        return false;
    }

    protected void reset() {}
}
