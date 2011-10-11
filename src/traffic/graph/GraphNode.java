package traffic.graph;
/**
 * Jonathan Ramaswamy
 * GraphNode represents the nodes that make up the graph
 */
import java.util.ArrayList;
import java.util.List;


public class GraphNode {
	
	public List<Integer> outgoing; //Nodes that this node has an edge directed towards
	public int nodeNum; //The number of this node on the graph
	public List<Integer> cars; //The cars at this node
	public int oStop; //The original stop time for a car at this node
	public int stop; //The time a car must wait at this node
	
	public GraphNode(int n, int s) {
		nodeNum = n;
		outgoing = new ArrayList<Integer>();
		cars = new ArrayList<Integer>();
		oStop = s;
		stop = oStop;
	}
	
	public void makeNode(int[] c, int[] o) {
		for(int car: c) {
			cars.add(car);
		}
		for(int out: o) {
			outgoing.add(out);
		}
	}
	
	/**
	 * Adds an edge from this node to the given node
	 * @param i
	 */
	public void addEdge(int i) {
		outgoing.add(i);
	}
	
	/**
	 * Returns all nodes this node has an edge directed towards
	 */
	public List<Integer> getNeighbors() {
		List<Integer> clone = new ArrayList<Integer>(outgoing.size());
		for(int i: outgoing) {
			clone.add(i);
		}
		return clone;
	}
	
	public int[] getNeighborsArray() {
		int[] n = new int[outgoing.size()];
		for(int i = 0; i < outgoing.size(); i++) {
			n[i] = outgoing.get(i);
		}
		return n;
	}
	
	/**
	 * Adds the given car number to the list of cars currently at the node
	 */
	public void addCar(int i) {
		cars.add(i);
		stop = oStop * cars.size();
	}
	
	/**
	 * Removes the given car from the list of cars at this node
	 */
	public void removeCar(int i) {
		cars.remove(cars.indexOf(i));
		stop = oStop * cars.size();
	}
	
	/**
	 * Returns the number of turns a car must stay stopped at this node
	 */
	public int getStopNum() {
		return stop;
	}
	
	/**
	 * Returns the number of cars currently at this node
	 */
	public int numCarsAtNode() {
		return cars.size();
	}

}
