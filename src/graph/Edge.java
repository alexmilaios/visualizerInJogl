package graph;

public class Edge{
    public  Node from;
    public  Node to;
    
    public Edge(Node from, Node to) {
      this.from = from;
      this.to = to;
      
     //System.out.println(from+"->"+ to);
    }
    
    public boolean equals(Object obj) {
      Edge e = (Edge)obj;
      return e.from == from && e.to == to;
    }
}