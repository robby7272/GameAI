package com.idtech.entity.HerobrineGOAP;

import com.idtech.entity.Herobrine;
import com.javagoap.src.javaGOAP.*;

import static com.idtech.BaseMod.printInfo;

import java.util.Queue;

public class HerobrineAgent extends GoapAgent {

    GoapPlanner planner; //Added
    Herobrine target;

    public HerobrineAgent(IGoapUnit assignedUnit, Herobrine target) {
        super(assignedUnit);
        this.target = target;
        planner = generatePlannerObject();
        printInfo("planner: " + planner);
    }

    @Override
    protected GoapPlanner generatePlannerObject() {
        return new HerobrinePlanner();
    }

    public Queue<GoapAction> getPlan(){
        printInfo("GETTING PLAN");
        this.assignNewUnit(new HerobrineUnit(target));
        Queue<GoapAction> newPlan = planner.plan(getAssignedGoapUnit());
        printInfo("Plan: " + newPlan);
        return newPlan;
    }
}
