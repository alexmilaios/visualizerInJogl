package graphics;

import graph.Node;

public class AreaOfNode {

	public Node node;
	private int center_x, center_y;
	
	public AreaOfNode(Node node, int x, int y) {
		this.node = node;
		center_x = x;
		center_y = y; 
	}
	
	public boolean contains(int x, int y){
		
		if((x >= center_x - 5) && (x <= center_x + 5)
		&& (y >= center_y - 5) && (y <= center_y + 5)){
			return true;
		}
		return false;
	}
	
	public String toString(){
		return node.toString();
	}
}
