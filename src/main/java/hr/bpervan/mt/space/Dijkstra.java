package hr.bpervan.mt.space;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra implements SPAlgorithm{
		
	public Dijkstra(){}

	@Override
	public List<Node> getShortestPath(Graph g, String startNode, String endNode) {
		calculateDistances(g, startNode);
		
		List<Node> path = new ArrayList<>();
		for(Node n = g.getNodeByName(endNode); n != null; n = n.getPrevious()){
			path.add(n);
		}
		Collections.reverse(path);
		return path;
	}

	public void calculateDistances(Graph g, String startNode){
		boolean firstIter = true;
		Node currentNode = g.getNodeByName(startNode);
		currentNode.setDistance(0);
		
		Queue<Node> iteratingQ = new PriorityQueue<Node>();
		iteratingQ.add(currentNode);
		
		while(!iteratingQ.isEmpty()){
			if(firstIter){
				firstIter = false;
			} else {
				currentNode = iteratingQ.poll();
			}
			
			for(Map.Entry<Node, Integer> neighbourMap : currentNode.getNeighbours().entrySet()){
				if((neighbourMap.getValue() + currentNode.getDistance()) < neighbourMap.getKey().getDistance()){
					neighbourMap.getKey().setDistance(neighbourMap.getValue() + currentNode.getDistance());
					neighbourMap.getKey().setPrevious(currentNode);
					iteratingQ.add(neighbourMap.getKey());
				}
			}
		}
		
		/** Testing */
		/*for(Node node : g.getGraphNodes()){
			System.out.println(node.getNodeName());
			for(Map.Entry<Node, Integer> entry : node.getNeighbours().entrySet()){
				System.out.println("Vertex: "  + entry.getKey().getNodeName() + "->" + entry.getValue());
			}
			System.out.println("Min distance from A: " + node.getDistance());
			System.out.println("--------------------------");
		}*/
	}
}
