package hr.bpervan.mt.space;

import java.util.HashMap;
import java.util.Map;

/** 
 * @author bpervan
 * 
 * */
public class Node implements Comparable<Node>{
	
	/** HashMap sadrži parove Key - Value gdje su
	 * Key - susjedni èvor ovom èvoru
	 * Value - udaljenost do tog èvora*/
	private Map<Node, Integer> neighbours;
	private String nodeName;
	
	/** Udaljenost od polaznog do ovog èvora*/
	private int distance;
	
	/** Prethodni èvor ovom èvoru koji je u najkraæem
	 * lancu do krajnjeg èvora*/
	private Node previous;
	
	public Node(){
		this.neighbours = new HashMap<Node, Integer>();
		this.distance = Integer.MAX_VALUE;
	}
	/** Constructor */
	public Node(String name){
		this.nodeName = name;
		this.neighbours = new HashMap<Node, Integer>();
		this.distance = Integer.MAX_VALUE;
	}
	
	public Map<Node, Integer> getNeighbours(){
		return this.neighbours;
	}
	
	public void addNeighbour(Node neighbour, int distance){
		neighbours.put(neighbour, distance);
	}

	public String getNodeName(){
		return this.nodeName;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Node getPrevious() {
		return previous;
	}

	public void setPrevious(Node previous) {
		this.previous = previous;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.distance, o.distance);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Node)) return false;

		Node node = (Node) o;

		if (getDistance() != node.getDistance()) return false;
		if (getNeighbours() != null ? !getNeighbours().equals(node.getNeighbours()) : node.getNeighbours() != null)
			return false;
		if (getNodeName() != null ? !getNodeName().equals(node.getNodeName()) : node.getNodeName() != null)
			return false;
		return !(getPrevious() != null ? !getPrevious().equals(node.getPrevious()) : node.getPrevious() != null);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
		/*int result = getNeighbours() != null ? getNeighbours().hashCode() : 0;
		result = 31 * result + (getNodeName() != null ? getNodeName().hashCode() : 0);
		result = 31 * result + getDistance();
		result = 31 * result + (getPrevious() != null ? getPrevious().hashCode() : 0);
		return result;*/
	}

	@Override
	public String toString() {
		/*return "Node{" +
				"neighbours=" + neighbours +
				", nodeName='" + nodeName + '\'' +
				", distance=" + distance +
				", previous=" + previous +
				'}';*/
		return nodeName;
	}
}
