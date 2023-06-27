package com.idtech.entity.HerobrineGOAP.actions;

import com.javagoap.src.javaGOAP.GoapAction;
import com.javagoap.src.javaGOAP.GoapState;
import com.javagoap.src.javaGOAP.IGoapUnit;

public class CustomAction2 extends GoapAction {

    public CustomAction2(Object target) {
        super(target);
        this.addPrecondition(new GoapState(-1, "conditionThree", false));
        this.addEffect(new GoapState(-1, "conditionTwo", false));
    }

    @Override
    protected boolean isDone(IGoapUnit goapUnit) {
        return false;
    }

    @Override
    protected boolean performAction(IGoapUnit goapUnit) {
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
