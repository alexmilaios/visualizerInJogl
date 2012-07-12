package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sort.Pair;

public class Node{

	public Pair pair;
	public List<Edge> inEdges;
	public List<Edge> outEdges;


	public Node(Pair pair) {
		this.pair = pair;
		inEdges = new ArrayList<Edge>();
		outEdges = new ArrayList<Edge>();
	}

	public Node addEdge(Node node){
		Edge e = new Edge(this, node);
		outEdges.add(e);
		node.inEdges.add(e);
		return this;
	}

	public void printInNeighboors(){
		System.out.println("At node: " + this.toString());
		for(Iterator<Edge> it = outEdges.iterator(); it.hasNext();){
			System.out.print(it.next().to.toString());
		}
		System.out.println();
	}

	@Override
	public String toString() {
		return pair.present();
	}
}