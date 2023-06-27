package com.idtech.entity.HerobrineGOAP.actions;

import com.idtech.customMobAI.SpawnMob;
import com.idtech.customMobAI.SpecialAction;
import com.idtech.entity.Herobrine;
import com.javagoap.src.javaGOAP.GoapAction;
import com.javagoap.src.javaGOAP.GoapState;
import com.javagoap.src.javaGOAP.IGoapUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;

import java.util.List;

public class CustomAction extends GoapAction {
        private final Herobrine herobrine;
        private final EntityMob mob;
        private final String type;
        private final Entity player;
        private List<EntityMob> mobsToUse;
        private boolean isSending;

    public CustomAction(Object target, EntityMob par1Entity, String _type, Entity _player) {
        super(target);
        this.herobrine = (Herobrine) target;
        this.mob = par1Entity;
        this.type = _type;
        this.player = _player;
        switch (type){
            case "skeleton":

                break;
            case "creeper":
                for ( EntityMob mob : herobrine.getAllies()) {
                    if(mob instanceof EntityCreeper){
                        this.mobsToUse.add(mob);
                    }
                }
                break;
            case "zombie":

                break;
            case "witch":

                break;
            case "enderman":

                break;
            case "spider":

                break;
        }
//        this.addPrecondition(new GoapState(-1, "conditionOne", false));
//        this.addEffect(new GoapState(-1, "conditionThree", false));
    }

    @Override
    protected boolean isDone(IGoapUnit goapUnit) {
        return false;
    }

    @Override
    protected boolean performAction(IGoapUnit goapUnit) {
        //do appropriate action on one specific mob from Herobrine ally list
        for (EntityMob mob: mobsToUse) {
            //mob.tasks.addTask(new SpecialAction(mob, type, player, herobrine));
        }
        return false;
    }

    @Override
    protected float generateBaseCost(IGoapUnit goapUnit) {
        return 0;
    }

    @Override
    protected float generateCostRelativeToTarget(IGoapUnit goapUnit) {
        return 0;
    }

    @Override
    protected boolean checkProceduralPrecondition(IGoapUnit goapUnit) {
        return true;
    }

    @Override
    protected boolean requiresInRange(IGoapUnit goapUnit) {
        return false;
    }

    @Override
    protected boolean isInRange(IGoapUnit goapUnit) {
        return false;
    }

    @Override
    protected void reset() {

    }
}
