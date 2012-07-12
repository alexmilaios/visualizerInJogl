package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Graph {

	private Node[] allNodes;

	public Graph(Node[] allNodes) {
		this.allNodes = allNodes;
	}

	public List<List<Node>> topologicalSort() {
		List<List<Node>> sortedTS = new ArrayList<List<Node>>();
		List<Node> remainingNodes = new ArrayList<Node>();
		for (Node n : allNodes) {
			remainingNodes.add(n);
		}

		List<Node> frontiers = new ArrayList<Node>();
		for (Node n : remainingNodes) {
			if (n.inEdges.isEmpty()) {
				frontiers.add(n);
			}
		}

		remainingNodes.removeAll(frontiers);

		while (!remainingNodes.isEmpty() && !frontiers.isEmpty()) {
			List<Node> newFrontiers = new ArrayList<Node>();
			for (Node n : frontiers) {
				// for each node m with an edge e from n to m do
				for (Iterator<Edge> it = n.outEdges.iterator(); it.hasNext();) {
					// remove edge e from the graph
					Edge e = it.next();
					Node m = e.to;

					m.inEdges.remove(e);// Remove edge from m

					// if m has no other incoming edges then insert m into S
					if (m.inEdges.isEmpty() && !newFrontiers.contains(m)) {
						newFrontiers.add(m);
					}
				}
			}
			
			sortedTS.add(frontiers);			
			frontiers = newFrontiers;
			remainingNodes.removeAll(frontiers);
		}
		
		if (!frontiers.isEmpty()) {
			sortedTS.add(frontiers);
		}
		
		if (!remainingNodes.isEmpty()) {
			System.out.println("Problem:");
			for (Node n : remainingNodes) {
				System.out.print(n.toString() + ";");
			}
			
			System.out.println("----------------");
		}
//
		return sortedTS;
//		// while S is non-empty do
//
//
//
//
//		// // Check to see if all edges are removed
//		// boolean cycle = false;
//		// for (Node n : allNodes) {
//		// if (!n.inEdges.isEmpty()) {
//		// cycle = true;
//		// break;
//		// }
//		// }
//
//		if (cycle) {
//			System.out.println("Cycle present, topological sort not possible");
//			return null;
//		} else {
//			return sortedTS;
//		}
	}

}