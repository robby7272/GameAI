package com.javagoap.src.tests;

import com.javagoap.src.javaGOAP.GoapState;
import com.javagoap.src.javaGOAP.IGoapUnit;

/**
 * TestActionTwo.java --- Second TestAction.
 * 
 * @author P H - 13.03.2017
 *
 */
public class TestActionTwo extends TestActionOne {

	public TestActionTwo(Object target) {
		super(target);

		this.addPrecondition(new GoapState(0, "step", true));
		this.addEffect(new GoapState(0, "goal", true));
	}

	@Override
	protected float generateBaseCost(IGoapUnit goapUnit) {
		return 1;
	}

}
