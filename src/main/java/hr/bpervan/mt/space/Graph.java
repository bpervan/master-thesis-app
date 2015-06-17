package hr.bpervan.mt.space;

import hr.bpervan.mt.utils.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Graph {
	
	private List<Node> graphNodes;
	
	public Graph(){
		this.graphNodes = new ArrayList<Node>();
	}
	
	public Graph(List<Node> graphNodes){
		this.graphNodes = graphNodes;
	}
	
	public List<Node> getGraphNodes(){
		return this.graphNodes;
	}
	
	public void addGraphNode(Node nodeToAdd){
		this.graphNodes.add(nodeToAdd);
	}
	
	public Node getNodeByName(String name){
		for(Node n : graphNodes){
			if(n.getNodeName().equals(name))
				return n;
		}
		return null;
	}

	public static Graph fromCsv(String relativePath) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(relativePath);

		List<Node> nodes = new ArrayList<>();
		int numNodes = 0;
		try {
			numNodes = StringUtils.countLines(classLoader.getResource(relativePath).getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < numNodes; ++i){
			nodes.add(new Node(i + ""));
		}
		Graph g = new Graph(nodes);

		int i = 0;
		try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){
			String line = null;
			while((line = bufferedReader.readLine()) != null){
				String[] parts = line.split(";");

				for (int j = 0; j < parts.length; j++) {
					if(Integer.compare(Integer.parseInt(parts[j]), 1) == 0){
						System.out.println(i + " - " + j);
						g.getNodeByName(i+"").addNeighbour(g.getNodeByName(j+""), 1);
					}
				}

				i++;
			}
			bufferedReader.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return g;
	}
}
