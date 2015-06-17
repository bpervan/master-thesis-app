package hr.bpervan.mt.space;

import java.util.List;

public interface SPAlgorithm {
	public List<Node> getShortestPath(Graph g, String startNode, String endNode);
}
