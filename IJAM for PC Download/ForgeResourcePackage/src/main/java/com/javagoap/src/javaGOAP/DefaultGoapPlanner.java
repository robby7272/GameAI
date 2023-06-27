package com.javagoap.src.javaGOAP;

import com.javagoap.src.javaGOAP.graph.DirectedWeightedGraph;
import com.javagoap.src.javaGOAP.graph.IWeightedGraph;
import com.javagoap.src.javaGOAP.graph.WeightedEdge;

import java.util.Queue;

/**
 * DefaultGoapPlanner.java --- The default implementation of the GoapPlanner.
 * 
 * @author P H - 15.03.2017
 *
 */
public class DefaultGoapPlanner extends GoapPlanner {

	@Override
	protected <EdgeType extends WeightedEdge> IWeightedGraph<GraphNode, EdgeType> generateGraphObject() {
		return new DirectedWeightedGraph<GraphNode, EdgeType>();
	}
}
