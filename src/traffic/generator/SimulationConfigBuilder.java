/*******************************************************************************
 * Copyright (c) 2011 - Jochen Wuttke.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Jochen Wuttke (wuttkej@gmail.com) - initial API and implementation
 ********************************************************************************
 *
 * Created: Dec 3, 2011
 */

package traffic.generator;

import java.util.List;
import java.util.Random;

import org.jdom.Content;
import org.jdom.DefaultJDOMFactory;
import org.jdom.Document;
import org.jdom.Element;

/**
 * @author Jochen Wuttke - wuttkej@gmail.com
 *
 */
class SimulationConfigBuilder {
	
	private static final String DEFAULT_SPEED_STRATEGY = "traffic.strategy.LinearSpeedStrategy";
	private static final String DEFAULT_CAR_STRATEGY = "traffic.strategy.DijkstraCarStrategy";
	
	private DefaultJDOMFactory factory = new DefaultJDOMFactory();
	private Random random;
	
	SimulationConfigBuilder() {
		random = new Random();
	}
	
	SimulationConfigBuilder( long seed ) {
		random = new Random( seed );
	}
	
	Document build( ConfigurationOptions opts ) {
		Element sim = factory.element( "simulation" );
		factory.addContent(sim, buildGraph( opts.getNumNodes(), opts.getNodeDelay(), opts.getDegreeProb() ) );
		factory.addContent(sim, buildCars( opts.getNumCars(), opts.getStrategies(), opts.getNumNodes() ) );
		return factory.document( sim );
	}
	
	/**
	 * @param numCars
	 * @param strategies
	 * @param numNodes TODO
	 * @return
	 */
	Content buildCars(int numCars, List<String> strategies, int numNodes) {
		Element cars = factory.element( "cars" );
		cars.setAttribute( "default_strategy", DEFAULT_CAR_STRATEGY);
		for( int i = 0; i < numCars; i++ ) {
			cars.addContent( buildCar(strategies, i, numNodes) );
		}
		return cars;
	}

	/**
	 * @param strategies
	 * @param i
	 * @param numNodes TODO
	 * @return
	 */
	Element buildCar(List<String> strategies, int i, int numNodes) {
		Element car = factory.element( "car" );
		car.setAttribute( "id", "" + i );
		int s = randomNode( numNodes );
		car.setAttribute( "start", "" + s );
		car.setAttribute( "end", "" + randomNode( s, numNodes ) );
		car.setAttribute( "strategy", randomStrategy( strategies ) );
		return car;
	}

	/**
	 * @param strategies
	 * @return
	 */
	private String randomStrategy(List<String> strategies) {
		return strategies.get( random.nextInt( strategies.size() ) );
	}

	/**
	 * @param s
	 * @return
	 */
	private int randomNode(int s, int maxValue) {
		//TODO: this can cause an infinite loop if there is only one node in the system!!!!!
		int n;
		do {
			n = randomNode( maxValue );
		} while ( n == s );
		return n;
	}

	/**
	 * @return
	 */
	private int randomNode( int maxValue) {
		return random.nextInt(maxValue);
	}

	/**
	 * @param numNodes
	 * @param nodeDelay
	 * @param degreeProb
	 * @return
	 */
	Content buildGraph(int numNodes, int[] nodeDelay, double degreeProb) {
		Element graph = factory.element( "graph" );
		graph.setAttribute( "default_strategy", DEFAULT_SPEED_STRATEGY);
		for( int i = 0; i < numNodes; i++ ) {
			graph.addContent( buildNode(i) );
		}
		return graph;
	}

	/**
	 * @param graph
	 * @param i
	 */
	Element buildNode( int i) {
		Element node = factory.element( "node" );
		node.setAttribute( "id", "" + i );
		node.setAttribute( "neighbors", randomizeNeighbors() );
		return node;
	}

	/**
	 * @return
	 */
	private String randomizeNeighbors() {
		return "";
	}

}
