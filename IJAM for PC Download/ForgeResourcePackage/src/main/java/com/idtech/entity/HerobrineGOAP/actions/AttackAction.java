package com.idtech.entity.HerobrineGOAP.actions;

import com.idtech.entity.Herobrine;
import com.javagoap.src.javaGOAP.GoapAction;
import com.javagoap.src.javaGOAP.GoapState;
import com.javagoap.src.javaGOAP.IGoapUnit;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;

import static com.idtech.BaseMod.printInfo;

public class AttackAction extends GoapAction {

    //Precondition: Herobrine has enough energy
    //Postcondition: The selected mob gets spawned. Energy depletes

    /*
    public SpawnAction(Object target, String entity) {
        super(target);
        this.addEffect(new GoapState(-1, "mobNearby" + entity, true));
        this.addPrecondition(new GoapState(-1, "energyLevel", 100));
    }
    */

    private final Herobrine herobrine;
    private String type;
    private boolean isDone;

    public AttackAction(Object target, String _type) {
        super (target);
        this.herobrine = (Herobrine) target;
        this.type = _type;
        switch (type){
            case "skeleton":
                this.addEffect(new GoapState(-1, "skeletonAttacking", true));
                this.addPrecondition(new GoapState(-1, "skeletonSpawned", true));
                break;
            case "creeper":
                this.addEffect(new GoapState(-1, "creeperAttacking", true));
                this.addPrecondition(new GoapState(-1, "creeperSpawned", true));
                break;
            case "zombie":
                this.addEffect(new GoapState(-1, "zombieAttacking", true));
                this.addPrecondition(new GoapState(-1, "zombieSpawned", true));
                break;
            case "witch":
                this.addEffect(new GoapState(-1, "witchAttacking", true));
                this.addPrecondition(new GoapState(-1, "witchSpawned", true));
                break;
            case "enderman":
                this.addEffect(new GoapState(-1, "endermanAttacking", true));
                this.addPrecondition(new GoapState(-1, "endermanSpawned", true));
                break;
            case "spider":
                this.addEffect(new GoapState(-1, "spiderAttacking", true));
                this.addPrecondition(new GoapState(-1, "spiderSpawned", true));
                break;
        }
        //this.addEffect(new GoapState(-1, "can_reach_player", true)); // remove later

    }
    // Return true when the action is finished.
    protected boolean isDone(IGoapUnit goapUnit) {
        return this.isDone;
    }
    // Perform the action itself. Return true as long as the action is performed.
    protected boolean performAction(IGoapUnit goapUnit) {
        printInfo("Spawn 123");
        herobrine.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this.herobrine, EntityPlayer.class, false));
        return false;
    }
    // Return the general base cost of the action itself.
    protected float generateBaseCost(IGoapUnit goapUnit) {
        return 1;
    }
    // Return the cost based on the distance to the target.
    protected float generateCostRelativeToTarget(IGoapUnit goapUnit) {
        return 1;
    }
    // Check if the action can be performed (used for planning and while actual performing)
    protected boolean checkProceduralPrecondition(IGoapUnit goapUnit) {
        return true;
    }
    // Return true if the action requires the Unit to be in a certain range to the target itself.
    protected boolean requiresInRange(IGoapUnit goapUnit) {
        return false;
    }
    // Is only called when requiresInRange() returns true. Return true if the Unit is in range of the target.
    protected boolean isInRange(IGoapUnit goapUnit) {
        return false;
    }
    // General reset function called when isDone() returns true.
    protected void reset() {
        //(this.target).spawned = false;
    }
}
