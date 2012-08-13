package graphics;

import graph.Node;

import java.util.Iterator;
import java.util.List;

import sort.Pair;

public class NodeWindow implements Runnable {

	private AreaOfNode point;
	private List<List<Node>> sortedGraph;
	private Pair pointId;
	
	public NodeWindow(AreaOfNode node,List<List<Node>> sortedGraph) {
		this.point= node;
		this.sortedGraph = sortedGraph;
		pointId = getOriginalTime();
	}
	
	private Pair getOriginalTime() {
		int level = point.node.pair.getTime() + 1,time = -1,node = point.node.pair.getNode();
		List<Node> layer = sortedGraph.get(level);
		Iterator<Node> itr = layer.iterator();
		while(itr.hasNext()) {
			Node current = itr.next();
			if(current.pair.getNode() == node)
				time = current.pair.getTime();
		}
		return new Pair(node, time);
	}
	
	@Override
	public void run() {
		System.out.println(point + " = " + pointId.present());
	}

}
